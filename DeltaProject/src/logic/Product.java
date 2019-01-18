package logic;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Product {

	private int vareID;
	private String name;
	private LocalDate purchaseDate;
	private String amount;
	private ProductType type;
	private String note;

	public Product(int vareID, String name, LocalDate purchaseDate, String amount, ProductType type, String note) {
		this.vareID = vareID;
		this.name = name;
		this.purchaseDate = purchaseDate;
		this.amount = amount;
		this.type = type;
		this.note = note;
	}
// Test
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

	public LocalDate getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(long purchaseDate) {
		this.purchaseDate.toEpochDay();
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

	public int daysBetweenTwoDates() {

		LocalDate dateToday = LocalDate.now();
		LocalDate expireDate = purchaseDate.plusMonths(type.getMonths());

		int daysBetween = (int) ChronoUnit.DAYS.between(dateToday, expireDate);


		return daysBetween;
	}

	public LocalDate getExpireDate() {

		LocalDate expireDate = getPurchaseDate().plusMonths(type.getMonths());

		return expireDate;
	}

	public void checkDate() {

		LocalDate expireDate = purchaseDate.plusMonths(type.getMonths());

		if (daysBetweenTwoDates() < 0 && LocalDate.now().isAfter(expireDate)) {
			System.out.println(getName() + " er nu blevet for gamle!"
					+ "\nDet anbefales at smide det ud, af sundhedsmæssige grunde");
		}

		// Hvis der er 3 dage eller under før det bliver for gammelt
		else if (daysBetweenTwoDates() < 3 && LocalDate.now().isAfter(expireDate.minusDays(3))) {
			System.out.println(getName() + " er rigtig tæt på at blive for gamle!" + "\nDu har nu ca. "
					+ daysBetweenTwoDates() + " dage før de er ubrugelige");

		} else if (daysBetweenTwoDates() < 14 && LocalDate.now().isAfter(expireDate.minusDays(14))) {
			System.out.println(getName() + " er tæt på at blive for gamle!" + "\nDu har ca. " + daysBetweenTwoDates()
					+ " dage før de er ubrugelige");

		} else if (daysBetweenTwoDates() < 30 && LocalDate.now().isAfter(expireDate.minusDays(30))) {
			System.out.println(getName() + " er ved at blive for gamle!" + "\nDu har ca. " + daysBetweenTwoDates()
					+ " dage før de er ubrugelige");

		}

	}

	@Override
	public String toString() {
		return "Product [vareID=" + vareID + ", name=" + name + ", purchaseDate=" + purchaseDate + ", amount=" + amount
				+ ", type=" + type + ", note=" + note + "]";
	}

}
