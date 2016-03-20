<%@include file="../layout/header.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="helper" uri="/WEB-INF/tlds/helper" %>
<div class="page-content">
    <div class="page-header">
        <h1>
            Food
            <small>
                <i class="ace-icon fa fa-angle-double-right"></i>
                Update an existing food
            </small>
        </h1>
    </div><!-- /.page-header -->

    <div class="row">
        <div class="col-xs-12">
            <!-- PAGE CONTENT BEGINS -->
            <form class="form-horizontal" method="POST" role="form" action="" enctype="multipart/form-data">
                <div class="form-group">

                    <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Menu </label>
                    <div class="col-sm-9">
                        <select class="col-sm-5" name="menuId" disabled>
                            <c:forEach var="menu" items="${menus}">
                                <option value="${menu.id}" <c:if test="${food.menuId == menu.id}">selected</c:if>>${menu.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Name </label>

                    <div class="col-sm-9">
                        <input readonly="readonly" type="text" name="name" value="${food.name}" placeholder="Food's name" class="col-sm-5" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Description </label>

                    <div class="col-sm-9">
                        <textarea readonly="readonly" name="description" placeholder="Food's description" class="col-sm-5">${food.description}</textarea>
                    </div>
                </div>
                <div class="form-group">

                    <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Price </label>
                    <div class="col-sm-9">
                        <input readonly="readonly" type="text" name="price" value="${food.price}" placeholder="Price" class="col-sm-2 mask-input"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Image </label>
                    <div class="col-sm-3">
                        <img src="${helper:baseUrl()}/uploads/${food.image}" style="width: 100px;" id="menu_image_preview">
                    </div>
                </div>

                <div class="clearfix form-actions">
                    <div class="col-md-offset-3 col-md-9">
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
<%@include file="../layout/footer.jsp" %>