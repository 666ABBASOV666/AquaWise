package pictures;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.time.LocalTime;  

public class CurrentDateAndTime {  

    static DateTimeFormatter dtf;
    String previousDateTime = "2020/01/01 00:00:00";

    public static void main(String[] args) {  
        CurrentDateAndTime cdt = new CurrentDateAndTime();
        String currentDateTime = getCurrentDateAndTime();
        System.out.println(currentDateTime);
        
        
        
    }  
    CurrentDateAndTime(){
        String str = getTimeDifference();
        System.out.println(str);
    }

    static String getCurrentDateAndTime(){
        dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    String getTimeDifference(){
        String currentDateTime = getCurrentDateAndTime();
        String rtn = currentDateTime.substring(0, 11);
        LocalTime currentTime = LocalTime.parse(currentDateTime.substring(11, 19));
        LocalTime previousTime = LocalTime.parse(previousDateTime.substring(11, 19));

        int hourDifference = getDifference(previousTime.getHour(), currentTime.getHour());
        int minuteDifference = getDifference(previousTime.getMinute(), currentTime.getMinute());
        int secondDifference = getDifference(previousTime.getSecond(), currentTime.getSecond());

        rtn = rtn + hourDifference + " hours, " + minuteDifference + " minutes, " + secondDifference + " seconds";


        return rtn;
    }
    int getDifference(int previous, int current){
        return current - previous;
    }
    int strToInt(String str){
        return Integer.parseInt(str);
    }
}
