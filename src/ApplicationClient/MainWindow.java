package ApplicationClient;

import java.util.List;

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
	private String stAppID;
	
	public MainWindow(String stTitle)
	{
		this.stTitle = stTitle;
		stAppID = "";
		
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
        DefaultListModel<String> lmCustomers = getCustomersListModel();
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
				if (stAppID != "")
				{
					ApplicationWindow viewWindow = new ApplicationWindow(frame, UpdateType.VIEW, stAppID);
				}
			}
		};

        ActionListener alAdd = new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				ApplicationWindow addWindow = new ApplicationWindow(frame, UpdateType.ADD, stAppID);
			}
		};
		
        ActionListener alUpdate = new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				ApplicationWindow updateWindow = new ApplicationWindow(frame, UpdateType.UPDATE, stAppID);
			}
		};

        ActionListener alDelete = new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				ApplicationWindow deleteWindow = new ApplicationWindow(frame, UpdateType.DELETE, stAppID);
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
                    System.out.println(selectedValuesList.get(0).split(" ")[0]);
                    stAppID = selectedValuesList.get(0).split(" ")[0];                
                }
            }
        });        
        
        // Display winodw
        frame.setVisible(true);
	}
	
	public static boolean okCancel(String stMessage, String stTitle)
	{
		return (JOptionPane.showConfirmDialog((Component)null, stMessage, stTitle, JOptionPane.OK_CANCEL_OPTION) == 0) ? true : false;
	}
	
	private DefaultListModel<String> getCustomersListModel()
	{
		DefaultListModel<String> lmCustomers = new DefaultListModel<>();
		lmCustomers.addElement("APP01 David Cleary");
		lmCustomers.addElement("APP52 Chris Zhong");
		lmCustomers.addElement("APP67 Mike Hall");
		lmCustomers.addElement("APP87 Scott Hopkins");
		lmCustomers.addElement("APP91 Nicky Moore");
		
		return lmCustomers;
	}
}
