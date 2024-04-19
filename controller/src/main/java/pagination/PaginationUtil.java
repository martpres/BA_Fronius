package pagination;

import org.springframework.data.domain.PageRequest;

import java.util.Optional;

public class PaginationUtil {

    private PaginationUtil() {
    }

    public static Optional<PageRequest> getPagination(Optional<Integer> page, Optional<Integer> pagesize){
        if (page.isEmpty() || pagesize.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(PageRequest.of(page.get(), pagesize.get()));
    }

}
