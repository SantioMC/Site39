package me.santio.site39.utils;

import me.santio.site39.Site39;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.user.User;
import org.bukkit.ChatColor;

import java.util.UUID;

public class RankUtils {

    public static String getRank(UUID uuid) {
        User user = Site39.getLpApi().getUserManager().getUser(uuid);
        if (user == null) return null;
        return user.getCachedData().getMetaData().getPrimaryGroup();
    }

    public static String getPrefix(UUID uuid) {
        User user = Site39.getLpApi().getUserManager().getUser(uuid);
        if (user == null) return null;
        if(user.getCachedData().getMetaData().getPrefix() == null) return "";
        return ChatColor.translateAlternateColorCodes('&', user.getCachedData().getMetaData().getPrefix());
    }

    public static String getSuffix(UUID uuid) {
        User user = Site39.getLpApi().getUserManager().getUser(uuid);
        if (user == null) return null;
        if(user.getCachedData().getMetaData().getSuffix() == null) return "";
        
        return ChatColor.translateAlternateColorCodes('&', user.getCachedData().getMetaData().getSuffix());
    }
    
    public static int getWeight(String rank) {
        Group group = Site39.getLpApi().getGroupManager().getGroup(rank);
        if (group == null) return 0;
        if(!group.getWeight().isPresent()) return 0;
        
        return group.getWeight().getAsInt();
    }

    public static String getColor(UUID uuid) {
        User user = Site39.getLpApi().getUserManager().getUser(uuid);
        if(user == null) return null;
        if(user.getCachedData().getMetaData().getPrefix() == null) return "";
        if(!user.getCachedData().getMetaData().getPrefix().startsWith("&")) return "";

        return user.getCachedData().getMetaData().getPrefix().substring(0,2);
    }
}
