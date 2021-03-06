import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class FrontEnd {
    // Variables needed throughout the program
    public static Scanner userIn = new Scanner(System.in);
    static BookShelf userBookShelf = new BookShelf();
    public static int inputNum = 0;
    public static int validInputs[] = new int[] { 1, 2, 3, 4, 5, 6, -1 };

    public static void main(String[] args) {
        // Run method to display menu to the user and also
        // Ensure the user is done with requested functions.

        while (inputNum != -1) {
            bookShelfMenu();
            if (inputNum == -1)
                break;
            System.out.println("\nType '7' If You Wish To Perform Another Action");
            System.out.println("Type '8' If You Wish To Quit");
            inputNum = Integer.parseInt(userIn.nextLine());
            if (inputNum == 8) {
                System.out.println("Thank You For Visting. Have A Nice Day.");
                break;
            } else if (inputNum == 7) {
                System.out.println("Continuing With New Command.\n");
            } else if (!ValidUserInput(validInputs, inputNum)) {
                System.out.println("Invalid Input. Now Exiting Program.");
                break;
            }
        }
        System.out.println(userBookShelf.size());
;       userIn.close();
    } // End of main


    /**
     * bookShelfMenu runs the menu for the book shelf for the user to select a function from.
     * 
     * @param none
     * @return none
     */
    public static void bookShelfMenu() {
        while (!ValidUserInput(validInputs, inputNum)) {
            System.out.println("Library BookShelf Menu:");
            System.out.println("Please Enter a Menu Option.");
            System.out.println("\t(1): Add Book To Shelf.");
            System.out.println("\t(2): Remove Book From Shelf.");
            System.out.println("\t(3): Replace A Book On Shelf");
            System.out.println("\t(4): Search For Book On Shelf.");
            System.out.println("\t(5): Clear All Books From Shelf.");
            System.out.println("\t(6): Add Multiple Books From .txt File");
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
            case 6:
                readFile();
                break;
            case -1:
                System.out.println("Thank You For Visiting.");
                break;

        }
    } // End of bookShelfMenu

     /**
      * readFile will add a file of books from the user and read it.
      * 
      * @param
      * @return
      */
    public static void readFile() {
        System.out.println("\nPlease Ensure That Your File Displays Books In This Form:");
        System.out.println("\t'Title,Author,ISBN' With One Book Per Line, No Spaces.");
        System.out.println("The File Will Not Be Read Correctly Otherwise.");
        System.out.println("What Is The Exact Name Of The File You Wish To Add?");
        File bookFile = new File(userIn.nextLine());
        try {
            Scanner fileReader = new Scanner(bookFile);
            while (fileReader.hasNextLine()) {
                String[] str = fileReader.nextLine().split(",");
                Book bookToAdd = new Book(str[0].trim(), str[1].trim(), Long.parseLong(str[2].trim()));
                userBookShelf.add(bookToAdd);
            }
            fileReader.close();
        } 
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Your File Was Succesfully Uploaded.");
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
        Long isbn = Long.parseLong(userIn.nextLine());
        // Make the book with the given information, then add to the user's bookshelf
        Book bookToAdd = new Book(title, author, isbn);
        Integer bookKey = bookToAdd.generateKey();
        userBookShelf.add(bookToAdd);
        System.out.println("'" + title + "' Was Added To Your BookShelf.");
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
        // Remove the book using the hashcode from the title.
        Integer removeBookKey = Integer.valueOf(title.hashCode());
        userBookShelf.remove(removeBookKey);
        System.out.println("'" + title + "' Was Removed From Your BookShelf.");
    }

    /**
   * replaceBook will replace the requested book on the shelf with the 
   *    given book by the user.
   * @param 
   * @return 
   */
    public static void replaceBook() {
        System.out.println("You Can Now Replace A Book On The Shelf.");
        // Prompt the user to input the requested book title to remove
        System.out.println("\nPlease Enter The TITLE Of The Book You Wish To Remove");
        String removeTitle = userIn.nextLine();
        // Prompt the user to input the requested book to add to book shelf
        System.out.println("\nPlease Enter The TITLE Of The Book You Wish To Add");
        String addTitle = userIn.nextLine();
        System.out.println("\nPlease Enter The AUTHOR Of The Book You Wish To Add");
        String addAuthor = userIn.nextLine();
        System.out.println("\nPlease Enter The ISBN Of The Book You Wish To Add");
        Long addIsbn = Long.parseLong(userIn.nextLine());
        Book bookToAdd = new Book(addTitle, addAuthor, addIsbn);
        userBookShelf.replace(removeTitle, bookToAdd);
        System.out.println("'" + removeTitle + "' Was Replaced With '" + addTitle + "'");
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
        System.out.println("Your BookShelf Contains:" + userBookShelf);
        // Prompt the user to confirm the action of clearing the bookshelf.
        System.out.println("Please Type 'Y' (yes) or 'N' (no).");
        char userAnswer = 0;
        while (userAnswer != 'Y' || userAnswer != 'N') {
            userAnswer = userIn.nextLine().charAt(0);
            if (userAnswer == 'Y') {
                userBookShelf.clear();
                System.out.println("Your BookShelf Has Been Cleared.");
                System.out.println("Your BookShelf Contains:" + userBookShelf);
                break;
            } else if (userAnswer == 'N') {
                System.out.println("Your BookShelf Was Not Cleared.");
                break;
            } else {
                System.out.println("Invalid Response. Please Answer 'Y' or 'N'.");
            }
        }
    }
}
