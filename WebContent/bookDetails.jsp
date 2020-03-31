	<!-- Header -->
	<jsp:include page="header.jsp" />
	
	<!-- JSTL includes -->
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	
<!-- 	Just some stuff you need -->
	<header>
	  <div class="container">
		
	<c:choose>
	<c:when test="${not empty message }">
	  <p class="alert ${messageClass}">${message }</p>
	<%
	  session.setAttribute("message", null);
	  session.setAttribute("messageClass", null);
	%>
	</c:when>
	</c:choose>
	
		<h1>PUBHUB <small>Book Details - ${book.isbn13 }</small></h1>
		<hr class="book-primary">
		
		<div class="row">
		  	<div class="col-md-8">
		  	<h3>Edit Book Information</h3>
		  <form action="UpdateBook" method="post" class="form-horizontal">
		  
		  <input type="hidden" class="form-control" id="isbn13" name="isbn13" required="required" value="${book.isbn13 }" />
		  
		  <div class="form-group">
		    <label for="title" class="col-sm-4 control-label">Title</label>
		    <div class="col-sm-5">
		      <input type="text" class="form-control" id="title" name="title" placeholder="Title" required="required" value="${book.title }" />
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="author" class="col-sm-4 control-label">Author</label>
		    <div class="col-sm-5">
		      <input type="text" class="form-control" id="author" name="author" placeholder="Author" required="required" value="${book.author }" />
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="price" class="col-sm-4 control-label">Price</label>
		    <div class="col-sm-5">
		      <input type="number" step="0.01" class="form-control" id="price" name="price" placeholder="Price" required="required" value="${book.price }" />
		    </div>
		  </div>
		  
		  <div class="form-group">
		    <div class="col-sm-offset-4 col-sm-1">
		      <button type="submit" class="btn btn-info">Update</button>
		    </div>
		  </div>
		  </form>
		  	</div>
		  	
		  	<!-- Column width 4 on the right side -->
		  	<div class="col-md-4">
		  	<h3>Edit Book Tags</h3>
		  	<!-- my stuff -->
		  	<form action="TagAdd" method="post" class="form-horizontal">
		  	<input type="hidden" class="form-control" id="isbn13" name="isbn13" required="required" value="${book.isbn13 }" />
		  		<div class="form-group">
				    <label for="tag" class="col-sm-4 control-label">Add Tag</label>
				    <div class="row">
				    	<div class="col-sm-5">
				      		<input type="text" step="0.01" class="form-control" id="tag" name="tag" placeholder="enter tag here" />
				    	</div>
				    	<div class="col-sm-1">
				    		<button type="submit" class="btn btn-success">Add</button>
				    	</div>
				    </div>
		  		</div>
		  	</form>
		  <c:forEach var="tag" items="${book.getListOfTagsOfBook()}">
		  <form action="TagDelete" method="post" class="form-horizontal">
		  <input type="hidden" class="form-control" id="isbn13" name="isbn13" required="required" value="${book.isbn13 }" />
		  <div class="form-group">
		    <label for="tag" class="col-sm-4 control-label">Tag</label>
		    
								<!-- <option>tag1</option> -->
								<!-- <option>tag2</option> -->
								<%-- <option><c:out value="${tag.tag_name}" /></option> --%>
				  <div class="row">
				  	<div class="col-sm-5">
		      			<input type="text" step="0.01" class="form-control" id="tag" name="tag"
		      				value="${tag.tag_name}" 
		      				placeholder="tag" readonly />
		    		</div>
		    		<div class="col-sm-1">
		    			<button type="submit" class="btn btn-sm btn-danger">Delete</button>
		    		</div>
				  </div>
					
				
		    
		  </div>
		  </form>
		  </c:forEach>
		  
		
		  	</div>
		</div>
		

		  

	  </div>
	</header>
	
	<!-- Footer -->
	<jsp:include page="footer.jsp" />
