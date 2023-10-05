import java.io.*; //System for input and output data streams 
import java.io.BufferedReader; //Buffers characters in a character input-stream to efficiently read them
import java.io.BufferedWriter; //Buffers characters in a character output-stream to efficiently write them
import java.io.File; // representation of file pathnames
import java.io.FileReader; //Convenience class for reading character files.
import java.io.FileWriter; //Convenience class for writing character files.
import java.io.IOException; //Signals an I/O exception occured
import java.lang.*; //Classes fundemtnal to java design 
import java.nio.file.Files;//Consists of static methods that opperate on file and directories
import java.nio.file.Paths; //Convert path string to URI
import java.text.*; //interface for text, dates, numbers and messages 
import java.text.SimpleDateFormat;//class for formating and parsing dates 
import java.util.ArrayList; //resizable array for the List interface
import java.util.Arrays; //ways to maniplate arrays 
import java.util.Date;// represents specific instant in time 
import java.util.stream.*;//support for functioanl-style operations on streams of elements 
import java.util.stream.Stream;//sequence of elements for sequential and parallel aggregate opperations 
import javafx.application.*; //Class from which JavaFX extends   
import javafx.application.Application;
import javafx.beans.property.*;  //Defines read only and writable properties
import javafx.beans.property.SimpleObjectProperty; //full implementation of the Property wrapping for objects 
import javafx.beans.property.StringProperty; // full implementation of the Property wrapping for Strings 
import javafx.collections.*; // Consists of a number of interfaces and classes
import javafx.collections.FXCollections;//static methods that are 1:1 copies of java.util.Collections methods.
import javafx.collections.ObservableList; //list to allow listeners to track changes 
import javafx.event.*; // Provides framework for Fx event delievery and handling 
import javafx.geometry.*; // Provides 2D classes for defining and performing operations on objects related to 2D geometry
import javafx.geometry.Insets; //side offsets for 4 side rectangular area
import javafx.scene.*;   // Class that contains content found on a graph
import javafx.scene.control.*;  // Specialized nodes that can be reused in different application contexts
import javafx.scene.control.Alert.AlertType;//value of property alertType
import javafx.scene.control.cell.*; // Where all cell realated classes are located 
import javafx.scene.control.cell.PropertyValueFactory;//implementation of Callback interface
import javafx.scene.control.TableCell; //Single column intersection in TableView
import javafx.scene.control.TableColumn; //
import javafx.scene.control.TableView;//visualize infinitenumber of rows of data broken into columns
import javafx.scene.Group; //node that contains an ObservableList with children that render when the node is rendered
import javafx.scene.layout.*;  // Provides classes for user interface layout 
import javafx.scene.layout.VBox; // lays children in vertical column 
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.scene.text.*;  //Contains properties to create text and modify its appearence 
import javafx.stage.*;  // Provides container classes for JavaFX containt 
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.*; // Is responsible for standard string converters 
import static javafx.application.Application.launch;

/**
 * This class will provide all datamanagment for all treatment sessions
 * @author (your name)
 * @version (a version number or a date)
 */
