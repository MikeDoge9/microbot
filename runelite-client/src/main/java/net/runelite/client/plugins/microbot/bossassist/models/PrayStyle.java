package net.runelite.client.plugins.microbot.bossassist.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PrayStyle {
    MAGE("Pray mage"),
    RANGED("Pray ranged"),
    MELEE("Pray melee");

    private final String action;

    @Override
    public String toString() {
        return action;
    }
}
