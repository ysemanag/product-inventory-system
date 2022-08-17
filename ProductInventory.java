import java.util.*;

/**
* Class with a collection of individual products using PositionalList ADT
* @author Yves Semana Gisubizo
* @version 1.0
*/

public class ProductInventory extends Product{

  private String name;   //name of the product
  private int number;    //product number
  private String category;  //category of the product
  private boolean available; //availability status of the product
  private String companyName;  //name of the company using this inventory
  private int size = 0;
  PositionalList<Product> productList = new LinkedPositionalList<>(); //Storing the products
  PositionalList<Product> searchedList = new LinkedPositionalList<>(); //Storing the searched products


  /**
   * Default constructor
   */
  public ProductInventory(){}

  /**
   * Parametric constructor
   * @param number the product number
   * @param name the product's name
   * @param category the product's category
   * @param available the product's availability
   */
  public ProductInventory(int number, String name, String category, boolean available){
    super(number, name, category, available);
  }

  /**
   * Checks if the product already exists in the inventory using the product number
   * @param num  the product number to be checked
   * @return true if the product number already exists and false otherwise
   * Time Complexity: O(n)
   */
  public boolean contains(int num){
    Iterator<Product> it = productList.iterator();
    while(it.hasNext()){
      if (it.next().getNumber() == num){
         return true;
       }
    }
    return false;
  }

  /**
   * Inserts the new distinct product number into the positional list
   * @param p the product to be inserted
   * @throws IllegalArgumentException for invalid product numbers
   * Time Complexity: O(n)
   */
   public void insert(Product p) throws IllegalArgumentException{
    if (p.getNumber() < 0)
        throw new IllegalArgumentException("Provide a positive product number.");
    else {
      if (productList.isEmpty()){
        System.out.println("......Inserting the product into the inventory......");
        productList.addLast(p);
        size++;
      }
      else {
       if (contains(p.getNumber())){
         System.out.println( " product number " + p.getNumber() + " already exists. Please provide a distinct number");
       }
       else{
         System.out.println("......Inserting the product into the inventory......");
         //sorting the list as I add the products
         Position<Product> pos = productList.first();
         while(pos != null){    //traversing the list until the last product for sorting
           if (pos.getElement().compareTo(p) > 0){  //checking if new product number is less product numbers in the list
             productList.addBefore(pos, p);   //then if it is, we insert it aposition before
             size++;
             return;
           }
           pos = productList.after(pos);   //changing the position in the list for the while loop
         }
         productList.addLast(p);   //otherwise we add the new product at last because its number is greater than everything
         size++;
       }
      }
    }

   }

   /**
    * Removes the product with a specified product number
    * @param prodNumber the number of the product to be removed
    * @throws IllegalArgumentException for invalid product numbers
    * Time Complexity: O(n)
    */
    public void remove(int prodNumber) throws IllegalArgumentException{
      if (prodNumber < 0)
          throw new IllegalArgumentException("Provide a positive product number.");
      else {
      Position<Product> pos = productList.first();
      if (!contains(prodNumber)){ //checking if this number exists first
        System.out.println("This product number " +prodNumber+ " does not exist in the inventory.");
      }else {
      for (int i=0;i<size;i++){
        if (pos.getElement().getNumber() == prodNumber){  //found
          System.out.println("..... removing product "+ prodNumber+" from the inventory.....");
          productList.remove(pos);
          size--;
          break;
        }
          pos = productList.after(pos);
      }
     }
   }
  }

   /**
    * Displays all the products available and unavailable
    * Time Complexity: O(n)
    */
   public void display(){
    System.out.println(".....Displaying all products.....");
    Iterator<Product> it = productList.iterator();
    while(it.hasNext()){
       System.out.println(it.next());
     }
   }

   /**
    * Displays only the available products
    * Time Complexity: O(n)
    */
   public void displayAvailable(){
    Iterator<Product> it = productList.iterator();
    System.out.println(".....Displaying available products... ..");
    int availableCount = 0; //maintaining the count of available products
    while(it.hasNext()){
      Product temp = it.next();
      if (temp.isAvailable()){
         System.out.println(temp);
         availableCount++;
       }
     }
     System.out.println("The number of available products is "+ availableCount);
     System.out.println("The total number of products is "+ size);
   }

   /**
    * Finds the specified product and displays it
    * @param prodNumber the number of the product to be found
    * @throws IllegalArgumentException for invalid product numbers
    * Time Complexity: O(log n)
    */
   public void find(int prodNumber) throws IllegalArgumentException{
     if (prodNumber < 0)
         throw new IllegalArgumentException("Provide a positive product number.");
     else {
         if (!contains(prodNumber)){
         System.out.println("This product number " +prodNumber+ " can not be found in the inventory.");
       }else {
         System.out.println("....searching for product number " +prodNumber+ "...." );

         //using binary search as the list in sorted
         Iterator<Product> it = productList.iterator();
         Product temp = it.next();
         int low = 0, high = size; //setting low and high boundaries of the search
         while (low <= high) {
            int mid = low + (high - low) / 2;

            // Check if prodNumber equal to the current list product number
            if (temp.getNumber() == prodNumber){
                System.out.println(temp);
                searchedList.addFirst(temp); //adding products in a reversed order as they are searched having recent at the first position always
                break;
            }
            // If prodNumber is greater, update low and temp and ignore the left half
            if (temp.getNumber() < prodNumber){
                low = mid + 1;
                temp = it.next();
              }
            // If prodNumber is smaller, update high and temp and ignore right half
            else{
                high = mid - 1;
                temp = it.next();
              }
        }
         }
     }
   }

