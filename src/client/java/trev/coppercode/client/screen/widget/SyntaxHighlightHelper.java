package trev.coppercode.client.screen.widget;

import trev.coppercode.script.lexer.TokenType;

/** Maps future Cogscript token types to editor colors. */
public final class SyntaxHighlightHelper {
	private SyntaxHighlightHelper() {
	}

	public static int colorFor(TokenType tokenType) {
		return switch (tokenType) {
			case KEYWORD_IF, KEYWORD_WHILE, KEYWORD_REPEAT, KEYWORD_CALL -> 0xFF7CC7FF;
			case NUMBER -> 0xFFFFC66D;
			case STRING -> 0xFF9CDC7C;
			case COMMENT -> 0xFF7A7F85;
			default -> 0xFFE0E0E0;
		};
	}
}
