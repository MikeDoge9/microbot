package net.runelite.client.plugins.pumsterplugins.pTrapper;

import net.runelite.api.Client;
import net.runelite.api.GameObject;
import net.runelite.api.Perspective;
import net.runelite.api.coords.LocalPoint;
import net.runelite.client.plugins.microbot.Microbot;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.outline.ModelOutlineRenderer;

import javax.inject.Inject;
import java.awt.*;

import static net.runelite.client.ui.overlay.OverlayUtil.renderPolygon;

public class PTrapperOverlay extends Overlay {
    private final Client client;
    private final PTrapperPlugin plugin;

    private final PTrapperConfig config;

    private final ModelOutlineRenderer modelOutlineRenderer;

    private static final Color RED_TRANSLUCENT = new Color(255, 0, 0, 127);



    @Inject
    public PTrapperOverlay (Client client, PTrapperPlugin plugin, ModelOutlineRenderer modelOutlineRenderer, PTrapperConfig config) {
        this.client = client;
        this.plugin = plugin;
        this.modelOutlineRenderer = modelOutlineRenderer;
        this.config = config;

        setNaughty();
        // Test if this doesnt lag
        setPosition(OverlayPosition.DYNAMIC);
        setLayer(OverlayLayer.ABOVE_SCENE);

    }

    @Override
    public Dimension render(Graphics2D graphics) {
        if (plugin.startingLocation != null) {

            // render start location
            LocalPoint startTile = LocalPoint.fromWorld(Microbot.getClient(), plugin.startingLocation);
            if (startTile != null) {
                Polygon safeSpotPoly = Perspective.getCanvasTileAreaPoly(Microbot.getClient(), startTile, 1);
                if (safeSpotPoly != null) {
                    renderPolygon(graphics, safeSpotPoly, RED_TRANSLUCENT);
                }
            }

            LocalPoint lp =  LocalPoint.fromWorld(Microbot.getClient(), plugin.startingLocation);
            if (lp != null) {
                Polygon poly = Perspective.getCanvasTileAreaPoly(Microbot.getClient(), lp, config.maxDist());

                if (poly != null)
                {
                    renderPolygon(graphics, poly, Color.CYAN);
                }
            }

            if(!plugin.traps.isEmpty()) {
                for (GameObject trap: plugin.traps) {
                    Polygon poly = Perspective.getCanvasTileAreaPoly(Microbot.getClient(), trap.getLocalLocation(), 1);
                    if (poly != null)
                    {
                        renderPolygon(graphics, poly, Color.magenta);
                    }
                }
            }

//
//            TileObjects.search().filter(t -> plugin.startTile.distanceTo(t.getWorldLocation()) <= plugin.config.maxDist()).withName("Young tree").result().forEach(tree -> {
//                renderTile(graphics, LocalPoint.fromWorld(client, tree.getWorldLocation()),
//                        Color.GREEN, 2, new Color(0, 255, 0, 20));
//            });
//        } else {
//            TileObjects.search().withinDistance(plugin.config.maxDist()).withName("Young tree").result().forEach(tree -> {
//                renderTile(graphics, LocalPoint.fromWorld(client, tree.getWorldLocation()),
//                        Color.GREEN, 2, new Color(0, 255, 0, 20));
//            });
        }
        return null;
    }
}
