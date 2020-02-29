package efo;

import java.util.Map;

public interface Operation<T> {

	T apply(Map<String, Object> product);

}
