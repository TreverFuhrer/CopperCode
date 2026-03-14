package trev.coppercode.script.ast;

import java.util.List;

/** Placeholder repeat-loop node. */
public record RepeatNode(ExpressionNode repetitions, BlockNode body) implements StatementNode {
	@Override
	public List<? extends AstNode> children() {
		return List.of(this.repetitions, this.body);
	}
}
