import java.util.ArrayList;

public class Receipt {

    private int receiptNumber;
    private String receiptDate;
    private String receiptName;
    private String receiptType;
    private double receiptAmount;
    private String receiptDescription;
    private String receiptStatus; // 1. completed, 2. returned
    private double receiptTaxRate;
    private double receiptTaxAmount;
    private double receiptTip;
    private static ArrayList<Receipt> receiptList = new ArrayList<>();

    public Receipt(String receiptDate, String receiptName, String receiptType,
                   String receiptDescription,double receiptAmount, double receiptTip) {

        this.receiptDate = receiptDate;
        this.receiptName = receiptName;
        this.receiptType = receiptType;
        this.receiptAmount = receiptAmount;
        this.receiptDescription = receiptDescription;
        this.receiptTaxRate = 0.13;
        this.receiptTip = receiptTip;
        this.receiptStatus = "completed";
        generateTax();

        receiptList.add(this);
        this.receiptNumber = receiptList.size();
    }

    public double getReceiptTotal() {
        return receiptAmount+receiptTaxAmount+receiptTip;
    }
    public String getReceiptType() {
        return receiptType;
    }

    public String getReceiptDate() {
        return receiptDate;
    }
    public void generateTax() {
        receiptAmount = receiptAmount * receiptTaxRate;
    }

    public static ArrayList<Receipt> getReceiptList() {
        return receiptList;
    }

    @Override
    public String toString() {
        return "\n---------Receipt---------" +
                "\nreceiptNumber:" + receiptNumber +
                "\nreceiptDate:" + receiptDate +
                "\nreceiptName:" + receiptName +
                "\nreceiptType:" + receiptType +
                "\nreceiptDescription:" + receiptDescription +
                "\nreceiptAmount:" + receiptAmount +
                "\nreceiptTaxAmount:" + receiptTaxAmount +
                "\nreceiptTip:" + receiptTip +
                "\nreceiptTotalAmount:" + (receiptAmount+receiptTaxAmount+receiptTip) +
                "\nreceiptStatu:" + receiptStatus +
                "\n-------------------------\n";
    }

    public String getReceiptName() {
        return receiptName;
    }

    public void setReceiptNumber(int receiptNumber) {
        this.receiptNumber = receiptNumber;
    }


    public void setReceiptStatus(String receiptStatus) {
        this.receiptStatus = receiptStatus;
    }
}
