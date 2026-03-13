package trev.coppercode.datagen;

import java.util.concurrent.CompletableFuture;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;
import trev.coppercode.util.ModConstants;

/** Generates the base English translations for Copper Code. */
public final class ModEnglishLangProvider extends FabricLanguageProvider {
	public ModEnglishLangProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
		super(dataOutput, registryLookup);
	}

	@Override
	public void generateTranslations(HolderLookup.Provider registryLookup, TranslationBuilder translationBuilder) {
		translationBuilder.add("itemGroup.coppercode.main", ModConstants.MOD_NAME);

		// TODO Add item, block, entity, screen, and message translations here as content is introduced.
	}
}
