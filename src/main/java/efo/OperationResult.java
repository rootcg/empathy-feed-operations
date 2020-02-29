package efo;

public class OperationResult<ID, T> {

	private final OperationType operationType;
	private final ID id;
	private final T content;

	public OperationResult(OperationType operationType, ID id, T content) {
		this.operationType = operationType;
		this.id = id;
		this.content = content;
	}

	public OperationType getOperationType() {
		return operationType;
	}

	public ID getId() {
		return id;
	}

	public T getContent() {
		return content;
	}

}
