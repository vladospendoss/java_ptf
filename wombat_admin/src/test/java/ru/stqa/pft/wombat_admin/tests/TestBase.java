package ru.stqa.pft.wombat_admin.tests;

import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.wombat_admin.appmanager.ApplicationManager;

public class TestBase {

    protected static final ApplicationManager app = new ApplicationManager();

    @BeforeSuite
    public void setUp() { app.init(); }

}