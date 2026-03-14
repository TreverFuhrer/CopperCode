package trev.coppercode.client.screen;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import trev.coppercode.client.screen.widget.CodeEditorWidget;
import trev.coppercode.client.screen.widget.EditorLayoutHelper;
import trev.coppercode.client.screen.widget.KeywordSidebarWidget;
import trev.coppercode.client.screen.widget.RoutineListWidget;
import trev.coppercode.script.data.RoutineData;
import trev.coppercode.script.data.ScriptMetadata;
import trev.coppercode.screen.CodingDeskScreenHandler;

/** Foundation shell for the future coding desk IDE screen. */
@Environment(EnvType.CLIENT)
public final class CodingDeskScreen extends AbstractContainerScreen<CodingDeskScreenHandler> {
	private final RoutineListWidget routineListWidget = new RoutineListWidget();
	private final KeywordSidebarWidget keywordSidebarWidget = KeywordSidebarWidget.defaultSidebar();
	private final CodeEditorWidget codeEditorWidget = new CodeEditorWidget();

	public CodingDeskScreen(CodingDeskScreenHandler menu, Inventory playerInventory, Component title) {
		super(menu, playerInventory, title);
		this.imageWidth = 268;
		this.imageHeight = 216;
		this.inventoryLabelY = this.imageHeight - 94;
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
		super.render(guiGraphics, mouseX, mouseY, partialTick);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
		EditorLayoutHelper.EditorLayout layout = EditorLayoutHelper.editorLayoutFor(this.leftPos + 8, this.topPos + 18, this.imageWidth - 16, 114);
		guiGraphics.fill(this.leftPos, this.topPos, this.leftPos + this.imageWidth, this.topPos + this.imageHeight, 0xFF1D2126);
		this.routineListWidget.render(guiGraphics, this.font, layout.routineList());
		this.codeEditorWidget.render(guiGraphics, this.font, layout.editor());
		this.keywordSidebarWidget.render(guiGraphics, this.font, layout.keywordSidebar());
		guiGraphics.fill(
			this.leftPos + 8,
			this.topPos + 136,
			this.leftPos + this.imageWidth - 8,
			this.topPos + this.imageHeight - 8,
			0xFF2B3138
		);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, this.title, 8, 6, 0xE0E0E0, false);
		guiGraphics.drawString(this.font, this.playerInventoryTitle, 8, this.inventoryLabelY, 0xBFC7D5, false);
	}

	@Override
	protected void init() {
		super.init();
		this.routineListWidget.setRoutines(
			java.util.List.of(
				new RoutineData("example", "Example Routine", "# TODO write Cogscript here", ScriptMetadata.empty())
			)
		);
		this.codeEditorWidget.setText("# TODO: coding desk editor scaffold\n");
	}
}
