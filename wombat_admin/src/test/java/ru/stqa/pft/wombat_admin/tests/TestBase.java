package ru.stqa.pft.wombat_admin.tests;

import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.wombat_admin.appmanager.ApplicationManager;

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

    protected void fillGradeFormWithNotUniqueIndex() {
        app.grade().setNotUniqueIndex();
        app.grade().setName();
        app.grade().setMoney();
        app.grade().setPoint();
        app.grade().setDescription();
    }

    //добавление квеста
    protected void addQuest() {
        app.grade().goToAddQuest();
        app.grade().openQuestGroup();
        app.grade().selectQuest();
        app.grade().findFirstQuestName();
        app.grade().getCountQuestAndCloseQuestModal();
    }

    protected void addQuestsFromDifferentGroups() {
        app.grade().goToAddQuest();
        app.grade().openFirstQuestGroup();
        app.grade().selectQuest();
        app.grade().findFirstQuestName();
        app.grade().openSecondQuestGroup();
        app.grade().selectQuest();
        app.grade().findSecondQuestName();
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

    protected  void checkAllRequiredErrors(){
        app.check().clickToSeeRequiredErrors();
        app.check().indexRequeredError();
        app.check().nameRequeredError();
        app.check().moneyRequeredError();
        app.check().pointRequeredError();
        app.check().descriptionRequeredError();
    }

    protected  void checkAllOnlyNumberErrors(){
        app.check().indexOnlyNumberError();
        app.check().moneyEmptyError();
        app.check().pointEmptyError();
    }

    protected  void checkSpacesInStrigFieldErrors(){
        app.check().nameSpaceError();
        app.check().descriptionSpaceError();
    }

    @BeforeSuite
    public void setUp() { app.init(); }

}