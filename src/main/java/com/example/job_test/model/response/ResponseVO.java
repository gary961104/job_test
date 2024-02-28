package com.example.job_test.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseVO<T>{

    private String rtnCode;

    private String rtnMessage;

    private T rtnData;

}
