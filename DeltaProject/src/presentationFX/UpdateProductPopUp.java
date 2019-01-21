package presentationFX;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
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
import logic.ProductType;

public class UpdateProductPopUp {

	private ProductController productController = new ProductController(DatabaseConnection.newConnection("JanProjectDB"));
	private TextField varenavntxt;
	private TextField indkøbsdatotxt;
	private TextField mængdetxt;
	private TextField notetxt;
	private TextField varetypetxt;
	private Label varenavnEmpty = new Label("*");
	private Menu menu;
	private TableView tbvOverview;
//	private ProductTypeConverter converter = new ProductTypeConverter();

	//test
	
	public void start(Stage stage, Menu menu, Product product) {
		this.menu = menu;
		this.tbvOverview = tbvOverview;
		Stage popUp = new Stage();
		popUp.setTitle("Opdater Vare");
		popUp.setHeight(300);
		popUp.setWidth(400);
		popUp.setResizable(false);
		// Test		
		// Knapperne
		Button tilføj = new Button();
		tilføj.setText("Opdater");
		tilføj.setOnAction(e -> {
			updateProduct(menu, product);
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
		
		// Varenavn
		Label varenavnlbl = new Label("Varenavn:");
		varenavntxt = new TextField();
		varenavntxt.setMaxWidth(150);
		varenavntxt.setText(product.getName());
		

		// Varetype
		Label varetypelbl = new Label("Varetype:");
		varetypetxt = new TextField();
		varetypetxt.setMaxWidth(150);
		varetypetxt.setText(product.getTheType().getDanishType());
		// Gør tekstfeltet uneditable
		varetypetxt.setEditable(false);
		varetypetxt.setMouseTransparent(true);
		varetypetxt.setFocusTraversable(false);
		varetypetxt.setStyle("-fx-text-inner-color: grey;");
		varetypetxt.setStyle("-fx-control-inner-background:lightgrey");
		varetypetxt.setAlignment(Pos.CENTER);

		// Indkøbsdato
		Label indkøbsdatolbl = new Label("Indkøbsdato:");
		indkøbsdatotxt = new TextField();
		indkøbsdatotxt.setMaxWidth(150);
		indkøbsdatotxt.setText(product.getPurchaseDate().format(DateTimeFormatter.ofPattern("dd MMMM - yyyy")));
		// Gør tekstfeltet uneditable
		indkøbsdatotxt.setEditable(false);
		indkøbsdatotxt.setMouseTransparent(true);
		indkøbsdatotxt.setFocusTraversable(false);
		indkøbsdatotxt.setStyle("-fx-text-inner-color: grey;");
		indkøbsdatotxt.setStyle("-fx-control-inner-background:lightgrey");
		indkøbsdatotxt.setAlignment(Pos.CENTER);

		// Mængde
		Label mængdelbl = new Label("Mændge:");
		mængdetxt = new TextField();
		mængdetxt.setMaxWidth(150);
		mængdetxt.setText(product.getAmount());

		// Note
		Label notelbl = new Label("Note:");
		notetxt = new TextField();
		notetxt.setMaxWidth(150);
		notetxt.setText(product.getNote());
		
		//fejlmeddelser fixes
		varenavnEmpty.setFont(new Font("Calibri", 16));
		varenavnEmpty.setTextFill(Color.RED);
		varenavnEmpty.setVisible(false);
		
		
		// Teksfelterne
		//Vbokse til at indsætte tingene
		VBox labelcolumn = new VBox();
		VBox txtcolumn = new VBox();
		VBox fejlcolumn = new VBox();
		
		//Indsætter tingene i Vboksene
		labelcolumn.getChildren().addAll(varenavnlbl, varetypelbl, indkøbsdatolbl, mængdelbl, notelbl);
		labelcolumn.setSpacing(20);
		labelcolumn.setPadding(new Insets(5, 0, 0, 0));
		labelcolumn.setAlignment(Pos.CENTER_RIGHT);
		
		txtcolumn.getChildren().addAll(varenavntxt, varetypetxt, indkøbsdatotxt, mængdetxt, notetxt);
		txtcolumn.setSpacing(12);
		txtcolumn.setPadding(new Insets(5, 0, 0, 0));
		txtcolumn.setAlignment(Pos.CENTER_RIGHT);
		
		fejlcolumn.getChildren().addAll(varenavnEmpty);
		fejlcolumn.setPadding(new Insets(0, 0, 0, 3));
		fejlcolumn.setSpacing(20);
		
		// GridPane med textfelterne
		GridPane textFields = new GridPane();
		textFields.getColumnConstraints().add(new ColumnConstraints(110));
		textFields.getColumnConstraints().add(new ColumnConstraints(183));
		textFields.add(labelcolumn, 0, 0);
		textFields.add(txtcolumn, 1, 0);
		textFields.add(fejlcolumn, 2, 0);

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

	private void updateProduct(Menu menu, Product product) {
		//Fejlmeddelse hvis der ikke er et varenavn
		if (varenavntxt.getText().isEmpty()) {
			varenavnEmpty.setVisible(true);
		}
		
		else if (!varenavntxt.getText().isEmpty()){
			product.setName(varenavntxt.getText());
			product.setAmount(mængdetxt.getText());
			product.setNote(notetxt.getText());
			productController.updateProduct(product);
			menu.tbvOverview.refresh();

		
		// Giv feedback på hvad der er blevet opdateret
//			if (product.getName() != varenavntxt.getText()) {
//				System.out.println("varenavn er opdateret");
//			}
//			
//			if (product.getAmount() != mængdetxt.getText()) {
//				System.out.println("mængde er opdateret");
//			}
//			
//			if (product.getNote() != notetxt.getText()) {
//				System.out.println("Note opdateret");
//			}
//			
//			if (product.getName() != varenavntxt.getText() && product.getAmount() != mængdetxt.getText()) {
//				System.out.println("navn og mængde opdateret");
//			}
//			
//			if (product.getName() != varenavntxt.getText() && product.getNote() != notetxt.getText()) {
//				System.out.println("Navn og Note opdateret");
//			}
//			
//			if (product.getAmount() != mængdetxt.getText() && product.getNote() != notetxt.getText()) {
//				System.out.println("Mængde og note opdateret");
//			}
//			
//			if (product.getName() != varenavntxt.getText() && product.getAmount() != mængdetxt.getText() && product.getNote() != notetxt.getText()) {
//				System.out.println("Navn, mængde og note opdateret");
//			}
			//slet
		}
	}
}