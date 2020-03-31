package examples.pubhub.servlets;

import java.io.IOException;

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

@WebServlet("/TagAdd")
public class TagAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		boolean isSuccess = false;
		String isbn13 = request.getParameter("isbn13");
		System.out.println("here is isbn13: " + isbn13);
		
		TagDAO dao = DAOUtilities.getTagDAO();
		Tag tag = new Tag();
		
		if(request.getParameter("tag") != null){
			// if tag is entered, isbn should already be good
			tag.setIsbn_13(isbn13);
			tag.setTag_name(request.getParameter("tag"));
			if(dao.addTag(tag)) {
				// adding the Tag to the database was successful, so ...
				//BookDAO bookdao = DAOUtilities.getBookDAO();
				//Book book = bookdao.getBookByISBN(isbn13);
				//book.addBook_tags(tag);	// ...no need to load the Tag into the Book obj because ViewBookDetails does
				//request.setAttribute("book", book);
				isSuccess = true;
			}
		}
		
		if(isSuccess){
			request.getSession().setAttribute("message", "Tag successfully updated");
			request.getSession().setAttribute("messageClass", "alert-success");
			response.sendRedirect("ViewBookDetails?isbn13=" + isbn13);
		}else {
			request.getSession().setAttribute("message", "There was a problem adding the tag");
			request.getSession().setAttribute("messageClass", "alert-danger");
			request.getRequestDispatcher("bookDetails.jsp").forward(request, response);
		}
	}
}
