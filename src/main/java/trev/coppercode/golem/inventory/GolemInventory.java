package trev.coppercode.golem.inventory;

import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.jspecify.annotations.Nullable;
import trev.coppercode.entity.ProgrammableCopperGolemEntity;

/**
 * Common-side golem inventory backend.
 *
 * TODO Persist richer golem configuration state here once routines, upgrades, and unlocked capacity rules exist.
 */
public final class GolemInventory extends SimpleContainer {
	public static final int LEFT_TOOL_SLOT = 0;
	public static final int RIGHT_TOOL_SLOT = 1;
	public static final int STORAGE_SLOT = 2;
	public static final int CORE_SLOT = 3;
	public static final int UPGRADE_SLOT_1 = 4;
	public static final int UPGRADE_SLOT_2 = 5;
	public static final int UPGRADE_SLOT_3 = 6;
	public static final int BAND_SLOT = 7;
	public static final int EQUIPMENT_SLOT_COUNT = 8;
	public static final int CARGO_SLOT_COUNT = 27;
	public static final int TOTAL_SLOT_COUNT = EQUIPMENT_SLOT_COUNT + CARGO_SLOT_COUNT;

	@Nullable
	private final ProgrammableCopperGolemEntity owner;

	public GolemInventory(@Nullable ProgrammableCopperGolemEntity owner) {
		super(TOTAL_SLOT_COUNT);
		this.owner = owner;
	}

	@Override
	public boolean stillValid(Player player) {
		return this.owner == null || this.owner.isAlive() && this.owner.distanceToSqr(player) <= 64.0;
	}

	@Override
	public boolean canPlaceItem(int slot, ItemStack stack) {
		if (isCargoSlot(slot)) {
			return this.isCargoSlotEnabled(slot);
		}

		return slotTypeFor(slot).accepts(stack);
	}

	@Override
	public void setChanged() {
		super.setChanged();
		if (this.owner != null) {
			this.owner.onInventoryChanged();
		}
	}

	public GolemStorageMode getStorageMode() {
		ItemStack storageStack = this.getItem(STORAGE_SLOT);
		if (storageStack.isEmpty()) {
			return GolemStorageMode.NONE;
		}

		if (storageStack.is(net.minecraft.world.item.Items.BUNDLE)) {
			return GolemStorageMode.BUNDLE;
		}

		if (storageStack.is(net.minecraft.world.item.Items.CHEST)) {
			return GolemStorageMode.CHEST;
		}

		// TODO Add other storage modules such as barrels or custom upgrades here.
		return GolemStorageMode.NONE;
	}

	public int getVisibleCargoRows() {
		return this.getStorageMode().visibleRows();
	}

	public int getVisibleCargoSlotCount() {
		return this.getStorageMode().visibleSlotCount();
	}

	public boolean isCargoSlotEnabled(int slot) {
		if (!isCargoSlot(slot)) {
			return false;
		}

		return cargoOffsetFor(slot) < this.getVisibleCargoSlotCount();
	}

	public static boolean isCargoSlot(int slot) {
		return slot >= EQUIPMENT_SLOT_COUNT && slot < TOTAL_SLOT_COUNT;
	}

	public static int cargoSlotIndex(int cargoOffset) {
		return EQUIPMENT_SLOT_COUNT + cargoOffset;
	}

	public static int cargoOffsetFor(int slot) {
		return slot - EQUIPMENT_SLOT_COUNT;
	}

	public static GolemSlotTypes slotTypeFor(int slot) {
		return switch (slot) {
			case LEFT_TOOL_SLOT -> GolemSlotTypes.LEFT_TOOL;
			case RIGHT_TOOL_SLOT -> GolemSlotTypes.RIGHT_TOOL;
			case STORAGE_SLOT -> GolemSlotTypes.STORAGE;
			case CORE_SLOT -> GolemSlotTypes.CORE;
			case UPGRADE_SLOT_1 -> GolemSlotTypes.UPGRADE_1;
			case UPGRADE_SLOT_2 -> GolemSlotTypes.UPGRADE_2;
			case UPGRADE_SLOT_3 -> GolemSlotTypes.UPGRADE_3;
			case BAND_SLOT -> GolemSlotTypes.BAND;
			default -> GolemSlotTypes.CARGO;
		};
	}
}
