import org.python.antlr.PythonTree;

/**
 * Created by Nik on 08-05-2015
 */
public class PythonTreePrinter {

	private final PythonTree tree;

	public PythonTreePrinter(PythonTree tree) {
		this.tree = tree;
	}

	public void prettyPrint() {
		System.out.println(this.getPrettyString(this.tree, 0));
	}

	private String getPrettyString(PythonTree treeNode, int indent) {
		StringBuilder sb = new StringBuilder();
		for (int i=0; i <= indent; i++) {
			sb.append("\t");
		}
		sb.append(treeNode.getText());
		sb.append(": ");
		sb.append(treeNode);
		sb.append("\n");

		if (treeNode.getChildCount() > 0) {
			int childIndent = indent+1;
			for (PythonTree child : treeNode.getChildren()) {
				sb.append(this.getPrettyString(child, childIndent));
			}
		}

		return sb.toString();
	}

	public void printTree() {
		this.printTree(this.tree);
	}

	private void printTree(PythonTree node) {
		if (node != null) {
			System.out.println(node.getText());
			if (node.getChildCount() > 0) {
				node.getChildren().forEach(this::printTree);
			}
		}
	}

	public void printTreeInfo() {
		System.out.println("NODE: ");
		System.out.println("CharStartIndex: " + this.tree.getCharStartIndex());
		System.out.println("CharStopIndex: " + this.tree.getCharStopIndex());
		System.out.println("ChildCount: " + this.tree.getChildCount());
		System.out.println("Line: " + this.tree.getLine());
		System.out.println("TokenStartIndex: " + this.tree.getTokenStartIndex());
		System.out.println("TokenStopIndex: " + this.tree.getTokenStopIndex());
		System.out.println("Text: " + this.tree.getText() + "\n\n");
	}
}
