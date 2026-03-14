package trev.coppercode.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;
import trev.coppercode.registry.ModItems;

/** Generates client asset models for blocks and items. */
public final class ModModelProvider extends FabricModelProvider {
	public ModModelProvider(FabricDataOutput output) {
		super(output);
	}

	@Override
	public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
		// TODO Generate blockstate and block models here when mod blocks are added.
	}

	@Override
	public void generateItemModels(ItemModelGenerators itemModelGenerator) {
		ModItems.entries().forEach(entry -> itemModelGenerator.generateFlatItem(entry.item(), ModelTemplates.FLAT_ITEM));
	}
}
