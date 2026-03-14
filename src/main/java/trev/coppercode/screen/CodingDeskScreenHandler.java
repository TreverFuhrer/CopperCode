package trev.coppercode.screen;

import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import trev.coppercode.block.entity.CodingDeskBlockEntity;
import trev.coppercode.registry.ModScreenHandlers;

/** One-row inventory menu used by the coding desk block. */
public final class CodingDeskScreenHandler extends AbstractContainerMenu {
	private final Container container;

	public CodingDeskScreenHandler(int syncId, Inventory playerInventory) {
		this(syncId, playerInventory, new SimpleContainer(CodingDeskBlockEntity.SLOT_COUNT));
	}

	public CodingDeskScreenHandler(int syncId, Inventory playerInventory, Container container) {
		super(ModScreenHandlers.CODING_DESK, syncId);
		checkContainerSize(container, CodingDeskBlockEntity.SLOT_COUNT);
		this.container = container;
		container.startOpen(playerInventory.player);

		for (int slotIndex = 0; slotIndex < CodingDeskBlockEntity.SLOT_COUNT; slotIndex++) {
			this.addSlot(new Slot(container, slotIndex, 8 + slotIndex * 18, 20));
		}

		this.addStandardInventorySlots(playerInventory, 8, 51);
	}

	@Override
	public boolean stillValid(Player player) {
		return this.container.stillValid(player);
	}

	@Override
	public ItemStack quickMoveStack(Player player, int slotIndex) {
		ItemStack quickMoved = ItemStack.EMPTY;
		Slot slot = this.slots.get(slotIndex);
		if (slot != null && slot.hasItem()) {
			ItemStack slotStack = slot.getItem();
			quickMoved = slotStack.copy();
			if (slotIndex < CodingDeskBlockEntity.SLOT_COUNT) {
				if (!this.moveItemStackTo(slotStack, CodingDeskBlockEntity.SLOT_COUNT, this.slots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.moveItemStackTo(slotStack, 0, CodingDeskBlockEntity.SLOT_COUNT, false)) {
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
		this.container.stopOpen(player);
	}
}
