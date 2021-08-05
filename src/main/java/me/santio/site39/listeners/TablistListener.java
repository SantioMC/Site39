package me.santio.site39.listeners;

import me.santio.site39.utils.RankUtils;
import me.santio.site39.utils.TextUtils;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
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
        int weightR = 9999 - RankUtils.getWeight(rank);
        String teamID = weightR + "-" + event.getPlayer().getUniqueId().toString().substring(0, 10);

        Scoreboard scoreboard = scoreboardManager.getMainScoreboard();
        Team team = scoreboard.getTeam(teamID);
        if (team == null) team = scoreboard.registerNewTeam(teamID);

        team.setPrefix(TextUtils.tacc(RankUtils.getPrefix(event.getPlayer().getUniqueId()) + "&8 | &7"));
        event.getPlayer().setPlayerListName(TextUtils.tacc(RankUtils.getColor(event.getPlayer().getUniqueId())+event.getPlayer().getName()));

        team.addPlayer(event.getPlayer());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        if (scoreboardManager == null) return;
        
        String rank = RankUtils.getRank(event.getPlayer().getUniqueId());
        int weightR = 9999 - RankUtils.getWeight(rank);
        String teamID = weightR + "-" + event.getPlayer().getUniqueId().toString().substring(0, 10);
        
        Team team = scoreboardManager.getMainScoreboard().getTeam(teamID);
        if (team == null) return;
        
        team.removePlayer(event.getPlayer());
        team.unregister();
    }
    
}
