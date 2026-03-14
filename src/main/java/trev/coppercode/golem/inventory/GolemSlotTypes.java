package trev.coppercode.golem.inventory;

import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import trev.coppercode.registry.ModItems;

/** Enumerates the programmable golem's equipment and cargo slot categories. */
public enum GolemSlotTypes {
	LEFT_TOOL("slot.coppercode.golem.left_tool", 1),
	RIGHT_TOOL("slot.coppercode.golem.right_tool", 1),
	STORAGE("slot.coppercode.golem.storage", 1),
	CORE("slot.coppercode.golem.core", 1),
	UPGRADE_1("slot.coppercode.golem.upgrade_1", 1),
	UPGRADE_2("slot.coppercode.golem.upgrade_2", 1),
	UPGRADE_3("slot.coppercode.golem.upgrade_3", 1),
	BAND("slot.coppercode.golem.band", 1),
	CARGO("slot.coppercode.golem.cargo", 64);

	private final String translationKey;
	private final int maxStackSize;

	GolemSlotTypes(String translationKey, int maxStackSize) {
		this.translationKey = translationKey;
		this.maxStackSize = maxStackSize;
	}

	public String translationKey() {
		return this.translationKey;
	}

	public int maxStackSize() {
		return this.maxStackSize;
	}

	public boolean accepts(ItemStack stack) {
		return switch (this) {
			case LEFT_TOOL, RIGHT_TOOL -> isToolCandidate(stack);
			case STORAGE -> stack.is(Items.CHEST) || stack.is(Items.BUNDLE);
			case CORE -> stack.is(ModItems.COPPER_CORE);
			case UPGRADE_1, UPGRADE_2, UPGRADE_3 -> isUpgradeCandidate(stack);
			case BAND -> stack.is(ItemTags.WOOL);
			case CARGO -> true;
		};
	}

	private static boolean isToolCandidate(ItemStack stack) {
		if (stack.isEmpty()) {
			return false;
		}

		if (stack.is(ModItems.COPPER_CORE) || stack.is(Items.CHEST) || stack.is(Items.BUNDLE) || stack.is(ItemTags.WOOL)) {
			return false;
		}

		// TODO Narrow this down once the golem's actual hand/tool capability system exists.
		return true;
	}

	private static boolean isUpgradeCandidate(ItemStack stack) {
		// TODO Replace this broad placeholder rule with explicit module items.
		return !stack.isEmpty() && !stack.is(Items.CHEST) && !stack.is(Items.BUNDLE) && !stack.is(ItemTags.WOOL);
	}
}
