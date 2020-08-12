package rootcg.xhapex.operations.transform;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import rootcg.xhapex.operations.TransformOperation;

public final class IdentityOperation implements TransformOperation {

	public List<Map<String, Object>> apply(Map<String, Object> product) {
		return Collections.singletonList(product);
	}

}
