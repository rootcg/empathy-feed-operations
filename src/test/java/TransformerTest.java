import efo.OperationResult;
import efo.OperationType;
import efo.Transformer;
import efo.operations.terminal.UpdateOperation;
import efo.operations.transform.CopyValue;
import efo.operations.transform.DuplicateByField;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransformerTest {

	@Test
	public void updateOperation() {
		Transformer transformer = new Transformer(
				new CopyValue("name", "newName"),
				new UpdateOperation("id")
		);

		Map<String, Object> source = new HashMap<>();
		source.put("id", 1);
		source.put("name", "Cristian");

		Map<String, Object> expected = new HashMap<>();
		expected.put("id", 1);
		expected.put("newName", "Cristian");

		List<OperationResult> result = transformer.transform(source);
		assertEquals(1, result.size());
		assertEquals(OperationType.UPDATE, result.get(0).getOperationType());
		assertEquals(expected, result.get(0).getContent());
	}

	@Test
	public void duplicateOperation() {
		Transformer transformer = new Transformer(
				new CopyValue("name", "newName"),
				new DuplicateByField("id", "color", "-"),
				new UpdateOperation("id")
		);

		Map<String, Object> source = new HashMap<>();
		source.put("id", 1);
		source.put("name", "shirt");
		source.put("color", Arrays.asList("red", "blue", "pink"));

		List<String> expectedIds = Arrays.asList("1-red", "1-blue", "1-pink");

		List<OperationResult> result = transformer.transform(source);
		assertEquals(OperationType.UPDATE, result.get(0).getOperationType());
		assertEquals(expectedIds, result.stream().map(OperationResult::getId).collect(Collectors.toList()));
	}

}
