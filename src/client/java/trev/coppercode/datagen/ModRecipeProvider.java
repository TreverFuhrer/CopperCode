package trev.coppercode.datagen;

import java.util.concurrent.CompletableFuture;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import trev.coppercode.registry.ModBlocks;
import trev.coppercode.registry.ModItems;

/** Generates Copper Code recipe data once craftable content exists. */
public final class ModRecipeProvider extends FabricRecipeProvider {
	public ModRecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
		super(output, registriesFuture);
	}

	@Override
	public String getName() {
		return "Copper Code Recipes";
	}

	@Override
	protected RecipeProvider createRecipeProvider(HolderLookup.Provider registryLookup, RecipeOutput exporter) {
		return new RecipeProvider(registryLookup, exporter) {
			@Override
			public void buildRecipes() {
				HolderGetter<Item> items = registryLookup.lookupOrThrow(Registries.ITEM);

				ShapedRecipeBuilder.shaped(items, RecipeCategory.MISC, ModItems.COPPER_CORE)
					.define('C', Items.COPPER_INGOT)
					.define('R', Items.REDSTONE)
					.pattern(" C ")
					.pattern("CRC")
					.pattern(" C ")
					.unlockedBy("has_copper_ingot", has(Items.COPPER_INGOT))
					.save(exporter);

				ShapelessRecipeBuilder.shapeless(items, RecipeCategory.MISC, ModItems.INSTRUCTION_PAGE)
					.requires(Items.PAPER)
					.requires(Items.REDSTONE)
					.unlockedBy("has_paper", has(Items.PAPER))
					.save(exporter);

				ShapelessRecipeBuilder.shapeless(items, RecipeCategory.TOOLS, ModItems.CODE_BOOK)
					.requires(Items.BOOK)
					.requires(ModItems.INSTRUCTION_PAGE)
					.requires(ModItems.COPPER_CORE)
					.unlockedBy("has_instruction_page", has(ModItems.INSTRUCTION_PAGE))
					.save(exporter);

				ShapedRecipeBuilder.shaped(items, RecipeCategory.TOOLS, ModItems.GOLEM_REMOTE)
					.define('C', Items.COPPER_INGOT)
					.define('O', ModItems.COPPER_CORE)
					.define('R', Items.REDSTONE)
					.pattern(" CR")
					.pattern("COC")
					.pattern(" R ")
					.unlockedBy("has_copper_core", has(ModItems.COPPER_CORE))
					.save(exporter);

				ShapedRecipeBuilder.shaped(items, RecipeCategory.DECORATIONS, ModBlocks.CODING_DESK)
					.define('C', Items.COPPER_INGOT)
					.define('T', Items.CRAFTING_TABLE)
					.define('B', ModItems.CODE_BOOK)
					.define('O', ModItems.COPPER_CORE)
					.pattern(" C ")
					.pattern("TBT")
					.pattern(" O ")
					.unlockedBy("has_code_book", has(ModItems.CODE_BOOK))
					.save(exporter);
			}
		};
	}
}
