package net.runelite.client.plugins.gauntlethelper.src.test.java.com.theplug.kotori.gauntlethelper;


import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;
import net.runelite.client.plugins.gauntlethelper.src.main.java.com.theplug.kotori.gauntlethelper.GauntletHelperPlugin;

public class GauntletHelperPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(GauntletHelperPlugin.class);
		RuneLite.main(args);
	}
}