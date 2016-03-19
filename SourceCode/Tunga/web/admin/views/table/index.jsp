<%@include file="../layout/header.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="roomModel" uri="/WEB-INF/tlds/roomModel" %>
<div class="page-content">
    <div class="page-header">
        <h1>
            Table
            <small>
                <i class="ace-icon fa fa-angle-double-right"></i>
                List of all table
            </small>
        </h1>
    </div>
    <div class="row">
        <div class="col-xs-12">
            <div class="table-header">
                List of all table
            </div>
            <div>
                <table id="dynamic-table" class="table table-striped table-bordered table-hover">
                    <thead>
                        <tr>
                            <th class="col-sm-1">Table ID</th>
                            <th>Table's name</th>
                            <th class="col-sm-3">Room's name</th>
                            <th class="col-sm-1">Number holders</th>
                            <th class="col-sm-2">Price</th>
                            <th class="col-sm-2"></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="table" items="${tables}">
                            <tr>
                                <td>${table.id}</td>
                                <td>${table.name}</td>
                                <td>${roomtable.name}</td>
                                <td>${table.type}</td>
                                <td class="col-sm-2">
                                    <div class="action-buttons">
                                        <a class="blue" href="table?action=view&id=${table.id}">
                                            <i class="ace-icon fa fa-search-plus bigger-130"></i>
                                        </a>
                                        <a class="green" href="table?action=edit&id=${table.id}">
                                            <i class="ace-icon fa fa-pencil bigger-130"></i>
                                        </a>
                                        <a data-bind="" class="red" href="table?action=delete&id=${table.id}">
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