package java_dom_parser;

import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
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
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

public class DomParser {
	static Validator validator = new Validator();
	public static List<Book> bookList;
    static List<String> fields;
    static SortBooks sortObj;
	
//  <------------Do you want to update a this field-------->
	public static boolean update(String field) {
		
		if(fields.contains(field.toLowerCase())) {
			System.out.println("Do you want to update "+field+" of the Book: \n");
	        System.out.println("1-Enter 'y' or 'n' \n");
	
	        Scanner input = new Scanner(System.in);
	        String choise=input.nextLine();
	
	        boolean theChoice=false;
	       
	        String search;
	        switch (choise) {
	            case "y": {
	            	theChoice= true;
	            	break;
	            }
	            case "n": {
	            	theChoice= false;
	            	break;
	            }
	            default: {
	            	System.out.println("something went wrong!");
	            	theChoice=update(field);
	            	break;
	            }
	        }
			return theChoice;
		}
		return false;
	}
	
//  <------------update book fields-------->
	public static void updateFields(Node bookNode) {
		Scanner input = new Scanner(System.in);
		
		NodeList bookChildren = bookNode.getChildNodes();
		for (int j=0; j<bookChildren.getLength();j++) {
			Node bookChild = bookChildren.item(j);
			
			if(update(bookChild.getNodeName()) && bookChild.getNodeType()==Node.ELEMENT_NODE) {
				switch (bookChild.getNodeName().toLowerCase()) {
				
					case "author":{
						bookChild.setTextContent(validator.validateAuthor());
						break;
					}
					case "title":{
						bookChild.setTextContent(validator.validateTitle());
						break;
					}
					case "genre":{
						bookChild.setTextContent(validator.chooseGenre());		
						break;
					}
					case "price":{
						bookChild.setTextContent(String.valueOf(validator.validatePrice()));
						break;
					}
					case "publish_date":{
						bookChild.setTextContent(validator.validateDate());	
						break;
					}
					case "description":{
						System.out.println("Enter Description: ");
		                String descriptionValue = input.nextLine();
						bookChild.setTextContent(descriptionValue);	
						break;
					}
				}		
			}
		}
	}
	
	
//	<------------Update Book ----------->
	public static int updateBook(String id,Document doc) {
		NodeList bookNodes = doc.getElementsByTagName("Book");
		for(int i=0; i<bookNodes.getLength();i++) {
			Node bookNode = bookNodes.item(i);
			if( bookNode.getNodeType()== Node.ELEMENT_NODE) {
				NodeList bookChildren = bookNode.getChildNodes();
				
				for (int j=0; j<bookChildren.getLength();j++) {
					Node bookChild = bookChildren.item(j);
					if(bookChild.getNodeName().toLowerCase().contains("id")&&bookChild.getNodeType()==Node.ELEMENT_NODE) {
						if(bookChild.getChildNodes().item(0).getTextContent().toLowerCase().contains(id.toLowerCase())) {
							updateFields(bookNode);		
							saveDoc(doc, "books.xml");
							XMLToList( bookNodes);
					        System.out.println("Book has been Updated successfuly");
					        return(1);
						}
					}
				}
				
			}
		}
		System.out.println("ID not found");
		return(1);
	}
	
	
//	<------------Store XML in Book Model ----------->
    public static List<Book> XMLToList(NodeList bookNodes) {

        List<Book> bookList = new ArrayList<Book>();

        for (int i = 0; i < bookNodes.getLength(); i++) {
            Node bookNode = bookNodes.item(i);
            if (bookNode.getNodeType() == Node.ELEMENT_NODE) {
                Element book = (Element) bookNode;

//                String id = book.getAttribute("ID");
                String id = book.getElementsByTagName("ID").item(0).getChildNodes().item(0).getNodeValue();
                String author = book.getElementsByTagName("Author").item(0).getChildNodes().item(0).getNodeValue();
                String title = book.getElementsByTagName("Title").item(0).getChildNodes().item(0).getNodeValue();
                String genre = book.getElementsByTagName("Genre").item(0).getChildNodes().item(0).getNodeValue();
                String price = book.getElementsByTagName("Price").item(0).getChildNodes().item(0).getNodeValue();
                String publishDate = book.getElementsByTagName("Publish_Date").item(0).getChildNodes().item(0).getNodeValue();
                String description = book.getAttribute("Description");
                
                Book bookTemp = new Book(id, author, title, genre, price, publishDate, description);
                if(!bookList.contains(bookTemp)) {
                	bookList.add(bookTemp);                	
                }
                
            }
        }

        return bookList;
    }
//	<-------------------------------------------------------->

//	<---------------------add Book ------------------------>
    public static void addBook(int booksNumber, Document doc) {

        Scanner input = new Scanner(System.in);
        Random rand = new Random();

        for (int i = 0; i < booksNumber; i++) {
            try {
                Element book = doc.createElement("Book");

                validator.setBookList(bookList);
                String idValue = validator.validateID();
                Element id = doc.createElement("ID");
                Text idVal = doc.createTextNode(idValue);
                id.appendChild(idVal);

                
                String titleValue = validator.validateTitle();
                Element title = doc.createElement("Title");
                Text titleVal = doc.createTextNode(titleValue);
                title.appendChild(titleVal);
                
                
                String authorValue = validator.validateAuthor();
                Element author = doc.createElement("Author");
                Text authorVal = doc.createTextNode(authorValue);
                author.appendChild(authorVal);


                
                String genreValue = validator.chooseGenre();
                Element genre = doc.createElement("Genre");
                Text genreVal = doc.createTextNode(genreValue);
                genre.appendChild(genreVal);

                
                String priceValue = String.valueOf(validator.validatePrice());
                Element price = doc.createElement("Price");
                Text priceVal = doc.createTextNode(priceValue);
                price.appendChild(priceVal);

                
                String publishValue = validator.validateDate();
                Element publishDate = doc.createElement("Publish_Date");
                Text publishVal = doc.createTextNode(publishValue);
                publishDate.appendChild(publishVal);

                System.out.println("Enter Description: ");
                String descriptionValue = input.nextLine();
                Element description = doc.createElement("Description");
                Text descriptionVal = doc.createTextNode(descriptionValue);
                description.appendChild(descriptionVal);

                book.appendChild(id);
                book.appendChild(author);
                book.appendChild(title);
                book.appendChild(genre);
                book.appendChild(price);
                book.appendChild(publishDate);
                book.appendChild(description);

//			Node root= doc.getElementsByTagName("Catalogue").item(0);
                Node root = doc.getDocumentElement();
                Element newCatalogue = (Element) root;

//			Element catalogue =doc.createElement("Catalogue");
                newCatalogue.appendChild(book);

//			doc.appendChild(catalogue);
                doc.replaceChild(root, newCatalogue);

                DOMSource source = new DOMSource(doc);

                File f = new File("books.xml");
                Result result = new StreamResult(f);
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
                transformer.setOutputProperty(OutputKeys.INDENT, "no");
                transformer.transform(source, result);
                

                System.out.println("Book has been added successfuly");

            } catch (TransformerConfigurationException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (TransformerException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }
//	<-------------------------------------------------------->


    //<--------------------delete--------------------------->
    public static void deleteByID(String id) {
        String xmlFile = "books.xml";
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(xmlFile);
            NodeList books = document.getElementsByTagName("Book");
            String bookID = "";
            for (int i = 0; i < books.getLength(); i++) {
                Element book = (Element) books.item(i);
                bookID = book.getAttribute("ID");
                System.out.println("ID" + bookID);
                if (bookID.equalsIgnoreCase(id)) {
                    System.out.println("ID" + bookID);
                    Element foundedbook = book;
                    System.out.println("ID" + foundedbook.getTextContent());
                    foundedbook.getParentNode().removeChild(books.item(i));
                    break;
                }
            }

            saveDoc(document, xmlFile);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    //<-------------------------save doc------------------------>

    public static void saveDoc(Document doc, String xmlFile) {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "no");
            DOMSource domSource = new DOMSource(doc);
            StreamResult streamResult = new StreamResult(xmlFile);
            transformer.transform(domSource, streamResult);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
//	<-------------------------------------------------------->

   

    
//	<-------------------------------------------------------->
    
    
    
//	<--------------------Print Books------------------>
    public static void print(List<Book> bookList) {	
    	System.out.println("Current Books Are: \n");
    	 for (Book book : bookList) {
             System.out.println(book.toString());
         }
    	 
    	 System.out.println("\n \n _______________________________ \n \n");
    }  
//	<------------------------------------------------->
    
    
//	<-------------------Interface---------------------->
    public static void userInterface(Document doc,NodeList bookNodes) {
    	Scanner input = new Scanner(System.in);
    	
    	String flag = "y";
        while (flag.equalsIgnoreCase("y")) {
            System.out.println("choose number of statement:");
            System.out.println("1-Enter number of book you want to be added and book data"
                    + "\n2-search for book\n"
                    + "3-delete book\n"
                    + "4-Update book\n"
                    + "5-Sort Books\n"
                    + "6-Exit");

            int choise;

            choise = input.nextInt();
            String search;
            switch (choise) {
                case 1: {
                    int numOfBooks;
                    System.out.println("Enter number of books:");
                    numOfBooks = input.nextInt();
                    addBook(numOfBooks, doc);
                    bookList = XMLToList(bookNodes);
                    break;
                }
                case 2: {
                    SearchBook sb=new SearchBook();
                    sb.setBookList(bookList);
                    sb.searchBook();
                    break;
                }
                case 3:
                {
                    System.out.println("Enter book ID:");
                    search = input.nextLine();
                    search = input.nextLine();
                    deleteByID(search) ;
                    break;
                }
                case 4:
                {
                	System.out.println("Enter book ID:");
                    search = input.nextLine();
                    search = input.nextLine();
                    updateBook( search, doc);
                    break;
                }
                case 5:
                {
                	sortObj =new SortBooks();
                	sortObj.sortInterface(bookList, doc);
                    break;
                }
                default:
                {
                    flag="n";
                    break;
                }
            }
        }
    }
//	<-------------------------------------------------->
    
    

    public static void main(String[] args) {
        

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse("books.xml");
            
            NodeList bookNodes = doc.getElementsByTagName("Book");
            bookList = new ArrayList<Book>();
            
            bookList = XMLToList(bookNodes);
            
            print(bookList);
            
            fields=new ArrayList<String>();
            fields.add("author");
            fields.add("description");
            fields.add("title");
            fields.add("price");
            fields.add("publish_date");
            fields.add("genre");
         
          
           
           userInterface(doc,bookNodes);
            
            
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
