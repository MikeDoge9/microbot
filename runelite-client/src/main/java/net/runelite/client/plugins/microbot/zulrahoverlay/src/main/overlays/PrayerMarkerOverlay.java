package net.runelite.client.plugins.microbot.zulrahoverlay.src.main.overlays;

import net.runelite.api.Client;
import net.runelite.api.Prayer;
import net.runelite.api.VarClientInt;
import net.runelite.api.widgets.Widget;
import net.runelite.client.plugins.microbot.pumstersreflection.rlapi.PrayerExtended;
import net.runelite.client.plugins.microbot.zulrahoverlay.src.main.ZulrahConfig;
import net.runelite.client.plugins.microbot.zulrahoverlay.src.main.ZulrahPlugin;
import net.runelite.client.plugins.microbot.zulrahoverlay.src.main.util.OverlayUtils;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;

import javax.inject.Inject;
import java.awt.*;

public class PrayerMarkerOverlay extends Overlay
{
	private final Client client;
	private final ZulrahPlugin plugin;
	private final ZulrahConfig config;

	@Inject
	private PrayerMarkerOverlay(Client client, ZulrahPlugin plugin, ZulrahConfig config)
	{
		this.client = client;
		this.plugin = plugin;
		this.config = config;
		this.setPosition(OverlayPosition.DYNAMIC);
		this.setLayer(OverlayLayer.ABOVE_WIDGETS);
		this.setPriority(Overlay.PRIORITY_HIGHEST);
	}

	@Override
	public Dimension render(Graphics2D graphics)
	{
		if (config.prayerMarker() && plugin.getZulrahNpc() != null && !plugin.getZulrahNpc().isDead())
		{
			plugin.getZulrahData().forEach((data) ->
				data.getCurrentPhasePrayer().ifPresent((prayer) -> {
					if (client.getVarcIntValue(VarClientInt.INVENTORY_TAB) == 5)
					{
						Widget widget = client.getWidget(541, prayerToChildId(prayer));
						Color color = !client.isPrayerActive(prayer) ? Color.RED : Color.GREEN;
						OverlayUtils.renderWidgetPolygon(graphics, widget, color, true, false);
					}
				}));
			return null;
		}
		else
		{
			return null;
		}
	}

	private int prayerToChildId(Prayer prayer)
	{
		switch (prayer)
		{
			case PROTECT_FROM_MELEE:
			case PROTECT_FROM_MISSILES:
			case PROTECT_FROM_MAGIC:
				return PrayerExtended.getPrayerChildId(prayer);
			default:
				return -1;
		}
	}
}
