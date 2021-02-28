package ch.biasutti.greenville;

import ch.biasutti.greenville.listener.PlayerDeathListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println("Start GreenVille Plugin!");
        PluginManager manager = Bukkit.getPluginManager();
        manager.registerEvents(new PlayerDeathListener(),this);
    }

    @Override
    public void onDisable() {
        System.out.println("Stop GreenVille Plugin!");
    }
}
