import java.util.ArrayList;
import java.util.List;

public class LinkedTree {
	private static class Node {
		private String name = null;
		private List<Node> children = null;

		public Node() {}

		public Node(String name) {
			this.name = name;
		}

		public void add(String name) {
			if (children == null) children = new ArrayList<>();
			children.add(new Node(name));
		}
	}

	private Node root = null;

	private void add(Node pos, String name, String parentName) {
		if (pos.name.equals(parentName)) {
			pos.add(name);
			return;
		}
		if (pos.children == null) return;
		for (Node child : pos.children)
			add(child, name, parentName);
	}

	private String toString(Node pos, int level) {
		StringBuilder answer = new StringBuilder();
		for (int i = 0; i < level; i++)
			answer.append("\t");
		answer.append(pos.name).append("\n");
		if (pos.children == null) return answer.toString();
		for (Node child : pos.children)
			answer.append(toString(child, level + 1));
		return answer.toString();
	}

	public LinkedTree() {}

	public LinkedTree(String name) {
		root = new Node(name);
	}

	public void add(String name, String parentName) {
		add(root, name, parentName);
	}

	@Override
	public String toString() {
		return toString(root, 0);
	}
}
