package trev.coppercode.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.animal.golem.CopperGolem;
import net.minecraft.world.entity.animal.golem.CopperGolemState;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;
import trev.coppercode.inventory.GolemInventory;
import trev.coppercode.screen.CopperGolemScreenHandler;

/** A programmable variant that reuses vanilla copper golem behavior and rendering. */
public final class ProgrammableCopperGolemEntity extends CopperGolem implements MenuProvider {
	private static final String INVENTORY_TAG = "Inventory";
	private static final String ROUTINE_SLOT_TAG = "RoutineSlot";
	private static final String EXECUTING_ROUTINE_TAG = "ExecutingRoutine";

	private final GolemInventory inventory = new GolemInventory(this);
	private ItemStack routineSlot = ItemStack.EMPTY;
	private boolean executingRoutine;
	private boolean lockedYawInitialized;
	private float lockedYaw;

	public ProgrammableCopperGolemEntity(EntityType<? extends CopperGolem> entityType, Level level) {
		super(entityType, level);
	}

	public static AttributeSupplier.Builder createAttributes() {
		return CopperGolem.createAttributes();
	}

	public GolemInventory getGolemInventory() {
		return this.inventory;
	}

	public ItemStack getRoutineSlot() {
		return this.routineSlot;
	}

	public void setRoutineSlot(ItemStack routineSlot) {
		this.routineSlot = routineSlot.copy();
	}

	public boolean isExecutingRoutine() {
		return this.executingRoutine;
	}

	public void setExecutingRoutine(boolean executingRoutine) {
		this.executingRoutine = executingRoutine;
		if (!executingRoutine) {
			this.lockedYawInitialized = false;
		}
	}

	public void onInventoryChanged() {
	}

	@Override
	protected void customServerAiStep(ServerLevel serverLevel) {
		if (this.shouldRemainStationary()) {
			this.stopMoving();
			return;
		}

		super.customServerAiStep(serverLevel);
	}

	@Override
	public void tick() {
		super.tick();
		if (this.shouldRemainStationary()) {
			this.stopMoving();
			this.lockFacing();
		} else {
			this.lockedYawInitialized = false;
		}
	}

	@Override
	public void addAdditionalSaveData(ValueOutput valueOutput) {
		super.addAdditionalSaveData(valueOutput);
		this.inventory.storeAsItemList(valueOutput.list(INVENTORY_TAG, ItemStack.CODEC));
		if (!this.routineSlot.isEmpty()) {
			valueOutput.store(ROUTINE_SLOT_TAG, ItemStack.CODEC, this.routineSlot);
		}

		valueOutput.putBoolean(EXECUTING_ROUTINE_TAG, this.executingRoutine);
	}

	@Override
	public void readAdditionalSaveData(ValueInput valueInput) {
		super.readAdditionalSaveData(valueInput);
		valueInput.list(INVENTORY_TAG, ItemStack.CODEC).ifPresent(this.inventory::fromItemList);
		this.routineSlot = (ItemStack)valueInput.read(ROUTINE_SLOT_TAG, ItemStack.CODEC).orElse(ItemStack.EMPTY);
		this.executingRoutine = valueInput.getBooleanOr(EXECUTING_ROUTINE_TAG, false);
	}

	@Override
	protected void dropCustomDeathLoot(ServerLevel serverLevel, DamageSource damageSource, boolean causedByPlayer) {
		super.dropCustomDeathLoot(serverLevel, damageSource, causedByPlayer);
		this.inventory.removeAllItems().forEach(stack -> this.spawnAtLocation(serverLevel, stack));
		if (!this.routineSlot.isEmpty()) {
			this.spawnAtLocation(serverLevel, this.routineSlot.copy());
			this.routineSlot = ItemStack.EMPTY;
		}
	}

	@Override
	public InteractionResult mobInteract(Player player, InteractionHand interactionHand) {
		if (player.isSecondaryUseActive()) {
			if (!this.level().isClientSide()) {
				player.openMenu(this);
			}

			return InteractionResult.SUCCESS;
		}

		return super.mobInteract(player, interactionHand);
	}

	@Override
	public Component getDisplayName() {
		return this.getName();
	}

	@Nullable
	@Override
	public AbstractContainerMenu createMenu(int syncId, Inventory inventory, Player player) {
		return new CopperGolemScreenHandler(syncId, inventory, this.inventory);
	}

	private boolean shouldRemainStationary() {
		return !this.executingRoutine;
	}

	private void stopMoving() {
		this.getNavigation().stop();
		this.clearOpenedChestPos();
		this.setState(CopperGolemState.IDLE);
		this.setDeltaMovement(Vec3.ZERO);
		this.setSpeed(0.0F);
		this.zza = 0.0F;
		this.xxa = 0.0F;
	}

	private void lockFacing() {
		if (!this.lockedYawInitialized) {
			this.lockedYaw = this.getYRot();
			this.lockedYawInitialized = true;
		}

		this.setYRot(this.lockedYaw);
		this.setYHeadRot(this.lockedYaw);
		this.setYBodyRot(this.lockedYaw);
		this.yRotO = this.lockedYaw;
		this.yHeadRotO = this.lockedYaw;
		this.yBodyRotO = this.lockedYaw;
	}
}
