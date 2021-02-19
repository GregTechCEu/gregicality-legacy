package gregicadditions.coremod.transform;

import gregicadditions.coremod.GAClassTransformer;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class EnergyContainerHandlerTransformer extends GAClassTransformer.ClassMapper {

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
            if (name.equals("changeEnergy")) {
                return new TransformChangeEnergy(api, super.visitMethod(access, name, desc, signature, exceptions));
            }
            return super.visitMethod(access, name, desc, signature, exceptions);
        }

    }

    private static class TransformChangeEnergy extends MethodVisitor {

        TransformChangeEnergy(int api, MethodVisitor mv) {
            super(api, mv);
        }

        @Override
        public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
            if (opcode == Opcodes.INVOKEVIRTUAL && owner.equals("gregtech/api/capability/impl/EnergyContainerHandler") && name.equals("setEnergyStored")) {
                super.visitMethodInsn(Opcodes.INVOKESTATIC,
                        "gregicadditions/coremod/hooks/GregTechCEHooks",
                        "setEnergyStored",
                        "(Lgregtech/api/capability/impl/EnergyContainerHandler;J)V",
                        false);
            } else {
                super.visitMethodInsn(opcode, owner, name, desc, itf);
            }
        }

    }
}
