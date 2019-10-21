package krusty_krab.krusty_krab.domain;

public class Time {
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private boolean positive;

    public Time(int year, int month, int day, int hour, int minute, boolean positive) {
        this.year = year;
        this.positive = positive;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
    }

    public boolean isLessThan(Time t){
        return this.toMinutes() < t.toMinutes();
    }

    public long toMinutes(){
        return this.getMinute() + 60 * this.getHour() + 60 * 24 * this.getDay() + 60 * 24 * 31 * this.getMonth() + 60 * 24 * 31 * 12 * this.getYear();
    }

    public Time add(Time t){
        int minute = t.getMinute() + this.getMinute();
        int hour = t.getHour() + this.getHour();
        int day = t.getDay() + this.getDay();
        int month = t.getMonth() + this.getMonth();
        int year = t.getYear() + this.getYear();

        if(minute >= 60){
            minute -= 60;
            hour ++;
        }
        if(hour >= 24){
            hour -= 24;
            day ++;
        }
        if(day > 31){
            day -= 31;
            month++;
        }
        if(month > 12){
            month -= 12;
            year++;
        }
        return new Time(year, month, day, hour, minute, true);
    }

    public Time getDifference(Time t){
        int minute = t.getMinute() - this.getMinute();
        int hour = t.getHour() - this.getHour();
        int day = t.getDay() - this.getDay();
        int month = t.getMonth() - this.getMonth();
        int year = t.getYear() - this.getYear();

        int multiplier;
        if(year < 0 || (year == 0 && month < 0) || (year == 0 && month == 0 && day < 0) || (year == 0 && month == 0 && day ==0 && hour < 0) || (year == 0 && month == 0 && day ==0 && hour == 0 && minute < 0)){
            multiplier = -1;
        }
        else{
            multiplier = 1;
        };

        if(month*multiplier < 0){
            month += multiplier*12;
            year-= multiplier;
        }
        if(day*multiplier < 0){
            day += multiplier*31;
            month-= multiplier;
        }
        if(hour*multiplier < 0){
            hour += multiplier*24;
            day-= multiplier;
        }
        if(minute*multiplier < 0){
            minute += multiplier*60;
            hour-= multiplier;
        }

        Time diff;
        if(multiplier == -1){
            diff = new Time(multiplier*year, multiplier * month, multiplier * day, multiplier * hour, multiplier * minute, false);
        }
        else{
            diff = new Time(multiplier*year, multiplier * month, multiplier * day, multiplier * hour, multiplier * minute, true);
        }
        return diff;

    }

    public String toString(){
        String result;
        if(this.isPositive()){
            result = getHour() + ":" + getMinute() + " " + getDay() + "/" + getMonth() + "/" + getYear();
        }
        else{
            result = "-" + getHour() + ":" + getMinute() + " " + getDay() + "/" + getMonth() + "/" + getYear();
        }
        return result;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public boolean isPositive() {
        return positive;
    }

    public void setPositive(boolean positive) {
        this.positive = positive;
    }
}