public class TreatmentDATABase extends Application
{public static void main(String[] args){
        launch(args);}
    private TableView<Treatment> table;
    private TextField txtPatientID, txtDiagnosis,txtCompensation, txtMaterial0, txtMaterial1, txtMaterial2, txtMaterial3, txtMaterial4, txtDate;
    //private static final String STYLESHEET_PATH = "/styles/styles.css";
    @Override public void start(Stage mainStage){
        //Scene scene = new Scene(new Group());
        //scene.getStylesheets().add(STYLESHEET_PATH);
        mainStage.setTitle("DatePicker TableCell Sample");
        mainStage.setWidth(315);
        mainStage.setHeight(450);

        Label lblHeading = new Label("Treatment database");
        lblHeading.setFont(new Font("Arial", 20));
        table = new TableView<Treatment>();
        table.setEditable(true);
        table.setItems(RetrieveData());

        TableColumn colPatientID = new TableColumn("PatientID");
        colPatientID.setMinWidth(300);
        colPatientID.setCellValueFactory(new PropertyValueFactory<Treatment, String>("PatientID"));
        colPatientID.setCellFactory( TextFieldTableCell.forTableColumn());
        colPatientID.setOnEditCommit(e -> colPatientID_OnEditCommit(e));

        TableColumn colDiagnosis = new TableColumn("Diagnosis");
        colDiagnosis.setMinWidth(300);
        colDiagnosis.setCellValueFactory(new PropertyValueFactory<Treatment, String>("Diagnosis"));
        colDiagnosis.setCellFactory( TextFieldTableCell.forTableColumn());
        colDiagnosis.setOnEditCommit(e -> colDiagnosis_OnEditCommit(e));

        TableColumn colMaterial0 = new TableColumn("Material0");
        colMaterial0.setMinWidth(300);
        colMaterial0.setCellValueFactory(new PropertyValueFactory<Treatment, String>("Material0"));
        colMaterial0.setCellFactory( TextFieldTableCell.forTableColumn());
        colMaterial0.setOnEditCommit(e -> colMaterial0_OnEditCommit(e));

        TableColumn colMaterial1 = new TableColumn("Material1");
        colMaterial1.setMinWidth(300);
        colMaterial1.setCellValueFactory(new PropertyValueFactory<Treatment, String>("Material1"));
        colMaterial1.setCellFactory( TextFieldTableCell.forTableColumn());
        colMaterial1.setOnEditCommit(e -> colMaterial1_OnEditCommit(e));

        TableColumn colMaterial2 = new TableColumn("Material2");
        colMaterial2.setMinWidth(300);
        colMaterial2.setCellValueFactory(new PropertyValueFactory<Treatment, String>("Material2"));
        colMaterial2.setCellFactory( TextFieldTableCell.forTableColumn());
        colMaterial2.setOnEditCommit(e -> colMaterial2_OnEditCommit(e));

        TableColumn colMaterial3 = new TableColumn("Material3");
        colMaterial3.setMinWidth(300);
        colMaterial3.setCellValueFactory(new PropertyValueFactory<Treatment, String>("Material3"));
        colMaterial3.setCellFactory( TextFieldTableCell.forTableColumn());
        colMaterial3.setOnEditCommit(e -> colMaterial3_OnEditCommit(e));

        TableColumn colMaterial4 = new TableColumn("Material4");
        colMaterial4.setMinWidth(300);
        colMaterial4.setCellValueFactory(new PropertyValueFactory<Treatment, String>("Material4"));
        colMaterial4.setCellFactory( TextFieldTableCell.forTableColumn());
        colMaterial4.setOnEditCommit(e -> colMaterial4_OnEditCommit(e));

        TableColumn colCompensation = new TableColumn("Compensation");
        colCompensation.setMinWidth(100);
        colCompensation.setCellValueFactory( new PropertyValueFactory<Treatment, Integer>("Compensation"));
        colCompensation.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        colCompensation.setOnEditCommit(e -> colCompensation_OnEditCommit(e));

        TableColumn colDate = new TableColumn("Date");
        colDate.setEditable(true);
        colDate.setPrefWidth(75);
        colDate.setMinWidth(200);

        colDate.setCellValueFactory(new PropertyValueFactory<TreatmentDATABase, Date>("date"));
        colDate.setCellFactory(new Callback<TableColumn, TableCell>() {
                @Override
                public TableCell call(TableColumn p) {
                   DFormat datePick = new DFormat(RetrieveData());
                    return datePick;
                }
            });
        table.getColumns().addAll(colPatientID, colDiagnosis,colCompensation,colMaterial0, colMaterial1, colMaterial2, colMaterial3, colMaterial4, colDate);
        table.setItems(RetrieveData());

        txtPatientID = new TextField();
        txtPatientID.setPromptText("PatientID");
        txtPatientID.setMinWidth(100);

        txtDiagnosis =new TextField();
        txtDiagnosis.setPromptText("Diagnosis");
        txtDiagnosis.setMinWidth(100);

        txtCompensation = new TextField();
        txtCompensation.setPromptText("Compensation");
        txtCompensation.setMinWidth(100);

        txtMaterial0 = new TextField();
        txtMaterial0.setPromptText("Material0");
        txtMaterial0.setMinWidth(100);

        txtMaterial1 = new TextField();
        txtMaterial1.setPromptText("Material1");
        txtMaterial1.setMinWidth(100);

        txtMaterial2 = new TextField();
        txtMaterial2.setPromptText("Material2");
        txtMaterial2.setMinWidth(100);

        txtMaterial3 = new TextField();
        txtMaterial3.setPromptText("Material3");
        txtMaterial3.setMinWidth(100);

        txtMaterial4 = new TextField();
        txtMaterial4.setPromptText("Material4");
        txtMaterial4.setMinWidth(100);

        txtDate = new TextField();
        txtDate.setPromptText("Date");
        txtDate.setMinWidth(100);

        Button buttonAdd = new Button("Add");
        buttonAdd.setMinWidth(50);
        buttonAdd.setOnAction(e-> buttonAdd_Clicked());
        buttonAdd.setStyle("-fx-backround-color : #1200AB; " );

        Button buttonMR= new Button("Material Rating");
        buttonMR.setMinWidth(50);

        buttonMR.setStyle("-fx-backround-color : #1200AB; " );
        txtPatientID.focusedProperty().addListener((arg0, oldValue, newValue) -> {
                //validate Id entered by user
                if (!newValue) 
                { 
                    if((!txtPatientID.getText().matches("^[0-9]+")))
                    {

                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText("Error!");
                        alert.setContentText("Incorrect data type. Please enter only numbers");
                        alert.showAndWait();

                    }
                    if(!validate(txtPatientID.getText())){
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText("Error!");
                        alert.setContentText("This ID does not correspond to an ID in the client database, Enter a different ID!");
                        alert.showAndWait();}

                }}
        );
        txtMaterial0.focusedProperty().addListener((arg0, oldValue, newValue) -> {
                // validate material entered by users
                if (!newValue) 
                { 
                    if(!validate1(txtMaterial0.getText()))
                    {

                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText("Error!");
                        alert.setContentText("This material does not exist in the material data base");
                        alert.showAndWait();

                    }}});
        txtMaterial1.focusedProperty().addListener((arg0, oldValue, newValue) -> {
                // validate material entered by users
                if (!newValue) 
                { 
                    if(!validate1(txtMaterial1.getText()))
                    {

                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText("Error!");
                        alert.setContentText("This material does not exist in the material data base");
                        alert.showAndWait();

                    }}});
        txtMaterial2.focusedProperty().addListener((arg0, oldValue, newValue) -> {
                // validate material entered by users
                if (!newValue) 
                { 
                    if(!validate1(txtMaterial2.getText()))
                    {

                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText("Error!");
                        alert.setContentText("This material does not exist in the material data base");
                        alert.showAndWait();

                    }}});
        txtMaterial3.focusedProperty().addListener((arg0, oldValue, newValue) -> {
                // validate material entered by users
                if (!newValue) 
                { 
                    if(!validate1(txtMaterial3.getText()))
                    {

                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText("Error!");
                        alert.setContentText("This material does not exist in the material data base");
                        alert.showAndWait();

                    }}});

        txtMaterial4.focusedProperty().addListener((arg0, oldValue, newValue) -> {
                // validate material entered by users
                if (!newValue) 
                { 
                    if(!validate1(txtMaterial4.getText()))
                    {

                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText("Error!");
                        alert.setContentText("This material does not exist in the material data base");
                        alert.showAndWait();

                    }}});

       

        Button buttonDelete = new Button("Delete");
        buttonDelete.setMinWidth(50);
        buttonDelete.setOnAction(e-> buttonDelete_Clicked());
        Button buttonStore = new Button ("Store");
        buttonStore.setMinWidth(60);

        buttonStore.setOnAction(e-> Save());

        Button buttonShowMaterials = new Button("Show Materials");
        buttonShowMaterials.setMinWidth(50);
        //  buttonShowMaterials.setOnAction(e-> buttonShowMaterials_Clicked());

        HBox paneAdd = new HBox();
        paneAdd.getChildren().addAll(txtPatientID, txtDiagnosis,txtCompensation,txtMaterial0, txtMaterial1, txtMaterial2, txtMaterial3, txtMaterial4, txtDate, buttonAdd,buttonMR, buttonDelete,buttonStore, buttonShowMaterials);
        paneAdd.setSpacing(6);

        VBox paneMain = new VBox();
        paneMain.setSpacing(10);
        paneMain.setPadding(new Insets(10,10,10,10));
        paneMain.getChildren().addAll(lblHeading, table, paneAdd);

        Scene scene = new Scene(paneMain);
        mainStage.setScene(scene);
        mainStage.setTitle("Treatment data base");
        mainStage.show();

        buttonAdd.setTooltip(new Tooltip("Click to add Treatment"));
        buttonDelete.setTooltip(new Tooltip("Click to delete Treatment"));

        
        
        
        
        buttonMR.setOnAction(new EventHandler<ActionEvent>()
        //show the number of times each material is used
            { @Override
                public void handle (ActionEvent Event)
                {

                    ArrayList<Material>Mdata = new ArrayList<Material>();
                    try{
                        Stream<String>d_cl = Files.lines(Paths.get("Materials.txt"));
                        d_cl.forEach((String input)->
                            { String[]StringData=input.split(",");
                                Mdata.add
                                (new Material (StringData[0], StringData[1],Integer.parseInt(StringData[2])));
                            });
                    }
                    catch(IOException error)
                    {Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setContentText("File not found OR problem opening the file");
                        alert.showAndWait();
                    }
                    int[] count = new int[Mdata.size()];  

                    for (int m =0; m< Mdata.size(); m++){
                        String Name  =  Mdata.get(m).getName();
                        ObservableList<Treatment> s,objects;      
                        objects = table.getItems();    
                        count[m] = 0;
                        for(int i=0; i<objects.size(); i++){

                            String material0, material1, material2, material3, material4;
                            material0 = objects.get(i).getMaterial0();
                            material1 = objects.get(i).getMaterial1();
                            material2 = objects.get(i).getMaterial2();
                            material3 = objects.get(i).getMaterial3();
                            material4 = objects.get(i).getMaterial4();

                            if ( Name.equals(material0)){
                                count[m]++;}
                            if ( Name.equals(material1)){
                                count[m]++;}
                            if ( Name.equals(material2)){
                                count[m]++;}
                            if ( Name.equals(material3)){
                                count[m]++;}
                            if ( Name.equals(material4)){
                                count[m]++;}


                        }

                    }
                    boolean sorted = false;
                    int temp;
                    Material temp1; 
                    while (!sorted) {
                        sorted = true;
                        for (int i = 0; i < Mdata.size()- 1; i++) {
                            if (count[i] > count[i+1]) {
                                temp = count[i];
                                temp1 = Mdata.get(i);
                                count[i] = count[i+1];
                                Mdata.set(i, Mdata.get(i + 1));
                                count[i+1] = temp;
                                Mdata.set(i + 1, temp1);
                                sorted = false;
                            }
                        }
                    }
                    String Lmessage = " ";

                    for (int i =0; i<Mdata.size(); i++){
                        Lmessage = Lmessage + Mdata.get(i).getName() + " have been used " + count[i] + " time/s" +  "\n" + "\n";

                    }
                    StackPane sP=new StackPane();
                    Label message=new Label(Lmessage);
                    message.setFont(Font.font("arial", FontWeight.BOLD, FontPosture.REGULAR, 20));

                    sP.getChildren().add(message);
                    Scene Scene2= new Scene(sP,1600,1000,Color.BLUE);

                    Stage MaterialsPopUp = new Stage();
                    MaterialsPopUp.setTitle("MATERIALS !!!");
                    MaterialsPopUp.setScene(Scene2);

                    MaterialsPopUp.initModality(Modality.WINDOW_MODAL);
                    MaterialsPopUp.initOwner(mainStage);
                    MaterialsPopUp.setX(mainStage.getX()+00);
                    MaterialsPopUp.setY(mainStage.getY()+100);
                    MaterialsPopUp.show();

                    System.out.println(Arrays.toString(count));
                }}); 

        buttonShowMaterials.setOnAction(new EventHandler<ActionEvent>()
        //show a list of material, material Id and price 
                    { @Override
                public void handle (ActionEvent Event)
                {
                    ObservableList<Treatment> s,objects;
                    objects = table.getItems();    
                    s = table.getSelectionModel().getSelectedItems();
                    String material0, material1, material2, material3, material4;
                    material0 = s.get(0).getMaterial0();
                    material1 = s.get(0).getMaterial1();
                    material2 = s.get(0).getMaterial2();
                    material3 = s.get(0).getMaterial3();
                    material4 = s.get(0).getMaterial4();
                    // System.out.println(material0 + " " + material1 + " " + material2 + " " + material3 + " " + material4);

                    ArrayList<Material>data = new ArrayList<Material>();
                    try{
                        Stream<String>d_cl = Files.lines(Paths.get("Materials.txt"));
                        d_cl.forEach((String input)->
                            { String[]StringData=input.split(",");
                                data.add
                                (new Material(StringData[0],StringData[1], Integer.parseInt(StringData[2])));
                            });
                    }
                    catch(IOException error)
                    {Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setContentText("File not found OR problem opening the file");
                        alert.showAndWait();
                    }

                    Material m0 = new Material();
                    Material m1 = new Material();
                    Material m2 = new Material();
                    Material m3 = new Material();
                    Material m4 = new Material();
                    //Simple Sequential Search is used to check if multiple conditions are met 
                    for (int i=0; i<data.size(); i++){
                        String u = data.get(i).getName().trim();

                        if (material0.trim().equals(u)){
                            m0 = data.get(i);
                        }
                        if( material1.trim().equals(u)){
                            m1 = data.get(i);

                        }
                        if (material2.trim().equals(u)){
                            m2 = data.get(i);
                        }
                        if (material3.trim().equals(u)){
                            m3 = data.get(i);
                        }
                        if (material4.trim().equals(u)){
                            m4 = data.get(i);
                        }
                    }
                    {
                        Label message=new Label("Material 0 : " + m0.getName() + "   , ID : " + m0.getID() + "   , Price : " + m0.getPrice()  + "\n" + "Material 1 : " + m1.getName() + "   ,ID : "+ m1.getID() + "   , Price : "+m1.getPrice() + "\n" + "Material 2 : " + m2.getName() + "   , ID : " + m2.getID() + "   , Price : "  + m2.getPrice() + "\n" + "Material 3 : " + m3.getName() + "   , ID : " + m3.getID()  + "   , Price : " + m3.getPrice()  +"\n" + "Material 4 : " + m4.getName() + "   , ID : " + m4.getID() + "   , Price : " + m4.getPrice());
                        message.setFont(Font.font("arial", FontWeight.BOLD, FontPosture.REGULAR, 40));
                        StackPane sP=new StackPane();
                        sP.getChildren().add(message);
                        Scene Scene2= new Scene(sP,1600,1000,Color.BLUE);

                        Stage MaterialsPopUp = new Stage();
                        MaterialsPopUp.setTitle("THE MATERIAL CHARACTERISTICS !!!");
                        MaterialsPopUp.setScene(Scene2);

                        MaterialsPopUp.initModality(Modality.WINDOW_MODAL);
                        MaterialsPopUp.initOwner(mainStage);
                        MaterialsPopUp.setX(mainStage.getX()+00);
                        MaterialsPopUp.setY(mainStage.getY()+100);
                        MaterialsPopUp.show();
                    }}}); 
    }

