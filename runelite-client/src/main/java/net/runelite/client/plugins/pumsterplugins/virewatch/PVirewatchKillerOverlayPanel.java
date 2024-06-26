package net.runelite.client.plugins.pumsterplugins.virewatch;

import net.runelite.client.plugins.microbot.Microbot;
import net.runelite.client.ui.FontManager;
import net.runelite.client.ui.overlay.OverlayPanel;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.components.LineComponent;
import net.runelite.client.ui.overlay.components.TitleComponent;

import javax.inject.Inject;
import java.awt.*;

public class PVirewatchKillerOverlayPanel extends OverlayPanel {

    private final PVirewatchKillerPlugin plugin;

    private final PVirewatchKillerConfig config;

    private final PVirewatchScript script;
    @Inject
    PVirewatchKillerOverlayPanel(PVirewatchKillerPlugin plugin, PVirewatchKillerConfig config, PVirewatchScript script)
    {
        super(plugin);
        this.plugin = plugin;
        this.config = config;
        this.script = script;
        setPosition(OverlayPosition.TOP_LEFT);
        setNaughty();
    }

    @Override
    public Dimension render(Graphics2D graphics) {
        try {
            panelComponent.setPreferredSize(new Dimension(200, 300));
            panelComponent.getChildren().add(TitleComponent.builder()
                    .text("Pumsters Virewatch" + PVirewatchKillerPlugin.version)
                    .color(Color.GREEN)
                    .build());

            String check = plugin.startingLocation != null ? "\u2713" : "\u2717";
            panelComponent.getChildren().add(LineComponent.builder()
                    .left("Starting location set")
                    .right(check)
                    .rightFont(FontManager.getDefaultFont())
                    .rightColor(plugin.startingLocation != null ? Color.GREEN : Color.RED)
                    .build());

            panelComponent.getChildren().add(LineComponent.builder()
                    .left("Counted ticks when not in combat")
                    .right(String.valueOf(plugin.countedTicks))
                    .rightFont(FontManager.getDefaultFont())
                    .rightColor(Color.RED)
                    .build());

            panelComponent.getChildren().add(LineComponent.builder()
                    .left("Attackble NPC's")
                    .right(String.valueOf(script.attackableNpcs.size()))
                    .rightFont(FontManager.getDefaultFont())
                    .rightColor(Color.RED)
                    .build());

            panelComponent.getChildren().add(LineComponent.builder().build());

            panelComponent.getChildren().add(LineComponent.builder()
                    .left(Microbot.status)
                    .build());


        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
        return super.render(graphics);
    }
}
