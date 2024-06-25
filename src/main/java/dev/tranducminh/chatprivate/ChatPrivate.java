package dev.tranducminh.chatprivate;

import org.bukkit.plugin.java.JavaPlugin;
import dev.tranducminh.chatprivate.Manager.MessageManager;
import dev.tranducminh.chatprivate.Command.ReplyCommand;
import dev.tranducminh.chatprivate.Command.WhisperCommand;

public class ChatPrivate extends JavaPlugin {
    private MessageManager messageManager;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        messageManager = new MessageManager(this);
        getCommand("whisper").setExecutor(new WhisperCommand(this, messageManager));
        getCommand("reply").setExecutor(new ReplyCommand(this, messageManager));
    }

    public MessageManager getMessageManager() {
        return messageManager;
    }
}