package trev.coppercode.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.redstone.Orientation;
import org.jspecify.annotations.Nullable;
import trev.coppercode.entity.ProgrammableCopperGolemEntity;
import trev.coppercode.registry.ModEntities;

/** Hidden ritual block created by using a Copper Core on a copper block. */
public final class CopperCoreBlock extends Block {
	public static final MapCodec<CopperCoreBlock> CODEC = simpleCodec(CopperCoreBlock::new);

	public CopperCoreBlock(BlockBehaviour.Properties properties) {
		super(properties);
	}

	@Override
	public MapCodec<? extends CopperCoreBlock> codec() {
		return CODEC;
	}

	@Override
	protected void onPlace(BlockState blockState, Level level, BlockPos blockPos, BlockState previousState, boolean movedByPiston) {
		if (!previousState.is(blockState.getBlock())) {
			this.trySpawnGolem(level, blockPos);
		}
	}

	@Override
	protected void neighborChanged(
		BlockState blockState,
		Level level,
		BlockPos blockPos,
		Block neighborBlock,
		@Nullable Orientation orientation,
		boolean movedByPiston
	) {
		this.trySpawnGolem(level, blockPos);
	}

	private void trySpawnGolem(Level level, BlockPos blockPos) {
		if (!(level instanceof ServerLevel serverLevel)) {
			return;
		}

		BlockPos pumpkinPos = blockPos.above();
		BlockState pumpkinState = serverLevel.getBlockState(pumpkinPos);
		if (!pumpkinState.is(Blocks.JACK_O_LANTERN)) {
			return;
		}

		ProgrammableCopperGolemEntity golem = ModEntities.PROGRAMMABLE_COPPER_GOLEM.create(serverLevel, EntitySpawnReason.TRIGGERED);
		if (golem == null) {
			return;
		}

		clearRitualBlocks(serverLevel, blockPos, pumpkinPos);
		golem.snapTo(blockPos.getX() + 0.5, blockPos.getY() + 0.05, blockPos.getZ() + 0.5, 0.0F, 0.0F);
		serverLevel.addFreshEntity(golem);

		for (ServerPlayer serverPlayer : serverLevel.getEntitiesOfClass(ServerPlayer.class, golem.getBoundingBox().inflate(5.0))) {
			CriteriaTriggers.SUMMONED_ENTITY.trigger(serverPlayer, golem);
		}

		serverLevel.updateNeighborsAt(blockPos, Blocks.AIR);
		serverLevel.updateNeighborsAt(pumpkinPos, Blocks.AIR);
	}

	private static void clearRitualBlocks(ServerLevel level, BlockPos basePos, BlockPos topPos) {
		BlockState baseState = level.getBlockState(basePos);
		BlockState topState = level.getBlockState(topPos);
		level.setBlock(basePos, Blocks.AIR.defaultBlockState(), 2);
		level.levelEvent(2001, basePos, Block.getId(baseState));
		level.setBlock(topPos, Blocks.AIR.defaultBlockState(), 2);
		level.levelEvent(2001, topPos, Block.getId(topState));
	}
}
