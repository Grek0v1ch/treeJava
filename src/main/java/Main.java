public class Main {
    public static void main(String[] args) {
        LinkedTree tree = new LinkedTree("node");
        tree.add("node1", "node");
        tree.add("node2", "node");
        tree.add("node3", "node");
        tree.add("node11", "node1");
        System.out.println(tree);
        tree.remove("node1");
        System.out.println(tree);
        tree.add("node1", "node");
        System.out.println(tree);
    }
}
