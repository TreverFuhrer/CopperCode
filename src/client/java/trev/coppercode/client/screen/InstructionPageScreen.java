package trev.coppercode.client.screen;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import trev.coppercode.client.screen.widget.CodeEditorWidget;
import trev.coppercode.client.screen.widget.EditorLayoutHelper;
import trev.coppercode.client.screen.widget.KeywordSidebarWidget;

/** Standalone shell for future instruction page editing. */
@Environment(EnvType.CLIENT)
public final class InstructionPageScreen extends Screen {
	private final KeywordSidebarWidget keywordSidebarWidget = KeywordSidebarWidget.defaultSidebar();
	private final CodeEditorWidget codeEditorWidget = new CodeEditorWidget();

	public InstructionPageScreen() {
		super(Component.translatable("screen.coppercode.instruction_page"));
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
		super.renderBackground(guiGraphics, mouseX, mouseY, partialTick);
		EditorLayoutHelper.EditorLayout layout = EditorLayoutHelper.editorLayoutFor(16, 24, this.width - 32, this.height - 48);
		this.codeEditorWidget.render(guiGraphics, this.font, layout.editor());
		this.keywordSidebarWidget.render(guiGraphics, this.font, layout.keywordSidebar());
		guiGraphics.drawString(this.font, this.title, 16, 8, 0xE0E0E0, false);
		super.render(guiGraphics, mouseX, mouseY, partialTick);
	}
}
