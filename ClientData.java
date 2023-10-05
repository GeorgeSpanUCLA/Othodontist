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


/**
 * 
 * This class will provide all data managment for all patients 
 * @author (your name)
 * @version (a version number or a date)
 */


public class ClientData extends Application
{public static void main(String[] args){
        launch(args);}
    private TableView<Client> table;
    private TextField txtName, txtSurname, txtID, txtTelephone, txtEmail, txtExpence, txtPayment, txtProfit, txtThreshold;

    @Override public void start(Stage mainStage){
        // This initializes all graphical components
        Label lblHeading = new Label("Client database");
        lblHeading.setFont(new Font("Arial", 20));
        table =new TableView<Client>();
        table.setEditable(true);
        table.setItems(RetrieveData());

        TableColumn colName = new TableColumn("Name");
        colName.setMinWidth(300);
        colName.setCellValueFactory( new PropertyValueFactory<Client, String>("Name"));
        colName.setCellFactory( TextFieldTableCell.forTableColumn());
        colName.setOnEditCommit(e -> colName_OnEditCommit(e));

        TableColumn colSurname = new TableColumn("Surname");
        colSurname.setMinWidth(300);
        colSurname.setCellValueFactory( new PropertyValueFactory<Client, String>("Name"));
        colSurname.setCellFactory( TextFieldTableCell.forTableColumn());
        colSurname.setOnEditCommit(e -> colSurname_OnEditCommit(e));

        TableColumn colID = new TableColumn("ID");
        colID.setMinWidth(300);
        colID.setCellValueFactory(new PropertyValueFactory<Client, String>("ID"));
        colID.setCellFactory(TextFieldTableCell.forTableColumn());
        colID.setOnEditCommit(e -> colID_OnEditCommit(e));

        TableColumn colTelephone = new TableColumn("Telephone");
        colTelephone.setMinWidth(300);
        colTelephone.setCellValueFactory(new PropertyValueFactory<Client, String>("Telephone"));
        colTelephone.setCellFactory(TextFieldTableCell.forTableColumn());
        colTelephone.setOnEditCommit(e -> colTelephone_OnEditCommit(e));

        TableColumn colEmail = new TableColumn("Email");
        colEmail.setMinWidth(300);
        colEmail.setCellValueFactory(new PropertyValueFactory<Client, String>("Email"));
        colEmail.setCellFactory(TextFieldTableCell.forTableColumn());
        colEmail.setOnEditCommit(e -> colEmail_OnEditCommit(e));

        TableColumn colExpence = new TableColumn("Expence");
        colExpence.setMinWidth(100);
        colExpence.setCellValueFactory( new PropertyValueFactory<Client, Integer>("Expence"));
        colExpence.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        colExpence.setOnEditCommit(e -> colExpence_OnEditCommit(e));

        TableColumn colPayment = new TableColumn("Payment");
        colPayment.setMinWidth(100);
        colPayment.setCellValueFactory( new PropertyValueFactory<Client, Integer>("Payment"));
        colPayment.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        colPayment.setOnEditCommit(e -> colPayment_OnEditCommit(e));

        TableColumn colProfit = new TableColumn("Profit");
        colProfit.setMinWidth(100);
        colProfit.setCellValueFactory( new PropertyValueFactory<Client, Integer>("Profit"));
        colProfit.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        colProfit.setOnEditCommit(e -> colProfit_OnEditCommit(e));

        TableColumn colThreshold = new TableColumn("Threshold");
        colThreshold.setMinWidth(100);
        colThreshold.setCellValueFactory( new PropertyValueFactory<Client, Integer>("Threshold"));
        colThreshold.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        colThreshold.setOnEditCommit(e -> colThreshold_OnEditCommit(e));

        table.getColumns().addAll(colName, colSurname, colID, colTelephone, colEmail, colExpence, colPayment, colProfit, colThreshold);
        txtName = new TextField();
        txtName.setPromptText("Name");
        txtName.setMinWidth(100);

        txtSurname = new TextField();
        txtSurname.setPromptText("Surname");
        txtSurname.setMinWidth(100);

        txtID = new TextField();
        txtID.setMaxWidth(100);
        txtID.setPromptText("ID");

        txtTelephone = new TextField();
        txtTelephone.setMaxWidth(100);
        txtTelephone.setPromptText("Thelephone");

        txtEmail = new TextField();
        txtEmail.setMaxWidth(100);
        txtEmail.setPromptText("Email");

        txtExpence = new TextField();
        txtExpence.setMaxWidth(100);
        txtExpence.setPromptText("Expence");

        txtPayment = new TextField();
        txtPayment.setMaxWidth(100);
        txtPayment.setPromptText("Payment");

        txtProfit = new TextField();
        txtProfit.setMaxWidth(100);
        txtProfit.setPromptText("Profit");

        txtThreshold = new TextField();
        txtThreshold.setMaxWidth(100);
        txtThreshold.setPromptText("Threshold");

        Button buttonAdd = new Button("Add");
        buttonAdd.setMinWidth(50);
        buttonAdd.setOnAction(e-> buttonAdd_Clicked());
        buttonAdd.setStyle("-fx-backround-color : #1200AB; " );

        txtName.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            // Validates Name entered by user
                if (!newValue) 
                { 
                    if(!txtName.getText().matches("[a-zA-Z]+"))
                    {

                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText("Error!");
                        alert.setContentText("Incorrect data type. Please enter only characterts");
                        alert.showAndWait();

                    }

                }
            }

        );
        txtID.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            //validates ID entered by user 
                if (!newValue) 
                { 
                    if((!txtID.getText().matches("^[0-9]+")||(validate(txtID.getText()))))
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

        txtSurname.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            //validates Surname entred by user 
                if (!newValue) 
                { 
                    if(!txtSurname.getText().matches("[a-zA-Z]+"))
                    {

                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText("Error!");
                        alert.setContentText("Incorrect data type. Please enter only characterts");
                        alert.showAndWait();

                    }

                }
            }

        );
        txtTelephone.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            // validates telephone entered by user 
                if (!newValue) 
                { 
                    if(!txtTelephone.getText().matches("^(\\d{3}[- .]?){2}\\d{4}$"))
                    {

                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText("Error!");
                        alert.setContentText("Incorrect data type. Please enter only valid characterts");
                        alert.showAndWait();

                    }

                }
            }

        );
        txtEmail.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            // validates email address entered by the user 
                if (!newValue) 
                { 
                    if(!txtEmail.getText().matches("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$"))
                    {

                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText("Error!");
                        alert.setContentText("Incorrect data type. Please enter only valid characterts");
                        alert.showAndWait();

                    }

                }
            }

        );

        txtExpence.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            //validates expence entered by user 
                if (!newValue) 
                { 
                    if(!txtExpence.getText().matches("^[0-9]+"))
                    {

                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText("Error!");
                        alert.setContentText("Incorrect data type. Please enter only numbers");
                        alert.showAndWait();

                    }

                }
            }

        );
        txtProfit.focusedProperty().addListener((arg0, oldValue, newValue) -> {
             //validates profit entered by user 
                if (!newValue) 
                { 
                    if(!txtProfit.getText().matches("^[0-9]+"))
                    {

                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText("Error!");
                        alert.setContentText("Incorrect data type. Please enter only numbers");
                        alert.showAndWait();

                    }

                }
            }

        );
        txtPayment.focusedProperty().addListener((arg0, oldValue, newValue) -> {
             //validates payment entered by user 
                if (!newValue) 
                { 
                    if(!txtPayment.getText().matches("^[0-9]+"))
                    {

                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText("Error!");
                        alert.setContentText("Incorrect data type. Please enter only characterts");
                        alert.showAndWait();

                    }

                }
            }

        );
        txtThreshold.focusedProperty().addListener((arg0, oldValue, newValue) -> {
             //validates treshold entered by user 
                if (!newValue) 
                { 
                    if(!txtThreshold.getText().matches("^[0-9]+"))
                    {

                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText("Error!");
                        alert.setContentText("Incorrect data type. Please enter only characterts");
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

        Button buttonBill = new Button("Bill");
        buttonBill.setMinWidth(50);

        Button buttonPhotos = new Button ("Photos");
        buttonPhotos.setMinWidth(60);

        Button buttonCalcThresh = new Button("Calculate Thershold");
        buttonCalcThresh.setMinWidth(50);
        buttonCalcThresh.setOnAction(e-> buttonCalcThersh_Clicked());

        HBox paneAdd = new HBox();
        paneAdd.getChildren().addAll(txtName, txtSurname, txtID, txtTelephone, txtEmail, txtExpence, txtPayment, txtProfit, txtThreshold,  buttonAdd, buttonDelete, buttonStore, buttonPhotos, buttonBill, buttonCalcThresh);
        paneAdd.setSpacing(6);

        VBox paneMain = new VBox();
        paneMain.setSpacing(10);
        paneMain.setPadding(new Insets(10,10,10,10));
        paneMain.getChildren().addAll(lblHeading, table, paneAdd);

        Scene scene = new Scene(paneMain);
        mainStage.setScene(scene);
        mainStage.setTitle("Client data base");
        mainStage.show();

        buttonAdd.setTooltip(new Tooltip("Click to add Client"));
        buttonDelete.setTooltip(new Tooltip("Click to delete Client"));

        buttonBill.setOnAction(new EventHandler<ActionEvent>()
        //calculates total bill
            { @Override
                public void handle (ActionEvent Event)
                {
                    ObservableList<Client> s,objects;

                    objects = table.getItems();   
                  

                    s = table.getSelectionModel().getSelectedItems();
                    String ID = s.get(0).getID();
                    String Name = s.get(0).getName();

                    ArrayList<Client>Cdata = new ArrayList<Client>();
                    try{
                        Stream<String>d_cl = Files.lines(Paths.get("Clients.txt"));
                        d_cl.forEach((String input)->
                            { String[]StringData=input.split(",");
                                Cdata.add
                                (new Client(StringData[0],StringData[1], StringData[2], StringData[3], StringData[4], Integer.parseInt(StringData[5]), Integer.parseInt(StringData[6]),Integer.parseInt(StringData[7]),Integer.parseInt(StringData[8])));
                            });
                    }
                    catch(IOException error)
                    {Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setContentText("File not found OR problem opening the file");
                        alert.showAndWait();
                    }
                    ArrayList<Treatment>Tdata = new ArrayList<Treatment>();
                    try{
                        Stream<String>d_cl = Files.lines(Paths.get("Treatment.txt"));
                        d_cl.forEach((String input)->
                            { String[]StringData=input.split(",");
                                Tdata.add
                                (new Treatment ( StringData[0], StringData[1], StringData[2], StringData[3], StringData[4], StringData[5], StringData[6], Integer.parseInt(StringData[7]),new Date(StringData[8]))); 
                            });
                    }
                    catch(IOException error)
                    {Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setContentText("File not found OR problem opening the file");
                        alert.showAndWait();
                    }
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
                    String material0 = "", material1= "", material2= "", material3= "", material4= "";
                    int Bill =0;
                    int count =0;
                    for (int i=0; i<Tdata.size(); i++){
                        if(Tdata.get(i).getPatientID().equals(ID)){
                            material0 = Tdata.get(i).getMaterial0();
                            material1 = Tdata.get(i).getMaterial1();
                            material2 = Tdata.get(i).getMaterial2();
                            material3 = Tdata.get(i).getMaterial3();
                            material4 = Tdata.get(i).getMaterial4();
                            count = count +1;
                        }
                        for (int c=0; c<Mdata.size(); c++){
                            if(material0.equals(Mdata.get(c).getName())){
                                Bill = Bill + Mdata.get(c).getPrice();

                            }}
                        for (int c=0; c<Mdata.size(); c++){
                            if(material1.equals(Mdata.get(c).getName())){
                                Bill = Bill + Mdata.get(c).getPrice();

                            }}
                        for (int c=0; c<Mdata.size(); c++){
                            if(material2.equals(Mdata.get(c).getName())){
                                Bill = Bill + Mdata.get(c).getPrice();

                            }}
                        for (int c=0; c<Mdata.size(); c++){
                            if(material3.equals(Mdata.get(i).getName())){
                                Bill = Bill + Mdata.get(i).getPrice();

                            }}
                        for (int c=0; c<Mdata.size(); c++){
                            if(material4.equals(Mdata.get(c).getName())){
                                Bill = Bill + Mdata.get(c).getPrice();

                            }}

                    }
                    Bill = Bill + count *100;

                    Label message=new Label( Name + "'s total Bill is " + Bill + "!!!");
                    message.setFont(Font.font("arial", FontWeight.BOLD, FontPosture.REGULAR, 40));
                    StackPane sP=new StackPane();
                    sP.getChildren().add(message);
                    Scene Scene2= new Scene(sP,700,500,Color.BLUE);

                    Stage BillPopUp = new Stage();
                    BillPopUp.setTitle("Total bill of client!!!");
                    BillPopUp.setScene(Scene2);

                    BillPopUp.initModality(Modality.WINDOW_MODAL);
                    BillPopUp.initOwner(mainStage);
                    BillPopUp.setX(mainStage.getX()+00);
                    BillPopUp.setY(mainStage.getY()+100);

                    BillPopUp.show();
                }});

        buttonPhotos.setOnAction(new EventHandler<ActionEvent>()
        //opens folder with client photos 
            {@Override
                public void handle(ActionEvent Event)
                {ObservableList<Client>sel, Objects;
                    Objects = table.getItems();
                    sel=table.getSelectionModel().getSelectedItems();
                    String Name = sel.get(0).getName();

                    String PATH = "D:\\CS\\IA\\Pictures" + "\\" + Name;
                    PATH = PATH.trim();

                    //String PATH = "C:\\Pictures\\George";
                    System.out.println(PATH);
                    TilePane tile = new TilePane();
                    tile.setHgap(20);
                    tile.setVgap(20);
                    tile.setPadding(new Insets(20));
                    tile.setPrefColumns(4);

                    File dir = new File(PATH);
                    File[] files = dir.listFiles();
                    for (File f : files)
                    {
                        Image img = new Image(f.toURI().toString(),
                                200, 200, true, true);

                        ImageView iview = new ImageView(img);
                        iview.setFitWidth(200);
                        iview.setFitHeight(200);
                        iview.setPreserveRatio(true);

                        iview.setOnMouseClicked(e ->
                            {
                                iview.setScaleX(2.0);
                                iview.setScaleY(2.0);
                            });

                        Text txt = new Text(f.getName());
                        txt.setFont(new Font("Times New Roman", 16));

                        Region spacer = new Region();

                        VBox box = new VBox(10, iview, spacer, txt);
                        box.setVgrow(spacer, Priority.ALWAYS);
                        box.setAlignment(Pos.CENTER);
                        tile.getChildren().add(box);
                    }
                    Stage ps = new Stage();
                    ScrollPane scroll = new ScrollPane(tile);
                    scroll.setMinWidth(920);
                    scroll.setMinHeight(450);

                    Scene scene = new Scene(scroll);
                    ps.setScene(scene);
                    ps.setTitle("Photo Viewer - " + PATH);
                    ps.show();
                }

            });

    }

   
    
    public ObservableList<Client> RetrieveData(){
        //retrieves data from txt file
        ObservableList<Client>data = FXCollections.observableArrayList();
        try{
            Stream<String>d_cl = Files.lines(Paths.get("Clients.txt"));
            d_cl.forEach((String input)->
                { String[]StringData=input.split(",");
                    data.add(new Client(StringData[0],StringData[1], StringData[2], StringData[3], StringData[4], Integer.parseInt(StringData[5]), Integer.parseInt(StringData[6]),Integer.parseInt(StringData[7]),Integer.parseInt(StringData[8])));

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
        //edit name column 
        TableColumn.CellEditEvent<Client, String> ce;
        ce = (TableColumn.CellEditEvent<Client, String>) e; 
        Client  m = ce.getRowValue();
        m.setName(ce.getNewValue());
    }

    public void colID_OnEditCommit(Event e){
         //edit ID column 
        TableColumn.CellEditEvent<Client, String> ce;
        ce = (TableColumn.CellEditEvent<Client, String>) e; 
        Client  m = ce.getRowValue();
        m.setID(ce.getNewValue());
    }

    public void colSurname_OnEditCommit(Event e){
        //edit surname column 
        TableColumn.CellEditEvent<Client, String> ce;
        ce = (TableColumn.CellEditEvent<Client, String>) e; 
        Client  m = ce.getRowValue();
        m.setSurname(ce.getNewValue());
    }

    public void colTelephone_OnEditCommit(Event e){
        //edit Telephone column 
        TableColumn.CellEditEvent<Client, String> ce;
        ce = (TableColumn.CellEditEvent<Client, String>) e; 
        Client  m = ce.getRowValue();
        m.setTelephone(ce.getNewValue());
    }

    public void colEmail_OnEditCommit(Event e){
        //edit name column 
        TableColumn.CellEditEvent<Client, String> ce;
        ce = (TableColumn.CellEditEvent<Client, String>) e; 
        Client  m = ce.getRowValue();
        m.setEmail(ce.getNewValue());
    }

    public void colExpence_OnEditCommit(Event e){
        //edit Expence column 
        TableColumn.CellEditEvent<Client, Integer> ce;
        ce = (TableColumn.CellEditEvent<Client, Integer>) e; 
        Client  m = ce.getRowValue();
        m.setExpence(ce.getNewValue());
    }

    public void colPayment_OnEditCommit(Event e){
        //edit payment column 
        TableColumn.CellEditEvent<Client, Integer> ce;
        ce = (TableColumn.CellEditEvent<Client, Integer>) e; 
        Client  m = ce.getRowValue();
        m.setPayment(ce.getNewValue());
    }

    public void colProfit_OnEditCommit(Event e){
        //edit profit column 
        TableColumn.CellEditEvent<Client, Integer> ce;
        ce = (TableColumn.CellEditEvent<Client, Integer>) e; 
        Client  m = ce.getRowValue();
        m.setProfit(ce.getNewValue());
    }

    public void colThreshold_OnEditCommit(Event e){
        //edit threshold co validate lumn 
        TableColumn.CellEditEvent<Client, Integer> ce;
        ce = (TableColumn.CellEditEvent<Client, Integer>) e; 
        Client  m = ce.getRowValue();
        m.setThreshold(ce.getNewValue());
    }

    public void buttonCalcThersh_Clicked(){
        //calculates weather threshold has been surpased and send email
        ObservableList<Client>sel, Objects;
        Objects = table.getItems();
        sel=table.getSelectionModel().getSelectedItems();
        for (int i=0; i<Objects.size(); i++){  

            String ID = Objects.get(i).getID();
            int Threshold = Objects.get(i).getThreshold();
            String Email = Objects.get(i).getEmail();
            ArrayList<Treatment>Tdata = new ArrayList<Treatment>();
            try{
                Stream<String>d_cl = Files.lines(Paths.get("Treatment.txt"));
                d_cl.forEach((String input)->
                    { String[]StringData=input.split(",");
                        Tdata.add
                        (new Treatment ( StringData[0], StringData[1], StringData[2], StringData[3], StringData[4], StringData[5], StringData[6], Integer.parseInt(StringData[7]),new Date(StringData[8]))); 
                    });
            }
            catch(IOException error)
            {Alert alert = new Alert(AlertType.INFORMATION);
                alert.setContentText("File not found OR problem opening the file");
                alert.showAndWait();
            }
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
            String material0 = "", material1= "", material2= "", material3= "", material4= "";
            int Bill =0;
            int count =0;
            for (int  k=0; k<Tdata.size(); k++){
                if(Tdata.get(i).getPatientID().equals(ID)){
                    material0 = Tdata.get(k).getMaterial0();
                    material1 = Tdata.get(k).getMaterial1();
                    material2 = Tdata.get(k).getMaterial2();
                    material3 = Tdata.get(k).getMaterial3();
                    material4 = Tdata.get(k).getMaterial4();
                    count = count +1;
                }
                for (int c=0; c<Mdata.size(); c++){
                    if(material0.equals(Mdata.get(c).getName())){
                        Bill = Bill + Mdata.get(c).getPrice();

                    }}
                for (int c=0; c<Mdata.size(); c++){
                    if(material1.equals(Mdata.get(c).getName())){
                        Bill = Bill + Mdata.get(c).getPrice();

                    }}
                for (int c=0; c<Mdata.size(); c++){
                    if(material2.equals(Mdata.get(c).getName())){
                        Bill = Bill + Mdata.get(c).getPrice();

                    }}
                for (int c=0; c<Mdata.size(); c++){
                    if(material3.equals(Mdata.get(i).getName())){
                        Bill = Bill + Mdata.get(i).getPrice();

                    }}
                for (int c=0; c<Mdata.size(); c++){
                    if(material4.equals(Mdata.get(c).getName())){
                        Bill = Bill + Mdata.get(c).getPrice();

                    }}

            }
            Bill = Bill + count *100;
            System.out.println(Threshold - Bill);
            if (Bill >= Threshold){
            EmailClient ec =  new EmailClient();
            ec.sent(Bill, Email); 
            
            
            
            }

        }}

    public void buttonAdd_Clicked(){
        //adds all properties to table
        Client m = new Client();
        m.setName(txtName.getText());
        m.setSurname(txtSurname.getText());
        m.setID(txtID.getText());
        m.setTelephone(txtTelephone.getText());
        m.setEmail(txtEmail.getText());
        m.setExpence(Integer.parseInt(txtExpence.getText()));
        int Ex = Integer.parseInt(txtExpence.getText());
        m.setPayment(Integer.parseInt(txtPayment.getText()));
        int Pay = Integer.parseInt(txtPayment.getText());
        m.setProfit(Integer.parseInt(txtProfit.getText()));
        int Prof = Integer.parseInt(txtProfit.getText());
        m.setThreshold(Integer.parseInt(txtThreshold.getText()));
        int Thre = Integer.parseInt(txtThreshold.getText());
        table.getItems().add(m);
        String path = "D:\\CS\\IA\\Pictures" + "\\" +  txtName.getText()+txtID.getText() ;  

        //Instantiate the File class   
        File f1 = new File(path);  
        //Creating a folder using mkdir() method  
        boolean bool = f1.mkdir();  

        if(bool){  
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("NEW FOLDER");
            alert.setHeaderText("Message!");
            alert.setContentText("Folder Created Successfully!!!");
            alert.showAndWait();
        }else{  
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("NEW FOLDER ERROR!!");
            alert.setHeaderText("Message!");
            alert.setContentText("Error encountered while creating the folder!!!");
            alert.showAndWait();  
        }  

        txtName.clear();
        txtSurname.clear();
        txtID.clear();
        txtTelephone.clear();
        txtEmail.clear();
        txtExpence.clear();
        txtPayment.clear();
        txtProfit.clear();
        txtThreshold.clear();

        if (Ex>Pay){
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("LOSS!!");
            alert.setHeaderText("Message!");
            alert.setContentText("Expence is larger than payment. You are running at a loss!!!");
            alert.showAndWait();
        }     
        if (Ex<Pay){
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("PROFIT!!!");
            alert.setHeaderText("Message!");
            alert.setContentText("Payment larger than expence. You are running at a profit!!!");
            alert.showAndWait();        
        }
        if (Ex == Pay){
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("EVEN");
            alert.setHeaderText("Message!");
            alert.setContentText("Expence is equal to payment.");
            alert.showAndWait();
        }
        if (Ex == Thre){
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("THRESHOLD REACHED!!!!");
            alert.setHeaderText("Message!");
            alert.setContentText("You have reached the threshold agreed upon!!!");
            alert.showAndWait();
        }
        int dif = Ex - Thre;
        if (dif>0){
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("THRESHOLD SURPASED");
            alert.setHeaderText("MESSAGE!");
            alert.setContentText("You have surpased the threshold by " + dif);
            alert.showAndWait();
        }
        if(dif<0){
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("THRESHOLD NOT REACHED");
            alert.setHeaderText("Message!");
            alert.setContentText("You have reached " + (double)(Ex* 100.0/Thre) + "% of the Threshold!!!");
            alert.showAndWait();
        }
    }

    public void buttonDelete_Clicked(){
        //deletes properties from table
        ObservableList<Client>sel, items;
        items = table.getItems();
        sel = table.getSelectionModel().getSelectedItems();

        for(Client m : sel)
            items.remove(m);
    }

    private void Save(){
        //Saves properties in txt file 
        ObservableList<Client>sel,properties;
        properties = table.getItems();
        try (BufferedWriter C = new BufferedWriter (new FileWriter("Clients.txt")))
        {Client c;
            int m = 0;
            for(Object CL:properties){
                c = properties.get(m);
                C.write(c.getName() + "," +c.getSurname() + "," + c.getID() + "," + c.getTelephone() + "," + c.getEmail() + "," +c.getExpence() + "," + c.getProfit() + "," +c.getPayment() + "," + c.getThreshold());
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

    public boolean validate(String s){
        // validates weather save txt file exists
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

}

