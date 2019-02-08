package ru.stqa.pft.wombat_admin.appmanager;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.person.Person;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.Random;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class GradeHelper {

    private Integer lastUpperGrade = 0;
    private String index;
    private String name;
    private Integer money;
    private String description;
    private int point;
    private String questFirstName;
    private String questSecondName;
    private Integer countQuest = 0;
    private String indexForArchive;

    Random rand = new Random();


    public String description() {
        Fairy fairy = Fairy.create();
        Person person = fairy.person();
        return person.firstName();
    }

    //переход к редактированию грейда с максимальным индексом
    public void goToModify() {
        index = String.valueOf(lastUpperGrade);
        $("div[class^='wrapper__src-gradesBrandNew-views-Grades-containers-GradeItemRoutes-']").
                waitUntil(visible, 10000);
        $$("div[class^='wrapper__src-gradesBrandNew-views-Grades-containers-GradeItemRoutes-']")
                .findBy(text("G" + index)).scrollTo().hover()
                .find("img[alt^='Edit']").click();
    }

    //
    private int getNumberFromGrade(String grade) {
        return Integer.valueOf(grade.split("G")[1]);
    }

    //находит грейд с максимальным индексом из всех грейдов
    public void findMaxIndex() {
        $("div[class^='grade__src-gradesBrandNew-components-GradeBaseInfo-']").
                waitUntil(visible, 20000);
        ElementsCollection grades = $$("div[class^='grade__src-gradesBrandNew-components-GradeBaseInfo-']");
        for (SelenideElement grade : grades) {
            int nextGrade = getNumberFromGrade(grade.getText());
            if (lastUpperGrade < nextGrade) {
                lastUpperGrade = nextGrade;
            }
        }
        System.out.println(lastUpperGrade);
    }

    //ввод нового уникального индекса, на 1 больше существующего индекса
    public void setIndex() {
            index = String.valueOf(lastUpperGrade + 1);
            $(By.cssSelector("input[placeholder*='1']")).setValue(index);
    }


    public void setNotUniqueIndex(){
        index = String.valueOf(lastUpperGrade);
        $(By.cssSelector("input[placeholder*='1']")).setValue(index);
    }

    //ввод нового уникального названия грейда
    public void setName() {
        String stringName = String.valueOf(lastUpperGrade + 1);
        name = "Grade " + stringName;
        $(By.cssSelector("input[placeholder*='Название']")).setValue(name);
    }

    //ввод рандомной зарплаты <99999
    public void setMoney() {
        money = rand.nextInt(100000);
        $(By.cssSelector("input[placeholder^='+']")).
                setValue(String.valueOf(money));
    }

    //ввод рандомных очков за альтернативные квесты <10000
    public void setPoint() {
        point = rand.nextInt(1000);
        $(By.cssSelector("input[placeholder^='10000']")).
                setValue(String.valueOf(point));
    }

    //ввод описания
    public void setDescription() {
       description = description();
       $(By.cssSelector("textarea[placeholder*='Описание']")).setValue(description);
    }

    //подтверждение создания или редакирования грейда
    public void submit() {
        $("button[type*='submit']").waitUntil(visible, 5000).click();
    }


    //переход к модальному окну с группами квестов при создании или редактировании квеста
    public void goToAddQuest() {
        $(byText("+ Добавить квест")).click();
    }

    //отмена создания или редакирования грейда
    public void closeGradeModal() {
        $(byText("Отмена")).waitUntil(enabled, 10000).click();}

    //разворачивание ячейки квеста
    public void openQuestGroup() { $(By.cssSelector("img[alt^='Open']")).scrollTo().click(); }

    //получаем название квеста
    public void findFirstQuestName() {
            questFirstName = $("div[class^='itemName__src-gradesBrandNew-views-Grades-containers-GradesQuestsModal-']").
                    waitUntil(visible, 2000).getText();
    }

    public void findSecondQuestName() {
        questSecondName = $("div[class^='itemName__src-gradesBrandNew-views-Grades-containers-GradesQuestsModal-']").
                waitUntil(visible, 2000).getText();
    }

    /* после выбора квеста получаем кол-во выбранных для добавления в грейд квестов
    если не выбрано ни одного, просто закрываем модальное окно */
    public void getCountQuestAndCloseQuestModal() {
        if (!$("span[class^='numberOfQuests__src-gradesBrandNew-views-Grades-containers-GradesQuestsModal-']").
                isDisplayed()) {
            $("button[type^='reset']").click();
        } else if (countQuest == 0) {
            String count = $("span[class^='numberOfQuests__src-gradesBrandNew-views-Grades-containers-GradesQuestsModal-']").
                    waitUntil(visible, 2000).getText();
            countQuest = Integer.valueOf(count);
        } else {
            String count = $("span[class^='numberOfQuests__src-gradesBrandNew-views-Grades-containers-GradesQuestsModal-']").
                    waitUntil(visible, 2000).getText();
            countQuest = countQuest + Integer.valueOf(count);
        }
        $("button[class^='modalButton__src-gradesBrandNew-views-Grades-containers-GradesQuestsModal-']").click();
    }

    //выбираем квест из раскрывшейся группы
    public void selectQuest() {
        $(By.cssSelector("input[value^='on']")).click();
    }

    public void changeDataOnSpaces() {
        String space = "  ";
        $(By.cssSelector("input[placeholder*='1']")).waitUntil(visible,5000).setValue(space);
        $(By.cssSelector("input[placeholder*='Название']")).setValue(space);
        $(By.cssSelector("input[placeholder*='+']")).setValue(space);
        $(By.cssSelector("input[placeholder*='10000']")).setValue(space);
        $(By.cssSelector("textarea[placeholder*='Описание']")).setValue(space);
    }

    public void changeDataOnNegativeValues() {
        String negative = "-1";
        $(By.cssSelector("input[placeholder*='1']")).waitUntil(visible,5000).setValue(negative);
        $(By.cssSelector("input[placeholder*='Название']")).setValue(description);
        $(By.cssSelector("input[placeholder*='+']")).setValue(negative);
        $(By.cssSelector("input[placeholder*='10000']")).setValue(negative);
        $(By.cssSelector("textarea[placeholder*='Описание']")).setValue(description);
    }

    public void changeDataOnZero() {
        String zero = "0";
        $(By.cssSelector("input[placeholder*='1']")).waitUntil(visible,5000).setValue(zero);
        $(By.cssSelector("input[placeholder*='Название']")).setValue(description);
        $(By.cssSelector("input[placeholder*='+']")).setValue(zero);
        $(By.cssSelector("input[placeholder*='10000']")).setValue(zero);
        $(By.cssSelector("textarea[placeholder*='Описание']")).setValue(description);
    }

    public void changeAndSetDataToString() {
        $(By.cssSelector("input[placeholder*='1']")).waitUntil(visible,5000).setValue(description);
        $(By.cssSelector("input[placeholder*='Название']")).setValue(description);
        $(By.cssSelector("input[placeholder*='+']")).setValue(description);
        $(By.cssSelector("input[placeholder*='10000']")).setValue(description);
        $(By.cssSelector("textarea[placeholder*='Описание']")).setValue(description);
    }


    public void assertAddGrade() {
        String listGrade = "div[class^='grade__src-gradesBrandNew-components-GradeBaseInfo-']";
        $(listGrade).waitUntil(visible,10000);
        $$(listGrade).findBy(text(index));
    }

    public void assertQuestFromGrade() {
        String addGrade = "div[class*='grade__src-gradesBrandNew-components-GradeBaseInfo-__Mt7']";
        String dopInfo = "div[class^='wrapper__src-gradesBrandNew-views-Grades-containers-GradeItemRoutes-']";
        String quest = "span[class^=questName__src-gradesBrandNew-views-Grades-containers-GradeItemRoutes-']";
        $(addGrade).waitUntil(visible, 15000);
        $$(addGrade).findBy(text("G" + index)).scrollTo().click();
        $$(dopInfo).findBy(text("G" + index)).waitUntil(visible,5000).find(quest);
    }

    public void assertQuestFromDifferentGroupFromGrade() {
        String addGrade = "div[class*='grade__src-gradesBrandNew-components-GradeBaseInfo-__Mt7']";
        String quest = "span[class^='questName__src-gradesBrandNew-views-Grades-containers-GradeItemRoutes-']";
        $(addGrade).waitUntil(visible, 15000);
        $$(addGrade).findBy(text("G" + index)).scrollTo().click();
        $$(quest).shouldHaveSize(2);
    }



    //проверка всех полей после создания грейда, кроме квеста
    public void assertInfoAddGradeWithOneQuest() {
        String addGrade = "div[class^='gradeBaseInfo__src-gradesBrandNew-components-GradeBaseInfo-']";
        String opisanie = "div[class^='wrapper__src-gradesBrandNew-views-Grades-containers-GradeItemRoutes-']";
        $(addGrade).waitUntil(visible, 10000);
        $$(addGrade).findBy(text("G" + index)).scrollTo().click();
        $$(opisanie).last().scrollTo();
        $$(opisanie).findBy(text("G" + index)).shouldHave(text(name)).
                shouldHave(text(String.valueOf(String.format("%,d", money)))).
                shouldHave(text(questFirstName));
    }


    public void assertBaseInfoAdd() {
        String addGrade = "div[class^='gradeBaseInfo__src-gradesBrandNew-components-GradeBaseInfo-']";
        $(addGrade).waitUntil(visible, 10000);
        $$(addGrade).findBy(text("G" + index)).shouldHave(text(name)).
                shouldHave(text(String.valueOf(String.format("%,d", money)))).
                shouldHave(text(String.valueOf(point)));
    }

    public void assertDescriptionAdd() {
        String listIndex = "div[class^='grade__src-gradesBrandNew-components-GradeBaseInfo-']";
        String allInfo = "div[class*='infoText__src-gradesBrandNew-components-GradeUsualInfo-__12K']";
        $(listIndex).waitUntil(visible, 10000);
        $$(listIndex).findBy(text(index)).scrollTo().click();
        $(allInfo).waitUntil(visible, 5000).shouldHave(text(description));
    }




    public void assertInfoAddGradeWithQuestFromDifferentGroup() {
        String addGrade = "div[class^='gradeBaseInfo__src-gradesBrandNew-components-GradeBaseInfo-']";
        String opisanie = "div[class^='wrapper__src-gradesBrandNew-views-Grades-containers-GradeItemRoutes-']";
        $(addGrade).waitUntil(visible, 10000);
        $$(addGrade).findBy(text("G" + index)).click();
        $$(opisanie).findBy(text("G" + index)).shouldHave(text(name)).
                shouldHave(text(String.valueOf(String.format("%,d", money)))).shouldHave(text(description)).
                shouldHave(text(questFirstName)).shouldHave(text(questSecondName));
    }


    public void assertNotAddGrade() {
        String listGrade = "div[class^='grade__src-gradesBrandNew-components-GradeBaseInfo-']";
        $(listGrade).waitUntil(visible, 15000);
        $$(listGrade).find(exactText(index)).shouldBe(hidden);
    }

    public int getSizeList() {
        sleep(2000);
        String addGrade = "div[class^='grade__src-gradesBrandNew-components-GradeBaseInfo-']";
        $(addGrade).waitUntil(visible, 15000);
        return $$(addGrade).size();
    }

    public void checkEmptyField() {
        $(By.cssSelector("input[placeholder*='1']")).waitUntil(visible,10000).shouldHave(empty);
        $(By.cssSelector("input[placeholder*='Название']")).shouldHave(empty);
        $(By.cssSelector("input[placeholder*='+']")).shouldHave(empty);
        $(By.cssSelector("input[placeholder*='10000']")).shouldHave(empty);
        $(By.cssSelector("input[placeholder*='Название']")).shouldHave(empty);
        $(By.cssSelector("itemName__src-gradesBrandNew-views-Grades-containers-GradeForm-")).shouldHave(hidden);
    }

    public void openFirstQuestGroup() {
        $(By.cssSelector("img[alt^='Open']")).click();
    }

    public void openSecondQuestGroup() {
        $$(By.cssSelector("img[width*='18']")).get(1).click();
    }

    public void openModalNotArchivedGrade() {
        $("div[class^='wrapper__src-gradesBrandNew-views-Grades-containers-GradeItemRoutes-']").
                waitUntil(visible, 10000);
        indexForArchive = $$("div[class^='grade__src-gradesBrandNew-components-GradeBaseInfo-']")
                .findBy(enabled).scrollTo().getText();
        $$("div[class^='wrapper__src-gradesBrandNew-views-Grades-containers-GradeItemRoutes-']")
                .findBy(enabled).hover().find("img[alt*='Archive']").waitUntil(visible, 3000).click();
    }

    public void openModalArchivedGrade() {
        $("div[class^='wrapper__src-gradesBrandNew-views-Grades-containers-GradeItemRoutes-']").
                waitUntil(visible, 10000);
        indexForArchive = $("div[class*='isArchive__src']").scrollTo().
                find("div[class^='grade__src-gradesBrandNew-components-GradeBaseInfo-']").getText();
        $("div[class*='isArchive__src']").scrollTo().hover().
                find("img[data-name*='ARCHIVED']").waitUntil(visible, 3000).click();
    }

    public void acceptArchiveGrade() {
           $$("button[class*='ModalArchiveDialogue']").findBy(text("Да")).
        waitUntil(enabled, 5000).click();
    }

    public void cancelArchiveGrade() {
        $$("button[class*='ModalArchiveDialogue']").findBy(text("Нет")).
                waitUntil(enabled, 5000).click();
    }

    public void assertArchivedGrade(){
        $("div[class^='loader__src-shared-Preloader-__1Jt']").waitUntil(visible,5000);
        $("div[class^='grade__src-gradesBrandNew-components-GradeBaseInfo-__Mt7']").
                waitUntil(visible, 10000);
        $$("div[class^='grade__src-gradesBrandNew-components-GradeBaseInfo-__Mt7']")
                .findBy(text(indexForArchive)).scrollTo();
        Assert.assertTrue($$("div[class^='wrapper__src-gradesBrandNew-views-Grades-containers-GradeItemRoutes-__3pH isArchive__src-gradesBrandNew-views-Grades-containers-GradeItemRoutes-__1_m']")
                .findBy(text(indexForArchive)).waitUntil(visible, 3000).is(disabled));
    }

    public void assertUnArchived(){
        $("div[class^='wrapper__src-gradesBrandNew-views-Grades-containers-GradeItemRoutes-']").
                waitUntil(visible, 10000);
        $$("div[class^='wrapper__src-gradesBrandNew-views-Grades-containers-GradeItemRoutes-']")
                .findBy(text(indexForArchive)).scrollTo().is(enabled);
        $$("div[class^='wrapper__src-gradesBrandNew-views-Grades-containers-GradeItemRoutes-']")
                .findBy(text(indexForArchive)).is(enabled);
    }


}






