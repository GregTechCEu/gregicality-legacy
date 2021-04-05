package gregicadditions.coremod.transform;

import gregicadditions.coremod.GAClassTransformer.ClassMapper;
import gregicadditions.coremod.GAClassTransformer.GAMethodVisitor;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class RecipeMapMultiblockControllerTransformer extends ClassMapper {

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

    private static class TransformRenderMetaTileEntity extends GAMethodVisitor {

        TransformRenderMetaTileEntity(int api, MethodVisitor mv) {
            super(api, mv);
        }

        @Override
        public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
            if (opcode == Opcodes.INVOKEVIRTUAL && owner.equals("gregtech/api/render/OrientedOverlayRenderer") && name.equals("render")) {
                super.visitVarInsn(Opcodes.ALOAD,0);
                super.visitFieldInsn(Opcodes.GETFIELD, MetaTileEntityTransformer.owner, MetaTileEntityTransformer.spin, MetaTileEntityTransformer.enumface_desc);
                super.injectStaticMethod(GTCEHooks, "renderFrontOverlay");
            }else {
                super.visitMethodInsn(opcode, owner, name, desc, itf);
            }
        }

    }

}
