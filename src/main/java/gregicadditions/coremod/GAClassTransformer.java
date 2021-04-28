package gregicadditions.coremod;

import gregicadditions.coremod.transform.*;
import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.*;

import java.lang.reflect.Method;
import java.util.Arrays;

public class GAClassTransformer implements IClassTransformer {
    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        Transform tform;
        switch (transformedName) {
            case "appeng.core.sync.packets.PacketJEIRecipe":
                tform = PacketJEIRecipeTransformer.INSTANCE;
                break;
            case "com.raoulvdberge.refinedstorage.apiimpl.network.node.NetworkNodeGrid":
                tform = NetworkNodeGridTransformer.INSTANCE;
                break;
            case "mcjty.xnet.blocks.controller.TileEntityController":
                tform = TileEntityControllerTransformer.INSTANCE;
                break;
            case "gregtech.api.metatileentity.MetaTileEntityHolder":
                tform = MetaTileEntityHolderTransformer.INSTANCE;
                break;
            case "gregtech.api.render.MetaTileEntityTESR":
                tform = MetaTileEntityTESRTransformer.INSTANCE;
                break;
            case "gregtech.api.capability.impl.EnergyContainerHandler":
                tform = EnergyContainerHandlerTransformer.INSTANCE;
                break;
            case "gregtech.api.capability.impl.EnergyContainerBatteryBuffer":
                tform = EnergyContainerBatteryBufferTransformer.INSTANCE;
                break;
            case "gregtech.common.items.behaviors.CoverPlaceBehavior":
                tform = CoverPlaceBehaviorTransformer.INSTANCE;
                break;
            case "gregtech.api.metatileentity.MetaTileEntity":
                tform = MetaTileEntityTransformer.INSTANCE;
                break;
            case "gregtech.api.multiblock.BlockPattern":
                tform = BlockPatternTransformer.INSTANCE;
                break;
            case "gregtech.api.metatileentity.multiblock.MultiblockControllerBase":
                tform = MultiblockControllerBaseTransformer.INSTANCE;
                break;
            default:
                return basicClass;
        }
        System.out.println("[Gregicality] Transforming class: " + transformedName);
        return tform.transformClass(basicClass);
    }

    public interface Transform {

        byte[] transformClass(byte[] code);

    }

    public static abstract class ClassMapper implements Transform {

        @Override
        public byte[]
        transformClass(byte[] code) {
            ClassReader reader = new ClassReader(code);
            ClassWriter writer = new ClassWriter(reader, getWriteFlags());
            reader.accept(getClassMapper(writer), 0);
            return writer.toByteArray();
        }

        protected int getWriteFlags() {
            return 0;
        }

        protected abstract ClassVisitor getClassMapper(ClassVisitor downstream);

    }

    public static abstract class GAMethodVisitor extends MethodVisitor {

        protected static Class<?> XNetHooks = null;
        protected static Class<?> GTCEHooks = null;
        protected static Class<?> RSHooks = null;
        protected static Class<?> AE2Hooks = null;

        static { // register hooks
            try {
                XNetHooks = ClassLoader.getSystemClassLoader().loadClass("gregicadditions.coremod.hooks.XNetHooks");
                GTCEHooks = ClassLoader.getSystemClassLoader().loadClass("gregicadditions.coremod.hooks.GregTechCEHooks");
                RSHooks = ClassLoader.getSystemClassLoader().loadClass("gregicadditions.coremod.hooks.RefinedStorageHooks");
                AE2Hooks = ClassLoader.getSystemClassLoader().loadClass("gregicadditions.coremod.hooks.AppliedEnergistics2Hooks");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public GAMethodVisitor(int api, MethodVisitor mv) {
            super(api, mv);
        }

        protected Method findFirstMethod(Class<?> clazz, String methodName, Class<?>... parameterTypes) {
            if (parameterTypes.length == 0 || parameterTypes[0] == null) {
                return Arrays.stream(clazz.getMethods()).filter(method -> method.getName().equals(methodName)).findFirst().orElse(null);
            } else {
                try {
                    return clazz.getMethod(methodName, parameterTypes);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        protected void injectStaticMethod(Class<?> clazz, String methodName, Class<?>... parameterTypes) {
            Method method = findFirstMethod(clazz, methodName, parameterTypes);
            if (method == null) {
                throw new IllegalArgumentException("error inject method, class: " + clazz.getName() + " method: " + methodName);
            } else {
                super.visitMethodInsn(Opcodes.INVOKESTATIC, Type.getInternalName(clazz), methodName, Type.getMethodDescriptor(method), false);
            }
        }

    }
}
