package me.santio.site39.listeners;

import me.santio.site39.Site39;
import me.santio.site39.utils.RankUtils;
import me.santio.site39.utils.TextUtils;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.event.EventBus;
import net.luckperms.api.event.node.NodeAddEvent;
import net.luckperms.api.event.node.NodeRemoveEvent;
import net.luckperms.api.event.user.track.UserPromoteEvent;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.Node;
import net.luckperms.api.node.types.InheritanceNode;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

public class RankListener implements Listener {
    ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();

    public RankListener() {
        EventBus eventBus = Site39.getLpApi().getEventBus();
        eventBus.subscribe(Site39.getInstance(), NodeAddEvent.class, this::onNodeAdd);
        eventBus.subscribe(Site39.getInstance(), NodeRemoveEvent.class, this::onNodeRemove);
    }

    private void onNodeAdd(NodeAddEvent event) {
        if(!event.isUser()) return;


        User target = (User) event.getTarget();
        Node node = event.getNode();

        Site39.getInstance().getServer().getScheduler().runTask(Site39.getInstance(), () -> {
            Player player = Site39.getInstance().getServer().getPlayer(target.getUniqueId());
            if(player == null) return;

            if(node instanceof InheritanceNode) {
                String groupName = ((InheritanceNode) node).getGroupName();
                player.sendMessage(TextUtils.tacc(Site39.getPrefix() + "You were added to &e" + groupName + "&7!"));

                String rank = RankUtils.getRank(player.getUniqueId());
                int weightR = 9999 - RankUtils.getWeight(rank);
                String teamID = weightR + "-" + player.getUniqueId().toString().substring(0, 10);

                Scoreboard scoreboard = scoreboardManager.getMainScoreboard();

                Team previousTeam = scoreboard.getEntryTeam(player.getDisplayName());
                if(previousTeam == null) return;
                previousTeam.unregister();

                Team team = scoreboard.getTeam(teamID);
                if (team == null) team = scoreboard.registerNewTeam(teamID);

                team.setPrefix(TextUtils.tacc(RankUtils.getPrefix(player.getUniqueId()) + "&8 | &7"));
                player.setPlayerListName(TextUtils.tacc(RankUtils.getColor(player.getUniqueId()) + player.getName()));

                if(!team.hasEntry(player.getName())) team.addEntry(player.getName());
            }
        });
    }

    private void onNodeRemove(NodeRemoveEvent event) {
        if(!event.isUser()) return;

        User target = (User) event.getTarget();
        Node node = event.getNode();

        Site39.getInstance().getServer().getScheduler().runTask(Site39.getInstance(), () -> {
           Player player = Site39.getInstance().getServer().getPlayer(target.getUniqueId());
           if(player == null) return;

           if(node instanceof InheritanceNode) {
               String groupName = ((InheritanceNode) node).getGroupName();
               player.sendMessage(TextUtils.tacc(Site39.getPrefix() + "You were removed from &e" + groupName + "&7!"));

               String rank = RankUtils.getRank(player.getUniqueId());
               int weightR = 9999 - RankUtils.getWeight(rank);
               String teamID = weightR + "-" + player.getUniqueId().toString().substring(0, 10);

               Scoreboard scoreboard = scoreboardManager.getMainScoreboard();

               Team previousTeam = scoreboard.getEntryTeam(player.getDisplayName());
               if(previousTeam == null) return;
               previousTeam.unregister();

               Team team = scoreboard.getTeam(teamID);
               if (team == null) team = scoreboard.registerNewTeam(teamID);

               team.setPrefix(TextUtils.tacc(RankUtils.getPrefix(player.getUniqueId()) + "&8 | &7"));
               player.setPlayerListName(TextUtils.tacc(RankUtils.getColor(player.getUniqueId()) + player.getName()));

               if(!team.hasEntry(player.getName())) team.addEntry(player.getName());
           }
        });
    }
}
