package presentationFX;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
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

public class Menu {

	TextField tf;
	Stage stage;
	Scene start;
	private TableView tableJanProject = new TableView();

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
		textSearch.setMaxSize(2000, 2000);
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
		// btnRemove.setOnAction(e -> Slet());

		// vores tableview
		tableJanProject.setEditable(true);

		TableColumn nameCol = new TableColumn("Navn");
		TableColumn purchaseCol = new TableColumn("Indkøbsdato");
		TableColumn expirationCol = new TableColumn("Udløbsdato");
		TableColumn typeCol = new TableColumn("Type");
		TableColumn noteCol = new TableColumn("Note");

		nameCol.setText("Navn");
		nameCol.setCellValueFactory(new PropertyValueFactory("Navn"));
		purchaseCol.setText("Indkøbsdato");
		purchaseCol.setCellValueFactory(new PropertyValueFactory("Indkøbsdato"));
		expirationCol.setText("Udløbsdato");
		expirationCol.setCellValueFactory(new PropertyValueFactory("Udløbsdato"));
		typeCol.setText("Type");
		typeCol.setCellValueFactory(new PropertyValueFactory("Type"));
		noteCol.setText("Note");
		noteCol.setCellValueFactory(new PropertyValueFactory("Note"));

		tableJanProject.getColumns().addAll(nameCol, purchaseCol, expirationCol, typeCol, noteCol);

		tableJanProject.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		tableJanProject.setEditable(true);

		// samling på det hele
		borderPaneStart.setCenter(tableJanProject);
		borderPaneStart.setRight(gridRight);
		borderPaneStart.setTop(hboxSearch);
		gridRight.add(btnCreate, 2, 0);
		gridRight.add(btnRemove, 2, 1);

		stage.setScene(start);
		stage.show();
	}

	private void addProduct() {
		AddProductPopUp tilføj  = new AddProductPopUp();
		tilføj.start(new Stage());
	}

}
