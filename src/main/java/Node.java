import java.util.ArrayList;
import java.util.List;

public class Node {
    private String name;
    List<Node> children;

    public Node(String value) {
        this.name = new String(value);
        children = null;
    }

    public String getName() {
        return name;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void add(Node child) {
        if (children == null) children = new ArrayList<>();
        children.add(child);
    }

    public static void printTree(Node root, int level) {
        for (int i = 0; i < level; i++)
            System.out.print("\t");
        System.out.println(root.getName());
        if (root.getChildren() == null) return;
        for (Node child : root.children)
            printTree(child, level + 1);
    }

    public static String toString(Node root, int level) {
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < level; i++)
            answer.append("\t");
        answer.append(root.getName());
        answer.append("\n");
        if (root.getChildren() == null) return answer.toString();
        for (Node child : root.children)
            answer.append(toString(child, level + 1));
        return answer.toString();
    }
}
