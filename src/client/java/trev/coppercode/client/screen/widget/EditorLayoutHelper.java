package trev.coppercode.client.screen.widget;

/** Shared panel sizing helpers for the future Cogscript editor screens. */
public final class EditorLayoutHelper {
	private static final int ROUTINE_LIST_WIDTH = 78;
	private static final int KEYWORD_SIDEBAR_WIDTH = 86;
	private static final int PANEL_GAP = 8;

	private EditorLayoutHelper() {
	}

	public static EditorLayout editorLayoutFor(int x, int y, int width, int height) {
		int editorWidth = width - ROUTINE_LIST_WIDTH - KEYWORD_SIDEBAR_WIDTH - PANEL_GAP * 2;
		return new EditorLayout(
			new PanelBounds(x, y, ROUTINE_LIST_WIDTH, height),
			new PanelBounds(x + ROUTINE_LIST_WIDTH + PANEL_GAP, y, editorWidth, height),
			new PanelBounds(x + ROUTINE_LIST_WIDTH + PANEL_GAP + editorWidth + PANEL_GAP, y, KEYWORD_SIDEBAR_WIDTH, height)
		);
	}

	public record EditorLayout(PanelBounds routineList, PanelBounds editor, PanelBounds keywordSidebar) {
	}

	public record PanelBounds(int x, int y, int width, int height) {
	}
}
