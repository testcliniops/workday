package work;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.testng.annotations.Test;

public class DriverScript extends WorkAuto {

	@Test
	public static void driverscript()
			throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, IOException, ClassNotFoundException, InstantiationException {

		DriverScript CAS = new DriverScript();

		String testDataPath = "C:/Users/Sreeram/Downloads/runner.xls";
		//String ReportsPath = ".\\test-output\\Suite\\Report";
		String ReportsPath = "./src/work/Reports";
		String[][] recData = Reusable.readSheet(testDataPath, "Sheet1");

		String tc = null;
		// looping through the rows
		for (int i = 0; i < recData.length; i++) {

			if (recData[i][1].equalsIgnoreCase("y")) {

				tc = recData[i][2];
				System.out.println("Testcase to run: " + recData[i][2]);

				// we are getting handle to the method for invoking...
				Method testcase = DriverScript.class.getMethod(tc);

				// invoke---executes the method
				if (recData[i][3].equalsIgnoreCase("Y")) {
					Reusable.startReport(tc, ReportsPath, "FIREFOX");
					CAS.select_browser("firefox");
					testcase.invoke(CAS);
					// closing bufferwriter
					Reusable.bw.close();
				}

				if (recData[i][4].equalsIgnoreCase("Y")) {
					Reusable.startReport(tc, ReportsPath, "CHROME");
					CAS.select_browser("chrome");
					testcase.invoke(CAS);
					Reusable.bw.close();

				}
				if (recData[i][5].equalsIgnoreCase("Y")) {
					Reusable.startReport(tc, ReportsPath, "IE");
					CAS.select_browser("IE");
					testcase.invoke(CAS);
					Reusable.bw.close();

				}
				Reusable.bw.close();
			} else if (recData[i][1].equalsIgnoreCase("n")) {
				System.out.println(recData[i][2] + "in line number " + i + "skipped from " + "execution");
			}
		}

	}
}
