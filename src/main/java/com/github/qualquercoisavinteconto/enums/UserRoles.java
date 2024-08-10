package com.github.qualquercoisavinteconto.enums;

public enum UserRoles {
    ADMIN(1L),
    CUSTOMER(2L),
    SELLER(3L),
    ADVERTISER(4L);

    private final Long value;

    UserRoles(Long value) {
        this.value = value;
    }

    public Long getValue() {
        return value;
    }

    public static UserRoles fromValue(Long value) {
        for (UserRoles roleType : UserRoles.values()) {
            if (roleType.getValue().equals(value)) {
                return roleType;
            }
        }
        throw new IllegalArgumentException("Invalid RoleType value: " + value);
    }
}
