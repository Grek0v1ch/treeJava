import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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

	// Тестируем удаление несуществующего узла
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

	@Test
	void writeFile() throws IOException {
		LinkedTree tree = new LinkedTree("node");
		tree.add("node1", "node");
		tree.add("node2", "node");
		tree.add("node3", "node");
		tree.add("node11", "node1");
		String content = tree.toString();
		String path = "/home/user/IdeaProjects/treeJava/target/text.txt";
		Files.write(Paths.get(path), content.getBytes(StandardCharsets.UTF_8));

		byte[] writeByteContent = Files.readAllBytes(Paths.get(path));
		String writeContent = new String(writeByteContent);
		assertEquals(content, writeContent);
	}

	@Test
	void writeFileJSON() throws IOException {
		LinkedTree tree = new LinkedTree("node");
		tree.add("node1", "node");
		tree.add("node2", "node");
		tree.add("node3", "node");
		tree.add("node11", "node1");

		ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
		String actual = objectMapper.writeValueAsString(tree);
		String path = "/home/user/IdeaProjects/treeJava/target/text.json";
		Files.write(Paths.get(path), actual.getBytes(StandardCharsets.UTF_8));

		byte[] writeByteContent = Files.readAllBytes(Paths.get(path));
		String writeContent = new String(writeByteContent);

		LinkedTree tree1 = objectMapper.readValue(actual, LinkedTree.class);
		LinkedTree tree2 = objectMapper.readValue(writeContent, LinkedTree.class);
		assertEquals(actual, writeContent);
	}

	@Test
	void readFromJSON() throws JsonProcessingException {
		String jsonString = "{\n" +
				"  \"root\" : {\n" +
				"    \"name\" : \"node\",\n" +
				"    \"children\" : [ {\n" +
				"      \"name\" : \"node1\",\n" +
				"      \"children\" : [ {\n" +
				"        \"name\" : \"node11\",\n" +
				"        \"children\" : null\n" +
				"      } ]\n" +
				"    }, {\n" +
				"      \"name\" : \"node2\",\n" +
				"      \"children\" : null\n" +
				"    }, {\n" +
				"      \"name\" : \"node3\",\n" +
				"      \"children\" : null\n" +
				"    } ]\n" +
				"  }\n" +
				"}";
		ObjectMapper objectMapper = new ObjectMapper();
		LinkedTree tree = objectMapper.readValue(jsonString, LinkedTree.class);
		String stringTree =
				"node\n" +
						"\tnode1\n" +
						"\t\tnode11\n" +
						"\tnode2\n" +
						"\tnode3\n";
		assertEquals(stringTree, tree.toString());
	}
}
