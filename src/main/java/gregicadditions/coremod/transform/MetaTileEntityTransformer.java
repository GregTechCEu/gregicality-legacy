package gregicadditions.coremod.transform;

import gregicadditions.coremod.GAClassTransformer.ClassMapper;
import gregicadditions.coremod.GAClassTransformer.GAMethodVisitor;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class MetaTileEntityTransformer extends ClassMapper {
    public static final String owner = "gregtech/api/metatileentity/MetaTileEntity";
    public static final String enumface_desc = "Lnet/minecraft/util/EnumFacing;";
    public static final String spin = "spin";

    public static final MetaTileEntityTransformer INSTANCE = new MetaTileEntityTransformer();

    private MetaTileEntityTransformer() {
        // NO-OP
    }

    @Override
    protected int getWriteFlags() {
        return 1;
    }

    @Override
    protected ClassVisitor getClassMapper(ClassVisitor downstream) {
        return new TransformerMetaTileEntity(Opcodes.ASM5, downstream);
    }

    private static class TransformerMetaTileEntity extends ClassVisitor {

        TransformerMetaTileEntity(int api, ClassVisitor cv) {
            super(api, cv);
        }

        private boolean isFieldPresent;

        @Override
        public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
            if (name.equals(spin)) {
                isFieldPresent = true;
            }
            return super.visitField(access, name, desc, signature, value);
        }

        @Override
        public void visitEnd() {
            if (!isFieldPresent) {
                super.visitField(Opcodes.ACC_PUBLIC, spin, enumface_desc, null, null).visitEnd();
            }
            super.visitEnd();
        }


        @Override
        public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
            if(name.equals("placeCoverOnSide")) {
                return new TransformPlaceCoverOnSide(api, super.visitMethod(access, name, desc, signature, exceptions));
            } else if (name.equals("<init>")) {
                return new TransformInit(api, super.visitMethod(access, name, desc, signature, exceptions));
            } else if (name.equals("writeInitialSyncData")){
                return new TransformInitial(api, super.visitMethod(access, name, desc, signature, exceptions), true);
            } else if (name.equals("receiveInitialSyncData")){
                return new TransformInitial(api, super.visitMethod(access, name, desc, signature, exceptions), false);
            } else if (name.equals("writeToNBT")) {
                return new TransformNBT(api, super.visitMethod(access, name, desc, signature, exceptions), true);
            } else if (name.equals("readFromNBT")) {
                return new TransformNBT(api, super.visitMethod(access, name, desc, signature, exceptions), false);
            } else if (name.equals("receiveCustomData")) {
                return new TransformReceiveCustomData(api, super.visitMethod(access, name, desc, signature, exceptions));
            }
            return super.visitMethod(access, name, desc, signature, exceptions);
        }


    }

    private static class TransformReceiveCustomData extends GAMethodVisitor {

        TransformReceiveCustomData(int api, MethodVisitor mv) {
            super(api, mv);
        }

        @Override
        public void visitInsn(int opcode) {
            super.visitVarInsn(Opcodes.ALOAD, 0);
            super.visitVarInsn(Opcodes.ILOAD, 1);
            super.visitVarInsn(Opcodes.ALOAD, 2);
            super.injectStaticMethod(GTCEHooks, "receiveCustomData");
            super.visitInsn(opcode);
        }
    }

    private static class TransformPlaceCoverOnSide extends GAMethodVisitor {

        TransformPlaceCoverOnSide(int api, MethodVisitor mv) {
            super(api, mv);
        }

        @Override
        public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
            if (opcode == Opcodes.INVOKEVIRTUAL && name.equals("canPlaceCoverOnSide")) {
                this.visitVarInsn(Opcodes.ALOAD, 4);
                super.injectStaticMethod(GTCEHooks, "canPlaceCoverOnSide2");
            } else {
                super.visitMethodInsn(opcode, owner, name, desc, itf);
            }
        }
    }

    private static class TransformInit extends GAMethodVisitor {

        TransformInit(int api, MethodVisitor mv) {
            super(api, mv);
        }

        @Override
        public void visitInsn(int opcode) {
            if (opcode == Opcodes.RETURN) {
                super.visitVarInsn(Opcodes.ALOAD, 0);
                super.visitFieldInsn(Opcodes.GETSTATIC, "net/minecraft/util/EnumFacing", "NORTH", enumface_desc);
                super.visitFieldInsn(Opcodes.PUTFIELD, owner, spin, enumface_desc);
            }
            super.visitInsn(opcode);
        }

    }

    private static class TransformInitial extends GAMethodVisitor {

        boolean write;

        TransformInitial(int api, MethodVisitor mv, boolean isWrite) {
            super(api, mv);
            write = isWrite;
        }

        @Override
        public void visitInsn(int opcode) {
            if (opcode == Opcodes.RETURN) {
                if (write) {
                    super.visitVarInsn(Opcodes.ALOAD, 1);
                    super.visitVarInsn(Opcodes.ALOAD,0);
                    super.visitFieldInsn(Opcodes.GETFIELD, owner, spin, enumface_desc);
                    super.injectStaticMethod(GTCEHooks, "writeSpinBuf");
                } else {
                    super.visitVarInsn(Opcodes.ALOAD, 0);
                    super.visitVarInsn(Opcodes.ALOAD, 1);
                    super.injectStaticMethod(GTCEHooks, "readSpinBuf");
                    super.visitFieldInsn(Opcodes.PUTFIELD, owner, spin, enumface_desc);
                }
            }
            super.visitInsn(opcode);
        }

    }

    private static class TransformNBT extends GAMethodVisitor {

        boolean write;

        TransformNBT(int api, MethodVisitor mv, boolean isWrite) {
            super(api, mv);
            write = isWrite;
        }

        @Override
        public void visitInsn(int opcode) {
            if (opcode == Opcodes.RETURN) {
                 if (!write) {
                    super.visitVarInsn(Opcodes.ALOAD, 0);
                    super.visitVarInsn(Opcodes.ALOAD, 1);
                     super.injectStaticMethod(GTCEHooks, "readSpinNBT");
                    super.visitFieldInsn(Opcodes.PUTFIELD, owner, spin, enumface_desc);
                }
            } else if (opcode == Opcodes.ARETURN){
                if (write) {
                    super.visitVarInsn(Opcodes.ALOAD,0);
                    super.visitFieldInsn(Opcodes.GETFIELD, owner, spin, enumface_desc);
                    super.injectStaticMethod(GTCEHooks, "writeSpinNBT");
                }
            }
            super.visitInsn(opcode);
        }

    }
}
