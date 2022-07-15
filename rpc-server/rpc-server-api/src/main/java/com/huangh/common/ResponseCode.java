package com.huangh.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum   ResponseCode {
    SUCCESS(200, "SUCCESS"),
    ERROR(500, "服务端异常");
    private final Integer code;
    private final String message;


}
