package rootcg.xhapex.operations.transform;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import rootcg.xhapex.operations.TransformOperation;

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
