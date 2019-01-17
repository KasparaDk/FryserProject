package logic;

public enum ProductType {
	MINCEDMEAT(120, "Fars"), FATFISH(90, "Fisk, Fed"), MINCEDFISH(60, "Fisk, Hakket"), LEANFISH(180, "Fisk, Magert"), 
	FRUIT(365, "Frugt"), VEGETABLES(300, "Grøntsager"), CHICKEN(300, "Kylling"), LAMB(300, "Lammekød"), BEEF(300, "Oksekød"), 
	SAUSAGE(90, "Pølse"), SMOKEDMEAT(180, "Røget kød"), FATPORK(90, "Svinekød, Fed"), LEANPORK(180, "Svinekød, Magert");

	private int days;
	private String danishType;

ProductType(int days, String danishType) {
	this.days = days;
	this.danishType = danishType;
}

public int getDays() {
	return days;
}


public String getDanishType() {
	return danishType;
}

@Override
public String toString() {
	return danishType;
}
}