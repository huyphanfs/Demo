<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en-us">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=Edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <base href="/Hibernate/" />
  <title>Book Management</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
 <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css.css"/>
</head>
<body>
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-lg-12">
          <center><h1>Book Management</h1></center><br/>
        <div class="form">
            <form action="" method="POST">
			
            <div class="form-row">
               <div class="form-group col-md-2">
               <label for="inputTitle">ID</label>
                <input type="text" class="form-control" id="id" name="id" value="${book.id }" readonly="readonly">
              </div>
               <div class="form-group col-md-10">
               <label for="inputTitle">Title</label>
                <input type="text" class="form-control" id="title" name="title" value="${book.title }" placeholder="Title">
              </div>
              </div>
              <div class="form-row">
                <div class="form-group col-md-4">
                  <label for="inputAuthor">Author</label>
                  <input type="text" class="form-control" id="author" name="author" value="${book.author }" placeholder="Author">
                </div>
                <div class="form-group col-md-4">
                  <label for="inputGenre">Genre</label>
                  <input type="text" class="form-control" id="genre" name="genre" value="${book.genre }" placeholder="Genre">
                </div>
                <div class="form-group col-md-4">
                  <label for="inputYear">Year</label>
                  <input type="text" class="form-control" id="year" name="year" value="${book.year }" placeholder="Year">
                </div>
              </div>

              <button class="btn btn-primary" formaction="BookServlet/insert">Insert</button>
              <button class="btn btn-warning" formaction="BookServlet/update">Update</button>
              <button class="btn btn-danger" formaction="BookServlet/delete">Delete</button>
              <button class="btn btn-info" formaction="BookServlet/reset">Reset</button>
               <c:if test="${not empty message }">
              		${message }
              	</c:if>
              	<c:if test="${not empty error }">
              		<${error }
              	</c:if>              	
            </form>

        </div>
        <br/>
        <div class="table">
          <table class="table table-striped">
    <thead>
      <tr>
        <th>ID</th>
        <th>Title</th>
        <th>Author</th>
        <th>Genre</th>
        <th>Year</th>
        <th>&nbsp;</th>
      </tr>
    </thead>
    <tbody>
    <c:forEach var="item" items="${books }" >
      <tr>
        <td>${item.id }</td>
        <td>${item.title }</td>
        <td>${item.author }</td>
        <td>${item.genre }</td>
        <td>${item.year }</td>
        <td><a href="BookServlet/edit?id=${item.id }">Edit</a>
         <a href="BookServlet/delete?id=${item.id }">Delete</a>
        </td>
      </tr>
    	</c:forEach>
    </tbody>
  </table>

        </div>
      </div>
    </div>

  </div>

  <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
</body>
</html>