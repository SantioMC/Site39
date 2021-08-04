package me.santio.site39.utils;

import org.bukkit.ChatColor;

public class TextUtils {

    public static String translateColor(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
