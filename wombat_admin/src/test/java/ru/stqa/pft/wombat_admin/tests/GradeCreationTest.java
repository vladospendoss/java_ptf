package ru.stqa.pft.wombat_admin.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.model.SeverityLevel;

import static com.codeborne.selenide.Selenide.sleep;

public class GradeCreationTest extends TestBase {

    @Test(priority = 1)
    @Severity(SeverityLevel.CRITICAL)
    public void testGradeCreationWithoutQuest() {
        app.goTo().gradePage();
        int before = app.grade().getSizeList();
        app.grade().findMaxIndex();
        app.goTo().createGrade();
        fillGradeForm();
        app.grade().submit();
        app.grade().closeGradeModal();
        int after = app.grade().getSizeList();
        Assert.assertEquals(before + 1, after);
        app.grade().assertAddGrade();
    }

    @Test(priority = 2)
    @Severity(SeverityLevel.BLOCKER)
    public void testGradeCreationWithQuest() {
        app.goTo().gradePage();
        app.grade().findMaxIndex();
        app.goTo().createGrade();
        fillGradeForm();
        addQuest();
        app.grade().submit();
        sleep(2000);
        app.grade().closeGradeModal();
        app.grade().assertQuestFromGrade();
    }

    @Test(priority = 3)
    @Severity(SeverityLevel.CRITICAL)
    public void testGradeCreationWithQuestsFromDifferentGroups() {
        app.goTo().gradePage();
        app.grade().findMaxIndex();
        app.goTo().createGrade();
        fillGradeForm();
        addQuestsFromDifferentGroups();
        app.grade().submit();
        sleep(2000);
        app.grade().closeGradeModal();
        app.grade().assertQuestFromDifferentGroupFromGrade();
    }

    @Test(priority = 4)
    @Severity(SeverityLevel.CRITICAL)
    public void testGradeCreationWithNotUniqueIndex(){
        app.goTo().gradePage();
        int before = app.grade().getSizeList();
        app.goTo().createGrade();
        app.grade().findMaxIndex();
        fillGradeFormWithNotUniqueIndex();
        app.grade().submit();
        app.grade().closeGradeModal();
        int after = app.grade().getSizeList();
        Assert.assertEquals(before, after);
        app.grade().assertNotAddGrade();
    }

    @Test(priority = 5)
    @Severity(SeverityLevel.CRITICAL)
    public void testCancelGradeCreation() {
        app.goTo().gradePage();
        app.goTo().createGrade();
        app.grade().findMaxIndex();
        fillGradeForm();
        app.grade().closeGradeModal();
        app.grade().assertNotAddGrade();
    }

    @Test(priority = 6)
    @Severity(SeverityLevel.CRITICAL)
    public void testGradeCreationWithEmptyFields() {
        app.goTo().gradePage();
        int before = app.grade().getSizeList();
        app.goTo().createGrade();
        app.grade().submit();
        app.grade().closeGradeModal();
        int after = app.grade().getSizeList();
        Assert.assertEquals(before, after);
        app.grade().assertNotAddGrade();
    }


    @Test(priority = 7)
    @Severity(SeverityLevel.CRITICAL)
    public void testGradeCreationWithDataFromSpaces() {
        app.goTo().gradePage();
        int before = app.grade().getSizeList();
        app.goTo().createGrade();
        app.grade().changeDataOnSpaces();
        app.grade().submit();
        app.grade().closeGradeModal();
        int after = app.grade().getSizeList();
        Assert.assertEquals(before, after);
        app.grade().assertNotAddGrade();
    }

    @Test(priority = 8)
    @Severity(SeverityLevel.CRITICAL)
    public void testGradeCreationWithNegativeValues() {
        app.goTo().gradePage();
        int before = app.grade().getSizeList();
        app.goTo().createGrade();
        app.grade().changeDataOnNegativeValues();
        app.grade().submit();
        app.grade().closeGradeModal();
        int after = app.grade().getSizeList();
        Assert.assertEquals(before, after);
        app.grade().assertNotAddGrade();
    }

    @Test(priority = 9)
    @Severity(SeverityLevel.CRITICAL)
    public void testGradeCreationWithZeroValues() {
        app.goTo().gradePage();
        int before = app.grade().getSizeList();
        app.goTo().createGrade();
        app.grade().changeDataOnZero();
        app.grade().submit();
        app.grade().closeGradeModal();
        int after = app.grade().getSizeList();
        Assert.assertEquals(before, after);
        app.grade().assertNotAddGrade();
    }

