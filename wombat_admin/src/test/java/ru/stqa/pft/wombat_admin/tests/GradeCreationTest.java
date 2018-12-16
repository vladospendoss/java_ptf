package ru.stqa.pft.wombat_admin.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class GradeCreationTest extends TestBase {

    @Test
    public void testGradeCreation() {
        app.goTo().gradePage();
        int before = getCountGrade();
        app.goTo().createGrade();
        fillGradeForm();
        addQuest();
        app.grade().submit();
        app.check().accessCreationAlert();
        app.grade().closeGradeModal();
        int after = getCountGrade();
        Assert.assertEquals(before + 1,after);
        app.grade().assertAddGrade();
    }

    @Test
    public void testGradeCreationWithHotUniqueIndex(){
        app.goTo().gradePage();
        int before = getCountGrade();
        app.goTo().createGrade();
        fillGradeForm();
        app.grade().submit();
        app.check().indexNotUniqueError();
        app.grade().closeGradeModal();
        int after = getCountGrade();
        Assert.assertEquals(before,after);

    }

    @Test
    public void testCancelGradeCreation() {
        app.goTo().gradePage();
        int before = getCountGrade();
        app.goTo().createGrade();
        app.grade().findIndex();
        fillGradeForm();
        addQuest();
        app.grade().closeGradeModal();
        int after = getCountGrade();
        Assert.assertEquals(before,after);
    }

    @Test
    public void testGradeCreationWithEmptyField(){
        app.goTo().gradePage();
        int before = getCountGrade();
        app.goTo().createGrade();
        app.grade().submit();
        checkAllEmptyErrors();
        app.grade().closeGradeModal();
        int after = getCountGrade();
        Assert.assertEquals(before,after);
    }

}
