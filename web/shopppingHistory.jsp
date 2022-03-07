<%-- 
    Document   : shopppingHistory
    Created on : Jun 21, 2021, 3:23:01 PM
    Author     : CND
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping History</title>
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
            .cart{
                margin: 10px 0;
            }
            .cart_total{
                border-left: 1px solid black;
                border-right: 1px solid black;
            }
        </style>
        <h1>Shopping History</h1>
        <form>
            <input type="Date" name="date" value="${param.date}" />
            <input type="text" name="bookName" value="${param.bookName}" />
            <button type="submit" name="btnAction" value="shoppingHistory">Search</button>
        <button>
            <a  href="MainServlet?btnAction=search">Shopping Page</a>
        </button>
        </form>
        <c:set var="listCartDetail" value="${requestScope.listCartDetail}"/>
        <c:if test="${not empty listCartDetail}">
            <c:forEach var="cart" items="${listCartDetail.entrySet()}" >
                <c:set var="totalPrice" value="0" />
                <div class="cart">
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>Name</th>
                                <th>Image</th>
                                <th>Author</th>
                                <th>Price</th>
                                <th>Amount</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="item" items="${cart.getValue()}" varStatus="counter">
                                <tr>
                                    <td>${counter.count}</td>
                                    <td>${item.title}</td>
                                    <td>
                                        <img src="<c:url value="/image/" />${item.image}" width="100px"/>
                                    </td>
                                    <td>${item.author}</td>
                                    <td>${item.price}</td>
                                    <td>${item.amount}</td>
                                    <c:set var="totalPrice" value="${totalPrice + item.price*item.amount}"/>
                                </tr>
                            </c:forEach>
                            <tr class="cart_total">
                                <td class="no_border" colspan="2">Checkout:</td>
                                <td class="no_border" colspan="4">${cart.key.createDate}</td>
                            </tr>
                            <tr class="cart_total">
                                <td class="no_border" colspan="2">Promotion: </td>
                                <td class="no_border" colspan="4">${cart.key.discountId}</td>
                            </tr>
                            <tr class="cart_total">
                                <c:set var="discountValue" value="0" />
                                <c:forEach var="discount" items="${requestScope.listDiscount}">
                                    <c:if test="${discount.discountId eq cart.key.discountId}">
                                        <c:set var="discountValue" value="${discount.value}" />
                                    </c:if>
                                </c:forEach>
                                <td class="no_border" colspan="2">Discount:</td>
                                <c:set var="discount" value="${totalPrice * discountValue/100}"/>
                                <td class="no_border" colspan="4">$ ${String.format("%.2f",discount)}</td>
                            </tr>
                            <tr style="border-bottom: 1px solid black;" class="cart_total">
                                <td class="no_border" colspan="2">Total Price: </td>
                                <td class="no_border" colspan="4">$ ${String.format("%.2f",totalPrice - discount)}</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </c:forEach>
        </c:if>
        <c:if test="${empty listCartDetail}">
            <font color="red">you've never shopped before, let's go buy something</font>
        </c:if>
    </body>
</html>
