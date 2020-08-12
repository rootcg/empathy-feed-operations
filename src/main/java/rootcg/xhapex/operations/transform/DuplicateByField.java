package rootcg.xhapex.operations.transform;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import rootcg.xhapex.operations.TransformOperation;

public final class DuplicateByField implements TransformOperation {

	private final String baseField;
	private final String pivotField;
	private final String separator;

	public DuplicateByField(String baseField, String pivotField, String separator) {
		this.baseField = baseField;
		this.pivotField = pivotField;
		this.separator = separator;
	}

	public List<Map<String, Object>> apply(Map<String, Object> element) {
		Object pivot = element.remove(pivotField);

		if (pivot == null) {
			throw new IllegalStateException("Pivot field not found: " + pivotField);
		}

		if (!(pivot instanceof List)) {
			throw new IllegalStateException("Pivot field should be a list");
		}

		if (!element.containsKey(baseField)) {
			throw new IllegalStateException("Base field not found: " + baseField);
		}

		List<String> pivotValues = (List<String>) pivot;
		return pivotValues.stream().map(p -> combine(p, element)).collect(Collectors.toList());
	}

	private Map<String, Object> combine(String pivot, Map<String, Object> element) {
		Map<String, Object> newElement = new HashMap<>(element);
		newElement.compute(baseField, (k, v) -> String.valueOf(v).concat(separator).concat(pivot));
		return newElement;
	}

}
