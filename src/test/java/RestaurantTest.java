import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

/***
 * All Testcase are tested using Java version 11.0.9
 * beforeEach executed before each test case
 * Thank you
 *  */

class RestaurantTest {
    Restaurant restaurant;
   @BeforeEach
    public void setup(){
        LocalTime openingTime = LocalTime.parse("09:00:00");
        LocalTime closingTime = LocalTime.parse("11:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
       restaurant.addToMenu("Sweet corn soup",119);
       restaurant.addToMenu("Vegetable lasagne", 269);
        //System.out.println("At Before All");

    }


    //REFACTOR ALL THE REPEATED LINES OF CODE

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE

    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){

        Restaurant restaurant1=Mockito.spy(restaurant);

        Mockito.when(restaurant1.getCurrentTime()).thenReturn(LocalTime.parse("10:00:00"));

        assertEquals(true,restaurant1.isRestaurantOpen());
}

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){

        Restaurant restaurant1=Mockito.spy(restaurant);

        Mockito.when(restaurant1.getCurrentTime()).thenReturn(LocalTime.parse("12:00:00"));

        assertEquals(false,restaurant1.isRestaurantOpen());
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {

        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    @Test
    public void should_return_Correct_order_Total_Amount_for_selected_items() {
        List<String> listItemName = new ArrayList<>();
        listItemName.add("Sweet corn soup");
        listItemName.add("Vegetable lasagne");
        System.out.println(listItemName);
        System.out.println(restaurant.orderPrice(listItemName));
        // expected order cost is 388 [119(Sweet corn soup)+269(Vegetable lasagne)]
        assertEquals(388,restaurant.orderPrice(listItemName));

    }

    @Test
    public  void should_display_correct_details_of_added_restaurant(){
        //As displayDetails method is returning void and printing System output therefore to test this method we need to use output Strem
        //Saving System.out to Stream
        PrintStream output =System.out;
        //Created new instance of ByteArrayOutStream
        ByteArrayOutputStream bout =new ByteArrayOutputStream();
        //change system.out to point out to our stream
        System.setOut(new PrintStream(bout));
        //Print by calling void method
        restaurant.displayDetails();
        //Reset the system.out
        System.setOut(output);

        //Our bout has now content from the print statement
        String Display =new String(bout.toByteArray());
        //add some assertion out output
        assertTrue(Display.contains("Restaurant:Amelie's"));
        assertTrue(Display.contains("Location:Chennai"));
        assertTrue(Display.contains("Opening time:09:00"));
        assertTrue(Display.contains("Closing time:11:00"));
        assertTrue(Display.contains("Sweet corn soup:119"));
        assertTrue(Display.contains("Vegetable lasagne:269"));

   }
}