package examples.pubhub.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.pubhub.dao.TagDAO;
import examples.pubhub.model.Tag;
import examples.pubhub.utilities.DAOUtilities;


@WebServlet("/BookPublishing")
public class BookPublishingTagServlet extends HttpServlet {
	
	//private static final long serialVersionUID = 2L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Grab the list of Books from the Database
		String isbn13 = request.getParameter("isbn13");
		//String isbn13 = "1111111111111";
		
		TagDAO dao = DAOUtilities.getTagDAO();
		List<Tag> tagList = dao.getTagsByBook(isbn13);

		// Populate the list into a variable that will be stored in the session
		request.getSession().setAttribute("tags", tagList);
		
		request.getRequestDispatcher("bookPublishingHome.jsp").forward(request, response);
	}

}
