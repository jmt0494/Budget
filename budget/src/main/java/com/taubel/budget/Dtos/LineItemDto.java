package com.taubel.budget.Dtos;

import com.taubel.budget.entities.LineItem;
import com.taubel.budget.entities.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LineItemDto {

    public LineItemDto(LineItem lineItem) {
        this.id = lineItem.getId();
        this.name = lineItem.getName();
    }

    private Long id;

    private String name;

    private User user;

    private CategoryDto category;

}