    public ObservableList<Treatment> RetrieveData(){
        //retrieve data from  txt file 
        ObservableList<Treatment>data = FXCollections.observableArrayList();
        try{
            Stream<String>d_cl = Files.lines(Paths.get("Treatment.txt"));
            d_cl.forEach((String input)->
                { String[]StringData=input.split(",");
                    data.add(new Treatment(StringData[0],StringData[1],StringData[2], StringData[3], StringData[4],StringData[5],StringData[6],Integer.parseInt(StringData[7]),new Date(StringData[8])));

                });
        }
        catch(IOException error)
        {Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("File not found OR problem opening the file");
            alert.showAndWait();
        }
        return data;
    }

    public void colPatientID_OnEditCommit(Event e){
        //edit patient Id column 
        TableColumn.CellEditEvent<Treatment, String> ce;
        ce = (TableColumn.CellEditEvent<Treatment, String>) e; 
        Treatment  m = ce.getRowValue();
        m.setPatientID(ce.getNewValue());
    }

    public void colDiagnosis_OnEditCommit(Event e){
        //edit diagnosis column 
        TableColumn.CellEditEvent<Treatment, String> ce;
        ce = (TableColumn.CellEditEvent<Treatment, String>) e; 
        Treatment  m = ce.getRowValue();
        m.setDiagnosis(ce.getNewValue());
    }

