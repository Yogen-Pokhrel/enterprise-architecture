package com.lab9.book.repository;

import com.lab9.book.dto.request.BookFilterDto;
import com.lab9.book.entity.Book;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class BookSpecification {
    public static Specification<Book> filterBy(BookFilterDto filterDto) {
        return (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();
            if(filterDto != null) {
                if (StringUtils.hasText(filterDto.getTitle())) {
                    predicate = criteriaBuilder.and(predicate,
                            criteriaBuilder.like(root.get("title"), "%" + filterDto.getTitle() + "%"));
                }

                if (StringUtils.hasText(filterDto.getAuthor())) {
                    predicate = criteriaBuilder.and(predicate,
                            criteriaBuilder.like(root.get("author"), "%" + filterDto.getAuthor() + "%"));
                }
            }
            return predicate;
        };
    }
}
