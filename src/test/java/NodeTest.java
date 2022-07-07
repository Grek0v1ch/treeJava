import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NodeTest {
    @Test
    void createNode() {
        Node tree = new Node("node1");
        assertEquals("node1", tree.getName());
    }

    @Test
    void addNode() {
        Node root = new Node("node1");
        Node child = new Node("node2");
        root.add(child);
        assertEquals(1, root.getChildren().size());
        assertEquals("node2", root.getChildren().get(0).getName());
    }
}
