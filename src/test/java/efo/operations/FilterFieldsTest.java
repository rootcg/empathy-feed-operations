package efo.operations;

import efo.OperationResult;
import efo.Transformer;
import efo.operations.terminal.UpdateOperation;
import efo.operations.transform.FilterFields;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static efo.OperationType.UPDATE;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FilterFieldsTest {

	@Test
	public void apply_shouldFilterFields() {
		Transformer transformer = new Transformer(
				new FilterFields("name", "color"),
				new UpdateOperation("name")
		);

		Map<String, Object> source = new HashMap<>();
		source.put("id", 1);
		source.put("name", "shirt");
		source.put("color", "red");

		Map<String, Object> expected = new HashMap<>();
		expected.put("name", "shirt");
		expected.put("color", "red");

		List<OperationResult> result = transformer.transform(source);
		assertEquals(1, result.size());
		assertEquals(UPDATE, result.get(0).getOperationType());
		assertEquals(expected, result.get(0).getContent());
	}

}
