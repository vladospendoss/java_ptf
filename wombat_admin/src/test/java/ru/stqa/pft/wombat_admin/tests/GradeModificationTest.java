package ru.stqa.pft.wombat_admin.tests;

import org.testng.annotations.Test;

public class GradeModificationTest extends TestBase {

    @Test
    public void testGradeModify() {
        app.goTo().gradePage();
        app.grade().goToModify();
        fillGradeForm();
        addQuest();
        app.grade().submit();
        app.grade().closeGradeModal();
        app.check().accessModifyAlert();
        app.grade().assertModifyGrade();
    }

    @Test
    public void testGradeModifyWithNotUniqueIndex(){
        app.goTo().createGrade();
        app.grade().goToModify();
        fillGradeForm();
        app.grade().submit();
        app.check().indexNotUniqueError();
        app.grade().closeGradeModal();
        app.grade().assertModifyGrade();
    }

    @Test
    public void testAddQuestGradeModify(){
        app.goTo().createGrade();
        app.grade().goToModify();

    }

    @Test
    public void testDeleteQuestGradeModify(){
        app.goTo().createGrade();
        app.grade().goToModify();

    }

}
