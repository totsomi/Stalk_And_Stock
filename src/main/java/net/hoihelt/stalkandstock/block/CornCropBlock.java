package net.hoihelt.stalkandstock.block;

import net.hoihelt.stalkandstock.registry.ModBlocks;
import net.hoihelt.stalkandstock.registry.ModItems;
import net.hoihelt.stalkandstock.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Ravager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("deprecation")
public class CornCropBlock extends DoublePlantBlock implements BonemealableBlock {
    public static final IntegerProperty AGE = BlockStateProperties.AGE_7;

    public static final int MAX_AGE = 7;
    public static final int DOUBLE_PLANT_AGE_INTERSECTION = 4;
    public static final int BONEMEAL_INCREASE = 1;

    private static final VoxelShape[] UPPER_SHAPE_BY_AGE = new VoxelShape[]{
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 3.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 5.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 14.0D, 16.0D)};
    private static final VoxelShape[] LOWER_SHAPE_BY_AGE = new VoxelShape[]{
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D)};

    public CornCropBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter level, BlockPos pos, Player player) {
        return new ItemStack(ModItems.CORN_KERNELS.get());
    }

    private boolean isMaxAge(BlockState state) {
        return state.getValue(AGE) >= 7;
    }



    @Override
    protected boolean mayPlaceOn(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return pState.is(ModTags.Blocks.FARMLANDS);
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return state.getValue(HALF) == DoubleBlockHalf.LOWER && !this.isMaxAge(state);
    }

    public static boolean isLower(BlockState state){
        return state.is(ModBlocks.CORN.get()) && state.getValue(HALF) == DoubleBlockHalf.LOWER;
    }

    public static boolean isUpper(BlockState state){
        return state.is(ModBlocks.CORN.get()) && state.getValue(HALF) == DoubleBlockHalf.UPPER;
    }

    private boolean canGrow(LevelReader pLevel, BlockPos pPos, BlockState state, int age) {
        return !this.isMaxAge(state) && sufficientLight(pLevel, pPos) && (age < 4 || canGrowInto(pLevel, pPos.above()));
    }

    private static boolean canGrowInto(LevelReader level, BlockPos pos) {
        BlockState blockstate = level.getBlockState(pos);
        return blockstate.isAir() || blockstate.is(ModBlocks.CORN.get());
    }

    private static boolean sufficientLight(LevelReader pLevel, BlockPos pPos) {
        return pLevel.getRawBrightness(pPos, 0) >= 8 || pLevel.canSeeSky(pPos);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(AGE);
        super.createBlockStateDefinition(pBuilder);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return pState.getValue(HALF) == DoubleBlockHalf.UPPER ? UPPER_SHAPE_BY_AGE[Math.min(Math.abs(4 - (pState.getValue(AGE) + 1)),UPPER_SHAPE_BY_AGE.length - 1)] : LOWER_SHAPE_BY_AGE[pState.getValue(AGE)];
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState();
    }

    @Override
    public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
        return !pState.canSurvive(pLevel,pCurrentPos) ? Blocks.AIR.defaultBlockState() : pState;
    }

    @Override
    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        if (!isLower(pState)) {
            return super.canSurvive(pState, pLevel, pPos);
        } else {
            BlockPos below = pPos.below();
            boolean isSoil = this.mayPlaceOn(pLevel.getBlockState(below), pLevel, below);
            //if (pLevel.getBlockState(below).getBlock() == this)
             //   isSoil = pLevel.getBlockState(below).canSustainPlant(pLevel, below, Direction.UP, this);
            return isSoil && sufficientLight(pLevel, pPos) && (pState.getValue(AGE) < 4 || isUpper(pLevel.getBlockState(pPos.above())));
        }
    }





    @Override
    public void setPlacedBy(Level pLevel, BlockPos pPos, BlockState pState, LivingEntity pPlacer, ItemStack pStack) {

    }

    public void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity) {
        if (pEntity instanceof Ravager && pLevel.getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING)) {
            pLevel.destroyBlock(pPos, true, pEntity);
        }

        super.entityInside(pState, pLevel, pPos, pEntity);
    }

    @Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        float f = getGrowthSpeed(this, pLevel, pPos);
        boolean flag = pRandom.nextInt((int)(25.0F / f) + 1) == 0;
        if (flag) {
            this.grow(pLevel, pState, pPos, 1);
        }

    }

    private void grow(ServerLevel level, BlockState state, BlockPos pos, int i1) {
        int i = Math.min(state.getValue(AGE) + i1, 7);
        if (this.canGrow(level, pos, state, i)) {
            level.setBlock(pos, state.setValue(AGE, Integer.valueOf(i)), 2);
            if (i >= 4) {
                BlockPos above = pos.above();
                level.setBlock(above, copyWaterloggedFrom(level, pos, this.defaultBlockState().setValue(AGE, Integer.valueOf(i)).setValue(HALF, DoubleBlockHalf.UPPER)), 3);
            }

        }
    }

    @javax.annotation.Nullable
    private CornCropBlock.PosAndState getLowerHalf(LevelReader levelReader, BlockPos pos, BlockState state) {
        if (isLower(state)) {
            return new CornCropBlock.PosAndState(pos, state);
        } else {
            BlockPos blockpos = pos.below();
            BlockState blockstate = levelReader.getBlockState(blockpos);
            return isLower(blockstate) ? new CornCropBlock.PosAndState(blockpos, blockstate) : null;
        }
    }

    @Override
    public boolean canBeReplaced(BlockState pState, Fluid pFluid) {
        return false;
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader pLevel, BlockPos pPos, BlockState pState, boolean pIsClient) {
        CornCropBlock.PosAndState corncropblock$posandstate = this.getLowerHalf(pLevel,pPos,pState);
        return corncropblock$posandstate != null && this.canGrow(pLevel, corncropblock$posandstate.pos, corncropblock$posandstate.state, corncropblock$posandstate.state.getValue(AGE) + 1);
    }

    @Override
    public boolean isBonemealSuccess(Level pLevel, RandomSource pRandom, BlockPos pPos, BlockState pState) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel pLevel, RandomSource pRandom, BlockPos pPos, BlockState pState) {
        CornCropBlock.PosAndState corncropblock$posandstate = this.getLowerHalf(pLevel,pPos,pState);
        if (corncropblock$posandstate != null) {
            this.grow(pLevel,corncropblock$posandstate.state,corncropblock$posandstate.pos,1);
        }
    }

    static record PosAndState(BlockPos pos, BlockState state) {
    }
    protected static float getGrowthSpeed(Block pBlock, BlockGetter pLevel, BlockPos pPos) {
        float f = 1.0F;
        BlockPos blockpos = pPos.below();

        for(int i = -1; i <= 1; ++i) {
            for(int j = -1; j <= 1; ++j) {
                float growthSpeed = 0.0F;
                BlockState blockstate = pLevel.getBlockState(blockpos.offset(i, 0, j));
                if (blockstate.canSustainPlant(pLevel, blockpos.offset(i, 0, j), net.minecraft.core.Direction.UP, (net.minecraftforge.common.IPlantable) pBlock)) {
                    growthSpeed = 0.8F;
                    if (blockstate.isFertile(pLevel, pPos.offset(i, 0, j))) {
                        growthSpeed = 2.8F;
                    }
                }

                if (i != 0 || j != 0) {
                    growthSpeed /= 4.0F;
                }

                f += growthSpeed;
            }
        }

        BlockPos blockpos1 = pPos.north();
        BlockPos blockpos2 = pPos.south();
        BlockPos blockpos3 = pPos.west();
        BlockPos blockpos4 = pPos.east();
        boolean flag = pLevel.getBlockState(blockpos3).is(pBlock) || pLevel.getBlockState(blockpos4).is(pBlock);
        boolean flag1 = pLevel.getBlockState(blockpos1).is(pBlock) || pLevel.getBlockState(blockpos2).is(pBlock);
        if (flag && flag1) {
            f /= 2.0F;
        } else {
            boolean flag2 = pLevel.getBlockState(blockpos3.north()).is(pBlock) || pLevel.getBlockState(blockpos4.north()).is(pBlock) || pLevel.getBlockState(blockpos4.south()).is(pBlock) || pLevel.getBlockState(blockpos3.south()).is(pBlock);
            if (flag2) {
                f /= 2.0F;
            }
        }

        return f;
    }

}

