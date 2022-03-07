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
        <title>Update Book</title>
    </head>
    <body>
        <style>
            input{
                width: 350px;
                height: 20px;
            }
        </style>
        <header>
            <c:set var="account" value="${sessionScope.account}"/>
            <c:if test="${not empty account}">
                <h3 style="color: dodgerblue;">Welcome ${account.name}</h3>
                <button><a href="<c:url value='MainServlet?btnAction=logout'/>">LogOut</a> </button>
            </c:if>
        </header>
        <main>
            <h1>Update Book</h1>
            <c:if test="${not empty requestScope.msgSuccess}">
                <font color="green"><h3>${requestScope.msgSuccess}</h3></font>
                </c:if>

            <c:set var="bookObj" value="${requestScope.bookObj}"/>
            <form action="MainServlet" method="POST" enctype="multipart/form-data">
                <table>
                    <tbody>
                    <input type="hidden" name="bookId" value="${param.bookId}" pattern="BS[0-9]{3}" required />
                    <input type="hidden" name="realUpdate" value="true"/>
                    <tr>
                        <td>Title:</td>
                        <td>
                            <input type="text" name="title" value="${bookObj.title}${param.title}" minlength="5" maxlength="150" required /> *
                            <br/><font color="green">Title have 5 -> 50 character</font>
                        </td>
                        <td>
                            <font color="red">${requestScope.bookErr.titleErr}</font>
                        </td>
                    </tr>
                    <tr>
                        <td>Image:</td>
                        <td>
                            <img src="<c:url value="/image/" />${bookObj.image}${param.imageTmp}"  width="100px"/>
                            <input type="hidden" name="imageTmp" value="${bookObj.image}${param.imageTmp}" />
                            <input type="file" name="image" value="<c:url value="/image/" />${bookObj.image}" />
                            <br/><font color="green">We accept image with extension jpg</font>
                        </td>
                        <td>
                            <font color="red">${requestScope.bookErr.imageErr}</font>
                        </td>
                    </tr>
                    <tr>
                        <td>Author:</td>
                        <td>
                            <input type="text" name="author" value="${bookObj.author}${param.author}" minlength="3" maxlength="50" required /> *
                            <br/><font color="green">Author have 3 -> 50 character</font>
                        </td>
                        <td>
                            <font color="red">${requestScope.bookErr.authorErr}</font>
                        </td>
                    </tr>
                    <tr>
                        <td>Description:</td>
                        <td><textarea style="width: 300px; height: 200px" name="description">${bookObj.description}${param.description}</textarea> </td>
                    </tr>
                    <tr>
                        <td>Price:</td>
                        <td>
                            <input type="number" step="0.01" min="0" name="price" value="${bookObj.price}${param.price}" required /> *
                            <br/><font color="green">price is a number</font>
                        </td>
                    </tr>
                    <tr>
                        <td>Quantity:</td>
                        <td>
                            <input type="number" min="1" name="quantity" value="${bookObj.quantity}${param.quantity}" required /> *
                            <br/><font color="green">quantity greater than 0 </font>
                        </td>
                    </tr>
                    <tr>
                        <td>Date</td>
                        <td>
                            <input type="date" name="date" value="${bookObj.date}${param.date}" required /> *
                        </td>
                    </tr>
                    <tr>
                        <td>Category:</td>
                        <td>
                            <select name="category">
                                <c:forEach var="item" items="${requestScope.listCategory}">
                                    <c:choose>
                                        <c:when test="${item.categoryId == bookObj.categoryId or item.categoryId == param.category}">
                                            <option value="${item.categoryId}" selected>${item.name}</option>
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
                        <td></td>
                        <td><button type="submit" name="btnAction" value="updateBook">Update</button> </td>
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
