package net.runelite.client.plugins.microbot.util.reflection.utils;

import net.runelite.api.ChatMessageType;
import net.runelite.client.RuneLite;
import net.runelite.client.chat.ChatMessageBuilder;
import net.runelite.client.chat.ChatMessageManager;
import net.runelite.client.chat.QueuedMessage;

import java.awt.*;

public class Rs2ReflectionUtils {

    static final ChatMessageManager chatMessageManager = RuneLite.getInjector().getInstance(ChatMessageManager.class);


    public static void sendGameMessage(String chatMessage, Color color)
    {
        // Construct a message
        final String message = new ChatMessageBuilder().append(color, chatMessage).build();

        // Que it into the chatbox
        chatMessageManager.queue(
                QueuedMessage.builder()
                        .type(ChatMessageType.CONSOLE)
                        .runeLiteFormattedMessage(message)
                        .timestamp(0).build());
    }

    public static void sendGameErrorMessage(String gameMessage)
    {
        sendGameMessage("[REFLECTION] " + gameMessage, Color.RED);
    }
}
