package efo;

import efo.operations.TerminalOperation;
import efo.operations.TransformOperation;

import java.util.*;
import java.util.stream.Collectors;

public class Transformer {

	private final LinkedList<Operation> operationList;

	public Transformer(List<Operation> operations) {
		operationList = new LinkedList<>(operations);
	}

	public Transformer(Operation... operations) {
		this(Arrays.asList(operations));
	}

	public List<OperationResult> transform(Map<String, Object> element) {
		return transform(new HashMap<>(element), operationList);
	}

	private List<OperationResult> transform(Map<String, Object> element, LinkedList<Operation> operationList) {
		Operation operation = operationList.poll();

		if (operation instanceof TerminalOperation) {
			return Collections.singletonList(((TerminalOperation) operation).apply(element));
		}

		if (operation instanceof TransformOperation) {
			List<Map<String, Object>> transformedElements = ((TransformOperation) operation).apply(element);
			return transformedElements.stream()
									  .map(e -> transform(e, new LinkedList<>(operationList)))
									  .flatMap(List::stream)
									  .collect(Collectors.toList());
		}

		throw new IllegalStateException("Unknown operation");
	}

}
