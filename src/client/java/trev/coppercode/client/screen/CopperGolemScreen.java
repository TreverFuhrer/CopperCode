package trev.coppercode.client.screen;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import trev.coppercode.client.screen.widget.GolemStatusPanelWidget;
import trev.coppercode.client.screen.widget.SlotOverlayHelper;
import trev.coppercode.golem.inventory.GolemInventory;
import trev.coppercode.screen.handler.CopperGolemScreenHandler;
import trev.coppercode.screen.handler.GolemScreenLayout;

/** Configuration/equipment screen scaffold for the programmable copper golem. */
@Environment(EnvType.CLIENT)
public final class CopperGolemScreen extends AbstractContainerScreen<CopperGolemScreenHandler> {
	private final GolemStatusPanelWidget statusPanelWidget = new GolemStatusPanelWidget();

	public CopperGolemScreen(CopperGolemScreenHandler menu, Inventory playerInventory, Component title) {
		super(menu, playerInventory, title);
		this.imageWidth = GolemScreenLayout.IMAGE_WIDTH;
		this.imageHeight = GolemScreenLayout.IMAGE_HEIGHT;
		this.inventoryLabelY = GolemScreenLayout.PLAYER_INVENTORY_Y - 11;
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
		super.render(guiGraphics, mouseX, mouseY, partialTick);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
		guiGraphics.fill(this.leftPos, this.topPos, this.leftPos + this.imageWidth, this.topPos + this.imageHeight, 0xFF20242A);
		guiGraphics.fill(
			this.leftPos + GolemScreenLayout.EQUIPMENT_PANEL_X,
			this.topPos + GolemScreenLayout.EQUIPMENT_PANEL_Y,
			this.leftPos + GolemScreenLayout.EQUIPMENT_PANEL_X + 62,
			this.topPos + GolemScreenLayout.EQUIPMENT_PANEL_Y + 132,
			0xFF2B3138
		);
		guiGraphics.fill(
			this.leftPos + GolemScreenLayout.CARGO_PANEL_X,
			this.topPos + GolemScreenLayout.CARGO_PANEL_Y,
			this.leftPos + GolemScreenLayout.CARGO_PANEL_X + 98,
			this.topPos + GolemScreenLayout.CARGO_PANEL_Y + 62,
			0xFF2B3138
		);
		guiGraphics.fill(
			this.leftPos + GolemScreenLayout.STATUS_PANEL_X,
			this.topPos + GolemScreenLayout.STATUS_PANEL_Y,
			this.leftPos + GolemScreenLayout.STATUS_PANEL_X + 52,
			this.topPos + GolemScreenLayout.STATUS_PANEL_Y + 86,
			0xFF2B3138
		);
		guiGraphics.fill(
			this.leftPos + GolemScreenLayout.PLAYER_INVENTORY_X - 8,
			this.topPos + GolemScreenLayout.PLAYER_INVENTORY_Y - 12,
			this.leftPos + GolemScreenLayout.PLAYER_INVENTORY_X + 176,
			this.topPos + GolemScreenLayout.HOTBAR_Y + 18,
			0xFF2B3138
		);

		this.renderSlotOutlines(guiGraphics);
		this.statusPanelWidget.render(
			guiGraphics,
			this.font,
			this.menu,
			this.leftPos + GolemScreenLayout.STATUS_PANEL_X + 6,
			this.topPos + GolemScreenLayout.STATUS_PANEL_Y + 16,
			40
		);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		Font font = this.font;
		guiGraphics.drawString(font, this.title, 8, 6, 0xE0E0E0, false);
		guiGraphics.drawString(font, Component.translatable("gui.coppercode.golem.equipment"), GolemScreenLayout.EQUIPMENT_PANEL_X, 22, 0xBFC7D5, false);
		guiGraphics.drawString(font, Component.translatable("gui.coppercode.golem.cargo"), GolemScreenLayout.CARGO_PANEL_X, 22, 0xBFC7D5, false);
		guiGraphics.drawString(font, Component.translatable("gui.coppercode.golem.status"), GolemScreenLayout.STATUS_PANEL_X, 22, 0xBFC7D5, false);
		guiGraphics.drawString(this.font, this.playerInventoryTitle, GolemScreenLayout.PLAYER_INVENTORY_X, this.inventoryLabelY, 0xBFC7D5, false);
	}

	private void renderSlotOutlines(GuiGraphics guiGraphics) {
		SlotOverlayHelper.renderSlotOutline(guiGraphics, this.leftPos + GolemScreenLayout.LEFT_TOOL_X, this.topPos + GolemScreenLayout.LEFT_TOOL_Y, true);
		SlotOverlayHelper.renderSlotOutline(guiGraphics, this.leftPos + GolemScreenLayout.RIGHT_TOOL_X, this.topPos + GolemScreenLayout.RIGHT_TOOL_Y, true);
		SlotOverlayHelper.renderSlotOutline(guiGraphics, this.leftPos + GolemScreenLayout.STORAGE_X, this.topPos + GolemScreenLayout.STORAGE_Y, true);
		SlotOverlayHelper.renderSlotOutline(guiGraphics, this.leftPos + GolemScreenLayout.CORE_X, this.topPos + GolemScreenLayout.CORE_Y, true);
		SlotOverlayHelper.renderSlotOutline(guiGraphics, this.leftPos + GolemScreenLayout.UPGRADE_X, this.topPos + GolemScreenLayout.UPGRADE_ROW_1_Y, true);
		SlotOverlayHelper.renderSlotOutline(guiGraphics, this.leftPos + GolemScreenLayout.UPGRADE_X, this.topPos + GolemScreenLayout.UPGRADE_ROW_2_Y, true);
		SlotOverlayHelper.renderSlotOutline(guiGraphics, this.leftPos + GolemScreenLayout.UPGRADE_X, this.topPos + GolemScreenLayout.UPGRADE_ROW_3_Y, true);
		SlotOverlayHelper.renderSlotOutline(guiGraphics, this.leftPos + GolemScreenLayout.BAND_X, this.topPos + GolemScreenLayout.BAND_Y, true);

		for (int cargoIndex = 0; cargoIndex < GolemInventory.CARGO_SLOT_COUNT; cargoIndex++) {
			int row = cargoIndex / GolemScreenLayout.CARGO_COLUMNS;
			int column = cargoIndex % GolemScreenLayout.CARGO_COLUMNS;
			boolean active = cargoIndex < this.menu.getVisibleCargoSlotCount();
			SlotOverlayHelper.renderSlotOutline(
				guiGraphics,
				this.leftPos + GolemScreenLayout.cargoSlotX(column),
				this.topPos + GolemScreenLayout.cargoSlotY(row),
				active
			);
		}
	}
}
