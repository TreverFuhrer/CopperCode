package trev.coppercode.client.screen.widget;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;

/** Render-only shell for a future multiline Cogscript editor widget. */
public final class CodeEditorWidget {
	private String text = "";

	public void setText(String text) {
		this.text = text;
	}

	public String text() {
		return this.text;
	}

	public void render(GuiGraphics guiGraphics, Font font, EditorLayoutHelper.PanelBounds bounds) {
		guiGraphics.fill(bounds.x(), bounds.y(), bounds.x() + bounds.width(), bounds.y() + bounds.height(), 0xFF2B3138);
		guiGraphics.drawString(font, "Editor", bounds.x() + 6, bounds.y() + 6, 0xFFE0E0E0, false);
		guiGraphics.drawString(font, this.text, bounds.x() + 6, bounds.y() + 22, 0xFFBFC7D5, false);
		// TODO Implement cursor, scrolling, selection, IME-safe text editing, and syntax-highlighted line rendering.
	}
}
