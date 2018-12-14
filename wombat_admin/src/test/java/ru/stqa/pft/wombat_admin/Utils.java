package ru.stqa.pft.wombat_admin;

public class Utils {

    public static String incrementAndReturnString(String target) {
        int incrementCounter = Integer.valueOf(target) + 1;
        return String.valueOf(incrementCounter);
    }

    public static String getNumberFromGradeString(String target){
        return target.split(" ")[1];
    }
}
