package eyeq.joshiryokuispower;

import eyeq.joshiryokuispower.common.capabilities.IJoshiryoku;
import eyeq.joshiryokuispower.common.capabilities.Joshiryoku;
import eyeq.joshiryokuispower.common.capabilities.JoshiryokuStorage;
import eyeq.joshiryokuispower.event.JoshiryokuIsPowerEventHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.util.HashMap;
import java.util.Map;

import static eyeq.joshiryokuispower.JoshiryokuIsPower.MOD_ID;

@Mod(modid = MOD_ID, version = "1.0", dependencies = "after:eyeq_util")
public class JoshiryokuIsPower {
    public static final String MOD_ID = "eyeq_joshiryokuispower";

    @Mod.Instance(MOD_ID)
    public static JoshiryokuIsPower instance;

    private static Configuration save = null;

    protected static Map<String, Integer> playerJPMap = new HashMap<>();

    public static boolean isInitDeath;
    public static int maxJP;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        CapabilityManager.INSTANCE.register(IJoshiryoku.class, new JoshiryokuStorage(), Joshiryoku.class);
        MinecraftForge.EVENT_BUS.register(new JoshiryokuIsPowerEventHandler());
        load(new Configuration(event.getSuggestedConfigurationFile()));
    }

    public static void load(Configuration config) {
        config.load();

        isInitDeath = config.get("Boolean", "isInitDeath", false).getBoolean(false);
        maxJP = config.get("Int", "maxJP", 100).getInt();

        if(config.hasChanged()) {
            config.save();
        }
    }
}
