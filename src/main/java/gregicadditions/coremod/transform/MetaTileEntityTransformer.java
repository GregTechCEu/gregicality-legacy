package gregicadditions.coremod.transform;

import gregicadditions.coremod.GAClassTransformer;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class MetaTileEntityTransformer extends GAClassTransformer.ClassMapper {

    public static final MetaTileEntityTransformer INSTANCE = new MetaTileEntityTransformer();

    private MetaTileEntityTransformer() {
        // NO-OP
    }

    @Override
    protected ClassVisitor getClassMapper(ClassVisitor downstream) {
        return new TransformerMetaTileEntity(Opcodes.ASM5, downstream);
    }

    private static class TransformerMetaTileEntity extends ClassVisitor {

        TransformerMetaTileEntity(int api, ClassVisitor cv) {
            super(api, cv);
        }

        @Override
        public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
            if(name.equals("placeCoverOnSide")) {
                return new TransformPlaceCoverOnSide(api, super.visitMethod(access, name, desc, signature, exceptions));
            }
            return super.visitMethod(access, name, desc, signature, exceptions);
        }

    }

    private static class TransformPlaceCoverOnSide extends MethodVisitor {

        TransformPlaceCoverOnSide(int api, MethodVisitor mv) {
            super(api, mv);
        }

        @Override
        public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
            if (opcode == Opcodes.INVOKEVIRTUAL && name.equals("canPlaceCoverOnSide")) {
                this.visitVarInsn(Opcodes.ALOAD, 4);
                super.visitMethodInsn(Opcodes.INVOKESTATIC,
                        "gregicadditions/coremod/hooks/GregTechCEHooks",
                        "canPlaceCoverOnSide2",
                        "(Lgregtech/api/metatileentity/MetaTileEntity;Lnet/minecraft/util/EnumFacing;Lgregtech/api/cover/CoverBehavior;)Z",
                        false);
            } else {
                super.visitMethodInsn(opcode, owner, name, desc, itf);
            }
        }
    }
}
