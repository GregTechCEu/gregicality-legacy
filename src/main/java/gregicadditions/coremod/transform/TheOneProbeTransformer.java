package gregicadditions.coremod.transform;

import gregicadditions.coremod.GAClassTransformer.ClassMapper;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class TheOneProbeTransformer extends ClassMapper {

    public static final TheOneProbeTransformer INSTANCE = new TheOneProbeTransformer();

    private TheOneProbeTransformer() {}

    @Override
    protected ClassVisitor getClassMapper(ClassVisitor cv) {
        return new TransformerTheOneProbe(Opcodes.ASM5, cv);
    }

    private static class TransformerTheOneProbe extends ClassVisitor implements Opcodes {

        TransformerTheOneProbe(int api, ClassVisitor cv) {
            super(api, cv);
        }

        @Override
        public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
            if (access == ACC_PRIVATE && name.equals("showRF"))
                return new TransformShowRF(api, super.visitMethod(access, name, desc, signature, exceptions));
            return super.visitMethod(access, name, desc, signature, exceptions);
        }
    }

    private static class TransformShowRF extends MethodVisitor {

        TransformShowRF(int api, MethodVisitor mv) {
            super(api, mv);
        }

        @Override
        public void visitCode() {

            // TODO

            super.visitCode();
        }
    }
}
