package gregicadditions.coremod.transform;

import gregicadditions.coremod.GAClassTransformer.ClassMapper;
import gregicadditions.coremod.GAClassTransformer.GAMethodVisitor;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class PreviewRendererTransformer extends ClassMapper {

    public static final PreviewRendererTransformer INSTANCE = new PreviewRendererTransformer();

    private PreviewRendererTransformer() {
        // NO-OP
    }

    @Override
    protected ClassVisitor getClassMapper(ClassVisitor downstream) {
        return new TransformPreviewRenderer(Opcodes.ASM5, downstream);
    }

    private static class TransformPreviewRenderer extends ClassVisitor {

        TransformPreviewRenderer(int api, ClassVisitor cv) {
            super(api, cv);
        }

        @Override
        public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
            if (name.equals("onUse")) {
                return new TransformOnUse(api, super.visitMethod(access, name, desc, signature, exceptions));
            }
            return super.visitMethod(access, name, desc, signature, exceptions);
        }

    }

    private static class TransformOnUse extends GAMethodVisitor {

        TransformOnUse(int api, MethodVisitor mv) {
            super(api, mv);
        }

        @Override
        public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
            if (opcode == Opcodes.INVOKESTATIC && owner.equals("eutros/multiblocktweaker/client/PreviewHandler") && name.equals("getMetaController")) {
                super.visitMethodInsn(Opcodes.INVOKESTATIC,
                        "gregicadditions/coremod/hooks/MultiBlockTweakerHooks",
                        "getMetaController",
                        "(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;)Lgregtech/api/metatileentity/multiblock/MultiblockControllerBase;",
                        false);
            } else {
                super.visitMethodInsn(opcode, owner, name, desc, itf);
            }
        }

    }
}
