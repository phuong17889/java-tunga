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
                            <th class="col-sm-1">Order ID</th>
                            <th class="col-sm-2">Order token</th>
                           
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="order" items="${invoices}">
                            <tr>
                                <td class="col-sm-1">
                                    ${order.id}
                                </td>
                                <td>${order.token}</td>
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