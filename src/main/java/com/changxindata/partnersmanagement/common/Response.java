package com.changxindata.partnersmanagement.common;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

@Setter
@Getter
public class Response<T> {

    public static final Response<?> EMPTY = new Response<>();

    @Nullable
    private final T body;

    private Integer resultCode = null;
    private String resultMsg = null;

    protected Response() {
        this.body = null;
    }

    public Response(T body) {
        this.body = body;
    }
}
