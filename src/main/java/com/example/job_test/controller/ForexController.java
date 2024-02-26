package com.example.job_test.controller;

import com.example.job_test.entity.Forex;
import com.example.job_test.entity.ForexDetail;
import com.example.job_test.service.ForexService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ForexController {
    @Autowired
    private ForexService forexService;

    @GetMapping("/")
    public ModelAndView test(){
        ModelAndView modelAndView = new ModelAndView("index");
        try {
            List<Forex> forexList = forexService.getDocument();
            modelAndView.addObject("forexList", forexList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return modelAndView;
    }
}
