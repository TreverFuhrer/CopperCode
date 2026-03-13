package trev.coppercode.registry;

/** Owns block entity type registrations for future interactive blocks. */
public final class ModBlockEntities {
	private ModBlockEntities() {
	}

	public static void initialize() {
		registerBlockEntityTypes();
	}

	private static void registerBlockEntityTypes() {
		// TODO Register block entity types here when blocks require persistent logic.
	}
}
