package dev.tranducminh.chatprivate.Manager;

import dev.tranducminh.chatprivate.ChatPrivate;
import org.bukkit.entity.Player;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.ChatColor;

public class MessageManager {
    private final ChatPrivate plugin;
    private final Map<Player, Player> lastMessenger = new HashMap<>();

    public MessageManager(ChatPrivate plugin) {
        this.plugin = plugin;
    }

    public void sendMessage(Player from, Player to, String message) {
        String fromMessage = this.plugin.getConfig().getString("whisper.from")
                .replace("<player_name>", to.getName())
                .replace("<message>", message);
        String toMessage = this.plugin.getConfig().getString("whisper.to")
                .replace("<player_name>", from.getName())
                .replace("<message>", message);

        fromMessage = ChatColor.translateAlternateColorCodes('&', fromMessage);
        toMessage = ChatColor.translateAlternateColorCodes('&', toMessage);
        from.sendMessage(fromMessage);
        to.sendMessage(toMessage);

        this.lastMessenger.put(to, from);
    }

    public Player getLastMessenger(Player player) {
        return this.lastMessenger.get(player);
    }
}