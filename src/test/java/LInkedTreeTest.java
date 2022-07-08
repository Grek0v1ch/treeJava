import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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

	@Test
	void removeNode() {
		LinkedTree tree = new LinkedTree("node");
		tree.add("node1", "node");
		tree.add("node2", "node");
		tree.add("node3", "node");
		// Сохраняем дерево до удаления.
		String firstTree = tree.toString();
		// Теперь добавляем узел и сохраняем дерево.
		tree.add("node11", "node1");
		String secondTree = tree.toString();
		assertNotEquals(firstTree, secondTree);
		// Удаляем последний добавленный узел.
		tree.remove("node11");
		assertEquals(firstTree, tree.toString());
	}

	@Test
	void removeSubtree() {
		LinkedTree tree = new LinkedTree("node");
		tree.add("node1", "node");
		tree.add("node2", "node");
		tree.add("node3", "node");
		tree.add("node11", "node1");
		// В данный момент дерево имеет такую структуру.
		String firstTree =
				"node\n" +
				"\tnode1\n" +
				"\t\tnode11\n" +
				"\tnode2\n" +
				"\tnode3\n";
		assertEquals(firstTree, tree.toString());
		// Нам нужно сделать такую структуру.
		String secondTree =
				"node\n" +
				"\tnode2\n" +
				"\tnode3\n";
		tree.remove("node1");
		assertEquals(secondTree, tree.toString());
	}

	// Тестируем удаление несуществующего узла.
	@Test
	void removeNode1() {
		LinkedTree tree = new LinkedTree("node");
		tree.add("node1", "node");
		tree.add("node2", "node");
		tree.add("node3", "node");
		tree.add("node11", "node1");
		String firstTree =
				"node\n" +
				"\tnode1\n" +
				"\t\tnode11\n" +
				"\tnode2\n" +
				"\tnode3\n";
		assertEquals(firstTree, tree.toString());
		tree.remove("folder");
		assertEquals(firstTree, tree.toString());
	}
}
