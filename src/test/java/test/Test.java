package test;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import com.lj.czwgl.utils.Utils;

public class Test {

	public static void main(String[] args) throws Exception {
//		LocalDate dt = LocalDate.of(2019, 3, 1);
//		dt = dt.minusDays(1);
//		LocalDate lddt = Utils.dateToLocalDate(new Date());
//		System.out.println(lddt);
//		Instant a = Instant.ofEpochMilli(new Date().getTime());
//		Date dt = Utils.localDateToDate(lddt);
//		System.out.println(dt);
//		new Date()
		System.out.println(Utils.relativeDate(new Date(), Calendar.MONTH, 1));
	}
	
	private  static String subMonth(String date) throws Exception {  
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
        Date dt = sdf.parse(date);  
        Calendar rightNow = Calendar.getInstance();  
        rightNow.setTime(dt);  
        rightNow.add(Calendar.MONTH, 1);  
        Date dt1 = rightNow.getTime();  
        String reStr = sdf.format(dt1);  
        return reStr;  
	}

}
