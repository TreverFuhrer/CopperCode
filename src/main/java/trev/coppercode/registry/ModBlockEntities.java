package trev.coppercode.registry;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import trev.coppercode.block.entity.CodingDeskBlockEntity;
import trev.coppercode.util.ModIds;

/** Owns block entity registrations for interactive Copper Code blocks. */
public final class ModBlockEntities {
	public static final BlockEntityType<CodingDeskBlockEntity> CODING_DESK = Registry.register(
		BuiltInRegistries.BLOCK_ENTITY_TYPE,
		ModIds.id("coding_desk"),
		FabricBlockEntityTypeBuilder.create(CodingDeskBlockEntity::new, ModBlocks.CODING_DESK).build()
	);

	private ModBlockEntities() {
	}

	public static void initialize() {
		// Class loading performs block entity registration through the static fields above.
	}
}
