package trev.coppercode.screen.handler;

import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import trev.coppercode.golem.inventory.GolemInventory;
import trev.coppercode.golem.inventory.GolemSlotTypes;
import trev.coppercode.golem.inventory.GolemStorageMode;
import trev.coppercode.registry.ModScreenHandlers;

/**
 * Server/common menu for the programmable copper golem configuration screen.
 *
 * TODO Sync richer golem status here: selected routine, current execution state, installed capability unlocks,
 * lock/unlock state for upgrade slots, and server-authoritative cargo visibility rules.
 */
public final class CopperGolemScreenHandler extends AbstractContainerMenu {
	private final GolemInventory inventory;

	public CopperGolemScreenHandler(int syncId, Inventory playerInventory) {
		this(syncId, playerInventory, null);
	}

	public CopperGolemScreenHandler(int syncId, Inventory playerInventory, GolemInventory inventory) {
		super(ModScreenHandlers.COPPER_GOLEM, syncId);
		this.inventory = inventory == null ? createClientPlaceholderInventory(playerInventory.player) : inventory;
		checkContainerSize(this.inventory, GolemInventory.TOTAL_SLOT_COUNT);
		this.inventory.startOpen(playerInventory.player);

		this.addEquipmentSlots();
		this.addCargoSlots();
		this.addStandardInventorySlots(playerInventory, GolemScreenLayout.PLAYER_INVENTORY_X, GolemScreenLayout.PLAYER_INVENTORY_Y);
	}

	@Override
	public boolean stillValid(Player player) {
		return this.inventory.stillValid(player);
	}

