package presentationFX;

import javafx.util.StringConverter;
import logic.ProductType;

public class ProductTypeConverter extends StringConverter<ProductType> {

	@Override
	public ProductType fromString(String type) {
		switch (type) {
		case "Pølse":
			return ProductType.SAUSAGE;
		case "Magert svinekød":
			return ProductType.LEANPORK;
		case "Fedt svinekød":
			return ProductType.FATPORK;
		case "Hakket kød":
			return ProductType.MINCEDMEAT;
		case "Røget kød":
			return ProductType.SMOKEDMEAT;
		case "Kylling":
			return ProductType.CHICKEN;
		case "Oksekød":
			return ProductType.BEEF;
		case "Lammekød":
			return ProductType.LAMB;
		case "Fiskefars":
			return ProductType.MINCEDFISH;
		case "Fed fisk":
			return ProductType.FATFISH;
		case "Mager fisk":
			return ProductType.LEANFISH;
		default:	
			return null;
	}
	}
	@Override
	public String toString(ProductType type) {
		switch (type) {
		case SAUSAGE:
			return "Pølse";
		case LEANPORK:
			return "Magert svinekød";
		case FATPORK:
			return "Fedt svinekød";
		case MINCEDMEAT:
			return "Hakket kød";
		case SMOKEDMEAT:
			return "Røget kød";
		case CHICKEN:
			return "Kylling";
		case BEEF:
			return "Oksekød";
		case LAMB:
			return "Lammekød";
		case MINCEDFISH:
			return "Fiskefars";
		case FATFISH:
			return "Fed fisk";
		case LEANFISH:
			return "Mager fisk";
		default:
			return null;
	}
	}
}
