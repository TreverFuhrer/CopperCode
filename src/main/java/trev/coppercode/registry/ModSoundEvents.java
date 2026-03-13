package trev.coppercode.registry;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import trev.coppercode.util.ModIds;

/** Owns sound event registrations and a helper for future custom sounds. */
public final class ModSoundEvents {
	private ModSoundEvents() {
	}

	public static void initialize() {
		registerSoundEvents();
	}

	private static void registerSoundEvents() {
		// TODO Register custom sound events here.
	}

	public static SoundEvent register(String path) {
		Identifier id = ModIds.id(path);
		return Registry.register(BuiltInRegistries.SOUND_EVENT, id, SoundEvent.createVariableRangeEvent(id));
	}
}
