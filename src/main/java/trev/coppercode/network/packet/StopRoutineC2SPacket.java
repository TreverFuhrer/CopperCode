package trev.coppercode.network.packet;

import net.minecraft.resources.Identifier;
import trev.coppercode.util.ModIds;

/** Placeholder serverbound packet for stopping the currently running routine. */
public record StopRoutineC2SPacket(int golemEntityId) {
	public static final Identifier ID = ModIds.id("stop_routine");

	public static void registerPayloadType() {
		// TODO Register CustomPacketPayload type and codec.
	}

	public static void registerServerReceiver() {
		// TODO Stop the active interpreter context for the target golem.
	}
}
