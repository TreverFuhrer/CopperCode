package trev.coppercode.script.ast;

import java.util.List;

/** Placeholder while-loop node. */
public record WhileNode(ExpressionNode condition, BlockNode body) implements StatementNode {
	@Override
	public List<? extends AstNode> children() {
		return List.of(this.condition, this.body);
	}
}
