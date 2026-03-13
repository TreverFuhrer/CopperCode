package trev.coppercode.datagen;

import java.util.concurrent.CompletableFuture;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;

/** Generates item tags for Copper Code items and block items. */
public final class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
	public ModItemTagProvider(
		FabricDataOutput output,
		CompletableFuture<HolderLookup.Provider> registriesFuture,
		ModBlockTagProvider blockTagProvider
	) {
		super(output, registriesFuture, blockTagProvider);
	}

	@Override
	protected void addTags(HolderLookup.Provider wrapperLookup) {
		// TODO Copy relevant block tags and add item-only tags here when content is introduced.
	}
}
