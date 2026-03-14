package trev.coppercode.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import trev.coppercode.registry.ModBlockEntities;
import trev.coppercode.screen.CodingDeskScreenHandler;

/** Simple persistent inventory for the coding desk block. */
public final class CodingDeskBlockEntity extends BaseContainerBlockEntity {
	public static final int SLOT_COUNT = 9;

	private static final Component DEFAULT_NAME = Component.translatable("block.coppercode.coding_desk");

	private NonNullList<ItemStack> items = NonNullList.withSize(SLOT_COUNT, ItemStack.EMPTY);

	public CodingDeskBlockEntity(BlockPos blockPos, BlockState blockState) {
		super(ModBlockEntities.CODING_DESK, blockPos, blockState);
	}

	@Override
	protected void loadAdditional(ValueInput valueInput) {
		super.loadAdditional(valueInput);
		this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
		ContainerHelper.loadAllItems(valueInput, this.items);
	}

	@Override
	protected void saveAdditional(ValueOutput valueOutput) {
		super.saveAdditional(valueOutput);
		ContainerHelper.saveAllItems(valueOutput, this.items);
	}

	@Override
	public int getContainerSize() {
		return SLOT_COUNT;
	}

	@Override
	protected NonNullList<ItemStack> getItems() {
		return this.items;
	}

	@Override
	protected void setItems(NonNullList<ItemStack> items) {
		this.items = items;
	}

	@Override
	protected Component getDefaultName() {
		return DEFAULT_NAME;
	}

	@Override
	protected AbstractContainerMenu createMenu(int syncId, Inventory inventory) {
		return new CodingDeskScreenHandler(syncId, inventory, this);
	}
}
