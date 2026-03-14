package trev.coppercode.script.ast;

import java.util.List;

/** Represents a builtin or future user-defined command invocation. */
public record CallNode(String commandName, List<ExpressionNode> arguments) implements StatementNode {
	@Override
	public List<? extends AstNode> children() {
		return this.arguments;
	}
}
