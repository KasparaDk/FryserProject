package presentationFX;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import logic.DatabaseConnection;
import logic.Product;
import logic.ProductController;
import logic.ProductType;

public class AddProductPopUp {

	private ProductController productController = new ProductController(DatabaseConnection.newConnection("JanProjectDB"));
	private TextField productnametxt;
	private TextField buydatetxt;
	private TextField amounttxt;
	private TextField notetxt;
	private ComboBox<ProductType> producttypecmb;
	private Label productNameEmpty = new Label("*");
	private Label cmbEmpty = new Label("*");
	private Menu menu;
	private Scene popUpScene;

	public void start(Stage stage, Menu menu) {
		// Selve vinduet sættes op
		this.menu = menu;
		Stage popUp = new Stage();
		popUp.setTitle("Tilføj Vare");
		popUp.setHeight(300);
		popUp.setWidth(400);
		popUp.setResizable(false);

		
		// Knapperne sættes op
		Button add = new Button();
		add.setText("Tilføj Vare");
		add.setOnAction(e -> {
			addProduct();
		});

		Button cancel = new Button();
		cancel.setText("Annuller");
		cancel.setOnAction(e -> {
			popUp.hide();
		});
		

		

		// BorderPane til knapperne
		BorderPane buttonPane = new BorderPane();
		buttonPane.setPadding(new Insets(20, 50, 20, 50));
		buttonPane.setLeft(cancel);
		buttonPane.setRight(add);
		
		
		// Labels sættes op
		Label productnamelbl = new Label("Varenavn:");
		Label producttypelbl = new Label("Varetype:");
		Label buydatelbl = new Label("Indkøbsdato:");
		Label amountlbl = new Label("Mændge:");
		Label notelbl = new Label("Note:");
		
		// Tekstfelterne og Combobox sættes op
		// Varenavn
		productnametxt = new TextField();
		productnametxt.setMaxWidth(180);
		productnametxt.setPromptText("Fx Pommes Frites");
		menu.addTextLimiter(productnametxt, 20);
		
		// Dropdown menu med varetyper
		producttypecmb = new ComboBox<>();
		producttypecmb.getItems().addAll(ProductType.values());
		producttypecmb.setMinWidth(180);
		
		// Indkøbsdato - uneditable og rigtig format
		buydatetxt = new TextField();
		buydatetxt.setMaxWidth(180);
		buydatetxt.setEditable(false);
		buydatetxt.setMouseTransparent(true);
		buydatetxt.setFocusTraversable(false);
		String date = LocalDate.now().format(DateTimeFormatter.ofPattern("dd MMMM - yyyy"));
		buydatetxt.setText(date);
		buydatetxt.setStyle("-fx-text-inner-color: grey;");
		buydatetxt.setStyle("-fx-control-inner-background:lightgrey");
		buydatetxt.setAlignment(Pos.CENTER);
		
		// Mængde
		amounttxt = new TextField();
		amounttxt.setMaxWidth(180);
		amounttxt.setPromptText("Skrives i gram");
		menu.addTextLimiter(amounttxt, 20);
		
		// Note
		notetxt = new TextField();
		notetxt.setMaxWidth(180);
		notetxt.setPromptText("Fx Højre side i fryseren");
		menu.addTextLimiter(notetxt, 20);
		
		// Fejlmeddelser
		productNameEmpty.setFont(new Font("Calibri", 16));
		productNameEmpty.setTextFill(Color.RED);
		cmbEmpty.setFont(new Font("Calibri", 16));
		cmbEmpty.setTextFill(Color.RED);
		productNameEmpty.setVisible(false);
		cmbEmpty.setVisible(false);
		
		// Vbokse til at indsætte labels, textfelterne, og fejlmeddelserne
		VBox labelcolumn = new VBox();
		VBox txtcolumn = new VBox();
		VBox errorcolumn = new VBox();
		
		// Labels indsættes i labelcolumn
		labelcolumn.getChildren().addAll(productnamelbl, producttypelbl, buydatelbl, amountlbl, notelbl);
		labelcolumn.setSpacing(20);
		labelcolumn.setPadding(new Insets(5, 5, 0, 0));
		labelcolumn.setAlignment(Pos.CENTER_RIGHT);
		
		// Tekstfelterne indsættes i txtcolumn
		txtcolumn.getChildren().addAll(productnametxt, producttypecmb, buydatetxt, amounttxt, notetxt);
		txtcolumn.setSpacing(12);
		txtcolumn.setPadding(new Insets(5, 0, 0, 0));
		txtcolumn.setAlignment(Pos.CENTER_RIGHT);
		
		// Fejlmeddelserne indsættes i fejlcolumn
		errorcolumn.getChildren().addAll(productNameEmpty, cmbEmpty);
		errorcolumn.setPadding(new Insets(0, 0, 0, 3));
		errorcolumn.setSpacing(20);
		
		// GridPane med alle columns
		GridPane centerPane = new GridPane();
		centerPane.getColumnConstraints().add(new ColumnConstraints(110));
		centerPane.getColumnConstraints().add(new ColumnConstraints(183));
		centerPane.add(labelcolumn, 0, 0);
		centerPane.add(txtcolumn, 1, 0);
		centerPane.add(errorcolumn, 2, 0);

		// Layout laves og sættes som mainscene
		BorderPane layout = new BorderPane();
		layout.setPadding(new Insets(20, 20, 10, 20));
		popUpScene = new Scene(layout);

		// Knapper til højre, columns i center
		layout.setBottom(buttonPane);
		layout.setCenter(centerPane);

		popUp.setScene(popUpScene);
		popUp.show();

		popUpScene.setOnKeyReleased(e -> {
		if (e.getCode() == KeyCode.ESCAPE) {
			popUp.hide();
		}
	});
		
	}

	private void addProduct() {
		// Ingen varenavn + Ingen varetype
		if (productnametxt.getText().isEmpty() && producttypecmb.getValue() == null) {
			productNameEmpty.setVisible(true);
			cmbEmpty.setVisible(true);
		}
		
		// Ingen varenavn
		if (productnametxt.getText().isEmpty() && producttypecmb.getValue() != null) {
			productNameEmpty.setVisible(true);
			cmbEmpty.setVisible(false);
		}
		
		// Ingen varetype
		else if (producttypecmb.getValue() == null && !productnametxt.getText().isEmpty()) {
			cmbEmpty.setVisible(true);
			productNameEmpty.setVisible(false);
		}
		
		// Udfyldt korrekt
		else if (producttypecmb.getValue() != null && !productnametxt.getText().isEmpty()){
		menu.statuslbl.setText(productnametxt.getText() + " er blevet tilføjet");
		menu.statuslbl1.setText(productnametxt.getText() + " er blevet tilføjet");
		Product product = new Product(0, productnametxt.getText(), LocalDate.now(), amounttxt.getText(), producttypecmb.getValue(), notetxt.getText());
		productController.addProduct(product);
		productnametxt.clear();
		amounttxt.clear();
		notetxt.clear();
		menu.productList.add(product);
		}
	}
}