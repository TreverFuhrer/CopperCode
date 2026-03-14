package trev.coppercode.network.packet;

import net.minecraft.resources.Identifier;
import trev.coppercode.script.data.RoutineData;
import trev.coppercode.script.data.RoutineReference;
import trev.coppercode.util.ModIds;

/** Placeholder serverbound packet for saving routine source back to a container or golem slot. */
public record SaveRoutineC2SPacket(RoutineReference target, RoutineData routine) {
	public static final Identifier ID = ModIds.id("save_routine");

	public static void registerPayloadType() {
		// TODO Register CustomPacketPayload type and codec.
	}

	public static void registerServerReceiver() {
		// TODO Register server receiver and validate editor permissions.
	}
}
