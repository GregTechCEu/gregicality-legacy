package gregicadditions.coremod.transform;

import gregicadditions.coremod.GAClassTransformer;
import org.objectweb.asm.*;

public class PacketJEIRecipeTransformer extends GAClassTransformer.ClassMapper {

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

    private static class TransformServerPacketData extends MethodVisitor {

        TransformServerPacketData(int api, MethodVisitor mv) {
            super(api, mv);
        }

        @Override
        public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
            if (opcode == Opcodes.INVOKESTATIC && owner.equals("appeng/util/Platform") && name.equals("poweredExtraction")) {
                super.visitMethodInsn(Opcodes.INVOKESTATIC,
                        "gregicadditions/coremod/hooks/AppliedEnergistics2Hooks",
                        "poweredExtraction",
                        "(Lappeng/api/networking/energy/IEnergySource;Lappeng/api/storage/IMEMonitor;Lappeng/api/storage/data/IAEItemStack;Lappeng/api/networking/security/IActionSource;)Lappeng/api/storage/data/IAEItemStack;",
                        false);
            } else if (opcode == Opcodes.INVOKEVIRTUAL && owner.equals("appeng/util/inv/AdaptorItemHandler") && name.equals("removeItems")) {
                super.visitMethodInsn(Opcodes.INVOKESTATIC,
                        "gregicadditions/coremod/hooks/AppliedEnergistics2Hooks",
                        "removeItems",
                        "(Lappeng/util/inv/AdaptorItemHandler;ILnet/minecraft/item/ItemStack;Lappeng/util/inv/IInventoryDestination;)Lnet/minecraft/item/ItemStack;",
                        false);
            } else {
                super.visitMethodInsn(opcode, owner, name, desc, itf);
            }
        }

    }
}
