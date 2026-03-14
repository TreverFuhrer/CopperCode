package trev.coppercode.script.parser;

import java.util.List;
import java.util.Optional;
import trev.coppercode.script.ast.RoutineNode;

/** Parse result wrapper that can carry a partial root plus recoverable diagnostics. */
public record ParserResult(Optional<RoutineNode> root, List<ParseException> errors) {
	public static ParserResult unsupported(String message) {
		return new ParserResult(Optional.empty(), List.of(new ParseException(message)));
	}
}
