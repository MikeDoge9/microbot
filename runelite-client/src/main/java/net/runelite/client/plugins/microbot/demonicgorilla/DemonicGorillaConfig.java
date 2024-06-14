package net.runelite.client.plugins.microbot.demonicgorilla;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;

@ConfigGroup("Demonic-gorrlias")
public interface DemonicGorillaConfig extends Config {

    @ConfigItem(
            keyName = "guide",
            name = "How to use",
            description = "How to use this plugin",
            position = 0
    )
    default String GUIDE() {
        return "TODO: Write this XD";
    }

    @ConfigSection(
            name = "Teleport",
            description = "Teleport",
            position = 2,
            closedByDefault = false
    )
    String teleportSections = "teleport";

    @ConfigSection(
            name = "Combat",
            description = "Combat settings",
            position = 1,
            closedByDefault = false
    )
    String combatSection = "combat";

    @ConfigItem(
            keyName = "WalkUnder",
            name = "Walk under to dodge",
            description = "Prefer walking under the gorilla to dodge its boulder attack",
            position = 1,
            section = combatSection
    )
    default boolean walkUnder()
    {
        return false;
    }


}
