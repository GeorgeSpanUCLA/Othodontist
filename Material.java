
/**
 *this class will instate an object material
 */
public class Material
//instance variables 
{private String Name;
    private String ID;
    private int Price;
    // no argument constructor 
    public Material(){
    }
    // constructor with parameters
    public Material(String Name, String ID, int Price){
        this.Name = Name;
        this.ID = ID;
        this.Price = Price;}

    //accessor
    public String getName(){
        return Name;}

    public String getID(){
        return ID;}

    public int getPrice(){
        return Price;}

    // mutator
    public void setName(String Name){
        this.Name = Name;}

    public void setID(String ID){
        this.ID = ID; }

    public void setPrice(int Price){
        this.Price = Price;}

}
