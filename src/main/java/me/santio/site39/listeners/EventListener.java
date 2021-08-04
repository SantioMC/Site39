package me.santio.site39.listeners;

import me.santio.site39.utils.RankUtils;
import me.santio.site39.utils.TextUtils;
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
        String color = player.hasPermission("site39.staff") ? "&f" : "&7";
        
        String message = event.getMessage();
        message = message.replaceAll("%", "%%");

        if (player.hasPermission("site39.staff")) message = TextUtils.tacc(message);

        event.setFormat(TextUtils.tacc(prefix + "&8 | "+ suffix +"&7" + player.getDisplayName() + color + ": " + message));
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String color = player.hasPermission("site39.staff") ? "&b&o" : "&3&o";
        event.setJoinMessage(TextUtils.tacc(color + player.getName() + "&7&o has joined."));
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        String color = player.hasPermission("site39.staff") ? "&b&o" : "&3&o";
        event.setQuitMessage(TextUtils.tacc(color+ player.getName() + "&7&o has left."));
    }
}
