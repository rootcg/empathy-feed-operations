package rootcg.xhapex;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import rootcg.xhapex.operations.OptionalOperation;
import rootcg.xhapex.operations.terminal.DeleteOperation;
import rootcg.xhapex.operations.terminal.UpdateOperation;
import rootcg.xhapex.operations.transform.CopyValue;
import rootcg.xhapex.operations.transform.DuplicateByField;

public class TransformerTest {

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
		result.forEach(r -> Assertions.assertEquals(OperationType.UPDATE, r.getOperationType()));
		assertEquals(expectedIds, result.stream().map(OperationResult::getId).collect(Collectors.toList()));
	}

	@Test
	public void optionalOperation() {
		Transformer transformer = new Transformer(
				new CopyValue("name", "newName"),
				new DuplicateByField("id", "color", "-"),
				new OptionalOperation(e -> String.valueOf(e.get("id")).length() > 5, new DeleteOperation("id")),
				new UpdateOperation("id")
		);

		Map<String, Object> source = new HashMap<>();
		source.put("id", 1);
		source.put("name", "shirt");
		source.put("color", Arrays.asList("red", "blue", "pink"));

		List<String> expectedIds = Arrays.asList("1-red", "1-blue", "1-pink");

		List<OperationResult> result = transformer.transform(source);
		assertEquals(1, result.stream().filter(r -> r.getOperationType().equals(OperationType.UPDATE)).count());
		assertEquals(2, result.stream().filter(r -> r.getOperationType().equals(OperationType.DELETE)).count());
		assertEquals(expectedIds, result.stream().map(OperationResult::getId).collect(Collectors.toList()));

	}

}
