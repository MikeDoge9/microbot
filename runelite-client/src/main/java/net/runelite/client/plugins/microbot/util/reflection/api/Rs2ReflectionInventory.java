package net.runelite.client.plugins.microbot.util.reflection.api;

import net.runelite.api.MenuAction;
import net.runelite.client.plugins.microbot.util.inventory.Rs2Inventory;
import net.runelite.client.plugins.microbot.util.inventory.Rs2Item;
import net.runelite.client.plugins.microbot.util.reflection.Rs2Reflection;

import java.util.Objects;

import static net.runelite.client.plugins.microbot.util.inventory.Rs2Inventory.items;

public class Rs2ReflectionInventory {

    public static boolean drop(int id) {
        // This is streamed in memory by the microbot framework
        Rs2Item item = items().stream().filter(x -> x.id == id).findFirst().orElse(null);
        if (item == null) return false;

        dropItem(item);
        return true;
    }

    public static boolean dropItems (int[] ids) {
        for (int id: ids) {
            Rs2Item[] items = (Rs2Item[]) items().stream().filter(x -> x.id == id).toArray();
            if(items != null) {
                for (Rs2Item item: items) {
                    if(item != null) {
                        drop(item.id);
                    }
                }
            }

        }
        return  true;
    }

    public static void dropItem (Rs2Item item) {
        Rs2Inventory.drop(-1);
        int param0;
        int param1;
        int identifier = 3;

        String action = "Drop";
        String[] actions = item.getInventoryActions();
        for (int i = 0; i < actions.length; i++) {
            if (action.equalsIgnoreCase(actions[i])) {
                identifier = i + 2;
                break;
            }
        }
        if ((Objects.equals(actions[1], "Wear") && actions[0] == null) || action.equalsIgnoreCase("drop") || action.equalsIgnoreCase("empty") || action.equalsIgnoreCase("check")) {
            identifier++;
        }


        MenuAction menuAction = MenuAction.CC_OP;
        param0 = item.slot;
        param1 = 9764864; // Drop action
        Rs2Reflection.invokeMenu(param0, param1, menuAction.getId(), identifier, item.id, -1, "Drop", item.getName(), -1, -1);

    }
}
