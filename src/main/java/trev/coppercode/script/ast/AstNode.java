package trev.coppercode.script.ast;

import java.util.List;

/** Base AST node contract used by parser, validator, and interpreter stages. */
public interface AstNode {
	default List<? extends AstNode> children() {
		return List.of();
	}
}
