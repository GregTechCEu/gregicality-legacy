package gregicadditions.coremod.transform;

import gregicadditions.coremod.GAClassTransformer;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class TileEntityControllerTransformer extends GAClassTransformer.ClassMapper {

    public static final TileEntityControllerTransformer INSTANCE = new TileEntityControllerTransformer();

    private TileEntityControllerTransformer() {
        // NO-OP
    }

    @Override
    protected ClassVisitor getClassMapper(ClassVisitor downstream) {
        return new TransformerTileEntityController(Opcodes.ASM5, downstream);
    }

    private static class TransformerTileEntityController extends ClassVisitor {

        TransformerTileEntityController(int api, ClassVisitor cv) {
            super(api, cv);
        }

        @Override
        public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
            if (access == 0x1002 && name.startsWith("lambda$findConnectedBlocksForClient$")) {
                return new TransformFindConnectedBlocksForClient(api, super.visitMethod(access, name, desc, signature, exceptions));
            }
            return super.visitMethod(access, name, desc, signature, exceptions);
        }

    }

    private static class TransformFindConnectedBlocksForClient extends MethodVisitor {

        TransformFindConnectedBlocksForClient(int api, MethodVisitor mv) {
            super(api, mv);
        }

        @Override
        public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
            if (opcode == Opcodes.INVOKEVIRTUAL && owner.equals("net/minecraft/block/Block") && (name.equals("getItem") || name.equals("func_185473_a"))) {
                super.visitMethodInsn(Opcodes.INVOKESTATIC,
                        "gregicadditions/coremod/hooks/XNetHooks",
                        "getItem",
                        "(Lnet/minecraft/block/Block;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/state/IBlockState;)Lnet/minecraft/item/ItemStack;",
                        false);
            } else {
                super.visitMethodInsn(opcode, owner, name, desc, itf);
            }
        }

    }
}
