package net.runelite.client.plugins.pumsterplugins.virewatch;

import com.google.inject.Provides;
import net.runelite.api.Client;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.events.GameTick;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.plugins.microbot.util.combat.Rs2Combat;
import net.runelite.client.ui.overlay.OverlayManager;

import javax.inject.Inject;
import java.awt.*;

@PluginDescriptor(
        name = PluginDescriptor.Pumster + "PVirewatch Killer",
        description = "Pumsters virewatch killer",
        tags = {"pumster"},
        enabledByDefault = false
)
public class PVirewatchKillerPlugin extends Plugin {

    public static String version = "0.1";
    @Inject
    private OverlayManager overlayManager;
    @Inject
    private ClientThread clientThread;
    @Inject
    private Client client;
    @Inject
    private PVirewatchKillerPlugin plugin;
    @Inject
    private PVirewatchKillerOverlayPanel infoOverlay;
    @Inject
    private PVirewatchKillerConfig config;
    @Inject
    private PVirewatchKillerOverlay overlay;
    @Inject
    private  PVirewatchScript script;
    @Provides
    PVirewatchKillerConfig provideConfig(ConfigManager configManager) {
        return configManager.getConfig(PVirewatchKillerConfig.class);
    }

    public WorldPoint startingLocation = null;
    public int countedTicks = 0;

    @Subscribe
    private void onGameTick(GameTick event) {
        if (Rs2Combat.inCombat()) {
            countedTicks = 0;
        } else {
            countedTicks++;
        }

        if(startingLocation == null) startingLocation = client.getLocalPlayer().getWorldLocation();


    }

    @Override
    protected void startUp() throws AWTException {
        if (overlayManager != null) {
            overlayManager.add(infoOverlay);
            overlayManager.add(overlay);

        }
        script.run(config, plugin);
    }

    protected void shutDown() {
        script.shutdown();
        overlayManager.remove(overlay);
        overlayManager.remove(infoOverlay);
        startingLocation = null;
        script.attackableNpcs.clear();
        countedTicks = 0;
    }
}
