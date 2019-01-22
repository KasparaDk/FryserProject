package logic;

public enum ProductType {
	BABYFOOD(2, "Babymos"), BACON(1, "Bacon"), BANANA(3, "Banan"), BROCCOLI(12, "Broccoli"), BREAD(6, "Brød"),
	BERRY(12, "Bær"), MINCEDMEAT(4, "Fars"), FATFISH(3, "Fisk, Fed"), MINCEDFISH(2, "Fisk, Hakket"),
	LEANFISH(6, "Fisk, Magert"), CREAM(4, "Fløde"), ICECREAM(2, "Flødeis"), FROMAGE(3, "Fromage/Mousse"),
	FRUIT(12, "Frugt"), READYMEAL(3, "Hjemmelavet ret"), CAKE(3, "Kage"), VEGETABLES(10, "Grøntsager"),
	CARROT(12, "Gulerod"), BOILEDANDFRIEDFOWL(2, "Kogt og stegt fjerkræ"), CHICKEN(10, "Kylling"), LAMB(10, "Lammekød"),
	MILK(4, "Mælk"), BEEF(10, "Oksekød"), CHEESE45(4, "Ost, Fed(45+)"), GRATEDCHEESE(4, "Revet ost"),
	SAUSAGE(3, "Pølse"), SMOKEDMEAT(6, "Røget kød"), SEAFOOD(3, "Skaldyr"), BUTTER(3, "Smør"),
	MUSHROOM(5, "Svampe(Ristet i fedtstof)"), FATPORK(3, "Svinekød, Fed"), LEANPORK(6, "Svinekød, Magert"),
	TOMATO(12, "Tomat"), YOGHURT(2, "Yoghurt"), APPLE(8, "Æbler"), EGGYOLK(6, "Æggeblommer"),
	EGGWHITES(8, "Æggehvider");

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