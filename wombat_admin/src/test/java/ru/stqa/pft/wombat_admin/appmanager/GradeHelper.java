package ru.stqa.pft.wombat_admin.appmanager;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ru.stqa.pft.wombat_admin.Utils;

import java.util.Random;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class GradeHelper {

    private Integer lastUpperGrade = -1;
    private String index;
    private String name;
    private String money;
    private String description = "декспиршн";
    private String point;
    private String questName;
    private String countQuest;

    public void creationGrade() {
        sleep(3000);
        findIndex();
        goToCreate();
        setIndex();
        setName();
        setMoney();
        setPoint();
        setDescription();
        goToAddQuest();
        openQuestGroup();
        getQuestName();
        selectQuest();
        getCountQuestAndCloseQuestModal();
        submitCreation();
        closeCreationModal();
    }

    public void modifyGrade() {
        sleep(5000);
        findIndex();
        goToModify();
//        setIndex();
        setName();
        setMoney();
        setPoint();
        setDescription();
//        openQuestGroup();
//        getQuestName();
//        selectQuest();
//        getCountQuestAndCloseQuestModal();
        submitCreation();
        closeCreationModal();
    }
    private void goToModify() {
        index = String.valueOf(lastUpperGrade);
        $$("div[class^='wrapper__src-gradesBrandNew-views-Grades-containers-GradeItemRoutes-']")
                .findBy(text("G"+index)).scrollTo().hover()
                .find("img[alt^='Edit']").click();
    }

    private int getNumberFromGrade(String grade){ return Integer.valueOf(grade.split("G")[1]); }

    public void findIndex() {
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

    public void setIndex() {
        index = String.valueOf(lastUpperGrade + 1);
        $(By.cssSelector("input[placeholder*='12']")).setValue(index); }

    public void setName() {
        name = "Grade " + String.valueOf(lastUpperGrade + 1);
        $(By.cssSelector("input[placeholder*='Название']")).setValue(name); }

    public void setMoney() {
        Random rand = new Random();
        int number = rand.nextInt(1000);
        $(By.cssSelector("input[placeholder^='+']")).setValue(String.valueOf(number));
    }

    public void setPoint() {
        Random rand = new Random();
        point = String.valueOf(rand.nextInt(100));
        $(By.cssSelector("input[placeholder^='10000']")).setValue(point);
    }

    public void setDescription() { $(By.cssSelector("textarea[placeholder*='Описание']")).setValue(description); }

    public void submitCreation() { $(byText("Сохранить")). scrollTo().waitUntil(visible, 5000).click(); }

    public void goToAddQuest() { $(byText("+ Добавить квест")).click();}

    public void closeCreationModal() { $(byText("Отмена")).scrollTo().waitUntil(visible, 10000).click(); }

    public void openQuestGroup() { $(By.cssSelector("img[alt^='Open']")).scrollTo().click();}

    public void getQuestName() {
        questName = $("div[class^='itemName__src-gradesBrandNew-views-Grades-containers-GradesQuestsModal-']").
                waitUntil(visible,2000).getText(); }

    public void getCountQuestAndCloseQuestModal() {
        boolean image = $("span[class^='numberOfQuests__src-gradesBrandNew-views-Grades-containers-GradesQuestsModal-']").isDisplayed();
        if (image){
        countQuest = $("span[class^='numberOfQuests__src-gradesBrandNew-views-Grades-containers-GradesQuestsModal-']").
                waitUntil(visible,2000).getText();
            $(By.className("modalButton__src-gradesBrandNew-views-Grades-containers-GradesQuestsModal-__2_W")).click();
        }
        else { countQuest = "0"; $("button[type^='reset']").click(); }
        }

    public void selectQuest() { $(By.cssSelector("input[value^='on']")).click();}

    public void assertAddGrade() {
        sleep(3000);
        $$("div[class^='grade__src-gradesBrandNew-components-GradeBaseInfo-']").findBy(text(index));
        $$("div[class^='grade__src-gradesBrandNew-components-GradeBaseInfo-']").findBy(text("G"+index));
        $$("div[class^='gradeName__src-gradesBrandNew-components-GradeBaseInfo-']").findBy(text(name));
        $$("div[class^='gradeSalary__src-gradesBrandNew-components-GradeBaseInfo-']").findBy(text(money));
        $$("div[class^='gradeRequiredQuests__src-gradesBrandNew-components-GradeBaseInfo-']").findBy(text(countQuest));
        $$("div[class^='gradeAlternativeQuests__src-gradesBrandNew-components-GradeBaseInfo-']").findBy(text(point));
//        $( "div[class^='infoText__src-gradesBrandNew-components-GradeUsualInfo-']" ).shouldHave(text(description));
//        $("span[class^='questName__src-gradesBrandNew-views-Grades-containers-GradeItemRoutes-']").
//                waitUntil(visible, 3000).shouldHave(text(questName));
    }

    public void assertModifyGrade() {
        sleep(5000);
        closeCreationModal();
        $$("div[class^='grade__src-gradesBrandNew-components-GradeBaseInfo-']").findBy(text(index)).click();
        $$("div[class^='grade__src-gradesBrandNew-components-GradeBaseInfo-']").findBy(text("G" + Utils.incrementAndReturnString(index)));
        $$("div[class^='gradeName__src-gradesBrandNew-components-GradeBaseInfo-']").findBy(text(Utils.incrementAndReturnString(Utils.getNumberFromGradeString(name))));
        $$("div[class^='gradeSalary__src-gradesBrandNew-components-GradeBaseInfo-']").findBy(text(money));
        $$("div[class^='gradeRequiredQuests__src-gradesBrandNew-components-GradeBaseInfo-']").findBy(text(countQuest));
        $$("div[class^='gradeAlternativeQuests__src-gradesBrandNew-components-GradeBaseInfo-']").findBy(text(point));
        $( "div[class^='infoText__src-gradesBrandNew-components-GradeUsualInfo-']" ).shouldHave(text(description));
       /* $("span[class^='questName__src-gradesBrandNew-views-Grades-containers-GradeItemRoutes-']").
                waitUntil(visible, 3000).shouldHave(text(questName));*/
    }
}




