package net.runelite.client.plugins.pumsterplugins.virewatch;

import net.runelite.api.NPC;
import net.runelite.api.NpcID;
import net.runelite.client.plugins.microbot.Microbot;
import net.runelite.client.plugins.microbot.Script;
import net.runelite.client.plugins.microbot.util.npc.Rs2Npc;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class PVirewatchScript extends Script {


    public List<NPC> attackableNpcs = new ArrayList<>();
    public boolean run(PVirewatchKillerConfig config, PVirewatchKillerPlugin plugin) {
        Microbot.enableAutoRunOn = false;
        mainScheduledFuture = scheduledExecutorService.scheduleWithFixedDelay(() -> {
            try {
                if (!Microbot.isLoggedIn()) return;
                if (!super.run()) return;
                long startTime = System.currentTimeMillis();

                attackableNpcs = Rs2Npc.getNpcs("Vyrewatch Sentinel", true)
                        .sorted(Comparator.comparingInt(value -> value.getLocalLocation().distanceTo(Microbot.getClient().getLocalPlayer().getLocalLocation())))
                        .filter(x ->
                                !x.isDead()
                                && x.getWorldLocation().distanceTo(plugin.startingLocation) < config.radius()

                        )
                        .collect(Collectors.toList());


                long endTime = System.currentTimeMillis();
                long totalTime = endTime - startTime;
                System.out.println("Total time for loop " + totalTime);

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }, 0, 1000, TimeUnit.MILLISECONDS);
        return true;
    }

    @Override
    public void shutdown() {
        super.shutdown();
    }
}
