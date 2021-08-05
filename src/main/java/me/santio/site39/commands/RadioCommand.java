package me.santio.site39.commands;

import me.santio.site39.Site39;
import me.santio.site39.utils.RankUtils;
import me.santio.site39.utils.TextUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RadioCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "This command can only be ran in-game!");
            return true;
        }
        Player player = (Player) sender;

        if(!(player.hasPermission("site39.faculty"))) {
            player.sendMessage(TextUtils.tacc(Site39.getPrefix() + "You do not have permission to run this command."));
            return true;
        }

        if(args.length < 1) {
            player.sendMessage(Site39.getPrefix() + TextUtils.tacc("&e/radio <text>"));
            return true;
        }
        String message = String.join(" ", args);
        String color = RankUtils.getColor(player.getUniqueId());
        Bukkit.getOnlinePlayers().stream().filter((Player p) -> p.hasPermission("site39.faculty")).forEach((Player p) -> {
            p.sendMessage(TextUtils.tacc("&8[&3Radio&8] " + color + player.getName() + "&3: " + message));
        });
        return true;
    }
}
