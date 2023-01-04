package java_dom_parser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

public class SortBooks {
	
	static DomParser domParser ;
	
	public static List<Book> sortByID(List<Book> bookList) {

		bookList.sort((o1, o2)
				-> o1.getID().toLowerCase().compareTo(
					o2.getID().toLowerCase()));
		return bookList;
	}
	public static List<Book> sortByTitle(List<Book> bookList) {

		bookList.sort((o1, o2)
				-> o1.getTitle().toLowerCase().compareTo(
					o2.getTitle().toLowerCase()));
		return bookList;
	}
	public static List<Book> sortByAuthor(List<Book> bookList) {

		bookList.sort((o1, o2)
				-> o1.getAuthor().toLowerCase().compareTo(
					o2.getAuthor().toLowerCase()));
		return bookList;
	}
	public static List<Book> sortByPrice(List<Book> bookList) {

		bookList.sort((o1, o2)
				-> o1.getPrice().toLowerCase().compareTo(
					o2.getPrice().toLowerCase()));
		return bookList;
	}
	
	
	public static void removeChilds(Node node) {
	    while (node.hasChildNodes())
	        node.removeChild(node.getFirstChild());
	}
	
	
	
	
//	<---------------------Save Sorted ------------------------>
    public static void bookListToXML(List<Book> bookList, Document doc) {

    	Node root = doc.getDocumentElement();
        Element newCatalogue = (Element) root;
        removeChilds(newCatalogue);
        
        
        for (Book elm : bookList) {
            
                Element book = doc.createElement("Book");

                Element id = doc.createElement("ID");
                Text idVal = doc.createTextNode(elm.getID());
                id.appendChild(idVal);

                
                Element title = doc.createElement("Title");
                Text titleVal = doc.createTextNode(elm.getTitle());
                title.appendChild(titleVal);
                
                
                Element author = doc.createElement("Author");
                Text authorVal = doc.createTextNode(elm.getAuthor());
                author.appendChild(authorVal);


                
                Element genre = doc.createElement("Genre");
                Text genreVal = doc.createTextNode(elm.getGenre());
                genre.appendChild(genreVal);

                
                Element price = doc.createElement("Price");
                Text priceVal = doc.createTextNode(elm.getPrice());
                price.appendChild(priceVal);

                
                Element publishDate = doc.createElement("Publish_Date");
                Text publishVal = doc.createTextNode(elm.getPublishDate());
                publishDate.appendChild(publishVal);

                
                Element description = doc.createElement("Description");
                Text descriptionVal = doc.createTextNode(elm.getDescription());
                description.appendChild(descriptionVal);

                book.appendChild(id);
                book.appendChild(author);
                book.appendChild(title);
                book.appendChild(genre);
                book.appendChild(price);
                book.appendChild(publishDate);
                book.appendChild(description);

//                newCatalogue.removeChild((Node)book);
                newCatalogue.appendChild(book);

        }
        doc.replaceChild(root, newCatalogue);
        try {
	        DOMSource source = new DOMSource(doc);
	
	        File f = new File("books.xml");
	        Result result = new StreamResult(f);
	        TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        Transformer transformer = transformerFactory.newTransformer();
	        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
	        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	        transformer.transform(source, result);
	        

	
	    } catch (TransformerConfigurationException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    } catch (TransformerException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
        
    }
//	<-------------------------------------------------------->
    

    
//	<--------------------Print Books------------------>
    public static void print(List<Book> bookList) {	
    	System.out.println("Sorted Books Are: \n");
    	 for (Book book : bookList) {
             System.out.println(book.toString());
         }
    	 
    	 System.out.println("\n \n _______________________________ \n \n");
    }  
//	<------------------------------------------------->
    
    

	
	public static void sortInterface(List<Book> bookList,Document doc) {
		Scanner input = new Scanner(System.in);
		List<Book> newBookList = new ArrayList<Book>();
    	
    	boolean flag = true;
        while (flag) {
            System.out.println("How would like to Sort Books\nchoose number:");
            System.out.println("1-Sort By ID\n"
                    + "2-Sort By Title\n"
                    + "3-Sort By Author\n"
                    + "4-Sort By Price\n"
                    + "5-Exit");

            int choise;

            choise = input.nextInt();
            switch (choise) {
                case 1: {
                	newBookList=sortByID(bookList);
                	bookListToXML(newBookList,doc);
                	print(newBookList);
                	flag=false;
                    break;
                }
                case 2: {
                	newBookList=sortByTitle(bookList);
                	bookListToXML(newBookList,doc);
                	print(newBookList);
                	flag=false;
                    break;
                }
                case 3:
                {
                	newBookList=sortByAuthor(bookList);
                	bookListToXML(newBookList,doc);
                	print(newBookList);
                	flag=false;
                    break;
                }
                case 4:
                {
                	newBookList=sortByPrice(bookList);
                	bookListToXML(newBookList,doc);
                	print(newBookList);
                	flag=false;
                    break;
                }
                default:
                {
                    flag=false;
                    break;
                }
            }
        }
		
	}
	
	
	

}
