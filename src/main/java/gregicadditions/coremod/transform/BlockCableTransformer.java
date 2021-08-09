package gregicadditions.coremod.transform;

import gregicadditions.coremod.GAClassTransformer.ClassMapper;
import gregicadditions.coremod.GAClassTransformer.GAMethodVisitor;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class BlockCableTransformer extends ClassMapper {

    public static final BlockCableTransformer INSTANCE = new BlockCableTransformer();

    private BlockCableTransformer() {
        // NO-OP
    }

    @Override
    protected int getWriteFlags() {
        return 1;
    }

    @Override
    protected ClassVisitor getClassMapper(ClassVisitor downstream) {
        return new TransformerBlockCable(Opcodes.ASM5, downstream);
    }

    private static class TransformerBlockCable extends ClassVisitor {

        TransformerBlockCable(int api, ClassVisitor cv) {
            super(api, cv);
        }

        @Override
        public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
            if (name.startsWith("getActiveNodeConnections")) {
                return new TransformFindGetActiveNodeConnections(api, super.visitMethod(access, name, desc, signature, exceptions));
            }
            return super.visitMethod(access, name, desc, signature, exceptions);
        }

    }

    private static class TransformFindGetActiveNodeConnections extends GAMethodVisitor {

        TransformFindGetActiveNodeConnections(int api, MethodVisitor mv) {
            super(api, mv);
        }

        @Override
        public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
            if (opcode == Opcodes.INVOKEVIRTUAL && owner.equals("net/minecraft/tileentity/TileEntity") && name.equals("getCapability")) {
                super.visitMethodInsn(Opcodes.INVOKESTATIC,
                        "gregicadditions/coremod/hooks/GregTechCEHooks",
                        "getCapability",
                        "(Lnet/minecraft/tileentity/TileEntity;Lnet/minecraftforge/common/capabilities/Capability;Lnet/minecraft/util/EnumFacing;)Ljava/lang/Object;",
                        false);
            } else {
                super.visitMethodInsn(opcode, owner, name, desc, itf);
            }
        }

    }
}
