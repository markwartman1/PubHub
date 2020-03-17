package examples.pubhub.dao;

import java.util.List;

import examples.pubhub.model.Book;
import examples.pubhub.model.Tag;

/**
 * Interface for our Data Access Object to handle database queries related to Tags.
 */
public interface TagDAO {
	
	public List<Tag> getAllTags();
	public List<Book> getBooksByTag(String tag);			// requested
	public List<Tag> getTagsByBook(String isbn);			// requested
	
	public boolean addTag(Tag tag);							// requested
	public boolean updateTag(Tag tag);
	public boolean deleteTag(Tag tag);						// requested

}
