package trev.coppercode.script.runtime;

/** High-level runtime lifecycle state for a routine execution session. */
public enum ExecutionState {
	IDLE,
	READY,
	RUNNING,
	WAITING,
	STOPPED,
	ERROR
}
