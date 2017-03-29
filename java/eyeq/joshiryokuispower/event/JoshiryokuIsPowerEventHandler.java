package eyeq.joshiryokuispower.event;

import eyeq.joshiryokuispower.JoshiryokuIsPower;
import eyeq.joshiryokuispower.common.capabilities.IJoshiryoku;
import eyeq.joshiryokuispower.common.capabilities.JoshiryokuProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

public class JoshiryokuIsPowerEventHandler {
    public static final ResourceLocation capabilityName = new ResourceLocation(JoshiryokuIsPower.MOD_ID, "jp");

    @SubscribeEvent
    public void attachCapability(AttachCapabilitiesEvent.Entity event) {
        if(event.getEntity() instanceof EntityPlayer) {
            event.addCapability(capabilityName, new JoshiryokuProvider());
        }
    }

    @SubscribeEvent
    public void onPlayerClone(net.minecraftforge.event.entity.player.PlayerEvent.Clone event) {
        if(JoshiryokuIsPower.isInitDeath) {
            return;
        }
        if(!event.isWasDeath()) {
            return;
        }
        EntityPlayer oldPlayer = event.getOriginal();
        EntityPlayer newPlayer = event.getEntityPlayer();
        IJoshiryoku capability = oldPlayer.getCapability(JoshiryokuProvider.JOSHIRYOKU, null);
        newPlayer.getCapability(JoshiryokuProvider.JOSHIRYOKU, null).set(capability.get());
    }

    @SubscribeEvent
    public void onPlayerAttackEntity(AttackEntityEvent event) {
        EntityPlayer player = event.getEntityPlayer();
        if(player.getEntityWorld().isRemote) {
            return;
        }
        int jp = player.getCapability(JoshiryokuProvider.JOSHIRYOKU, null).get();
        if(jp > 0) {
            System.out.println(jp);
            event.getTarget().attackEntityFrom(DamageSource.FALL, jp / 10.0F);
        }
    }

    @SubscribeEvent
    public void onPlayerItemCrafted(PlayerEvent.ItemCraftedEvent event) {
        EntityPlayer player = event.player;
        if(player.getEntityWorld().isRemote) {
            return;
        }
        ItemStack itemStack = event.crafting;
        if(itemStack.getItem() instanceof ItemFood) {
            increaseJP(player);
        }
    }

    @SubscribeEvent
    public void onPlayerItemSmelted(PlayerEvent.ItemSmeltedEvent event) {
        EntityPlayer player = event.player;
        if(player.getEntityWorld().isRemote) {
            return;
        }
        ItemStack itemStack = event.smelting;
        if(itemStack.getItem() instanceof ItemFood) {
            increaseJP(player);
        }
    }


    private void increaseJP(EntityPlayer player) {
        IJoshiryoku capability = player.getCapability(JoshiryokuProvider.JOSHIRYOKU, null);
        int jp = capability.get() + 1;
        if(jp > JoshiryokuIsPower.maxJP) {
            jp = JoshiryokuIsPower.maxJP;
        }
        capability.set(jp);
    }
}
