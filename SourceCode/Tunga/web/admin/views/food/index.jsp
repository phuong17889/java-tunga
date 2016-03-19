<%@include file="../layout/header.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="page-content">
    <div class="page-header">
        <h1>
            Food
            <small>
                <i class="ace-icon fa fa-angle-double-right"></i>
                List of all food
            </small>
        </h1>
    </div>
    <div class="row">
        <div class="col-xs-12">
            <div class="table-header">
                List of all food
            </div>
            <div>
                <table id="dynamic-table" class="table table-striped table-bordered table-hover">
                    <thead>
                        <tr>
                            <th class="col-sm-1">Food ID</th>
                            <th class="col-sm-2">Menu's name</th>
                            <th>Food's name</th>
                            <th class="col-sm-2">Image</th>
                            <th class="col-sm-2">Price</th>
                            <th class="col-sm-2"></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="food" items="${foods}">
                            <tr>
                                <td>${food.id}</td>
                                <td>${food.menuId}</td>
                                <td>${food.name}</td>
                                <td>${food.image}</td>
                                <td>${food.price}</td>
                                <td class="col-sm-2">
                                    <div class="action-buttons">
                                        <a class="blue" href="food?action=view&id=${food.id}">
                                            <i class="ace-icon fa fa-search-plus bigger-130"></i>
                                        </a>

                                        <a class="green" href="food?action=edit&id=${food.id}">
                                            <i class="ace-icon fa fa-pencil bigger-130"></i>
                                        </a>

                                        <a data-bind="" class="red" href="food?action=delete&id=${food.id}">
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
            {"bSortable": false},
            null,
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