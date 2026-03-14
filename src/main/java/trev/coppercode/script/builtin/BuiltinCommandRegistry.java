package trev.coppercode.script.builtin;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

/** Registry shell for mapping command names to Java implementations. */
public final class BuiltinCommandRegistry {
	private final Map<String, BuiltinCommand> commands = new LinkedHashMap<>();

	public void register(BuiltinCommand command) {
		this.commands.put(command.name(), command);
	}

	public Optional<BuiltinCommand> find(String name) {
		return Optional.ofNullable(this.commands.get(name));
	}

	public Collection<BuiltinCommand> commands() {
		return this.commands.values();
	}
}
