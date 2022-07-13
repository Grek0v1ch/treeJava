import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LinkedTree {
	private static class Node {
		private String name = null;
		private List<Node> children = null;

		public Node() {}

		public Node(String name) {
			this.name = name;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			Node node = (Node) o;
			return Objects.equals(name, node.name);
		}

		public void add(String name) {
			if (children == null) children = new ArrayList<>();
			children.add(new Node(name));
		}

		public String getName() {
			return name;
		}

		public List<Node> getChildren() {
			return children;
		}
	}

	private Node root = null;

	public Node getRoot() {
		return root;
	}

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
		if (pos == null) return "";
		StringBuilder answer = new StringBuilder();
		for (int i = 0; i < level; i++)
			answer.append("\t");
		answer.append(pos.name).append("\n");
		if (pos.children == null) return answer.toString();
		for (Node child : pos.children)
			answer.append(toString(child, level + 1));
		return answer.toString();
	}

	private void remove(Node pos, String name) {
		if (pos.children == null) return;
		for (Node child : pos.children)
			if (child.name.equals(name)) {
				pos.children.remove(child);
				return;
			}
		for (Node child : pos.children)
			remove(child, name);
	}

	public LinkedTree() {}

	public LinkedTree(String name) {
		root = new Node(name);
	}

	public void add(String name, String parentName) {
		add(root, name, parentName);
	}

	public void remove(String name) {
		if (root == null) return;
		if (root.name.equals(name)) {
			root = null;
			return;
		}
		remove(root, name);
	}

	@Override
	public String toString() {
		return toString(root, 0);
	}

	private String toHtml(Node pos) {
		if (pos == null) return "";
		StringBuilder answer = new StringBuilder();
		answer.append("<li><a href=\"edit/").append(pos.name).append("\">").append(pos.name).append("</a>");
		if (pos.children == null) return answer.append("</li>").toString();
		answer.append("<ul>");
		for (Node child : pos.children)
			answer.append(toHtml(child));
		answer.append("</ul>");
		return answer.append("</li>").toString();
	}

	public String printToHtml() {
		return "<ul>" +
				toHtml(root) +
				"</ul>";
	}

	private void edit(Node pos, String name, String newName) {
		if (pos == null) return;
		if (pos.name.equals(name)) {
			pos.name = newName;
			return;
		}
		if (pos.children == null) return;
		for (Node child : pos.children)
			edit(child, name, newName);
	}

	public void edit(String name, String newName) {
		if (name == null || newName == null) return;
		edit(root, name, newName);
	}
}
