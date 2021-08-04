package me.santio.site39;

import lombok.Getter;
import me.santio.site39.commands.RadioCommand;
import me.santio.site39.listeners.EventListener;
import me.santio.site39.listeners.TablistListener;
import net.luckperms.api.LuckPerms;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings({"ResultOfMethodCallIgnored", "ConstantConditions"})
public final class Site39 extends JavaPlugin {
    @Getter private static LuckPerms lpApi;
    @Getter private final static String prefix = ChatColor.of("#227aa9") + "S" + ChatColor.of("#267dae") + "i" + ChatColor.of("#2a7fb3") + "t" + ChatColor.of("#2e82b8") + "e" + ChatColor.of("#3385bc") + "-" + ChatColor.of("#3787c1") + "3" + ChatColor.of("#3c8ac6") + "9" + ChatColor.DARK_GRAY + " | " + ChatColor.GRAY;
    
    @Override
    public void onEnable() {
        if(!getDataFolder().exists()) getDataFolder().mkdir();

        RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
        if (provider != null) {
            lpApi = provider.getProvider();
            getLogger().info("LuckPerms API connected!");
        }
        else this.setEnabled(false);
        
        getServer().getPluginManager().registerEvents(new EventListener(), this);
        getServer().getPluginManager().registerEvents(new TablistListener(), this);
        
        getCommand("radio").setExecutor(new RadioCommand());
    }
    
    @Override
    public void onDisable() {}

    public static Site39 getInstance() { return Site39.getPlugin(Site39.class); }
    
}
