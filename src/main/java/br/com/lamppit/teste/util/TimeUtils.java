package br.com.lamppit.teste.util;

import java.time.LocalTime;
import java.time.ZoneId;

public class TimeUtils {

    // Como o servidor pode estar hospedado em qualquer lugar do mundo, o ideal
    // é que a localização do cliente fosse guardada no banco para usar o fuso horário local dele.
    public static int getCurrentHour() {
        ZoneId serverZone = ZoneId.systemDefault();
        LocalTime currentTime = LocalTime.now(serverZone);

        return currentTime.getHour();
    }
}
