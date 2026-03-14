package trev.coppercode.script.runtime;

/**
 * Tick-driven interpreter shell.
 *
 * TODO Execute validated AST statements incrementally, emit wait states for time-based golem actions, and integrate builtin commands.
 */
public final class CogscriptInterpreter {
	public RuntimeStepResult tick(ExecutionContext context) {
		return RuntimeStepResult.idle();
	}
}
