<%-- 
    Document   : index
    Created on : Mar 21, 2016, 12:35:22 AM
    Author     : MyPC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="helper" uri="/WEB-INF/tlds/helper" %>
<!DOCTYPE html>
<%@include file="../layout/header.jsp" %>
<div class="page-content">
    <div class="page-header">
        <h1>
            Welcome to admin control panel!
        </h1>
    </div>
    <div class="col-sm-12">
        <div class="row">
            <div class="row">
                <div class="col-sm-5">
                    <div class="widget-box transparent">
                        <div class="widget-header widget-header-flat">
                            <h4 class="widget-title lighter">
                                <i class="ace-icon fa fa-star orange"></i>
                                New order
                            </h4>

                            <div class="widget-toolbar">
                                <a href="#" data-action="collapse">
                                    <i class="ace-icon fa fa-chevron-up"></i>
                                </a>
                            </div>
                        </div>

                        <div class="widget-body" style="display: block;">
                            <div class="widget-main no-padding">
                                <div class="comments">
                                    <div class="first"></div>
                                    <c:forEach var="invoice" items="${requestScope.list}">
                                        <%@include file="_order.jsp" %>
                                    </c:forEach>
                                </div>
                            </div><!-- /.widget-main -->
                        </div><!-- /.widget-body -->
                    </div><!-- /.widget-box -->
                </div><!-- /.col -->

                <div class="col-sm-7">
                    <div class="widget-box transparent">
                        <div class="widget-header widget-header-flat">
                            <h4 class="widget-title lighter">
                                <i class="ace-icon fa fa-signal"></i>
                                Sale Stats
                            </h4>

                            <div class="widget-toolbar">
                                <a href="#" data-action="collapse">
                                    <i class="ace-icon fa fa-chevron-up"></i>
                                </a>
                            </div>
                        </div>

                        <div class="widget-body">
                            <div class="widget-main padding-4">
                                <div id="sales-charts"></div>
                            </div><!-- /.widget-main -->
                        </div><!-- /.widget-body -->
                    </div><!-- /.widget-box -->
                </div><!-- /.col -->
            </div>
        </div>
    </div>
</div>
<script src="${themeUrl}/assets/js/highcharts.js"></script>
<script src="${themeUrl}/assets/js/exporting.js"></script>
<script>
    $('#sales-charts').highcharts({
        title: {
            text: '',
            x: -20 //center
        },
        xAxis: {
            categories: [${requestScope.categories}]
        },
        yAxis: {
            title: {
                text: 'Invoice number'
            },
            plotLines: [{
                    value: 0,
                    width: 1,
                    color: '#808080'
                }]
        },
        tooltip: {
            valueSuffix: ''
        },
        legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'middle',
            borderWidth: 0
        },
        series: [{
                name: 'Food ordered',
                data: [${requestScope.foodOrdered}]
            }, {
                name: 'Table booked',
                data: [${requestScope.tableBooked}]
            }]
    });
    var socket = new WebSocket("${helper:socketUrl()}/websocket");
    socket.onmessage = onMessage;
    function onMessage(event) {
        var invoice = JSON.parse(event.data);
        if (invoice.action === "add" && invoice.notify === 1) {
            $.ajax({
                type: "POST",
                cache: false,
                url: "index?action=newOrder",
                data: "id=" + invoice.id,
                success: function (response) {
                    if ($(".comments").find(".itemdiv.commentdiv").length === 10) {
                        $(".comments").find(".itemdiv.commentdiv:last").slideUp("slow", function(){
                            $(this).remove();
                        });
                    }
                    $(response).hide().appendTo(".comments .first").slideDown("slow");
                }
            });
            return false;
        }
    }
</script>
<%@include file="../layout/footer.jsp" %>