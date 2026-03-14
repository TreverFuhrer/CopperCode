package trev.coppercode.script.validate;

import java.util.List;

/** Validator output wrapper. */
public record ValidationResult(List<ValidationError> errors, List<String> warnings) {
	public static ValidationResult unsupported() {
		return new ValidationResult(List.of(), List.of("Validator scaffold exists, but semantic analysis is still TODO."));
	}

	public boolean isSuccessful() {
		return this.errors.isEmpty();
	}
}
