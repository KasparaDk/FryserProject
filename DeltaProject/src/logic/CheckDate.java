//package logic;
//
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.time.temporal.ChronoUnit;
//
//public class CheckDate {
//
////	public LocalDate convertStringToDate(Product product) {
////		// Convert string to LocalDate
////		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
////		LocalDate localDateObj = LocalDate.parse(product.getPurchaseDate(), dateTimeFormatter);
////
////		// Opretter et expireDate ud fra den konveterede LocalDate fra ProductType klassen
////		LocalDate expireDate = localDateObj.plusDays(product.getTheType().getDays());
////
////		return expireDate;
////	}
//
//	public void checExpirekDate(Product product) {
//
////		LocalDate expireDate;
////
////		expireDate = convertStringToDate(product);
//		LocalDate expireDate = purchaseDate.plusDays(type.getDays());
//		
//		if (product.getTheType().getDays() > 0 && LocalDate.now().isAfter(expireDate)) {
//			System.out.println(product.getName() + " er nu blevet for gamle!"
//					+ "\nDet anbefales at smide det ud, af sundhedsmæssige grunde");
//		}
//
//		// Hvis der er 3 dage eller under før det bliver for gammelt
//		else if (product.getTheType().getDays() > 3 && LocalDate.now().isAfter(expireDate.minusDays(3))) {
//			System.out.println(product.getName() + " er rigtig tæt på at blive for gamle!"
//					+ "\nDu har nu ca. 3 dage før de er ubrugelige");
//
//		}
//		// Hvis der er 14 dage eller under før det bliver for gammelt
//		else if (product.getTheType().getDays() > 14 && LocalDate.now().isAfter(expireDate.minusDays(14))) {
//			System.out.println(product.getName() + " er tættere på at blive for gamle!"
//					+ "\nDu har ca. 14 dage før de er ubrugelige");
//		}
//		// Hvis der er 30 dage eller under før det blive for gammelt
//		else if (product.getTheType().getDays() > 30 && LocalDate.now().isAfter(expireDate.minusDays(30))) {
//			System.out.println(
//					product.getName() + " er ved at blive for gamle!" + "\nDu har ca. 30 dage før de er ubrugelige");
//		}
//
//	}
//
//	public void getExpireDate(Product product) {
//
//		LocalDate expireDate;
//
//		expireDate = convertStringToDate(product);
//
//		DateTimeFormatter expireDateFormatter = DateTimeFormatter.ofPattern("dd/MMMM - yyyy");
//
//		String formattedExpireDate = expireDate.format(expireDateFormatter);
//		// Printer den udløbsdatoen ud
////		System.out.println(expireDate.toString());
//		System.out.println(formattedExpireDate);
//	}
//
//	public long daysBetweenTwoDates(Product product) {
//
//		LocalDate dateToday;
//		LocalDate expireDate;
//		
//		dateToday = LocalDate.now();
//		expireDate = convertStringToDate(product);
//		
//		long daysBetween = ChronoUnit.DAYS.between(dateToday, expireDate);
//		
//		System.out.println(daysBetween);
//	
//		return daysBetween;
//	}
//	
//
//}
