package trev.coppercode.registry;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import trev.coppercode.item.CodeBookItem;
import trev.coppercode.item.CopperCoreItem;
import trev.coppercode.item.GolemRemoteItem;
import trev.coppercode.item.InstructionPageItem;
import trev.coppercode.util.ModIds;

/** Owns item registrations and shared item registration helpers. */
public final class ModItems {
	private static final List<ItemDefinition<? extends Item>> ITEM_DEFINITIONS = new ArrayList<>();

	public static final Item COPPER_CORE = register("copper_core", "Copper Core", CopperCoreItem::new);
	public static final Item INSTRUCTION_PAGE = register("instruction_page", "Instruction Page", InstructionPageItem::new);
	public static final Item CODE_BOOK = register("code_book", "Code Book", CodeBookItem::new);
	public static final Item GOLEM_REMOTE = register("golem_remote", "Golem Remote", GolemRemoteItem::new);

	private ModItems() {
	}

	public static void initialize() {
		// Class loading performs item registration through the static fields above.
	}

	public static List<ItemDefinition<? extends Item>> entries() {
		return List.copyOf(ITEM_DEFINITIONS);
	}

	private static <T extends Item> T register(String path, String englishName, Function<Item.Properties, T> factory) {
		ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, ModIds.id(path));
		T item = factory.apply(new Item.Properties().setId(itemKey));
		Registry.register(BuiltInRegistries.ITEM, itemKey, item);
		ITEM_DEFINITIONS.add(new ItemDefinition<>(path, englishName, item));
		return item;
	}

	public record ItemDefinition<T extends Item>(String path, String englishName, T item) {
	}
}
