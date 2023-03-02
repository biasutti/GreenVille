package ch.biasutti.greenville.recipes.models;

import lombok.Data;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

@Data
public class CompressedCobblestone {

    ItemStack item;

    public CompressedCobblestone() {
        item = new ItemStack(Material.COBBLESTONE, 1);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(ChatColor.BLUE + "Compressed Cobblestone");
            item.setItemMeta(meta);
        }
    }

}
