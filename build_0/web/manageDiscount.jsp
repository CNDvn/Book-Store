<%-- 
    Document   : manageDiscount
    Created on : Jun 22, 2021, 1:01:05 AM
    Author     : CND
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Management Discount</title>
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
        <h1>Management Discount</h1>
        <button><a href="MainServlet?btnAction=search">Search Page</a></button>
        <div style="margin-bottom: 15px; width: 500px; height: 200px; padding: 10px">
            <font color="green">${requestScope.msgAddDiscountSuccess}</font>
            <font color="red">${requestScope.msgAddDiscountFail}</font>
            <form action="MainServlet" method="post">
                <table>
                    <tbody>
                        <tr>
                            <td class="no_border">Input discount code: </td>
                            <td class="no_border">
                                <input maxlength="10" type="text" name="codeDiscount" value="" required /> *
                                </br><font color="green">limit 10 character</font>
                            </td>
                        </tr>
                        <tr>
                            <td class="no_border">Input value:</td>
                            <td class="no_border"><input min="0" max="100" type="number" name="valueDiscount" value="" required /> * </td>
                        </tr>
                        <tr>
                            <td class="no_border">description:</td>
                            <td class="no_border"><textarea name="descriptionDiscount"></textarea> </td>
                        </tr>
                    </tbody>
                </table>
                <button type="submit" name="btnAction" value="addNewDiscount">Create Discount</button>
            </form>
        </div>
        <c:set var="listAccUser" value="${requestScope.listAccUser}"/>
        <c:if test="${not empty listAccUser}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>User ID</th>
                        <th>Name</th>
                        <th>Promotion</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="acc" items="${listAccUser}" varStatus="counter">
                    <form action="MainServlet" method="post">
                        <tr>
                            <td>${counter.count}</td>
                            <td>
                                ${acc.userId}
                                <input type="hidden" name="userId" value="${acc.userId}" />
                            </td>
                            <td>${acc.name}</td>
                            <td>
                                <select name="discountId">
                                    <c:forEach var="discount" items="${requestScope.listDiscount}">
                                        <option value="${discount.discountId}">${discount.discountId}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <td>
                                <button type="submit" value="addDiscountInAccount" name="btnAction">Add Discount</button>
                            </td>
                            <c:if test="${acc.userId eq requestScope.userAddDiscountFail}">
                                <td class="no_border">
                                    <font color="red">This user has already added this code discount before that</font>
                                </td>
                            </c:if>
                            <c:if test="${acc.userId eq requestScope.userAddDiscountSuccess}">
                                <td class="no_border">
                                    <font color="green">Add Successful</font>
                                </td>
                            </c:if>
                        </tr>
                    </form>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
</body>
</html>
