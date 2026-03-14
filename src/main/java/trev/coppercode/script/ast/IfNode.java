package trev.coppercode.script.ast;

import java.util.ArrayList;
import java.util.List;
import org.jspecify.annotations.Nullable;

/** Placeholder conditional node. */
public record IfNode(ExpressionNode condition, BlockNode thenBlock, @Nullable BlockNode elseBlock) implements StatementNode {
	@Override
	public List<? extends AstNode> children() {
		List<AstNode> children = new ArrayList<>();
		children.add(this.condition);
		children.add(this.thenBlock);
		if (this.elseBlock != null) {
			children.add(this.elseBlock);
		}

		return List.copyOf(children);
	}
}
