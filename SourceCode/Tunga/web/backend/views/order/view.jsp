<%@include file="../layout/header.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="page-content">
    <div class="row">
        <div class="col-xs-12">
            <!-- PAGE CONTENT BEGINS -->
            <div class="space-6"></div>

            <div class="row">
                <div class="col-sm-10 col-sm-offset-1">
                    <div class="widget-box transparent">
                        <div class="widget-header widget-header-large">
                            <h3 class="widget-title grey lighter">
                                <i class="ace-icon fa fa-leaf green"></i>
                                Customer Invoice ${order.id}
                            </h3>

                            <div class="widget-toolbar no-border invoice-info">
                                <span class="invoice-info-label">Invoice token:</span>
                                <span class="red">#${order.token}</span>

                                <br />
                                <span class="invoice-info-label">Date:</span>
                                <span class="blue">${order.createdAt}</span>
                            </div>

                            <div class="widget-toolbar hidden-480">
                                <a href="#">
                                    <i class="ace-icon fa fa-print"></i>
                                </a>
                            </div>
                        </div>

                        <div class="widget-body">
                            <div class="widget-main padding-24">
                                <div class="row">
                                    <div class="col-sm-6">
                                        <div class="row">
                                            <div class="col-xs-11 label label-lg label-success arrowed-in arrowed-right">
                                                <b>Order Info</b>
                                            </div>
                                        </div>

                                        <div>
                                            <ul class="list-unstyled  spaced">
                                                <li>
                                                    <i class="ace-icon fa fa-caret-right green"></i>Invoice No.: <strong>${order.id}</strong>
                                                </li>

                                                <li>
                                                    <i class="ace-icon fa fa-caret-right green"></i>Invoice token: <strong>${order.token}</strong>
                                                </li>

                                                <li>
                                                    <i class="ace-icon fa fa-caret-right green"></i>Invoice status: <strong>${order.statusText}</strong>
                                                </li>

                                                <li>
                                                    <i class="ace-icon fa fa-caret-right green"></i>Ordered Date: <strong>${order.createdAt}</strong>
                                                </li>
                                            </ul>
                                        </div>
                                    </div><!-- /.col -->
                                    <div class="col-sm-6">
                                        <div class="row">
                                            <div class="col-xs-11 label label-lg label-info arrowed-in arrowed-right">
                                                <b>Customer Info</b>
                                            </div>
                                        </div>

                                        <div>
                                            <ul class="list-unstyled spaced">
                                                <li>
                                                    <i class="ace-icon fa fa-caret-right blue"></i>Full name: <strong>${order.fullName}</strong>
                                                </li>

                                                <li>
                                                    <i class="ace-icon fa fa-caret-right blue"></i>Address: <strong>${order.address}</strong>
                                                </li>

                                                <li>
                                                    <i class="ace-icon fa fa-caret-right blue"></i>Email: <strong>${order.email}</strong>
                                                </li>

                                                <li>
                                                    <i class="ace-icon fa fa-caret-right blue"></i>Phone: <strong>${order.phone}</strong>
                                                </li>
                                            </ul>
                                        </div>
                                    </div><!-- /.col -->
                                </div><!-- /.row -->

                                <c:if test="${order.invoiceTable != null}">
                                    <div class="space"></div>
                                    <h3>Table reserve</h3>
                                    <div>
                                        <table class="table table-striped table-bordered">
                                            <thead>
                                                <tr>
                                                    <th class="center col-sm-1">#</th>
                                                    <th class="col-sm-2">Room's name</th>
                                                    <th class="col-sm-5">Table's name</th>
                                                    <th class="right col-sm-2">Price</th>
                                                    <th class="right col-sm-2">Sub Total</th>
                                                </tr>
                                            </thead>

                                            <tbody>
                                                <tr>
                                                    <td class="center">1</td>
                                                    <td>${order.invoiceTable.table.room.name}</td>
                                                    <td>${order.invoiceTable.table.name}</td>
                                                    <td class="right">${helper:currency(order.invoiceTable.price)}</td>
                                                    <td class="right">${helper:currency(order.invoiceTable.price)}</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </c:if>
                                <c:if test="${order.invoiceFoods != null}">
                                    <div class="space"></div>
                                    <h3>Food order</h3>
                                    <div>
                                        <table class="table table-striped table-bordered">
                                            <thead>
                                                <tr>
                                                    <th class="center col-sm-1">#</th>
                                                    <th class="col-sm-2">Menu's name</th>
                                                    <th class="col-sm-4">Food's name</th>
                                                    <th class="center col-sm-1">Quantity</th>
                                                    <th class="right col-sm-2">Price</th>
                                                    <th class="right col-sm-2">Sub Total</th>
                                                </tr>
                                            </thead>

                                            <tbody>
                                                <c:forEach var="invoiceFood" items="${order.invoiceFoods}">
                                                    <tr>
                                                        <td class="center">1</td>
                                                        <td>${invoiceFood.food.menu.name}</td>
                                                        <td>${invoiceFood.food.name}</td>
                                                        <td class="center">${invoiceFood.quantity}</td>
                                                        <td class="right">${helper:currency(invoiceFood.food.price)}</td>
                                                        <td class="right">${helper:currency((invoiceFood.food.price*invoiceFood.quantity))}</td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </c:if>
                                <div class="hr hr8 hr-double hr-dotted"></div>

                                <div class="row">
                                    <div class="col-sm-5 pull-right">
                                        <h4 class="pull-right">
                                            Total amount :
                                            <span class="red">$${order.total}</span>
                                        </h4>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-sm-10 col-sm-offset-1">
                    <div class="center">
                        <a class="btn btn-update btn-danger" ${(order.status == 0 || order.status == 3) ? "disabled" : ""} href="order?action=update&status=0&id=${order.id}">Cancel this invoice</a>
                        <a class="btn btn-update btn-primary" ${order.status == 1 ? "" : "disabled"} href="order?action=update&status=2&id=${order.id}">Mark as Shipped</a>
                        <c:choose>
                            <c:when test="${order.invoiceTable != null}">
                                <a class="btn btn-update btn-success" ${(order.status == 0 || order.status == 3) ? "disabled":""} href="order?action=update&status=3&id=${order.id}">Mark as Completed</a>
                            </c:when>
                            <c:otherwise>
                                <a class="btn btn-update btn-success" ${(order.status == 0 || order.status == 3) ? "disabled":""} href="order?action=update&status=3&id=${order.id}">Mark as Delivered</a>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
            <!-- PAGE CONTENT ENDS -->
        </div><!-- /.col -->
    </div><!-- /.row -->
</div><!-- /.page-content -->
<script>
    <c:if test="${not empty sessionScope.message}">
    alert("<c:out value="${sessionScope.message}"/>");
        <c:remove var="message" scope="session"/>
    </c:if>
    $(document).on("click", ".btn-update", function () {
        var conf = confirm("Are you sure?");
        return conf;
    });
</script>
<%@include file="../layout/footer.jsp" %>