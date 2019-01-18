package presentationFX;

import java.time.format.DateTimeFormatter;

import javax.swing.text.TabableView;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import logic.DatabaseConnection;
import logic.Product;
import logic.ProductController;

public class Menu {
	ObservableList<Product> productList;
	TextField tf;
	Stage stage;
	Scene start;
	public TableView tableJanProject = new TableView();
	private ProductController productController = new ProductController(DatabaseConnection.newConnection("JanProjectDB"));

	public void start(Stage stage) {

		stage.setTitle("Januar Projekt");
		stage.setWidth(1500);
		stage.setHeight(550);

		BorderPane borderPaneStart = new BorderPane();
		start = new Scene(borderPaneStart);

		GridPane gridRight = new GridPane();

		gridRight.setPadding(new Insets(0, 20, 20, 20));
		gridRight.setVgap(10);

		HBox hboxSearch = new HBox();

		// vores søgefelt
		TextField textSearch = new TextField();
//		textSearch.setMaxSize(2000, 2000);
		textSearch.setAlignment(Pos.CENTER);
		textSearch.setPromptText("Søg efter en vare");
		textSearch.setFocusTraversable(false);
		textSearch.setStyle("-fx-padding: 10 100 10 100;");
		hboxSearch.setAlignment(Pos.CENTER);
		hboxSearch.getChildren().addAll(textSearch);

		// vores forskellige knapper
		Button btnCreate = new Button("Tilføj vare");
		btnCreate.setPrefSize(300, 100);
		btnCreate.setFont(Font.font("Serif", FontWeight.BOLD, 30));
		btnCreate.setOnAction(e -> addProduct());

		Button btnRemove = new Button("Slet vare");
		btnRemove.setPrefSize(300, 100);
		btnRemove.setFont(Font.font("Serif", FontWeight.BOLD, 30));
		btnRemove.setOnAction(e -> DeleteRow());

		Button btnUpdate = new Button("Opdater vare");
		btnUpdate.setPrefSize(300, 100);
		btnUpdate.setFont(Font.font("Serif", FontWeight.BOLD, 30));
		// btnUpdate.setOnAction(e -> Opdater());
		
		// vores tableview
		tableJanProject.setEditable(true);

		TableColumn nameCol = new TableColumn("Navn");
		TableColumn<Product, String> purchaseCol = new TableColumn("Indkøbsdato");
		TableColumn<Product, String> expirationCol = new TableColumn("Udløbsdato");
		TableColumn typeCol = new TableColumn("Type");
		TableColumn noteCol = new TableColumn("Note");
		TableColumn amountCol = new TableColumn("Mængde");

		nameCol.setText("Navn");
		nameCol.setCellValueFactory(new PropertyValueFactory("name"));
//		purchaseCol.setText("Indkøbsdato");
		purchaseCol.setCellValueFactory(e -> {
			Product product = e.getValue();
			return new SimpleStringProperty(product.getPurchaseDate().format(DateTimeFormatter.ofPattern("dd MMMM - yyyy")));
		});
//		expirationCol.setText("Udløbsdato");
		expirationCol.setCellValueFactory(e -> {
			Product product = e.getValue();
			
			return new SimpleStringProperty(product.getExpireDate().format(DateTimeFormatter.ofPattern("dd MMMM - yyyy")));
		});
//		expirationCol.setCellValueFactory(new PropertyValueFactory("Udløbsdato"));
		typeCol.setText("Type");
		typeCol.setCellValueFactory(new PropertyValueFactory("type"));
		noteCol.setText("Note");
		noteCol.setCellValueFactory(new PropertyValueFactory("note"));
		amountCol.setText("Mængde");
		amountCol.setCellValueFactory(new PropertyValueFactory("amount"));

		tableJanProject.getColumns().addAll(nameCol, purchaseCol, expirationCol, typeCol, noteCol, amountCol);
		productList = FXCollections.observableList(productController.getAllProducts());
//		Product[] productArr = new Product[productList.size()];
//		productArr = productList.toArray(productArr);
//		FXCollections.observableArrayList();
		tableJanProject.setItems(productList);

		tableJanProject.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		tableJanProject.setEditable(true);

		// samling på det hele
		borderPaneStart.setCenter(tableJanProject);
		borderPaneStart.setRight(gridRight);
		borderPaneStart.setTop(hboxSearch);
		gridRight.add(btnCreate, 2, 0);
		gridRight.add(btnRemove, 2, 1);
		gridRight.add(btnUpdate, 2, 2);

		stage.setScene(start);
		stage.show();
	}

	private void DeleteRow() {
	    int selectedIndex = tableJanProject.getSelectionModel().getSelectedIndex();
	    if (selectedIndex >= 0) {
	        Product product = (Product) tableJanProject.getItems().get(selectedIndex);
	    	tableJanProject.getItems().remove(selectedIndex);
	        productController.deleteProduct(product);
	    } else {
	        // Nothing selected.
	        Alert alert = new Alert(AlertType.WARNING);
	//        alert.initOwner( start.getPrimaryStage());
	        alert.setTitle("No Selection");
	        alert.setHeaderText("No Person Selected");
	        alert.setContentText("Please select a person in the table.");

	        alert.showAndWait();
	    }
	}
	
	private void addProduct() {
		AddProductPopUp tilføj  = new AddProductPopUp();
		tilføj.start(new Stage(), this);
	}
	

}
