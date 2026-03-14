package trev.coppercode.network.packet;

import net.minecraft.resources.Identifier;
import trev.coppercode.script.data.RoutineReference;
import trev.coppercode.util.ModIds;

/** Placeholder serverbound packet for future non-slot golem configuration changes. */
public record UpdateGolemConfigC2SPacket(int golemEntityId, RoutineReference selectedRoutine, boolean shouldAutoRun) {
	public static final Identifier ID = ModIds.id("update_golem_config");

	public static void registerPayloadType() {
		// TODO Register CustomPacketPayload type and codec.
	}

	public static void registerServerReceiver() {
		// TODO Apply validated config updates to the target programmable golem.
	}
}
