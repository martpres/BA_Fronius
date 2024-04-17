package response;

import java.util.List;

public class QueryDslResponse<T> {
    private List<T> content;
    private long elements;

    public QueryDslResponse(List<T> content, long elements) {
        this.content = content;
        this.elements = elements;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public long getElements() {
        return elements;
    }

    public void setElements(long elements) {
        this.elements = elements;
    }
}
