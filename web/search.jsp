<%-- 
    Document   : search
    Created on : Jun 12, 2021, 12:11:32 PM
    Author     : CND
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="hieubd.utils.MyContanst" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
    </head>
    <body>
        <style>
            td{
                max-width: 300px;
            }
            td, th {
                border: 1px solid black;
            }

            table {
                border: none;
                border-collapse: collapse;
            }
            .no_border{
                border: none;
            }
        </style>
        <header>
            <c:set var="account" value="${sessionScope.account}"/>
            <c:if test="${not empty account}">
                <h3 style="color: dodgerblue;">Welcome ${account.name}</h3>
                <button><a href="<c:url value='MainServlet?btnAction=logout'/>">LogOut</a> </button>
            </c:if>
            <c:if test="${empty account}">
                <button><a href="<c:url value='login.html' />">Login In</a> </button>
            </c:if>
            <c:if test="${account.roleId eq MyContanst.ROLE_CUSTOMER}">
                <button><a href="<c:url value='MainServlet?btnAction=cart' />">Your Cart</a> </button>
                <button><a href="<c:url value='MainServlet?btnAction=shoppingHistory'/>">Shopping History</a></button>
            </c:if>
        </header>
        <main>
            <c:if test="${not empty account and account.roleId eq MyContanst.ROLE_ADMIN}">
                <div class="function-app" style="margin: 15px 0 30px 0">
                    <button>
                        <c:url var="addBook" value="MainServlet">
                            <c:param name="btnAction" value="addBook" />
                        </c:url>
                        <a href="${addBook}">Add new Book</a>
                    </button>
                    <button>
                        <c:url var="addBook" value="MainServlet">
                            <c:param name="btnAction" value="manageDiscount" />
                        </c:url>
                        <a href="${addBook}">Manage Discount</a>
                    </button>
                </div>
            </c:if>

            <div class="search" style="margin: 20px 0">
                <form action="MainServlet">
                    <label>Price:</label>
                    <input onchange="checkPrice()" min="0" id="min" type="number" name="txtMin" value="${param.txtMin}" placeholder="min" style="width: 50px" /> -
                    <input onchange="checkPrice()" min="0" id="max" type="number" name="txtMax" value="${param.txtMax}" placeholder="max" style="width: 50px" />
                    <select name="txtCategory">
                        <option value="">Choose category</option>
                        <c:forEach var="cate" items="${requestScope.listCategory}">
                            <c:choose>
                                <c:when test="${param.txtCategory == cate.categoryId}">
                                    <option selected="true" value="${cate.categoryId}">${cate.name}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${cate.categoryId}">${cate.name}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                    <input type="text" name="txtSearch" value="${param.txtSearch}" placeholder="Search by name" />
                    <input type="submit" value="search" name="btnAction" />
                </form>
                    <h1><font color="green">${requestScope.paySuccess}</font></h1>
                <font color="red" style="display: none" id="errorPrice"></font>
            </div>
            <c:if test="${not empty requestScope.msgFailCart}">
                <font color="red">${requestScope.msgFailCart}</font>
            </c:if>
            <c:choose>
                <c:when test="${not empty requestScope.listBook}">
                    <div class="container">
                        <table border="1">
                            <thead>
                                <tr>
                                    <th>No.</th>
                                    <th>Title</th>
                                    <th>Image</th>
                                    <th>Description</th>
                                    <th>Category</th>
                                    <th>Author</th>
                                    <th>Price</th>
                                        <c:if test="${account.roleId eq MyContanst.ROLE_ADMIN}">
                                        <th>Quantity</th>
                                        </c:if>
                                    <th colspan="2">Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="item" items="${requestScope.listBook}" varStatus="counter">
                                    <tr>
                                        <td>${counter.count}</td>
                                        <td>${item.title}</td>
                                        <td >
                                            <img src="<c:url value="/image/" />${item.image}" width="100px"/>
                                        </td>
                                        <td>${item.description}</td>
                                        <td>
                                            <c:forEach var="cate" items="${requestScope.listCategory}">
                                                <c:if test="${cate.categoryId == item.categoryId}">
                                                    ${cate.name}
                                                </c:if>
                                            </c:forEach>
                                        </td>
                                        <td style="text-align: center">${item.author}</td>
                                        <td style="text-align: center">$ ${item.price}</td>
                                        <c:if test="${account.roleId eq MyContanst.ROLE_ADMIN}">
                                            <td style="text-align: center">${item.quantity}</td>
                                        </c:if>
                                        <c:choose>
                                            <c:when test="${not empty account and account.roleId eq MyContanst.ROLE_ADMIN}">
                                                <c:url var="update" value="MainServlet">
                                                    <c:param name="btnAction" value="updateBook"/>
                                                    <c:param name="bookId" value="${item.bookId}"/>
                                                </c:url>
                                                <td>
                                                    <button><a href="${update}">Update</a></button> 
                                                </td>
                                                <td>
                                                    <button onclick="Delete('${item.title}', '${item.bookId}', '${param.txtMin}', '${param.txtMax}', '${param.txtCategory}', '${param.txtSearch}')">Delete</button> 
                                                </td>
                                                <c:if test="${requestScope.bookIdSuccess == item.bookId}">
                                                    <td class="no_border">
                                                        <font style="margin-left: 5px" color="green">Update Successful</font>
                                                    </td>
                                                </c:if>
                                            </c:when>
                                            <c:otherwise>
                                                <td>
                                                    <c:url var="buy" value="MainServlet">
                                                        <c:param name="btnAction" value="addCart" />
                                                        <c:param name="bookId" value="${item.bookId}" />
                                                        <c:param name="txtMin" value="${param.txtMin}" />
                                                        <c:param name="txtMax" value="${param.txtMax}" />
                                                        <c:param name="txtCategory" value="${param.txtCategory}" />
                                                        <c:param name="txtSearch" value="${param.txtSearch}" />
                                                    </c:url> 
                                                    <button><a href="${buy}">Buy</a></button> 
                                                </td>
                                            </c:otherwise>
                                        </c:choose>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div> 
                </c:when>
                <c:otherwise>
                    <font color="red">No have book</font>
                </c:otherwise>
            </c:choose>

        </main>
        <script>
            function checkPrice() {
                const min = document.getElementById("min").value;
                const max = document.getElementById("max").value;
                let msg = document.getElementById("errorPrice");
                console.log(min + " : " + max);
                if (min === "" && max !== "") {
                    msg.innerHTML = "Min can not empty";
                    msg.style.display = "block";
                    return;
                }
                if (min !== "" && max === "") {
                    msg.innerHTML = "Max can not empty";
                    msg.style.display = "block";
                    return;
                }
                if (max < min) {
                    msg.innerHTML = "Max > Min, please";
                    msg.style.display = "block";
                    return;
                }
                msg.style.display = "none";
            }

            function Delete(title, bookId, txtMin, txtMax, txtCategory, txtSearch) {
                result = confirm("Do you want to delete " + title + "?");
                if (result) {
                    const param = "?btnAction=deleteBook&bookId=" + bookId + "&txtMin=" + txtMin + "&txtMax=" + txtMax + "&txtCategory=" + txtCategory + "&txtSearch=" + txtSearch;
                    window.location.assign("MainServlet" + param);
                }
            }
        </script>
    </body>
</html>
