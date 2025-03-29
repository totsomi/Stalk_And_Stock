package net.hoihelt.stalkandstock.registry;

import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    public static final FoodProperties CORNCOB = new FoodProperties.Builder()
            .nutrition(3)
            .saturationMod(0.6f)
            .build();

    public static final FoodProperties COOKED_CORNCOB = new FoodProperties.Builder()
            .nutrition(5)
            .saturationMod(1.2f)
            .build();

    public static final FoodProperties CORNBREAD = new FoodProperties.Builder()
            .nutrition(6)
            .saturationMod(0.9f)
            .build();
}
