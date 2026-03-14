package trev.coppercode.client.screen.widget;

import net.minecraft.client.gui.GuiGraphics;

/** Small render helper for drawing placeholder slot frames and disabled cargo regions. */
public final class SlotOverlayHelper {
	private SlotOverlayHelper() {
	}

	public static void renderSlotOutline(GuiGraphics guiGraphics, int x, int y, boolean active) {
		int color = active ? 0xFF5C6672 : 0xFF3A4047;
		guiGraphics.fill(x, y, x + 18, y + 18, 0xFF16191E);
		guiGraphics.renderOutline(x, y, 18, 18, color);
	}
}
