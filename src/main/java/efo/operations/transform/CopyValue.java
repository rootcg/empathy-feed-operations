package efo.operations.transform;

import efo.operations.TransformOperation;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public final class CopyValue implements TransformOperation {

	private final String source;
	private final String destiny;

	public CopyValue(String source, String destiny) {
		this.source = source;
		this.destiny = destiny;
	}

	public List<Map<String, Object>> apply(Map<String, Object> product) {
		Object value = product.remove(source);

		if (value != null) {
			product.put(destiny, value);
		}

		return Collections.singletonList(product);
	}

}