    public void colMaterial0_OnEditCommit(Event e){
        //edit material 0 column 
        TableColumn.CellEditEvent<Treatment, String>ce;
        ce = (TableColumn.CellEditEvent<Treatment, String>)e;
        Treatment m = ce.getRowValue();
        m.setMaterial0(ce.getNewValue());}

    public void colMaterial1_OnEditCommit(Event e){
        //edit material 1 column 
        TableColumn.CellEditEvent<Treatment, String>ce;
        ce = (TableColumn.CellEditEvent<Treatment, String>)e;
        Treatment m = ce.getRowValue();
        m.setMaterial1(ce.getNewValue());}

    public void colMaterial2_OnEditCommit(Event e){
        //edit material 2 column 
        TableColumn.CellEditEvent<Treatment, String>ce;
        ce = (TableColumn.CellEditEvent<Treatment, String>)e;
        Treatment m = ce.getRowValue();
        m.setMaterial2(ce.getNewValue());}

    public void colMaterial3_OnEditCommit(Event e){
        //edit material 3 column
        TableColumn.CellEditEvent<Treatment, String>ce;
        ce = (TableColumn.CellEditEvent<Treatment, String>)e;
        Treatment m = ce.getRowValue();
        m.setMaterial3(ce.getNewValue());}

    public void colMaterial4_OnEditCommit(Event e){
        //edit material 4 column 
        TableColumn.CellEditEvent<Treatment, String>ce;
        ce = (TableColumn.CellEditEvent<Treatment, String>)e;
        Treatment m = ce.getRowValue();
        m.setMaterial4(ce.getNewValue());}

