package net.runelite.client.plugins.pumsterplugins.pTrapper;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.plugins.pumsterplugins.pTrapper.model.Salamander;

@ConfigGroup("PTrapperConfig")
public interface PTrapperConfig extends Config {

    @ConfigItem(
            keyName = "salamanderType",
            name = "Salamander",
            description = "",
            position = 1
    )
    default Salamander salamander() {
        return Salamander.RED_SALAMANDER;
    }


    @ConfigItem(
            keyName = "tileRadius",
            name = "Tile radius",
            description = "Keeps u in the tiles from starting point",
            position = 2
    )
    default int maxDist() {
        return 10;
    }

}
