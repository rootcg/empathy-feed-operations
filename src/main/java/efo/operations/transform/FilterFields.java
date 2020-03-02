package efo.operations.transform;

import efo.operations.TransformOperation;

import java.util.*;

public final class FilterFields implements TransformOperation {

	private final List<String> fields;

	public FilterFields(String... fields) {
		this.fields = Arrays.asList(fields);
	}

	public List<Map<String, Object>> apply(Map<String, Object> product) {
		Map<String, Object> newProduct = new HashMap<>();

		for (String field : fields) {
			newProduct.put(field, product.get(field));
		}

		return Collections.singletonList(newProduct);
	}

}
