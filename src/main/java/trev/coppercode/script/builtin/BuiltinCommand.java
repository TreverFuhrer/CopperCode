package trev.coppercode.script.builtin;

import java.util.List;
import trev.coppercode.script.runtime.ExecutionContext;
import trev.coppercode.script.runtime.RuntimeStepResult;

/** Runtime contract for a single builtin Cogscript command. */
public interface BuiltinCommand {
	String name();

	RuntimeStepResult execute(ExecutionContext context, List<Object> arguments);
}
