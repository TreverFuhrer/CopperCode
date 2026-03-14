package trev.coppercode.inventory;

import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import trev.coppercode.entity.ProgrammableCopperGolemEntity;

/** Lightweight container wrapper for the programmable golem's equipment and storage. */
public final class GolemInventory extends SimpleContainer {
	private final ProgrammableCopperGolemEntity owner;

	public GolemInventory(ProgrammableCopperGolemEntity owner) {
		super(GolemSlotTypes.values().length);
		this.owner = owner;
	}

	@Override
	public boolean stillValid(Player player) {
		return this.owner.isAlive() && this.owner.distanceToSqr(player) <= 64.0;
	}

	@Override
	public boolean canPlaceItem(int slot, ItemStack stack) {
		return GolemSlotTypes.byIndex(slot).accepts(stack);
	}

	@Override
	public void setChanged() {
		super.setChanged();
		this.owner.onInventoryChanged();
	}

	public ItemStack getItem(GolemSlotTypes slotType) {
		return super.getItem(slotType.slotIndex());
	}

	public void setItem(GolemSlotTypes slotType, ItemStack stack) {
		super.setItem(slotType.slotIndex(), stack);
	}
}
