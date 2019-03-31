package ru.stqa.pft.wombat_admin.appmanager;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ErrorsHelper
{

    private static final String ERROR_MESSAGE = "span[class^='errorMessageSpan']";
    private static final String RIGHT_ALERT = "div[class*='rrt-text']";
    private static final String TITLE_ARCHIVE_MODAL = "div[class*='content__src-gradesBrandNew-components-ModalArchiveDialogue-']";

    public void accessCreationAlert() {
        $(RIGHT_ALERT).shouldHave(text("Добавлен новый грейд"));
    }

    public void indexNotUniqueAlert(){ $(RIGHT_ALERT).shouldHave(text("Ошибка при создании грейда: Грейд с таким индексом уже существует")); }

    public void accessArchiveAlert(){
        $(RIGHT_ALERT).shouldHave(text("Грейд отправлен в архив"));
    }

    public void accessModifyAlert(){
        $(RIGHT_ALERT).shouldHave(text("Внесены изменения в грейд"));
    }

    public void questDeleteError() {
        $(RIGHT_ALERT).shouldHave(text("Удален квест из грейда"));
    }

    public void indexEmptyError() { $$(ERROR_MESSAGE).findBy(text("Индекс грейда: В поле вводятся положительные целые числа больше 0")); }

    public void indexOnlyNumberError(){ $$(ERROR_MESSAGE).findBy(text("Индекс грейда: В поле вводятся только числа")); }

    public void nameEmptyError() { $$(ERROR_MESSAGE).findBy(text("Поле \"Название\" не может содержать только пробелы")); }

    public void moneyEmptyError() {
        $$(ERROR_MESSAGE).findBy(text("+ Зарплата: В поле вводятся только числа"));
    }

    public void pointEmptyError() { $$(ERROR_MESSAGE).findBy(text("Поле \"Описание\" не может содержать только пробелы")); }

    public void descriptionEmptyError() {
        $$(ERROR_MESSAGE).findBy(text("Очки: В поле вводятся только числа"));
    }

    public void indexRequeredError() {
        $$(ERROR_MESSAGE).findBy(text("Индекс грейда обязателен"));
    }

    public void nameRequeredError() {
        $$(ERROR_MESSAGE).findBy(text("Название грейда обязателено"));
    }

    public void moneyRequeredError() {
        $$(ERROR_MESSAGE).findBy(text("+ Зарплата: Значение должно быть заполнено"));
    }

    public void pointRequeredError() {
        $$(ERROR_MESSAGE).findBy(text("Очки: В поле вводятся только числа"));
    }

    public void descriptionRequeredError() {
        $$(ERROR_MESSAGE).findBy(text("Описание грейда обязательно"));
    }

    public void nameSpaceError(){ $$(ERROR_MESSAGE).findBy(text("Поле \"Название\" не может содержать только пробелы")); }

    public void descriptionSpaceError(){ $$(ERROR_MESSAGE).findBy(text("Поле \"Описание\" не может содержать только пробелы")); }

    public void textUnArchivedModal(){ $(TITLE_ARCHIVE_MODAL).shouldHave(text("Отправить грейд в архив?")); }

    public void textArchivedModal(){ $(TITLE_ARCHIVE_MODAL).shouldHave(text("Восстановить грейд из архива?")); }


    public void clickToSeeRequiredErrors() {
            $(By.cssSelector("input[placeholder*='1']")).waitUntil(visible,5000).click();
            $(By.cssSelector("input[placeholder*='Название']")).click();
            $(By.cssSelector("input[placeholder^='+ 10000']")).click();
            $(By.cssSelector("input[placeholder^='10000']")).click();
            $(By.cssSelector("textarea[placeholder^='Описание']")).waitUntil(visible,5000).click();
            $(By.cssSelector("input[placeholder^='1']")).click();
    }


}

