package trev.coppercode.script;

import java.util.List;
import trev.coppercode.script.data.RoutineData;
import trev.coppercode.script.lexer.CogscriptLexer;
import trev.coppercode.script.lexer.LexException;
import trev.coppercode.script.lexer.Token;
import trev.coppercode.script.parser.CogscriptParser;
import trev.coppercode.script.parser.ParserResult;
import trev.coppercode.script.runtime.CogscriptInterpreter;
import trev.coppercode.script.validate.CogscriptValidator;
import trev.coppercode.script.validate.ValidationResult;

/**
 * Small composition root for the Cogscript toolchain.
 *
 * TODO Own project/routine loading, save hooks, parse caching, validation orchestration, and future server-side runtime bootstrapping.
 */
public final class CogscriptManager {
	private final CogscriptLexer lexer = new CogscriptLexer();
	private final CogscriptParser parser = new CogscriptParser();
	private final CogscriptValidator validator = new CogscriptValidator();
	private final CogscriptInterpreter interpreter = new CogscriptInterpreter();

	public List<Token> lex(RoutineData routine) throws LexException {
		return this.lexer.tokenize(routine.source());
	}

	public ParserResult parse(RoutineData routine) throws LexException {
		return this.parser.parse(this.lex(routine));
	}

	public ValidationResult validate(RoutineData routine) throws LexException {
		ParserResult parserResult = this.parse(routine);
		return parserResult.root().map(this.validator::validate).orElseGet(ValidationResult::unsupported);
	}

	public CogscriptInterpreter interpreter() {
		return this.interpreter;
	}
}
