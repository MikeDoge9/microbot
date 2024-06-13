package net.runelite.client.plugins.microbot.pumsterstickeat;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
@ConfigGroup("Tick Eater")

public interface TickEatConfig extends Config {

    @ConfigItem(
            keyName = "Guide",
            name = "How to use",
            description = "How to use the script",
            position = 0
    )
    default String GUIDE()
    {
        return "Setup ur food items and health threshold";
    }

    @ConfigItem(
            keyName = "healthThreshold",
            name = "Health threshold",
            description = "When to tick eat",
            position = 1
    )
    default int hpThreshold()
    {
        return 40;
    }
}
