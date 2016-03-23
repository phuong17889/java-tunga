<%@include file="../layout/header.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="page-content">

    <div class="page-header">
        <h1>
            Table
            <small>
                <i class="ace-icon fa fa-angle-double-right"></i>
                Add a new table
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
                        <input type="text" name="name" value="${table.name}" placeholder="Table's name" class="col-sm-5" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Description </label>
                    <div class="col-sm-9">
                        <textarea name="description" placeholder="Food's description" class="col-sm-5">${food.description}</textarea>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Room </label>
                    <div class="col-sm-9">
                        <select class="col-sm-5" name="roomId">
                            <c:forEach var="room" items="${rooms}">
                                <option value="${room.id}" <c:if test="${table.roomId == room.id}">selected</c:if>>${room.name} (<c:out value="${room.type ? 'VIP' : 'Normal'}"/>)</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Number holders </label>
                    <div class="col-sm-9">
                        <input type="number" name="type" value="${table.type}" placeholder="Number holders" class="col-sm-2"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Price </label>
                    <div class="col-sm-9">
                        <input type="text" name="price" value="${table.price}" placeholder="Price" class="col-sm-2 mask-input"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Image </label>
                    <div class="col-sm-3">
                        <input type="file" id="table_image" name="image" value="${food.image}" class="file-picker"/>
                        <img src="" style="width: 100px;" id="menu_image_preview">
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
    $("#table_image").change(function () {
        var reader = new FileReader();
        reader.onload = imageIsLoaded;
        reader.readAsDataURL(this.files[0]);
    });
    function imageIsLoaded(e) {
        $('#table_image_preview').attr('src', e.target.result).attr('width', '100px');
    }
    $('.file-picker').ace_file_input({
        no_file: 'No File ...',
        btn_choose: 'Choose',
        btn_change: 'Change',
        droppable: true,
        onchange: null,
        thumbnail: 'small',
        before_change: function (files, dropped) {
            var allowed_files = [];
            for (var i = 0; i < files.length; i++) {
                var file = files[i];
                if (typeof file === "string") {
                    if (!(/\.(jpe?g|png|gif|bmp|ico)$/i).test(file)) {
                        return false;
                    }
                } else {
                    var type = $.trim(file.type);
                    if ((type.length > 0 && !(/^image\/(jpe?g|png|gif|bmp|ico)$/i).test(type)) || (type.length === 0 && !(/\.(jpe?g|png|gif|bmp|ico)$/i).test(file.name))) {
                        alert('Invalid file type.');
                        continue;
                    }
                }
                allowed_files.push(file);
            }
            if (allowed_files.length === 0) {
                return false;
            }
            return allowed_files;
        }
    });
    tinymce.init({
        selector: 'textarea',
        height: 500,
        plugins: [
            'advlist autolink lists link image charmap print preview anchor',
            'searchreplace visualblocks code fullscreen',
            'insertdatetime media table contextmenu paste code'
        ],
        toolbar: 'insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image'
    });
</script>
<%@include file="../layout/footer.jsp" %>
