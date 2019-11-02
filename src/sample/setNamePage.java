package sample;

import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;

import java.awt.*;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class setNamePage extends Scene {
    public setNamePage(StackPane sp, Scene bp, Stage window) throws Exception {
        super( sp, 1000, 650);
        InputStream is = Files.newInputStream(Paths.get("images/setname.jpg"));
        Image img = new Image(is);
        is.close();
        ImageView imgView = new ImageView(img);
        imgView.setFitHeight(650);
        imgView.setFitWidth(1000);
        sp.getChildren().add(imgView);

        OptionsPage.BackButton bb = new OptionsPage.BackButton();
        bb.setOnMouseClicked( event -> {
            window.setScene( bp);
        });
        bb.setTranslateX(-350);
        bb.setTranslateY(-230);

        // isim ve yüz seçerkenki karanlık alan
        Rectangle nameArea = new Rectangle(300,200);
        nameArea.setOpacity(0.6);
        nameArea.setFill(Color.BLACK);
        nameArea.setEffect( new GaussianBlur(3.5));
        TextField tf = new TextField("Player");
        tf.setMaxWidth(150);
        Text nameText = new Text("Player Name: ");
        nameText.setFill(Color.WHITESMOKE);
        nameText.setFont(Font.font("Verdana", FontWeight.BOLD,15));
        nameText.setTranslateY(-40);
        Text sideText = new Text("Which side you want to play? ");
        sideText.setFill(Color.WHITESMOKE);
        sideText.setFont(Font.font("Verdana", FontWeight.BOLD,15));
        sideText.setTranslateY(40);
        RadioButton aSide = new RadioButton("A");
        RadioButton bSide = new RadioButton("B");
        aSide.setTextFill(Color.WHITESMOKE);
        bSide.setTextFill(Color.WHITESMOKE);
        ToggleGroup tg = new ToggleGroup();
        aSide.setToggleGroup(tg);
        bSide.setToggleGroup(tg);
        HBox hbox = new HBox(aSide, bSide);
        bSide.setTranslateX(40);
        hbox.setTranslateY(390);
        hbox.setTranslateX(440);

        // Start Button
        Main.MenuButton sb = new Main.MenuButton("Start");
        sb.setMaxSize(250,30);
        sb.setTranslateY(170);
        sb.setOnMouseClicked(event -> {
            Scene scene = null;
            StackPane gameScreen = new StackPane();
            try {
                scene = new GamePage(gameScreen, bp, window, tf.getText(), (RadioButton) tg.getSelectedToggle());
            } catch (Exception e) {
                e.printStackTrace();
            }
            window.setScene( scene);
        });

        sp.getChildren().addAll( bb, nameArea, nameText, tf, sideText, hbox, sb);
    }
}
