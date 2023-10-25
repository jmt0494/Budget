package com.taubel.budget.dtos;

import java.util.List;
import java.util.stream.Collectors;

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
        this.budget = new BudgetDto(category.getBudget());
        this.user = new UserDto(category.getUser());
        this.name = category.getName();
        this.lineItems = category.getLineitems().stream()
            .map(LineItemDto::new)
            .collect(Collectors.toList());
    }
    
    private Long id;

    private String name;

    private BudgetDto budget;

    private List<LineItemDto> lineItems;

    private UserDto user;

}
