package trev.coppercode.client.registry;

import net.minecraft.client.gui.screens.MenuScreens;
import trev.coppercode.client.screen.CodingDeskScreen;
import trev.coppercode.client.screen.CopperGolemScreen;
import trev.coppercode.registry.ModScreenHandlers;

/** Owns handled screen registrations for client-only menu UIs. */
public final class ModScreens {
	private ModScreens() {
	}

	public static void initialize() {
		registerScreens();
	}

	private static void registerScreens() {
		MenuScreens.register(ModScreenHandlers.CODING_DESK, CodingDeskScreen::new);
		MenuScreens.register(ModScreenHandlers.COPPER_GOLEM, CopperGolemScreen::new);
	}
}
