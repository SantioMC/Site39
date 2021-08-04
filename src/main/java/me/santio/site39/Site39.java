package me.santio.site39;

import lombok.Getter;
import me.santio.site39.commands.RadioCommand;
import me.santio.site39.listeners.EventListener;
import net.luckperms.api.LuckPerms;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings({"ResultOfMethodCallIgnored", "ConstantConditions"})
public final class Site39 extends JavaPlugin {
    @Getter private static LuckPerms lpApi;
    @Getter private final static String prefix = ChatColor.of("#0d5f8b") + "S" + ChatColor.of("#126390") + "i" + ChatColor.of("#176795") + "t" + ChatColor.of("#1b6a9a") + "e" + ChatColor.of("#1f6ea0") + "-" + ChatColor.of("#2372a5") + "3" + ChatColor.of("#2776aa") + "9" + ChatColor.DARK_GRAY + " | " + ChatColor.GRAY;
    
    @Override
    public void onEnable() {
        if(!getDataFolder().exists()) getDataFolder().mkdir();

        RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
        if (provider != null) { lpApi = provider.getProvider(); }
        else this.setEnabled(false);
        
        getLogger().info("LuckPerms API connected!");
        getServer().getPluginManager().registerEvents(new EventListener(), this);
        getCommand("radio").setExecutor(new RadioCommand());
    }
    
    @Override
    public void onDisable() {}

    public static Site39 getInstance() { return Site39.getPlugin(Site39.class); }
    
}
