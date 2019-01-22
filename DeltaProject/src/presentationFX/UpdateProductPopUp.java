package presentationFX;

import java.time.format.DateTimeFormatter;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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

public class UpdateProductPopUp {

	private Stage popUp = new Stage();
	private ProductController productController = new ProductController(
			DatabaseConnection.newConnection("JanProjectDB"));
	private TextField productnametxt;
	private TextField buydatetxt;
	private TextField amounttxt;
	private TextField notetxt;
	private TextField producttypetxt;
	private Label productnameEmpty = new Label("*");
	private Menu menu;


	public void start(Stage stage, Menu menu, Product product) {
		// Selve vinduet sættes op
		this.menu = menu;
		popUp.setTitle("Opdater Vare");
		popUp.setHeight(320);
		popUp.setWidth(400);
		popUp.setResizable(false);

		// Knapperne sættes op
		Button add = new Button();
		add.setText("Opdater");
		add.setOnAction(e -> {
			updateProduct(menu, product);
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
		
		// Tekstfelterne sættes op
		productnametxt = new TextField();
		productnametxt.setMaxWidth(180);
		productnametxt.setText(product.getName());
		menu.addTextLimiter(productnametxt, 20);
		
		// Varetype - uneditable
		producttypetxt = new TextField();
		producttypetxt.setMaxWidth(180);
		producttypetxt.setText(product.getTheType().getDanishType());
		producttypetxt.setEditable(false);
		producttypetxt.setMouseTransparent(true);
		producttypetxt.setFocusTraversable(false);
		producttypetxt.setStyle("-fx-text-inner-color: grey;");
		producttypetxt.setStyle("-fx-control-inner-background:lightgrey");
		producttypetxt.setAlignment(Pos.CENTER);

		// Indkøbsdato - uneditable og rigtig format
		buydatetxt = new TextField();
		buydatetxt.setMaxWidth(180);
		buydatetxt.setText(product.getPurchaseDate().format(DateTimeFormatter.ofPattern("dd MMMM - yyyy")));
		buydatetxt.setEditable(false);
		buydatetxt.setMouseTransparent(true);
		buydatetxt.setFocusTraversable(false);
		buydatetxt.setStyle("-fx-text-inner-color: grey;");
		buydatetxt.setStyle("-fx-control-inner-background:lightgrey");
		buydatetxt.setAlignment(Pos.CENTER);

		// Mængde
		amounttxt = new TextField();
		amounttxt.setMaxWidth(180);
		amounttxt.setText(product.getAmount());
		menu.addTextLimiter(amounttxt, 20);

		// Note
		notetxt = new TextField();
		notetxt.setMaxWidth(180);
		notetxt.setText(product.getNote());
		menu.addTextLimiter(notetxt, 20);

		// fejlmeddelser fixes
		productnameEmpty.setFont(new Font("Calibri", 16));
		productnameEmpty.setTextFill(Color.RED);
		productnameEmpty.setVisible(false);

		// Tekstfelterne - Vbokse til at indsætte tingene
		VBox labelcolumn = new VBox();
		VBox txtcolumn = new VBox();
		VBox errorcolumn = new VBox();

		// Labels indsættes i labelcolumn
		labelcolumn.getChildren().addAll(productnamelbl, producttypelbl, buydatelbl, amountlbl, notelbl);
		labelcolumn.setSpacing(20);
		labelcolumn.setPadding(new Insets(5, 5, 0, 0));
		labelcolumn.setAlignment(Pos.CENTER_RIGHT);

		// Tekstfelterne indsættes i txtcolumn
		txtcolumn.getChildren().addAll(productnametxt, producttypetxt, buydatetxt, amounttxt, notetxt);
		txtcolumn.setSpacing(12);
		txtcolumn.setPadding(new Insets(5, 0, 0, 0));
		txtcolumn.setAlignment(Pos.CENTER_RIGHT);

		// Fejlmeddelserne indsættes i fejlcolumn
		errorcolumn.getChildren().addAll(productnameEmpty);
		errorcolumn.setPadding(new Insets(0, 0, 0, 3));
		errorcolumn.setSpacing(20);

		// GridPane med textfelterne
		GridPane centerPane = new GridPane();
		centerPane.getColumnConstraints().add(new ColumnConstraints(110));
		centerPane.getColumnConstraints().add(new ColumnConstraints(183));
		centerPane.add(labelcolumn, 0, 0);
		centerPane.add(txtcolumn, 1, 0);
		centerPane.add(errorcolumn, 2, 0);

		BorderPane layout = new BorderPane();
		layout.setPadding(new Insets(20, 20, 10, 20));
		Scene scene = new Scene(layout);

		// Knapper til højre, columns i center
		layout.setBottom(buttonPane);
		layout.setCenter(centerPane);

		popUp.setScene(scene);
		popUp.show();

	}

	private void updateProduct(Menu menu, Product product) {
		// Ingen varenavn
		if (productnametxt.getText().isEmpty()) {
			productnameEmpty.setVisible(true);
		}

		// Udfyldt korrekt
		else if (!productnametxt.getText().isEmpty()) {
			menu.statuslbl.setText(productnametxt.getText() + " er blevet opdateret");
			menu.statuslbl1.setText(productnametxt.getText() + " er blevet opdateret");
			product.setName(productnametxt.getText());
			product.setAmount(amounttxt.getText());
			product.setNote(notetxt.getText());
			productController.updateProduct(product);
			menu.tbvOverview.refresh();
			popUp.close();
		}
	}
}