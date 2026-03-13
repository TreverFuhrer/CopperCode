package trev.coppercode.registry;

import java.util.function.Function;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import trev.coppercode.util.ModIds;

/** Owns item registrations and shared item registration helpers. */
public final class ModItems {
	private ModItems() {
	}

	public static void initialize() {
		registerItems();
	}

	private static void registerItems() {
		// TODO Register mod items here.
	}

	public static Item register(String path, Function<Item.Properties, Item> factory, Item.Properties properties) {
		return Registry.register(BuiltInRegistries.ITEM, ModIds.id(path), factory.apply(properties));
	}
}
