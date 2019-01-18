package logic;

public enum ProductType {
	MINCEDMEAT(4, "Fars"), FATFISH(3, "Fisk, Fed"), MINCEDFISH(2, "Fisk, Hakket"), LEANFISH(6, "Fisk, Magert"),
	FRUIT(12, "Frugt"), READYMEAL(3, "Færdig Ret"), VEGETABLES(10, "Grøntsager"), CHICKEN(10, "Kylling"),
	LAMB(10, "Lammekød"), BEEF(10, "Oksekød"), SAUSAGE(3, "Pølse"), SMOKEDMEAT(6, "Røget kød"),
	FATPORK(3, "Svinekød, Fed"), LEANPORK(6, "Svinekød, Magert");

	private int months;
	private String danishType;

	ProductType(int months, String danishType) {
		this.months = months;
		this.danishType = danishType;
	}

	public int getMonths() {
		return months;
	}

	public String getDanishType() {
		return danishType;
	}

	@Override
	public String toString() {
		return danishType;
	}
}