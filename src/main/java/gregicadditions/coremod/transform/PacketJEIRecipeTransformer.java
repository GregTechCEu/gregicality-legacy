package gregicadditions.coremod.transform;

import gregicadditions.coremod.GAClassTransformer.ClassMapper;
import gregicadditions.coremod.GAClassTransformer.GAMethodVisitor;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class PacketJEIRecipeTransformer extends ClassMapper {

    public static final PacketJEIRecipeTransformer INSTANCE = new PacketJEIRecipeTransformer();

    private PacketJEIRecipeTransformer() {
        // NO-OP
    }

    @Override
    protected ClassVisitor getClassMapper(ClassVisitor downstream) {
        return new TransformPacketJEIRecipe(Opcodes.ASM5, downstream);
    }

    private static class TransformPacketJEIRecipe extends ClassVisitor {

        TransformPacketJEIRecipe(int api, ClassVisitor cv) {
            super(api, cv);
        }

        @Override
        public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
            if (name.equals("serverPacketData")) {
                return new TransformServerPacketData(api, super.visitMethod(access, name, desc, signature, exceptions));
            }
            return super.visitMethod(access, name, desc, signature, exceptions);
        }

    }

    private static class TransformServerPacketData extends GAMethodVisitor {

        TransformServerPacketData(int api, MethodVisitor mv) {
            super(api, mv);
        }

        @Override
        public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
            if (opcode == Opcodes.INVOKESTATIC && owner.equals("appeng/util/Platform") && name.equals("poweredExtraction")) {
                super.injectStaticMethod(AE2Hooks, "poweredExtraction");
            } else if (opcode == Opcodes.INVOKEVIRTUAL && owner.equals("appeng/util/inv/AdaptorItemHandler") && name.equals("removeItems")) {
                super.injectStaticMethod(AE2Hooks, "removeItems");
            } else {
                super.visitMethodInsn(opcode, owner, name, desc, itf);
            }
        }

    }
}
