package ApplicationClient;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.*;

import java.util.LinkedList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main
{
	private static final String COMMA_DELIMITER = ",";
	
	public static void readApplications(String stFileName, LinkedList<Application> llApps)
    {
        try
        {
        	BufferedReader br = new BufferedReader(new FileReader(stFileName));

            String stLine;
            while ((stLine = br.readLine()) != null)
            {
            	String[] stValues = stLine.split(COMMA_DELIMITER);
                Application app = new Application(stValues[0],
                	stValues[1], stValues[2], stValues[3], Boolean.parseBoolean(stValues[4]), Integer.parseInt(stValues[5]), Integer.parseInt(stValues[6]), Integer.parseInt(stValues[7]),
                	stValues[8], stValues[9], Boolean.parseBoolean(stValues[10]), Integer.parseInt(stValues[11]));
                llApps.add(app);
            }
            br.close();
        }
        catch (Exception e)
        {
        	e.printStackTrace();
        }
    }

	public static void insertionSort(LinkedList<Application>llApps)
    {
        Application appkey = new Application();

        int iSize = llApps.size();

        for (int iStep = 1; iStep < iSize; iStep ++)
        {
            Application appi = llApps.get(iStep);

            appkey.set(appi.getID(), appi.getData());
            String stKey= appkey.getID();

            int j = iStep - 1;

            Application appj;
            
            while (j >= 0 && stKey.compareTo(llApps.get(j).getID()) < 0)
            {
                appj = llApps.get(j);
                llApps.get(j + 1).set(appj.getID(), appj.getData());
                --  j;
            }

            llApps.get(j + 1).set(appkey.getID(), appkey.getData());
        }
    }


	public static void main(String[] args)
	{
		// TODO Auto-generated method stub

		System.out.println("Application Client");
		
		LinkedList<Application> llApps = new LinkedList<>();
        readApplications("Applications.dat", llApps);
        System.out.println(llApps);
        insertionSort(llApps);
        System.out.println(llApps);
	
		MainWindow apps = new MainWindow("Home Loan Application Approval System", llApps);
	}
}