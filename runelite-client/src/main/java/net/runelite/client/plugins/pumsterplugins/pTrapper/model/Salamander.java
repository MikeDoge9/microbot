package net.runelite.client.plugins.pumsterplugins.pTrapper.model;

import lombok.Getter;

public enum Salamander {
    RED_SALAMANDER("Red salamander");

    @Getter
    private String name;

    Salamander(String name) {
        this.name = name;
    }


}
