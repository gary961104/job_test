package com.example.job_test.model.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ReturnCode {

    SUCCESS("200", "外匯資料讀取成功"),
    ERROR("999", "外匯資料讀取異常");

    private String rtnCode;

    private String rtnMessage;


}
