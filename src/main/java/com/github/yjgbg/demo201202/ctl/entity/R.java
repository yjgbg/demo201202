package com.github.yjgbg.demo201202.ctl.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.Value;

import java.util.HashMap;
import java.util.Map;

@Value
@JsonInclude(content = JsonInclude.Include.NON_NULL)
public class R<T> {
    int code;
    String error;
    T data;

    public <U> R<U> withData(U data) {
        return new R<>(code,error,data);
    }

    public <U> R<U> withError(String errorFmt,Object... args) {
        return new R<>(code,String.format(errorFmt,args),null);
    }

    private static final Map<Integer,R<Void>> cache = new HashMap<>(100);

    public static R<Void> code(int code) {
        var rVoid = cache.get(code);
        if (rVoid!=null) return rVoid;
        rVoid = new R<>(code, null, null);
        cache.put(code,rVoid);
        return rVoid;
    }

    public static R<Void> ok() {
        return code(0);
    }

    public static R<Void> failed() {
        return code(-1);
    }
}
