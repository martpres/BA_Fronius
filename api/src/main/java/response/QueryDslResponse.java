package response;

import java.io.Serializable;
import java.util.List;

public record QueryDslResponse<T>(List<T> content, long elements) implements Serializable {
}
