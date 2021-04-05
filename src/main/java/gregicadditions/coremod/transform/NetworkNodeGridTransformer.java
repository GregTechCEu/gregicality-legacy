package gregicadditions.coremod.transform;

import gregicadditions.coremod.GAClassTransformer.ClassMapper;
import gregicadditions.coremod.GAClassTransformer.GAMethodVisitor;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class NetworkNodeGridTransformer extends ClassMapper {

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

    private static class TransformOnRecipeTransfer extends GAMethodVisitor {

        TransformOnRecipeTransfer(int api, MethodVisitor mv) {
            super(api, mv);
        }

        @Override
        public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
            if (opcode == Opcodes.INVOKEINTERFACE && owner.equals("com/raoulvdberge/refinedstorage/api/network/INetwork") && name.equals("extractItem")) {
                super.injectStaticMethod(RSHooks, "extractItem");
            } else if (opcode == Opcodes.INVOKEINTERFACE && owner.equals("com/raoulvdberge/refinedstorage/api/util/IComparer") && name.equals("isEqual")) {
                super.injectStaticMethod(RSHooks, "isEqual");
            } else {
                super.visitMethodInsn(opcode, owner, name, desc, itf);
            }
        }

    }
}