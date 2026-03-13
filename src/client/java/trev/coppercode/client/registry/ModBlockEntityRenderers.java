package trev.coppercode.client.registry;

/** Owns block entity renderer registrations for client-only block visuals. */
public final class ModBlockEntityRenderers {
	private ModBlockEntityRenderers() {
	}

	public static void initialize() {
		registerBlockEntityRenderers();
	}

	private static void registerBlockEntityRenderers() {
		// TODO Register block entity renderers here when block entities are added.
	}
}
