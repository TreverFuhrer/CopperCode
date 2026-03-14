package trev.coppercode.registry;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import trev.coppercode.util.ModIds;

/** Owns creative tab registrations and display ordering hooks. */
public final class ModItemGroups {
	public static final CreativeModeTab MAIN = Registry.register(
		BuiltInRegistries.CREATIVE_MODE_TAB,
		ModIds.id("main"),
		FabricItemGroup.builder()
			.title(Component.translatable("itemGroup.coppercode.main"))
			.icon(() -> new ItemStack(ModItems.COPPER_CORE))
			.displayItems((parameters, output) -> {
				ModItems.entries().forEach(entry -> output.accept(entry.item()));
				ModBlocks.entries().forEach(entry -> output.accept(entry.item()));
			})
			.build()
	);

	private ModItemGroups() {
	}

	public static void initialize() {
		// Class loading performs item group registration through the static field above.
	}
}
