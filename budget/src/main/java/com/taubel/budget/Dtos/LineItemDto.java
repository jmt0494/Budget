package com.taubel.budget.Dtos;

import com.taubel.budget.entities.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LineItemDto {

    private Long id;

    private String name;

    private User user;

    private CategoryDto category;

}
