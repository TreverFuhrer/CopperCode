package trev.coppercode.screen;

import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import trev.coppercode.inventory.GolemSlotTypes;
import trev.coppercode.registry.ModScreenHandlers;

/** Custom menu that exposes the programmable golem inventory. */
public final class CopperGolemScreenHandler extends AbstractContainerMenu {
	private final Container container;

	public CopperGolemScreenHandler(int syncId, Inventory playerInventory) {
		this(syncId, playerInventory, new SimpleContainer(GolemSlotTypes.values().length));
	}

	public CopperGolemScreenHandler(int syncId, Inventory playerInventory, Container container) {
		super(ModScreenHandlers.COPPER_GOLEM, syncId);
		checkContainerSize(container, GolemSlotTypes.values().length);
		this.container = container;
		container.startOpen(playerInventory.player);

		for (GolemSlotTypes slotType : GolemSlotTypes.values()) {
			this.addSlot(new GolemSlot(container, slotType));
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
			if (slotIndex < GolemSlotTypes.values().length) {
				if (!this.moveItemStackTo(slotStack, GolemSlotTypes.values().length, this.slots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.moveIntoGolemSlots(slotStack)) {
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

	private boolean moveIntoGolemSlots(ItemStack stack) {
		return this.moveIntoSlot(stack, GolemSlotTypes.CORE)
			|| this.moveIntoSlot(stack, GolemSlotTypes.LEFT_ARM)
			|| this.moveIntoSlot(stack, GolemSlotTypes.RIGHT_ARM)
			|| this.moveIntoSlot(stack, GolemSlotTypes.STORAGE);
	}

	private boolean moveIntoSlot(ItemStack stack, GolemSlotTypes slotType) {
		if (!slotType.accepts(stack)) {
			return false;
		}

		return this.moveItemStackTo(stack, slotType.slotIndex(), slotType.slotIndex() + 1, false);
	}

	private static final class GolemSlot extends Slot {
		private final GolemSlotTypes slotType;

		private GolemSlot(Container container, GolemSlotTypes slotType) {
			super(container, slotType.slotIndex(), slotType.x(), slotType.y());
			this.slotType = slotType;
		}

		@Override
		public boolean mayPlace(ItemStack stack) {
			return this.slotType.accepts(stack);
		}

		@Override
		public int getMaxStackSize() {
			return this.slotType.maxStackSize();
		}

		@Override
		public int getMaxStackSize(ItemStack stack) {
			return Math.min(this.slotType.maxStackSize(), stack.getMaxStackSize());
		}
	}
}
