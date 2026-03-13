package trev.coppercode;

import net.fabricmc.api.ClientModInitializer;
import trev.coppercode.client.network.ClientPacketHandlers;
import trev.coppercode.client.registry.ModBlockEntityRenderers;
import trev.coppercode.client.registry.ModEntityRenderers;
import trev.coppercode.client.registry.ModScreens;
import trev.coppercode.util.ModConstants;
import trev.coppercode.util.ModLogger;

/** Client-only entrypoint for Copper Code. */
public final class CopperCodeClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ModLogger.info("Initializing {} client", ModConstants.MOD_NAME);

		ModScreens.initialize();
		ModEntityRenderers.initialize();
		ModBlockEntityRenderers.initialize();
		ClientPacketHandlers.initialize();
	}
}
