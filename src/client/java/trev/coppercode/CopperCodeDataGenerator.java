package trev.coppercode;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import trev.coppercode.datagen.ModBlockLootTableProvider;
import trev.coppercode.datagen.ModBlockTagProvider;
import trev.coppercode.datagen.ModEnglishLangProvider;
import trev.coppercode.datagen.ModItemTagProvider;
import trev.coppercode.datagen.ModModelProvider;
import trev.coppercode.datagen.ModRecipeProvider;

/** Datagen entrypoint that owns future providers for generated assets and data. */
public final class CopperCodeDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		configureProviders(pack);
	}

	private void configureProviders(FabricDataGenerator.Pack pack) {
		ModBlockTagProvider blockTagProvider = pack.addProvider(ModBlockTagProvider::new);

		pack.addProvider(ModEnglishLangProvider::new);
		pack.addProvider(ModRecipeProvider::new);
		pack.addProvider(ModModelProvider::new);
		pack.addProvider(ModBlockLootTableProvider::new);
		pack.addProvider((output, registriesFuture) -> new ModItemTagProvider(output, registriesFuture, blockTagProvider));
	}
}
