package dev.tranducminh.chatprivate.Command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import dev.tranducminh.chatprivate.ChatPrivate;
import dev.tranducminh.chatprivate.Manager.MessageManager;

public class WhisperCommand implements CommandExecutor {
    private final ChatPrivate plugin;
    private final MessageManager messageManager;

    public WhisperCommand(ChatPrivate plugin, MessageManager messageManager) {
        this.plugin = plugin;
        this.messageManager = messageManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Lệnh này chỉ dành cho người chơi.");
            return true;
        }

        if (args.length < 2) {
            sender.sendMessage(ChatColor.DARK_RED + "Lệnh sai, gõ /help cho tất cả các lệnh.");
            return true;
        }

        Player player = (Player) sender;
        Player target = Bukkit.getPlayer(args[0]);

        if (target == null || !target.isOnline()) {
            String message = plugin.getConfig().getString("messages.no_online");
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
            return true;
        }

        if (player.equals(target)) {
            String message = plugin.getConfig().getString("messages.pmself");
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
            return true;
        }

        StringBuilder messageBuilder = new StringBuilder();
        for (int i = 1; i < args.length; i++) {
            messageBuilder.append(args[i]).append(" ");
        }
        String message = messageBuilder.toString().trim();

        messageManager.sendMessage(player, target, message);
        return true;
    }
}