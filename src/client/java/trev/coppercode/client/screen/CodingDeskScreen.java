package trev.coppercode.client.screen;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import trev.coppercode.screen.CodingDeskScreenHandler;

/** Simple one-row desk screen backed by the vanilla generic container texture. */
@Environment(EnvType.CLIENT)
public final class CodingDeskScreen extends AbstractContainerScreen<CodingDeskScreenHandler> {
	private static final Identifier BACKGROUND = Identifier.withDefaultNamespace("textures/gui/container/generic_54.png");

	public CodingDeskScreen(CodingDeskScreenHandler menu, Inventory playerInventory, Component title) {
		super(menu, playerInventory, title);
		this.imageHeight = 133;
		this.inventoryLabelY = this.imageHeight - 94;
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
		super.render(guiGraphics, mouseX, mouseY, partialTick);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
		int left = (this.width - this.imageWidth) / 2;
		int top = (this.height - this.imageHeight) / 2;
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, BACKGROUND, left, top, 0.0F, 0.0F, this.imageWidth, 35, 256, 256);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, BACKGROUND, left, top + 35, 0.0F, 126.0F, this.imageWidth, 98, 256, 256);
	}
}
