package trev.coppercode.datagen;

import java.util.concurrent.CompletableFuture;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;
import trev.coppercode.golem.inventory.GolemSlotTypes;
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
		translationBuilder.add(GolemSlotTypes.LEFT_TOOL.translationKey(), "Left Tool");
		translationBuilder.add(GolemSlotTypes.CORE.translationKey(), "Core");
		translationBuilder.add(GolemSlotTypes.RIGHT_TOOL.translationKey(), "Right Tool");
		translationBuilder.add(GolemSlotTypes.STORAGE.translationKey(), "Storage");
		translationBuilder.add(GolemSlotTypes.UPGRADE_1.translationKey(), "Upgrade I");
		translationBuilder.add(GolemSlotTypes.UPGRADE_2.translationKey(), "Upgrade II");
		translationBuilder.add(GolemSlotTypes.UPGRADE_3.translationKey(), "Upgrade III");
		translationBuilder.add(GolemSlotTypes.BAND.translationKey(), "Band");
		translationBuilder.add(GolemSlotTypes.CARGO.translationKey(), "Cargo");
		translationBuilder.add("gui.coppercode.golem.equipment", "Equipment");
		translationBuilder.add("gui.coppercode.golem.cargo", "Cargo");
		translationBuilder.add("gui.coppercode.golem.status", "Status");
		translationBuilder.add("screen.coppercode.code_book", "Code Book");
		translationBuilder.add("screen.coppercode.instruction_page", "Instruction Page");
	}
}
