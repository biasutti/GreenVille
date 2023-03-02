package ch.biasutti.greenville.recipes;

import ch.biasutti.greenville.recipes.models.CompressedCobblestone;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.Plugin;

public class CobblestoneRecipe {

    @Getter
    ShapelessRecipe recipe;
    CompressedCobblestone item;

    @Getter
    String key = "revert_compressed_cobblestone";

    public CobblestoneRecipe(Plugin plugin) {
        item = new CompressedCobblestone();
        NamespacedKey key = new NamespacedKey(plugin, getKey());
        ItemStack result = new ItemStack(Material.COBBLESTONE, 9);
        recipe = new ShapelessRecipe(key, result);
        this.setIngredient();
    }

    private void setIngredient() {
        recipe.addIngredient(new RecipeChoice.ExactChoice(item.getItem()));
    }

}
