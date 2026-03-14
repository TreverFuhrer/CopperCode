package trev.coppercode.network.packet;

import net.minecraft.resources.Identifier;
import trev.coppercode.script.data.RoutineReference;
import trev.coppercode.util.ModIds;

/** Placeholder serverbound packet for requesting execution of a selected routine. */
public record RunRoutineC2SPacket(int golemEntityId, RoutineReference routine) {
	public static final Identifier ID = ModIds.id("run_routine");

	public static void registerPayloadType() {
		// TODO Register CustomPacketPayload type and codec.
	}

	public static void registerServerReceiver() {
		// TODO Validate golem ownership/state and start runtime execution.
	}
}
