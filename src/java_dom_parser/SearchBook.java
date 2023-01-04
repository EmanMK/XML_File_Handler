package java_dom_parser;

import java.util.List;
import java.util.Scanner;

public class SearchBook {
	static List<Book> bookList;
	public void setBookList(List<Book> bookList) {
		this.bookList= bookList;
	}
	
	
//	<-----------------Search A Book by Author----------------------->
    public static void searchByID(String id) {
    	int books=0;
        boolean exist = false;
        for (Book book : bookList) {
            if (book.getID().contains(id)) {
                System.out.println(book.toString());
                exist = true;
                books+=1;
            }
        }
        if (!exist) {
            System.out.println("no books are found!");
        }else {
      	  System.out.println("Number of founed Books: " + books);
        }

    }
    
    
//	<-----------------Search A Book by Author----------------------->
    public static void searchByAuthor(String author) {
    	int books=0;
        boolean exist = false;
        for (Book book : bookList) {
            if (book.getAuthor().contains(author)) {
                System.out.println(book.toString());
                exist = true;
                books+=1;
            }
        }
        if (!exist) {
            System.out.println("Author \"" + author + "\"doesn't have any books");
        }else {
      	  System.out.println("Number of found Books: " + books);
        }

    }
    
//	<-----------------Search A Book by Title----------------------->
    public static void searchByTitle(String title) {
    	int books=0;
        boolean found = false;
        for (Book book : bookList) {
            if (book.getTitle().contains(title)) {
                System.out.println(book.toString());
                found = true;
                books+=1;
            }
        }
        if (!found) {
            System.out.println("There is no such book");
        }else {
      	  System.out.println("Number of found Books: " + books);
        }
    }
    
//    <-----------------Search A Book by publishDate----------------------->
    public static void searchByPublishDate(String publishDate) {
    	int books=0;
        boolean found = false;
        for (Book book : bookList) {
            if (book.getPublishDate().contains(publishDate)) {
                System.out.println(book.toString());
                found = true;
                books+=1;
            }
        }
        if (!found) {
            System.out.println("There is no such book");
        }else {
      	  System.out.println("Number of found Books: " + books);
        }
    }
    
//  <-----------------Search A Book by Genre ----------------------->
  public static void searchByGenre(String genre) {
	  int books =0;
      boolean found = false;
      for (Book book : bookList) {
          if (book.getGenre().contains(genre)) {
              System.out.println(book.toString());
              found = true;
              books+=1;
          }
      }
      if (!found) {
          System.out.println("There is no such book");
      }else {
    	  System.out.println("Number of found Books: " + books);
      }
  }
  
  public static void searchByPrice(String price) {
	  int books=0;
      boolean found = false;
      for (Book book : bookList) {
          if (book.getPrice().contains(price)) {
              System.out.println(book.toString());
              found = true;
              books+=1;
          }
      }
      if (!found) {
          System.out.println("There is no such book");
      }else {
    	  System.out.println("Number of found Books: " + books);
      }
  }
  
  public static void searchByDescription(String description) {
	  int books=0;
      boolean found = false;
      for (Book book : bookList) {
          if (book.getDescription().contains(description)) {
              System.out.println(book.toString());
              found = true;
              books+=1;
          }
      }
      if (!found) {
          System.out.println("There is no such book");
      }else {
    	  System.out.println("Number of found Books: " + books);
      }
  }
  
  
  public static void searchBook() {
	  System.out.println("You want to search book by : \n");
	  System.out.println("1-ID \n2-Author \n3-Title \n4-Genre \n5-Price \n6-Publish Date \n7-description");

	  Scanner input = new Scanner(System.in);
	  int choice = input.nextInt();
	  
	  String search;
	  
	  switch(choice) {
		  case 1:
			  System.out.println("Enter book ID:");
              search = input.nextLine();
              search = input.nextLine();
              searchByID(search);
			  break;
		  case 2: {
			  System.out.println("Enter book author:");
              search = input.nextLine();
              search = input.nextLine();
              searchByAuthor(search);
              break;
          }
          case 3:
          {
              System.out.println("Enter book title:");
              search = input.nextLine();
              search = input.nextLine();
              searchByTitle(search);
              break;
          }
          case 4:
          {
              System.out.println("Enter book Genre:");
              search = input.nextLine();
              search = input.nextLine();
              searchByGenre(search) ;
              break;
          }
          case 5:
          {
          	System.out.println("Enter book Price:");
              search = input.nextLine();
              search = input.nextLine();
              searchByPrice(search);
              break;
          }
          case 6:
          {
              System.out.println("Enter book Publish Date:");
              search = input.nextLine();
              search = input.nextLine();
              searchByPublishDate(search);
              break;
          }
          case 7:
          {
              System.out.println("Enter book description:");
              search = input.nextLine();
              search = input.nextLine();
              searchByDescription(search) ;
              break;
          }
          default:
          {
              System.out.println("Invalid Input");
              searchBook();
              break;
          }
	  }
  }

}
