package com.vacika.projectstartertemplate.domain;

import lombok.Getter;

import static com.vacika.projectstartertemplate.domain.EngineType.EngineTypeValueIds.*;

@Getter
public enum EngineType {
    DIESEL(DIESEL_ID),
    GASOLINE(GASOLINE_ID),
    ELECTRIC(ELECTRIC_ID),
    H2O(H2O_ID);

    private final int id;

    EngineType(int id) {
        this.id = id;
    }

    static class EngineTypeValueIds {
        public static final int DIESEL_ID = 1;
        public static final int GASOLINE_ID = 2;
        public static final int ELECTRIC_ID = 3;
        public static final int H2O_ID = 4;
    }

    public static EngineType setById(int id) {
        for (EngineType enumObj : EngineType.values()) {
            if (enumObj.id == id) {
                return enumObj;
            }
        }
        return null;
    }

}
