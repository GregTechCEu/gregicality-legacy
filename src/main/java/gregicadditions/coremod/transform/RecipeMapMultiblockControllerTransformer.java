package gregicadditions.coremod.transform;

import gregicadditions.coremod.GAClassTransformer;
import gregicadditions.coremod.hooks.GregTechCEHooks;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class RecipeMapMultiblockControllerTransformer extends GAClassTransformer.ClassMapper {

    public static final RecipeMapMultiblockControllerTransformer INSTANCE = new RecipeMapMultiblockControllerTransformer();

    private RecipeMapMultiblockControllerTransformer() {
        // NO-OP
    }

    @Override
    protected ClassVisitor getClassMapper(ClassVisitor downstream) {
        return new TransformerRecipeMapMultiblockController(Opcodes.ASM5, downstream);
    }

    @Override
    protected int getWriteFlags() {
        return 1;
    }

    private static class TransformerRecipeMapMultiblockController extends ClassVisitor {

        TransformerRecipeMapMultiblockController(int api, ClassVisitor cv) {
            super(api, cv);
        }

        @Override
        public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
            if (name.equals("renderMetaTileEntity")) {
                return new TransformRenderMetaTileEntity(api, super.visitMethod(access, name, desc, signature, exceptions));
            }
            return super.visitMethod(access, name, desc, signature, exceptions);
        }

    }

    private static class TransformRenderMetaTileEntity extends MethodVisitor {

        TransformRenderMetaTileEntity(int api, MethodVisitor mv) {
            super(api, mv);
        }

        @Override
        public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
            if (opcode == Opcodes.INVOKEVIRTUAL && owner.equals("gregtech/api/render/OrientedOverlayRenderer") && name.equals("render")) {
                super.visitVarInsn(Opcodes.ALOAD,0);
                super.visitFieldInsn(Opcodes.GETFIELD, MetaTileEntityTransformer.owner, MetaTileEntityTransformer.spin, MetaTileEntityTransformer.enumface_desc);
                super.visitMethodInsn(Opcodes.INVOKESTATIC,
                        GregTechCEHooks.hooks,
                        "renderFrontOverlay",
                        "(Lgregtech/api/render/OrientedOverlayRenderer;Lcodechicken/lib/render/CCRenderState;Lcodechicken/lib/vec/Matrix4;[Lcodechicken/lib/render/pipeline/IVertexOperation;Lnet/minecraft/util/EnumFacing;ZLnet/minecraft/util/EnumFacing;)V",
                        false);
            }else {
                super.visitMethodInsn(opcode, owner, name, desc, itf);
            }
        }

    }

}
