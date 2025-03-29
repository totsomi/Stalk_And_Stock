package net.hoihelt.stalkandstock.datagen;

import net.hoihelt.stalkandstock.registry.ModBlocks;
import net.hoihelt.stalkandstock.registry.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        nineBlockStorageRecipes(consumer, RecipeCategory.MISC, ModItems.CORN_KERNELS.get(),RecipeCategory.BUILDING_BLOCKS, ModBlocks.PACKED_KERNELS.get());
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_KERNELS.get(), 4).define('S', ModBlocks.PACKED_KERNELS.get()).pattern("SS").pattern("SS").unlockedBy("has_packed_kernels", has(ModBlocks.PACKED_KERNELS.get())).save(consumer);

        foodCookingRecipe(ModItems.CORNCOB.get(),ModItems.COOKED_CORNCOB.get(),0.35f,200,consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.CORNBREAD.get()).define('#', ModItems.CORN_KERNELS.get()).pattern("###").unlockedBy("has_corn_kernels", has(ModItems.CORN_KERNELS.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.CORN_KERNELS.get(),2).requires(ModItems.CORNCOB.get()).unlockedBy("has_corncob",has(ModItems.CORNCOB.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,Items.YELLOW_DYE,1).requires(ModItems.YELLOW_TASSEL.get()).unlockedBy("has_yellow_tassel",has(ModItems.YELLOW_TASSEL.get())).save(consumer);
    }

    public static void foodCookingRecipe(ItemLike ingredient, @NonNull ItemLike result,float experience,int cookingTime,Consumer<FinishedRecipe> consumer) {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ingredient),RecipeCategory.FOOD,result,experience,cookingTime).unlockedBy(getHasName(ingredient),has(ingredient)).save(consumer,getItemName(result) + "from_smelting");
        SimpleCookingRecipeBuilder.smoking(Ingredient.of(ingredient),RecipeCategory.FOOD,result,experience,100).unlockedBy(getHasName(ingredient),has(ingredient)).save(consumer,getItemName(result) + "from_smoking");
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(ingredient),RecipeCategory.FOOD,result,experience,600).unlockedBy(getHasName(ingredient),has(ingredient)).save(consumer,getItemName(result) + "from_campfire_cooking");
    }
}
