package examples.pubhub.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import examples.pubhub.model.Book;
import examples.pubhub.model.Tag;
import examples.pubhub.utilities.DAOUtilities;

public class TagDAOImpl implements TagDAO {
	
	Connection connection = null;
	PreparedStatement stmt = null;

	@Override
	public List<Tag> getAllTags() {
		
		List<Tag> tags = new ArrayList<>();
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT tag_name FROM book_tags";
			stmt = connection.prepareStatement(sql);	// Creates the prepared statement from the query
			
			ResultSet rs = stmt.executeQuery();			// Queries the database
			
			while(rs.next()) {
				Tag tag = new Tag(
						rs.getString("isbn_13"),
						rs.getString("tag_name"));
				
				tags.add(tag);
			}
			
			rs.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			// We need to make sure our statements and connections are closed, 
			// or else we could wind up with a memory leak
			closeResources();
		}
		
		// return list of Tag objects from the database
		return tags;
	}

	@Override
	public List<Book> getBooksByTag(String tag) {

		List<Book> books = new ArrayList<>();

		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT * FROM books INNER JOIN book_tags ON books.isbn_13 = book_tags.isbn_13 "
					+ "WHERE tag_name = ?";	// Note the ? in the query
			stmt = connection.prepareStatement(sql);
			
			// Set the '?' with the tag variable
			stmt.setString(1, tag);	
			
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Book book = new Book();

				book.setIsbn13(rs.getString("isbn_13"));
				book.setAuthor(rs.getString("author"));
				book.setTitle(rs.getString("title"));
				book.setPublishDate(rs.getDate("publish_date").toLocalDate());
				book.setPrice(rs.getDouble("price"));
				book.setContent(rs.getBytes("content"));
				
				books.add(book);
			}
			
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		
		return books;

	}

	@Override
	public List<Tag> getTagsByBook(String isbn) {

		List<Tag> tags = new ArrayList<>();
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT isbn_13, tag_name FROM book_tags WHERE isbn_13 = ?";	// note the '?' in the query
			stmt = connection.prepareStatement(sql);	// Creates the prepared statement from the query
			
			// This command populates the '?' with the passed in isbn
			stmt.setString(1, isbn);
			
			ResultSet rs = stmt.executeQuery();			// Queries the database
			
			while(rs.next()) {
				Tag tag = new Tag(
						rs.getString("isbn_13"),
						rs.getString("tag_name"));
				
				tags.add(tag);
			}
			
			rs.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			// We need to make sure our statements and connections are closed, 
			// or else we could wind up with a memory leak
			closeResources();
		}
		
		// return list of Tag objects from the database
		return tags;

	}

	@Override
	public boolean addTag(Tag tag) {

		try {
			connection = DAOUtilities.getConnection();
			String sql = "INSERT INTO book_tags VALUES (?, ?)"; // note two '?''s
			stmt = connection.prepareStatement(sql);
			
			// Setting '?' values
			stmt.setString(1, tag.getIsbn_13());
			stmt.setString(2, tag.getTag_name());
			
			// If we were able to add our Tag to the DB, we want to return true. 
			// This if statement both executes our query, and looks at the return 
			// value to determine how many rows were changed
			if (stmt.executeUpdate() != 0)
				return true;
			else
				return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeResources();
		}

	}

	@Override
	public boolean updateTag(Tag tag) {

		try {
			connection = DAOUtilities.getConnection();
			String sql = "UPDATE book_tags SET tag_name=? WHERE isbn_13=?"; // note two '?''s
			stmt = connection.prepareStatement(sql);
			
			// Setting '?' values
			stmt.setString(2, tag.getIsbn_13());
			stmt.setString(1, tag.getTag_name());
			
			// If stmt.executeUpdate() value is not 0, update is successful
			if (stmt.executeUpdate() != 0)
				return true;
			else
				return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeResources();
		}

	}

	@Override
	public boolean deleteTag(Tag tag) {

		try {
			connection = DAOUtilities.getConnection();
			String sql = "DELETE from book_tags WHERE isbn_13=? and tag_name=?"; // note '?'
			stmt = connection.prepareStatement(sql);
			
			// Setting '?' value
			stmt.setString(1, tag.getIsbn_13());
			stmt.setString(2, tag.getTag_name());
			
			// If stmt.executeUpdate() value is not 0, update is successful
			if (stmt.executeUpdate() != 0)
				return true;
			else
				return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeResources();
		}

	}
	
	/*------------------------------------------------------------------------------------------------*/

	// Closing all resources is important, to prevent memory leaks. 
	// Ideally, you really want to close them in the reverse-order you open them
	private void closeResources() {
		try {
			if (stmt != null)
				stmt.close();
		} catch (SQLException e) {
			System.out.println("Could not close statement!");
			e.printStackTrace();
		}
		
		try {
			if (connection != null)
				connection.close();
				System.out.println("Closing down connection...");
		} catch (SQLException e) {
			System.out.println("Could not close connection!");
			e.printStackTrace();
		}
	}


}
