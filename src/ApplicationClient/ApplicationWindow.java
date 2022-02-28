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
	private Application app;
	private String stTitle;
	private String stOkay; 
	
	public ApplicationWindow(JFrame frParent, UpdateType updateType, Application app)
	{
		this.frParent = frParent;
		this.updateType = updateType;
		this.app = app;
		
		switch (updateType)
		{
		case VIEW:
			stTitle = "View Application:" + app.getID();
			stOkay = "View";
			break;
			case ADD:
				stTitle = "Add Application:" + app.getID();
				stOkay = "Add";
				break;
			case UPDATE:
				stTitle = "Update Application:" + app.getID();
				stOkay = "Update";
				break;
			case DELETE:
				stTitle = "Delete Application:" + app.getID();
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
		JTextField txCID = createField(plCID, "Client ID:", app.getCID());
		JPanel plCName = new JPanel(new BorderLayout());
		JTextField txCName = createField(plCName, "Name:", app.getName());
		JPanel plCEmail = new JPanel(new BorderLayout());
		JTextField txCEmail = createField(plCEmail, "Email:", app.getEmail());
		JPanel plCCreditStatus = new JPanel(new BorderLayout());
		JTextField txCCreditStatus = createField(plCCreditStatus, "Credit status:",String.valueOf(app.getCreditStatus()));
		JPanel plCIncome = new JPanel(new BorderLayout());
		JTextField txCIncome = createField(plCIncome, "Income:", String.valueOf(app.getIncome()));
		JPanel plCExpenses = new JPanel(new BorderLayout());
		JTextField txCExpenses = createField(plCExpenses, "Expenses:", String.valueOf(app.getExpenses()));
		JPanel plCOffer = new JPanel(new BorderLayout());
		JTextField txCOffer = createField(plCOffer, "Offer:", String.valueOf(app.getOffer()));
				
		JPanel plClient = new JPanel();
		plClient.setLayout(new BoxLayout(plClient, BoxLayout.Y_AXIS));
		plClient.add(new JLabel("Client"));
		plClient.add(plCID);
		plClient.add(plCName);
		plClient.add(plCEmail);
		plClient.add(plCCreditStatus);
		plClient.add(plCIncome);
		plClient.add(plCExpenses);
		plClient.add(plCOffer);
		
		// Property panels
		JPanel plPID = new JPanel(new BorderLayout());
		JTextField txPID = createField(plPID, "Property ID:", app.getPID());
		JPanel plPAddress = new JPanel(new BorderLayout());
		JTextField txPAddress = createField(plPAddress, "Address:", app.getAddress());
		JPanel plPCondition = new JPanel(new BorderLayout());
		JTextField txPCondition = createField(plPCondition, "Condition:", String.valueOf(app.getInspection()));
		JPanel plPValue = new JPanel(new BorderLayout());
		JTextField txPValue = createField(plPValue, "Value:", String.valueOf(app.getAppraisal()));
				
		JPanel plProperty = new JPanel();
		plProperty.setLayout(new BoxLayout(plProperty, BoxLayout.Y_AXIS));
		plProperty.add(new JLabel("Property"));
		plProperty.add(plPID);
		plProperty.add(plPAddress);
		plProperty.add(plPCondition);
		plProperty.add(plPValue);
		
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

	private JTextField createField(JPanel plField, String stLabel, String stValue)
	{
		JLabel lbField = new JLabel(stLabel);
		JTextField txField = new JTextField(stValue);
		//plField.add(lbField, BorderLayout.WEST);
		//plField.add(txField, BorderLayout.CENTER);
		plField.setLayout(new BoxLayout(plField, BoxLayout.X_AXIS));
		plField.add(lbField);
		plField.add(txField);
		
		return txField;
	}
	
}