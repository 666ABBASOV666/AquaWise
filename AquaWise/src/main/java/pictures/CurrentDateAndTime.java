package pictures;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;  
public class CurrentDateAndTime {  

  int[] upcomingActivities = new int[3];
  int[] pastActivities = new int[3]; 
  static DateTimeFormatter dtf;

  public static void main(String[] args) {  
    getCurrentDateAndTime();
  }  
  static DateTimeFormatter getCurrentDateAndTime(){
    dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    LocalDateTime now = LocalDateTime.now();
    System.out.println(dtf.format(now));

    return dtf;

  }

}   
