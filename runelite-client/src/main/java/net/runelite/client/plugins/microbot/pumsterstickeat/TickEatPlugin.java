package net.runelite.client.plugins.microbot.pumsterstickeat;

import com.google.inject.Provides;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.events.ClientTick;
import net.runelite.api.events.MenuOptionClicked;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.menus.MenuManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.plugins.microbot.Microbot;
import net.runelite.client.plugins.microbot.prayerflicker.FlickerConfig;
import net.runelite.client.plugins.microbot.util.inventory.Rs2Inventory;
import net.runelite.client.plugins.microbot.util.player.Rs2Player;
import net.runelite.client.util.ColorUtil;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.Set;
@Slf4j
@PluginDescriptor(
        name = PluginDescriptor.Pumster + "Tick eater",
        description = "Pumster tick eater",
        tags = {"eat", "microbot"},
        enabledByDefault = false
)
public class TickEatPlugin extends Plugin {

    @Inject
    private Client client;

    @Inject
    private TickEatConfig config;

    @Inject
    private MenuManager menuManager;

    @Provides
    TickEatConfig provideConfig(ConfigManager configManager) {
        return configManager.getConfig(TickEatConfig.class);
    }


    private final Set<Integer> foodItemIds = new HashSet<>();
    private static final String MENU_OPTION = "<col=03ff4e>Tick eat</col>";


    @Override
    protected void startUp() throws Exception {
        // Initialize the list of food item IDs
        initializeFoodItemIds();
    }

    @Override
    protected void shutDown() throws Exception {
        // Clean up if necessary
    }

    private void initializeFoodItemIds() {
        // Add the item IDs for food items
        foodItemIds.add(ItemID.SHRIMPS);
        foodItemIds.add(ItemID.COOKED_CHICKEN);
        foodItemIds.add(ItemID.LOBSTER);
        foodItemIds.add(ItemID.COOKED_CHOMPY);
        foodItemIds.add(ItemID.COOKED_KARAMBWAN);

        System.out.println("We made the food items");
    }

    @Subscribe
    public void onClientTick(ClientTick event) {
        ItemContainer inventory = client.getItemContainer(InventoryID.INVENTORY);
        if (inventory != null) {
            Item[] items = inventory.getItems();
            for (int slot = 0; slot < items.length; slot++) {
                Item item = items[slot];
                int itemId = item.getId();
                if (foodItemIds.contains(itemId)) {
                    MenuEntry[] menuEntries = client.getMenuEntries();
                    for (MenuEntry entry : menuEntries) {
                        if (entry.getType() == MenuAction.CC_OP) { // Corrected comparison
                            if (entry.getParam0() == slot) {

                                double treshHold = (double) (Microbot.getClient().getBoostedSkillLevel(Skill.HITPOINTS) * 100) / Microbot.getClient().getRealSkillLevel(Skill.HITPOINTS);
                                if (treshHold <= config.hpThreshold()) {

                                    client.createMenuEntry(-1)
                                            .setOption(MENU_OPTION)
                                            .setTarget(entry.getTarget())
                                            .setType(MenuAction.RUNELITE)
                                            .onClick(e ->
                                            {
                                                Rs2Inventory.interact(ItemID.COOKED_KARAMBWAN, "eat");
                                                Rs2Inventory.interact(ItemID.LOBSTER, "eat");
                                            });

                                }
                            }
                        }
                    }
                    client.setMenuEntries(menuEntries);
                }
            }
        }
    }

}
