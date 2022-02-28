package ApplicationClient;

import java.util.Iterator;

public class Application
{
    private String stID;

    private class Data
    {
    	private String stCID;
        private String stName;
        private String stEmail;
    	private boolean bCreditStatus;
        private int iIncome;
        private int iExpenses;
        private int iOffer;
    	
    	private String stPID;
        private String stAddress;
        private boolean bInspection;
        private int iAppraisal;

        Data()
        {
        	this.stCID = "";
        	this.stName = "";
            this.stEmail = "";
            this.bCreditStatus = false;
            this.iIncome = 0;
            this.iExpenses = 0;
            this.iOffer = 0;

        	this.stPID = "";
            this.stAddress = "";
            this.bInspection = false;
            this.iAppraisal = 0;
        }
    	
        Data(String stCID, String stName, String stEmail, boolean bCreditStatus, int iIncome, int iExpenses, int iOffer,
        	 String stPID, String stAddress, boolean bInspection, int iAppraisal)
        {
            this.stCID = stCID;
            this.stName = stName;
            this.stEmail = stEmail;
            this.bCreditStatus = bCreditStatus;
            this.iIncome = iIncome;
            this.iExpenses = iExpenses;
            this.iOffer = iOffer;

            this.stPID = stPID;
            this.stAddress = stAddress;
            this.bInspection = bInspection;
            this.iAppraisal = iAppraisal;
        }
    }
    private Data data;
    
    Application()
    {
        this.stID = "";
        this.data = new Data();
    }

    Application(String stID,
    			String stCID, String stName, String stEmail, boolean bCreditStatus, int iIncome, int iExpenses, int iOffer, 
    			String stPID, String stAddress, boolean bInspection, int iAppraisal)
    {
        this.stID = stID;
        this.data = new Data(stCID, stName, stEmail, bCreditStatus, iIncome, iExpenses, iOffer,
        					 stPID, stAddress, bInspection, iAppraisal);
    }
    
    public String getID()
    {
        return stID;
    }

    public String getCID()
    {
        return data.stCID;
    }

    public String getName()
    {
        return data.stName;
    }

    public String getEmail()
    {
        return data.stEmail;
    }

    public boolean getCreditStatus()
    {
        return data.bCreditStatus;
    }

    public int getIncome()
    {
        return data.iIncome;
    }

    public int getExpenses()
    {
        return data.iExpenses;
    }

    public int getOffer()
    {
        return data.iOffer;
    }

    public String getPID()
    {
        return data.stPID;
    }

    public String getAddress()
    {
        return data.stAddress;
    }

    public boolean getInspection()
    {
        return data.bInspection;
    }

    public int getAppraisal()
    {
        return data.iAppraisal;
    }

    public Data getData()
    {
        return data;
    }

    public void setID(String stID)
    {
        this.stID = stID;
    }

    public void setCID(String stCID)
    {
        this.data.stCID = stCID;
    }

    public void setName(String stName)
    {
        this.data.stName = stName;
    }

    public void setEmail(String stEmail)
    {
        this.data.stEmail = stEmail;
    }

    public void setCreditStatus(boolean bCreditStatus)
    {
        this.data.bCreditStatus = bCreditStatus;
    }

    public void setIncome(int iIncome)
    {
        this.data.iIncome = iIncome;
    }

    public void setExpenses(int iExpenses)
    {
        this.data.iExpenses = iExpenses;
    }

    public void setOffer(int iOffer)
    {
        this.data.iOffer = iOffer;
    }

    public void setPID(String stPID)
    {
        this.data.stPID = stPID;
    }

    public void setAddress(String stAddress)
    {
        this.data.stAddress = stAddress;
    }

    public void setInspection(boolean bInspection)
    {
        this.data.bInspection = bInspection;
    }

    public void setAppraisal(int iAppraisal)
    {
        this.data.iAppraisal = iAppraisal;
    }

    public void setData(Data data)
    {
        this.data = data;
    }

    public void set(String stID, Data data)
    {
        this.stID = stID;
        this.data = data;
    }

    @Override
     public String toString()
     {
         return "Application: " + stID + " " +
        		 data.stCID + " " + data.stName + " " + data.stEmail + " " + data.bCreditStatus + " " + data.iIncome + " " + data.iExpenses + " " + data.iOffer + " " +
        		 data.stPID + " " + data.stAddress + " " + data.bInspection + " " + data.iAppraisal;
     }
}
