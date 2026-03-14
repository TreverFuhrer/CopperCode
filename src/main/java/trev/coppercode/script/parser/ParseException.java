package trev.coppercode.script.parser;

/** Represents a parser diagnostic that should be surfaced back to the editor. */
public final class ParseException extends Exception {
	public ParseException(String message) {
		super(message);
	}
}
