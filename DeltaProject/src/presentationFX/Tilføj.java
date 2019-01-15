package presentationFX;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Tilføj {

	// Liste over varetyper
	private static final String[] liste = { "Abacate", "Abacaxi", "Ameixa", "Amora", "Araticum", "Atemoia", "Avocado",
			"Banana prata", "Caju", "Cana descascada", "Caqui", "Caqui Fuyu", "Carambola", "Cereja", "Coco verde",
			"Figo", "Figo da Índia", "Framboesa", "Goiaba", "Graviola", "Jabuticaba", "Jambo", "Jambo rosa", "Jambolão",
			"Kino (Kiwano)", "Kiwi", "Laranja Bahia", "Laranja para suco", "Laranja seleta", "Laranja serra d’água",
			"Laranjinha kinkan", "Lichia", "Lima da pérsia", "Limão galego", "Limão Taiti", "Maçã argentina",
			"Maçã Fuji", "Maçã gala", "Maçã verde", "Mamão formosa", "Mamão Havaí", "Manga espada", "Manga Haden",
			"Manga Palmer", "Manga Tommy", "Manga Ubá", "Mangostim", "Maracujá doce", "Maracujá para suco", "Melancia",
			"Melancia sem semente", "Melão", "Melão Net", "Melão Orange", "Melão pele de sapo", "Melão redinha",
			"Mexerica carioca", "Mexerica Murcote", "Mexerica Ponkan", "Mirtilo", "Morango", "Nectarina",
			"Nêspera ou ameixa amarela", "Noni", "Pera asiática", "Pera portuguesa", "Pêssego", "Physalis", "Pinha",
			"Pitaia", "Romã", "Tamarilo", "Tamarindo", "Uva red globe", "Uva rosada", "Uva Rubi", "Uva sem semente",
			"Abobora moranga", "Abobrinha italiana", "Abobrinha menina", "Alho", "Alho descascado",
			"Batata baroa ou cenoura amarela", "Batata bolinha", "Batata doce", "Batata inglesa", "Batata yacon",
			"Berinjela", "Beterraba", "Cebola bolinha", "Cebola comum", "Cebola roxa", "Cenoura", "Cenoura baby",
			"Couve flor", "Ervilha", "Fava", "Gengibre", "Inhame", "Jiló", "Massa de alho", "Maxixe", "Milho",
			"Pimenta biquinho fresca", "Pimenta de bode fresca", "Pimentão amarelo", "Pimentão verde",
			"Pimentão vermelho", "Quiabo", "Repolho", "Repolho roxo", "Tomate cereja", "Tomate salada",
			"Tomate sem acidez", "Tomate uva", "Vagem", "Agrião", "Alcachofra", "Alface", "Alface americana",
			"Almeirão", "Brócolis", "Broto de alfafa", "Broto de bambu", "Broto de feijão", "Cebolinha", "Coentro",
			"Couve", "Espinafre", "Hortelã", "Mostarda", "Rúcula", "Salsa", "Ovos brancos", "Ovos de codorna",
			"Ovos vermelhos" };


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

		// VBox til knapperne
		VBox buttonVBox = new VBox();
		buttonVBox.getChildren().addAll(tilføj, annuller);

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
		Label indkøbsdato = new Label("Indkøbsdato:");
		DatePicker datePicker = new DatePicker();
		datePicker.setValue(LocalDate.of(2019, 01, 11));
		datePicker.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		datePicker.setShowWeekNumbers(true);
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
		HBox indkøbsdatobox = new HBox();
		indkøbsdatobox.getChildren().addAll(indkøbsdato, datePicker);
		indkøbsdatobox.setSpacing(10);

		// Skal have fixes Dato-format

		// Note
		Label notelbl = new Label("Note:");
		TextField notetxt = new TextField();
		notetxt.setPrefWidth(300);
		notetxt.setPrefHeight(200);
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
		layout.setRight(buttonVBox);
		layout.setCenter(textFields);

		Scene scene = new Scene(layout);
		popUp.setScene(scene);
		popUp.show();

		new Dropdown<String>(cmb);
	}
}




