package trev.coppercode.script.data;

/** Stable pointer to a routine stored in a code book or future external project source. */
public record RoutineReference(String containerId, String routineId) {
	public static RoutineReference unbound() {
		return new RoutineReference("", "");
	}

	public boolean isBound() {
		return !this.containerId.isBlank() && !this.routineId.isBlank();
	}
}
