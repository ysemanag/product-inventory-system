
/**
* Class representing an individual product of the inventory
* @author Yves Semana Gisubizo
* @version 1.0
*/
public class Product implements Comparable{

    private String name;    //instance variable name of the  product
    private int number;     //instance variable number fo the product
    private String category; //instance variable category of the product
    private boolean available; //instance variable to check availability of the product

  /**
  * Default constructor
  */
  public Product (){}

  /**
  * Parameterized constructor
  * @param name assigned to the product
  * @param number assigned to the product
  * @param category assigned to the product
  */
  public Product(int number, String name, String category, boolean available){
    this.number = number;
    this.name = name;
    this.category = category;
    this.available = available;
  }

  /**
   * Compares numbers of two Product objects
   * @param o Object type arguement to be casted to product type
   * @returns a negative number if this product number is less than arguement's and otherwise a positive number
   */
  @Override
  public int compareTo(Object o){
    Product p = (Product) o;  //narrowing cast conversion
    int newNumber = p.getNumber();
    if (this.number == newNumber)//comparing this product's number with the number of the passed object narrowedly casted to Product
       return 0;
    else if (this.number < newNumber)
       return -1;
    return 1;
  }

  /**
  * Checks the status availability
  * @return true if product is available and false otherwise
  * Time Complexity: O(1)
  */
  public boolean isAvailable(){return available;}

  /**
  * Getter method to get the product's name
  * @return name of the product
  * Time Complexity: O(1)
  */
  public String getName(){return name;}

  /**
  * Getter method to get the product's number
  * @return number of the product
  * Time Complexity: O(1)
  */
  public int getNumber(){return number;}

  /**
  * Getter method to get the product's category
  * @return category of the product
  * Time Complexity: O(1)
  */
  public String getCategory(){return category;}

  /**
  * Setter method to set the product's name
  * @param name to be set for the product
  * Time Complexity: O(1)
  */
  public void setName(String name){this.name = name;}

  /**
  * Setter method to set the product's number
  * @param number to be set for the product
  * Time Complexity: O(1)
  */
  public void setNumber(int number){this.number = number;}

  /**
  * Setter method to set the product's category
  * @param category to be set for the product
  * Time Complexity: O(1)
  */
  public void setCategory(String category){this.category = category;}

  /**
   * Displays the string consiting of the product's name, number, category and availability
   * @return string
   * Time Complexity: O(1)
   */
  @Override
  public String toString(){
    return "Product number: " + number + "\nProduct name: " + name + "\nCategory: " + category + "\nAvailable: " + available +"\n";
  }

}
