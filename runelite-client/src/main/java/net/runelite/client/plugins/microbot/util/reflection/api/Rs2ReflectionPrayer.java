package net.runelite.client.plugins.microbot.util.reflection.api;

import net.runelite.api.MenuAction;
import net.runelite.client.plugins.microbot.Microbot;
import net.runelite.client.plugins.microbot.util.menu.NewMenuEntry;
import net.runelite.client.plugins.microbot.util.player.Rs2Player;
import net.runelite.client.plugins.microbot.util.prayer.Rs2Prayer;
import net.runelite.client.plugins.microbot.util.prayer.Rs2PrayerEnum;
import net.runelite.client.plugins.microbot.util.reflection.Rs2Reflection;
import net.runelite.client.plugins.microbot.util.widget.Rs2Widget;

import java.awt.*;

import static net.runelite.api.Varbits.QUICK_PRAYER;
import static net.runelite.client.plugins.microbot.globval.VarbitValues.QUICK_PRAYER_ENABLED;

public class Rs2ReflectionPrayer {

    public static boolean toggleQuickPrayer (boolean on) {
        boolean bit = Microbot.getVarbitValue(QUICK_PRAYER) == QUICK_PRAYER_ENABLED.getValue();

        boolean isQuickPrayerSet = Microbot.getVarbitValue(4102) > 0;
        if (!isQuickPrayerSet) return false;

        if (Rs2Widget.isHidden(10485779)) return false;
        if (on == bit) return true;

        Rs2Reflection.invokeMenuAction(-1, 10485779, MenuAction.CC_OP.getId(), 1, 1, "Activate", "Quick-prayers");
        return true;
    }

    public static void togglePrayer (Rs2PrayerEnum prayer, boolean on) {
        if (!Rs2Player.hasPrayerPoints()) return;
        final int varBit = prayer.getVarbit();
        if(!on) {
            if (Microbot.getVarbitValue(varBit) == 0) return;
        } else {
            if (Microbot.getVarbitValue(varBit) == 1) return;
        }
        Rs2Reflection.invokeMenu(-1, prayer.getIndex(), MenuAction.CC_OP.getId(), 1,-1, -1, "Activate", "", -1, -1);
    }
}
