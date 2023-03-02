package ch.biasutti.greenville.recipes;

import ch.biasutti.greenville.recipes.models.CompressedCobblestone;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.Plugin;

public class CompressedCobblestoneRecipe {

    @Getter
    ShapedRecipe recipe;
    @Getter
    String key = "compressed_cobblestone";

    public CompressedCobblestoneRecipe(Plugin plugin) {
        CompressedCobblestone item = new CompressedCobblestone();
        NamespacedKey key = new NamespacedKey(plugin, getKey());
        recipe = new ShapedRecipe(key, item.getItem());
        this.setShape();
    }

    private void setShape() {
        recipe.shape("***", "***", "***");
        recipe.setIngredient('*', new RecipeChoice.ExactChoice(new ItemStack(Material.COBBLESTONE, 1)));
    }
}
