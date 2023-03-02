package ch.biasutti.greenville.recipes;

import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.Plugin;

public class ZombieLeatherRecipe {

    @Getter
    ShapedRecipe recipe;

    public ZombieLeatherRecipe(Plugin plugin) {
        ItemStack leather = new ItemStack(Material.LEATHER, 1);
        NamespacedKey key = new NamespacedKey(plugin, "zombie_lether");
        recipe = new ShapedRecipe(key, leather);
        this.setShape();
    }

    private void setShape() {
        recipe.shape("***", "***", "***");
        recipe.setIngredient('*', Material.ROTTEN_FLESH);
    }

}
