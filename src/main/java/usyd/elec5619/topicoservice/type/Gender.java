package usyd.elec5619.topicoservice.type;

import lombok.Getter;

@Getter
public enum Gender {
    NOT_KNOWN(0),
    MALE(1),
    FEMALE(2),
    NOT_APPLICABLE(9);

    private final int value;

    Gender(int value) {
        this.value = value;
    }

    public static Gender fromValue(int value) {
        for (Gender gender : Gender.values()) {
            if (gender.getValue() == value) {
                return gender;
            }
        }
        throw new IllegalArgumentException("Invalid gender value: " + value);
    }
}
