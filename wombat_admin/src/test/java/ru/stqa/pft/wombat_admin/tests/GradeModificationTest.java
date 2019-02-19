package ru.stqa.pft.wombat_admin.tests;

import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.model.SeverityLevel;

public class GradeModificationTest extends TestBase {

    @Test
    @Severity(SeverityLevel.BLOCKER)
    public void testGradeModify() {
        app.goTo().gradePage();
        app.grade().findMaxIndex();
        app.grade().goToModify();
        fillGradeForm();
        app.grade().submit();
        app.check().accessModifyAlert();
        app.grade().assertAddGrade();
    }
}
