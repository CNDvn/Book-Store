<%-- 
    Document   : cart
    Created on : Jun 18, 2021, 1:29:30 PM
    Author     : CND
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="hieubd.utils.MyContanst" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1"> <!-- Ensures optimal rendering on mobile devices. -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge" /> <!-- Optimal Internet Explorer compatibility -->
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart</title>
    </head>
    <body>
<%--        <c:if test="${not empty requestScope.confirmCheckout}">
            <script src="https://www.paypal.com/sdk/js?client-id=${MyContanst.CLIENT_ID}"> // Replace YOUR_CLIENT_ID with your sandbox client ID
            </script>
        </c:if> --%>
        <style>
            a{
                text-decoration: none;
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
            .btn_checkout{
                width: 300px;
                height: 45px;
                border-radius: 5px;
                outline: none;
                border: none;
                margin-bottom: 20px;
                background-color: #df2418bd;
                font-size: larger;
                font-weight: 700;
                cursor: pointer;
            }
        </style>
        <h1>Your Cart</h1>
        <div style="margin: 20px 0 ">
            <button><a href="MainServlet?btnAction=search">Search Page</a></button>
        </div>


        <div>
            <c:set var="cartObj" value="${sessionScope.cartObj}" />
            <c:if test="${not empty cartObj}">
                <div style="float: left; margin-right: 20px">
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>Book Name</th>
                                <th>Price</th>
                                <th>Amount</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:set var="total" value="0" />
                            <c:forEach  var="item" items="${cartObj.items.entrySet()}" varStatus="counter" >
                                <tr>
                                    <td>${counter.count}</td>
                                    <td>${item.getKey().title}</td>
                                    <td>$ ${item.getKey().price}</td>
                                    <td>
                                        <c:url var="reduce" value="MainServlet">
                                            <c:param name="btnAction" value="modifyQuantityBook" />
                                            <c:param name="bookId" value="${item.getKey().bookId}" />
                                            <c:param name="type" value="reduce" />
                                        </c:url>
                                        <button <c:if test="${item.getValue() < 2}">disabled="true"</c:if>>
                                            <c:if test="${item.getValue() < 2}">-</c:if>
                                            <c:if test="${item.getValue() > 1}"><a href="${reduce}" >-</a></c:if>
                                            </button>
                                        ${item.getValue()}
                                        <c:url var="increase" value="MainServlet">
                                            <c:param name="btnAction" value="modifyQuantityBook" />
                                            <c:param name="bookId" value="${item.getKey().bookId}" />
                                            <c:param name="type" value="increase" />
                                        </c:url>
                                        <button><a href="${increase}" >+</a></button>
                                    </td>
                                    <td>
                                        <button onclick="remove('${item.getKey().bookId}')">Remove</button>
                                    </td>
                                    <c:set var="total" value="${total + item.getKey().price * item.getValue()}"/>
                                    <td class="no_border">
                                        <c:if test="${requestScope.listBookFail.containsKey(item.getKey().bookId)}">
                                            <font color="red">in my store just have ${requestScope.listBookFail.get(item.getKey().bookId)}</font>
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div style="float: left">
                    <form action="MainServlet" method="post">
                        Promotion: <input type="text" name="codekm" value='<c:if test="${empty requestScope.msgPromotion}">${sessionScope.cartObj.discountId}</c:if>' 
                                          <c:if test='${not empty requestScope.applyCode}'>disabled="true"</c:if> /> 
                        <c:if test="${empty requestScope.applyCode}">
                            <button type="submit" name="btnAction" value="applyPromotion">Apply</button>
                        </c:if>
                        <c:if test="${not empty requestScope.msgPromotion}">
                            <font color="red">${requestScope.msgPromotion}</font>
                        </c:if>
                    </form>
                    <p>Provisional: $ ${String.format("%.2f",total)}</p>
                    <p>discount: ${ String.format("%.2f",total * (requestScope.applyCode/100))}</p>
                    <p>total: $ ${ String.format("%.2f", total - total * requestScope.applyCode/100) }</p>
                    <c:if test="${empty requestScope.confirmCheckout}">
                        <button><a href='<c:url value="MainServlet?btnAction=checkOut"/>'>Check out</a></button>
                    </c:if>
                    <c:if test="${not empty requestScope.confirmCheckout}">
                        <h4>Choose payment methods</h4>
                       <%-- <button class="btn_checkout" onclick='window.location.assign("<c:url value="MainServlet?btnAction=checkOut&checkout=thisapp"/>");'>This app</button> --%>
                        <button class="btn_checkout" onclick='window.location.assign("<c:url value="MainServlet?btnAction=checkOut&checkout=paypal&discountValue=${requestScope.applyCode}&total=${ String.format("%.2f", total - total * requestScope.applyCode/100) }"/>");'>Paypal</button>
                        <!--<div id="paypal-button-container"></div>-->
                    </c:if>
                </div>
            </c:if>
        </div>
<%--       <c:if test="${not empty requestScope.confirmCheckout}">
            <!-- Add the checkout buttons, set up the order and approve the order -->
            <script>
                paypal.Buttons({
                    createOrder: function (data, actions) {
                        return actions.order.create({
                            purchase_units: [{
                                    amount: {
                                        value: '${ String.format("%.2f", total - total * requestScope.applyCode/100) }'
                                    }
                                }]
                        });
                    },
                    onApprove: function (data, actions) {
                        return actions.order.capture().then(function (details) {
                            alert('Transaction completed by ' + details.payer.name.given_name);
                            window.location.assign("<c:url value="MainServlet"/>?btnAction=checkOut&checkout=ok");
                        });
                    }
                }).render('#paypal-button-container'); // Display payment options on your web page
            </script>
        </c:if> --%>
        <script>
            function remove(bookId) {
                result = confirm("Are you sure to delete this book");
                if (result) {
                    window.location.assign("<c:url value="MainServlet"/>?btnAction=removeBookInCart&bookId=" + bookId);
                }
            }
        </script>
    </body>
</html>
