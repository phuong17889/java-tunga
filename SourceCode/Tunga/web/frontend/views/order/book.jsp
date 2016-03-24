<%-- 
    Document   : book
    Created on : Mar 23, 2016, 11:38:12 PM
    Author     : notte
--%>
<%@include file="../layout/header.jsp" %>
<div class="table-reserved">
    <div class="row">
        <div class="col-sm-12">
            <c:forEach var="room" items="${rooms}">
                <c:if test="${room.tables != null}">
                    <div class="row">
                        <div class="col-sm-12">
                            <h2 class="head2">${room.name} (${room.type ? "VIP" : "Normal"})</h2>
                            <c:forEach var="table" items="${room.getFreeTables(sessionScope.book)}">
                                <div class="news"> <img src="${table.imageUrl}" alt="" class="img_inner fleft">
                                    <div class="extra_wrapper">
                                        <div class="col1">${table.name}</div>
                                        <p>${table.description}</p>
                                        <a href="#" class="btn">Book this table</a> </div>
                                </div>
                            </c:forEach>
                            <hr>
                        </div>
                    </div>
                </c:if>
            </c:forEach>
        </div>
    </div>
</div>
<%@include file="../layout/footer.jsp" %>
