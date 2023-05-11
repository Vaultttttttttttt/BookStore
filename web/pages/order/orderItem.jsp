<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单详情</title>
	<%@include file="/pages/common/head.jsp"%>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
</style>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">这是用户代码为${requestScope.userId}为订单号为${requestScope.orderId}订单详情</span>
			<%@include file="/pages/common/login_success_menu.jsp"%>>
	</div>
	
	<div id="main">
		
		<table>
			<tr>
				<td>商品名</td>
				<td>商品单价</td>
				<td>商品数量</td>
				<td>商品总金额</td>
			</tr>		
			<c:forEach items="${requestScope.orderItems}" var="orderItem">
				<tr>
					<td>${orderItem.name}</td>
					<td>${orderItem.price}</td>
					<td>${orderItem.count}</td>
					<td>${orderItem.totalPrice}</td>
				</tr>
			</c:forEach>

		</table>
		
	
	</div>

	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>