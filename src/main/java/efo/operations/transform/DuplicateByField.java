package efo.operations.transform;

import efo.operations.TransformOperation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class DuplicateByField implements TransformOperation {

	private final String idField;
	private final String pivotField;
	private final String separator;

	public DuplicateByField(String idField, String pivotField, String separator) {
		this.idField = idField;
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

		if (!element.containsKey(idField)) {
			throw new IllegalStateException("ID field not found: " + idField);
		}

		List<String> pivotValues = (List<String>) pivot;
		return pivotValues.stream().map(p -> combine(p, element)).collect(Collectors.toList());
	}

	private Map<String, Object> combine(String pivot, Map<String, Object> element) {
		Map<String, Object> newElement = new HashMap<>(element);
		newElement.compute(idField, (k, v) -> String.valueOf(v).concat(separator).concat(pivot));
		return newElement;
	}

}
