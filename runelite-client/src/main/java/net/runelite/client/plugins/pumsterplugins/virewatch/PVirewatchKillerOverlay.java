package net.runelite.client.plugins.pumsterplugins.virewatch;

import net.runelite.api.Client;
import net.runelite.api.Perspective;
import net.runelite.api.TileObject;
import net.runelite.api.coords.LocalPoint;
import net.runelite.client.plugins.microbot.Microbot;
import net.runelite.client.plugins.microbot.util.gameobject.Rs2GameObject;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayPriority;
import net.runelite.client.ui.overlay.outline.ModelOutlineRenderer;

import javax.inject.Inject;
import java.awt.*;

import static net.runelite.client.plugins.microbot.playerassist.combat.AttackNpcScript.attackableNpcs;
import static net.runelite.client.ui.overlay.OverlayUtil.renderPolygon;

public class PVirewatchKillerOverlay extends Overlay {
    private final Client client;

    private final PVirewatchKillerPlugin plugin;
    private final ModelOutlineRenderer modelOutlineRenderer;

    private TileObject alterStatue;

    private final PVirewatchKillerConfig config;
    private final PVirewatchScript script;
    @Inject
    public PVirewatchKillerOverlay(Client client, PVirewatchKillerPlugin plugin, ModelOutlineRenderer modelOutlineRenderer, PVirewatchKillerConfig config, PVirewatchScript script) {
        this.client = client;
        this.plugin = plugin;
        this.modelOutlineRenderer = modelOutlineRenderer;
        this.config = config;
        this.script = script;

        setNaughty();
        setPosition(OverlayPosition.DYNAMIC);
        setLayer(OverlayLayer.ABOVE_SCENE);
        setPriority(OverlayPriority.HIGH);

    }


    @Override
    public Dimension render(Graphics2D graphics) {

        alterStatue = Rs2GameObject.findObjectById(39234);

        if(alterStatue != null) {
            renderOutline(alterStatue);
            renderOutline(graphics, alterStatue);
        }

        // render start location
        if(plugin.startingLocation != null) {
            LocalPoint startTile = LocalPoint.fromWorld(Microbot.getClient(), plugin.startingLocation);
            if (startTile != null) {
                Polygon safeSpotPoly = Perspective.getCanvasTileAreaPoly(Microbot.getClient(), startTile, 1);
                if (safeSpotPoly != null) {
                    renderPolygon(graphics, safeSpotPoly, Color.RED);
                }
            }
        }


        for (net.runelite.api.NPC npc :
                script.attackableNpcs) {
            if (npc != null && npc.getCanvasTilePoly() != null) {
                try {
                    graphics.setColor(Color.MAGENTA);
                    modelOutlineRenderer.drawOutline(npc, 2, Color.MAGENTA, 4);
                    graphics.draw(npc.getCanvasTilePoly());
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        // Render the radius arround the player
        LocalPoint lp = LocalPoint.fromWorld(Microbot.getClient(), client.getLocalPlayer().getWorldLocation());
        if (lp != null) {
            Polygon poly = Perspective.getCanvasTileAreaPoly(Microbot.getClient(), lp, config.radius());

            if (poly != null) {
                renderPolygon(graphics, poly, Color.CYAN);
            }
        }

        return null;
    }

    private void renderOutline(TileObject gameObject)
    {
        if (gameObject != null)
        {
            modelOutlineRenderer.drawOutline(alterStatue, 2, Color.GREEN, 4);
        }
    }

    private void renderOutline(Graphics2D graphics, TileObject gameObject)
    {
        LocalPoint localPoint = gameObject.getLocalLocation();
        Polygon poly = gameObject.getCanvasTilePoly();
        if (poly != null && localPoint != null)
        {
            graphics.setColor(Color.GREEN);
            graphics.setStroke(new BasicStroke(2));
            graphics.draw(poly);
        }
    }
}
