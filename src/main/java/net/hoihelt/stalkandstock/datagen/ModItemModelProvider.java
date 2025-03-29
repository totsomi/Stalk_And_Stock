package net.hoihelt.stalkandstock.datagen;

import net.hoihelt.stalkandstock.StalkAndStock;
import net.hoihelt.stalkandstock.registry.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output,ExistingFileHelper existingFileHelper) {
        super(output, StalkAndStock.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
       basicItem(ModItems.CORNCOB.get());
       basicItem(ModItems.COOKED_CORNCOB.get());
       basicItem(ModItems.CORN_KERNELS.get());
       basicItem(ModItems.CORNBREAD.get());
       basicItem(ModItems.MAIZE_BANNER_PATTERN.get());

    }
}
