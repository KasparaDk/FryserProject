package logic;

public enum ProductType {
	SAUSAGE(90, "Pølse"), LEANPORK(180, "Mager Svinekød"), FATPORK(90, "Fedt Svinekød"),
	MINCEDMEAT(120, "Hakket kød"), SMOKEDMEAT(180, "Røget kød"), CHICKEN(300, "Kylling"), BEEF(300, "Bøf"),
	LAMB(300, "Lammekød"), MINCEDFISH(60, "Hakket fisk"), FATFISH(90, "Fed fisk"),
	LEANFISH(180, "Mager fisk"), VEGETABLES(300, "Grøntsager"), FRUIT(365, "Frugt");
//	PØLSER, MAGERTSVINEKØD, FEDTSVINEKØD, HAKKETKØD, RØGETKØD, KYLLING, OKSEKØD, LAMMEKØD,
//	RØGETFISK, FISKEFARS, FEDFISK, MAGERFISK, GRØNTSAGER, FRUGT

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