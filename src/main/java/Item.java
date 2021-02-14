public class Item {
    private String name;
    private int price;

    public Item(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    // added for TDD
    public int getPrice(String name){return price;}

    @Override
    public String toString(){
        return  name + ":"
                + price
                + "\n"
                ;
    }
}
