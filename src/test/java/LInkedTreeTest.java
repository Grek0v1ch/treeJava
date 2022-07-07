import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LInkedTreeTest {
	@Test
	void createTree() {
		LinkedTree tree = new LinkedTree();
		assertEquals("", tree.toString());
		tree = new LinkedTree("node");
		assertEquals("node\n", tree.toString());
	}
	@Test
	void addTree() {
		LinkedTree tree = new LinkedTree("node");
        tree.add("node1", "node");
        tree.add("node2", "node");
        tree.add("node3", "node");
        tree.add("node11", "node1");
		String trueResult =
				"node\n" +
				"\tnode1\n" +
				"\t\tnode11\n" +
				"\tnode2\n" +
				"\tnode3\n";
		assertEquals(trueResult, tree.toString());
	}

	// Попытка добавить элемент к несуществующему родителю. Дерево никак не должно быть изменено.
	@Test
	void addTree1() {
		LinkedTree tree = new LinkedTree("node");
		tree.add("node1", "node");
        tree.add("node2", "folder");
		String trueResult =
				"node\n" +
				"\tnode1\n";
		assertEquals(trueResult, tree.toString());
	}
}
