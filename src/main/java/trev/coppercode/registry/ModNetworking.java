package trev.coppercode.registry;

import trev.coppercode.network.packet.RunRoutineC2SPacket;
import trev.coppercode.network.packet.SaveRoutineC2SPacket;
import trev.coppercode.network.packet.StopRoutineC2SPacket;
import trev.coppercode.network.packet.UpdateGolemConfigC2SPacket;

/** Owns common packet registration and server-side receiver setup. */
public final class ModNetworking {
	private ModNetworking() {
	}

	public static void initialize() {
		registerPayloadTypes();
		registerServerReceivers();
	}

	private static void registerPayloadTypes() {
		SaveRoutineC2SPacket.registerPayloadType();
		RunRoutineC2SPacket.registerPayloadType();
		StopRoutineC2SPacket.registerPayloadType();
		UpdateGolemConfigC2SPacket.registerPayloadType();
	}

	private static void registerServerReceivers() {
		SaveRoutineC2SPacket.registerServerReceiver();
		RunRoutineC2SPacket.registerServerReceiver();
		StopRoutineC2SPacket.registerServerReceiver();
		UpdateGolemConfigC2SPacket.registerServerReceiver();
	}
}
