package ApplicationClient;

import java.util.List;
import java.util.LinkedList;

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
	private LinkedList<Application> llApps;
	private String stTitle;
	private String stOkay; 
	
	private JTextField txCID;
	private JTextField txCName;
	private JTextField txCEmail;
	private JTextField txCCreditStatus;
	private JTextField txCIncome;
	private JTextField txCExpenses;
	private JTextField txCOffer;
	
	private JTextField txPID;
	private JTextField txPAddress;
	private JTextField txPCondition;
	private JTextField txPValue;
	
	public ApplicationWindow(JFrame frParent, UpdateType updateType, Application app, LinkedList<Application> llApps)
	{
		this.frParent = frParent;
		this.updateType = updateType;
		this.app = app;
		this.llApps = llApps;
		
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
		
		// Customer panels
		JPanel plCID = new JPanel(new BorderLayout());
		txCID = createField(plCID, "Client ID:", app.getCID());
		JPanel plCName = new JPanel(new BorderLayout());
		txCName = createField(plCName, "Name:", app.getName());
		JPanel plCEmail = new JPanel(new BorderLayout());
		txCEmail = createField(plCEmail, "Email:", app.getEmail());
		JPanel plCCreditStatus = new JPanel(new BorderLayout());
		txCCreditStatus = createField(plCCreditStatus, "Credit status:",String.valueOf(app.getCreditStatus()));
		JPanel plCIncome = new JPanel(new BorderLayout());
		txCIncome = createField(plCIncome, "Income:", String.valueOf(app.getIncome()));
		JPanel plCExpenses = new JPanel(new BorderLayout());
		txCExpenses = createField(plCExpenses, "Expenses:", String.valueOf(app.getExpenses()));
		JPanel plCOffer = new JPanel(new BorderLayout());
		txCOffer = createField(plCOffer, "Offer:", String.valueOf(app.getOffer()));
				
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
		txPID = createField(plPID, "Property ID:", app.getPID());
		JPanel plPAddress = new JPanel(new BorderLayout());
		txPAddress = createField(plPAddress, "Address:", app.getAddress());
		JPanel plPCondition = new JPanel(new BorderLayout());
		txPCondition = createField(plPCondition, "Condition:", String.valueOf(app.getInspection()));
		JPanel plPValue = new JPanel(new BorderLayout());
		txPValue = createField(plPValue, "Value:", String.valueOf(app.getAppraisal()));
				
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
        ActionListener alOkay = new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				switch (updateType)
				{
				case VIEW:
					break;
				case ADD:
					addApplication();
					break;
				case UPDATE:
					updateApplication();
					break;
				case DELETE:
					if (MainWindow.okCancel("Do you want to delete application: " + app.getID() + "?", "Delete Application"))
					{
						deleteApplication();
						frame.dispose();
					}
					break;
				}
			}
		};

		btOkay.addActionListener(alOkay);
		
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
		JTextField txField = (updateType == UpdateType.ADD) ? new JTextField() : new JTextField(stValue);
		txField.setEditable(updateType == UpdateType.ADD || updateType == UpdateType.UPDATE);

		plField.setLayout(new BoxLayout(plField, BoxLayout.X_AXIS));
		plField.add(lbField);
		plField.add(txField);
		
		return txField;
	}
	
	private void deleteApplication()
	{
		llApps.remove(app);
	}

	private void updateApplication()
	{
		app.setCID(txCID.getText());
		app.setName(txCName.getText());
		app.setEmail(txCEmail.getText());
		app.setCreditStatus(Boolean.parseBoolean(txCCreditStatus.getText()));
		app.setIncome(Integer.parseInt(txCIncome.getText()));
		app.setExpenses(Integer.parseInt(txCExpenses.getText()));
		app.setOffer(Integer.parseInt(txCOffer.getText()));
		app.setPID(txPID.getText());
		app.setAddress(txPAddress.getText());
		app.setInspection(Boolean.parseBoolean(txPCondition.getText()));
		app.setAppraisal(Integer.parseInt(txPValue.getText()));
	}

	private void addApplication()
	{
		updateApplication();
		//llApps.remove(app);
	}

}