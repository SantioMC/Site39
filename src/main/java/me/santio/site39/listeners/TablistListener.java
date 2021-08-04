package me.santio.site39.listeners;

import me.santio.site39.utils.RankUtils;
import me.santio.site39.utils.TextUtils;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

@SuppressWarnings("deprecation")
public class TablistListener implements Listener {
    
    
    ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
    
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if (scoreboardManager == null) return;
        
        String rank = RankUtils.getRank(event.getPlayer().getUniqueId());
        int weightR = 10000 - RankUtils.getWeight(rank);
    
        Scoreboard scoreboard = scoreboardManager.getMainScoreboard();
        Team team = scoreboard.getTeam(weightR + "-" + rank);
        if (team == null) team = scoreboard.registerNewTeam(weightR + "-" + rank);
        
        team.setPrefix(TextUtils.tacc(RankUtils.getPrefix(rank) + "&8 | &7"));
        event.getPlayer().setPlayerListName(TextUtils.tacc(RankUtils.getColor(event.getPlayer().getUniqueId())+event.getPlayer().getName()));
        
        team.addPlayer(event.getPlayer());
    }
    
}
