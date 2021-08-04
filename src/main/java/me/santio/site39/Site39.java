package me.santio.site39;

import lombok.Getter;
import me.santio.site39.commands.RadioCommand;
import me.santio.site39.listeners.EventListener;
import net.luckperms.api.LuckPerms;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("ResultOfMethodCallIgnored")
public final class Site39 extends JavaPlugin {
    @Getter private static LuckPerms lpApi;
    
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
    public void onDisable() {

    }

    public static Site39 getInstance() { return Site39.getPlugin(Site39.class); }
    
}
