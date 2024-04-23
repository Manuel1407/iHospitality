package com.ikechukwu.ihospitality.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class APIResponse<T> {

    private String statusCode;
    private String message;
    private T data;

}