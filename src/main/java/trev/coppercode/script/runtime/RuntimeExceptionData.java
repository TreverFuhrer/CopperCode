package trev.coppercode.script.runtime;

/** Data wrapper for runtime failures that should be surfaced to UI and logs. */
public record RuntimeExceptionData(String message, Throwable cause) {
}
