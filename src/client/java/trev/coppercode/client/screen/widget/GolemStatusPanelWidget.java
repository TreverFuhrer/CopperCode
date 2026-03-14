package trev.coppercode.client.screen.widget;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import trev.coppercode.screen.handler.CopperGolemScreenHandler;

/** Small render helper for future synced golem status details. */
public final class GolemStatusPanelWidget {
	public void render(GuiGraphics guiGraphics, Font font, CopperGolemScreenHandler handler, int x, int y, int width) {
		guiGraphics.drawString(font, "Routine", x, y, 0xFFE0E0E0, false);
		guiGraphics.drawWordWrap(font, net.minecraft.network.chat.Component.literal(handler.getRoutineNamePlaceholder()), x, y + 12, width, 0xFFBFC7D5);
		guiGraphics.drawString(font, "State", x, y + 38, 0xFFE0E0E0, false);
		guiGraphics.drawString(font, handler.getExecutionStatePlaceholder(), x, y + 50, 0xFFBFC7D5, false);
		guiGraphics.drawString(font, "Storage", x, y + 66, 0xFFE0E0E0, false);
		guiGraphics.drawString(font, handler.getStorageMode().name(), x, y + 78, 0xFFBFC7D5, false);
	}
}