	@Override
	public ItemStack quickMoveStack(Player player, int slotIndex) {
		ItemStack quickMoved = ItemStack.EMPTY;
		Slot slot = this.slots.get(slotIndex);
		if (slot != null && slot.hasItem()) {
			ItemStack slotStack = slot.getItem();
			quickMoved = slotStack.copy();

			if (slotIndex < GolemInventory.TOTAL_SLOT_COUNT) {
				if (!this.moveItemStackTo(slotStack, GolemInventory.TOTAL_SLOT_COUNT, this.slots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.tryMovePlayerStackIntoGolemInventory(slotStack)) {
				return ItemStack.EMPTY;
			}

			if (slotStack.isEmpty()) {
				slot.setByPlayer(ItemStack.EMPTY);
			} else {
				slot.setChanged();
			}
		}

		return quickMoved;
	}

	@Override
	public void removed(Player player) {
		super.removed(player);
		this.inventory.stopOpen(player);
	}

	public GolemInventory getGolemInventory() {
		return this.inventory;
	}

	public GolemStorageMode getStorageMode() {
		return this.inventory.getStorageMode();
	}

	public int getVisibleCargoRows() {
		return this.inventory.getVisibleCargoRows();
	}

	public int getVisibleCargoSlotCount() {
		return this.inventory.getVisibleCargoSlotCount();
	}

	public String getRoutineNamePlaceholder() {
		// TODO Replace with synced routine metadata from the golem entity or attached routine storage.
		return "No routine selected";
	}

	public String getExecutionStatePlaceholder() {
		// TODO Replace with a synced execution state enum once script runtime integration exists.
		return "Idle";
	}

	private void addEquipmentSlots() {
		this.addSlot(new GolemEquipmentSlot(this.inventory, GolemInventory.LEFT_TOOL_SLOT, GolemScreenLayout.LEFT_TOOL_X, GolemScreenLayout.LEFT_TOOL_Y));
		this.addSlot(new GolemEquipmentSlot(this.inventory, GolemInventory.RIGHT_TOOL_SLOT, GolemScreenLayout.RIGHT_TOOL_X, GolemScreenLayout.RIGHT_TOOL_Y));
		this.addSlot(new GolemEquipmentSlot(this.inventory, GolemInventory.STORAGE_SLOT, GolemScreenLayout.STORAGE_X, GolemScreenLayout.STORAGE_Y));
		this.addSlot(new GolemEquipmentSlot(this.inventory, GolemInventory.CORE_SLOT, GolemScreenLayout.CORE_X, GolemScreenLayout.CORE_Y));
		this.addSlot(new GolemEquipmentSlot(this.inventory, GolemInventory.UPGRADE_SLOT_1, GolemScreenLayout.UPGRADE_X, GolemScreenLayout.UPGRADE_ROW_1_Y));
		this.addSlot(new GolemEquipmentSlot(this.inventory, GolemInventory.UPGRADE_SLOT_2, GolemScreenLayout.UPGRADE_X, GolemScreenLayout.UPGRADE_ROW_2_Y));
		this.addSlot(new GolemEquipmentSlot(this.inventory, GolemInventory.UPGRADE_SLOT_3, GolemScreenLayout.UPGRADE_X, GolemScreenLayout.UPGRADE_ROW_3_Y));
		this.addSlot(new GolemEquipmentSlot(this.inventory, GolemInventory.BAND_SLOT, GolemScreenLayout.BAND_X, GolemScreenLayout.BAND_Y));
	}

	private void addCargoSlots() {
		for (int row = 0; row < GolemScreenLayout.CARGO_ROWS; row++) {
			for (int column = 0; column < GolemScreenLayout.CARGO_COLUMNS; column++) {
				int cargoOffset = row * GolemScreenLayout.CARGO_COLUMNS + column;
				int slotIndex = GolemInventory.cargoSlotIndex(cargoOffset);
				this.addSlot(
					new GolemCargoSlot(
						this.inventory,
						slotIndex,
						GolemScreenLayout.cargoSlotX(column),
						GolemScreenLayout.cargoSlotY(row)
					)
				);
			}
		}
	}

	private boolean tryMovePlayerStackIntoGolemInventory(ItemStack stack) {
		return this.tryMoveIntoSlot(stack, GolemInventory.CORE_SLOT)
			|| this.tryMoveIntoSlot(stack, GolemInventory.STORAGE_SLOT)
			|| this.tryMoveIntoSlot(stack, GolemInventory.LEFT_TOOL_SLOT)
			|| this.tryMoveIntoSlot(stack, GolemInventory.RIGHT_TOOL_SLOT)
			|| this.tryMoveIntoSlot(stack, GolemInventory.UPGRADE_SLOT_1)
			|| this.tryMoveIntoSlot(stack, GolemInventory.UPGRADE_SLOT_2)
			|| this.tryMoveIntoSlot(stack, GolemInventory.UPGRADE_SLOT_3)
			|| this.tryMoveIntoSlot(stack, GolemInventory.BAND_SLOT)
			|| this.tryMoveIntoCargo(stack);
	}

	private boolean tryMoveIntoSlot(ItemStack stack, int slotIndex) {
		if (!this.inventory.canPlaceItem(slotIndex, stack)) {
			return false;
		}

		return this.moveItemStackTo(stack, slotIndex, slotIndex + 1, false);
	}

	private boolean tryMoveIntoCargo(ItemStack stack) {
		int firstCargoSlot = GolemInventory.cargoSlotIndex(0);
		int lastVisibleCargoSlot = GolemInventory.cargoSlotIndex(this.getVisibleCargoSlotCount());
		if (lastVisibleCargoSlot <= firstCargoSlot) {
			return false;
		}

		return this.moveItemStackTo(stack, firstCargoSlot, lastVisibleCargoSlot, false);
	}

	private static GolemInventory createClientPlaceholderInventory(Player owner) {
		// TODO Replace this with a client-side mirror of server state if the menu needs entity-specific sync data.
		return new GolemInventory(null);
	}

	private static final class GolemEquipmentSlot extends Slot {
		private GolemEquipmentSlot(Container container, int slotIndex, int x, int y) {
			super(container, slotIndex, x, y);
		}

		@Override
		public boolean mayPlace(ItemStack stack) {
			return this.container.canPlaceItem(this.getContainerSlot(), stack);
		}

		@Override
		public int getMaxStackSize() {
			return GolemInventory.slotTypeFor(this.getContainerSlot()).maxStackSize();
		}
	}

	private static final class GolemCargoSlot extends Slot {
		private GolemCargoSlot(GolemInventory inventory, int slotIndex, int x, int y) {
			super(inventory, slotIndex, x, y);
		}

		@Override
		public boolean mayPlace(ItemStack stack) {
			return this.container.canPlaceItem(this.getContainerSlot(), stack);
		}

		@Override
		public boolean mayPickup(Player player) {
			return this.isActive();
		}

		@Override
		public boolean isActive() {
			return ((GolemInventory)this.container).isCargoSlotEnabled(this.getContainerSlot());
		}
	}
}
