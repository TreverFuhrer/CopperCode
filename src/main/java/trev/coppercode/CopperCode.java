package trev.coppercode;

import net.fabricmc.api.ModInitializer;
import trev.coppercode.registry.ModBlockEntities;
import trev.coppercode.registry.ModBlocks;
import trev.coppercode.registry.ModCommands;
import trev.coppercode.registry.ModDataComponents;
import trev.coppercode.registry.ModEntities;
import trev.coppercode.registry.ModItemGroups;
import trev.coppercode.registry.ModItems;
import trev.coppercode.registry.ModNetworking;
import trev.coppercode.registry.ModScreenHandlers;
import trev.coppercode.registry.ModSoundEvents;
import trev.coppercode.util.ModConstants;
import trev.coppercode.util.ModLogger;

/** Common/server-safe entrypoint for Copper Code. */
public final class CopperCode implements ModInitializer {
	@Override
	public void onInitialize() {
		ModLogger.info("Initializing {}", ModConstants.MOD_NAME);

		ModSoundEvents.initialize();
		ModDataComponents.initialize();
		ModBlocks.initialize();
		ModItems.initialize();
		ModBlockEntities.initialize();
		ModEntities.initialize();
		ModScreenHandlers.initialize();
		ModItemGroups.initialize();
		ModNetworking.initialize();
		ModCommands.initialize();

		ModLogger.info("{} initialized", ModConstants.MOD_NAME);
	}
}
