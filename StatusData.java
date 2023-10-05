import java.io.*; //System for input and output data streams 
import java.io.BufferedReader; //Buffers characters in a character input-stream to efficiently read them
import java.io.BufferedWriter; //Buffers characters in a character output-stream to efficiently write them
import java.io.File; // representation of file pathnames
import java.io.FileReader; //Convenience class for reading character files.
import java.io.FileWriter; //Convenience class for writing character files.
import java.io.IOException; //Signals an I/O exception occured
import java.lang.*; //Classes fundemtnal to java design 
import java.lang.Object;// root of class hierarchy
import java.nio.file.Files;//Consists of static methods that opperate on file and directories
import java.nio.file.Paths; //Convert path string to URI
import java.util.*; //contains collection framework, event models, date and time facilities, intirnationalization and misceleneous utility classes 
import java.util.stream.*;//support for functioanl-style operations on streams of elements 
import java.util.stream.Stream;//sequence of elements for sequential and parallel aggregate opperations 
import javafx.application.*; //Class from which JavaFX extends   
import javafx.beans.property.*;  //Defines read only and writable properties
import javafx.collections.*; // Consists of a number of interfaces and classes
import javafx.event.*; // Provides framework for Fx event delievery and handling 
import javafx.event.ActionEvent;//Event representing an action 
import javafx.event.EventHandler;//Handler for specific class events
import javafx.geometry.*; // Provides 2D classes for defining and performing operations on objects related to 2D geometry
import javafx.scene.*;   // Class that contains content found on a graph
import javafx.scene.control.*;  // Specialized nodes that can be reused in different application contexts
import javafx.scene.control.Alert.AlertType; //value of property alertType
import javafx.scene.control.Button; //Button control
import javafx.scene.control.cell.*; // Where all cell realated classes are located 
import javafx.scene.control.Label; // Non additable lable control
import javafx.scene.control.PopupControl; //extension to PopupWindow to allow for CSS styling 
import javafx.scene.image.*; //loading and displaying images 
import javafx.scene.image.Image; //represents images and loads images from URL
import javafx.scene.layout.*;  // Provides classes for user interface layout 
import javafx.scene.paint.*; // classes for colors and gradients for shapes and backgrounds 
import javafx.scene.shape.*; //2D classes for definging and performing operations 
import javafx.scene.text.*;  //Contains properties to create text and modify its appearence 
import javafx.scene.text.Font; //represents fonts
import javafx.scene.text.FontPosture;  //font italisization
import javafx.scene.text.FontWeight;  //specifies different font weights
import javafx.scene.text.Text;  //defines node that displays text
import javafx.stage.*;  // Provides container classes for JavaFX containt 
import javafx.stage.Popup; //special container for scene graph
import javafx.stage.PopupWindow; //parent for different type of popup based windows
import javafx.stage.Stage; //top level Javafx container 
import javafx.stage.Window;//top level window to host a scene with which the user can interact 
import javafx.util.converter.*; // Is responsible for standard string converters 
import java.text.SimpleDateFormat;
import java.text.DateFormat;


/**
 * 
 *This class will probide data managment for client status 
 * @author (your name)
 * @version (a version number or a date)
 */

public class StatusData extends Application
{public static void main(String[] args){
        launch(args);}
    private TableView<ClientStatus> table;
    private TextField txtID, txtFinished, txtRating, txtFDate;

