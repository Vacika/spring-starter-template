package com.vacika.projectstartertemplate.domain.base;

public enum Status {
    ACTIVE,
    INACTIVE, // Used when some entity is not activated yet, like for example some verification required until it becomes ACTIVE
    SUSPENDED, // Suspended on purpose by some action
    DELETED; // Used for soft-delete
}