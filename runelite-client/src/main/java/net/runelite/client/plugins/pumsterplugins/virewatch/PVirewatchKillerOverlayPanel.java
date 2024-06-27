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
                    .text("Pumsters Vyrewatch " + PVirewatchKillerPlugin.version)
                    .color(Color.GREEN)
                    .build());

            panelComponent.getChildren().add(LineComponent.builder().build());

            String check = plugin.startingLocation != null ? "\u2713" : "\u2717";
            panelComponent.getChildren().add(LineComponent.builder()
                    .left("Starting location set")
                    .right(check)
                    .rightFont(FontManager.getDefaultFont())
                    .rightColor(plugin.startingLocation != null ? Color.GREEN : Color.RED)
                    .build());

            String check4 = plugin.fightArea != null ? "\u2713" : "\u2717";
            panelComponent.getChildren().add(LineComponent.builder()
                    .left("Fight area set")
                    .right(check4)
                    .rightFont(FontManager.getDefaultFont())
                    .rightColor(plugin.fightArea != null ? Color.GREEN : Color.RED)
                    .build());


            String check2 = script.rechargingPrayer ? "\u2713" : "\u2717";
            panelComponent.getChildren().add(LineComponent.builder()
                    .left("Recharging prayer")
                    .right(check2)
                    .rightFont(FontManager.getDefaultFont())
                    .rightColor(script.rechargingPrayer ? Color.GREEN : Color.RED)
                    .build());


            String check3 = config.alchItems() ? "\u2713" : "\u2717";
            panelComponent.getChildren().add(LineComponent.builder()
                    .left("Alching drops")
                    .right(check3)
                    .rightFont(FontManager.getDefaultFont())
                    .rightColor(config.alchItems() ? Color.GREEN : Color.RED)
                    .build());

            panelComponent.getChildren().add(LineComponent.builder()
                    .left("Ticks not in combat")
                    .right(String.valueOf(plugin.countedTicks))
                    .rightColor(Color.RED)
                    .build());

            panelComponent.getChildren().add(LineComponent.builder()
                    .left("Ticks out of area")
                    .right(String.valueOf(plugin.ticksOutOfArea))
                    .rightColor(Color.RED)
                    .build());

            panelComponent.getChildren().add(LineComponent.builder()
                    .left("Pray style")
                    .right(config.prayStyle().getName())
                    .rightColor(Color.CYAN)
                    .build());

            panelComponent.getChildren().add(LineComponent.builder()
                    .left("Profit")
                    .right(plugin.getTotalItemValue())
                    .rightColor(Color.GREEN)
                    .build());


            panelComponent.getChildren().add(LineComponent.builder()
                    .left(Microbot.status)
                    .build());


        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
        return super.render(graphics);
    }
}
