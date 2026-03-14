package trev.coppercode.script.data;

/** Main stored representation for an editable Cogscript routine. */
public record RoutineData(String routineId, String displayName, String source, ScriptMetadata metadata) {
	public RoutineData withSource(String newSource) {
		return new RoutineData(this.routineId, this.displayName, newSource, this.metadata);
	}

	public RoutineData withDisplayName(String newDisplayName) {
		return new RoutineData(this.routineId, newDisplayName, this.source, this.metadata);
	}
}
