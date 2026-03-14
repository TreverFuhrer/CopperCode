package trev.coppercode.script.runtime;

import java.util.HashMap;
import java.util.Map;
import trev.coppercode.script.data.RoutineData;

/**
 * Mutable runtime context shell.
 *
 * TODO Add bound golem/entity references, tick scheduling, call stack data, local variable scopes, and wait/action continuations.
 */
public final class ExecutionContext {
	private final RoutineData routine;
	private final Map<String, Object> variables = new HashMap<>();
	private int instructionPointer;

	public ExecutionContext(RoutineData routine) {
		this.routine = routine;
	}

	public RoutineData routine() {
		return this.routine;
	}

	public Map<String, Object> variables() {
		return this.variables;
	}

	public int instructionPointer() {
		return this.instructionPointer;
	}

	public void advance() {
		this.instructionPointer++;
	}
}
