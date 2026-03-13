package trev.coppercode.registry;

/** Owns common packet registration and server-side receiver setup. */
public final class ModNetworking {
	private ModNetworking() {
	}

	public static void initialize() {
		registerPayloadTypes();
		registerServerReceivers();
	}

	private static void registerPayloadTypes() {
		// TODO Register CustomPayload.Id and packet codecs here.
	}

	private static void registerServerReceivers() {
		// TODO Register serverbound packet receivers here.
	}
}