   /**
    * Displays the top five recently searched products
    * Time Complexity: O(1)
    */
   public void topSearched(){
     if (searchedList.isEmpty())
     { System.out.println("There are no searched products yet.");}
     else{
     System.out.println("...Displaying top 5 recently searched products...");
     Iterator<Product> it = searchedList.iterator();
     int count = 0; //keeping the count at <=5
     while (count < 5 && it.hasNext()) {
          System.out.println(it.next());
          count++;
        }
    }
   }

   /**
    * Displays the string consiting of the product's name, number, category and availability
    * @return string
    * Time Complexity: O(1)
    */
   @Override
   public String toString(){
     return "Product number: " + number + "\nProduct name: " + name + "\nCategory: " + category + "\nAvailable: " + available +"\n";
   }

   public static void main(String[] args){
/**
     ProductInventory newInventory = new ProductInventory();
     newInventory.insert(new Product(1, "Beans", "Food",true));
     newInventory.insert(new Product(2, "Tomatoes", "Vegetables",false));
     newInventory.insert(new Product(3, "Nails", "Construction",true));
     newInventory.display();
     newInventory.insert(new Product(2, "Amata", "Drinks",false));
     newInventory.remove(2);
     newInventory.display();
     newInventory.insert(new Product(9, "Phone", "Tech",true));
     newInventory.insert(new Product(6, "Books", "School",false));
     newInventory.insert(new Product(4, "Wine", "Drinks",true));
     newInventory.insert(new Product(10, "Jeans", "Clothings",false));
     newInventory.display();
     newInventory.insert(new Product(4, "Earphones", "Electronics",true));
     newInventory.insert(new Product(11, "Pens", "School",false));
     newInventory.remove(6);
     newInventory.insert(new Product(12, "Speaker", "Electronics",true));
     newInventory.insert(new Product(8, "Omolette", "Food",true));
     newInventory.insert(new Product(5, "Strawberry", "Fruits",true));
     newInventory.insert(new Product(7, "Stella", "Beer",false));
     newInventory.display();
     newInventory.displayAvailable();
     newInventory.topSearched();
     newInventory.find(15);
     newInventory.find(1);
     newInventory.find(12);
     newInventory.find(4);
     newInventory.find(9);
     newInventory.find(7);
     newInventory.find(3);
     newInventory.find(12);
     newInventory.topSearched();
     newInventory.find(15);
     newInventory.find(7);
     newInventory.topSearched();
    // newInventory.remove(-3);
     newInventory.insert(new Product(-20, "Strawberry", "Fruits",true));
*/
  //Extra credit
  int choice = 0;
  ProductInventory newInventory = new ProductInventory();
   do{
     try{
     Scanner sc = new Scanner(System.in);
     System.out.println("Choose option number of the operation you would like to perform? :\n1.Insert a product\n2.Remove a product\n3.Display all products"+
     "\n4.Display only available products\n5.Find a product\n6.Display top 5 recent searched products\n7.Quit the program");
     System.out.println("Enter your choice:");
     choice = sc.nextInt();

     switch(choice) {
       case 1:
             System.out.println("Enter a positive number  of the product:");
             int newNumber = sc.nextInt();
             System.out.println("Enter the name of the product:");
             String newName = sc.next();
             System.out.println("Enter the category of the product");
             String newCategory = sc.next();
             System.out.println("Is this product available? (true/false)");
             Boolean available = sc.nextBoolean();
             Product newProduct = new Product(newNumber, newName, newCategory, available);
             System.out.print("\033[H\033[2J");
             newInventory.insert(newProduct);
             break;
       case 2:
             System.out.println("Enter the number of the product to be removed.");
             int removeProd = sc.nextInt();
             System.out.print("\033[H\033[2J");
             newInventory.remove(removeProd);
             break;
       case 3:
             System.out.print("\033[H\033[2J");
             newInventory.display();
             break;
       case 4:
             System.out.print("\033[H\033[2J");   //clearing the creen
             newInventory.displayAvailable();
             break;
       case 5:
             System.out.println("Enter the number of the product to search for.");
             int searchProd = sc.nextInt();
             System.out.print("\033[H\033[2J");   //clearing the creen
             newInventory.find(searchProd);
             break;
       case 6:
             newInventory.topSearched();
             break;
       case 7:
             System.out.print("\033[H\033[2J");   //clearing the creen
             System.out.println("....Quiting the program. BYE!.......");
            // System.out.flush();
             System.exit(0);
       default:
             System.out.println("Enter a valid choice.");
     }
   }catch(InputMismatchException exception){
     System.out.println("Please enter only numbers for your option.");
   }
   }while (choice != 7);

   }

}
