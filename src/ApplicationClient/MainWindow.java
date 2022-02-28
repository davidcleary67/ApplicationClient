package ApplicationClient;

import java.util.List;
import java.util.Iterator;
import java.util.LinkedList;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class MainWindow
{
	private String stTitle;
	private LinkedList<Application> llApps;
	private String stAppID;
	private Application app;
	private DefaultListModel<String> lmCustomers; 
	
	public MainWindow(String stTitle, LinkedList<Application> llApps)
	{
		this.stTitle = stTitle;
		this.stAppID = "";
		this.llApps = llApps;
		
		CreateWindow();
	}
	
	private void CreateWindow()
	{
		// Windows frame
		JFrame frame = new JFrame(stTitle);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setSize(400, 400);
		
		//Creating the MenuBar and adding components
        JMenuBar mbMain = new JMenuBar();
        JMenu muFile = new JMenu("File");
        JMenu muHelp = new JMenu("Help");
        mbMain.add(muFile);
        mbMain.add(muHelp);
        
        JMenuItem miExit = new JMenuItem("Exit");
        muFile.add(miExit);

        // Button panel
        JPanel plButtons = new JPanel(); 
        JButton btView = new JButton("View");
        JButton btAdd = new JButton("Add");
        JButton btUpdate = new JButton("Update");
        JButton btDelete = new JButton("Delete");
        JButton btExit = new JButton("Exit");
        
        plButtons.add(btView);
        plButtons.add(btAdd);
        plButtons.add(btUpdate);
        plButtons.add(btDelete);
        plButtons.add(btExit);
        
        // Customer list
        lmCustomers = getCustomersListModel();
        JList<String> ltCustomers = new JList<>(lmCustomers);
        ltCustomers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ltCustomers.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        		
        //Add components to frame
        frame.getContentPane().add(BorderLayout.SOUTH, plButtons);
        frame.getContentPane().add(BorderLayout.NORTH, mbMain);
        frame.getContentPane().add(BorderLayout.CENTER, new JScrollPane(ltCustomers));
        
        // Action listeners
        ActionListener alExit = new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (okCancel("Do you wish to exit the HLAAS?", "Confirm Exit"))
				{
					System.exit(0);
				}
			}
		};
        
        ActionListener alView = new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (stAppID.compareTo("") != 0)
				{
					ApplicationWindow viewWindow = new ApplicationWindow(frame, UpdateType.VIEW, app, llApps);
				}
			}
		};

        ActionListener alAdd = new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				app = new Application();
				ApplicationWindow addWindow = new ApplicationWindow(frame, UpdateType.ADD, app, llApps);
			}
		};
		
        ActionListener alUpdate = new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (stAppID.compareTo("") != 0)
				{
					app = new Application();
					ApplicationWindow updateWindow = new ApplicationWindow(frame, UpdateType.UPDATE, app, llApps);
				}
			}
		};

        ActionListener alDelete = new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (stAppID.compareTo("") != 0)
				{
					ApplicationWindow deleteWindow = new ApplicationWindow(frame, UpdateType.DELETE, app, llApps);
					lmCustomers.clear();
					
					Application app; 
					Iterator<Application> appIter = llApps.iterator();
			        while (appIter.hasNext())
			        {
			        	app = appIter.next();
			            //System.out.println(app);
			            lmCustomers.addElement(app.getID() + " " + app.getName());
			        }
				}
			}
		};

		btView.addActionListener(alView);
		btAdd.addActionListener(alAdd);
		btUpdate.addActionListener(alUpdate);
		btDelete.addActionListener(alDelete);
		
		miExit.addActionListener(alExit);
		
        btAdd.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
			}
		});
        
        btExit.addActionListener(alExit);

        ltCustomers.addListSelectionListener(new ListSelectionListener()
        {
            @Override
            public void valueChanged(ListSelectionEvent e)
            {
                if(!e.getValueIsAdjusting())
                {
                    final List<String> selectedValuesList = ltCustomers.getSelectedValuesList();
                    if (selectedValuesList.size() != 0)
                    {
                    	System.out.println(selectedValuesList.get(0).split(" ")[0]);
                    	stAppID = selectedValuesList.get(0).split(" ")[0]; 
                    	app = findApp(stAppID);
                    }
                    else
                    {
                    	System.out.println("Unselected");
                    	stAppID = ""; 
                    }
                }
            }
        });        

        // Display window
        frame.setVisible(true);
	}
	
    public Application findApp(String stAppID)
    {
    	Iterator<Application> appIter = llApps.iterator();
    	
        while (appIter.hasNext())
        {
        	app = appIter.next();
        	System.out.println("Here: " + app.getID() + " " + stAppID);
        	if (stAppID.compareTo(app.getID()) == 0)
        	{
        		System.out.println("Found: " + app.getID());
        		break;
        	}
            //System.out.println(app);
        }
    	return app;
    }

    public static boolean okCancel(String stMessage, String stTitle)
	{
		return (JOptionPane.showConfirmDialog((Component)null, stMessage, stTitle, JOptionPane.OK_CANCEL_OPTION) == 0) ? true : false;
	}
	
	private DefaultListModel<String> getCustomersListModel()
	{
		DefaultListModel<String> lmCustomers = new DefaultListModel<>();
		
    	Application app; 
		Iterator<Application> appIter = llApps.iterator();
        while (appIter.hasNext())
        {
        	app = appIter.next();
            System.out.println(app);
            lmCustomers.addElement(app.getID() + " " + app.getName());
        }

		return lmCustomers;
	}
}
