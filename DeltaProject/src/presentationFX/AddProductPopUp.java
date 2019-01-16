package presentationFX;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import presentationFX.Dropdown;

public class AddProductPopUp {

	// Liste over varetyper
	private static final String[] liste = { "Fisk - Fed", "Fisk - Mager", "Fisk - Hakket", "Frugt",
											"Grøntsager",
											"Hakkekød",
											"Kalvekød", "Kylling", 
											"Lam",
											"Pølser",
											"Røget kød",
											"Svinekød - Fed", "Svinekød - Mager" };

	public void start(Stage stage) {
		Stage popUp = new Stage();
		popUp.setTitle("Tilføj Vare");
		popUp.setHeight(360);
		popUp.setWidth(500);

		// Knapperne
		Button tilføj = new Button();
		tilføj.setText("Tilføj Vare");
		tilføj.setOnAction(e -> {
			System.out.println("Test print");
		});

		Button annuller = new Button();
		annuller.setText("Annuller");
		annuller.setOnAction(e -> {
			popUp.hide();
		});

		// BorderPane til knapperne
		BorderPane buttonPane = new BorderPane();
		buttonPane.setPadding(new Insets(20, 50, 20 ,50));
		buttonPane.setLeft(annuller);
		buttonPane.setRight(tilføj);
		
//		VBox buttonVBox = new VBox();
//		buttonVBox.getChildren().addAll(tilføj, annuller);

		// Teksfelterne
		// Varenavn
		Label varenavnlbl = new Label("Varenavn:");
		TextField varenavntxt = new TextField();
		HBox varenavnbox = new HBox();
		varenavnbox.getChildren().addAll(varenavnlbl, varenavntxt);
		varenavnbox.setSpacing(10);

		// Dropdown menu med varetyper
		ComboBox<String> cmb = new ComboBox<>();
		cmb.setTooltip(new Tooltip());
		cmb.getItems().addAll(liste);

		// Indkøbsdato
		Label indkøbsdatolbl = new Label("Indkøbsdato:");
		TextField indkøbsdatotxt = new TextField();
		//Gør tekstfeltet uneditable
		indkøbsdatotxt.setEditable(false);
		indkøbsdatotxt.setMouseTransparent(true);
		indkøbsdatotxt.setFocusTraversable(false);
		String date = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		indkøbsdatotxt.setText(date);
		indkøbsdatotxt.setStyle("-fx-text-inner-color: grey;");
		HBox indkøbsdatobox = new HBox();
		indkøbsdatobox.getChildren().addAll(indkøbsdatolbl, indkøbsdatotxt);
		indkøbsdatobox.setSpacing(10);

		// Note
		Label notelbl = new Label("Note:");
		TextField notetxt = new TextField();
		notetxt.setAlignment(Pos.TOP_LEFT);
		notetxt.setPrefWidth(300);
		
		HBox notebox = new HBox();
		notebox.getChildren().addAll(notelbl, notetxt);
		notebox.setSpacing(10);

		// GridPane med textfelterne
		GridPane textFields = new GridPane();
		textFields.add(varenavnbox, 0, 0);
		textFields.add(cmb, 0, 1);
		textFields.add(indkøbsdatobox, 0, 2);
		textFields.add(notebox, 0, 3);

		BorderPane layout = new BorderPane();

		// Knapper til højre, textfelter i center
		layout.setBottom(buttonPane);
		layout.setCenter(textFields);

		Scene scene = new Scene(layout);
		popUp.setScene(scene);
		popUp.show();

		new Dropdown<String>(cmb);
	}
}




