package presentationFX;

import java.io.FileReader;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import logic.DatabaseConnection;
import logic.Mailer;
import logic.Product;
import logic.ProductController;

public class Menu {
	ObservableList<Product> productList;
	TextField tf;
	Stage stage;
	Scene start;
	Label statuslbl = new Label("");
	Label statuslbl1 = new Label(statuslbl.getText());
	private Mailer mailer = new Mailer();
	public TableView<Product> tbvOverview = new TableView();
	private ProductController productController = new ProductController(
			DatabaseConnection.newConnection("JanProjectDB"));

	public void start(Stage stage) {
		stage.setTitle("Januar Projekt");
		stage.setWidth(1500);
		stage.setHeight(550);

		// Borderpane start og top. Start er baggrunden og top er plaseret i top pane
		BorderPane borderPaneStart = new BorderPane();
		start = new Scene(borderPaneStart);
		borderPaneStart.setPadding(new Insets(0, 0, 0, 20));
		borderPaneStart.setStyle("-fx-background-color: lightgray");
		BorderPane topPane = new BorderPane();
		
		// gridpane placeres i højre side af borderpane
		GridPane gridRight = new GridPane();
		gridRight.setPadding(new Insets(0, 10, 10, 10));
		gridRight.setVgap(10);

		// vores søgefelt
		TextField textSearch = new TextField();
		textSearch.setAlignment(Pos.CENTER);
		textSearch.setPromptText("Søg efter en vare");
		textSearch.setFocusTraversable(false);
		textSearch.setStyle("-fx-padding: 10 100 10 100;");
		textSearch.setMaxWidth(500);
		textSearch.setMinWidth(100);
		topPane.setCenter(textSearch);
		topPane.setLeft(statuslbl);
		topPane.setRight(statuslbl1);
		statuslbl1.setVisible(false);
		statuslbl.setFont(new Font("Calibri", 16));
		statuslbl1.setFont(new Font("Calibri", 16));
		topPane.setMargin(textSearch, new Insets(10, 10, 10, 10));
		topPane.setMargin(statuslbl1, new Insets(20, 20, 0, 0));
		topPane.setMargin(statuslbl, new Insets(20, 0, 0, 0));

		// vores forskellige knapper
		Button btnCreate = new Button("Tilføj vare");
		btnCreate.setPrefSize(300, 100);
		btnCreate.setFont(Font.font("Calibri", FontWeight.BOLD, 30));
		btnCreate.setOnAction(e -> addProduct());

		Button btnRemove = new Button("Slet vare");
		btnRemove.setPrefSize(300, 100);
		btnRemove.setFont(Font.font("Calibri", FontWeight.BOLD, 30));
		btnRemove.setOnAction(e -> DeleteRow());

		Button btnUpdate = new Button("Opdater vare");
		btnUpdate.setPrefSize(300, 100);
		btnUpdate.setFont(Font.font("Calibri", FontWeight.BOLD, 30));
		btnUpdate.setOnAction(e -> updateProduct());
		
		Button btnEmail = new Button("Send Email");
		btnEmail.setPrefSize(300, 100);
		btnEmail.setFont(Font.font("Calibri", FontWeight.BOLD, 30));
		btnEmail.setOnAction(e -> sendEmail());

		// vores tableview
		TableColumn nameCol = new TableColumn("Navn");
//		TableColumn<Product, String> purchaseCol = new TableColumn("Indkøbsdato");
		TableColumn<Product, String> expirationCol = new TableColumn("Udløbsdato");
		TableColumn<Product, Integer> daysLeftCol = new TableColumn("Dage før varen udløber");
		TableColumn<Product, String> typeCol = new TableColumn("Type");
		TableColumn noteCol = new TableColumn("Note");
		TableColumn amountCol = new TableColumn("Mængde");

		// indsættelse af værdier i tabellen
		nameCol.setText("Navn");
		nameCol.setCellValueFactory(new PropertyValueFactory("name"));
		
//		purchaseCol.setCellValueFactory(e -> {
//			Product product = e.getValue();
//			return new SimpleStringProperty(
//					product.getPurchaseDate().format(DateTimeFormatter.ofPattern("dd MMMM - yyyy")));
//		});
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

		noteCol.setText("Note");
		noteCol.setCellValueFactory(new PropertyValueFactory("note"));
		
		amountCol.setText("Mængde");
		amountCol.setCellValueFactory(new PropertyValueFactory("amount"));
		
		// Størelse på rækkerne.
		nameCol.setMinWidth(125);
		nameCol.setMaxWidth(250);
//		purchaseCol.setMinWidth(125);
//		purchaseCol.setMaxWidth(250);
		expirationCol.setMinWidth(125);
		expirationCol.setMaxWidth(250);
		daysLeftCol.setMinWidth(50);
		daysLeftCol.setMaxWidth(250);
		typeCol.setMinWidth(125);
		typeCol.setMaxWidth(250);
		amountCol.setMinWidth(125);
		amountCol.setMaxWidth(250);
		noteCol.setMinWidth(125);
		noteCol.setMaxWidth(250);

		tbvOverview.getColumns().addAll(nameCol, expirationCol, daysLeftCol, typeCol, amountCol, noteCol);

		productList = FXCollections.observableList(productController.getAllProducts());
		tbvOverview.setItems(productList);

		tbvOverview.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		// Søge funktion
		FilteredList<Product> filteredData = new FilteredList<>(productList, p -> true);

		textSearch.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(productSearch -> {
				// Hvis filter text er tom, vis alle vare
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
		
		start.setOnKeyReleased(e -> {
		    if (e.getCode() == KeyCode.DELETE) {
		        DeleteRow();
		        
		    }
		});

		// samling på det hele
		borderPaneStart.setCenter(tbvOverview);
		borderPaneStart.setRight(gridRight);
		borderPaneStart.setTop(topPane);
		gridRight.add(btnCreate, 2, 0);
		gridRight.add(btnRemove, 2, 1);
		gridRight.add(btnUpdate, 2, 2);
		gridRight.add(btnEmail, 2, 3);

		stage.setScene(start);
		stage.show();
	}

	private void sendEmail() {
		Properties login = new Properties();
		try (FileReader reader = new FileReader("login.properties.txt")) {
			login.load(reader);		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String email = login.getProperty("email");
		String password = login.getProperty("password");
		
		StringBuilder sb = new StringBuilder();
		List<Product> product2 = productController.getAllProducts();
		for (Product product : product2) {
			String foo = product.checkDate();
			if (foo != null) {
				sb.append(foo);
			}
		}
		mailer.send(email, password,"kalle-drengen@hotmail.com","Fryser status" , sb.toString() ); {
			Alert alert = new Alert(AlertType.CONFIRMATION, "Mail sendt", ButtonType.OK);
			alert.showAndWait();
		}
	}

	private void DeleteRow() {
		int selectedIndex = tbvOverview.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			Product product = (Product) tbvOverview.getSelectionModel().getSelectedItem();
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Slet");
			alert.setHeaderText("Er du sikker på at du vil slette denne vare?");
			alert.setContentText(
					product.getName() + " - mængde: " + product.getAmount() + " - note: " + product.getNote());
			ButtonType buttonTypeOkay = new ButtonType("OK", ButtonData.OK_DONE);
			ButtonType buttonTypeCancel = new ButtonType("Annuller", ButtonData.CANCEL_CLOSE);
			alert.getButtonTypes().setAll(buttonTypeCancel, buttonTypeOkay);
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == buttonTypeOkay) {
				statuslbl.setText(product.getName() + " er blevet slettet");
				statuslbl1.setText(product.getName() + " er blevet slettet");
				productList.remove(product);
				productController.deleteProduct(product);

			}
			if (result.get() != buttonTypeOkay) {
				alert.close();
			}

		} else {
			// Intet markeret.
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
		int selectedIndex = tbvOverview.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			Product product = (Product) tbvOverview.getSelectionModel().getSelectedItem();
			UpdateProductPopUp updateproduct = new UpdateProductPopUp();
			updateproduct.start(new Stage(), this, product);
		} else {
			statuslbl.setText("Der skal vælges en vare, før der kan opdateres");
			statuslbl1.setText("Der skal vælges en vare, før der kan opdateres");
		}
	}

	public void addTextLimiter(final TextField tf, final int maxLength) {
	    tf.textProperty().addListener(new ChangeListener<String>() {
	        @Override
	        public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
	            if (tf.getText().length() > maxLength) {
	                String s = tf.getText().substring(0, maxLength);
	                tf.setText(s);
	            }
	        }
	    });
	}
}
