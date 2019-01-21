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
	private ComboBox<ProductType> cmb;
	private Label varenavnEmpty = new Label("*");
	private Label cmbEmpty = new Label("*");
	private Menu menu;
//	private ProductTypeConverter converter = new ProductTypeConverter();

	public void start(Stage stage, Menu menu) {
		this.menu = menu;
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
		
		// Varenavn
		Label varenavnlbl = new Label("Varenavn:");
		varenavntxt = new TextField();
		varenavntxt.setMaxWidth(150);
		varenavnlbl.setText();
		

		// Varetype
		Label varetypelbl = new Label("Varetype:");
		// Dropdown menu med varetyper
//		ComboBox<ProductType> 
		cmb = new ComboBox<>();
//		cmb.setTooltip(new Tooltip());
		cmb.getItems().addAll(ProductType.values());
		cmb.setMinWidth(150);

		// Indkøbsdato
		Label indkøbsdatolbl = new Label("Indkøbsdato:");
		indkøbsdatotxt = new TextField();
		indkøbsdatotxt.setMaxWidth(150);
		// Gør tekstfeltet uneditable
		indkøbsdatotxt.setEditable(false);
		indkøbsdatotxt.setMouseTransparent(true);
		indkøbsdatotxt.setFocusTraversable(false);
		String date = LocalDate.now().format(DateTimeFormatter.ofPattern("dd MMMM - yyyy"));
		indkøbsdatotxt.setText(date);
		indkøbsdatotxt.setStyle("-fx-text-inner-color: grey;");
		indkøbsdatotxt.setStyle("-fx-control-inner-background:lightgrey");
		indkøbsdatotxt.setAlignment(Pos.CENTER);

		// Mængde
		Label mængdelbl = new Label("Mændge:");
		mængdetxt = new TextField();
		mængdetxt.setMaxWidth(150);

		// Note
		Label notelbl = new Label("Note:");
		notetxt = new TextField();
		notetxt.setMaxWidth(150);
		
		//fejlmeddelser fixes
		varenavnEmpty.setFont(new Font("Calibri", 16));
		varenavnEmpty.setTextFill(Color.RED);
		cmbEmpty.setFont(new Font("Calibri", 16));
		cmbEmpty.setTextFill(Color.RED);
		varenavnEmpty.setVisible(false);
		cmbEmpty.setVisible(false);
		
		
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
		
		txtcolumn.getChildren().addAll(varenavntxt, cmb, indkøbsdatotxt, mængdetxt, notetxt);
		txtcolumn.setSpacing(12);
		txtcolumn.setPadding(new Insets(5, 0, 0, 0));
		txtcolumn.setAlignment(Pos.CENTER_RIGHT);
		
		fejlcolumn.getChildren().addAll(varenavnEmpty, cmbEmpty);
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

	private void addProcuct() {
		if (varenavntxt.getText().isEmpty() && cmb.getValue() == null) {
			varenavnEmpty.setVisible(true);
			cmbEmpty.setVisible(true);
		}
		
		if (varenavntxt.getText().isEmpty() && cmb.getValue() != null) {
			varenavnEmpty.setVisible(true);
			cmbEmpty.setVisible(false);
		}
		
		else if (cmb.getValue() == null && !varenavntxt.getText().isEmpty()) {
			cmbEmpty.setVisible(true);
			varenavnEmpty.setVisible(false);
		}
		
		else if (cmb.getValue() != null && !varenavntxt.getText().isEmpty()){
		Product product = new Product(0, varenavntxt.getText(), LocalDate.now(), mængdetxt.getText(), cmb.getValue(), notetxt.getText());
		productController.addProduct(product);
		varenavntxt.clear();
		mængdetxt.clear();
		notetxt.clear();
		menu.productList.add(product);	
		}
	}
}