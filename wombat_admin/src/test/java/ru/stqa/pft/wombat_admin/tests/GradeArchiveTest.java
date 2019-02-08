package ru.stqa.pft.wombat_admin.tests;

import org.testng.annotations.Test;

public class GradeArchiveTest extends TestBase {

    @Test (priority = 1)
    public void testArchiveGrade() {
        app.goTo().gradePage();
        app.grade().openModalNotArchivedGrade();
        app.grade().acceptArchiveGrade();
        app.grade().assertArchivedGrade();
    }

    @Test (priority = 2)
    public void testReturnFromArchiveGrade() {
        app.goTo().gradePage();
        app.grade().openModalArchivedGrade();
        app.grade().assertArchivedGrade();
        app.grade().assertUnArchived();
    }

    @Test
    public void testCancelArchiveGrade() {
        app.goTo().gradePage();
        app.grade().openModalNotArchivedGrade();
        app.grade().cancelArchiveGrade();
        app.grade().assertUnArchived();
    }

    @Test
    public void testCancelReturnFromArchiveGrade() {
        app.goTo().gradePage();
        app.grade().openModalNotArchivedGrade();
        app.grade().cancelArchiveGrade();
        app.grade().assertArchivedGrade();
    }

    @Test
    public void testArchiveGradeAlert() {
        app.goTo().gradePage();
        app.grade().openModalNotArchivedGrade();
        app.grade().acceptArchiveGrade();
        app.check().accessArchiveAlert();
    }

    @Test
    public void testReturnFromArchiveGradeAlert() {
        app.goTo().gradePage();
        app.grade().openModalArchivedGrade();
        app.grade().acceptArchiveGrade();
        app.check().accessModifyAlert();

    }



}
