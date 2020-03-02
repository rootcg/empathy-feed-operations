package efo.operations.transform;

import efo.operations.TransformOperation;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public final class CopyValue implements TransformOperation {

	private final String source;
	private final String destiny;
	private boolean nullable;

	public CopyValue(String source, String destiny) {
		this.source = source;
		this.destiny = destiny;
		this.nullable = false;
	}

	public List<Map<String, Object>> apply(Map<String, Object> product) {
		Object value = product.remove(source);

		if (!nullable && value == null) {
			throw new IllegalStateException("The " + source + " field is required");
		}

		product.put(destiny, value);
		return Collections.singletonList(product);
	}

	public CopyValue nullable() {
		this.nullable = true;
		return this;
	}

}
