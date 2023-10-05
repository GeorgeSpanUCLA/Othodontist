import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.image.Image;

/**
 * 
 * This class is the central access point for all other classes 
 * @author (your name)
 * @version (a version number or a date)
 */

public class orthodontex extends Application {


    public void start(Stage primaryStage)
    {//instantiate buttons 
        primaryStage.setTitle("Orthodontist explorer");
       VBox vBox = new VBox();
    vBox.setPrefWidth(500);

        vBox.setPrefWidth(500);

        Button c = new Button("Clients");
        c.setMinWidth(vBox.getPrefWidth());
        c.setStyle("-fx-font-size:30");
        Button t = new Button("Treatments");
        t.setMinWidth(vBox.getPrefWidth());
        t.setStyle("-fx-font-size:30");
        Button m = new Button("Materials");
        m.setMinWidth(vBox.getPrefWidth());
        m.setStyle("-fx-font-size:30");
        Button rm = new Button("Rate Material");
        rm.setMinWidth(vBox.getPrefWidth());
        rm.setStyle("-fx-font-size:30");
        Button cs = new Button("Client Status");
        cs.setMinWidth(vBox.getPrefWidth());
        cs.setStyle("-fx-font-size:30");
        
        TilePane n = new TilePane();

        Label o = new Label("please select a button");

        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            //acces the client database when pressed 
                public void handle(ActionEvent e)
                {
                    o.setText(" Clients button ");
                    new ClientData().start(new Stage());

                }
            };
        EventHandler<ActionEvent> event1 = new EventHandler<ActionEvent>() {
            //access the treatment database when pressed 
                public void handle(ActionEvent e)
                {
                    o.setText(" Treatment button ");
                    new TreatmentDATABase().start(new Stage());

                }
            };
        EventHandler<ActionEvent> event2 = new EventHandler<ActionEvent>() {
            //access the material database when pressed 
                public void handle(ActionEvent e)
                {
                    o.setText(" Materials button ");
                    new MaterialDATABASE().start(new Stage());

                }
            };
       
       

        EventHandler<ActionEvent> event3= new EventHandler<ActionEvent>() {
            //access client status database when pressed 
                public void handle(ActionEvent e)
                {
                    o.setText(" Client Status Button");
                     new StatusData().start(new Stage());

                }
            };
       
            
        StackPane primary = new StackPane();
        Scene s = new Scene(n, 1920, 1080);
        Image img = new Image("dentistsinNL-1920x1080-1.jpg");
        BackgroundImage bImg = new BackgroundImage(img,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background bGround = new Background(bImg);
        n.setBackground(bGround);
        // when button is pressed
        c.setOnAction(event);
        t.setOnAction(event1);
        m.setOnAction(event2);
        cs.setOnAction(event3);
       
        // add button
       
        n.getChildren().add(c);
        n.getChildren().add(t);
        n.getChildren().add(m);
       
        n.getChildren().add(cs);
        
      
        

       
        primaryStage.setScene(s);

        primaryStage.show();
    }

    public static void main(String args[])
    {
        // launch the application
        launch(args);
    }
}
 