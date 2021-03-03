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

import java.util.List;

public class PlayerDeathListener implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player p = e.getEntity().getPlayer();

        if (p != null) {
            Location deathLocation = p.getLocation();

            int singleChestSize = 27;
            Chest deathChest;
            List<Location> locations;

            if (e.getDrops().size() <= singleChestSize) {
                locations = this.setSingleChest(deathLocation);
            } else {
                locations = this.setDoubleChest(deathLocation);
            }

            locations.forEach((location) -> {
                setCustomName((Chest) location.getBlock().getState(), p.getName() + "'s death chest!");
            });

            deathChest = (Chest) locations.get(0).getBlock().getState();

            for (final ItemStack item : e.getDrops()) {
                deathChest.getInventory().addItem(item);
            }

            e.getDrops().clear();
            System.out.println("[GreenVille] Create deathchest for " + p.getName());
            p.sendMessage("Your chest is at X: " + deathChest.getLocation().getBlockX() + " Y: " + deathChest.getLocation().getBlockY() + " Z: " + deathChest.getLocation().getBlockZ());

        } else {
            System.out.println("Error player not found!");
        }

    }

    private List<Location> setSingleChest(Location deathLocation) {
        Location chestLocation = getNextFreeYBlock(deathLocation);
        return List.of(createChest(chestLocation, null));
    }

    private List<Location> setDoubleChest(Location deathLocation) {
        Location leftChestLocation = getNextFreeYBlock(deathLocation);
        Location rightChestLocation = leftChestLocation.clone();
        rightChestLocation.setX(rightChestLocation.getX() + 1);

        Location calcLeftChestLocation = createChest(leftChestLocation, Type.LEFT);
        Location calcRightChestLocation = createChest(rightChestLocation, Type.RIGHT);

        return List.of(calcLeftChestLocation, calcRightChestLocation);
    }

    private Location createChest(Location location, Type type) {
        Location chestLocation = location.clone();
        Block block = chestLocation.getBlock();
        block.setType(Material.CHEST);
        if (type != null) {
            Chest chest = (Chest) block.getState();
            org.bukkit.block.data.type.Chest chestData = (org.bukkit.block.data.type.Chest) chest.getBlockData();
            chestData.setType(type);
            block.setBlockData(chestData, true);
        }
        return chestLocation;
    }

    private Location getNextFreeYBlock(Location location) {
        Block block = location.getBlock();
        if (block.getType().isAir()) {
            return location;
        } else {
            Location chestLocation = location.clone();
            chestLocation.setY(location.getBlockY() + 1);
            return getNextFreeYBlock(chestLocation);
        }
    }

    private void setCustomName(Chest chest, String name) {
        chest.setCustomName(name);
        chest.update();
    }

}
