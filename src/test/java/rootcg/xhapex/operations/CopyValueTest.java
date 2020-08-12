package rootcg.xhapex.operations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import rootcg.xhapex.OperationResult;
import rootcg.xhapex.OperationType;
import rootcg.xhapex.Transformer;
import rootcg.xhapex.operations.terminal.UpdateOperation;
import rootcg.xhapex.operations.transform.CopyValue;

public class CopyValueTest {

	@Test
	public void shouldCopyValues() {
		Transformer transformer = new Transformer(
				new CopyValue("name", "newName"),
				new UpdateOperation("id")
		);

		Map<String, Object> source = new HashMap<>();
		source.put("id", 1);
		source.put("name", "shirt");

		Map<String, Object> expected = new HashMap<>();
		expected.put("id", 1);
		expected.put("newName", "shirt");

		List<OperationResult> result = transformer.transform(source);
		assertEquals(1, result.size());
		Assertions.assertEquals(OperationType.UPDATE, result.get(0).getOperationType());
		assertEquals(expected, result.get(0).getContent());
	}

	@Test
	public void fieldNotPresent_shouldFail() {
		Transformer transformer = new Transformer(
				new CopyValue("name", "newName"),
				new UpdateOperation("id")
		);

		Map<String, Object> source = new HashMap<>();
		source.put("id", 1);

		assertThrows(IllegalStateException.class, () -> transformer.transform(source));
	}

	@Test
	public void nullableFieldNotPresent_shouldCopyValues() {
		Transformer transformer = new Transformer(
				new CopyValue("name", "newName").nullable(),
				new UpdateOperation("id")
		);

		Map<String, Object> source = new HashMap<>();
		source.put("id", 1);

		Map<String, Object> expected = new HashMap<>();
		expected.put("id", 1);
		expected.put("newName", null);

		List<OperationResult> result = transformer.transform(source);
		assertEquals(1, result.size());
		Assertions.assertEquals(OperationType.UPDATE, result.get(0).getOperationType());
		assertEquals(expected, result.get(0).getContent());
	}

}
