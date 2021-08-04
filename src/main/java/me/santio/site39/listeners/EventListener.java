package me.santio.site39.listeners;

import me.santio.site39.utils.RankUtils;
import me.santio.site39.utils.TextUtils;
import org.apache.commons.lang.CharUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class EventListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String prefix = RankUtils.getPrefix(player.getUniqueId());
        String suffix = RankUtils.getSuffix(player.getUniqueId());
        
        String message = event.getMessage();
        message = message.replaceAll("%", "%%");

        if (player.hasPermission("site39.punish")) message = TextUtils.translateColor(message);

        event.setFormat(TextUtils.translateColor(prefix + "&8 | &7" + player.getDisplayName() + suffix + ": " + message));
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if(player.hasPermission("site39.punish")) event.setJoinMessage(TextUtils.translateColor("&b&o" + player.getName() + "&7&o has joined."));

        event.setJoinMessage(TextUtils.translateColor("&3&o" + player.getName() + "&7&o has joined."));
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        if(player.hasPermission("site39.punish")) event.setQuitMessage(TextUtils.translateColor("&b&o" + player.getName() + "&7&o has left."));

        event.setQuitMessage(TextUtils.translateColor("&3&o" + player.getName() + "&7&o has left."));
    }
}
