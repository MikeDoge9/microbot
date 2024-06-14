package net.runelite.client.plugins.microbot.util.reflection.api;

import net.runelite.api.MenuAction;
import net.runelite.api.widgets.ComponentID;
import net.runelite.api.widgets.Widget;
import net.runelite.client.plugins.microbot.Microbot;
import net.runelite.client.plugins.microbot.util.equipment.Rs2Equipment;
import net.runelite.client.plugins.microbot.util.inventory.Rs2Inventory;
import net.runelite.client.plugins.microbot.util.reflection.utils.Rs2ReflectionUtils;

import java.util.Arrays;
import java.util.List;

import static net.runelite.client.plugins.microbot.pumstersreflection.ReflectionLibrary.invokeMenuAction;

public class Rs2ReflectionEquipment {
    public static boolean equipItems(int[] itemIds, int numEquips)
    {
        if (itemIds == null)
        {
            return true;
        }

        int numItemEquippedAtOnce = 0;
        Widget inventoryWidget = Microbot.getClient().getWidget(ComponentID.INVENTORY_CONTAINER);
        if (inventoryWidget == null)
        {
            Rs2ReflectionUtils.sendGameErrorMessage("Inventory widget is null");
            return true;
        }

        Widget[] itemWidgets = inventoryWidget.getChildren();
        if (itemWidgets == null)
        {
            Rs2ReflectionUtils.sendGameErrorMessage("Items widgets are null");
            return true;
        }

        for (Widget itemWidget : itemWidgets)
        {
            //Item slot number
            int slot = itemWidget.getIndex();
            //Check if you can wield the item
            String[] menuActions = itemWidget.getActions();
            if (menuActions == null)
            {
                continue;
            }
            List<String> menuEntries = Arrays.asList(menuActions);
            boolean canWield = menuEntries.contains("Wield");
            boolean canWear = menuEntries.contains("Wear");
            boolean canEquip = menuEntries.contains("Equip");
            //Dynamically gets the index of the Wear or Wield action for the invoke actions.
            //You add 1 to the index of the actions cuz the returned index is always 1 less than the required action.
            int index = 1;

            if (canWield)
            {
                index += menuEntries.lastIndexOf("Wield");
            }
            else if (canWear)
            {
                index += menuEntries.lastIndexOf("Wear");
            }
            else if (canEquip)
            {
                index += menuEntries.lastIndexOf("Equip");
            }
            else
            {
                continue;
            }

            for (int itemId : itemIds)
            {

                if(Rs2Equipment.isWearing(itemId)) {
                    continue;
                }
                if (itemWidget.getItemId() == itemId)
                {
                    invokeMenuAction(slot, ComponentID.INVENTORY_CONTAINER, MenuAction.CC_OP.getId(), index, itemId);
                    numItemEquippedAtOnce++;
                    if (numItemEquippedAtOnce >= numEquips)
                    {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
