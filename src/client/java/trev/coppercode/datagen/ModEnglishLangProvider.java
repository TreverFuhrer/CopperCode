package trev.coppercode.datagen;

import java.util.concurrent.CompletableFuture;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;
import trev.coppercode.inventory.GolemSlotTypes;
import trev.coppercode.registry.ModBlocks;
import trev.coppercode.registry.ModItems;
import trev.coppercode.util.ModConstants;

/** Generates the base English translations for Copper Code. */
public final class ModEnglishLangProvider extends FabricLanguageProvider {
	public ModEnglishLangProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
		super(dataOutput, registryLookup);
	}

	@Override
	public void generateTranslations(HolderLookup.Provider registryLookup, TranslationBuilder translationBuilder) {
		translationBuilder.add("itemGroup.coppercode.main", ModConstants.MOD_NAME);
		ModItems.entries().forEach(entry -> translationBuilder.add(entry.item(), entry.englishName()));
		ModBlocks.entries().forEach(entry -> translationBuilder.add(entry.block(), entry.englishName()));
		translationBuilder.add("block.coppercode.copper_core_block", "Copper Core Block");
		translationBuilder.add("entity.coppercode.programmable_copper_golem", "Programmable Copper Golem");
		translationBuilder.add(GolemSlotTypes.LEFT_ARM.translationKey(), "Left Arm");
		translationBuilder.add(GolemSlotTypes.CORE.translationKey(), "Core");
		translationBuilder.add(GolemSlotTypes.RIGHT_ARM.translationKey(), "Right Arm");
		translationBuilder.add(GolemSlotTypes.STORAGE.translationKey(), "Storage");
	}
}
