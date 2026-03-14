package trev.coppercode.script.lexer;

/** Single lexical token produced from Cogscript source text. */
public record Token(TokenType type, String lexeme, int line, int column) {
}
