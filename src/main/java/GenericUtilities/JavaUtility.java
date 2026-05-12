package GenericUtilities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * @author B.Nandini This is a reusable class where we are fetch random integer
 *         Today's date and date after given period
 */
public class JavaUtility {

	/**
	 * This is a reusable method to fetch the random integer
	 * 
	 * @return
	 */
	public int fetchRandomInt() {

		Random r = new Random();
		int num = r.nextInt(1000);
		return num;

	}

	/**
	 * This method is used to Fetch the current date
	 * 
	 * @return
	 */
	public String fetchCurrentDate() {
		Date dobj = new Date();
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
		String currentdate = sim.format(dobj);
		return currentdate;
	}

	/**
	 * This method is used to fetch the date for some(given) days
	 * 
	 * @param days
	 * @return
	 */
	public String fetchDateAfterGivenDays(int days) {
		Date dobj = new Date();
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
		 sim.format(dobj);
		Calendar cal = sim.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, days);
		String date = sim.format(cal.getTime());
		return date;

	}

}
