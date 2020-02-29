package efo.operations;

import efo.Operation;
import efo.operations.transform.IdentityOperation;

import java.util.Map;
import java.util.function.Predicate;

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
