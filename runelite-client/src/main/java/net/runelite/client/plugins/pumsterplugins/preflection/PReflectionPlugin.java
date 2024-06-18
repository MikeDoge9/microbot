package net.runelite.client.plugins.pumsterplugins.preflection;

import com.google.inject.Provides;
import net.runelite.api.events.ClientTick;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.plugins.microbot.util.reflection.Rs2Reflection;
import net.runelite.client.plugins.microbot.util.reflection.api.Rs2ReflectionInventory;
import net.runelite.client.ui.overlay.OverlayManager;

import javax.inject.Inject;
import java.awt.*;

@PluginDescriptor(
        name = PluginDescriptor.Pumster + "PReflection",
        description = "Pumsters reflection",
        tags = {"reflection", "pumster"},
        enabledByDefault = true
)
public class PReflectionPlugin extends Plugin {
    @Inject
    private PReflectionConfig config;
    @Provides
    PReflectionConfig provideConfig(ConfigManager configManager) {
        return configManager.getConfig(PReflectionConfig.class);
    }

    @Inject
    private OverlayManager overlayManager;


    @Subscribe
    public void onClientTick(ClientTick clientTick) {
        if(config.testDrop()) {
            Rs2ReflectionInventory.drop(952);
        }
    }

    @Override
    protected void startUp() throws AWTException {
        try {
            Rs2Reflection.initReflection();
            Rs2Reflection.logClassNames();
        } catch (Exception e) {
            System.out.println("Failed to decrypt the classes needed for reflection");
        }
    }

    protected void shutDown() {
    }
}