    @Override public void start(Stage mainStage){
        //This initializes all graphical components
        Label lblHeading = new Label("Status database");
        lblHeading.setFont(new Font("Arial", 20));
        table =new TableView<ClientStatus>();
        table.setEditable(true);
        table.setItems(RetrieveData());
        
        
        
 TableColumn colID = new TableColumn("ID");
        colID.setMinWidth(300);
        colID.setCellValueFactory(new PropertyValueFactory<ClientStatus, String>("ID"));
        colID.setCellFactory(TextFieldTableCell.forTableColumn());
        colID.setOnEditCommit(e -> colID_OnEditCommit(e));  
        
    
    TableColumn colFinished = new TableColumn("Finished");
    colFinished.setMinWidth(300);
        colFinished.setCellValueFactory(new PropertyValueFactory<ClientStatus, String>("Finished"));
        colFinished.setCellFactory(TextFieldTableCell.forTableColumn());
        colFinished.setOnEditCommit(e -> colFinished_OnEditCommit(e));  
        
          TableColumn colRating = new TableColumn("Rating");
    colRating.setMinWidth(300);
     colRating.setCellValueFactory(new PropertyValueFactory<ClientStatus, String>("Rating"));
        colRating.setCellFactory(TextFieldTableCell.forTableColumn());
        colRating.setOnEditCommit(e -> colRating_OnEditCommit(e)); 
        
        
          TableColumn colFDate= new TableColumn("Final Date");
    colFDate.setMinWidth(300);
     colFDate.setCellValueFactory(new PropertyValueFactory<ClientStatus, String>("FDate"));
        colFDate.setCellFactory(TextFieldTableCell.forTableColumn());
              
        
        
     table.getColumns().addAll(colID, colFinished, colRating, colFDate);
     
       
        txtID = new TextField();
        txtID.setMaxWidth(100);
        txtID.setPromptText("ID");
        
        txtFinished = new TextField();
        txtFinished.setPromptText("Finished");
        txtFinished.setMinWidth(100);
        
        txtRating = new TextField();
        txtRating.setPromptText("Rating");
        txtRating.setMinWidth(100);
        
        
          Date date = Calendar.getInstance().getTime();  
                DateFormat dateFormat = new SimpleDateFormat("dd-mm-yy");  
                String strDate = dateFormat.format(date);  

        txtFDate = new TextField();
        txtFDate.setPromptText(strDate);
        txtFDate.setMinWidth(100);
        
        

         Button buttonAdd = new Button("Add");
        buttonAdd.setMinWidth(50);
        buttonAdd.setOnAction(e-> buttonAdd_Clicked());
        buttonAdd.setStyle("-fx-backround-color : #1200AB; " );
        
        txtID.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            //validate Id enetered by user 
                if (!newValue) 
                { 
                    if((!txtID.getText().matches("^[0-9]+")))
                    {

                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText("Error!");
                        alert.setContentText("Incorrect data type. Please enter only numbers");
                        alert.showAndWait();

                    }
                    if(!validate(txtID.getText())){
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText("Error!");
                        alert.setContentText("This ID does not correspond to an ID in the client database, Enter a different ID!");
                        alert.showAndWait();}

                }}
        );
        
       

         txtFinished.focusedProperty().addListener((arg0, oldValue, newValue) -> {
             //validate status entered by user 
                if (!newValue) 
                { 
                    if(!txtFinished.getText().matches("^([Tt][Rr][Uu][Ee]|[Ff][Aa][Ll][Ss][Ee])$"))
                    {

                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText("Error!");
                        alert.setContentText("Incorrect, please enter either true or false!!!");
                        alert.showAndWait();

                    }

                }
            }

        );

        
        txtRating.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            //validate rating entered by user 
                if (!newValue) 
                { 
                    if(!txtRating.getText().equals("Excellent") || !txtRating.getText().equals( "Good") || !txtRating.getText().equals("Bad") )
                    {

                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText("Error!");
                        alert.setContentText(" ");
                        alert.showAndWait();

                    }

                }
            }

        );

        
        
        Button buttonStore = new Button ("Store");
        buttonStore.setMinWidth(60);
        buttonStore.setOnAction(e-> Save());

        
        HBox paneAdd = new HBox();
        paneAdd.getChildren().addAll(txtID, txtFinished, txtRating, txtFDate, buttonAdd, buttonStore);
        paneAdd.setSpacing(6);
        
          VBox paneMain = new VBox();
        paneMain.setSpacing(10);
        paneMain.setPadding(new Insets(10,10,10,10));
        paneMain.getChildren().addAll(lblHeading, table, paneAdd);

        Scene scene = new Scene(paneMain);
        mainStage.setScene(scene);
        mainStage.setTitle("Status Database");
        mainStage.show();
    }
    
    public void colID_OnEditCommit(Event e){
        //edit Id column
        TableColumn.CellEditEvent<ClientStatus, String> ce;
        ce = (TableColumn.CellEditEvent<ClientStatus, String>) e; 
        ClientStatus  m = ce.getRowValue();
        m.setID(ce.getNewValue());
    }
    
    public void colFinished_OnEditCommit(Event e){
        //edit finished column 
        TableColumn.CellEditEvent<ClientStatus, String> ce;
        ce = (TableColumn.CellEditEvent<ClientStatus, String>) e; 
        ClientStatus  m = ce.getRowValue();
        m.setFinished(ce.getNewValue());
    }
    
      public void colRating_OnEditCommit(Event e){
          //edit rating column
        TableColumn.CellEditEvent<ClientStatus, String> ce;
        ce = (TableColumn.CellEditEvent<ClientStatus, String>) e; 
        ClientStatus  m = ce.getRowValue();
        m.setRating(ce.getNewValue());
    }
    
      public void colFDate_OnEditCommit(Event e){
          //edit date column 
        TableColumn.CellEditEvent<ClientStatus, String> ce;
        ce = (TableColumn.CellEditEvent<ClientStatus, String>) e; 
        ClientStatus  m = ce.getRowValue();
        m.setFDate(ce.getNewValue());
    }
    
     public ObservableList<ClientStatus> RetrieveData(){
         //retrieve data from txt file 
        ObservableList<ClientStatus>data = FXCollections.observableArrayList();
        try{
            Stream<String>d_cl = Files.lines(Paths.get("ClientStatus.txt"));
            d_cl.forEach((String input)->
                { String[]StringData=input.split(",");
                    data.add(new ClientStatus(StringData[0],StringData[1], StringData[2], StringData[3]));

                });
        }
        catch(IOException error)
        {Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("File not found OR problem opening the file");
            alert.showAndWait();
        }
        return data;
    }
    
       
        public void buttonAdd_Clicked(){
            //adds all properties to table 
            
      ClientStatus m = new ClientStatus();
        m.setID(txtID.getText());
       m.setFinished(txtFinished.getText());
       if (txtFinished.getText().equals("False") || txtFinished.getText().equals("false")){
           m.setRating("-");
           m.setFDate("-");
     
        }
         else{
        m.setRating(txtRating.getText());
        
        
      
        
         Date date = Calendar.getInstance().getTime();  
                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yy");  
                String strDate = dateFormat.format(date);  
                m.setFDate(strDate);}
                
       table.getItems().add(m);
        txtID.clear();
        txtFinished.clear();
        txtRating.clear();
        txtFDate.clear();
    }
    
    public boolean validate(String s){
        //validate if id corresponds to an ID in the client database  
        boolean found = false;
        try{
            BufferedReader bure = new BufferedReader(new FileReader("Clients.txt"));
            for (String text; (text = bure.readLine())!=null;)
            {
                String[] StringData  = text.split(",");
                Client m =  new Client (StringData[0],StringData[1], StringData[2], StringData[3], StringData[4], Integer.parseInt(StringData[5]), Integer.parseInt(StringData[6]),Integer.parseInt(StringData[7]),Integer.parseInt(StringData[8]));
                if(s.trim().equals(m.getID().trim())){
                    found =  true;
                }
            }  
        }
        catch(IOException Ex){
            Alert al = new Alert(AlertType.INFORMATION);
            al.setTitle("Information");
            al.setHeaderText("");
            al.setContentText("This file does not exist");
            al.showAndWait();
        }
        return found;
    }
      
      private void Save(){
          // save to txt file 
        ObservableList<ClientStatus>sel,properties;
        properties = table.getItems();
        try (BufferedWriter C = new BufferedWriter (new FileWriter("ClientStatus.txt")))
        {ClientStatus c;
            int m = 0;
            for(Object CL:properties){
                c = properties.get(m);
                C.write(c.getID() + "," + c.getFinished()+ ", " + c.getRating() + ", " + c.getFDate());
                C.newLine();
                m++;
            }
            C.close();
            Alert al = new Alert(AlertType.INFORMATION);
            al.setTitle("SAVE");
            al.setHeaderText("STORAGE!");
            al.setContentText("All Data were Successfully Saved");
            al.showAndWait();
        }
        catch(IOException error)
        {Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("File not found OR problem opening the file");
            alert.showAndWait();
        }
    } 
}

