package net.runelite.client.plugins.microbot.util.reflection;

import net.runelite.api.Client;
import net.runelite.client.RuneLite;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.plugins.microbot.Microbot;
import net.runelite.client.plugins.microbot.util.keyboard.Rs2Keyboard;
import net.runelite.client.plugins.microbot.util.magic.Rs2Spells;
import net.runelite.client.plugins.microbot.util.math.Random;
import net.runelite.client.plugins.microbot.util.reflection.utils.Rs2ReflectionUtils;

import java.awt.event.KeyEvent;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

import static net.runelite.client.plugins.microbot.util.reflection.utils.Rs2ReflectionUtils.sendGameErrorMessage;

public class Rs2Reflection {

    private final static Client client = RuneLite.getInjector().getInstance(Client.class);
    private final static ClientThread clientThread = RuneLite.getInjector().getInstance(ClientThread.class);
    private static final String MENU_CLASS_NAME = "el";
    private static final String MENU_METHOD_NAME = "mb";


    private static Class<?> getClass(String className)
    {
        Class<?> classInstance;

        try
        {
            classInstance = client.getClass().getClassLoader().loadClass(className);
        }
        catch (Exception e)
        {
            sendGameErrorMessage("Unable to load class \"" + className);
            return null;
        }

        return classInstance;
    }

    private static Field getField(Class<?> classInstance, String fieldName)
    {
        Field field;

        if (classInstance == null)
        {
            sendGameErrorMessage("class instance is null when trying to get field " + fieldName);
            return null;
        }

        try
        {
            field = classInstance.getDeclaredField(fieldName);
        }
        catch (Exception e)
        {

            sendGameErrorMessage("Unable to get field " + fieldName);
            return null;
        }

        return field;
    }


    public static void invokeMenu(int param0, int param1, int opcode, int identifier, int itemId, int worldViewId, String option, String target, int x, int y) {
        Class<?> instance = getClass(MENU_CLASS_NAME);
        Method method;
        if (instance == null)
        {
            return;
        }

        try {
            // Fetching method
            method = instance.getDeclaredMethod(MENU_METHOD_NAME, int.class, int.class, int.class, int.class, int.class, int.class, String.class, String.class,
                    int.class, int.class, byte.class);

        }
        catch (Exception e)
        {
            sendGameErrorMessage("obfuscated names outdated");
            return;
        }

        clientThread.invoke(() -> {
            try {
                method.setAccessible(true);

                method.invoke(null, param0, param1, opcode, identifier, itemId, worldViewId, option, target, x, y, (byte) 25);

                method.setAccessible(false);
            }
            catch (Exception e)
            {
                sendGameErrorMessage("Unable to invoke the method invokeMenuAction.");
            }
        });
    }

    public static void invokeMenuAction(int param0, int param1, int opcode, int identifier, int itemId, String option, String target)
    {
        invokeMenu(param0, param1, opcode, identifier, itemId, -1, option, target, -1, -1);
    }



    public static void invokeMenuAction(int param0, int param1, int opcode, int identifier, int itemId)
    {
        invokeMenu(param0, param1, opcode, identifier, itemId, -1, "", "", -1, -1);
    }



}
