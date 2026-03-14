package trev.coppercode.client.screen.widget;

import java.util.List;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;

/** Sidebar shell for showing future language keywords, snippets, and docs shortcuts. */
public final class KeywordSidebarWidget {
	private final List<Component> entries;

	public KeywordSidebarWidget(List<Component> entries) {
		this.entries = entries;
	}

	public static KeywordSidebarWidget defaultSidebar() {
		return new KeywordSidebarWidget(
			List.of(
				Component.literal("call"),
				Component.literal("repeat"),
				Component.literal("if"),
				Component.literal("while")
			)
		);
	}

	public void render(GuiGraphics guiGraphics, Font font, EditorLayoutHelper.PanelBounds bounds) {
		guiGraphics.fill(bounds.x(), bounds.y(), bounds.x() + bounds.width(), bounds.y() + bounds.height(), 0xFF2B3138);
		guiGraphics.drawString(font, "Keywords", bounds.x() + 6, bounds.y() + 6, 0xFFE0E0E0, false);
		int lineY = bounds.y() + 22;
		for (Component entry : this.entries) {
			guiGraphics.drawString(font, entry, bounds.x() + 6, lineY, 0xFFBFC7D5, false);
			lineY += 12;
		}

		// TODO Add clickable snippets, command docs, and context-sensitive suggestions.
	}
}
