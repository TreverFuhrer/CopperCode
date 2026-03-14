package trev.coppercode.golem.inventory;

/** Describes how many cargo rows the golem should expose for its installed storage module. */
public enum GolemStorageMode {
	NONE(0, 0),
	BUNDLE(1, 9),
	CHEST(3, 27);

	private final int visibleRows;
	private final int visibleSlotCount;

	GolemStorageMode(int visibleRows, int visibleSlotCount) {
		this.visibleRows = visibleRows;
		this.visibleSlotCount = visibleSlotCount;
	}

	public int visibleRows() {
		return this.visibleRows;
	}

	public int visibleSlotCount() {
		return this.visibleSlotCount;
	}
}
