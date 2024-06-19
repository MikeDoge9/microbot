package net.runelite.client.plugins.microbot.demonicgorilla;

import net.runelite.client.plugins.microbot.Microbot;
import net.runelite.client.plugins.microbot.barrows.AutoBarrowsPlugin;
import net.runelite.client.plugins.microbot.barrows.AutoBarrowsScript;
import net.runelite.client.plugins.microbot.barrows.models.TheBarrowsBrothers;
import net.runelite.client.ui.FontManager;
import net.runelite.client.ui.overlay.OverlayPanel;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.components.LineComponent;
import net.runelite.client.ui.overlay.components.TitleComponent;

import javax.inject.Inject;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class DemonicGorillaOverlayPanel extends OverlayPanel {

    DemonicGorillaPlugin plugin;

    @Inject
    DemonicGorillaOverlayPanel(DemonicGorillaPlugin plugin)
    {
        super(plugin);
        this.plugin = plugin;
        setPosition(OverlayPosition.TOP_LEFT);
        setNaughty();
    }

    @Override
    public Dimension render(Graphics2D graphics) {

        panelComponent.getChildren().clear();

        panelComponent.getChildren().add(TitleComponent.builder()
                .text("Demonic gorillas V" + AutoBarrowsScript.version)
                .color(Color.CYAN)
                .build());



        return panelComponent.render(graphics);
    }
}
