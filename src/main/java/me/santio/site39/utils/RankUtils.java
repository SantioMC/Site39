package me.santio.site39.utils;

import me.santio.site39.Site39;
import net.luckperms.api.model.user.User;
import org.bukkit.ChatColor;

import java.util.UUID;

public class RankUtils {

    public static String getRank(UUID uuid) {
        User user = Site39.getLpApi().getUserManager().getUser(uuid);
        return user.getCachedData().getMetaData().getPrimaryGroup();
    }

    public static String getPrefix(UUID uuid) {
        User user = Site39.getLpApi().getUserManager().getUser(uuid);
        if(user.getCachedData().getMetaData().getPrefix() == null) return "";
        return ChatColor.translateAlternateColorCodes('&', user.getCachedData().getMetaData().getPrefix());
    }

    public static String getSuffix(UUID uuid) {
        User user = Site39.getLpApi().getUserManager().getUser(uuid);
        if(user.getCachedData().getMetaData().getSuffix() == null) return "";
        return ChatColor.translateAlternateColorCodes('&', user.getCachedData().getMetaData().getSuffix());
    }
}
