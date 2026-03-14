package trev.coppercode.script.lexer;

/** Thrown when source text cannot be tokenized. */
public final class LexException extends Exception {
	public LexException(String message) {
		super(message);
	}
}
