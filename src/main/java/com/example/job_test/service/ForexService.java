package com.example.job_test.service;

import com.example.job_test.config.ForexConfig;
import com.example.job_test.entity.Forex;
import com.example.job_test.entity.ForexDetail;
import com.google.gson.Gson;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ForexService {

    @Autowired
    private ForexConfig forexConfig;

    public List<Forex> getDocument() {

        System.out.println("[GetWebDataService]-(getDocument) - Start");

        List<Forex> forexList = new ArrayList<Forex>();

        System.setProperty("webdriver.chrome.driver", forexConfig.getWebDriverPath());
        // 隱藏爬蟲彈出的瀏覽器
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");

        WebDriver driver = new ChromeDriver(options);
        driver.get(forexConfig.getWebDriverUrl());

        // 定位到下拉選單
        WebElement provinceDropdown = driver.findElement(By.id("currency"));

        // 建立Select物件
        Select select = new Select(provinceDropdown);

        // 取得所有下拉選項
        List<WebElement> selectOptions = select.getOptions();

        // 遍歷所有下拉選項，並取得其數據
        for (WebElement option : selectOptions) {

            String currency = option.getAttribute("value");
            System.out.println("[GetWebDataService]-(getDocument) - 正在取得" +currency + "匯率資料");

            // 下拉選項有請選擇，過濾掉
            if (null != currency && !currency.isBlank()) {

                // 取得下拉選單中value的值，用來切換不同的下拉選項。
                select.selectByValue(currency);

                // 執行JS腳本 取得動態產生的資料
                JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
                String script =
                        "var datarateElements = document.getElementsByClassName('datarate');" +
                        "var result = [];" +
                        "for (var i = 0; i < datarateElements.length; i++) {" +
                        "    var datarateElement = datarateElements[i];"+
                        "    var centerElement = datarateElement.querySelector('.table-text-center');" +
                        "    var rightElements = datarateElement.querySelectorAll('.table-text-right');" +
                        "    var data = {" +
                        "        date: centerElement.textContent ," +
                        "        buy: rightElements[0].textContent," +
                        "        sell: rightElements[1].textContent" +
                        "    };" +
                        "    result.push(data);" +
                        "}" +
                        "return result;";


                //取得擷取資料
                List<ForexDetail> forexDetailList = (List<ForexDetail>) jsExecutor.executeScript(script);

                Gson gson = new Gson();
                String json = gson.toJson(forexDetailList);

                Forex forex = new Forex();
                forex.setCurrency(currency);
                forex.setForexDetailListJson(json);
                forexList.add(forex);

                System.out.println("[GetWebDataService]-(getDocument) - 取得的資料：" + forex);
                System.out.println("數量:" + forexDetailList.size());
            }

        }

        System.out.println("[GetWebDataService]-(getDocument) - end");
        return forexList;
    }

}
