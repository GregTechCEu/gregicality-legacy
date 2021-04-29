package gregicadditions.coremod.transform;

import gregicadditions.coremod.GAClassTransformer.ClassMapper;
import gregicadditions.coremod.GAClassTransformer.GAMethodVisitor;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class BlockPatternTransformer extends ClassMapper {

    public static final BlockPatternTransformer INSTANCE = new BlockPatternTransformer();

    private BlockPatternTransformer() {
        // NO-OP
    }

    @Override
    protected ClassVisitor getClassMapper(ClassVisitor downstream) {
        return new TransformerBlockPattern(Opcodes.ASM5, downstream);
    }

    @Override
    protected int getWriteFlags() {
        return 1;
    }

    private static class TransformerBlockPattern extends ClassVisitor {
        private boolean isFieldPresent;

        TransformerBlockPattern(int api, ClassVisitor cv) {
            super(api, cv);
        }

        @Override
        public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
            if (name.equals("spin")) {
                isFieldPresent = true;
            }
            return super.visitField(access, name, desc, signature, value);
        }

        @Override
        public void visitEnd() {
            if (!isFieldPresent) {
                super.visitField(Opcodes.ACC_PUBLIC, "spin", "Lnet/minecraft/util/EnumFacing;", null, null).visitEnd();
            }
            super.visitEnd();
        }

        @Override
        public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
            if (name.equals("checkPatternAt")) {
                return new TransformCheckPatternAt(api, super.visitMethod(access, name, desc, signature, exceptions));
            }
            return super.visitMethod(access, name, desc, signature, exceptions);
        }


    }

    private static class TransformCheckPatternAt extends GAMethodVisitor {

        TransformCheckPatternAt(int api, MethodVisitor mv) {
            super(api, mv);
        }

        @Override
        public void visitCode() {
            super.visitVarInsn(Opcodes.ALOAD,0);
            super.visitVarInsn(Opcodes.ALOAD,1);
            super.visitVarInsn(Opcodes.ALOAD,2);
            super.visitMethodInsn(Opcodes.INVOKESTATIC,
                    "gregicadditions/utils/BlockPatternChecker",
                    "getSpin",
                    "(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/util/EnumFacing;",
                    false);
            super.visitFieldInsn(Opcodes.PUTFIELD, "gregtech/api/multiblock/BlockPattern", "spin", "Lnet/minecraft/util/EnumFacing;");
            super.visitCode();
        }

        @Override
        public void visitVarInsn(int opcode, int var) {
            super.visitVarInsn(opcode, var);
        }

        @Override
        public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
            if (opcode == Opcodes.INVOKESPECIAL && name.equals("setActualRelativeOffset")) {
                super.visitVarInsn(Opcodes.ALOAD,0);
                super.visitFieldInsn(Opcodes.GETFIELD, "gregtech/api/multiblock/BlockPattern", "spin", "Lnet/minecraft/util/EnumFacing;");

                super.visitVarInsn(Opcodes.ALOAD,0);
                super.visitFieldInsn(Opcodes.GETFIELD, "gregtech/api/multiblock/BlockPattern", "structureDir", "[Lgregtech/api/multiblock/BlockPattern$RelativeDirection;");

                super.visitMethodInsn(Opcodes.INVOKESTATIC,
                        "gregicadditions/utils/BlockPatternChecker",
                        "setActualRelativeOffset",
                        "(Lnet/minecraft/util/math/BlockPos$MutableBlockPos;IIILnet/minecraft/util/EnumFacing;Lnet/minecraft/util/EnumFacing;[Lgregtech/api/multiblock/BlockPattern$RelativeDirection;)Lnet/minecraft/util/math/BlockPos$MutableBlockPos;",
                        false);
                super.visitInsn(Opcodes.POP);
            } else {
                super.visitMethodInsn(opcode, owner, name, desc, itf);
            }
        }

        @Override
        public void visitMaxs(int maxStack, int maxLocals) {
            super.visitMaxs(maxStack, maxLocals);
        }
    }
}
