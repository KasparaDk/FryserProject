package presentationFX;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
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

	TextField tf;
	Stage stage;
	Scene start;
	public TableView tableJanProject = new TableView();
	private ProductController productController = new ProductController(
			DatabaseConnection.newConnection("JanProjectDB"));

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
		TableColumn purchaseCol = new TableColumn("Indkøbsdato");
		TableColumn expirationCol = new TableColumn("Udløbsdato");
		TableColumn typeCol = new TableColumn("Type");
		TableColumn noteCol = new TableColumn("Note");
		TableColumn amountCol = new TableColumn("Mængde");

		nameCol.setText("Navn");
		nameCol.setCellValueFactory(new PropertyValueFactory("name"));
		purchaseCol.setText("Indkøbsdato");
		purchaseCol.setCellValueFactory(new PropertyValueFactory("purchaseDate"));
//		expirationCol.setText("Udløbsdato");

//		expirationCol.setCellValueFactory(new PropertyValueFactory("Udløbsdato"));
		typeCol.setText("Type");
		typeCol.setCellValueFactory(new PropertyValueFactory("type"));
		noteCol.setText("Note");
		noteCol.setCellValueFactory(new PropertyValueFactory("note"));
		amountCol.setText("Mængde");
		amountCol.setCellValueFactory(new PropertyValueFactory("amount"));

		tableJanProject.getColumns().addAll(nameCol, purchaseCol, expirationCol, typeCol, noteCol, amountCol);
		ObservableList<Product> productList = FXCollections.observableList(productController.getAllProducts());
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
			final Stage dialog = new Stage();
			dialog.initModality(Modality.APPLICATION_MODAL);
			dialog.initOwner(stage);
			BorderPane bppopup = new BorderPane();
			BorderPane bpBottom = new BorderPane();
			BorderPane bpTop = new BorderPane();
			bpTop.setPadding(new Insets(1, 1, 1, 1));
			bpBottom.setPadding(new Insets(20, 50, 20, 50));
			HBox hboxButtons = new HBox();
			hboxButtons.setPadding(new Insets(100, 100, 100, 100));
			
			String getName = product.getName();
			String getNote = product.getNote();
			String getAmount = product.getAmount();
			Label labelbesked = new Label();
			Label labelvare = new Label();
			labelbesked.setText("vil du slette denne varelinje:");
			labelbesked.setFont(new Font("Arial", 15));
			labelvare.setPadding(new Insets(50, 0, 0, 0));
			labelvare.setText(getName + ", " + getAmount + ", " + getNote);
			labelvare.setFont(new Font("Arial", 15));
			
			Button btnDelete = new Button("OK");
			btnDelete.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					tableJanProject.getItems().remove(selectedIndex);
					productController.deleteProduct(product);
					dialog.close();
				}
			});
					
			Button btnAnnuler = new Button("Annuler");
			btnAnnuler.setOnAction(e -> dialog.close());
				
			hboxButtons.setAlignment(Pos.CENTER);
			hboxButtons.getChildren().addAll(btnAnnuler, btnDelete);
			
			bppopup.setBottom(bpBottom);
			bppopup.setTop(bpTop);
			bpTop.setTop(labelbesked);
			bpTop.setBottom(labelvare);
			bpBottom.setRight(btnDelete);
			bpBottom.setLeft(btnAnnuler);
			
			Scene dialogScene = new Scene(bppopup, 300, 200);
			dialog.setScene(dialogScene);
			dialog.show();
		} else {
			// Nothing selected.
			Alert alert = new Alert(AlertType.WARNING);
			// alert.initOwner( start.getPrimaryStage());
			alert.setTitle("Ingen vare markeret");
			alert.setHeaderText("Ingen vare markeret");
			alert.setContentText("Marker en linje inden du trykker på slet");

			alert.showAndWait();
		}
	}

	private void addProduct() {
		AddProductPopUp tilføj = new AddProductPopUp();
		tilføj.start(new Stage());
	}

}
