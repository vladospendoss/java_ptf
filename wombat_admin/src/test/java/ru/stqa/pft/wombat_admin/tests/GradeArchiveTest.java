package ru.stqa.pft.wombat_admin.tests;

import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.model.SeverityLevel;

public class GradeArchiveTest extends TestBase {

    @Test (priority = 1)
    @Severity(SeverityLevel.BLOCKER)
    public void testArchiveGrade() {
        app.goTo().gradePage();
        app.grade().openModalNotArchivedGrade();
        app.grade().acceptArchiveGrade();
        app.grade().assertArchived();
    }

    @Test (priority = 2)
    @Severity(SeverityLevel.BLOCKER)
    public void testReturnFromArchiveGrade() {
        app.goTo().gradePage();
        app.grade().openModalArchivedGrade();
        app.grade().acceptArchiveGrade();
        app.grade().assertUnArchived();
    }

    @Test (priority = 3)
    @Severity(SeverityLevel.CRITICAL)
    public void testCancelArchiveGrade() {
        app.goTo().gradePage();
        app.grade().openModalNotArchivedGrade();
        app.grade().cancelArchiveGrade();
        app.grade().assertUnArchived();
    }

    @Test (priority = 4)
    @Severity(SeverityLevel.CRITICAL)
    public void testCancelReturnFromArchiveGrade() {
        app.goTo().gradePage();
        app.grade().openModalArchivedGrade();
        app.grade().cancelArchiveGrade();
        app.grade().assertArchived();
    }

    @Test (priority = 5)
    @Severity(SeverityLevel.CRITICAL)
    public void testNoEditIconFromArchived() {
        app.goTo().gradePage();
        app.grade().openModalArchivedGrade();
        app.grade().acceptArchiveGrade();
        app.grade().assertNoEditIcon();
    }

    @Test (priority = 6)
    @Severity(SeverityLevel.CRITICAL)
    public void testEditIconAfterAddArchive() {
        app.goTo().gradePage();
        app.grade().openModalArchivedGrade();
        app.grade().acceptArchiveGrade();
        app.grade().assertEditIcon();
    }

    @Test (priority = 7)
    @Severity(SeverityLevel.MINOR)
    public void testAccessArchiveAlert() {
        app.goTo().gradePage();
        app.grade().openModalNotArchivedGrade();
        app.grade().acceptArchiveGrade();
        app.check().accessArchiveAlert();
    }

    @Test (priority = 8)
    @Severity(SeverityLevel.MINOR)
    public void testReturnFromArchiveGradeAlert() {
        app.goTo().gradePage();
        app.grade().openModalArchivedGrade();
        app.grade().acceptArchiveGrade();
        app.check().accessModifyAlert();

    }

    @Test (priority = 9)
    @Severity(SeverityLevel.NORMAL)
    public void testInabilityToOpenCardAfterArchive() {
        app.goTo().gradePage();
        app.grade().openModalNotArchivedGrade();
        app.grade().acceptArchiveGrade();
        app.grade().openCardAfterArchive();
    }

    @Test (priority = 10)
    @Severity(SeverityLevel.CRITICAL)
    public void testOpenCardAfterReturnArchive() {
        app.goTo().gradePage();
        app.grade().openModalArchivedGrade();
        app.grade().acceptArchiveGrade();
        app.grade().openCardAfterReturnArchived();

    }

    @Test (priority = 11)
    @Severity(SeverityLevel.TRIVIAL)
    public void testTextInModalUnArchived() {
        app.goTo().gradePage();
        app.grade().openModalNotArchivedGrade();
        app.check().textUnArchivedModal();
    }

    @Test (priority = 12)
    @Severity(SeverityLevel.TRIVIAL)
    public void testTextInModalArchived() {
        app.goTo().gradePage();
        app.grade().openModalArchivedGrade();
        app.check().textArchivedModal();
    }




}
