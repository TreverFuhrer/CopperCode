package trev.coppercode.client.network;

/** Owns clientbound packet receivers and client networking helpers. */
public final class ClientPacketHandlers {
	private ClientPacketHandlers() {
	}

	public static void initialize() {
		registerClientReceivers();
	}

	private static void registerClientReceivers() {
		// TODO Register clientbound packet receivers here.
	}
}
