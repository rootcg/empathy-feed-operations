package efo.operations.terminal;

import efo.OperationResult;
import efo.operations.TerminalOperation;

import java.util.Collections;
import java.util.Map;

import static efo.OperationType.DELETE;

public final class DeleteOperation implements TerminalOperation {

	private final String idField;

	public DeleteOperation(String idField) {
		this.idField = idField;
	}

	public OperationResult<Object, ?> apply(Map<String, Object> product) {
		Object id = product.get(idField);

		if (id == null) {
			throw new IllegalStateException("ID field not found: " + idField);
		}

		return new OperationResult<Object, Object>(DELETE, id, Collections.emptyList());
	}

}
