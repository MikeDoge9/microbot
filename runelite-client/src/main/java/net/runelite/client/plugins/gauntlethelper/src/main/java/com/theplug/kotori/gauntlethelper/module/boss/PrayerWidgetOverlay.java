package net.runelite.client.plugins.gauntlethelper.src.main.java.com.theplug.kotori.gauntlethelper.module.boss;

import net.runelite.api.Client;
import net.runelite.api.NPC;
import net.runelite.api.Point;
import net.runelite.api.Prayer;
import net.runelite.client.plugins.gauntlethelper.src.main.java.com.theplug.kotori.gauntlethelper.GauntletHelperConfig;
import net.runelite.client.plugins.gauntlethelper.src.main.java.com.theplug.kotori.gauntlethelper.GauntletHelperPlugin;
import net.runelite.client.plugins.microbot.pumstersreflection.overlay.OverlayUtility;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.awt.*;

@Singleton
class PrayerWidgetOverlay extends Overlay
{
    private final Client client;
    private final GauntletHelperConfig config;
    private final BossModule bossModule;

    @Inject
    PrayerWidgetOverlay(
            final Client client,
            final GauntletHelperPlugin plugin,
            final GauntletHelperConfig config,
            final BossModule bossModule)
    {
        super(plugin);

        this.client = client;
        this.config = config;
        this.bossModule = bossModule;

        setPosition(OverlayPosition.DYNAMIC);
        setPriority(Overlay.PRIORITY_HIGHEST);
        setLayer(OverlayLayer.ABOVE_WIDGETS);
    }


    @Override
    public Dimension render(Graphics2D graphics)
    {
        final GauntletHelperConfig.PrayerHighlightMode prayerHighlightMode = config.prayerOverlay();

        if (prayerHighlightMode == GauntletHelperConfig.PrayerHighlightMode.NONE ||
                prayerHighlightMode == GauntletHelperConfig.PrayerHighlightMode.BOX)
        {
            return null;
        }

        final Hunllef hunllef = bossModule.getHunllef();

        if (hunllef == null)
        {
            return null;
        }

        final NPC npc = hunllef.getNpc();

        if (npc == null || npc.isDead())
        {
            return null;
        }

        // Overlay outline on the prayer widget

        final Hunllef.AttackPhase phase = hunllef.getAttackPhase();

        final Prayer prayer = phase.getPrayer();

        final Color phaseColor = phase.getColor();

        final Rectangle rectangle = OverlayUtility.renderPrayerOverlay(graphics, client, prayer, phaseColor);

        if (rectangle == null)
        {
            return null;
        }

        // Overlay tick count on the prayer widget

        final int ticksUntilAttack = hunllef.getTicksUntilNextAttack();

        final String text = String.valueOf(ticksUntilAttack);

        final int fontSize = 16;
        final int fontStyle = Font.BOLD;
        final Color fontColor = ticksUntilAttack == 1 ? Color.WHITE : phaseColor;

        final int x = (int) (rectangle.getX() + rectangle.getWidth() / 2);
        final int y = (int) (rectangle.getY() + rectangle.getHeight() / 2);

        final Point point = new Point(x, y);

        final Point canvasPoint = new Point(point.getX() - 3, point.getY() + 6);

        OverlayUtility.renderTextLocation(graphics, text, fontSize, fontStyle, fontColor, canvasPoint, true, 0);

        return null;
    }
}
