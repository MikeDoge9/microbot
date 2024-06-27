package net.runelite.client.plugins.pumsterplugins.virewatch;

import net.runelite.client.plugins.microbot.Microbot;
import net.runelite.client.plugins.microbot.Script;
import net.runelite.client.plugins.microbot.example.ExampleConfig;
import net.runelite.client.plugins.microbot.util.inventory.Rs2Inventory;
import net.runelite.client.plugins.microbot.util.magic.Rs2Magic;

import java.util.concurrent.TimeUnit;

public class PAlcher extends Script {

    public boolean run(PVirewatchKillerConfig config) {
        Microbot.enableAutoRunOn = false;
        mainScheduledFuture = scheduledExecutorService.scheduleWithFixedDelay(() -> {
            try {
                if (!Microbot.isLoggedIn()) return;
                if (!super.run()) return;
                if(!config.alchItems()) return;

                if(Rs2Inventory.contains("Nature rune", "Fire rune")) {
                    if(Rs2Inventory.contains("Rune dagger")) {
                        Rs2Magic.alch("Rune dagger");
                        sleep(400,600);
                    } else if (Rs2Inventory.contains("Adamant platelegs")) {
                        Rs2Magic.alch("Adamant platelegs");
                        sleep(400,600);
                    } else if (Rs2Inventory.contains("Adamant platebody")) {
                        Rs2Magic.alch("Adamant platebody");
                        sleep(400,600);
                    } else if (Rs2Inventory.contains("Rune full helm")) {
                        Rs2Magic.alch("Rune full helm");
                        sleep(400,600);
                    } else if (Rs2Inventory.contains("Rune kiteshield")) {
                        Rs2Magic.alch("Rune kiteshield");
                        sleep(400,600);
                    }

                }


            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }, 0, 2000, TimeUnit.MILLISECONDS);
        return true;
    }

    @Override
    public void shutdown() {
        super.shutdown();
    }
}
