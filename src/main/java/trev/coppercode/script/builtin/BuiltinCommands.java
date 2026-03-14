package trev.coppercode.script.builtin;

/** Convenience entrypoint for future default builtin command registration. */
public final class BuiltinCommands {
	private BuiltinCommands() {
	}

	public static BuiltinCommandRegistry createDefaultRegistry() {
		BuiltinCommandRegistry registry = new BuiltinCommandRegistry();
		// TODO Register the real builtin movement, inventory, wait, and logic commands here.
		return registry;
	}
}
