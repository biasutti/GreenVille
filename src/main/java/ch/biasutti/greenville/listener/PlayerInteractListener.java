package ch.biasutti.greenville.listener;

import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractListener implements Listener {

    @EventHandler
    public void onPlayerDeathChestInteract(PlayerInteractEvent e) {
        if (e != null &&
                (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) || e.getAction().equals(Action.LEFT_CLICK_BLOCK)) &&
                e.getClickedBlock() != null
        ) {
            Material m = e.getClickedBlock().getType();
            if (m.equals(Material.CHEST)) {
                Chest chest = (Chest)e.getClickedBlock().getState();
                if (chest.getCustomName() != null && chest.getCustomName().contains("'s death chest!")) {
                    chest.getLocation().getBlock().setType(Material.AIR);
                    System.out.println("[GreenVille] " + e.getPlayer().getName() + " destroyed " + chest.getCustomName());
                }
            }
        }
    }

}
