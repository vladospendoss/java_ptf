package ru.stqa.pft.wombat_admin.appmanager;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.util.Random;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class GradeHelper {

    private Integer lastUpperGrade = -1;
    private String index;
    private String name;
    private int money;
    private String description = "декспиршн";
    private int point;
    private String questName;
    private String countQuestBeforeModify;
    private String countQuest;

    Random rand = new Random();

    //переход к редактированию грейда с максимальным индексом
    public void goToModify() {
        index = String.valueOf(lastUpperGrade);
        countQuestBeforeModify = $$("div[class^='wrapper__src-gradesBrandNew-views-Grades-containers-GradeItemRoutes-']")
                .findBy(text("G"+index)).scrollTo().
                find("div[class^='gradeRequiredQuests__src-gradesBrandNew-components-GradeBaseInfo-']").
                getText();
        $$("div[class^='wrapper__src-gradesBrandNew-views-Grades-containers-GradeItemRoutes-']")
                .findBy(text("G"+index)).scrollTo().hover()
                .find("img[alt^='Edit']").click();
    }

    //
    private int getNumberFromGrade(String grade){
        return Integer.valueOf(grade.split("G")[1]);
    }

    //находит грейд с максимальным индексом из всех грейдов
    public void findIndex() {
        $("div[class^='grade__src-gradesBrandNew-components-GradeBaseInfo-']").
                waitUntil(visible,10000);
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
    //если индекс не был получен ранее, ему присваевается 1
    public void setIndex() {
        String nextIndex = String.valueOf(lastUpperGrade + 1);
        if (index == null){
            index = "1"; $(By.cssSelector("input[placeholder*='12']")).setValue(index);
         }
    else {
            index = nextIndex;
            $(By.cssSelector("input[placeholder*='12']")).setValue(index);
          }
    }

    //ввод нового уникального названия грейда
    public void setName() {
        String stringName = String.valueOf(lastUpperGrade + 1);
        name = "Grade " + stringName;
        $(By.cssSelector("input[placeholder*='Название']")).setValue(name);
    }

    //ввод рандомной зарплаты <100000
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
    public void setDescription() { $(By.cssSelector("textarea[placeholder*='Описание']")).
            setValue(description);
    }

    //подтверждение создания или редакирования грейда
    public void submit() {
        if (!$(byText("Сохранить")).waitUntil(visible,10000).isDisplayed()){
            $(byText("Сохранить")).scrollTo().click();
        }
        else { $(byText("Сохранить")).click(); }
    }

    //переход к модальному окну с группами квестов при создании или редактировании квеста
    public void goToAddQuest() { $(byText("+ Добавить квест")).click();}

    //отмена создания или редакирования грейда
    public void closeGradeModal() {
        if (!$(byText("Отмена")).waitUntil(visible,10000).isDisplayed()){
            $(byText("Отмена")).scrollTo().click();
        }
        else { $(byText("Отмена")).click(); }
    }

    //разворачивание ячейки квеста
    public void openQuestGroup() { $(By.cssSelector("img[alt^='Open']")).scrollTo().click();}

    //получаем название квеста
    public void getQuestName() {
        questName = $("div[class^='itemName__src-gradesBrandNew-views-Grades-containers-GradesQuestsModal-']").
                waitUntil(visible,2000).getText(); }

    /* после выбора квеста получаем кол-во выбранных для добавления в грейд квестов
    если не выбрано ни одного, просто закрываем модальное окно */
    public void getCountQuestAndCloseQuestModal() {
        if ($("span[class^='numberOfQuests__src-gradesBrandNew-views-Grades-containers-GradesQuestsModal-']").
                isDisplayed()){
            //складываем кол-во квестов из иконки выбранных квестов в модальном окне
            //и кол-во квестов этого грейд из списка грейдов
           String countInIconQuest = $("span[class^='numberOfQuests__src-gradesBrandNew-views-Grades-containers-GradesQuestsModal-']").
                    waitUntil(visible,2000).getText();
            Integer.valueOf(countInIconQuest);
            Integer.valueOf(countQuestBeforeModify);
            countQuest = countInIconQuest + countQuestBeforeModify;
            $(By.className("modalButton__src-gradesBrandNew-views-Grades-containers-GradesQuestsModal-__2_W")).click();
        }
        else { countQuest = countQuestBeforeModify;
        $("button[type^='reset']").click(); }
    }

    //выбираем квест из раскрывшейся группы
    public void selectQuest() { $(By.cssSelector("input[value^='on']")).click();}

    //проверка всех полей после создания грейда
    public void assertAddGrade() {
        $$("div[class^='grade__src-gradesBrandNew-components-GradeBaseInfo-']").
                findBy(text(index)).waitUntil(visible, 10000).
                scrollTo().click();
        $$("div[class^='grade__src-gradesBrandNew-components-GradeBaseInfo-']").
                findBy(text("G"+index));
        $$("div[class^='gradeName__src-gradesBrandNew-components-GradeBaseInfo-']").
                findBy(text(name));
        $$("div[class^='gradeSalary__src-gradesBrandNew-components-GradeBaseInfo-']").
                findBy(text(String.valueOf(money)));
        $$("div[class^='gradeRequiredQuests__src-gradesBrandNew-components-GradeBaseInfo-']").
                findBy(text(countQuest));
        $$("div[class^='gradeAlternativeQuests__src-gradesBrandNew-components-GradeBaseInfo-']").
                findBy(text(String.valueOf(point)));
        $( "div[class^='infoText__src-gradesBrandNew-components-GradeUsualInfo-']" ).
                scrollTo().shouldHave(text(description));
        $("span[class^='questName__src-gradesBrandNew-views-Grades-containers-GradeItemRoutes-']").
                scrollTo().shouldHave(text(questName));
    }

    //проверка всех полей после редактирования грейда
    public void assertModifyGrade() {
        closeGradeModal();
        sleep(2000);
        $$("div[class^='grade__src-gradesBrandNew-components-GradeBaseInfo-']").
                findBy(text(index)).click();
        $$("div[class^='grade__src-gradesBrandNew-components-GradeBaseInfo-']").
                findBy(text("G" + (index)));
        $$("div[class^='gradeName__src-gradesBrandNew-components-GradeBaseInfo-']").
                findBy(text((name)));
        $$("div[class^='gradeSalary__src-gradesBrandNew-components-GradeBaseInfo-']").
                findBy(text(String.valueOf(money)));
        $$("div[class^='gradeRequiredQuests__src-gradesBrandNew-components-GradeBaseInfo-']").
                findBy(text(String.valueOf(countQuest)));
        $$("div[class^='gradeAlternativeQuests__src-gradesBrandNew-components-GradeBaseInfo-']").
                findBy(text(String.valueOf(point)));
        $( "div[class^='infoText__src-gradesBrandNew-components-GradeUsualInfo-']" ).
                scrollTo().shouldHave(text(description));
        $("span[class^='questName__src-gradesBrandNew-views-Grades-containers-GradeItemRoutes-']").
                scrollTo().shouldHave(text(questName));
    }

}




