package rootcg.xhapex.operations;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import rootcg.xhapex.OperationResult;
import rootcg.xhapex.OperationType;
import rootcg.xhapex.Transformer;
import rootcg.xhapex.operations.terminal.UpdateOperation;
import rootcg.xhapex.operations.transform.FilterFields;

public class FilterFieldsTest {

	@Test
	public void shouldFilterFields() {
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
		Assertions.assertEquals(OperationType.UPDATE, result.get(0).getOperationType());
		assertEquals(expected, result.get(0).getContent());
	}

}
