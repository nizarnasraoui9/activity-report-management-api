package com.emainfo.cra.exception;

import java.util.function.Supplier;

public class EntityException extends RuntimeException {

    public static Supplier<EntityException> toSupplier(final String reason) {
        return () -> new EntityException(reason);
    }

    public static Supplier<EntityException> toSupplier(final String reason, final Throwable cause) {
        return () -> new EntityException(reason, cause);
    }

    public EntityException(final String reason) {
        super(reason);
    }

    public EntityException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
