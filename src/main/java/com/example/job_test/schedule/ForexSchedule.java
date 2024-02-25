package com.example.job_test.schedule;

import com.example.job_test.service.ForexService;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ForexSchedule {

    @Autowired
    private ForexService forexService;

    public void getForex() {
        System.out.println("[ForexSchedule]-(getForex) - Start");

        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Google\\Chrome\\Application\\chromedriver.exe");
        // 隱藏爬蟲彈出的瀏覽器，測試時先關掉。確保爬蟲的流程沒問題。
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");

        WebDriver driver = new ChromeDriver(options);
        driver.get("https://www.esunbank.com/zh-tw/personal/deposit/rate/forex/exchange-rate-chart?Currency=USD/TWD");

        try {

            forexService.getDocument(driver);

            System.out.println("[ForexSchedule]-(getForex) - end");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }

    }
}
