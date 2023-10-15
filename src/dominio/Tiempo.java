/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

import java.util.concurrent.TimeUnit;

/**
 *
 * @author Pedro Chialanza (302782) - Leandro Meneses (305998)
 */
public class Tiempo {
    
    private long startTime;
    private long stopTime;

    public long getStartTime() {
        return startTime;
    }

    public long getStopTime() {
        return stopTime;
    }

    public void start() {
        this.startTime = new java.util.Date().getTime();
    }

    public void stop() {
        this.stopTime = new java.util.Date().getTime();
    }

    public Tiempo() {
        this.start();
    }
    
    /**
     * @return Retorna un string que contiene la conversion de milisegundos a segundos, minutos, horas y dias, por pedido del obligatorio 
     */
    
    @Override
    public String toString() {
        long total = this.getStopTime() - this.getStartTime();
        long dias = TimeUnit.MILLISECONDS.toDays(total);
        long horas = TimeUnit.MILLISECONDS.toHours(total) - TimeUnit.DAYS.toHours(dias);
        long minutos = TimeUnit.MILLISECONDS.toMinutes(total) - TimeUnit.HOURS.toMinutes(horas) - TimeUnit.DAYS.toMinutes(dias);
        long segundos = TimeUnit.MILLISECONDS.toSeconds(total) - TimeUnit.MINUTES.toSeconds(minutos) - TimeUnit.HOURS.toSeconds(horas) - TimeUnit.DAYS.toSeconds(dias);
        long milisegundos = total - TimeUnit.SECONDS.toMillis(segundos) - TimeUnit.MINUTES.toMillis(minutos) - TimeUnit.HOURS.toMillis(horas) - TimeUnit.DAYS.toMillis(dias);

        return String.format("%d Dias, %d Horas, %d Minutos, %d Segundos, %d Milisegundos",
                dias, horas, minutos, segundos, milisegundos);
    }
}
