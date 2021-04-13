package gregicadditions.coremod.transform;

import gregicadditions.coremod.GAClassTransformer.ClassMapper;
import gregicadditions.coremod.GAClassTransformer.GAMethodVisitor;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class EnergyContainerBatteryBufferTransformer extends ClassMapper {

    public static final EnergyContainerBatteryBufferTransformer INSTANCE = new EnergyContainerBatteryBufferTransformer();

    private EnergyContainerBatteryBufferTransformer() {
        // NO-OP
    }

    @Override
    protected ClassVisitor getClassMapper(ClassVisitor downstream) {
        return new TransformerEnergyContainerBatteryBuffer(Opcodes.ASM5, downstream);
    }

    private static class TransformerEnergyContainerBatteryBuffer extends ClassVisitor {

        TransformerEnergyContainerBatteryBuffer(int api, ClassVisitor cv) {
            super(api, cv);
        }

        @Override
        public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
            switch (name) {
                case "acceptEnergyFromNetwork":
                    return new TransformAcceptEnergyFromNetwork(api, super.visitMethod(access, name, desc, signature, exceptions));
                case "update":
                    return new TransformUpdate(api, super.visitMethod(access, name, desc, signature, exceptions));
                case "changeEnergy":
                    return new TransformChangeEnergy(api, super.visitMethod(access, name, desc, signature, exceptions));
            }
            return super.visitMethod(access, name, desc, signature, exceptions);
        }

    }

    private static class TransformAcceptEnergyFromNetwork extends GAMethodVisitor {
        static byte now = 0;

        TransformAcceptEnergyFromNetwork(int api, MethodVisitor mv) {
            super(api, mv);
        }

        @Override
        public void visitInsn(int opcode) {
            if (opcode == Opcodes.LRETURN) {
                if(++now == 2) {
                    this.visitVarInsn(Opcodes.ALOAD, 0);
                    this.visitVarInsn(Opcodes.LLOAD, 2);
                    this.visitVarInsn(Opcodes.LLOAD, 8);
                    super.injectStaticMethod(GTCEHooks, "acceptEnergyFromNetwork");
                }
            }
            super.visitInsn(opcode);
        }
    }

    private static class TransformUpdate extends GAMethodVisitor {
        static byte now = 0;
        TransformUpdate(int api, MethodVisitor mv) {
            super(api, mv);
        }

        @Override
        public void visitVarInsn(int opcode, int var) {
            super.visitVarInsn(opcode, var);
            if (opcode == Opcodes.LSTORE && var == 10) {
                if (++now == 1) {
                    this.visitVarInsn(Opcodes.ALOAD, 0);
                    this.visitVarInsn(Opcodes.LLOAD, 5);
                    this.visitVarInsn(Opcodes.LLOAD, 10);
                    super.injectStaticMethod(GTCEHooks, "update");
                }
            }
        }
    }

    private static class TransformChangeEnergy extends GAMethodVisitor {

        TransformChangeEnergy(int api, MethodVisitor mv) {
            super(api, mv);
        }

        @Override
        public void visitInsn(int opcode) {
            if (opcode == Opcodes.LRETURN) {
                this.visitVarInsn(Opcodes.ALOAD, 0);
                this.visitVarInsn(Opcodes.LLOAD, 7);
                super.injectStaticMethod(GTCEHooks, "changeEnergy");
            }
            super.visitInsn(opcode);
        }
    }
}
