package trev.coppercode.script.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Editable collection of routines.
 *
 * TODO Back this with item/block/entity data storage and add proper serialization/versioning once the IDE flow is implemented.
 */
public final class CodeBookData {
	private final String containerId;
	private final List<RoutineData> routines = new ArrayList<>();
	private ScriptMetadata metadata;

	public CodeBookData(String containerId, ScriptMetadata metadata) {
		this.containerId = containerId;
		this.metadata = metadata;
	}

	public String containerId() {
		return this.containerId;
	}

	public List<RoutineData> routines() {
		return List.copyOf(this.routines);
	}

	public ScriptMetadata metadata() {
		return this.metadata;
	}

	public void setMetadata(ScriptMetadata metadata) {
		this.metadata = metadata;
	}

	public void addRoutine(RoutineData routine) {
		this.routines.add(routine);
	}

	public Optional<RoutineData> findRoutine(String routineId) {
		return this.routines.stream().filter(routine -> routine.routineId().equals(routineId)).findFirst();
	}
}
