package logic;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CheckDate {

	public void checExpirekDate(Product product) {
		// Convert string to LocalDate
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate localDateObj = LocalDate.parse(product.getPurchaseDate(), dateTimeFormatter);
		
		// Opretter et expireDate ud fra den konveterede LocalDate fra ProductType klassen
		LocalDate expireDate = localDateObj.plusDays(product.getTheType().getDays());

		if (product.getTheType().getDays() > 0 && LocalDate.now().isAfter(expireDate)) {
			System.out.println(
					product.getName() + " er nu blevet for gamle!" + "\nDet anbefales at smide det ud, af sundhedsmæssige grunde");
		}
		
		// Hvis der er 3 dage eller under før det bliver for gammelt
		else if (product.getTheType().getDays() > 3 && LocalDate.now().isAfter(expireDate.minusDays(3))) {
			System.out.println(
					product.getName() + " er rigtig tæt på at blive for gamle!" + "\nDu har nu ca. 3 dage før de er ubrugelige");

		}
		// Hvis der er 14 dage eller under før det bliver for gammelt
		else if (product.getTheType().getDays() > 14 && LocalDate.now().isAfter(expireDate.minusDays(14))) {
			System.out.println(
					product.getName() + " er tættere på at blive for gamle!" + "\nDu har ca. 14 dage før de er ubrugelige");
		}
		// Hvis der er 30 dage eller under før det blive for gammelt
		else if (product.getTheType().getDays() > 30 && LocalDate.now().isAfter(expireDate.minusDays(30))) {
			System.out.println(
					product.getName() + " er ved at blive for gamle!" + "\nDu har ca. 30 dage før de er ubrugelige");
		}
		
		
	}
	
	
	
}
