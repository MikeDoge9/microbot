package net.runelite.client.plugins.microbot.pumstersreflection.methods;

import net.runelite.api.*;
import net.runelite.client.RuneLite;
import net.runelite.client.plugins.microbot.pumstersreflection.ReflectionLibrary;
import net.runelite.client.plugins.microbot.pumstersreflection.rlapi.Spells;

public class SpellInteractions
{
	private static final Client client = RuneLite.getInjector().getInstance(Client.class);

	public static void castSpell(Spells spell)
	{
		if (spell.getSpellbook() != VarUtilities.getActiveSpellbook() || client.getBoostedSkillLevel(Skill.MAGIC) < spell.getLevel())
		{
			return;
		}

		ReflectionLibrary.invokeMenuAction(-1, spell.getSpell().getId(), MenuAction.CC_OP.getId(), 1, -1);
	}

	public static void castResurrectGreaterThrall(Spells spell)
	{
		if (!VarUtilities.isSpellResurrectThrallActive() && VarUtilities.isSpellResurrectThrallOffCooldown()
				&& client.getBoostedSkillLevel(Skill.PRAYER) > 5)
		{
			switch (spell)
			{
				case RESURRECT_GREATER_GHOST:
				case RESURRECT_GREATER_SKELETON:
				case RESURRECT_GREATER_ZOMBIE:
					castSpell(spell);
					break;
			}
		}
	}
	
	public static void castSpellDeathCharge()
	{
		if (!VarUtilities.isSpellDeathChargeActive() && VarUtilities.isSpellDeathChargeOffCooldown())
		{
			castSpell(Spells.DEATH_CHARGE);
		}
	}

	public static void createOneClickAttackSpell(Spells spell)
	{
		if (VarUtilities.getActiveSpellbook() != spell.getSpellbook() || client.isMenuOpen())
		{
			return;
		}

		MenuEntry targetEntry = null;

		switch (spell.getTargetType())
		{
			case 1:
				targetEntry = MiscUtilities.findAnyMenuEntryWithAction(MenuAction.NPC_SECOND_OPTION, MenuAction.PLAYER_SECOND_OPTION);
				break;
			case 2:
				targetEntry = MiscUtilities.findAnyMenuEntryWithAction(MenuAction.NPC_SECOND_OPTION);
				break;
			case 3:
				targetEntry = MiscUtilities.findAnyMenuEntryWithAction(MenuAction.PLAYER_SECOND_OPTION);
				break;
		}

		if (targetEntry == null)
		{
			return;
		}

		ReflectionLibrary.setSelectedSpell(spell.getSpell().getId());
		String option = "Cast <col=39ff14>" + spell + "</col> ->";
		MenuEntry oneClick = client.createMenuEntry(-1).setParam0(0).setParam1(0).setIdentifier(targetEntry.getIdentifier())
				.setOption(option).setTarget(targetEntry.getTarget());

		switch (targetEntry.getType())
		{
			case NPC_SECOND_OPTION:
				oneClick.setType(MenuAction.WIDGET_TARGET_ON_NPC);
				break;
			case PLAYER_SECOND_OPTION:
				oneClick.setType(MenuAction.WIDGET_TARGET_ON_PLAYER);
				break;
		}
	}
}
