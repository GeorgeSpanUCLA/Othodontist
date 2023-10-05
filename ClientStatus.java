

/**
 * Write a description of class ClientStatus here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ClientStatus
//instance variables 
{ private String ID; 
    private String Finished; 
    private String Rating; 
    private String FDate;
    
    //no argument constructor 
    public ClientStatus(){
    }
    //constructor with parameters 
    public ClientStatus(String ID, String Finished, String Rating, String FDate){
        this.ID = ID; 
        this.Finished = Finished; 
        this.Rating =  Rating;
        this.FDate = FDate;

    }
    //accessor methods
    public String getID(){
        return  ID;}

        public String getFinished(){
        return Finished;}

    public String getRating(){
        return Rating;}

    public String getFDate(){
        return FDate;}
    //mutator methods
    public void setID(String ID){
        this.ID = ID;} 

    public void setFinished(String Finished){
        this.Finished = Finished;}

    public void setRating (String Rating){
        this.Rating = Rating;}

    public void setFDate( String FDate){
        this.FDate = FDate;}

    
}