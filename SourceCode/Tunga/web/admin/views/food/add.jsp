<%@include file="../layout/header.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="page-content">

    <div class="page-header">
        <h1>
            Menu
            <small>
                <i class="ace-icon fa fa-angle-double-right"></i>
                Add a new menu
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
                        <input type="text" id="form-field-1" name="name" value="${name}" placeholder="Food's name" class="col-sm-5" />
                    </div>
                </div>
                <div class="form-group">

                    <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Menu </label>
                    <div class="col-sm-9">
                        <select  id="form-field-1" name="menu_id">
                            <c:forEach var="menu" items="${menus}">
                                <option value="${menu.id}">${menu.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">

                    <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Price </label>
                    <div class="col-sm-9">
                        <input type="text" id="form-field-1" name="price" value="${price}" placeholder="Price" class="col-sm-5"/>
                    </div>
                </div>
                <div class="form-group">

                    <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Image </label>
                    <div class="col-sm-9">
                        <input type="text" id="form-field-1" name="image" value="${image}" class="sol-sm-5"/>
                    </div>
                </div>
               
                <div class="clearfix form-actions">
                    <div class="col-md-offset-3 col-md-9">
                        <button class="btn btn-info" type="submit">
                            <i class="ace-icon fa fa-check bigger-110"></i>
                            Submit
                        </button>
                    </div>
                </div>

                <div class="hr hr-24"></div>

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
