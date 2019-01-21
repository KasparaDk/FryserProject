package presentationFX;

import java.awt.Color;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import logic.DatabaseConnection;
import logic.Product;
import logic.ProductController;

public class Menu {
	ObservableList<Product> productList;
	TextField tf;
	Stage stage;
	Scene start;
	public TableView<Product> tbvOverview = new TableView();
	private ProductController productController = new ProductController(
			DatabaseConnection.newConnection("JanProjectDB"));

	public void start(Stage stage) {

		stage.setTitle("Januar Projekt");
		stage.setWidth(1500);
		stage.setHeight(550);

		BorderPane borderPaneStart = new BorderPane();
		start = new Scene(borderPaneStart);
		borderPaneStart.setPadding(new Insets(0, 0, 0, 20));
		borderPaneStart.setStyle("-fx-background-color: lightgray");
		GridPane gridRight = new GridPane();

		gridRight.setPadding(new Insets(0, 10, 10, 10));
		gridRight.setVgap(10);

		HBox hboxSearch = new HBox();

		// vores søgefelt
		TextField textSearch = new TextField();
		textSearch.setAlignment(Pos.CENTER);
		textSearch.setPromptText("Søg efter en vare");
		textSearch.setFocusTraversable(false);
		textSearch.setStyle("-fx-padding: 10 100 10 100;");
		hboxSearch.setAlignment(Pos.CENTER);
		hboxSearch.getChildren().addAll(textSearch);
		hboxSearch.setStyle("-fx-padding: 10 0 10 0;");

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
		btnUpdate.setOnAction(e -> updateProduct());

		Button btnCheckDate = new Button("Tjek vare");
		btnCheckDate.setPrefSize(300, 100);
		btnCheckDate.setFont(Font.font("Serif", FontWeight.BOLD, 30));
//		btnCheckDate.setOnAction(e -> checkDate());

		// vores tableview
		tbvOverview.setEditable(true);

		TableColumn nameCol = new TableColumn("Navn");
		TableColumn<Product, String> purchaseCol = new TableColumn("Indkøbsdato");
		TableColumn<Product, String> expirationCol = new TableColumn("Udløbsdato");
		TableColumn<Product, Integer> daysLeftCol = new TableColumn("Dage");
		TableColumn<Product, String> typeCol = new TableColumn("Type");
		TableColumn noteCol = new TableColumn("Note");
		TableColumn amountCol = new TableColumn("Mængde");

		nameCol.setText("Navn");
		nameCol.setCellValueFactory(new PropertyValueFactory("name"));
		purchaseCol.setCellValueFactory(e -> {
			Product product = e.getValue();
			return new SimpleStringProperty(
					product.getPurchaseDate().format(DateTimeFormatter.ofPattern("dd MMMM - yyyy")));
		});
		expirationCol.setCellValueFactory(e -> {
			Product product = e.getValue();

			return new SimpleStringProperty(
					product.getExpireDate().format(DateTimeFormatter.ofPattern("dd MMMM - yyyy")));
		});

		daysLeftCol.setCellValueFactory(e -> {
			Product product = e.getValue();

			return new SimpleIntegerProperty(product.daysBetweenTwoDates()).asObject();
		});

		typeCol.setCellValueFactory(e -> {
			Product product = e.getValue();
			return new SimpleStringProperty(product.getTheType().getDanishType());
		});

//		expirationCol.setCellValueFactory(new PropertyValueFactory("Udløbsdato"));
//		typeCol.setText("Type");
//		typeCol.setCellValueFactory(new PropertyValueFactory("type"));
		noteCol.setText("Note");
		noteCol.setCellValueFactory(new PropertyValueFactory("note"));
		amountCol.setText("Mængde");
		amountCol.setCellValueFactory(new PropertyValueFactory("amount"));
		daysLeftCol.setMinWidth(50);
		daysLeftCol.setMaxWidth(50);

		tbvOverview.getColumns().addAll(nameCol, purchaseCol, expirationCol, daysLeftCol, typeCol, noteCol, amountCol);
		productList = FXCollections.observableList(productController.getAllProducts());
//		Product[] productArr = new Product[productList.size()];
//		productArr = productList.toArray(productArr);
//		FXCollections.observableArrayList();
		tbvOverview.setItems(productList);

		tbvOverview.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		tbvOverview.setEditable(true);

		// Søge funktion
		FilteredList<Product> filteredData = new FilteredList<>(productList, p -> true);

		textSearch.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(productSearch -> {
				// Hvis filter text er tom, hvis alle vare
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				// Sammenligner felter i vores objekt med filter
				String lowerCaseFilter = newValue.toLowerCase();

				// Filter matches med navn
				if (productSearch.getName().toLowerCase().contains(lowerCaseFilter)) {
					return true;
				} else if (productSearch.getPurchaseDate().format(DateTimeFormatter.ofPattern("dd MMMM - yyyy"))
						.toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches for indkøbsdato
				} else if (productSearch.getExpireDate().format(DateTimeFormatter.ofPattern("dd MMMM - yyyy"))
						.toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches for udløbsdato
				} else if (productSearch.getTheType().getDanishType().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches for typen
				} else if (productSearch.getNote().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches for noten
				} else if (productSearch.getAmount().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches for mængden
				}
				return false; // Ingen match
			});
		});

		SortedList<Product> sortedData = new SortedList<>(filteredData);

		// Forbinder SortedList comparator til vores TableView comparator
		sortedData.comparatorProperty().bind(tbvOverview.comparatorProperty());
		// Tilføjer sorteret og filtreret data til vores TableView
		tbvOverview.setItems(sortedData);

		// samling på det hele
		borderPaneStart.setCenter(tbvOverview);
		borderPaneStart.setRight(gridRight);
		borderPaneStart.setTop(hboxSearch);
		gridRight.add(btnCreate, 2, 0);
		gridRight.add(btnRemove, 2, 1);
		gridRight.add(btnUpdate, 2, 2);
		gridRight.add(btnCheckDate, 2, 3);

		stage.setScene(start);
		stage.show();
	}

	private void DeleteRow() {
		int selectedIndex = tbvOverview.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			Product product = (Product) tbvOverview.getItems().get(selectedIndex);
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Slet");
			alert.setHeaderText("Er du sikker på at du vil slette denne vare?");
			alert.setContentText(
					product.getName() + " - mængde: " + product.getAmount() + " - note: " + product.getNote());
			ButtonType buttonTypeOkay = new ButtonType("OK", ButtonData.OK_DONE);
			ButtonType buttonTypeAnnuller = new ButtonType("Annuller", ButtonData.CANCEL_CLOSE);
			alert.getButtonTypes().setAll(buttonTypeAnnuller, buttonTypeOkay);
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == buttonTypeOkay) {
				productList.remove(selectedIndex);
				productController.deleteProduct(product);
				
			}	if (result.get() != buttonTypeOkay) {
					alert.close();
				}
			
		

		} else {
			// Nothing selected.
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Ingen vare markeret");
			alert.setHeaderText("Ingen vare markeret");
			alert.setContentText("Marker en linje inden du trykker på slet");

			alert.showAndWait();
		}
	}

	private void addProduct() {
		AddProductPopUp tilføj = new AddProductPopUp();
		tilføj.start(new Stage(), this);
	}

	private void updateProduct() {
		Product product = (Product) tbvOverview.getSelectionModel().getSelectedItem();
		UpdateProductPopUp updateproduct = new UpdateProductPopUp();
		updateproduct.start(new Stage(), this, product);
	}

//	private void checkDate() {
//		CheckDate openCheckDate = new CheckDate();
//		openCheckDate.start(new Stage(), this);
//	}
}
