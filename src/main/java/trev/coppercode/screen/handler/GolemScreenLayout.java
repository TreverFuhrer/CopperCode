package trev.coppercode.screen.handler;

/** Shared slot positions and sizing assumptions for the programmable golem menu and screen. */
public final class GolemScreenLayout {
	public static final int IMAGE_WIDTH = 248;
	public static final int IMAGE_HEIGHT = 222;

	public static final int EQUIPMENT_PANEL_X = 8;
	public static final int EQUIPMENT_PANEL_Y = 18;
	public static final int CARGO_PANEL_X = 80;
	public static final int CARGO_PANEL_Y = 18;
	public static final int STATUS_PANEL_X = 188;
	public static final int STATUS_PANEL_Y = 18;

	public static final int SLOT_SIZE = 18;
	public static final int LEFT_TOOL_X = 18;
	public static final int LEFT_TOOL_Y = 36;
	public static final int RIGHT_TOOL_X = 40;
	public static final int RIGHT_TOOL_Y = 36;
	public static final int STORAGE_X = 18;
	public static final int STORAGE_Y = 58;
	public static final int CORE_X = 40;
	public static final int CORE_Y = 58;
	public static final int UPGRADE_X = 18;
	public static final int UPGRADE_ROW_1_Y = 92;
	public static final int UPGRADE_ROW_2_Y = 114;
	public static final int UPGRADE_ROW_3_Y = 136;
	public static final int BAND_X = 40;
	public static final int BAND_Y = 92;

	public static final int CARGO_START_X = 80;
	public static final int CARGO_START_Y = 36;
	public static final int CARGO_COLUMNS = 9;
	public static final int CARGO_ROWS = 3;

	public static final int PLAYER_INVENTORY_X = 44;
	public static final int PLAYER_INVENTORY_Y = 140;
	public static final int HOTBAR_Y = 198;

	private GolemScreenLayout() {
	}

	public static int cargoSlotX(int column) {
		return CARGO_START_X + column * SLOT_SIZE;
	}

	public static int cargoSlotY(int row) {
		return CARGO_START_Y + row * SLOT_SIZE;
	}
}
