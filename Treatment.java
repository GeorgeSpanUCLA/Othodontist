import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
/**
 * This class will be used to create objects of type treatment 
 */
public class Treatment{
    //instance variables
    private String PatientID;
    private String Diagnosis; // validate to allow only acceptable diagnosis
    private String Material0;
    private String Material1;
    private String Material2;
    private String Material3;
    private String Material4;
    private int Compensation;
    private SimpleObjectProperty<Date> date;
    // no argument constructor
    public Treatment(){
        this.PatientID ="";
        this.Diagnosis = "";
        this.Material0 = "";
        this.Material1 = "";
        this.Material2  = "";
        this.Material3 = "";
        this.Material4 = "";
        this.Compensation = 0;
        this.date = null;
    }
    //constructor with parameters
    public Treatment(String PatientID, String Diagnosis, String Material0, String Material1, String Material2, String Material3, String Material4, int Compensation, Date date){
        this.PatientID = PatientID;
        this.Diagnosis = Diagnosis;
        this.Material0 = Material0;
        this.Material1 = Material1;
        this.Material2 = Material2;
        this.Material3 = Material3;
        this.Material4 = Material4;
        this.Compensation = Compensation;
        this.date = new SimpleObjectProperty(date);
    }

    // accessor
    public String getPatientID(){
        return PatientID;}

    public String getDiagnosis(){
        return Diagnosis;}

    public String getMaterial0(){
        return Material0;}

    public String getMaterial1(){
        return Material1;}

    public String getMaterial2(){
        return Material2;}

    public String getMaterial3(){
        return Material3;}

    public String getMaterial4(){
        return Material4;}

    public int getCompensation(){
        return Compensation;}

    public Date getDate() {
        return (Date) date.get();
    } 

    public String getDateAsString() {
        SimpleDateFormat smp = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = (null == date || null == date.get())
            ? "" : smp.format(date.get());

        return strDate;
    }

    // mutator
    public void setPatientID(String patientID){
        this.PatientID = patientID;}

    public void setDiagnosis(String diagnosis){
        this.Diagnosis = diagnosis;}

    public void setMaterial0(String Material0){
        this.Material0 = Material0;}

    public void setMaterial1(String Material1){
        this.Material1 = Material1;}

    public void setMaterial2(String Material2){
        this.Material2 = Material2;}

    public void setMaterial3(String Material3){
        this.Material3 = Material3;}

    public void setMaterial4(String Material4){
        this.Material4 = Material4;}

    public void setDate(Date date ) {
        this.date = new SimpleObjectProperty(date);
    }   

    public void setCompensation(int compensation){
        this.Compensation = compensation;}
}

