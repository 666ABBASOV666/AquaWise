package pictures;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;  

public class CurrentDateAndTime {  

    static DateTimeFormatter dtf;

    public static void main(String[] args) {  
        String currentDateTime = getCurrentDateAndTime();
        System.out.println(currentDateTime);
    }  

    static String getCurrentDateAndTime(){
        dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
}
