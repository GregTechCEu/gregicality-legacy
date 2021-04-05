package gregicadditions.coremod.transform;

import gregicadditions.coremod.GAClassTransformer.ClassMapper;
import gregicadditions.coremod.GAClassTransformer.GAMethodVisitor;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class MetaTileEntityTESRTransformer extends ClassMapper {

    public static final MetaTileEntityTESRTransformer INSTANCE = new MetaTileEntityTESRTransformer();

    private MetaTileEntityTESRTransformer() {
        // NO-OP
    }

    @Override
    protected ClassVisitor getClassMapper(ClassVisitor downstream) {
        return new TransformerMetaTileEntityTESR(Opcodes.ASM5, downstream);
    }

    private static class TransformerMetaTileEntityTESR extends ClassVisitor {

        TransformerMetaTileEntityTESR(int api, ClassVisitor cv) {
            super(api, cv);
        }

        @Override
        public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
            if (name.equals("renderTileEntityFast")) {
                return new TransformRenderTileEntityFast(api, super.visitMethod(access, name, desc, signature, exceptions));
            }
            return super.visitMethod(access, name, desc, signature, exceptions);
        }

    }

    private static class TransformRenderTileEntityFast extends GAMethodVisitor {

        TransformRenderTileEntityFast(int api, MethodVisitor mv) {
            super(api, mv);
        }

        @Override
        public void visitCode() {
            super.visitCode();
            this.visitVarInsn(Opcodes.ALOAD, 1);
            this.visitVarInsn(Opcodes.DLOAD, 2);
            this.visitVarInsn(Opcodes.DLOAD, 4);
            this.visitVarInsn(Opcodes.DLOAD, 6);
            this.visitVarInsn(Opcodes.FLOAD, 8);
            this.visitVarInsn(Opcodes.ALOAD, 11);
            super.injectStaticMethod(GTCEHooks, "renderTileEntityFast");
        }
    }
}
