package net.misterj05.what_the_fox;

import org.slf4j.Logger;
import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(WhatTheFox.MODID)
public class WhatTheFox {
    public static final String MODID = "what_the_fox";
    private static final Logger LOGGER = LogUtils.getLogger();

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public WhatTheFox(IEventBus modEventBus, ModContainer modContainer) {
        LOGGER.info("What The Fox has loaded!");
    }
}
