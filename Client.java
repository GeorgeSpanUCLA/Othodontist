
/**
 *This is the class that we will use to create objects of type client 
 */
public class Client
{//instance variables
    private String Name;
    private String Surname;
    private String ID;
    private String Telephone;
    private String Email;
    private Treatment[] Treatments;
    private int Expence;
    private int Payment;
    private int Profit;
    private int Threshold;
    //no argument constructor
    public Client(){
    }
    //constructor with parameters
    public Client(String Name, String Surname, String ID, String Telephone,String Email,int Expence, int Payment, int Profit, int Threshold){
        this.Name = Name;
        this.Surname = Surname;
        this.ID = ID;
        this.Telephone = Telephone;
        this.Email = Email;
        this.Expence = Expence;
        this.Payment = Payment;
        this.Profit = Profit;
        this.Threshold = Threshold; 
    }
    // constructor with parameters
    public Client(String name, String surname, String ID, String Telephone,String Email, int Threshold, Treatment[] treatments, int expence, int payment, int profit){
        this.Name = name;
        this.Surname = surname;
        this.ID = ID;
        this.Telephone = Telephone;
        this.Email = Email;
        this.Threshold = Threshold;     
        this.Expence = expence;
        this.Profit = profit;
        this.Treatments = treatments; 
    }
    //Accesor methods
    public String getName(){
        return  Name;}

    public String getSurname(){
        return Surname;}

    public String getID(){
        return  ID;}

    public String getTelephone(){
        return Telephone;}

    public String getEmail(){
        return Email;}

    public int getThreshold(){
        return Threshold;}

    public int getExpence(){
        return Expence;}

    public int getPayment(){
        return Payment;}

    public int getProfit(){
        return Profit;}

    public Treatment[] getTreatments(){
        return Treatments;}

    // mutator methods 
    public void setName(String name){
        this.Name = name;}

    public void setSurname(String surname){
        this.Surname = surname;} 

    public void setID(String ID){
        this.ID = ID;} 

    public void setTelephone(String Telephone){
        this.Telephone = Telephone;} 

    public void setEmail(String Email){
        this.Email = Email;} 

    public void setThreshold(int Threshold){
        this.Threshold = Threshold;} 

    public void setExpence(int expence ){
        this.Expence = expence;} 

    public void setPayment(int Payment ){
        this.Payment = Payment;} 

    public void setProfit(int profit ){
        this.Profit = profit;} 

    public void setTreatments(Treatment[] treatments){
        this.Treatments= treatments ;} 

} 