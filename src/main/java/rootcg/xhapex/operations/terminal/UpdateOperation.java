package rootcg.xhapex.operations.terminal;

import static rootcg.xhapex.OperationType.UPDATE;

import java.util.Map;
import rootcg.xhapex.OperationResult;
import rootcg.xhapex.operations.TerminalOperation;

public final class UpdateOperation implements TerminalOperation {

	private final String idField;

	public UpdateOperation(String idField) {
		this.idField = idField;
	}

	public OperationResult<Object, ?> apply(Map<String, Object> product) {
		Object id = product.get(idField);

		if (id == null) {
			throw new IllegalStateException("ID field not found: " + idField);
		}

		return new OperationResult<Object, Object>(UPDATE, id, product);
	}

}
