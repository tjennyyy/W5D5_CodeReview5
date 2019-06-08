package Product;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.InputStream;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        /*
        (60) Create a JavaFX solution  that displays a stage
        */
        primaryStage.setTitle("Set action prices");
        // GUI
        // -> Name: Label & TextField
        Label labelName = new Label("Product Name");
        TextField textFieldName = new TextField();
        textFieldName.setEditable(false);
        textFieldName.setCursor(Cursor.DEFAULT);
        // HBox
        HBox hBoxName = new HBox(labelName, textFieldName);
        hBoxName.setAlignment(Pos.CENTER_LEFT);
        hBoxName.setSpacing(30);
        hBoxName.setPadding(new Insets(20, 20, 0, 20));

        // -> Quantity: Label & Textfield
        Label labelQuantity = new Label("Quantity         ");
        TextField textfieldQuantity = new TextField();
        textfieldQuantity.setEditable(false);
        textfieldQuantity.setCursor(Cursor.DEFAULT);
        // HBox
        HBox hBoxQuantity = new HBox(labelQuantity, textfieldQuantity);
        hBoxQuantity.setAlignment(Pos.CENTER_LEFT);
        hBoxQuantity.setSpacing(30);
        hBoxQuantity.setPadding(new Insets(20, 20, 0, 20));

        // -> OldPrice: Label & Textfield
        Label labelOldPrice = new Label("Old Price           ");
        labelOldPrice.setPadding(new Insets(0, 20, 0, 0));
        TextField textfieldOldPrice = new TextField();
        // EuroOldPrice in a Label
        Label labelEuroOldPrice = new Label("    EUR");
        // HBox
        HBox hBoxOldPrice = new HBox(labelOldPrice, textfieldOldPrice, labelEuroOldPrice);
        hBoxOldPrice.setAlignment(Pos.CENTER_LEFT);
        hBoxOldPrice.setPadding(new Insets(20, 20, 0, 20));

        // -> NewPrice: Label & Textfield
        Label labelNewPrice = new Label("New Price          ");
        labelNewPrice.setPadding(new Insets(0, 20, 0, 0));
        TextField textFieldNewPrice = new TextField();
        // EuroNewPrice in a Label
        Label labelEuroNewPrice = new Label("    EUR");
        // HBox
        HBox hBoxNewPrice = new HBox(labelNewPrice, textFieldNewPrice, labelEuroNewPrice);
        hBoxNewPrice.setAlignment(Pos.CENTER_LEFT);
        hBoxNewPrice.setSpacing(0);
        hBoxNewPrice.setPadding(new Insets(20, 20, 10, 20));

        // -> Image
        ImageView imageView = new ImageView();
        imageView.setFitHeight(150);
        imageView.setFitWidth(150);
        // HBox
        HBox hBoxImage = new HBox(imageView);
        hBoxImage.setPadding(new Insets(0, 0, 0, 20));

        // -> Description: Label
        Label labelDescription = new Label("Description");
        labelDescription.setFont(Font.font("", FontWeight.BOLD, 25));
        labelDescription.setPadding(new Insets(0, 0, 0, 20));
        // Text
        Text textdescription = new Text();
       // textDescription.setWrapText(true);
        // HBox
        HBox HboxDescription = new HBox();
        HboxDescription.setPadding(new Insets(0, 0, 0, 20));


        // Buttons
        // -> Update
        Button btnUpdate = new Button("Update");
        btnUpdate.setCursor(Cursor.HAND);
        btnUpdate.setStyle("-fx-font-size: 10pt;");
        btnUpdate.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btnUpdate.setStyle("-fx-background-color:#FFF0F5");
        // -> Save
        Button btnSave = new Button("Save");
        btnSave.setCursor(Cursor.HAND);
        btnSave.setStyle("-fx-font-size: 10pt;");
        btnSave.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btnSave.setStyle("-fx-background-color:pink");
        // -> Report
        Button btnReport = new Button("Report");
        btnReport.setCursor(Cursor.HAND);
        btnReport.setStyle("-fx-font-size: 10pt; -fx-base:#ee2211;");
        btnReport.setEffect(new DropShadow());
        btnReport.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        // HBox
        HBox buttons = new HBox();
        buttons.setAlignment(Pos.CENTER_LEFT);
        buttons.getChildren().addAll(btnUpdate,btnSave,btnReport);
        buttons.setPadding(new Insets(20,20,20,20));
        buttons.setSpacing(20);


        /*
        (10) create a ListView of Product object and populate it according to test data.
        The ListView should display product name,  old and new price, similar to this screen shot
        */
        // Data
        ObservableList<Product> items = FXCollections.observableArrayList(
                new Product("Pfeffer", "1 Stück", 3.49, 2.79, "/resources/Pfeffer.png",
                        "Schwarzer Pfeffer verleiht Ihren Speisen eine pikante Schärfe, besonders wenn er länger mitgekocht wird."),
                new Product("Schafmilchkäse", "200 Gramm Packung", 2.59, 1.99,
                        "/resources/Salakis.png", "Hier gibt es keine Beschreibung, weil unsere Handelskette kennt sich nur bedingt damit aus, wie man eine Werbebeschreibung schreibt."),
                new Product("Vöslauer", "1.5 Liter Flasche", 0.75, 0.49,
                        "/resources/Voslauer.png", "Spritziges Vöslauer Mineralwasser."),
                new Product("Zucker", "500 Gramm Packung", 1.39, 0.89,
                        "/resources/Zucker.png", "Natürliches Gelieren wird durch Apfelpektin unterstützt, welches im richtigen Verhältnis mit Zitronensäure und Kristallzucker abgemischt wurde."));

        ListView<Product> productList = new ListView<>();
        productList.setItems(items);


        /*
        (10) Make Product name and Quantity TextFields as non-editable (somehow).
        Add button “update” that can update the old and new price in the list data model.
        */
        // EventHandling
        productList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Product>() {
            public void changed(ObservableValue<? extends Product> ov,
                                Product old_val, Product new_val) {
                textFieldName.setText(new_val.getName());
                textfieldQuantity.setText(new_val.getQuantity());
               // textfieldOldPrice.setText(new_val.getOldPrice());
               // textFieldNewPrice.setText(new_val.getNewPrice());
               // -> don't know how to solve the double

                imageView.setImage(new Image(this.getClass().getResourceAsStream(new_val.getImagePath())));
                textdescription.setText(new_val.getDescription());
            }
        });
        btnUpdate.setOnAction(eventUpdate -> {
            int selIdx = productList.getSelectionModel().getSelectedIndex();
            if (selIdx != -1) {
                String oldPrice = textfieldOldPrice.getText();
                String newPrice = textFieldNewPrice.getText();
             //   productList.getItems().get(selIdx).setOldPrice(oldPrice);
             //   productList.getItems().get(selIdx).setNewPrice(newPrice);
            // -> don't know how to solve the double

                productList.refresh();
            }
        });


        /*
        (10)  Synchronize clicks on ListView with the display of product properties on the right side.
        */
        // Show
        VBox show = new VBox(hBoxName, hBoxQuantity, hBoxOldPrice, hBoxNewPrice, buttons, hBoxImage, labelDescription, textdescription);
        show.setPrefSize(500, 600);
        HBox allOver = new HBox(show, productList);
        allOver.setSpacing(20);

        Scene scene = new Scene(allOver, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        Application.launch(args);
    }
}
