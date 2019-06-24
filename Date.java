import java.util.GregorianCalendar;
import java.util.Objects;

/**
 * Shay Yosopov
 * id:324124593
 */

public class Date {
    private int _day;
    private int _month;
    private int _year;


    public Date(int day, int month, int year){
        _day=day;
        _month=month;
        _year=year;
    }


    public void setDay(int day){
        _day=day;
    }

    public void setMonth(int month){
        _month=month;
    }

    public void setYear(int year){
        _year=year;
    }

    public int getDay(){
        return _day;
    }

    public int getMonth(){
        return _month;
    }

    public int getYear(){
        return _year;
    }


    public boolean equals(Object date){
        if(date instanceof Date) {
            return (((Date)date).getDay() == _day && ((Date)date).getMonth() == _month && ((Date)date).getYear() == _year);
        }
        return false;
    }


    public String toString(){
        return (_day+"/"+_month+"/"+_year);
    }

    public int hashCode()
    {
        GregorianCalendar calendar = new GregorianCalendar(_year + 1900, _month, _day, 0,0,0);
        long time = calendar.getTimeInMillis();
        return (int)time ^ (int)(time >>> 32);
    }

}
