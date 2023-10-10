package com.taubel.budget.enums;

public enum Month {
    Jan, Feb, Mar, Apr, May, Jun, Jul, Aug, Sep, Oct, Nov, Dec;

    public static Month fromString(String input) {
        for (Month month : Month.values()) {
            if (month.name().equalsIgnoreCase(input)) {
                return month;
            }
        }
        throw new IllegalArgumentException("No enum constant with input string: " + input);
    }
}
