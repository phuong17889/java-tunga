<%@include file="../layout/header.jsp" %>
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
        <div class="col-sm-3">
            <label for="id-date-range-picker-1">Date Range</label>
            <div class="input-group">
                <input class="form-control" type="text" name="date-range" id="id-date-range-picker-1" value="${requestScope.dateRanger}" />
                <span class="input-group-addon">
                    <i class="fa fa-calendar bigger-110"></i>
                </span>
            </div>
        </div>
    </div>
    <div class="space-10"></div>
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
                            <th class="col-sm-1">Order token</th>
                            <th class="col-sm-4">Customer's name</th>
                            <th class="col-sm-1">Type</th>
                            <th class="col-sm-1">Total</th>
                            <th class="col-sm-1">Status</th>
                            <th class="col-sm-2">Created At</th>
                            <th class="col-sm-1"></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="order" items="${invoices}">
                            <tr>
                                <td>${order.id}</td>
                                <td>${order.token}</td>
                                <td>${order.fullName}</td>
                                <td>${order.type}</td>
                                <td>${helper:currency(order.total)}</td>
                                <td>${order.statusText}</td>
                                <td>${order.createdAt}</td>
                                <td> 
                                    <div class="action-buttons">
                                        <a class="blue" href="order?action=view&id=${order.id}">
                                            <i class="ace-icon fa fa-search-plus bigger-130"></i>
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
        "order": [[0, "desc"]],
        "aoColumns": [
            null, null, null, null, null, null, null,
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
    $('#id-date-range-picker-1').daterangepicker({
        ranges: {
            'Today': [moment(), moment()],
            'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
            'Last 7 Days': [moment().subtract(6, 'days'), moment()],
            'Last 30 Days': [moment().subtract(29, 'days'), moment()],
            'This Month': [moment().startOf('month'), moment().endOf('month')],
            'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
        },
        startDate: '${requestScope.startDate}',
        endDate: '${requestScope.toDate}',
        applyClass: 'btn-sm btn-success',
        cancelClass: 'btn-sm btn-default'
    }, function (start, end, label) {
        window.location.href = "order?action=index&date=" + $("#id-date-range-picker-1").val();
    }).next().on(ace.click_event, function () {
        $(this).prev().focus();
    });
</script>
<%@include file="../layout/footer.jsp" %>