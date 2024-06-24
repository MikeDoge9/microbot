package net.runelite.client.plugins.microbot.util.reflection.api;

import net.runelite.api.HeadIcon;
import net.runelite.api.NPC;
import net.runelite.api.NPCComposition;
import net.runelite.client.plugins.microbot.util.reflection.Rs2Reflection;
import net.runelite.client.plugins.microbot.util.reflection.utils.Rs2ReflectionUtils;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Vector;

import static net.runelite.client.plugins.microbot.util.reflection.Rs2Reflection.getField;

public class Rs2ReflectionNpc {

    public static void init() {
    }


    public static HeadIcon getNpcOverheadIcon(NPC npc)
    {
        if (npc == null)
        {
            return null;
        }

        NPCComposition npcComposition = npc.getComposition();
        if (npcComposition == null)
        {
            return null;
        }

        Field overheads = Rs2Reflection.getField("iq", "bj");

        System.out.println(overheads);
        if (overheads == null)
        {
            return null;
        }

        try
        {
            overheads.setAccessible(true);
            Object headIconShortArray = overheads.get(npcComposition);
            if (headIconShortArray == null)
            {
                return null;
            }
            short overheadIconShortValue = Array.getShort(headIconShortArray, 0);
            overheads.setAccessible(false);
            return HeadIcon.values()[overheadIconShortValue];
        }
        catch (Exception e)
        {
            //log.error("Kotori Plugin Utils - Unable to get NPC Composition's overhead icon via field.", e);
        }

        try
        {
            for (Method declaredMethod : npcComposition.getClass().getDeclaredMethods())
            {
                if (declaredMethod.getReturnType() == short[].class && declaredMethod.getParameterTypes().length == 1)
                {
                    declaredMethod.setAccessible(true);
                    short[] headIconArray = (short[]) declaredMethod.invoke(npcComposition);
                    declaredMethod.setAccessible(false);
                    if (headIconArray == null || headIconArray.length == 0)
                    {
                        continue;
                    }
                    return HeadIcon.values()[headIconArray[0]];
                }
            }
        }
        catch (Exception e)
        {
            Rs2ReflectionUtils.sendGameErrorMessage("Unable to get NPC Composition's overhead icon.");
        }

        return null;
    }
}
