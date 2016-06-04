<%@include file="../layout/header.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="page-content">

    <div class="page-header">
        <h1>
            Room
            <small>
                <i class="ace-icon fa fa-angle-double-right"></i>
                Add a new room
            </small>
        </h1>
    </div><!-- /.page-header -->

    <div class="row">
        <div class="col-xs-12">
            <!-- PAGE CONTENT BEGINS -->
            <form class="form-horizontal" method="POST" role="form" action="">
                <input type="hidden" name="action" value="add">
                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Name </label>

                    <div class="col-sm-9">
                        <input type="text" name="name" value="${room.name}" placeholder="Room's name" class="col-sm-5" />
                    </div>
                </div>
                <div class="form-group">

                    <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Type </label>
                    <div class="col-sm-9">
                        <select class="col-sm-5" name="type">
                            <option value="0" <c:if test="${!room.type}">selected</c:if>>Normal</option>
                            <option value="1" <c:if test="${room.type}">selected</c:if>>VIP</option>
                        </select>
                    </div>
                </div>

                <div class="clearfix form-actions">
                    <div class="col-md-offset-3 col-md-9">
                        <button class="btn btn-info" type="submit">
                            <i class="ace-icon fa fa-check bigger-110"></i>
                            Submit
                        </button>
                        <button class="btn btn-default btn-back" type="button">
                            <i class="ace-icon fa fa-arrow-left bigger-110"></i>
                            Back
                        </button>
                    </div>
                </div>

                <div class="hr hr-24"></div>

            </form>
        </div><!-- /.col -->
    </div><!-- /.row -->
</div><!-- /.page-content -->
<script>
    <c:if test="${not empty requestScope.message}">
    alert("<c:out value="${requestScope.message}"/>");
    </c:if>
</script>
<%@include file="../layout/footer.jsp" %>
