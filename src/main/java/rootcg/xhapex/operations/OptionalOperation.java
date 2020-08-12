package rootcg.xhapex.operations;

import java.util.Map;
import java.util.function.Predicate;
import rootcg.xhapex.Operation;
import rootcg.xhapex.operations.transform.IdentityOperation;

public class OptionalOperation implements Operation<Operation> {

	private final Operation operation;
	private final Predicate<Map<String, Object>> condition;

	public OptionalOperation(Predicate<Map<String, Object>> condition, Operation operation) {
		this.condition = condition;
		this.operation = operation;
	}

	@Override
	public Operation apply(Map<String, Object> product) {
		return condition.test(product) ? operation : new IdentityOperation();
	}

}
