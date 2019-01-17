package presentationFX;

import java.awt.Color;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.DatabaseConnection;
import logic.Product;
import logic.ProductController;
import logic.ProductType;

public class AddProductPopUp {

	private ProductController productController = new ProductController(DatabaseConnection.newConnection("JanProjectDB"));
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
		//Vbokse til at indsætte tingene
		VBox column1 = new VBox();
		VBox column2 = new VBox();
		
		// Varenavn
		Label varenavnlbl = new Label("Varenavn:");
		varenavntxt = new TextField();
		varenavntxt.setMaxWidth(150);
		varenavntxt.setPromptText("Fx Pommes Frites");
//		HBox varenavnbox = new HBox();
//		varenavnbox.getChildren().addAll(varenavnlbl, varenavntxt);

		// Varetype
		Label varetypelbl = new Label("Varetype:");
		// Dropdown menu med varetyper
//		ComboBox<ProductType> 
		cmb = new ComboBox<>();
//		cmb.setTooltip(new Tooltip());
		cmb.getItems().addAll(ProductType.values());
		cmb.setMinWidth(150);
//		HBox varetypebox = new HBox();
//		varetypebox.getChildren().addAll(varetypelbl, cmb);

		// Indkøbsdato
		Label indkøbsdatolbl = new Label("Indkøbsdato:");
		indkøbsdatotxt = new TextField();
		indkøbsdatotxt.setMaxWidth(150);
		// Gør tekstfeltet uneditable
		indkøbsdatotxt.setEditable(false);
		indkøbsdatotxt.setMouseTransparent(true);
		indkøbsdatotxt.setFocusTraversable(false);
		String date = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MMMM - yyyy"));
		indkøbsdatotxt.setText(date);
		indkøbsdatotxt.setStyle("-fx-text-inner-color: grey;");
		indkøbsdatotxt.setStyle("-fx-control-inner-background:lightgrey");
		indkøbsdatotxt.setAlignment(Pos.CENTER);
//		HBox indkøbsdatobox = new HBox();
//		indkøbsdatobox.getChildren().addAll(indkøbsdatolbl, indkøbsdatotxt);

		// Mængde
		Label mængdelbl = new Label("Mændge:");
		mængdetxt = new TextField();
		mængdetxt.setMaxWidth(150);
		mængdetxt.setPromptText("Skrives i gram");
//		HBox mængdebox = new HBox();
//		mængdebox.getChildren().addAll(mængdelbl, mængdetxt);

		// Note
		Label notelbl = new Label("Note:");
		notetxt = new TextField();
		notetxt.setMaxWidth(150);
		notetxt.setPromptText("Fx Højre side i fryseren");
//		HBox notebox = new HBox();
//		notebox.getChildren().addAll(notelbl, notetxt);
//		notebox.setSpacing(10);
		
		//Indsætter tingene i Vboksene
		column1.getChildren().addAll(varenavnlbl, varetypelbl, indkøbsdatolbl, mængdelbl, notelbl);
		column1.setSpacing(20);
		column1.setAlignment(Pos.CENTER_RIGHT);
		
		column2.getChildren().addAll(varenavntxt, cmb, indkøbsdatotxt, mængdetxt, notetxt);
		column2.setSpacing(12);
		column2.setPadding(new Insets(0, 0, 0, 0));
		column2.setAlignment(Pos.CENTER_RIGHT);
		
		// GridPane med textfelterne
		GridPane textFields = new GridPane();
		textFields.getColumnConstraints().add(new ColumnConstraints(110));
		textFields.getColumnConstraints().add(new ColumnConstraints(183));
		textFields.add(column1, 0, 0);
		textFields.add(column2, 1, 0);

		BorderPane layout = new BorderPane();
		layout.setPadding(new Insets(20, 20, 10, 20));

		// Knapper til højre, textfelter i center
		layout.setBottom(buttonPane);
		layout.setCenter(textFields);

		Scene scene = new Scene(layout);
		popUp.setScene(scene);
		popUp.show();

//		new Dropdown<ProductType>(cmb);
	}

	private void addProcuct() {
		Product product = new Product(0, varenavntxt.getText(), indkøbsdatotxt.getText(), mængdetxt.getText(), cmb.getValue(), notetxt.getText());
		productController.addProduct(product);
//		Alert alert = new Alert(AlertType.INFORMATION, "Product" + product + "oprettet", ButtonType.OK);
//		alert.showAndWait();
		varenavntxt.clear();
		mængdetxt.clear();
		notetxt.clear();
	}
}