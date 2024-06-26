package net.runelite.client.plugins.microbot.util.reflection;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import net.runelite.api.Client;
import net.runelite.api.MenuEntry;
import net.runelite.client.RuneLite;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.plugins.microbot.Microbot;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Vector;
import java.util.stream.Collectors;

import static net.runelite.client.plugins.microbot.util.reflection.utils.Rs2ReflectionUtils.sendGameErrorMessage;

public class Rs2Reflection {

    private final static Client client = RuneLite.getInjector().getInstance(Client.class);
    private final static ClientThread clientThread = RuneLite.getInjector().getInstance(ClientThread.class);
    @Getter
    @Setter
    private static String MENU_CLASS_NAME = "el";
    @Getter
    @Setter
    private static String MENU_METHOD_NAME = "mb";


    public static void initReflection () throws NoSuchFieldException, IllegalAccessException {
        // Credits to EthansAPI
        Field classes = ClassLoader.class.getDeclaredField("classes");
        classes.setAccessible(true);
        ClassLoader classLoader = client.getClass().getClassLoader();
        Vector<Class<?>> classesVector = (Vector<Class<?>>) classes.get(classLoader);
        Class<?>[] params = new Class[]{int.class, int.class, int.class, int.class, int.class, int.class, String.class, String.class, int.class, int.class};
        for (Class<?> aClass : classesVector) {
            for (Method declaredMethod : aClass.getDeclaredMethods()) {
                if (declaredMethod.getParameterCount() != 11) {
                    continue;
                }
                if (declaredMethod.getReturnType() != void.class) {
                    continue;
                }
                if (!Arrays.equals(Arrays.copyOfRange(declaredMethod.getParameterTypes(), 0, 10), params)) {
                    continue;
                }
                MENU_CLASS_NAME = aClass.getSimpleName();
                MENU_METHOD_NAME = declaredMethod.getName();
            }
        }

    }

    public static void logClassNames () {
        System.out.println("Current decrypted class name: " + MENU_CLASS_NAME);
        System.out.println("Current decrypted method name: " + MENU_METHOD_NAME);
    }

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

    @SneakyThrows
    public static void setItemId(MenuEntry menuEntry, int itemId) throws IllegalAccessException, InvocationTargetException {
        var list =  Arrays.stream(menuEntry.getClass().getMethods())
                .filter(x -> x.getName().equals("setItemId"))
                .collect(Collectors.toList());

         list.get(0)
                .invoke(menuEntry, itemId); //use the setItemId method through reflection

    }
  
    public static Field getField(String className, String fieldName)
    {
        Class<?> clazz = getClass(className);
        return getField(clazz, fieldName);
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
