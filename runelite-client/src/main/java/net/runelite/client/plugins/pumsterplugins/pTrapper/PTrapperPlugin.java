package net.runelite.client.plugins.pumsterplugins.pTrapper;

import com.google.inject.Provides;
import net.runelite.api.*;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.events.GameTick;
import net.runelite.api.events.ItemSpawned;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.plugins.microbot.Microbot;
import net.runelite.client.plugins.microbot.util.gameobject.Rs2GameObject;
import net.runelite.client.plugins.microbot.util.grounditem.Rs2GroundItem;
import net.runelite.client.plugins.microbot.util.inventory.Rs2Inventory;
import net.runelite.client.plugins.microbot.util.reflection.Rs2Reflection;
import net.runelite.client.plugins.microbot.util.tile.Rs2Tile;
import net.runelite.client.plugins.microbot.util.walker.Rs2Walker;
import net.runelite.client.ui.overlay.OverlayManager;

import javax.inject.Inject;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.List;

import static net.runelite.client.plugins.microbot.util.Global.sleep;
import static net.runelite.client.plugins.microbot.util.Global.sleepUntil;


@PluginDescriptor(
        name = PluginDescriptor.Pumster + "PTrapper",
        description = "Pumsters salamander trapper",
        tags = {"reflection", "pumster"},
        enabledByDefault = false
)
public class PTrapperPlugin  extends Plugin {

    Queue<WorldPoint> droppedSupplies = new LinkedList<>();
    public int trapsAmount = 1;

    public static final String version = "1.0";

    public List<GameObject> traps = new ArrayList<>();

    public WorldPoint startingLocation = null;

    @Inject
    public PTrapperConfig config;

    @Provides
    PTrapperConfig provideConfig(ConfigManager configManager) {
        return configManager.getConfig(PTrapperConfig.class);
    }
    @Inject
    private PTrapperOverlay overlay;

    @Inject
    private Client client;

    @Inject
    private PTrapperInfoOverlay infoOverlay;

    @Inject
    private OverlayManager overlayManager;

    @Inject
    private ClientThread clientThread;
    @Override
    protected void startUp() {
        Microbot.pauseAllScripts = false;
        if (overlayManager != null) {
            overlayManager.add(overlay);
        }
    }

    protected void shutDown() {
        overlayManager.remove(overlay);
        Microbot.status = "IDLE";
    }
    @Subscribe
    public void onItemSpawned(ItemSpawned event) {
        if (client.getGameState() != GameState.LOGGED_IN || Microbot.pauseAllScripts) {
            return;
        }
        droppedSupplies.add(event.getTile().getWorldLocation());
    }

    @Subscribe
    private void onGameTick(GameTick event) {
        if(client.getGameState() != GameState.LOGGED_IN || Microbot.pauseAllScripts) {
            return;
        }

        // This wil be the main starting point of the plugin
        if (startingLocation == null)  startingLocation = client.getLocalPlayer().getWorldLocation();

        trapsAmount = getTrapsAmount();
        releaseSalamanders();

        getNearbyTraps(startingLocation, config.maxDist());

//        if(!getNearbyTraps(startingLocation, config.maxDist()).isEmpty()) {
//            for (var a : getNearbyTraps(startingLocation, config.maxDist())) {
//                final ObjectComposition finalTrap = Rs2GameObject.findObjectComposition(a.getId());
//
//                if(Rs2GameObject.hasAction(finalTrap, "Check")) {
//                    Rs2GameObject.interact(finalTrap.getId(), "Check");
//                    sleepUntil(() -> finalTrap.getId() != 8990);
//                }
//
//            }
//        }


        GameObject trap = Rs2GameObject.findObject("Young tree", true, config.maxDist(), false, startingLocation);

//        if (trap != null) {
//            final ObjectComposition finalTrap = Rs2GameObject.findObjectComposition(trap.getId());
//            if(Rs2GameObject.hasAction(finalTrap, "Set-trap")) {
//                Microbot.status = "SETTING TRAP";
//                Rs2GameObject.interact(trap, "Set-trap");
//                sleepUntil(() -> finalTrap.getId() != 8990);
//            }
//        }



//        if (!droppedSupplies.isEmpty()) {
//
//            WorldPoint point = droppedSupplies.peek();
//            if (Rs2GroundItem.exists("small fishing net", config.maxDist()) && Rs2GroundItem.exists("Rope", config.maxDist())) {
//                Microbot.status = "LOOTING";
//                Rs2GroundItem.loot("small fishing net", config.maxDist());
//
//            }
//        }

    }



    private int getTrapsAmount () {
        int level = client.getBoostedSkillLevel(Skill.HUNTER);
        if (level >= 80) {
            return 5;
        } else if (level >= 60) {
            return 4;
        } else if (level >= 40) {
            return 3;
        } else if (level >= 20) {
            return 2;
        }
        return 1;
    }


    private List<GameObject> getNearbyTraps(WorldPoint location, int radius) {
        for (GameObject object : Rs2GameObject.getGameObjects()) {
            if(object != null) {
                final ObjectComposition finalTrap = Rs2GameObject.findObjectComposition(object.getId());

                if (finalTrap != null && finalTrap.getName()== "Young tree" && object.getWorldLocation().distanceTo(location) <= radius) {
                    traps.add(object);
                }
            }

        }
        return traps;
    }
    private void releaseSalamanders() {

        if(Rs2Inventory.contains(config.salamander().getName())) {
            clientThread.invoke(() -> {
                Microbot.status = "DROPPING";
                Rs2Inventory.interact(config.salamander().getName(), "Release");
                sleep(300, 600);
            });
        }
    }


}
