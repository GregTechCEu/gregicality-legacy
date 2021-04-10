package gregicadditions.coremod;

import gregicadditions.coremod.transform.*;
import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

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
}
