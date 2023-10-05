import java.io.BufferedReader;//Buffers characters in a character input-stream to efficiently read them
import java.io.BufferedWriter;//Buffers characters in a character output-stream to efficiently write them
import java.io.File;// representation of file pathnames
import java.io.FileReader;//Convenience class for reading character files.
import java.io.FileWriter; //Convenience class for writing character files
import java.io.IOException;//Signals an I/O exception occured
import java.lang.*;//Classes fundemtnal to java design 
import java.nio.file.Files;//Consists of static methods that opperate on file and directories
import java.nio.file.Paths; //Convert path string to URI
import java.util.stream.*;//support for functioanl-style operations on streams of elements 
import java.util.stream.Stream;//sequence of elements for sequential and parallel aggregate opperations 
import javafx.application.*; //Class from which JavaFX extends   
import javafx.beans.property.*;  //Defines read only and writable properties
import javafx.collections.*; // Consists of a number of interfaces and classes
import javafx.event.*; // Provides framework for Fx event delievery and handling 
import javafx.geometry.*; // Provides 2D classes for defining and performing operations on objects related to 2D geometry
import javafx.geometry.*;//It is used to describe positioning and alignment
import javafx.scene.*;   // Class that contains content found on a graph
import javafx.scene.control.*;  // Specialized nodes that can be reused in different application contexts
import javafx.scene.control.Alert.AlertType;//value of property alertType
import javafx.scene.control.cell.*; // Where all cell realated classes are located 
import javafx.scene.layout.*;  // Provides classes for user interface layout 
import javafx.scene.text.*;  //Contains properties to create text and modify its appearence 
import javafx.stage.*;  // Provides container classes for JavaFX containt 
import javafx.util.converter.*; // Is responsible for standard string converters 

/**
 * 
 * This class will provide all datamanagment for all material 
 * @author (your name)
 * @version (a version number or a date)
 */
public class MaterialDATABASE extends Application
{public static void main(String[] args){
        launch(args);
    }
    private TableView<Material> table;
    private TextField txtName,txtID, txtPrice;

    @Override public void start(Stage mainStage){
         // This initializes all graphical components
        Label lblHeading = new Label("Material Inventory");
        lblHeading.setFont(new Font("Arial", 20));
        table =new TableView<Material>();
        table.setEditable(true);
        table.setItems(RetrieveData());

        TableColumn colName = new TableColumn("Name");
        colName.setMinWidth(300);
        colName.setCellValueFactory( new PropertyValueFactory<Material, String>("Name"));
        colName.setCellFactory( TextFieldTableCell.forTableColumn());
        colName.setOnEditCommit(e -> colName_OnEditCommit(e));

        TableColumn colPrice = new TableColumn("Price");
        colPrice.setMinWidth(100);
        colPrice.setCellValueFactory( new PropertyValueFactory<Material, Integer>("Price"));
        colPrice.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        colPrice.setOnEditCommit(e -> colPrice_OnEditCommit(e));

        TableColumn colID = new TableColumn("ID");
        colID.setMinWidth(300);
        colID.setCellValueFactory(new PropertyValueFactory<Material, String>("ID"));
        colID.setCellFactory(TextFieldTableCell.forTableColumn());
        colID.setOnEditCommit(e -> colID_OnEditCommit(e));

        table.getColumns().addAll(colName, colPrice, colID);

        txtName = new TextField();
        txtName.setPromptText("Name");
        txtName.setMinWidth(100);

        txtPrice = new TextField();
        txtPrice.setMaxWidth(100);
        txtPrice.setPromptText("Price");

        txtID = new TextField();
        txtID.setMaxWidth(100);
        txtID.setPromptText("ID");
        
        Button buttonAdd = new Button("Add");
        buttonAdd.setMinWidth(50);
        buttonAdd.setOnAction(e-> buttonAdd_Clicked());
        
         txtID.focusedProperty().addListener((arg0, oldValue, newValue) -> {
                        //validates ID entered by user 
                if (!newValue) 
                { ClientData n = new ClientData();
                    if((!txtID.getText().matches("^[0-9]+")||(n.validate(txtID.getText()))))
                    {

                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText("Error!");
                        alert.setContentText("Incorrect data type. Please enter only numbers OR This ID has already been assigned to a person");
                        alert.showAndWait();

                    }

                }
            }

        );
         txtPrice.focusedProperty().addListener((arg0, oldValue, newValue) -> {
             //validate Price entered by user
                if (!newValue) 
                { ClientData n = new ClientData();
                    if(!Validate_Price(txtPrice.getText()))
                    {

                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText("Error!");
                        alert.setContentText("Incorrect data type. Please enter only numbers OR This ID has already been assigned to a person");
                        alert.showAndWait();

                    }

                }
            }

        );
        
        Button buttonDelete = new Button("Delete");
        buttonDelete.setMinWidth(50);
        buttonDelete.setOnAction(e-> buttonDelete_Clicked());
        Button buttonStore = new Button ("Store");
        buttonStore.setMinWidth(60);
        buttonStore.setOnAction(e-> Save());

        
        
        HBox paneAdd = new HBox();
        paneAdd.getChildren().addAll(txtName, txtPrice, txtID, buttonAdd, buttonDelete, buttonStore);
        paneAdd.setSpacing(6);
        
        VBox paneMain = new VBox();
        paneMain.setSpacing(10);
        paneMain.setPadding(new Insets(10,10,10,10));
        paneMain.getChildren().addAll(lblHeading, table, paneAdd);
        
        Scene scene = new Scene(paneMain);
       mainStage.setScene(scene);
        mainStage.setTitle("Material Inventory");
        mainStage.show();
        
        buttonAdd.setTooltip(new Tooltip("Click to add Material"));
        buttonDelete.setTooltip(new Tooltip("Click to delete Material"));
        
    }
   
