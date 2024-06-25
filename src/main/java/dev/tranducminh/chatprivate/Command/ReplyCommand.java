package dev.tranducminh.chatprivate.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;
import dev.tranducminh.chatprivate.ChatPrivate;
import dev.tranducminh.chatprivate.Manager.MessageManager;


public class ReplyCommand implements CommandExecutor {
    private final ChatPrivate plugin;
    private final MessageManager messageManager;

    public ReplyCommand(ChatPrivate plugin, MessageManager messageManager) {
        this.plugin = plugin;
        this.messageManager = messageManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Lệnh này chỉ dành cho người chơi.");
            return true;
        }

        if (args.length < 1) {
            sender.sendMessage(ChatColor.DARK_RED + "Lệnh sai, gõ /help xem tất cả các lệnh.");
            return true;
        }

        Player player = (Player) sender;
        Player target = messageManager.getLastMessenger(player);

        if (target == null || !target.isOnline()) {
            String message = plugin.getConfig().getString("messages.no_reply");
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
            return true;
        }

        StringBuilder messageBuilder = new StringBuilder();
        for (String arg : args) {
            messageBuilder.append(arg).append(" ");
        }
        String message = messageBuilder.toString().trim();

        messageManager.sendMessage(player, target, message);
        return true;
    }
}