package com.example.job_test.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class ForexDetail {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    private double buyForex;

    private double sellForex;

}
