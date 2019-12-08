package com.hinet;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

public class Main extends Application {

	@FXML
	public JFXButton clear_btn, generate_btn;

	@FXML
	public JFXTextField customer_sn, activation_sn, licence_to;
	EncyptSN encyptSN = new EncyptSN();

	public static void main(String[] args) throws NoSuchAlgorithmException {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
		primaryStage.setTitle("Key Generator");
		Scene scene = new Scene(root, 450, 390);
		primaryStage.setScene(scene);
		primaryStage.setScene(scene);
		primaryStage.setMaxHeight(400);
		primaryStage.setMaxWidth(450);
		primaryStage.setMinHeight(350);
		primaryStage.setMinWidth(450);
		primaryStage.show();
	}

	@FXML
	public void generateSN() throws NoSuchAlgorithmException {

		String serial = customer_sn.getText();
		serial = encyptSN.EditSerialNumber(serial);
		encyptSN.setDeviceSN(serial);
		String result = encyptSN.getDeviceSN();
		activation_sn.setText(result);

	}

	private void saveTextToFile(String content, File file) {
		try {
			PrintWriter writer;
			writer = new PrintWriter(file);
			writer.println(content);
			writer.close();
		} catch (IOException ex) {
			System.out.println("Can't save the File!");
		}
	}

	@FXML
	public void saveFile() {

		FileChooser fileChooser = new FileChooser();

		//Set extension filter for text files
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Lic files (*.lic)", "*.Lic");

		fileChooser.getExtensionFilters().add(extFilter);
		fileChooser.setTitle("Save Licence File");
		fileChooser.setInitialFileName("Licence_" + licence_to.getText());

		//Show save file dialog
		File file = fileChooser.showSaveDialog(new Stage());

		if (file != null) {
			saveTextToFile(activation_sn.getText(), file);
		}
	}

	@FXML
	public void Exit() {

		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

		alert.setTitle("Closing  Confirmation ");
		alert.setHeaderText("Look, you're going to close the App");
		alert.setContentText("Are you ok with this?");

		Optional<ButtonType> result = alert.showAndWait();

		if (result.get() == ButtonType.OK) {
			// ... user chose OK
			Platform.exit();
		}
	}
}


