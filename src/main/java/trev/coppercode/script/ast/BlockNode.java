package trev.coppercode.script.ast;

import java.util.List;

/** Ordered list of statements that will eventually map to a routine block or nested control block. */
public record BlockNode(List<StatementNode> statements) implements AstNode {
	@Override
	public List<? extends AstNode> children() {
		return this.statements;
	}
}
