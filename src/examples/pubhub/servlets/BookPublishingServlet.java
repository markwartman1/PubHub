package examples.pubhub.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.pubhub.dao.BookDAO;
import examples.pubhub.dao.TagDAO;
import examples.pubhub.model.Book;
import examples.pubhub.model.Tag;
import examples.pubhub.utilities.DAOUtilities;

/*
 * This servlet will take you to the homepage for the Book Publishing module (level 100)
 */
@WebServlet("/BookPublishing")
public class BookPublishingServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Grab the list of Books from the Database
		BookDAO dao = DAOUtilities.getBookDAO();
		List<Book> bookList = dao.getAllBooks();
		System.out.println("\n\ndoGet: BookPublishingServlet is running...");

		
		/**
		 * get Tags for each and every of book-list and load them into the book object
		 */
		//String isbn13 = request.getParameter("isbn13");
		//System.out.println("This is isbn13: " + isbn13);
		//String isbn13 = "1111111111111";
		
		TagDAO tdao = DAOUtilities.getTagDAO();
		//List<Tag> tagList = tdao.getTagsByBook(isbn13);
		List<Tag> tagList = null;
		for (Book b : bookList) {
			tagList = tdao.getTagsByBook(b.getIsbn13());
			for(Tag t : tagList) {
				//System.out.println("this is tagname: " + t.getTag_name());
				//tagList.add(daoT.getTagsByBook(b.getIsbn13()));
				b.addBook_tags(t);
			}
		}

		// Populate the list into a variable that will be stored in the session
		request.getSession().setAttribute("books", bookList);
		
		request.getRequestDispatcher("bookPublishingHome.jsp").forward(request, response);
		/**
		 * attempt to forward
		 */
		//request.getRequestDispatcher("/BookPublishingTagServlet.java").forward(request, response);
	}
}
