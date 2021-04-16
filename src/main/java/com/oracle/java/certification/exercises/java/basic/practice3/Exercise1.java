package com.oracle.java.certification.exercises.java.basic.practice3;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;


public class Exercise1 {

    public static void main(String[] args) {
        /*
         * Exploring String and StringBuilder Objects
         * */
        System.out.println("=======================================");
        System.out.println("Exploring String and StringBuilder Objects");
        System.out.println("=======================================\n");
        String teaTxt = "Tea";
        String b = "Tea";
        System.out.println("teaTxt == b ? => " + (teaTxt == b));

        String c = new String("Tea");
        System.out.println("teaTxt == c ? => " + (teaTxt == c));

        String d = c.intern();
        System.out.println("teaTxt == d ? => " + (teaTxt == d));

        c = teaTxt + ' ' + b;
        System.out.println("new c value => " + c);

        System.out.println(c.indexOf('T'));
        System.out.println(c.indexOf('T', 1));
        System.out.println(c.lastIndexOf('T'));

        c = c.toUpperCase();

        System.out.println(c);

        StringBuilder txt = new StringBuilder(c);
        System.out.println(txt);
        System.out.println(txt.length());
        System.out.println(txt.capacity());
        System.out.println(txt.replace(0,3,"What is the price of"));
        System.out.println(txt.length());
        System.out.println(txt.capacity());
        System.out.println(txt.capacity());

        /*
         * Exploring BigDecimal class and Format Numeric Values
         * */
        System.out.println("\n=======================================");
        System.out.println("Exploring BigDecimal class and Format Numeric Values");
        System.out.println("=======================================\n");
        BigDecimal price = BigDecimal.valueOf(1.85);
        BigDecimal rate = BigDecimal.valueOf(0.065);
        System.out.println(price);
        System.out.println(rate);

        price = price.subtract(price.multiply(rate)).setScale(2, RoundingMode.HALF_UP);
        System.out.println(price);

        Locale locale = Locale.FRANCE;
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);
        NumberFormat percentFormat = NumberFormat.getPercentInstance(locale);

        percentFormat.setMaximumFractionDigits(2);

        System.out.println("price and rate values before formatting");
        System.out.println(price);
        System.out.println(rate);

        System.out.println("price and rate values after formatting");
        System.out.println(currencyFormat.format(price));
        System.out.println(percentFormat.format(rate));
        System.out.println(locale);

        System.out.println("Setting up locale for UK");
        locale = Locale.UK;
        System.out.println(locale);

        currencyFormat = NumberFormat.getCurrencyInstance(locale);
        percentFormat = NumberFormat.getPercentInstance(locale);

        percentFormat.setMaximumFractionDigits(2);

        String priceTxt = currencyFormat.format(price);
        System.out.println(priceTxt);

        String rateTxt = percentFormat.format(rate);
        System.out.println(rateTxt);

        /*
         * Exploring Date and Time values
         * */
        System.out.println("\n=======================================");
        System.out.println("Exploring Date and Time values");
        System.out.println("=======================================\n");
        LocalDate today = LocalDate.now();
        System.out.println(today);

        System.out.println("Plus one year and day if the week");
        System.out.println(today.plusYears(1).getDayOfWeek());

        LocalTime teaTime = LocalTime.of(22, 30);
        System.out.println(teaTime);


        Duration timeGap = Duration.between(LocalTime.now(), teaTime);
        System.out.println(timeGap);
        System.out.println(Duration.between(LocalTime.now(), LocalTime.of(23,0)));

        System.out.println(timeGap.toMinutes());
        System.out.println(timeGap.toHours());
        System.out.println(timeGap.toMinutesPart());

        LocalDateTime tomorrowTeaTime = LocalDateTime.of(today.plusDays(1), teaTime);
        System.out.println(tomorrowTeaTime);

        ZoneId london = ZoneId.of("Europe/London");
        ZoneId katmandu = ZoneId.of("Asia/Katmandu");

        System.out.println(london);
        System.out.println(katmandu);

        ZonedDateTime londonTime = ZonedDateTime.of(tomorrowTeaTime, london);
        System.out.println(londonTime);

        ZonedDateTime katmanduTime = ZonedDateTime.now().withZoneSameInstant(katmandu);
        System.out.println(katmanduTime);
        System.out.println(katmanduTime.getOffset());

        String datePattern = "EE', 'd' of 'MMM YYYY' at 'HH:mm z";
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(datePattern, locale);
        System.out.println(dateFormat);

        String timeTxt = dateFormat.format(katmanduTime);
        System.out.println(timeTxt);

        /*
         * Exploring Localization and Format Messages
         * */
        System.out.println("\n=======================================");
        System.out.println("Exploring Localization and Format Messages");
        System.out.println("=======================================\n");
        ResourceBundle msg = ResourceBundle.getBundle("messages", locale);

        String offerPattern = msg.getString("offer");
        System.out.println(offerPattern);

        System.out.println(teaTxt);
        System.out.println(priceTxt);
        System.out.println(rateTxt);
        System.out.println(timeTxt);

        System.out.println(MessageFormat.format(offerPattern, teaTxt, priceTxt, rateTxt, timeTxt));

        locale = new Locale("ru", "RU");
        msg = ResourceBundle.getBundle("messages", locale);

        offerPattern = msg.getString("offer");
        datePattern = msg.getString("dateFormat");

        currencyFormat = NumberFormat.getCurrencyInstance(locale);
        percentFormat = NumberFormat.getPercentInstance(locale);
        percentFormat.setMaximumFractionDigits(2);
        dateFormat = DateTimeFormatter.ofPattern(datePattern, locale);

        teaTxt = "blip blopity";
        priceTxt = currencyFormat.format(price);
        rateTxt = percentFormat.format(rate);
        timeTxt = dateFormat.format(katmanduTime);

        System.out.println(offerPattern);
        System.out.println(MessageFormat.format(offerPattern, teaTxt, priceTxt, rateTxt, timeTxt));
    }

}
