package gregicadditions.coremod.transform;

import gregicadditions.coremod.GAClassTransformer.ClassMapper;
import gregicadditions.coremod.GAClassTransformer.GAMethodVisitor;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class EnergyContainerHandlerTransformer extends ClassMapper {

    public static final EnergyContainerHandlerTransformer INSTANCE = new EnergyContainerHandlerTransformer();

    private EnergyContainerHandlerTransformer() {
        // NO-OP
    }

    @Override
    protected ClassVisitor getClassMapper(ClassVisitor downstream) {
        return new TransformEnergyContainerHandler(Opcodes.ASM5, downstream);
    }

    private static class TransformEnergyContainerHandler extends ClassVisitor {

        TransformEnergyContainerHandler(int api, ClassVisitor cv) {
            super(api, cv);
        }

        @Override
        public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
            if (name.equals("setEnergyStored")) {
                return new TransformSetEnergyStored(api, super.visitMethod(access, name, desc, signature, exceptions));
            }
            return super.visitMethod(access, name, desc, signature, exceptions);
        }

    }

    private static class TransformSetEnergyStored extends GAMethodVisitor {

        TransformSetEnergyStored(int api, MethodVisitor mv) {
            super(api, mv);
        }

        @Override
        public void visitCode() {
            super.visitVarInsn(Opcodes.ALOAD, 0);
            super.visitVarInsn(Opcodes.LLOAD, 1);
            super.injectStaticMethod(GTCEHooks, "setEnergyStored");
            super.visitCode();
        }

    }
}
