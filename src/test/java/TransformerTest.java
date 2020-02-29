import efo.OperationResult;
import efo.OperationType;
import efo.Transformer;
import efo.operations.terminal.UpdateOperation;
import efo.operations.transform.CopyValue;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransformerTest {

	@Test
	public void updateOperation() {
		Transformer transformer = new Transformer(
				new CopyValue("name", "newName"),
				new UpdateOperation("id")
		);

		Map<String, Object> source = new HashMap<String, Object>();
		source.put("id", 1);
		source.put("name", "Cristian");

		Map<String, Object> expected = new HashMap<String, Object>();
		expected.put("id", 1);
		expected.put("newName", "Cristian");

		List<OperationResult> result = transformer.transform(source);
		assertEquals(1, result.size());
		assertEquals(OperationType.UPDATE, result.get(0).getOperationType());
		assertEquals(expected, result.get(0).getContent());
	}

}
