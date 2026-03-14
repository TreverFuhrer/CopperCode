package trev.coppercode.registry;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import trev.coppercode.screen.CodingDeskScreenHandler;
import trev.coppercode.screen.CopperGolemScreenHandler;
import trev.coppercode.util.ModIds;

/** Owns menu registrations shared between the server and handled client screens. */
public final class ModScreenHandlers {
	public static final MenuType<CodingDeskScreenHandler> CODING_DESK = register("coding_desk", CodingDeskScreenHandler::new);
	public static final MenuType<CopperGolemScreenHandler> COPPER_GOLEM = register("copper_golem", CopperGolemScreenHandler::new);

	private ModScreenHandlers() {
	}

	public static void initialize() {
		// Class loading performs menu registration through the static fields above.
	}

	private static <T extends AbstractContainerMenu> MenuType<T> register(String path, MenuType.MenuSupplier<T> menuSupplier) {
		return Registry.register(BuiltInRegistries.MENU, ModIds.id(path), new MenuType<>(menuSupplier, FeatureFlags.VANILLA_SET));
	}
}
