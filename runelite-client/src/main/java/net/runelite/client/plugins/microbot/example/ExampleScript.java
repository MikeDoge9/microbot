package net.runelite.client.plugins.microbot.example;

import net.runelite.client.plugins.microbot.Microbot;
import net.runelite.client.plugins.microbot.Script;
import net.runelite.client.plugins.microbot.pumstersreflection.methods.InventoryInteractions;
import net.runelite.client.plugins.microbot.util.equipment.Rs2Equipment;
import net.runelite.client.plugins.microbot.util.inventory.Rs2Inventory;

import java.util.concurrent.TimeUnit;

public class ExampleScript extends Script {
    public static double version = 1.0;

    private boolean runned = false;

    public boolean run(ExampleConfig config) {
        Microbot.enableAutoRunOn = false;
        mainScheduledFuture = scheduledExecutorService.scheduleWithFixedDelay(() -> {
            try {
                if (!Microbot.isLoggedIn()) return;
                if (!super.run()) return;
                long startTime = System.currentTimeMillis();

                if(!runned) {
                    InventoryInteractions.equipItems(new int[] {12926, 4942,4947});
                    Rs2Inventory.wield(12926); // Blowpipe
                    sleepUntil(() -> Rs2Equipment.isWearing(12926));
                    Rs2Inventory.wield(4942); // Tassests
                    sleepUntil(() -> Rs2Equipment.isWearing(4942));
                    Rs2Inventory.wield(4947); // Chestplate
                    sleepUntil(() -> Rs2Equipment.isWearing(4947));

                }

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
