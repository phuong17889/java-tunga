<%-- 
    Document   : view
    Created on : Mar 20, 2016, 4:55:04 PM
    Author     : MyPC
--%>
<%@include file="../layout/header.jsp" %>
<div class="grid_9">
    <h2>${menu.name}</h2>
    <div class="menu">
        <c:forEach var="food" items="${menu.foods}">
            <div class="grid_3 alpha"> 
                <a href="food?id=${food.id}"><img src="${food.imageUrl}" alt=""></a>
                <div class="col1 upp"> <a href="food?id=${food.id}">${food.name}</a></div>
                <span> ${food.shortDescription}</span>
                <div class="price">${helper:currency(food.price)}</div>
            </div>
        </c:forEach>
    </div>
    <div class="clear"></div>
</div>
<div class="grid_3">
    <h2 class="head2">Menu list</h2>
    <ul class="list l1">
        <c:forEach var="menu" items="${menus}">
            <li><a href="menu?id=${menu.id}"> ${menu.name}</a></li>
            </c:forEach>
    </ul>
</div>
<div class="clear"></div>
<%@include file="../layout/footer.jsp" %>