//
// Manage a list of customers; the public methods
// have requirements specifications (detailed below);
// private entities do not since they only help the
// public methods. Specs for the public methods are
// in their own header comments.
//
// For JUnit testing and JaCoCo reporting, you can
// comment out print() and main() if you want, they
// are not used or needed when running under JUnit.
//

public class CustomerList
{

private class Customer
{
   String name;
   int id;
   Customer next;
   public String toString() {
      return name+"("+id+")";
   }
};

private Customer customerList;

// spec: create a new, empty customer list
public CustomerList()
{
   customerList = null;
}

// spec: return the customer formatted as "name(id)"
// that has a name equal to the search name, or null if
// none exist; the smallest id customer with the name
// should be returned, if multiple of that name exist.
public String find(String name)
{
   Customer cust = customerList;
   while (cust != null) {
      if (name.equals(cust.name))
         return cust.toString();
      cust = cust.next;
   }
   return null;
}

// Error: 
// - The program was counting customers inclusively with the lowestID but spec said to count outside inclusive range. 
// cust.id <= lowestID || cust.id > highestID, code below is corrected.
// - The prgram started the count with 1 instead of 0.
// - The program was checking the next customer when it should check current customer up until cust == null.

// spec: count the number of customers with either an ID 
// that is outside of the range (lowestID, highestID), 
// inclusive, or that has a name that begins and ends with
// the oddStart and oddEnd strings.
// Note:  
// (1) for the "CustomerList Testing - Junit & Jacoco" assignment, 
//     please keep this method commented. 
//     You do not need to test this method. 
// (2) For the "CustomerList Testing - MCDC" assignment, 
//     please uncomment this method and follow assignment instructions to test it. 
public int countOutliers(int lowestID, int highestID, String oddStart, String oddEnd)
{
   int count = 0;
   Customer cust = customerList;
   while (cust != null) {
      if (cust.id < lowestID || cust.id > highestID ||
          (cust.name.startsWith(oddStart) && cust.name.endsWith(oddEnd)))
         count++;
      cust = cust.next;
   }
   return count;
}

// spec: add the given customer (name,id) to 
// the customer list; at least one of (name,id) must 
// be unique; if the (name,id) pair is already in 
// the list, do not add it; return true if added,
// false if not added.
public boolean addCustomer(String name, int id)
{
   Customer newCust = new Customer();
   newCust.name = name;
   newCust.id = id;
   if (customerList == null ||
       name.compareTo(customerList.name) < 0) {
      newCust.next = customerList;
      customerList = newCust;
      return true;
   }
   Customer cust = customerList;
   while (cust.next != null && 
          name.compareTo(cust.next.name) > 0) {
      cust = cust.next;
   }
   newCust.next = cust.next;
   cust.next = newCust;
   return true;
}

// Error: Count started with 1 and checked the next customer when it should
//          check current customer up until cust == null.

// spec: countBeginsWith: return the count of the number 
// of customers that begin with the given name prefix.
public int countBeginsWith(String prefix)
{
   int count = 0;
   Customer cust = customerList;
   while (cust != null) {
      if (cust.name.startsWith(prefix))
         count++;
      cust = cust.next;
   }
   return count;
}

// // spec: print the customer list in alphabetic order,
// // one per line, formatted as "Customer: name(id)"; if
// // multiple of the same name exist, they should be
// // printed in ascending id order.
// public void print()
// {
//    Customer cust = customerList;
//    if (cust == null) {
//       System.out.println("No customers!");
//    }
//    while (cust != null) {
//       System.out.println("Customer: " + cust);
//       cust = cust.next;
//    }
// }
//
// // spec: adds command line arguments each as a
// // unique customer name, uses position for ID;
// // then tries to find the last entered customer
// // name, and then counts names beginning with 'A'
// public static void main(String[] args)
// {
//    int id = 1;
//    CustomerList customers = new CustomerList();
//    for (String arg: args) {
//       customers.addCustomer(arg,id++);
//    }
//    customers.print();
//    System.out.println("find: " + args[args.length-1]
//        + " is " + customers.find(args[args.length-1]));
//    System.out.println("Customers beginning with A: " +
//                       customers.countBeginsWith("A"));
// }

} //end class

