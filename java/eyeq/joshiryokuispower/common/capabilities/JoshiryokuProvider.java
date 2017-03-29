package eyeq.joshiryokuispower.common.capabilities;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class JoshiryokuProvider implements ICapabilitySerializable<NBTBase> {
    @CapabilityInject(IJoshiryoku.class)
    public static final Capability<IJoshiryoku> JOSHIRYOKU = null;

    private IJoshiryoku instance = JOSHIRYOKU.getDefaultInstance();

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return capability == JOSHIRYOKU;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        return capability == JOSHIRYOKU ? JOSHIRYOKU.cast(this.instance) : null;
    }

    @Override
    public NBTBase serializeNBT() {
        return JOSHIRYOKU.getStorage().writeNBT(JOSHIRYOKU, this.instance, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt) {
        JOSHIRYOKU.getStorage().readNBT(JOSHIRYOKU, this.instance, null, nbt);
    }
}