    @Test(priority = 10)
    @Severity(SeverityLevel.CRITICAL)
    public void testGradeCreationWithLetterInNumberField() {
        app.goTo().gradePage();
        int before = app.grade().getSizeList();
        app.goTo().createGrade();
        app.grade().changeAndSetDataToString();
        app.grade().submit();
        app.grade().closeGradeModal();
        int after = app.grade().getSizeList();
        Assert.assertEquals(before, after);
        app.grade().assertNotAddGrade();
    }


    @Test(priority = 11)
    @Severity(SeverityLevel.NORMAL)
    public void testGradeCreationBaseInfo() {
        app.goTo().gradePage();
        app.grade().findMaxIndex();
        app.goTo().createGrade();
        fillGradeForm();
        addQuest();
        app.grade().submit();
        app.grade().closeGradeModal();
        app.grade().assertBaseInfoAdd();
    }

    @Test(priority = 12)
    @Severity(SeverityLevel.NORMAL)
    public void testGradeCreationDopInfo() {
        app.goTo().gradePage();
        app.grade().findMaxIndex();
        app.goTo().createGrade();
        fillGradeForm();
        app.grade().submit();
        app.grade().closeGradeModal();
        app.grade().assertDescriptionAdd();
    }

    @Test(priority = 13)
    @Severity(SeverityLevel.CRITICAL)
    public void testGradeCreationWithOneQuest() {
        app.goTo().gradePage();
        app.grade().findMaxIndex();
        app.goTo().createGrade();
        fillGradeForm();
        addQuest();
        app.grade().submit();
        app.grade().closeGradeModal();
        app.grade().assertInfoAddGradeWithOneQuest();
    }

    @Test(priority = 14)
    @Severity(SeverityLevel.CRITICAL)
    public void testGradeCreationInfoWithQuestsFromDifferentGroups() {
        app.goTo().gradePage();
        app.grade().findMaxIndex();
        app.goTo().createGrade();
        fillGradeForm();
        addQuestsFromDifferentGroups();
        app.grade().submit();
        app.grade().closeGradeModal();
        app.grade().assertInfoAddGradeWithQuestFromDifferentGroup();
    }

    @Test(priority = 15)
    @Severity(SeverityLevel.MINOR)
    public void testNotCacheCreationAfterCancel() {
        app.goTo().gradePage();
        app.goTo().createGrade();
        fillGradeForm();
        addQuest();
        app.grade().closeGradeModal();
        app.goTo().createGrade();
        app.grade().checkEmptyField();
    }

    @Test(priority = 16)
    @Severity(SeverityLevel.MINOR)
    public void testNotCacheCreationAfterCreate() {
        app.goTo().gradePage();
        app.goTo().createGrade();
        app.grade().findMaxIndex();
        fillGradeForm();
        app.grade().submit();
        sleep(2000);
        app.grade().checkEmptyField();
    }


    @Test(priority = 17)
    @Severity(SeverityLevel.NORMAL)
    public void testGradeAlertCreation(){
        app.goTo().gradePage();
        app.grade().findMaxIndex();
        app.goTo().createGrade();
        fillGradeForm();
        app.grade().submit();
        app.check().accessCreationAlert();
    }

    @Test(priority = 18)
    @Severity(SeverityLevel.NORMAL)
    public void testGradeAlertCreationNotUnique(){
        app.goTo().gradePage();
        app.goTo().createGrade();
        fillGradeFormWithNotUniqueIndex();
        app.grade().submit();
        app.check().indexNotUniqueAlert();
    }

    @Test(priority = 19)
    @Severity(SeverityLevel.NORMAL)
    public void testGradeErrorsEmpty(){
        app.goTo().gradePage();
        app.goTo().createGrade();
        app.grade().submit();
        checkAllEmptyErrors();
    }

    @Test(priority = 20)
    @Severity(SeverityLevel.NORMAL)
    public void testGradeErrorsRequired(){
        app.goTo().gradePage();
        app.goTo().createGrade();
        checkAllRequiredErrors();
    }

    @Test(priority = 21)
    @Severity(SeverityLevel.NORMAL)
    public void testGradeErrorsOnlyNumbers(){
        app.goTo().gradePage();
        app.goTo().createGrade();
        app.grade().submit();
        app.grade().changeAndSetDataToString();
        checkAllOnlyNumberErrors();
    }

    @Test(priority = 22)
    @Severity(SeverityLevel.NORMAL)
    public void testGradeErrorsSpacesInstead(){
        app.goTo().gradePage();
        app.goTo().createGrade();
        app.grade().changeDataOnSpaces();
        checkSpacesInStrigFieldErrors();
    }

}
