package net.hoihelt.stalkandstock.datagen.loot;

import net.hoihelt.stalkandstock.block.CornCropBlock;
import net.hoihelt.stalkandstock.registry.ModBlocks;
import net.hoihelt.stalkandstock.registry.ModItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.storage.loot.IntRange;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.AlternativesEntry;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.LimitCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootSubProvider extends BlockLootSubProvider {
   public ModBlockLootSubProvider() {super(Set.of(), FeatureFlags.REGISTRY.allFlags());}
    @Override
    protected void generate() {
        this.dropSelf(ModBlocks.PACKED_KERNELS.get());
        this.dropSelf(ModBlocks.POLISHED_KERNELS.get());
        this.dropSelf(ModBlocks.POLISHED_KERNEL_STAIRS.get());
        this.dropSelf(ModBlocks.POLISHED_KERNELS_SLAB.get());
        this.dropSelf(ModBlocks.YELLOW_TASSEL.get());

        this.add(ModBlocks.POTTED_YELLOW_TASSEL.get(), (block -> this.createPotFlowerItemTable(ModItems.YELLOW_TASSEL.get())));


        this.add(ModBlocks.CORN.get(),(block -> this.createCornCropLoot()));

        this.add(ModBlocks.WILD_CORN.get(),(builder) -> createShearsDispatchTable(ModBlocks.WILD_CORN.get(), this.applyExplosionDecay(builder, LootItem.lootTableItem(ModItems.CORN_KERNELS.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F))).apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE)).apply(LimitCount.limitCount(IntRange.range(0, 4))))));

    }

    private LootTable.Builder createCornCropLoot() {
       return this.applyExplosionDecay(ModBlocks.CORN.get(),LootTable.lootTable().withPool(LootPool.lootPool().add(AlternativesEntry.alternatives(CornCropBlock.AGE.getPossibleValues(), (age)-> {

           LootItemCondition.Builder lowerhalfcondition$builder = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.CORN.get())
                   .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CornCropBlock.HALF,DoubleBlockHalf.LOWER));

           LootItemCondition.Builder agelootcondition$builder = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.CORN.get())
                   .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CornCropBlock.AGE,age));

           return age == 7 ?
                   LootItem.lootTableItem(ModItems.CORNCOB.get())
                           .when(lowerhalfcondition$builder).when(agelootcondition$builder)
                           .apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE,0.5714286F,3)) :
                   LootItem.lootTableItem(ModItems.CORN_KERNELS.get())
                           .when(lowerhalfcondition$builder).when(agelootcondition$builder)
                           .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0f))); }))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
