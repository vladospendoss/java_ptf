package ru.stqa.pft.wombat_admin.tests;

import org.testng.annotations.Test;

public class GradeModificationTest extends TestBase {

    @Test
    public void testGradeModify() {
        app.goTo().gradePage();
        app.grade().modifyGrade();
        app.grade().assertModifyGrade();
    }
}
