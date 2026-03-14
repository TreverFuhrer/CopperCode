package trev.coppercode.script.ast;

import java.util.List;

/** Root AST node for a named routine. */
public record RoutineNode(String name, BlockNode body) implements AstNode {
	@Override
	public List<? extends AstNode> children() {
		return List.of(this.body);
	}
}
