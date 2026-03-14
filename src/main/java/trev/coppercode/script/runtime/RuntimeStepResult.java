package trev.coppercode.script.runtime;

import org.jspecify.annotations.Nullable;

/** Result of a single future interpreter tick/step. */
public record RuntimeStepResult(ExecutionState nextState, boolean consumedGameTick, @Nullable RuntimeExceptionData failure) {
	public static RuntimeStepResult idle() {
		return new RuntimeStepResult(ExecutionState.IDLE, false, null);
	}
}
