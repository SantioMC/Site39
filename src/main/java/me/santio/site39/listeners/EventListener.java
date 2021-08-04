package me.santio.site39.listeners;

import me.santio.site39.utils.RankUtils;
import org.apache.commons.lang.CharUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class EventListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String prefix = RankUtils.getPrefix(player.getUniqueId());
        String suffix = RankUtils.getSuffix(player.getUniqueId());
        
        String message = event.getMessage();
        message = message.replaceAll("%", "%%");

        if (player.hasPermission("site39.punish")) message = ChatColor.translateAlternateColorCodes('&', message);
        
        event.setFormat(prefix + ChatColor.DARK_GRAY + " | " + ChatColor.GRAY + player.getDisplayName() + suffix + ": " + message);
    }
}
