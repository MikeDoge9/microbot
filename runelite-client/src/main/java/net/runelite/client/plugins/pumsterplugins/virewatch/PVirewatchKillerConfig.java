package net.runelite.client.plugins.pumsterplugins.virewatch;


import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("PVireKillerConfig")
public interface PVirewatchKillerConfig extends Config {
    @ConfigItem(
            keyName = "killRadius",
            name = "Radius",
            description = "Radius to kill/roam in",
            position = 1
    )
    default int radius() {
        return 15;
    }

    @ConfigItem(
            keyName = "piety",
            name = "Use piety",
            description = "Use the piety prayer",
            position = 2
    )
    default boolean piety() {
        return false;
    }
}
