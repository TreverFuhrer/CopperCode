package trev.coppercode.inventory;

import java.util.Arrays;
import java.util.function.Predicate;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import trev.coppercode.registry.ModItems;

/** Slot definitions for the programmable golem inventory. */
public enum GolemSlotTypes {
	LEFT_ARM(0, 35, 20, "slot.coppercode.golem.left_arm", 1, stack -> !stack.is(ModItems.COPPER_CORE)),
	CORE(1, 71, 20, "slot.coppercode.golem.core", 1, stack -> stack.is(ModItems.COPPER_CORE)),
	RIGHT_ARM(2, 107, 20, "slot.coppercode.golem.right_arm", 1, stack -> !stack.is(ModItems.COPPER_CORE)),
	STORAGE(3, 143, 20, "slot.coppercode.golem.storage", 64, stack -> true);

	private final int slotIndex;
	private final int x;
	private final int y;
	private final String translationKey;
	private final int maxStackSize;
	private final Predicate<ItemStack> placementRule;

	GolemSlotTypes(int slotIndex, int x, int y, String translationKey, int maxStackSize, Predicate<ItemStack> placementRule) {
		this.slotIndex = slotIndex;
		this.x = x;
		this.y = y;
		this.translationKey = translationKey;
		this.maxStackSize = maxStackSize;
		this.placementRule = placementRule;
	}

	public int slotIndex() {
		return this.slotIndex;
	}

	public int x() {
		return this.x;
	}

	public int y() {
		return this.y;
	}

	public int maxStackSize() {
		return this.maxStackSize;
	}

	public String translationKey() {
		return this.translationKey;
	}

	public boolean accepts(ItemStack stack) {
		return this.placementRule.test(stack);
	}

	public Component displayName() {
		return Component.translatable(this.translationKey);
	}

	public static GolemSlotTypes byIndex(int slotIndex) {
		return Arrays.stream(values())
			.filter(type -> type.slotIndex == slotIndex)
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("Unknown golem slot index: " + slotIndex));
	}
}
