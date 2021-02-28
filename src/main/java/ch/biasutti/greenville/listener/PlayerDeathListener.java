package ch.biasutti.greenville.listener;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.data.type.Chest.Type;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerDeathListener implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player p = e.getEntity().getPlayer();

        if (p != null) {
            Location deathLocation = p.getLocation();

            int singleChestSize = 27;
            Chest deathChest;
            Location chestLocation;

            if (e.getDrops().size() <= singleChestSize) {
                chestLocation = this.setSingleChestOnDeath(deathLocation);
            } else {
                chestLocation = this.setDoubleChestOnDeath(deathLocation);
            }

            deathChest = (Chest)chestLocation.getBlock().getState();
            deathChest.setCustomName(p.getName() + "'s death chest!");
            deathChest.update();

            for (final ItemStack item: e.getDrops()) {
                deathChest.getInventory().addItem(item);
            }

            e.getDrops().clear();
            System.out.println("[GreenVille] Create deathchest for " + p.getName());
            p.sendMessage("Your chest is at X: " + chestLocation.getBlockX() + " Y: " + chestLocation.getBlockY() + " Z: " + chestLocation.getBlockZ());

        } else {
            System.out.println("Error player not found!");
        }

    }

    private Location setSingleChestOnDeath(Location deathLocation) {
        Block block1 = deathLocation.getBlock();
        if (block1.getType().isAir()) {
            deathLocation.getBlock().setType(Material.CHEST);
            return deathLocation;
        } else {
            Location chestLocation = deathLocation.clone();
            chestLocation.setY(deathLocation.getBlockY() + 1);
            return setSingleChestOnDeath(chestLocation);
        }
    }

    private Location setDoubleChestOnDeath(Location deathLocation) {
        Location leftChestLocation = setSingleChestOnDeath(deathLocation);
        Location rightChestLocation = leftChestLocation.clone();
        rightChestLocation.setX(rightChestLocation.getX() + 1);

        Block leftBlock = leftChestLocation.getBlock();
        Block rightBlock = rightChestLocation.getBlock();

        leftBlock.setType(Material.CHEST);
        rightBlock.setType(Material.CHEST);

        Chest leftChest = (Chest) leftBlock.getState();
        Chest rightChest = (Chest) rightBlock.getState();

        org.bukkit.block.data.type.Chest leftChestData = (org.bukkit.block.data.type.Chest)leftChest.getBlockData();
        org.bukkit.block.data.type.Chest rightChestData = (org.bukkit.block.data.type.Chest)rightChest.getBlockData();

        leftChestData.setType(Type.LEFT);
        leftBlock.setBlockData(leftChestData, true);
        rightChestData.setType(Type.RIGHT);
        rightBlock.setBlockData(rightChestData, true);

        return leftChestLocation;
    }

}
