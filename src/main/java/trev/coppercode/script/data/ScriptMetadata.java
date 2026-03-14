package trev.coppercode.script.data;

/** Lightweight metadata attached to a routine or code container. */
public record ScriptMetadata(String authorName, long createdAtEpochMillis, long updatedAtEpochMillis, boolean dirty) {
	public static ScriptMetadata empty() {
		return new ScriptMetadata("", 0L, 0L, false);
	}
}
