package ru.stqa.pft.wombat_admin.tests;

import org.testng.annotations.Test;

public class GradeArchiveTest extends TestBase {

    //не только лишь все эти тесты работают, не только лишь все могут работать

    @Test (priority = 1)
    public void testArchiveGrade() {
        app.goTo().gradePage();
        app.grade().openModalNotArchivedGrade();
        app.grade().acceptArchiveGrade();
        app.grade().assertArchived();
    }

    @Test (priority = 2)
    public void testReturnFromArchiveGrade() {
        app.goTo().gradePage();
        app.grade().openModalArchivedGrade();
        app.grade().acceptArchiveGrade();
        app.grade().assertUnArchived();
    }

    @Test (priority = 3)
    public void testCancelArchiveGrade() {
        app.goTo().gradePage();
        app.grade().openModalNotArchivedGrade();
        app.grade().cancelArchiveGrade();
        app.grade().assertUnArchived();
    }

    @Test (priority = 4)
    public void testCancelReturnFromArchiveGrade() {
        app.goTo().gradePage();
        app.grade().openModalArchivedGrade();
        app.grade().cancelArchiveGrade();
        app.grade().assertArchived();
    }

    @Test (priority = 5)
    public void testNoEditIconFromArchived() {
        app.goTo().gradePage();
        app.grade().openModalArchivedGrade();
        app.grade().acceptArchiveGrade();
        app.grade().assertNoEditIcon();
    }

    @Test (priority = 6)
    public void testEditIconAfterAddArchive() {
        app.goTo().gradePage();
        app.grade().openModalArchivedGrade();
        app.grade().acceptArchiveGrade();
        app.grade().assertEditIcon();
    }

    @Test (priority = 7)
    public void testEditIconAfterReturnArchive() {
        app.goTo().gradePage();
        app.grade().openModalNotArchivedGrade();
        app.grade().acceptArchiveGrade();
        app.check().accessArchiveAlert();
    }

    @Test (priority = 8)
    public void testReturnFromArchiveGradeAlert() {
        app.goTo().gradePage();
        app.grade().openModalArchivedGrade();
        app.grade().acceptArchiveGrade();
        app.check().accessModifyAlert();

    }

    //этот точно падает

    @Test (priority = 9)
    public void testInabilityToOpenCardAfterArchive() {
        app.goTo().gradePage();
        app.grade().openModalNotArchivedGrade();
        app.grade().acceptArchiveGrade();
        app.grade().openCardAfterArchive();
    }

    @Test (priority = 10)
    public void testOpenCardAfterReturnArchive() {
        app.goTo().gradePage();
        app.grade().openModalArchivedGrade();
        app.grade().acceptArchiveGrade();
        app.grade().openCardAfterReturnArchived();

    }

    @Test (priority = 11)
    public void testTextInModalUnArchived() {
        app.goTo().gradePage();
        app.grade().openModalNotArchivedGrade();
        app.check().textUnArchivedModal();
    }

    @Test (priority = 12)
    public void testTextInModalArchived() {
        app.goTo().gradePage();
        app.grade().openModalArchivedGrade();
        app.check().textArchivedModal();
    }




}
