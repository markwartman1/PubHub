package examples.pubhub.dao;

import java.util.List;

import examples.pubhub.model.Book;
import examples.pubhub.model.Tag;

public class TestTagDAO {

	public static void main(String[] args) {
		
//		TagDAO dao = new TagDAOImpl();
//	    List<Tag> list = dao.getTagsByBook("1111111111111");
//	    
//	    for (int i =0; i < list.size(); i++) {
//	    	
//	    	Tag t = list.get(i);
//	    	System.out.println(t);
//	    }
		
		BookDAO dao = new BookDAOImpl();
	    Book book = dao.getBookByISBN("1111111111111");
	    System.out.println("book isbn: " + book.getIsbn13());
	    TagDAO tdao = new TagDAOImpl();
	    List<Tag> myTags = tdao.getTagsByBook(book.getIsbn13());
	    for(Tag t : myTags) {
	    	book.addBook_tags(t);
	    	System.out.println(t.getTag_name());

	    }
	    
	    for (Tag t : book.getListOfTagsOfBook()) {
	    	
	    	//Book t = list.get(i);
	    	System.out.println(t.getTag_name());
	    }

	}

}
