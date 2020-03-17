package examples.pubhub.model;

public class Tag {

	private String isbn_13;
	private String tag_name;
	
	// Default constructor
	public Tag() {
		this.isbn_13 = null;
		this.tag_name = null;
	}

	public Tag(String isbn_13, String tag_name) {

		this.isbn_13 = isbn_13;
		this.tag_name = tag_name;
	}

	public String getIsbn_13() {
		return isbn_13;
	}

	public void setIsbn_13(String isbn_13) {
		this.isbn_13 = isbn_13;
	}

	public String getTag_name() {
		return tag_name;
	}

	public void setTag_name(String tag_name) {
		this.tag_name = tag_name;
	}

}
