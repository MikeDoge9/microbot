/*
 * Copyright (c) 2021, Jordan Atwood <nightfirecat@protonmail.com>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package net.runelite.client.plugins.skillcalculator.skills;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.runelite.api.ItemID;
import net.runelite.client.game.ItemManager;

@AllArgsConstructor
@Getter
public enum FletchingAction implements ItemSkillAction
{
	ARROW_SHAFT(ItemID.ARROW_SHAFT, 1, 0.33f),
	HEADLESS_ARROW(ItemID.HEADLESS_ARROW, 1, 1),
	BRONZE_ARROW(ItemID.BRONZE_ARROW, 1, 1.3f),
	BRONZE_JAVELIN(ItemID.BRONZE_JAVELIN, 3, 1),
	OGRE_ARROW(ItemID.OGRE_ARROW, 5, 1),
	SHORTBOW(ItemID.SHORTBOW, 5, 5),
	SHORTBOW_U(ItemID.SHORTBOW_U, 5, 5),
	BRONZE_BOLTS(ItemID.BRONZE_BOLTS, 9, 0.5f),
	BRONZE_CROSSBOW(ItemID.BRONZE_CROSSBOW, 9, 6),
	WOODEN_STOCK(ItemID.WOODEN_STOCK, 9, 6),
	BRONZE_CROSSBOW_U(ItemID.BRONZE_CROSSBOW_U, 9, 12),
	BRONZE_DART(ItemID.BRONZE_DART, 10, 1.8f),
	LONGBOW(ItemID.LONGBOW, 10, 10),
	LONGBOW_U(ItemID.LONGBOW_U, 10, 10),
	OPAL_BOLTS(ItemID.OPAL_BOLTS, 11, 1.6f),
	IRON_ARROW(ItemID.IRON_ARROW, 15, 2.5f),
	IRON_JAVELIN(ItemID.IRON_JAVELIN, 17, 2),
	OAK_SHORTBOW(ItemID.OAK_SHORTBOW, 20, 16.5f),
	OAK_SHORTBOW_U(ItemID.OAK_SHORTBOW_U, 20, 16.5f),
	IRON_DART(ItemID.IRON_DART, 22, 3.8f),
	BLURITE_CROSSBOW(ItemID.BLURITE_CROSSBOW, 24, 16),
	OAK_STOCK(ItemID.OAK_STOCK, 24, 16),
	BLURITE_CROSSBOW_U(ItemID.BLURITE_CROSSBOW_U, 24, 32),
	OAK_LONGBOW(ItemID.OAK_LONGBOW, 25, 25),
	OAK_LONGBOW_U(ItemID.OAK_LONGBOW_U, 25, 25),
	OAK_SHIELD(ItemID.OAK_SHIELD, 27, 50),
	STEEL_ARROW(ItemID.STEEL_ARROW, 30, 5),
	KEBBIT_BOLTS(ItemID.KEBBIT_BOLTS, 32, 1),
	STEEL_JAVELIN(ItemID.STEEL_JAVELIN, 32, 5),
	WILLOW_SHORTBOW(ItemID.WILLOW_SHORTBOW, 35, 33.3f),
	WILLOW_SHORTBOW_U(ItemID.WILLOW_SHORTBOW_U, 35, 33.3f),
	STEEL_DART(ItemID.STEEL_DART, 37, 7.5f),
	IRON_BOLTS(ItemID.IRON_BOLTS, 39, 1.5f),
	IRON_CROSSBOW(ItemID.IRON_CROSSBOW, 39, 22),
	WILLOW_STOCK(ItemID.WILLOW_STOCK, 39, 22),
	IRON_CROSSBOW_U(ItemID.IRON_CROSSBOW_U, 39, 44),
	WILLOW_LONGBOW(ItemID.WILLOW_LONGBOW, 40, 41.5f),
	WILLOW_LONGBOW_U(ItemID.WILLOW_LONGBOW_U, 40, 41.5f),
	BATTLESTAFF(ItemID.BATTLESTAFF, 40, 80),
	PEARL_BOLTS(ItemID.PEARL_BOLTS, 41, 3.2f),
	LONG_KEBBIT_BOLTS(ItemID.LONG_KEBBIT_BOLTS, 42, 1.3f),
	WILLOW_SHIELD(ItemID.WILLOW_SHIELD, 42, 83),
	SILVER_BOLTS(ItemID.SILVER_BOLTS, 43, 2.5f),
	MITHRIL_ARROW(ItemID.MITHRIL_ARROW, 45, 7.5f),
	STEEL_BOLTS(ItemID.STEEL_BOLTS, 46, 3.5f),
	STEEL_CROSSBOW(ItemID.STEEL_CROSSBOW, 46, 27),
	TEAK_STOCK(ItemID.TEAK_STOCK, 46, 27),
	STEEL_CROSSBOW_U(ItemID.STEEL_CROSSBOW_U, 46, 54),
	MITHRIL_JAVELIN(ItemID.MITHRIL_JAVELIN, 47, 8),
	MAPLE_SHORTBOW(ItemID.MAPLE_SHORTBOW, 50, 50),
	MAPLE_SHORTBOW_U(ItemID.MAPLE_SHORTBOW_U, 50, 50),
	BARBED_BOLTS(ItemID.BARBED_BOLTS, 51, 9.5f),
	BROAD_ARROWS(ItemID.BROAD_ARROWS, 52, 10),
	MITHRIL_DART(ItemID.MITHRIL_DART, 52, 11.2f),
	TOXIC_BLOWPIPE(ItemID.TOXIC_BLOWPIPE, 53, 120),
	MITHRIL_BOLTS(ItemID.MITHRIL_BOLTS, 54, 5),
	MAPLE_STOCK(ItemID.MAPLE_STOCK, 54, 32),
	MITH_CROSSBOW(ItemID.MITHRIL_CROSSBOW, 54, 32),
	MITHRIL_CROSSBOW_U(ItemID.MITHRIL_CROSSBOW_U, 54, 64),
	BROAD_BOLTS(ItemID.BROAD_BOLTS, 55, 3),
	MAPLE_LONGBOW(ItemID.MAPLE_LONGBOW, 55, 58),
	MAPLE_LONGBOW_U(ItemID.MAPLE_LONGBOW_U, 55, 58.3f),
	SAPPHIRE_BOLTS(ItemID.SAPPHIRE_BOLTS, 56, 4.7f),
	MAPLE_SHIELD(ItemID.MAPLE_SHIELD, 57, 116.5f),
	EMERALD_BOLTS(ItemID.EMERALD_BOLTS, 58, 5.5f),
	ADAMANT_ARROW(ItemID.ADAMANT_ARROW, 60, 10),
	ADAMANT_BOLTS(ItemID.ADAMANT_BOLTS, 61, 7),
	ADAMANT_CROSSBOW(ItemID.ADAMANT_CROSSBOW, 61, 41),
	MAHOGANY_STOCK(ItemID.MAHOGANY_STOCK, 61, 41),
	ADAMANT_CROSSBOW_U(ItemID.ADAMANT_CROSSBOW_U, 61, 82),
	ADAMANT_JAVELIN(ItemID.ADAMANT_JAVELIN, 62, 10),
	RUBY_BOLTS(ItemID.RUBY_BOLTS, 63, 6.3f),
	DIAMOND_BOLTS(ItemID.DIAMOND_BOLTS, 65, 7),
	YEW_SHORTBOW(ItemID.YEW_SHORTBOW, 65, 67.5f),
	YEW_SHORTBOW_U(ItemID.YEW_SHORTBOW_U, 65, 67.5f),
	ADAMANT_DART(ItemID.ADAMANT_DART, 67, 15),
	RUNITE_BOLTS(ItemID.RUNITE_BOLTS, 69, 10),
	RUNE_CROSSBOW(ItemID.RUNE_CROSSBOW, 69, 50),
	YEW_STOCK(ItemID.YEW_STOCK, 69, 50),
	RUNITE_CROSSBOW_U(ItemID.RUNITE_CROSSBOW_U, 69, 100),
	YEW_LONGBOW(ItemID.YEW_LONGBOW, 70, 75),
	YEW_LONGBOW_U(ItemID.YEW_LONGBOW_U, 70, 75),
	DRAGONSTONE_BOLTS(ItemID.DRAGONSTONE_BOLTS, 71, 8.2f),
	YEW_SHIELD(ItemID.YEW_SHIELD, 72, 150),
	ONYX_BOLTS(ItemID.ONYX_BOLTS, 73, 9.4f),
	RUNE_ARROW(ItemID.RUNE_ARROW, 75, 12.5f),
	AMETHYST_BROAD_BOLTS(ItemID.AMETHYST_BROAD_BOLTS, 76, 10.6f),
	RUNE_JAVELIN(ItemID.RUNE_JAVELIN, 77, 12.4f),
	DRAGON_CROSSBOW(ItemID.DRAGON_CROSSBOW, 78, 70),
	MAGIC_STOCK(ItemID.MAGIC_STOCK, 78, 70),
	DRAGON_CROSSBOW_U(ItemID.DRAGON_CROSSBOW_U, 78, 135),
	MAGIC_SHORTBOW(ItemID.MAGIC_SHORTBOW, 80, 83.3f),
	MAGIC_SHORTBOW_U(ItemID.MAGIC_SHORTBOW_U, 80, 83.3f),
	RUNE_DART(ItemID.RUNE_DART, 81, 18.8f),
	AMETHYST_ARROW(ItemID.AMETHYST_ARROW, 82, 13.5f),
	DRAGON_BOLTS(ItemID.DRAGON_BOLTS_UNF, 84, 12),
	AMETHYST_JAVELIN(ItemID.AMETHYST_JAVELIN, 84, 13.5f),
	MAGIC_LONGBOW(ItemID.MAGIC_LONGBOW, 85, 91.5f),
	MAGIC_LONGBOW_U(ItemID.MAGIC_LONGBOW_U, 85, 91.5f),
	MAGIC_SHIELD(ItemID.MAGIC_SHIELD, 87, 183),
	DRAGON_ARROW(ItemID.DRAGON_ARROW, 90, 15),
	AMETHYST_DART(ItemID.AMETHYST_DART, 90, 21),
	DRAGON_JAVELIN(ItemID.DRAGON_JAVELIN, 92, 15),
	REDWOOD_SHIELD(ItemID.REDWOOD_SHIELD, 92, 216),
	DRAGON_DART(ItemID.DRAGON_DART, 95, 25),
	;

	private final int itemId;
	private final int level;
	private final float xp;

	@Override
	public boolean isMembers(final ItemManager itemManager)
	{
		return true;
	}
}
