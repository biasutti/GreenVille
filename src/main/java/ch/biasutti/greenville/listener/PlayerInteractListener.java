package ch.biasutti.greenville.listener;

import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PlayerInteractListener implements Listener {

    @EventHandler
    public void onPlayerDeathChestInteract(PlayerInteractEvent e) {
        System.out.println("Called player interact event");
        if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) || e.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
            Material m = e.getClickedBlock().getType();
            if (m.equals(Material.CHEST)) {
                Chest chest = (Chest)e.getClickedBlock().getState();
                if (chest.getCustomName().contains("'s death chest!")) {
                    List<ItemStack> items = Stream.of(chest.getInventory().getContents()).filter(Objects::nonNull).collect(Collectors.toList());
                    System.out.println("Chest item count: " + items.size());
                    /*items.forEach((item) -> {
                        chest.getWorld().dropItem(chest.getLocation(), item);
                    });*/
                    chest.getLocation().getBlock().setType(Material.AIR);
                }
            }
        }
    }

}
