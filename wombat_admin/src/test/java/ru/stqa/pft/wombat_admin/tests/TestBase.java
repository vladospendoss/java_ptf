package ru.stqa.pft.wombat_admin.tests;

import com.codeborne.selenide.Condition;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.wombat_admin.appmanager.ApplicationManager;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class TestBase {

    protected static final ApplicationManager app = new ApplicationManager();

    //заполнение полей
    protected void fillGradeForm() {
        app.grade().setIndex();
        app.grade().setName();
        app.grade().setMoney();
        app.grade().setPoint();
        app.grade().setDescription();
    }

    //добавление квеста
    protected void addQuest() {
        app.grade().goToAddQuest();
        app.grade().openQuestGroup();
        app.grade().getQuestName();
        app.grade().selectQuest();
        app.grade().getCountQuestAndCloseQuestModal();
    }

    //Проверка ошибок
    protected void checkAllEmptyErrors() {
        app.check().indexEmptyError();
        app.check().nameEmptyError();
        app.check().moneyEmptyError();
        app.check().pointEmptyError();
        app.check().descriptionEmptyError();
    }

    protected int getCountGrade() {
        $("(div[class^='grade__src-gradesBrandNew-components-GradeBaseInfo-']").
                waitUntil(Condition.visible,10000);
        int grades = $$("(div[class^='grade__src-gradesBrandNew-components-GradeBaseInfo-']").size();
        return grades;
    }


    @BeforeSuite
    public void setUp() { app.init(); }

}