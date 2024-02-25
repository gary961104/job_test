package com.example.job_test.service;

import com.google.gson.Gson;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ForexService {

    public void getDocument(WebDriver driver) {

        System.out.println("[GetWebDataService]-(getDocument) - Start");

        // 定位到下拉選單
        WebElement provinceDropdown = driver.findElement(By.id("currency"));

        // 建立Select物件
        Select select = new Select(provinceDropdown);

        // 取得所有選項
        List<WebElement> options = select.getOptions();

        // 遍歷所有下拉選項，並取得其數據
        for (WebElement option : options) {

            String currency = option.getAttribute("value");
            System.out.println("[GetWebDataService]-(getDocument) - 正在取得" +currency + "匯率資料");

            // 下拉選項有請選擇，過濾掉
            if (null != currency && !currency.isBlank()) {

                // 取得下拉選單中value的值，用來切換不同的下拉選項。
                select.selectByValue(currency);

                // 執行JS腳本 取得動態產生的資料
                JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
                String script = "var elements = document.getElementsByClassName('table-text-right');" +
                        "var result = [];" +
                        "for (var i = 0; i < elements.length; i++) {" +
                        "    result.push(elements[i].innerText);" +
                        "}" +
                        "return result;";

                //取得擷取資料
                List<String> dataList = (List<String>) jsExecutor.executeScript(script);

                Gson gson = new Gson();

                // 將資料轉換成Json
                String jsonData = gson.toJson(dataList);

                System.out.println("[GetWebDataService]-(getDocument) - JSON 格式的資料：" + jsonData);
            }

        }

        System.out.println("[GetWebDataService]-(getDocument) - end");
    }

}
