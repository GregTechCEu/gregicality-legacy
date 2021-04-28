package gregicadditions.coremod.transform;

import gregicadditions.coremod.GAClassTransformer.ClassMapper;
import gregicadditions.coremod.GAClassTransformer.GAMethodVisitor;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class MultiblockControllerBaseTransformer extends ClassMapper {

    public static final MultiblockControllerBaseTransformer INSTANCE = new MultiblockControllerBaseTransformer();

    private MultiblockControllerBaseTransformer() {
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
        public void visitCode() {
            super.visitVarInsn(Opcodes.ALOAD,0);
            super.visitVarInsn(Opcodes.ALOAD,2);
            super.injectStaticMethod(GTCEHooks, "renderMetaTileEntity");
            super.visitCode();
        }

    }

}
