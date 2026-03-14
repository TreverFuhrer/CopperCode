package trev.coppercode.registry;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import trev.coppercode.block.CodingDeskBlock;
import trev.coppercode.block.CopperCoreBlock;
import trev.coppercode.util.ModIds;

/** Owns block registrations and block-item metadata used by UI and datagen. */
public final class ModBlocks {
	private static final List<BlockDefinition> BLOCK_DEFINITIONS = new ArrayList<>();

	public static final Block COPPER_CORE_BLOCK = registerWithoutItem(
		"copper_core_block",
		CopperCoreBlock::new,
		BlockBehaviour.Properties.of().strength(3.0F).sound(SoundType.COPPER)
	);

	public static final Block CODING_DESK = registerWithItem(
		"coding_desk",
		"Coding Desk",
		CodingDeskBlock::new,
		BlockBehaviour.Properties.of().strength(2.5F).sound(SoundType.WOOD).noOcclusion()
	);

	private ModBlocks() {
	}

	public static void initialize() {
		// Class loading performs block registration through the static fields above.
	}

	public static List<BlockDefinition> entries() {
		return List.copyOf(BLOCK_DEFINITIONS);
	}

	private static <T extends Block> T registerWithoutItem(String path, Function<BlockBehaviour.Properties, T> factory, BlockBehaviour.Properties properties) {
		ResourceKey<Block> blockKey = ResourceKey.create(Registries.BLOCK, ModIds.id(path));
		T block = factory.apply(properties.setId(blockKey));
		Registry.register(BuiltInRegistries.BLOCK, blockKey, block);
		return block;
	}

	private static <T extends Block> T registerWithItem(
		String path,
		String englishName,
		Function<BlockBehaviour.Properties, T> factory,
		BlockBehaviour.Properties properties
	) {
		ResourceKey<Block> blockKey = ResourceKey.create(Registries.BLOCK, ModIds.id(path));
		T block = factory.apply(properties.setId(blockKey));
		Registry.register(BuiltInRegistries.BLOCK, blockKey, block);

		BlockItem blockItem = registerBlockItem(path, block);
		BLOCK_DEFINITIONS.add(new BlockDefinition(path, englishName, block, blockItem));
		return block;
	}

	private static BlockItem registerBlockItem(String path, Block block) {
		ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, ModIds.id(path));
		BlockItem blockItem = new BlockItem(block, new Item.Properties().setId(itemKey));
		Registry.register(BuiltInRegistries.ITEM, itemKey, blockItem);
		return blockItem;
	}

	public record BlockDefinition(String path, String englishName, Block block, BlockItem item) {
	}
}
