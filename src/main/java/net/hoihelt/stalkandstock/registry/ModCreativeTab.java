package net.hoihelt.stalkandstock.registry;

import net.hoihelt.stalkandstock.StalkAndStock;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.decoration.Painting;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Comparator;
import java.util.function.Predicate;

public class ModCreativeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, StalkAndStock.MODID);

    public static final RegistryObject<CreativeModeTab> STALKANDSTOCK_TAB = CREATIVE_MODE_TABS.register("stalkandstock_tab", () -> CreativeModeTab.builder()
            .icon(() -> ModItems.CORNCOB.get().getDefaultInstance())
            .noScrollBar().withLabelColor(15830528).title(Component.empty().append(Component.translatable("creativetab.stalkandstock.stalkandstock_tab")))
            .displayItems((parameters, output) -> {
                output.accept(ModItems.CORN_KERNELS.get());
                output.accept(ModItems.CORNCOB.get());
                output.accept(ModItems.COOKED_CORNCOB.get());
                output.accept(ModItems.CORNBREAD.get());
                output.accept(ModBlocks.PACKED_KERNELS.get());
                output.accept(ModBlocks.POLISHED_KERNELS.get());
                output.accept(ModBlocks.POLISHED_KERNEL_STAIRS.get());
                output.accept(ModBlocks.POLISHED_KERNELS_SLAB.get());
                output.accept(ModItems.WILD_CORN.get());
                output.accept(ModItems.YELLOW_TASSEL.get());
                output.accept(ModItems.MAIZE_BANNER_PATTERN.get());
                addModdedPaintings(output, CreativeModeTab.TabVisibility.PARENT_TAB_ONLY);


            }).build());

    private static void addModdedPaintings(CreativeModeTab.Output output,CreativeModeTab.TabVisibility tabVisibility) {
        Holder<PaintingVariant> cornfield_variant = ForgeRegistries.PAINTING_VARIANTS.getHolder(ModMiscRegistry.CORNFIELD.get()).get();

        ItemStack paintingStack = new ItemStack(Items.PAINTING);
            CompoundTag entityTag = paintingStack.getOrCreateTagElement("EntityTag");
            Painting.storeVariant(entityTag,cornfield_variant);
            output.accept(paintingStack, tabVisibility);
    }

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
