package ch.biasutti.greenville;

import ch.biasutti.greenville.listener.PlayerChatListener;
import ch.biasutti.greenville.listener.PlayerDeathListener;
import ch.biasutti.greenville.listener.PlayerInteractListener;
import ch.biasutti.greenville.recipes.CobblestoneRecipe;
import ch.biasutti.greenville.recipes.CompressedCobblestoneRecipe;
import ch.biasutti.greenville.recipes.ZombieLeatherRecipe;
import ch.biasutti.greenville.recipes.disabled.DisableCompressedCobblestoneStairsL;
import ch.biasutti.greenville.recipes.disabled.DisableCompressedCobblestoneStairsR;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class Main extends JavaPlugin {

    private final Logger log = Logger.getLogger("GreenVille");

    @Override
    public void onEnable() {
        log.info("Start GreenVille Plugin!");
        PluginManager manager = Bukkit.getPluginManager();

        // Register events
        manager.registerEvents(new PlayerDeathListener(),this);
        manager.registerEvents(new PlayerInteractListener(),this);
        manager.registerEvents(new PlayerChatListener(),this);

        // Register custom recipes
        getServer().addRecipe(new ZombieLeatherRecipe(this).getRecipe());
        getServer().addRecipe(new CompressedCobblestoneRecipe(this).getRecipe());
        getServer().addRecipe(new CobblestoneRecipe(this).getRecipe());
        /*getServer().addRecipe(new DisableCompressedCobblestoneStairsR(this).getRecipe());
        getServer().addRecipe(new DisableCompressedCobblestoneStairsL(this).getRecipe());*/
    }

    @Override
    public void onDisable() {
        log.info("Stop GreenVille Plugin!");
    }
}
