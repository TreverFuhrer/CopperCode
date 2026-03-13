package trev.coppercode.registry;

import java.util.function.Function;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import trev.coppercode.util.ModIds;

/** Owns block registrations and shared block registration helpers. */
public final class ModBlocks {
	private ModBlocks() {
	}

	public static void initialize() {
		registerBlocks();
	}

	private static void registerBlocks() {
		// TODO Register mod blocks here.
		// TODO Register matching BlockItem entries in ModItems when blocks are introduced.
	}

	public static Block register(String path, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties properties) {
		return Registry.register(BuiltInRegistries.BLOCK, ModIds.id(path), factory.apply(properties));
	}
}
