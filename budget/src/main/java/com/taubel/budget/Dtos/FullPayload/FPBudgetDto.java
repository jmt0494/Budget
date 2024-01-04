package com.taubel.budget.Dtos.FullPayload;

import java.util.List;

import com.taubel.budget.entities.Budget;
import com.taubel.budget.enums.Month;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FPBudgetDto {

    public FPBudgetDto(Budget budget) {
        this.id = budget.getId();
        this.month = budget.getMonth();
        this.year = budget.getYear();
        this.userId = budget.getUser().getId();
    }

    private Long id;

    private Month month;

    private Short year;

    private Long userId;

    private List<FPCategoryDto> categories;
}
