package ch.biasutti.greenville.listener;

import lombok.NonNull;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Chest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.logging.Logger;

public class PlayerInteractListener implements Listener {

    private final Logger log = Logger.getLogger("Minecraft");

    @EventHandler
    public void onPlayerDeathChestInteract(@NonNull PlayerInteractEvent e) {
        if ((e.getAction().equals(Action.RIGHT_CLICK_BLOCK) || e.getAction().equals(Action.LEFT_CLICK_BLOCK)) &&
                e.getClickedBlock() != null
        ) {
            Material m = e.getClickedBlock().getType();
            if (m.equals(Material.CHEST)) {
                Chest chest = (Chest)e.getClickedBlock().getState();
                if (chest.getCustomName() != null && chest.getCustomName().contains("'s death chest!")) {
                    ItemStack[] items = chest.getInventory().getContents();
                    World world = e.getPlayer().getWorld();

                    for (var item : items) {
                        if (item != null) world.dropItemNaturally(chest.getLocation(), item);
                    }
                    chest.getLocation().getBlock().setType(Material.AIR);

                    log.info("[GreenVille] " + e.getPlayer().getName() + " destroyed " + chest.getCustomName());
                }
            }
        }
    }

}
