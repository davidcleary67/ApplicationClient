package ApplicationClient;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JDialog;

public class ApplicationWindow
{
	private JFrame frParent;
	private UpdateType updateType;
	private String stAppID;
	private String stTitle;
	private String stOkay; 
	
	public ApplicationWindow(JFrame frParent, UpdateType updateType, String stAppID)
	{
		this.frParent = frParent;
		this.updateType = updateType;
		this.stAppID = stAppID;
		
		switch (updateType)
		{
		case VIEW:
			stTitle = "View Application:" + stAppID;
			stOkay = "View";
			break;
			case ADD:
				stTitle = "Add Application:" + stAppID;
				stOkay = "Add";
				break;
			case UPDATE:
				stTitle = "Update Application:" + stAppID;
				stOkay = "Update";
				break;
			case DELETE:
				stTitle = "Delete Application:" + stAppID;
				stOkay = "Delete";
				break;
		}
		CreateWindow();
	}
	
	private void CreateWindow()
	{
		// Windows frame
		JDialog frame = new JDialog(frParent, stTitle, true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(700, 400);
		
		// Client panels
		JPanel plCID = new JPanel(new BorderLayout());
		JTextField txCID = createField(plCID, "ID:");
		JPanel plCName = new JPanel(new BorderLayout());
		JTextField txCName = createField(plCName, "Name:");
		JPanel plCIncome = new JPanel(new BorderLayout());
		JTextField txCIncome = createField(plCIncome, "Income:");
				
		JPanel plClient = new JPanel();
		plClient.setLayout(new BoxLayout(plClient, BoxLayout.Y_AXIS));
		plClient.add(new JLabel("Client"));
		plClient.add(plCID);
		plClient.add(plCName);
		plClient.add(plCIncome);
		
		// Property panels
		JPanel plPID = new JPanel(new BorderLayout());
		JTextField txPID = createField(plPID, "ID:");
		JPanel plPAddress = new JPanel(new BorderLayout());
		JTextField txPAddress = createField(plPAddress, "Address:");
		JPanel plPOffer = new JPanel(new BorderLayout());
		JTextField txPOffer = createField(plPOffer, "Offer price:");
				
		JPanel plProperty = new JPanel();
		plProperty.setLayout(new BoxLayout(plProperty, BoxLayout.Y_AXIS));
		plProperty.add(new JLabel("Property"));
		plProperty.add(plPID);
		plProperty.add(plPAddress);
		plProperty.add(plPOffer);
		
        // Button panel
        JPanel plButtons = new JPanel();
        //plButtons.setLayout(new BoxLayout(plButtons, BoxLayout.LINE_AXIS));
        //plButtons.add(Box.createHorizontalGlue());
        
        JButton btCredit = new JButton("Check Credit Status");
        JButton btProperty = new JButton("Appraise Property");
        JButton btApproval = new JButton("Get Approval");
        JButton btOkay = new JButton(stOkay);
        JButton btClose = new JButton("Close");
        
        plButtons.add(btCredit);
        plButtons.add(btProperty);
        plButtons.add(btApproval);
        plButtons.add(btOkay);
        plButtons.add(btClose);
        
		// Add components to frame
        
		JPanel plFields = new JPanel();
		plFields.setLayout(new BoxLayout(plFields, BoxLayout.X_AXIS));
		plFields.add(plClient);
		plFields.add(plProperty);
		
        frame.getContentPane().add(BorderLayout.SOUTH, plButtons);
        frame.getContentPane().add(BorderLayout.CENTER, plFields);
        
        // Action listeners
        ActionListener alClose = new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				frame.dispose();
			}
		};

		btClose.addActionListener(alClose);
		
        // Display window
		frame.setVisible(true);
	}

	private JTextField createField(JPanel plField, String stLabel)
	{
		JLabel lbField = new JLabel(stLabel);
		JTextField txField = new JTextField();
		//plField.add(lbField, BorderLayout.WEST);
		//plField.add(txField, BorderLayout.CENTER);
		plField.setLayout(new BoxLayout(plField, BoxLayout.X_AXIS));
		plField.add(lbField);
		plField.add(txField);
		
		return txField;
	}
	
}