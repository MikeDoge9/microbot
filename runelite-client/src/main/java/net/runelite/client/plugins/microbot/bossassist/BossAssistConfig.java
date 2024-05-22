package net.runelite.client.plugins.microbot.bossassist;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;
import net.runelite.client.plugins.microbot.bossassist.models.DAMAGE_PRAYERS;
import net.runelite.client.plugins.microbot.bossassist.models.PRAY_MODE;

@ConfigGroup("Boss Assist")
public interface BossAssistConfig extends Config {

    @ConfigItem(
            keyName = "guide",
            name = "How to use",
            description = "How to use this plugin",
            position = 0
    )
    default String GUIDE() {
        return "This plugin will help u with defeating bosses this is not a Bot script but more like a legit helper";
    }

    @ConfigSection(
            name = "General",
            description = "General",
            position = 1,
            closedByDefault = false
    )
    String generalSection = "general";


    @ConfigSection(
            name = "Scurrius",
            description = "Settings for the Scurrius assist",
            position = 2,
            closedByDefault = true
    )
    String scurriousSection = "scurrious";

    @ConfigItem(
            keyName = "Scurrius",
            name = "Enabled",
            description = "Enable this if you want to assist againt scurry",
            position = 1,
            section = scurriousSection
    )
    default boolean isScurriousOn()
    {
        return false;
    }


    @ConfigItem(
            keyName = "prayermode",
            name = "Prayer mode",
            description = "Choose your prayer mode",
            position = 1,
            section = generalSection
    )
    default PRAY_MODE PRAYER_MODE() {
        return PRAY_MODE.AUTO;
    }

    @ConfigItem(
            keyName = "damageprayer",
            name = "Damage prayer",
            description = "Choose your damage prayer",
            position = 2,
            section = generalSection
    )
    default DAMAGE_PRAYERS DAMAGE_PRAYER() {
        return DAMAGE_PRAYERS.NONE;
    }

}

