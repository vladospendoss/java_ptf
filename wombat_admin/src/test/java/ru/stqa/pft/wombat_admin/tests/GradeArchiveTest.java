package ru.stqa.pft.wombat_admin.tests;

import org.testng.annotations.Test;

public class GradeArchiveTest extends TestBase {

    @Test
    public void testArchiveGrade() {
        app.goTo().gradePage();
    }

    @Test
    public void testReturnFromArchiveGrade() {
        app.goTo().gradePage();
    }

}
