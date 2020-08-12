package rootcg.xhapex.operations.terminal;

import java.util.Collections;
import java.util.Map;
import rootcg.xhapex.OperationResult;
import rootcg.xhapex.OperationType;
import rootcg.xhapex.operations.TerminalOperation;

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

		return new OperationResult<Object, Object>(OperationType.DELETE, id, Collections.emptyList());
	}

}
