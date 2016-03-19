<%@include file="../layout/header.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="page-content">
    <div class="page-header">
        <h1>
            Room
            <small>
                <i class="ace-icon fa fa-angle-double-right"></i>
                List of all room
            </small>
        </h1>
    </div>
    <div class="row">
        <div class="col-xs-12">
            <div class="table-header">
                List of all room
            </div>
            <div>
                <table id="dynamic-table" class="table table-striped table-bordered table-hover">
                    <thead>
                        <tr>
                            <th class="col-sm-1">Room ID</th>
                            <th>Room's name</th>
                            <th class="col-sm-2">Type</th>
                            <th class="col-sm-2"></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="room" items="${rooms}">
                            <tr>
                                <td>${room.id}</td>
                                <td>${room.name}</td>
                                <td><c:out value="${room.type ? 'VIP': 'Normal'}"/></td>
                                <td class="col-sm-2">
                                    <div class="action-buttons">
                                        <a class="blue" href="room?action=view&id=${room.id}">
                                            <i class="ace-icon fa fa-search-plus bigger-130"></i>
                                        </a>
                                        <a class="green" href="room?action=edit&id=${room.id}">
                                            <i class="ace-icon fa fa-pencil bigger-130"></i>
                                        </a>
                                        <a class="red" href="room?action=delete&id=${room.id}">
                                            <i class="ace-icon fa fa-trash-o bigger-130"></i>
                                        </a>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<script>
    var oTable1 = $('#dynamic-table').dataTable({
        bAutoWidth: false,
        "aoColumns": [
            null, null, null,
            {"bSortable": false}
        ]
    });
    $(document).on("click", "a.red", function () {
        var conf = confirm("Are you sure?");
        return conf;
    });
    <c:if test="${not empty requestScope.message}">
    alert("<c:out value="${requestScope.message}"/>");
    </c:if>
</script>
<%@include file="../layout/footer.jsp" %>