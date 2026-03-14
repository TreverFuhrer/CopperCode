package trev.coppercode.item;

import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import trev.coppercode.registry.ModBlocks;

/** Converts a copper block into the ritual-only Copper Core Block. */
public final class CopperCoreItem extends Item {
	public CopperCoreItem(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		Level level = context.getLevel();
		if (!level.getBlockState(context.getClickedPos()).is(Blocks.COPPER_BLOCK)) {
			return InteractionResult.PASS;
		}

		if (!level.isClientSide()) {
			Block ritualBlock = ModBlocks.COPPER_CORE_BLOCK;
			level.setBlock(context.getClickedPos(), ritualBlock.defaultBlockState(), Block.UPDATE_ALL);

			SoundType soundType = ritualBlock.defaultBlockState().getSoundType();
			level.playSound(
				null,
				context.getClickedPos(),
				soundType.getPlaceSound(),
				SoundSource.BLOCKS,
				(soundType.getVolume() + 1.0F) / 2.0F,
				soundType.getPitch() * 0.8F
			);

			ItemStack itemStack = context.getItemInHand();
			itemStack.consume(1, context.getPlayer());
		}

		return InteractionResult.SUCCESS;
	}
}
