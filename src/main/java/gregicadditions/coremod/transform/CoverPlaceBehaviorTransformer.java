package gregicadditions.coremod.transform;

import gregicadditions.coremod.GAClassTransformer.ClassMapper;
import gregicadditions.coremod.GAClassTransformer.GAMethodVisitor;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class CoverPlaceBehaviorTransformer extends ClassMapper {

    public static final CoverPlaceBehaviorTransformer INSTANCE = new CoverPlaceBehaviorTransformer();

    private CoverPlaceBehaviorTransformer() {
        // NO-OP
    }

    @Override
    protected ClassVisitor getClassMapper(ClassVisitor downstream) {
        return new TransformerCoverPlaceBehavior(Opcodes.ASM5, downstream);
    }

    private static class TransformerCoverPlaceBehavior extends ClassVisitor {

        TransformerCoverPlaceBehavior(int api, ClassVisitor cv) {
            super(api, cv);
        }

        @Override
        public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
            if(name.equals("onItemUseFirst")) {
                return new TransformOnItemUseFirst(api, super.visitMethod(access, name, desc, signature, exceptions));
            }
            return super.visitMethod(access, name, desc, signature, exceptions);
        }

    }

    private static class TransformOnItemUseFirst extends GAMethodVisitor {

        TransformOnItemUseFirst(int api, MethodVisitor mv) {
            super(api, mv);
        }

        @Override
        public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
            if (opcode == Opcodes.INVOKEINTERFACE && name.equals("canPlaceCoverOnSide")) {
                this.visitVarInsn(Opcodes.ALOAD, 0);
                super.injectStaticMethod(GTCEHooks, "canPlaceCoverOnSide");
            } else {
                super.visitMethodInsn(opcode, owner, name, desc, itf);
            }
        }
    }
}
