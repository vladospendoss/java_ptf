package ru.stqa.pft.wombat_admin.tests;

import org.testng.annotations.Test;

import static org.testng.Assert.fail;

public class GradeCreationTest extends TestBase {

        @Test
        public void testGradeCreationTest() throws InterruptedException {
            Thread.sleep(5000);
            app.goTo().gradePage();
        }
    }
//            driver.get("http://0.0.0.0:3000/skills/all-skills");
//            driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Сотрудники'])[1]/following::span[1]")).click();
//            driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Скиллы'])[1]/following::p[1]")).click();
//            driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='показано 10 из 1962'])[1]/following::p[1]")).click();
//            driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Название скилла'])[1]/following::input[1]")).clear();
//            driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Название скилла'])[1]/following::input[1]")).sendKeys("rtey");
//            driver.findElement(By.name("experience")).click();
//            driver.findElement(By.name("experience")).clear();
//            driver.findElement(By.name("experience")).sendKeys("24");
//            driver.findElement(By.name("description")).click();
//            driver.findElement(By.name("description")).clear();
//            driver.findElement(By.name("description")).sendKeys("rthr");
//            driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='rthr'])[1]/following::input[1]")).click();
//            driver.findElement(By.name("search")).clear();
//            driver.findElement(By.name("search")).sendKeys("1");
//            driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Поиск по названию'])[1]/following::li[1]")).click();
//            driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Поиск по названию'])[1]/following::button[1]")).click();
//        }
//
//        @AfterClass(alwaysRun = true)
//        public void tearDown() {
//            driver.quit();
//            String verificationErrorString = verificationErrors.toString();
//            if (!"".equals(verificationErrorString)) {
//                fail(verificationErrorString);
//            }
//        }
//
//        private boolean isElementPresent(By by) {
//            try {
//                driver.findElement(by);
//                return true;
//            } catch (NoSuchElementException e) {
//                return false;
//            }
//        }
//
//        private boolean isAlertPresent() {
//            try {
//                driver.switchTo().alert();
//                return true;
//            } catch (NoAlertPresentException e) {
//                return false;
//            }
//        }
//
//        private String closeAlertAndGetItsText() {
//            try {
//                Alert alert = driver.switchTo().alert();
//                String alertText = alert.getText();
//                if (acceptNextAlert) {
//                    alert.accept();
//                } else {
//                    alert.dismiss();
//                }
//                return alertText;
//            } finally {
//                acceptNextAlert = true;
//            }
//        }
//    }
//}
//
