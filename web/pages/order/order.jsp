<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>我的订单</title>
    <%@include file="/pages/common/head.jsp" %>
    <script type="text/javascript">
        $(function () {
            $("a.receiveOrder").click(function () {
                return confirm("你确定收到了订单号为" + $(this).attr("orderId") + "的货物吗？");
            })
        })
    </script>
    <style type="text/css">
        h1 {
            text-align: center;
            margin-top: 200px;
        }
    </style>

</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">我的订单</span>
    <%@include file="/pages/common/login_success_menu.jsp" %>
    >
</div>

<div id="main">

    <table>
        <tr>
            <td>日期</td>
            <td>金额</td>
            <td>状态</td>
            <td>详情</td>
            <td>选项</td>
        </tr>
        <c:forEach items="${requestScope.orders}" var="order">
            <tr>
                <td>${order.creatTime}</td>
                <td>${order.price}</td>
                <td>
                    <c:choose>
                        <c:when test="${order.status==0}">
                            未发货
                        </c:when>
                        <c:when test="${order.status==1}">
                            已发货
                        </c:when>
                        <c:when test="${order.status==2}">
                            已签收
                        </c:when>
                    </c:choose>
                </td>
                <td>
                    <a href="orderServlet?action=showOrderDetail&orderId=${order.orderId}&userId=${order.userId}">查看详情</a>
                </td>
                <td>
                    <c:if test="${order.status==0}">
                        <a>还未发货</a>
                    </c:if>
                    <c:if test="${order.status==1}">
                        <a class="receiveOrder" orderId="${order.orderId}"
                           href="orderServlet?action=receiveOrder&orderId=${order.orderId}">确认收货</a>
                    </c:if>
                    <c:if test="${order.status==2}">
                        <a>已完成该订单</a>
                    </c:if>
                </td>
            </tr>
        </c:forEach>

    </table>


</div>

<%@include file="/pages/common/footer.jsp" %>
</body>
</html>