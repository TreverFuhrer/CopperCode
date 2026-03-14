package trev.coppercode.client.screen;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import trev.coppercode.client.screen.widget.CodeEditorWidget;
import trev.coppercode.client.screen.widget.EditorLayoutHelper;
import trev.coppercode.client.screen.widget.KeywordSidebarWidget;
import trev.coppercode.client.screen.widget.RoutineListWidget;

/** Standalone shell for future code book editing. */
@Environment(EnvType.CLIENT)
public final class CodeBookScreen extends Screen {
	private final RoutineListWidget routineListWidget = new RoutineListWidget();
	private final KeywordSidebarWidget keywordSidebarWidget = KeywordSidebarWidget.defaultSidebar();
	private final CodeEditorWidget codeEditorWidget = new CodeEditorWidget();

	public CodeBookScreen() {
		super(Component.translatable("screen.coppercode.code_book"));
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
		super.renderBackground(guiGraphics, mouseX, mouseY, partialTick);
		EditorLayoutHelper.EditorLayout layout = EditorLayoutHelper.editorLayoutFor(16, 24, this.width - 32, this.height - 48);
		this.routineListWidget.render(guiGraphics, this.font, layout.routineList());
		this.codeEditorWidget.render(guiGraphics, this.font, layout.editor());
		this.keywordSidebarWidget.render(guiGraphics, this.font, layout.keywordSidebar());
		guiGraphics.drawString(this.font, this.title, 16, 8, 0xE0E0E0, false);
		super.render(guiGraphics, mouseX, mouseY, partialTick);
	}
}
