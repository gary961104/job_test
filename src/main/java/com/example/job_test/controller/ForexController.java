package com.example.job_test.controller;

import com.example.job_test.entity.Forex;
import com.example.job_test.entity.ForexDetail;
import com.example.job_test.model.code.ReturnCode;
import com.example.job_test.model.response.ResponseVO;
import com.example.job_test.service.ForexService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import com.example.job_test.model.code.ReturnCode;

@Controller
public class ForexController {
    @Autowired
    private ForexService forexService;

    @GetMapping("/")
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView("error");
        ResponseVO<List<Forex>> responseVO = new ResponseVO<>(ReturnCode.ERROR.getRtnCode(), ReturnCode.ERROR.getRtnMessage(), null);

        try {
            List<Forex> forexList = forexService.getDocument();
            if (null != forexList && !forexList.isEmpty()){
                responseVO.setRtnCode(ReturnCode.SUCCESS.getRtnCode());
                responseVO.setRtnMessage(ReturnCode.SUCCESS.getRtnMessage());
                responseVO.setRtnData(forexList);
                modelAndView = new ModelAndView("index");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        modelAndView.addObject("responseVO", responseVO);
        return modelAndView;
    }
}
