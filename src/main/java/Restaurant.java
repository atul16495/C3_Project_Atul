import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String name;
    private String location;
    public LocalTime openingTime;
    public LocalTime closingTime;

    private List<Item> menu = new ArrayList<Item>();


    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public boolean isRestaurantOpen() {


        boolean time1=(getCurrentTime().isAfter(this.openingTime));// time1 is Positive int when currentTime is > opening Time
        boolean time2=(getCurrentTime().isBefore(this.closingTime));// time2 is negative int when currentTime is < Closing Time

        if((time1)&&(time2)){
            return true;
        }
        else
            return false;
    }

    public LocalTime getCurrentTime(){

        return  LocalTime.now();
    }







    //getMenu method will return the list of menu intem added for the restaurant object
    //Ex: when one restaurant "A" is created as object from class,then for each restaurant have menu list of "A" as object parameter
    //when restaurant "A" owner add item in menu it get added to "A" menu list
    // A.getMenu() will return the list of menu of restaurant "A"
    public List<Item> getMenu() {
        return this.menu;
        //DELETE ABOVE RETURN STATEMENT AND WRITE CODE HERE

    }

    private Item findItemByName(String itemName){
        for(Item item: menu) {
            if(item.getName().equals(itemName))
                return item;
        }
        return null;
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name,price);
        menu.add(newItem);
    }
    
    public void removeFromMenu(String itemName) throws itemNotFoundException {

        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
            throw new itemNotFoundException(itemName);

        menu.remove(itemToBeRemoved);
    }
    public void displayDetails(){
        System.out.println("Restaurant:"+ name + "\n"
                +"Location:"+ location + "\n"
                +"Opening time:"+ openingTime +"\n"
                +"Closing time:"+ closingTime +"\n"
                +"Menu:"+"\n"+getMenu());

    }

    public String getName() {
        return name;
    }

    public int orderPrice(List<String> ListItemNames){
        int Cost=0;
        for(String item:ListItemNames) {
            Cost = Cost+findItemByName(item).getPrice(item);


        }
        return Cost;

    }

}
