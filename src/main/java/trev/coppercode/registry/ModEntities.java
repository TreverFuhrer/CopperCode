package trev.coppercode.registry;

/** Owns entity registrations and future attribute wiring. */
public final class ModEntities {
	private ModEntities() {
	}

	public static void initialize() {
		registerEntityTypes();
		registerDefaultAttributes();
	}

	private static void registerEntityTypes() {
		// TODO Register entity types here.
	}

	private static void registerDefaultAttributes() {
		// TODO Register default attributes after entity types exist.
	}
}
