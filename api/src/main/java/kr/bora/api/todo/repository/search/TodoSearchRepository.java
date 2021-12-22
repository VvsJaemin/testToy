package kr.bora.api.todo.repository.search;

import kr.bora.api.todo.domain.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TodoSearchRepository {

    Todo search();

    Page<Object[]> searchPage(String type, String keyword, Pageable pageable);


}
