<%@include file="../layout/header.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="page-content">
    <div class="page-header">
        <h1>
            Menu
            <small>
                <i class="ace-icon fa fa-angle-double-right"></i>
                View an existing menu
            </small>
        </h1>
    </div><!-- /.page-header -->

    <div class="row">
        <div class="col-xs-12">
            <!-- PAGE CONTENT BEGINS -->
            <form class="form-horizontal" method="POST" role="form" action="">
                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Name </label>

                    <div class="col-sm-9">
                        <input readonly="readonly" type="text" id="form-field-1" name="name" value="${menu.name}" placeholder="Menu's name" class="col-sm-5" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Order </label>

                    <div class="col-sm-9">
                        <input readonly="readonly" type="number" id="form-field-1" name="order" value="${menu.order}" class="col-sm-2" />
                    </div>
                </div>

                <div class="hr hr-24"></div>
                <div class="clearfix form-actions">
                    <div class="col-md-offset-3 col-md-9">
                        <button class="btn btn-default btn-back" type="button">
                            <i class="ace-icon fa fa-arrow-left bigger-110"></i>
                            Back
                        </button>
                    </div>
                </div>
            </form>
        </div><!-- /.col -->
    </div><!-- /.row -->
</div><!-- /.page-content -->
<script>
    <c:if test="${not empty sessionScope.message}">
    alert("<c:out value="${sessionScope.message}"/>");
        <c:remove var="message" scope="session"/>
    </c:if>
</script>
<%@include file="../layout/footer.jsp" %>