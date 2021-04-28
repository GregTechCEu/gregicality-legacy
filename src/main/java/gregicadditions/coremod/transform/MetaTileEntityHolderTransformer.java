package gregicadditions.coremod.transform;

import gregicadditions.coremod.GAClassTransformer.ClassMapper;
import gregicadditions.coremod.GAClassTransformer.GAMethodVisitor;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class MetaTileEntityHolderTransformer extends ClassMapper {

    public static final MetaTileEntityHolderTransformer INSTANCE = new MetaTileEntityHolderTransformer();

    private MetaTileEntityHolderTransformer() {
        // NO-OP
    }

    @Override
    protected ClassVisitor getClassMapper(ClassVisitor downstream) {
        return new TransformerMetaTileEntityHolder(Opcodes.ASM5, downstream);
    }

    private static class TransformerMetaTileEntityHolder extends ClassVisitor {

        TransformerMetaTileEntityHolder(int api, ClassVisitor cv) {
            super(api, cv);
        }

        @Override
        public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
            if (name.equals("shouldRenderInPass")) {
                return new TransformShouldRenderInPass(api, super.visitMethod(access, name, desc, signature, exceptions));
            } else if (name.equals(("hasFastRenderer"))) {
                return new TransformHasFastRenderer(api, super.visitMethod(access, name, desc, signature, exceptions));
            }
            return super.visitMethod(access, name, desc, signature, exceptions);
        }

    }

    private static class TransformShouldRenderInPass extends GAMethodVisitor {

        TransformShouldRenderInPass(int api, MethodVisitor mv) {
            super(api, mv);
        }

        @Override
        public void visitInsn(int opcode) {
            if (opcode == Opcodes.ICONST_0) {
                this.visitVarInsn(Opcodes.ALOAD, 0);
                this.visitVarInsn(Opcodes.ILOAD, 1);
                super.injectStaticMethod(GTCEHooks, "shouldCoverRenderPass");
            } else {
                super.visitInsn(opcode);
            }
        }
    }

    private static class TransformHasFastRenderer extends GAMethodVisitor {

        TransformHasFastRenderer(int api, MethodVisitor mv) {
            super(api, mv);
        }

        @Override
        public void visitInsn(int opcode) {
            //TODO unsafe, require smart implementation
            if (opcode == Opcodes.ICONST_0) {
                this.visitInsn(Opcodes.ICONST_1);
            } else {
                super.visitInsn(opcode);
            }
        }
    }
}
