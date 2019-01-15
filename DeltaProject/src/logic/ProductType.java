package logic;

public enum ProductType {
	SAUSAGE(90), LEANPORK(180), FATPORK(90), MINCEDMEAT(120), SMOKEDMEAT(180), CHICKEN(300), BEEF(300),
	LAMB(300), SMOKEDFISH(21), MINCEDFISH(60), FATFISH(90), LEANFISH(180), VEGETABLES(300), FRUIT(365);
//	PØLSER, MAGERTSVINEKØD, FEDTSVINEKØD, HAKKETKØD, RØGETKØD, KYLLING, OKSEKØD, LAMMEKØD,
//	RØGETFISK, FISKEFARS, FEDFISK, MAGERFISK, GRØNTSAGER, FRUGT

	private int days;

ProductType(int days) {
	this.days = days;
	
}

public int getDays() {
	return days;
}
}