package ru.stqa.pft.wombat_admin.tests;

import org.testng.annotations.Test;

public class GradeCreationTest extends TestBase {

    @Test
    public void testGradeCreation() {
        app.goTo().gradePage();
        app.grade().creationGrade();
        app.grade().assertAddGrade();
    }

}
