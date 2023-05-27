package com.vacika.projectstartertemplate.util;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Supplier;

@Component
public class TransactionHandler {

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public <E> E runInNewTransaction(Supplier<E> supplier) {
        return supplier.get();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public <E> E runInTransaction(Supplier<E> supplier) {
        return supplier.get();
    }
}
