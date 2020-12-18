package com.github.yjgbg.demo201202.exceptions;

import lombok.Getter;

import java.util.function.Supplier;

public class BizException extends RuntimeException implements Supplier<BizException> {
    @Getter
    private final Integer status;

    public BizException(Integer status, String format,Object... args) {
        super(String.format(format,args), null, false, false);
        this.status = status;
    }

    public BizException(String format,Object... args) {
        this(null,format,args);
    }

    public BizException causeBy(Throwable throwable) {
        super.initCause(throwable);
        return this;
    }

    @Override
    public BizException get() {
        return this;
    }
}