    public void colCompensation_OnEditCommit(Event e){
        //edit compensation column
        TableColumn.CellEditEvent<Treatment, Integer> ce;
        ce = (TableColumn.CellEditEvent<Treatment, Integer>) e; 
        Treatment m = ce.getRowValue();
        m.setCompensation(ce.getNewValue());
    }

    public void buttonAdd_Clicked(){
        //adds all properties to table 
        Treatment m = new Treatment();
        m.setPatientID(txtPatientID.getText());
        m.setDiagnosis(txtDiagnosis.getText());
        m.setCompensation(Integer.parseInt(txtCompensation.getText()));
        m.setDiagnosis(txtDiagnosis.getText());
        try
        {
            m.setDate(new SimpleDateFormat("dd/MM/yyyy").parse((txtDate.getText())));}
        catch(ParseException x)
        {}
        m.setMaterial0(txtMaterial0.getText());
        m.setMaterial1(txtMaterial1.getText());
        m.setMaterial2(txtMaterial2.getText());
        m.setMaterial3(txtMaterial3.getText());
        m.setMaterial4(txtMaterial4.getText());

        table.getItems().add(m);
        txtPatientID.clear();
        txtDiagnosis.clear();
        txtCompensation.clear();
        txtDate.clear();
        txtMaterial0.clear();
        txtMaterial1.clear();
        txtMaterial2.clear();
        txtMaterial3.clear();
        txtMaterial4.clear();
    }

