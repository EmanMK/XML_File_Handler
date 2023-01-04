package java_dom_parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Validator {

	static List<Book> bookList;
	public void setBookList(List<Book> bookList) {
		this.bookList= bookList;
	}

	public static String validateID() {
		System.out.println("Enter ID: ");
		Scanner input = new Scanner(System.in);
		String id = input.nextLine();
		
		boolean failed = true;
		while (failed) {
			failed = false;
			for (Book book : bookList) {
	            if (book.getID().contains(id)) {
	            	System.out.println("ID already exist. \n please enter another one..");
	            	id = input.nextLine();
	            	failed = true;
	            	break;
	            }
	        }
		}
		return id;
	}
	
	
	public static String validateTitle() {
		System.out.println("Enter Book Title: ");
		Scanner input = new Scanner(System.in);
        String title = input.nextLine();
		
		boolean failed = true;
		while (failed) {
			failed = false;
            if (title.isBlank()) {
            	System.out.println("Title is null. \n please enter another one..");
            	title = input.nextLine();
            	failed = true;
            }
		} 
		return title;
	}
	
	
	public static String validateAuthor() {
		System.out.println("Enter Author Name: ");
		Scanner input = new Scanner(System.in);
		String author = input.nextLine();
		
		boolean failed = true;
		while (failed) {
			failed = false;
			if (author.isBlank() ) {
				System.out.println("author is null. \n please enter another one..");
				author = input.nextLine();
				failed = true;
				continue;
			}
			if(!author.matches("[a-zA-Z][a-zA-Z ]*")) {
				System.out.println("Author name should only consist of a-z or A-Z \n please enter another one..");
				author = input.nextLine();
				failed = true;
			}
		} 
		return author;
	}
	
	public static enum Genre{
			Science,
			Fiction,
			Drama
	}
	
	public static String chooseGenre() {
		 boolean failed=true;
		 String genre=" ";

         int choise;
         Scanner input = new Scanner(System.in);
         while(failed) {
        	 failed=false;
       
	         System.out.println("choose Genre type:");
	         System.out.println("1-Science \n"
				                 + "2-Fiction \n"
				                 + "3-Drama \n");
	         
	         choise = input.nextInt();
	         switch (choise) {
	             case 1: {
	                 genre="Science";
	                 break;
	             }
	             case 2: {
	                 genre="Fiction";
	                 break;
	             }
	             case 3: {
	                 genre="Drama";
	                 break;
	             }
	             default:{
	            	 System.out.println("Invalid Input \n Input should be 1, 2, or 3. \n");
	            	 failed=true;
	             }
	         }
         }
             
		return genre;
	}
	
	public static boolean inRange( int value, int minimum, int maximum)
	{
	    return value >= minimum && value <= maximum;
	}
	
	public static String validateDate() {
		System.out.println("Enter Date in formate \"YYYY-MM-DD\"");
		
		Scanner scanner = new Scanner(System.in);
		String date = scanner.next();
		if(date.isBlank()) {
			System.out.println("Date is empty");
			return validateDate();
		}

		ParseException parseException = new ParseException("invalide format",0);
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date2=null;
		try {
		    date2 = simpleDateFormat.parse(date);
		    if ( 	!inRange(date2.getYear()+1900,1200,2023) 
		    		|| !inRange(date2.getMonth(),1,12)
		    		|| !inRange(date2.getDate(),1,31)) {
				throw parseException;
		    }
		    
		} catch (ParseException e) {
		    System.out.println("Formate Is wrong..");
		    date= validateDate();
		}
		return date;
	}
	
	public static double validatePrice() {
		System.out.println("Enter Price: ");
		Scanner input = new Scanner(System.in);
		try {
			double price = input.nextDouble();
			if(price >= 0)
				return price;
			else {
				System.out.println("Please enter a positive number");				
				return validatePrice();
			}
		}catch (java.util.InputMismatchException e) {
		    System.out.println("Please enter number");
		    return validatePrice();
		}	
	}
	
	
	
}
