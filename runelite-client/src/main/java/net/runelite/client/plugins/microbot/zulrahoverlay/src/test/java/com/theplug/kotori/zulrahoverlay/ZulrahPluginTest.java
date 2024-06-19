package net.runelite.client.plugins.microbot.zulrahoverlay.src.test.java.com.theplug.kotori.zulrahoverlay;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;
import net.runelite.client.plugins.microbot.zulrahoverlay.src.main.ZulrahPlugin;

public class ZulrahPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(ZulrahPlugin.class);
		RuneLite.main(args);
	}
}