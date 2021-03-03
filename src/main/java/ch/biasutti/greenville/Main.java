package ch.biasutti.greenville;

import ch.biasutti.greenville.listener.PlayerChatListener;
import ch.biasutti.greenville.listener.PlayerDeathListener;
import ch.biasutti.greenville.listener.PlayerInteractListener;
import ch.biasutti.greenville.recipes.ZombieLeather;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println("Start GreenVille Plugin!");
        PluginManager manager = Bukkit.getPluginManager();

        // Register events
        manager.registerEvents(new PlayerDeathListener(),this);
        manager.registerEvents(new PlayerInteractListener(),this);
        manager.registerEvents(new PlayerChatListener(),this);

        // Register custom recipes
        getServer().addRecipe(new ZombieLeather(this).getRecipe());
    }

    @Override
    public void onDisable() {
        System.out.println("Stop GreenVille Plugin!");
    }
}
