import java.util.ArrayList;
import java.util.List;

public class Receipt {

    private int receiptNumber;
    private String receiptDate;
    private String receiptName;
    private String receiptType;
    private String receiptDescription;
    private double receiptAmount;
    private double receiptTaxRate;
    private double receiptTaxAmount;
    private double receiptTip;
    private String receiptStatus;
    private static List<Receipt> receiptList = new ArrayList<>();

    public Receipt(String receiptDate, String receiptName, String receiptType, String receiptDescription, double receiptAmount, double receiptTip) {
        this.receiptDate = receiptDate;
        this.receiptName = receiptName;
        this.receiptType = receiptType;
        this.receiptDescription = receiptDescription;
        this.receiptAmount = receiptAmount;
        this.receiptTip = receiptTip;
        this.receiptTaxRate = 0.07;
        this.receiptTaxAmount = generateTax();
        this.receiptNumber = receiptList.size() + 1;
        this.receiptStatus = "completed";
        receiptList.add(this);
    }

    public double getReceiptTotal() {
        return receiptAmount + receiptTaxAmount + receiptTip;
    }

    public double generateTax() {
        return receiptAmount * receiptTaxRate;
    }

    public int getReceiptNumber() {
        return receiptNumber;
    }

    public void setReceiptNumber(int receiptNumber) {
        this.receiptNumber = receiptNumber;
    }

    public String getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(String receiptDate) {
        this.receiptDate = receiptDate;
    }

    public String getReceiptName() {
        return receiptName;
    }

    public void setReceiptName(String receiptName) {
        this.receiptName = receiptName;
    }

    public String getReceiptType() {
        return receiptType;
    }

    public void setReceiptType(String receiptType) {
        this.receiptType = receiptType;
    }

    public String getReceiptDescription() {
        return receiptDescription;
    }

    public void setReceiptDescription(String receiptDescription) {
        this.receiptDescription = receiptDescription;
    }

    public double getReceiptAmount() {
        return receiptAmount;
    }

    public void setReceiptAmount(double receiptAmount) {
        this.receiptAmount = receiptAmount;
    }

    public double getReceiptTaxRate() {
        return receiptTaxRate;
    }

    public void setReceiptTaxRate(double receiptTaxRate) {
        this.receiptTaxRate = receiptTaxRate;
    }

    public double getReceiptTaxAmount() {
        return receiptTaxAmount;
    }

    public void setReceiptTaxAmount(double receiptTaxAmount) {
        this.receiptTaxAmount = receiptTaxAmount;
    }

    public double getReceiptTip() {
        return receiptTip;
    }

    public void setReceiptTip(double receiptTip) {
        this.receiptTip = receiptTip;
    }

    public String getReceiptStatus() {
        return receiptStatus;
    }

    public void setReceiptStatus(String receiptStatus) {
        this.receiptStatus = receiptStatus;
    }

    public static List<Receipt> getReceiptList() {
        return receiptList;
    }

    public static void setReceiptList(List<Receipt> receiptList) {
        Receipt.receiptList = receiptList;
    }

    @Override
    public String toString() {
        return "receiptNumber:" + receiptNumber + "\n" +
                "receiptDate:" + receiptDate + "\n" +
                "receiptName:" + receiptName + "\n" +
                "receiptType:" + receiptType + "\n" +
                "receiptDescription:" + receiptDescription + "\n" +
                "receiptAmount:" + receiptAmount + "\n" +
                "receiptTaxRate:" + receiptTaxRate + "\n" +
                "receiptTaxAmount:" + receiptTaxAmount + "\n" +
                "receiptTip:" + receiptTip + "\n" +
                "receiptStatus:" + receiptStatus + "\n" +
                "-------------------------\n";
    }
}
