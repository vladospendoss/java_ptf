package ru.stqa.pft.wombat_admin.appmanager;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class GradeHelper {

    private Integer lastUpperGrade = -1;
    private String index;
    private String name;
    private String money = "12345";
    private String description = "декспиршн";
    private String point = "123";
    private String questName = null;
    private String countQuest = null;

    public void creationGrade() {
        sleep(3000);
        getIndex();
        goToCreate();
        index();
        name();
        money();
        point();
        description();
        goToAddQuest();
        openQuestGroup();
        getQuestName();
        selectQuest();
        getCountQuest();
        addSelectedQuest();
        submitCreation();
        closeCreationModal();
    }

    public void modifyGrade() {
        sleep(5000);
        getIndex();
        goToModify();
    }

    private void goToModify() {
        $$("div[class^='grade__src-gradesBrandNew-components-GradeBaseInfo-']").findBy(text(index)).scrollTo().hover();
    }

    private int getNumberFromGrade(String grade){ return Integer.valueOf(grade.split("G")[1]); }

    public void getIndex () {
        sleep(3000);
        ElementsCollection grades = $$("div[class^='grade__src-gradesBrandNew-components-GradeBaseInfo-']");
        for (SelenideElement grade : grades) {
            int nextGrade = getNumberFromGrade(grade.getText());
            if (lastUpperGrade < nextGrade) {
                lastUpperGrade = nextGrade;
            }
        }
        System.out.println(lastUpperGrade);
    }

    public void goToCreate() { $(By.cssSelector("div[class*='AddGradeButton']")).click();}

    public void index() {
        index = String.valueOf(lastUpperGrade + 1);
        $(By.cssSelector("input[placeholder*='12']")).setValue(index); }

    public void name() {
        name = "Grade " + String.valueOf(lastUpperGrade + 1);
        $(By.cssSelector("input[placeholder*='Название']")).setValue(name); }

    public void money() { $(By.cssSelector("input[placeholder^='+']")).setValue(money); }

    public void point() { $(By.cssSelector("input[placeholder^='10000']")).setValue(point); }

    public void description() { $(By.cssSelector("textarea[placeholder*='Описание']")).setValue(description); }

    public void submitCreation() { $(By.cssSelector("button[type^='submit']")).click(); }

    public void goToAddQuest() { $(By.cssSelector("button[type^='button']")).click();}

    public void closeCreationModal() { $(byText("Отмена")).waitUntil(Condition.enabled, 5000).click(); }

    public void openQuestGroup() { $(By.cssSelector("img[alt^='Open']")).click();}

    public void getQuestName() {
        questName = $("div[class^='itemName__src-gradesBrandNew-views-Grades-containers-GradesQuestsModal-']").
                waitUntil(visible,2000).getText(); }

    public void getCountQuest() {
        countQuest = $("span[class^='numberOfQuests__src-gradesBrandNew-views-Grades-containers-GradesQuestsModal-']").
                waitUntil(visible,2000).getText(); }

    public void selectQuest() { $(By.cssSelector("input[value^='on']")).click();}

    public void addSelectedQuest() { $(By.className("modalButton__src-gradesBrandNew-views-Grades-containers-GradesQuestsModal-__2_W")).click(); }

    public void assertAddGrade() {
        sleep(3000);
        $$("div[class^='grade__src-gradesBrandNew-components-GradeBaseInfo-']").findBy(text(index)).click();
        $$("div[class^='grade__src-gradesBrandNew-components-GradeBaseInfo-']").findBy(text("G"+index));
        $$("div[class^='gradeName__src-gradesBrandNew-components-GradeBaseInfo-']").findBy(text(name));
        $$("div[class^='gradeSalary__src-gradesBrandNew-components-GradeBaseInfo-']").findBy(text(money));
        $$("div[class^='gradeRequiredQuests__src-gradesBrandNew-components-GradeBaseInfo-']").findBy(text(countQuest));
        $( "div[class^='infoText__src-gradesBrandNew-components-GradeUsualInfo-']" ).shouldHave(text(description));
        $("span[class^='questName__src-gradesBrandNew-views-Grades-containers-GradeItemRoutes-']").
                waitUntil(visible, 3000).shouldHave(text(questName));
    }


//    public void assertAddGrade(){
//        sleep(3000);
//        ElementsCollection grades = $$("div[class^='grade__src-gradesBrandNew-components-GradeBaseInfo-");
//        int currentNumber = getNumberFromGrade(grades.get(grades.size() - 1).getText());
//    }

}




