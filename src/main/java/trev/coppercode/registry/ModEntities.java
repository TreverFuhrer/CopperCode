package trev.coppercode.registry;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import trev.coppercode.entity.ProgrammableCopperGolemEntity;
import trev.coppercode.util.ModIds;

/** Owns entity registrations and default attributes for Copper Code mobs. */
public final class ModEntities {
	public static final EntityType<ProgrammableCopperGolemEntity> PROGRAMMABLE_COPPER_GOLEM = register(
		"programmable_copper_golem",
		EntityType.Builder.of(ProgrammableCopperGolemEntity::new, MobCategory.MISC).sized(0.49F, 0.98F).eyeHeight(0.8125F).clientTrackingRange(10)
	);

	private ModEntities() {
	}

	public static void initialize() {
		registerDefaultAttributes();
	}

	private static void registerDefaultAttributes() {
		FabricDefaultAttributeRegistry.register(PROGRAMMABLE_COPPER_GOLEM, ProgrammableCopperGolemEntity.createAttributes());
	}

	private static <T extends Entity> EntityType<T> register(String path, EntityType.Builder<T> builder) {
		ResourceKey<EntityType<?>> entityKey = ResourceKey.create(Registries.ENTITY_TYPE, ModIds.id(path));
		return Registry.register(BuiltInRegistries.ENTITY_TYPE, entityKey, builder.build(entityKey));
	}
}