    public void buttonDelete_Clicked(){
        //delete properties from table 
        ObservableList<Treatment>sel, items;
        items = table.getItems();
        sel = table.getSelectionModel().getSelectedItems();

        for(Treatment m : sel)
            items.remove(m);

    }    

    public boolean validate(String s){
        //validate if patient Id corresponeds to an id in the client database 
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

    public boolean validate1(String s){
        //validate if materials entered exist in the materiial database 
        boolean found = false;
        try{
            BufferedReader bure = new BufferedReader(new FileReader("Materials.txt"));
            for (String text; (text = bure.readLine())!=null;)
            {
                String[] StringData  = text.split(",");
                Material m =  new Material (StringData[0], StringData[1],Integer.parseInt(StringData[2]));
                if(s.trim().equals(m.getName().trim())){
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
        //save table contents in txt file 
        ObservableList<Treatment>sel,properties;
        properties = table.getItems();
        try (BufferedWriter C = new BufferedWriter (new FileWriter("Treatment.txt")))
        {Treatment c;
            int m = 0;
            for(Object CL:properties){
                c = properties.get(m);
                C.write(c. getPatientID()+ "," +c.getDiagnosis() + "," + c.getMaterial0() + "," + c.getMaterial1() + "," + c.getMaterial2() + "," +c.getMaterial3() + "," + c.getMaterial4() + ","+c.getCompensation() + "," + c.getDateAsString());
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

