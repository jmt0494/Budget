package com.taubel.budget.dtos;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Hibernate;

import com.taubel.budget.entities.Category;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class CategoryDto {

    public CategoryDto(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.user = new UserDto(category.getUser());
    }
    
    private Long id;

    private String name;

    private BudgetDto budget;

    private UserDto user;

}
