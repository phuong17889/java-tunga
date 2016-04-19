<%-- 
    Document   : book
    Created on : Mar 23, 2016, 11:38:12 PM
    Author     : notte
--%>
<%@include file="../layout/header.jsp" %>
<div class="table-reserved">
    <div class="row">
        <div class="col-sm-12">
            <c:set var="countTable" value="0" scope="session"/>
            <c:forEach var="room" items="${rooms}">
                <c:if test="${room.getFreeTables(sessionScope.book) != null}">
                    <div class="row">
                        <div class="col-sm-12">
                            <h2 class="head2">${room.name} (${room.type ? "VIP" : "Normal"})</h2>
                            <c:forEach var="table" items="${room.getFreeTables(sessionScope.book)}">
                                <div class="news row"> 
                                    <img src="${table.imageUrl}" alt="" class="img_inner fleft table-image">
                                    <div class="extra_wrapper">
                                        <div class="col1 table-name">${table.name}</div>
                                        <p>${table.description}</p>
                                        <a href="table?action=book&id=${table.id}" class="btn">Book this table</a> </div>
                                </div>
                                <c:set var="countTable" value="${countTable + 1}" scope="session"/>
                            </c:forEach>
                            <hr>
                        </div>
                    </div>
                </c:if>
            </c:forEach>
            <c:if test="${countTable == 0}">
                <h1 class="no-table">No tables available</h1>
            </c:if>
        </div>
    </div>
</div>
<%@include file="../layout/footer.jsp" %>
