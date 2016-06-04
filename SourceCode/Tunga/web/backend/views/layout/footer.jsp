</div>
</div>
<div class="footer">
    <div class="footer-inner">
        <div class="footer-content">
            <span class="bigger-120">
                <span class="blue bolder">Tunga</span>
                Restaurant &copy; 2016
            </span>
        </div>
    </div>
</div>
<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
    <i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
</a>
</div>
<script>
    $(document).on("click", ".btn.btn-back", function () {
        window.history.back();
    });
    $(".mask-input").inputmask('$ 999,999,999.99', {
        numericInput: true
    });
    var ucFirst = function (string) {
        return string.charAt(0).toUpperCase() + string.slice(1);
    };
    $.each($(".breadcrumb").find("li span"),function(){
        var text = $(this).text();
        $(this).text(ucFirst(text));
    });
</script>
</body>
</html>
