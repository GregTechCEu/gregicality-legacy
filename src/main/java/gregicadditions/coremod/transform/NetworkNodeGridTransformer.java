package gregicadditions.coremod.transform;

import gregicadditions.coremod.GAClassTransformer;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class NetworkNodeGridTransformer extends GAClassTransformer.ClassMapper {

    public static final NetworkNodeGridTransformer INSTANCE = new NetworkNodeGridTransformer();

    private NetworkNodeGridTransformer() {
        // NO-OP
    }

    @Override
    protected ClassVisitor getClassMapper(ClassVisitor downstream) {
        return new TransformNetworkNodeGrid(Opcodes.ASM5, downstream);
    }

    private static class TransformNetworkNodeGrid extends ClassVisitor {

        TransformNetworkNodeGrid(int api, ClassVisitor cv) {
            super(api, cv);
        }

        @Override
        public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
            if (name.equals("onRecipeTransfer")) {
                return new TransformOnRecipeTransfer(api, super.visitMethod(access, name, desc, signature, exceptions));
            }
            return super.visitMethod(access, name, desc, signature, exceptions);
        }

    }

    private static class TransformOnRecipeTransfer extends MethodVisitor {

        TransformOnRecipeTransfer(int api, MethodVisitor mv) {
            super(api, mv);
        }

        @Override
        public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
            if (opcode == Opcodes.INVOKEINTERFACE && owner.equals("com/raoulvdberge/refinedstorage/api/network/INetwork") && name.equals("extractItem")) {
                super.visitMethodInsn(Opcodes.INVOKESTATIC,
                        "gregicadditions/coremod/hooks/CoreModHooks",
                        "extractItem",
                        "(Lcom/raoulvdberge/refinedstorage/api/network/INetwork;Lnet/minecraft/item/ItemStack;IILcom/raoulvdberge/refinedstorage/api/util/Action;)Lnet/minecraft/item/ItemStack;",
                        false);
            } else if (opcode == Opcodes.INVOKEINTERFACE && owner.equals("com/raoulvdberge/refinedstorage/api/util/IComparer") && name.equals("isEqual")) {
                super.visitMethodInsn(Opcodes.INVOKESTATIC,
                        "gregicadditions/coremod/hooks/CoreModHooks",
                        "isEqual",
                        "(Lcom/raoulvdberge/refinedstorage/api/util/IComparer;Lnet/minecraft/item/ItemStack;Lnet/minecraft/item/ItemStack;I)Z",
                        false);
            } else {
                super.visitMethodInsn(opcode, owner, name, desc, itf);
            }
        }

    }
}