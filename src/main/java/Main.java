public class Main {
    public static void main(String[] args) {
        Node root = new Node("node1");
        for (int i = 0; i < 3; i++)
            root.add(new Node("node" + (i + 1)));
        for (Node child : root.getChildren())
            child.add(new Node("node"));
        System.out.println(Node.toString(root, 0));
    }
}
