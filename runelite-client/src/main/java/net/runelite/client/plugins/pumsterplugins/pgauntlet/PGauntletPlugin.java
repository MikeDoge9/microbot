package net.runelite.client.plugins.pumsterplugins.pgauntlet;

import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.NpcID;
import net.runelite.api.events.GameTick;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.game.ItemManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

import javax.inject.Inject;
import java.util.Set;

@PluginDescriptor(
        name = PluginDescriptor.Pumster + "PGauntlet",
        description = "Pumsters gauntlet helper",
        tags = {"reflection", "pumster", "gauntlet"},
        enabledByDefault = false
)
public class PGauntletPlugin extends Plugin {

    @Inject
    Client client;
    @Inject
    ItemManager manager;

    public String updatedWeapon = "";
    boolean forceTab = false;
    Set<Integer> HUNLLEF_IDS = Set.of(NpcID.CORRUPTED_HUNLLEF, NpcID.CORRUPTED_HUNLLEF_9036,
            NpcID.CORRUPTED_HUNLLEF_9037, NpcID.CORRUPTED_HUNLLEF_9038, NpcID.CRYSTALLINE_HUNLLEF,
            NpcID.CRYSTALLINE_HUNLLEF_9022, NpcID.CRYSTALLINE_HUNLLEF_9023, NpcID.CRYSTALLINE_HUNLLEF_9024);
    boolean isRange = true;

    @Subscribe
    public void onGameTick(GameTick tick) {
        if (client.getLocalPlayer().isDead() || client.getLocalPlayer().getHealthRatio() == 0) {
            forceTab = false;
            return;
        }
        if (client.getGameState() != GameState.LOGGED_IN) {
            forceTab = false;
            return;
        }
        String name = "";



    }


}
