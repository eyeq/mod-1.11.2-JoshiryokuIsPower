package eyeq.joshiryokuispower.common.capabilities;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTPrimitive;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

public class JoshiryokuStorage implements Capability.IStorage<IJoshiryoku> {
    @Override
    public NBTBase writeNBT(Capability<IJoshiryoku> capability, IJoshiryoku instance, EnumFacing side) {
        return new NBTTagInt(instance.get());
    }

    @Override
    public void readNBT(Capability<IJoshiryoku> capability, IJoshiryoku instance, EnumFacing side, NBTBase nbt) {
        instance.set(((NBTPrimitive) nbt).getInt());
    }
}
