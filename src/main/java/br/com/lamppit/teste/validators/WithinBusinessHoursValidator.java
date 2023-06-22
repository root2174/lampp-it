package br.com.lamppit.teste.validators;

public class WithinBusinessHoursValidator {

    public static boolean validate(int now, int openHour, int closedHour) {
        if (closedHour >= openHour) {
            return now >= openHour && now <= closedHour;
        } else {
            return now >= openHour || now <= closedHour;
        }
    }
}
