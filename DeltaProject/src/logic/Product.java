package logic;

public class Product {

	private int vareID;
	private String name;
	private String purchaseDate;
	private String amount;
	private ProductType type;
	private String note;

	public Product(int vareID, String name, String purchaseDate, String amount, ProductType type, String note) {
		this.vareID = vareID;
		this.name = name;
		this.purchaseDate = purchaseDate;
		this.amount = amount;
		this.type = type;
		this.note = note;
	}

	public void setVareID(int vareID) {
		this.vareID = vareID;
	}

	public int getVareID() {
		return vareID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getType() {
		return this.type.name();
	}

	public void setType(String type) {
		this.type = ProductType.valueOf(type);
	}
	
	public ProductType getTheType() {
		return type;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return "Product [vareID=" + vareID + ", name=" + name + ", purchaseDate=" + purchaseDate + ", amount=" + amount
				+ ", type=" + type + ", note=" + note + "]";
	}

}
