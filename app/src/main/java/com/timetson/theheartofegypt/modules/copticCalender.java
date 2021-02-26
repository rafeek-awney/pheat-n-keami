package com.timetson.theheartofegypt.modules;

import java.util.Calendar;

public class copticCalender {
    public static long DAY = 0;
    public static long MONTH = 0;
    public static long YEAR = 0;
    public static String[] months = {"Ⲑⲱⲟⲩⲧ", "Ⲡⲁⲱⲡⲉ", "Ϩⲁⲑⲱⲣ", "Ⲭⲟⲓⲁⲕ", "Ⲧⲱⲃⲓ", "Ⲙ̀ϣⲓⲣ", "Ⲡⲁⲣⲉⲙϩⲁⲧ", "Ⲡⲁⲣⲙⲟⲩⲧⲉ", "Ⲡⲁϣⲟⲛⲥ", "Ⲡⲁⲱⲛⲓ", "Ⲉⲡⲏⲡ", "Ⲙⲉⲥⲟⲩⲣⲏ", "ⲕⲟⲩϫⲓ"};

    public static void days_count(String isEgyptian) {
        Calendar rightNow = Calendar.getInstance();
        long offset = rightNow.get(Calendar.ZONE_OFFSET) + rightNow.get(Calendar.DST_OFFSET);
        long timesInMillis = rightNow.getTimeInMillis() + offset;
        /////////////////////////
        long totaldays = (timesInMillis / 86400000) - 5367;
        long yearsOffset = isEgyptian.equals("0") ? 1701 : 6226;
        ///////////////////
        long years;
        if (((totaldays % 1461) / 365) > 2)
            years = yearsOffset + ((totaldays / 1461) * 4) + (((totaldays - 1) % 1461) / 365);
        else
            years = yearsOffset + ((totaldays / 1461) * 4) + ((totaldays % 1461) / 365);
        ///////////////////
        long days4years = totaldays % 1461;
        if (days4years > 364) {
            days4years -= 365;
            if (days4years > 364) {
                days4years -= 365;
                if (days4years > 365) {
                    days4years -= 366;
                }
            }
        }
        YEAR = years;
        MONTH = (days4years / 30);
        DAY = days4years - (MONTH * 30) + 1;
    }

    public static long dayOfYear() {
        days_count("1");
        return (MONTH * 30) + DAY;
    }

    public static String get_calender(String isEgyptian, String numbersType) {
        days_count(isEgyptian);
        if (numbersType.equals("0"))
            return DAY + " " + months[(int) MONTH] + " " + YEAR;
        else
            return toCopticNumbers(DAY) + " " + months[(int) MONTH] + " " + toCopticNumbers(YEAR);

    }

    public static String toCopticNumbers(long number) {
        String copticNumber = "";

        long t = number / 1000;
        number = number % 1000;
        switch ((int) t) {
            case 1:
                copticNumber += "ⲁ̿";
                break;
            case 2:
                copticNumber += "ⲃ̿";
                break;
            case 3:
                copticNumber += "ⲅ̿";
                break;
            case 4:
                copticNumber += "ⲇ̿";
                break;
            case 5:
                copticNumber += "ⲉ̿";
                break;
            case 6:
                copticNumber += "ⲋ̿";
                break;
            case 7:
                copticNumber += "ⲍ̿";
                break;
            case 8:
                copticNumber += "ⲏ̿";
                break;
            case 9:
                copticNumber += "ⲓ̿";
                break;
        }
///////////////////////////////////////
        long h = number / 100;
        number = number % 100;
        switch ((int) h) {
            case 1:
                copticNumber += "ⲣ̅";
                break;
            case 2:
                copticNumber += "ⲥ̅";
                break;
            case 3:
                copticNumber += "ⲧ̅";
                break;
            case 4:
                copticNumber += "ⲩ̅";
                break;
            case 5:
                copticNumber += "ⲫ̅";
                break;
            case 6:
                copticNumber += "ⲭ̅";
                break;
            case 7:
                copticNumber += "ⲯ̅";
                break;
            case 8:
                copticNumber += "ⲱ̅";
                break;
            case 9:
                copticNumber += "ϣ̅";
                break;
        }
//////////////////////////////////////
        long d = number / 10;
        number = number % 10;
        switch ((int) d) {
            case 1:
                copticNumber += "ⲓ̅";
                break;
            case 2:
                copticNumber += "ⲕ̅";
                break;
            case 3:
                copticNumber += "ⲗ̅";
                break;
            case 4:
                copticNumber += "ⲙ̅";
                break;
            case 5:
                copticNumber += "ⲛ̅";
                break;
            case 6:
                copticNumber += "ⲝ̅";
                break;
            case 7:
                copticNumber += "ⲟ̅";
                break;
            case 8:
                copticNumber += "ⲡ̅";
                break;
            case 9:
                copticNumber += "ϥ̅";
                break;
        }
/////////////////////////////////////
        switch ((int) number) {
            case 1:
                copticNumber += "ⲁ̅";
                break;
            case 2:
                copticNumber += "ⲃ̅";
                break;
            case 3:
                copticNumber += "ⲅ̅";
                break;
            case 4:
                copticNumber += "ⲇ̅";
                break;
            case 5:
                copticNumber += "ⲉ̅";
                break;
            case 6:
                copticNumber += "ⲋ̅";
                break;
            case 7:
                copticNumber += "ⲍ̅";
                break;
            case 8:
                copticNumber += "ⲏ̅";
                break;
            case 9:
                copticNumber += "ⲑ̅";
                break;
        }

        return copticNumber;
    }

}
