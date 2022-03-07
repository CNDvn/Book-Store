<%-- 
    Document   : addBook
    Created on : Jun 13, 2021, 8:34:38 PM
    Author     : CND
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Book</title>
    </head>
    <body>
        <header>
            <c:set var="account" value="${sessionScope.account}"/>
            <c:if test="${not empty account}">
                <h3 style="color: dodgerblue;">Welcome ${account.name}</h3>
                <button><a href="<c:url value='MainServlet?btnAction=logout'/>">LogOut</a> </button>
            </c:if>
        </header>
        <main>
            <h1>Add New Book</h1>
            <c:if test="${not empty requestScope.msgSuccess}">
                <font color="green"><h3>${requestScope.msgSuccess}</h3></font>
                </c:if>

            <form action="MainServlet" method="POST" enctype="multipart/form-data">
                <table>
                    <tbody>
                        <tr>
                            <td>Book ID:</td>
                            <td>
                                <input type="text" name="bookId" value="<c:if test='${empty requestScope.msgSuccess and empty requestScope.bookErr.bookIdErr}'>${param.bookId}</c:if>" pattern="BS[0-9]{3}" required /> *
                                    <br/><font color="green">Book ID have 5 character with format BSxxx (ex: BS000)</font>
                                </td>
                                <td>
                                    <font color="red">${requestScope.bookErr.bookIdErr}</font>
                            </td>
                        </tr>
                        <tr>
                            <td>Title:</td>
                            <td>
                                <input type="text" name="title" value="<c:if test='${empty requestScope.msgSuccess and empty requestScope.bookErr.titleErr}'>${param.title}</c:if>" minlength="5" maxlength="150" required /> *
                                    <br/><font color="green">Title have 5 -> 50 character</font>
                                </td>
                                <td>
                                    <font color="red">${requestScope.bookErr.titleErr}</font>
                            </td>
                        </tr>
                        <tr>
                            <td>Image:</td>
                            <td>
                                <input type="file" name="image" value="" />
                                <br/><font color="green">We accept image with extension jpg</font>
                            </td>
                            <td>
                                <font color="red">${requestScope.bookErr.imageErr}</font>
                            </td>
                        </tr>
                        <tr>
                            <td>Author:</td>
                            <td>
                                <input type="text" name="author" value="<c:if test='${empty requestScope.msgSuccess and empty requestScope.bookErr.authorErr}'>${param.author}</c:if>" minlength="3" maxlength="50" required /> *
                                    <br/><font color="green">Author have 3 -> 50 character</font>
                                </td>
                                <td>
                                    <font color="red">${requestScope.bookErr.authorErr}</font>
                            </td>
                        </tr>
                        <tr>
                            <td>Description:</td>
                            <td><textarea name="description"><c:if test='${empty requestScope.msgSuccess}'>${param.description}</c:if></textarea> </td>
                            </tr>
                            <tr>
                                <td>Price:</td>
                                <td>
                                    <input type="number" step="0.01" min="0" name="price" value="<c:if test='${empty requestScope.msgSuccess}'>${param.price}</c:if>" required /> *
                                    <br/><font color="green">price is a number</font>
                                </td>
                            </tr>
                            <tr>
                                <td>Quantity:</td>
                                <td>
                                    <input type="number" min="1" name="quantity" value="<c:if test='${empty requestScope.msgSuccess}'>${param.quantity}</c:if>" required /> *
                                    <br/><font color="green">quantity greater than 0 </font>
                                </td>
                            </tr>
                            <tr>
                                <td>Category:</td>
                                <td>
                                    <select name="category">
                                    <c:forEach var="item" items="${requestScope.listCategory}">
                                        <c:choose>
                                            <c:when test="${item.categoryId == param.category}">
                                                <option value="${item.categoryId}" selected="true">${item.name}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="${item.categoryId}">${item.name}</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td><button type="submit" name="btnAction" value="addBook">Add</button> </td>
                            <td><input type="reset" value="Reset" /> </td>
                        </tr>
                    </tbody>
                </table>
            </form>
            <c:url var="search" value="MainServlet">
                <c:param name="btnAction" value="search" />
            </c:url>
            <br/><a href="${search}"><- Back</a>
        </main>
    </body>
</html>
