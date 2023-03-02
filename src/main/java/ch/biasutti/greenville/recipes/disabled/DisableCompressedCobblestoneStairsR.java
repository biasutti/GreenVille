package ch.biasutti.greenville.recipes.disabled;

import ch.biasutti.greenville.recipes.models.CompressedCobblestone;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.Plugin;

public class DisableCompressedCobblestoneStairsR {

    @Getter
    ShapedRecipe recipe;
    CompressedCobblestone item;

    @Getter
    String key = "disable_compressed_cobblestone_stairs_r";

    public DisableCompressedCobblestoneStairsR(Plugin plugin) {
        item = new CompressedCobblestone();
        NamespacedKey key = new NamespacedKey(plugin, getKey());
        ItemStack result = new ItemStack(Material.AIR, 1);
        recipe = new ShapedRecipe(key, result);
        this.setShape();
    }

    private void setShape() {
        recipe.shape("  *", " **", "***");
        recipe.setIngredient('*', new RecipeChoice.ExactChoice(item.getItem()));
    }
}