    public ObservableList<Material> RetrieveData(){
        //retrieve data from txt file 
        ObservableList<Material>data = FXCollections.observableArrayList();
        try{
            Stream<String>d_cl = Files.lines(Paths.get("Materials.txt"));
            d_cl.forEach((String input)->
                { String[]StringData=input.split(",");
                    data.add(new Material(StringData[0],StringData[1], Integer.parseInt(StringData[2])));
                });
        }
        catch(IOException error)
        {Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("File not found OR problem opening the file");
            alert.showAndWait();
        }
        return data;
    }
    public void colName_OnEditCommit(Event e){
        //edit Name column 
        TableColumn.CellEditEvent<Material, String> ce;
        ce = (TableColumn.CellEditEvent<Material, String>) e; 
        Material  m = ce.getRowValue();
        m.setName(ce.getNewValue());
    }
    
    public void colPrice_OnEditCommit(Event e){
        //edit Price column 
        TableColumn.CellEditEvent<Material, Integer> ce;
        ce = (TableColumn.CellEditEvent<Material, Integer>) e; 
        Material  m = ce.getRowValue();
        m.setPrice(ce.getNewValue());
    }
    public void colID_OnEditCommit(Event e){
        //edit Id column
        TableColumn.CellEditEvent<Material, String> ce;
        ce = (TableColumn.CellEditEvent<Material, String>) e; 
        Material  m = ce.getRowValue();
        m.setID(ce.getNewValue());
    }
    
    public void buttonAdd_Clicked(){
        //adds all properties to table 
        Material m = new Material();
        m.setName(txtName.getText());
        m.setPrice(Integer.parseInt(txtPrice.getText()));
        m.setID(txtID.getText());
        table.getItems().add(m);
        txtName.clear();
        txtPrice.clear();
        txtID.clear();
    }
    
    public void buttonDelete_Clicked(){
        //delete properties from the table 
        ObservableList<Material>sel, items;
        items = table.getItems();
        sel = table.getSelectionModel().getSelectedItems();
        
        for(Material m : sel)
        items.remove(m);
    }
    private void Save(){
        //Save properties from table 
        ObservableList<Material>sel,properties;
        properties = table.getItems();
        try (BufferedWriter M = new BufferedWriter (new FileWriter("Materials.txt")))
        {Material m;
            int i = 0;
            for(Object CL:properties){
                m = properties.get(i);
                M.write(m.getName() + "," + m.getID() + "," + m.getPrice());
                M.newLine();
                i++;
            }
            M.close();
            Alert al = new Alert(AlertType.INFORMATION);
            al.setTitle("SAVE");
            al.setHeaderText("STORAGE!");
            al.setContentText("All Data were Successfully Saved");
            al.showAndWait();
        }
        catch(IOException error)
        {Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("Problem Writing Data!!!");
            alert.showAndWait();
        }
    }   

     public boolean Validate_Price (String S){

        try {

            int i = Integer.parseInt(S);
            return true;
        }catch (NumberFormatException ex) {
            return false;
        }    

    }
    
    }
