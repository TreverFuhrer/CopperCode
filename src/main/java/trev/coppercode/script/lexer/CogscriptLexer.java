package trev.coppercode.script.lexer;

import java.util.List;

/**
 * Placeholder lexer.
 *
 * TODO Implement full tokenization, line tracking, indentation handling, comments, and recoverable diagnostics.
 */
public final class CogscriptLexer {
	public List<Token> tokenize(String source) throws LexException {
		if (source == null) {
			throw new LexException("Source text cannot be null.");
		}

		return List.of(new Token(TokenType.EOF, "", 1, Math.max(1, source.length() + 1)));
	}
}
