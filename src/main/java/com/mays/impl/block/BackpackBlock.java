package com.mays.impl.block;

import com.mays.impl.entities.BackpackBlockEntity;
import com.mays.registries.ItemRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

public class BackpackBlock extends CurrentBlockEntity {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    private final VoxelShape SHAPE = makeShape();

    public BackpackBlock() {
        super(BlockBehaviour.Properties.of().sound(SoundType.WOOL).noOcclusion());
        this.registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.NORTH));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new BackpackBlockEntity(pos, state);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (!level.isClientSide()) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof BackpackBlockEntity backpackBlockEntity) {
                NetworkHooks.openScreen((ServerPlayer) player, backpackBlockEntity, pos);
            }
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        if (placer != null) {
            if (placer instanceof Player player) {
                level.setBlock(pos.above(), state.setValue(FACING, player.getDirection().getOpposite()), 2);
            }
        }

        if (level.getBlockEntity(pos) instanceof BackpackBlockEntity backpackBlockEntity) {
            backpackBlockEntity.loadFromItem(stack);
        }
    }

    @Override
    public void playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        if (level.getBlockEntity(pos) instanceof BackpackBlockEntity backpackBlockEntity) {
            ItemStack stack = new ItemStack(ItemRegistry.BACKPACK.get());
            backpackBlockEntity.saveToItem(stack);
            if (!player.isCreative()) {
                popResource(level, pos, stack);
            }
        }
        super.playerWillDestroy(level, pos, state, player);
    }

//    @Override
//    public VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
//        return SHAPE;
//    }

    private VoxelShape makeShape() {
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0.1875, 0.5625, 0.71875, 0.8125, 0.875, 0.78125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.1875, 0.875, 0.28125, 0.8125, 0.9375, 0.71875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.375, 0.9375, 0.34375, 0.4375, 1, 0.53125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.5625, 0.9375, 0.34375, 0.625, 1, 0.53125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.5625, 0.9375, 0.65625, 0.625, 1, 0.71875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.625, 0.9375, 0.46875, 0.6875, 1, 0.71875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.3125, 0.9375, 0.46875, 0.375, 1, 0.71875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.3125, 0.875, 0.71875, 0.4375, 0.9375, 0.78125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.5, 0.875, 0.71875, 0.625, 0.9375, 0.78125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.375, 0.8125, 0.78125, 0.4375, 0.875, 0.84375), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.4375, 0.75, 0.78125, 0.5625, 0.875, 0.84375), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.4375, 0.625, 0.78125, 0.5625, 0.6875, 0.84375), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.5625, 0.5, 0.71875, 0.625, 0.5625, 0.78125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.5625, 0.25, 0.78125, 0.625, 0.5, 0.84375), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.375, 0.3125, 0.78125, 0.4375, 0.5, 0.84375), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.375, 0.5, 0.71875, 0.4375, 0.5625, 0.78125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.625, 0.125, 0.09375, 0.8125, 0.8125, 0.21875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.1875, 0.125, 0.09375, 0.375, 0.8125, 0.21875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.125, 0.0625, 0.21875, 0.875, 0.875, 0.71875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.1875, 0.125, 0.71875, 0.8125, 0.5, 0.78125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.1875, 0, 0.21875, 0.8125, 0.0625, 0.65625), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.875, 0.125, 0.28125, 0.9375, 0.625, 0.59375), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.9375, 0.5, 0.40625, 1, 0.5625, 0.46875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.9375, 0.125, 0.28125, 1, 0.5, 0.59375), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.0625, 0.125, 0.28125, 0.125, 0.625, 0.59375), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0, 0.5, 0.40625, 0.0625, 0.5625, 0.46875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0, 0.125, 0.28125, 0.0625, 0.5, 0.59375), BooleanOp.OR);

        return shape;
    }
}
