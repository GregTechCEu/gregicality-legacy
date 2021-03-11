package gregicadditions.coremod.transform;

import gregicadditions.coremod.GAClassTransformer;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class GTUtilityTransformer extends GAClassTransformer.ClassMapper {

    public static final GTUtilityTransformer INSTANCE = new GTUtilityTransformer();

    private GTUtilityTransformer() {
    }

    @Override
    protected ClassVisitor getClassMapper(ClassVisitor cv) {
        return new TransformerGTUtility(Opcodes.ASM5, cv);
    }

    private static class TransformerGTUtility extends ClassVisitor implements Opcodes {

        TransformerGTUtility(int api, ClassVisitor cv) {
            super(api, cv);
        }

        @Override
        public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
            if (name.equals("formulaHook")) {
                return new TransformFormulaHook(api, super.visitMethod(access, name, desc, signature, exceptions));
            }
            return super.visitMethod(access, name, desc, signature, exceptions);
        }
    }

    private static class TransformFormulaHook extends MethodVisitor implements Opcodes {

        private static final String INSERT_CLASS_NAME = "gregicadditions/coremod/hooks/GregTechCEHooks";
        private static final String INSERT_METHOD_SIGNATURE = "(Lnet/minecraftforge/fluids/FluidStack;Ljava/lang/StringBuilder;)V";
        private static final String INSERT_METHOD_NAME = "getSimpleFluidTooltip";

        TransformFormulaHook(int api, MethodVisitor mv) {
            super(api, mv);
        }

        @Override
        public void visitCode() {

            mv.visitVarInsn(ALOAD, 0); // FluidStack
            mv.visitVarInsn(ALOAD, 1); // StringBuilder

            // statically call getSimpleFluidTooltip(FluidStack, StringBuilder)
            mv.visitMethodInsn(INVOKESTATIC, INSERT_CLASS_NAME, INSERT_METHOD_NAME, INSERT_METHOD_SIGNATURE, false);

            mv.visitCode();
        }
    }
}
