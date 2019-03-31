package ru.stqa.pft.wombat_admin.tests;

import org.testng.annotations.Test;

public class GradeModificationTest extends TestBase {

    @Test
    public void testGradeModify() {
        app.goTo().gradePage();
        app.grade().findMaxIndex();
        app.grade().goToModify();
        fillGradeForm();
        app.grade().submit();
        app.check().accessModifyAlert();
        app.grade().assertAddGrade();
    }

    @Test
    public void testGradeModifyWithNotUniqueIndex(){
        app.goTo().createGrade();
        app.grade().goToModify();
        fillGradeForm();
        app.grade().submit();
        app.check().indexNotUniqueAlert();
        app.grade().closeGradeModal();
        app.grade().assertAddGrade();
    }

    @Test
    public void testDeleteQuestGradeModify(){
        app.goTo().createGrade();
        app.grade().goToModify();

    }

}
