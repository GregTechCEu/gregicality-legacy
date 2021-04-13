package gregicadditions.coremod.transform;

import gregicadditions.coremod.GAClassTransformer.ClassMapper;
import gregicadditions.coremod.GAClassTransformer.GAMethodVisitor;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class TileEntityControllerTransformer extends ClassMapper {

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

    private static class TransformFindConnectedBlocksForClient extends GAMethodVisitor {

        TransformFindConnectedBlocksForClient(int api, MethodVisitor mv) {
            super(api, mv);
        }

        @Override
        public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
            if (opcode == Opcodes.INVOKEVIRTUAL && owner.equals("net/minecraft/block/Block") && (name.equals("getItem") || name.equals("func_185473_a"))) {
                super.injectStaticMethod(XNetHooks, "getItem");
            } else {
                super.visitMethodInsn(opcode, owner, name, desc, itf);
            }
        }

    }
}
