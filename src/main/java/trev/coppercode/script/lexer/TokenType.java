package trev.coppercode.script.lexer;

/** Initial token inventory for Cogscript. */
public enum TokenType {
	IDENTIFIER,
	NUMBER,
	STRING,
	NEWLINE,
	INDENT,
	DEDENT,
	COMMA,
	COLON,
	LEFT_PAREN,
	RIGHT_PAREN,
	KEYWORD_IF,
	KEYWORD_WHILE,
	KEYWORD_REPEAT,
	KEYWORD_CALL,
	COMMENT,
	EOF,
	UNKNOWN
}
