package org.inmopx.dmzevo_mod;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.inmopx.dmzevo_mod.common.init.ModItems;

@Mod(DMZEvoMod.MOD_ID)
public class DMZEvoMod {
    public static final String MOD_ID = "dmzevo_mod";
    public static final Logger LOGGER = LogManager.getLogger();

    public DMZEvoMod() {
        LOGGER.info("[DMZ Evolution] Hecho por InmortalPx");

        var modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModItems.register(modEventBus);

        LOGGER.info("[DMZ Evolution] Cargado correctamente");
    }
}