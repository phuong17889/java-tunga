<%@include file="../layout/header.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="page-content">
    <div class="page-header">
        <h1>
            Order
            <small>
                <i class="ace-icon fa fa-angle-double-right"></i>
                List of all order
            </small>
        </h1>
    </div>
    <div class="row">
        <div class="col-xs-12">
            <div class="table-header">
                List of all Order   
            </div>
            <div>
                <table id="dynamic-table" class="table table-striped table-bordered table-hover">
                    <thead>
                        <tr>
                            <th class="col-sm-1">Menu ID</th>
                            <th class="col-sm-2">Menu Name</th>
                            <th class="col-sm-2">Name</th>
                             <th class="col-sm-3">Address</th>
                             <th class="col-sm-3">Date Time</th>
                            <th class="col-sm-3">Order</th>
                           
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="order" items="${invoices}">
                            <tr>
                                <td class="col-sm-1">
                                    ${order.id}
                                </td>
                                <td>${order.name}</td>
                                <td class="col-sm-3">${menu.order}</td>
                                <td class="col-sm-2">
                                    <div class="action-buttons">
                                        <a class="blue" href="order?action=view&id=${order.id}">
                                            <i class="ace-icon fa fa-search-plus bigger-130"></i>
                                        </a>

                                        <a class="green" href="order?action=edit&id=${order.id}">
                                            <i class="ace-icon fa fa-pencil bigger-130"></i>
                                        </a>

                                        <a class="red" href="order?action=delete&id=${order.id}">
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