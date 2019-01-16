package presentationFX;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.Product;
import logic.ProductController;
import logic.ProductType;

public class AddProductPopUp {

	private ProductController productController;
	private TextField varenavntxt;
	private TextField indkøbsdatotxt;
	private TextField mængdetxt;
	private TextField notetxt;
	private ComboBox<ProductType> cmb;
//	private ProductTypeConverter converter = new ProductTypeConverter();

	// Liste over varetyper
	private static final String[] liste = { "Fisk - Fed", "Fisk - Mager", "Fisk - Hakket", "Frugt", "Grøntsager",
			"Hakkekød", "Kalvekød", "Kylling", "Lam", "Pølser", "Røget kød", "Svinekød - Fed", "Svinekød - Mager" };

	public void start(Stage stage) {
		Stage popUp = new Stage();
		popUp.setTitle("Tilføj Vare");
		popUp.setHeight(300);
		popUp.setWidth(400);
		popUp.setResizable(false);

		// Knapperne
		Button tilføj = new Button();
		tilføj.setText("Tilføj Vare");
		tilføj.setOnAction(e -> {
			addProcuct();
		});

		Button annuller = new Button();
		annuller.setText("Annuller");
		annuller.setOnAction(e -> {
			popUp.hide();
		});

		// BorderPane til knapperne
		BorderPane buttonPane = new BorderPane();
		buttonPane.setPadding(new Insets(20, 50, 20, 50));
		buttonPane.setLeft(annuller);
		buttonPane.setRight(tilføj);

//		VBox buttonVBox = new VBox();
//		buttonVBox.getChildren().addAll(tilføj, annuller);

		// Teksfelterne
		// Varenavn
		Label varenavnlbl = new Label("Varenavn:");
		varenavntxt = new TextField();
		varenavntxt.setMaxWidth(100);
		HBox varenavnbox = new HBox();
		varenavnbox.getChildren().addAll(varenavnlbl, varenavntxt);
//		varenavnbox.setSpacing(10);

		// Varetype
		Label varetypelbl = new Label("Varetype:");
		// Dropdown menu med varetyper
		ComboBox<ProductType> cmb = new ComboBox<>();
		cmb.setTooltip(new Tooltip());
		cmb.getItems().addAll(ProductType.values());
		HBox varetypebox = new HBox();
		varetypebox.getChildren().addAll(varetypelbl, cmb);

		// Indkøbsdato
		Label indkøbsdatolbl = new Label("Indkøbsdato:");
		indkøbsdatotxt = new TextField();
		indkøbsdatotxt.setMaxWidth(100);
		// Gør tekstfeltet uneditable
		indkøbsdatotxt.setEditable(false);
		indkøbsdatotxt.setMouseTransparent(true);
		indkøbsdatotxt.setFocusTraversable(false);
		String date = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		indkøbsdatotxt.setText(date);
		indkøbsdatotxt.setStyle("-fx-text-inner-color: grey;");
		HBox indkøbsdatobox = new HBox();
		indkøbsdatobox.getChildren().addAll(indkøbsdatolbl, indkøbsdatotxt);

		// Mængde
		Label mængdelbl = new Label("Mændge:");
		mængdetxt = new TextField();
		HBox mængdebox = new HBox();
		mængdebox.getChildren().addAll(mængdelbl, mængdetxt);

		// Note
		Label notelbl = new Label("Note:");
		notetxt = new TextField();
		notetxt.setMaxWidth(200);

		HBox notebox = new HBox();
		notebox.getChildren().addAll(notelbl, notetxt);
		notebox.setSpacing(10);

		// GridPane med textfelterne
		VBox textFields = new VBox();
		textFields.setAlignment(Pos.CENTER_RIGHT);
		textFields.getChildren().addAll(varenavnbox, varetypebox, indkøbsdatobox, mængdebox, notebox);

//		textFields.add(varenavnbox, 0, 0);
//		textFields.add(varetypebox, 0, 1);
//		textFields.add(indkøbsdatobox, 0, 2);
//		textFields.add(notebox, 0, 3);
//		textFields.setHgap(20);
//		textFields.setVgap(20);

		BorderPane layout = new BorderPane();
		layout.setPadding(new Insets(20, 20, 10, 20));

		// Knapper til højre, textfelter i center
		layout.setBottom(buttonPane);
		layout.setCenter(textFields);

		Scene scene = new Scene(layout);
		popUp.setScene(scene);
		popUp.show();

		new Dropdown<ProductType>(cmb);
	}

	private void addProcuct() {
		Product product = new Product(0, varenavntxt.getText(), indkøbsdatotxt.getText(), mængdetxt.getText(), cmb.getValue(), notetxt.getText());
		productController.addProduct(product);
		Alert alert = new Alert(AlertType.INFORMATION, "Product" + product + "oprettet", ButtonType.OK);
		alert.showAndWait();
		varenavntxt.clear();
		mængdetxt.clear();
		notetxt.clear();
	}
}