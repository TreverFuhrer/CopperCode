package trev.coppercode.client.screen.widget;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import trev.coppercode.script.data.RoutineData;

/** Sidebar shell for future routine/project navigation. */
public final class RoutineListWidget {
	private final List<RoutineData> routines = new ArrayList<>();
	private int selectedIndex = -1;

	public void setRoutines(List<RoutineData> routines) {
		this.routines.clear();
		this.routines.addAll(routines);
		this.selectedIndex = this.routines.isEmpty() ? -1 : 0;
	}

	public void render(GuiGraphics guiGraphics, Font font, EditorLayoutHelper.PanelBounds bounds) {
		guiGraphics.fill(bounds.x(), bounds.y(), bounds.x() + bounds.width(), bounds.y() + bounds.height(), 0xFF2B3138);
		guiGraphics.drawString(font, "Routines", bounds.x() + 6, bounds.y() + 6, 0xFFE0E0E0, false);
		int lineY = bounds.y() + 22;
		for (int index = 0; index < this.routines.size(); index++) {
			int color = index == this.selectedIndex ? 0xFF7CC7FF : 0xFFBFC7D5;
			guiGraphics.drawString(font, this.routines.get(index).displayName(), bounds.x() + 6, lineY, color, false);
			lineY += 12;
		}

		// TODO Implement scrolling, selection, creation, deletion, and rename workflows.
	}
}
