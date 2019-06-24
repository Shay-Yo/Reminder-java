/**
 * Shay Yosopov
 * id:324124593
 */

import java.io.Serializable;
import java.util.HashMap;

public class Reminder <E> implements Serializable {
    private HashMap<E,String> reminder;

    public Reminder(){
        reminder = new HashMap<E,String>();
    }

    public void addReminder(E date, String string){
        String str = string;
        reminder.put(date,str);
    }


    public String getReminder(E date) throws DateNotExistsException
    {
        if(reminder.get(date)==null)
            throw new DateNotExistsException("There is no reminder in "+date); //the given object does not exist in the hashMap
        return reminder.get(date);
    }

    public int getSize()
    {
        return reminder.size();
    }

    public String toString() {
        StringBuilder mapAsString = new StringBuilder();
        for (E key : reminder.keySet()) {
            mapAsString.append(key + " " + reminder.get(key) + "\n");
        }
        return mapAsString.toString();
    }

 /*   public String toString(){
        String rem="";
        for (E date: reminder.keySet()){
            String key = date.toString();
            String value = reminder.get(date);
            rem=key + " " + value+"\n";
        }
        return rem;
    }*/




}
