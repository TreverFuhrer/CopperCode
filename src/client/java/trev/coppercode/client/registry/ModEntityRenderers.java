package trev.coppercode.client.registry;

import net.minecraft.client.renderer.entity.EntityRenderers;
import trev.coppercode.client.render.entity.ProgrammableCopperGolemRenderer;
import trev.coppercode.registry.ModEntities;

/** Owns entity renderer registrations for client-only visuals. */
public final class ModEntityRenderers {
	private ModEntityRenderers() {
	}

	public static void initialize() {
		registerEntityRenderers();
	}

	private static void registerEntityRenderers() {
		EntityRenderers.register(ModEntities.PROGRAMMABLE_COPPER_GOLEM, ProgrammableCopperGolemRenderer::new);
	}
}
