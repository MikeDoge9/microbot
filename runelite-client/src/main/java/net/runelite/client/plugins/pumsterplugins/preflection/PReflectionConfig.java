package net.runelite.client.plugins.pumsterplugins.preflection;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("pReflection")
public interface PReflectionConfig  extends Config {
    @ConfigItem(
            keyName = "debugDrop",
            name = "Test drop action",
            description = "Debugger to drop items"
    )
    default boolean testDrop()
    {
        return false;
    }
}
