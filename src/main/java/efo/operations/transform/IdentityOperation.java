package efo.operations.transform;

import efo.operations.TransformOperation;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public final class IdentityOperation implements TransformOperation {

	public List<Map<String, Object>> apply(Map<String, Object> product) {
		return Collections.singletonList(product);
	}

}
