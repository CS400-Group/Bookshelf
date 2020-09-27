import java.util.Scanner;

public class FrontEnd {
    public static Scanner userIn = new Scanner(System.in);
    static BookShelf userBookShelf = new BookShelf();

    public static void main(String[] args) {
        int inputNum = 0;
        int validInputs[] = new int[] { 1, 2, 3, 4, 5, -1 };
        while (!ValidUserInput(validInputs, inputNum)) {
            System.out.println("Library BookShelf Menu:");
            System.out.println("Please Enter a Menu Option.");
            System.out.println("\t(1): Add Book To Shelf.");
            System.out.println("\t(2): Remove Book From Shelf.");
            System.out.println("\t(3): Replace A Book On Shelf");
            System.out.println("\t(4): Search For Book On Shelf.");
            System.out.println("\t(5): Clear All Books From Shelf.");
            System.out.println("\t(-1): Exit.");
            inputNum = Integer.parseInt(userIn.nextLine());
            if (!ValidUserInput(validInputs, inputNum)) {
                System.out.println("Invalid Request. Please Try Again:\n");
            }
        }
        // switch statement to determine which method to be run based on user input.
        switch (inputNum) {
            case 1:
                addBook();
                break;
            case 2:
                removeBook();
                break;
            case 3:
                replaceBook();
                break;
            case 4:
                searchBook();
                break;
            case 5:
                clearShelf();
                break;
            case -1:
                System.out.println("Thank You For Visiting.");
                break;
        }
        userIn.close();
    }

    /**
     * ValidUserInput is used to determine if the user inputted a valid input in
     *  the main selection menu. 
     * 
     * @param int[] validInputs- the possible valid inputs, int userInput- the user input
     * @return boolean, true if user inputted a valid input
     */
    public static boolean ValidUserInput(int[] validInputs, int userInput) {
        boolean inputIsValid = false;
        for (int num : validInputs) {
            if (num == userInput) {
                inputIsValid = true;
                break;
            }
        }
        return inputIsValid;
    }

    /**
     * addBook will add the book given by the user to the shelf.
     * 
     * @param none
     * @return none
     */
    public static void addBook() {
        System.out.println("You Can Now Add a Book To The Shelf.");
        // Prompt the user to input the information needed to create new book
        System.out.println("\nPlease Enter The TITLE Of The Book You Wish To Add");
        String title = userIn.nextLine();
        System.out.println("\nPlease Enter The AUTHOR Of The Book You Wish To Add");
        String author = userIn.nextLine();
        System.out.println("\nPlease Enter The ISBN Of The Book You Wish To Add");
        Long isbn = userIn.nextLong();
        // Make the book with the given information, then add to the user's bookshelf
        Book bookToAdd = new Book(title, author, isbn);
        userBookShelf.add(bookToAdd);
        System.out.println("'" + bookToAdd + "' Was Added To Your BookShelf.");
    }

    /**
   * removeBook will remove the book requested by the user from the shelf.
   * @param 
   * @return 
   */
    public static void removeBook() {
        System.out.println("You Can Now Remove a Book From The Shelf.");
        // Prompt the user to input the information needed to remove book
        System.out.println("\nPlease Enter The TITLE Of The Book You Wish To Remove");
        String title = userIn.nextLine();
        System.out.println("\nPlease Enter The AUTHOR Of The Book You Wish To Remove");
        String author = userIn.nextLine();
        System.out.println("\nPlease Enter The ISBN Of The Book You Wish To Remove");
        Long isbn = userIn.nextLong();
        // Make the book with the given information, find the Integer key for the book,
        //   then remove from the user's bookshelf
        Book bookToRemove = new Book(title, author, isbn);
        Integer removeBookKey = bookToRemove.generateKey();
        userBookShelf.remove(removeBookKey);
        System.out.println("'" + bookToRemove + "' Was Removed From Your BookShelf.");
    }

    /**
   * replaceBook will replace the requested book on the shelf with the 
   *    given book by the user.
   * @param 
   * @return 
   */
    public static void replaceBook() {
        System.out.println("You Can Now Replace A Book On The Shelf.");
        // Prompt the user to input the requested book to remove from book shelf
        System.out.println("\nPlease Enter The TITLE Of The Book You Wish To Remove");
        String removeTitle = userIn.nextLine();
        System.out.println("\nPlease Enter The AUTHOR Of The Book You Wish To Remove");
        String removeAuthor = userIn.nextLine();
        System.out.println("\nPlease Enter The ISBN Of The Book You Wish To Remove");
        Long removeIsbn = userIn.nextLong();
        Book bookToRemove = new Book(removeTitle, removeAuthor, removeIsbn);
        // Prompt the user to input the requested book to add to book shelf
        System.out.println("\nPlease Enter The TITLE Of The Book You Wish To Add");
        String addTitle = userIn.nextLine();
        System.out.println("\nPlease Enter The AUTHOR Of The Book You Wish To Add");
        String addAuthor = userIn.nextLine();
        System.out.println("\nPlease Enter The ISBN Of The Book You Wish To Add");
        Long addIsbn = userIn.nextLong();
        Book bookToAdd = new Book(addTitle, addAuthor, addIsbn);
        System.out.println("'" + bookToRemove + "' Was Replaced With '" + bookToAdd + "'");
    }

    /**
   * searchBook will perform the search function requested by user.
   * @param 
   * @return 
   */
    public static void searchBook() {
        // Implement once we know what the methods of search will be.
        System.out.println("You Can Now Search For a Book.");
    }

    /**
   * clearShelf will remove all books from the shelf.
   * @param 
   * @return 
   */
    public static void clearShelf() {
        System.out.println("Are You Sure You Wish To Clear The BookShelf?");
        // Prompt the user to confirm the action of clearing the bookshelf.
        System.out.println("Please Type 'Y' (yes) or 'N' (no).");
        char userAnswer = 0;
        while (userAnswer != 'Y' || userAnswer != 'N') {
            userAnswer = userIn.next().charAt(0);
            if (userAnswer == 'Y') {
                userBookShelf.clear();
                System.out.println("Your BookShelf Has Been Cleared.");
            } else if (userAnswer == 'N') {
                System.out.println("Your BookShelf Was Not Cleared.");
            } else {
                System.out.println("Invalid Response. Please Answer 'Y' or 'N'.");
            }
        }
    }
}
