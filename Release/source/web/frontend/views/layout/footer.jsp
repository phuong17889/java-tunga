<%-- 
    Document   : footer
    Created on : Mar 17, 2016, 2:11:10 AM
    Author     : MyPC
--%>
<div class="bottom_block">
</div>
<div class="clear"></div>
</div>
</div>
</div>
<div class="modal fade" tabindex="-1" role="dialog" id="table-reserve-popup">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-body">
                <div class="grid_12">
                    <div class="bookonlinewrapper">
                        <div class="container col-sm-12" id="bookonline">
                            <h2 class="wow fadeInUp"> Book online</h2>
                            <form class="form-inline" action="table" method="POST">
                                <input type="hidden" name="action" value="list">
                                <div>
                                    <div class="form-group col-sm-5">
                                        <span class="form-control-feedback" aria-hidden="true"><i class="fa fa-list-ol fa-2x"></i></span>
                                        <input name="number" required type="text" class="col-sm-12 form-control-static number-only" placeholder="Guest number">
                                    </div>
                                    <div class="form-group col-sm-4">
                                        <span class="form-control-feedback" aria-hidden="true"><i class="fa fa-calendar fa-2x"></i></span>
                                        <input name="date" required type="text" class="col-sm-12 form-control-static date-picker" placeholder="${today}">
                                    </div>
                                    <div class="form-group col-sm-3">
                                        <span class="form-control-feedback" aria-hidden="true"><i class="fa fa-clock-o fa-2x"></i></span>
                                        <input maxlength="4" name="time" required type="text" class="col-sm-12 form-control-static time-picker" placeholder="12:00">
                                    </div>
                                </div>
                                <button class="btn btn-success booknow" type="submit"> choose a table now </button>
                            </form>
                        </div>        
                    </div>
                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<footer>
    <div class="container_12">
        <div class="grid_12"> Tunga Traditional Restaurant &copy; 2016 | Powered by: Group 1 </div>
        <div class="clear"></div>
    </div>
</footer>
<script>
    var m_names = new Array("Jan", "Feb", "Mar",
            "Apr", "May", "Jun", "Jul", "Aug", "Sep",
            "Oct", "Nov", "Dec");
    var d = new Date();
    var curr_date = d.getDate();
    var curr_month = d.getMonth();
    var curr_year = d.getFullYear();
    $(".number-only").keydown(function (e) {
        if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 || (e.keyCode === 65 && (e.ctrlKey === true || e.metaKey === true)) || (e.keyCode >= 35 && e.keyCode <= 40)) {
            return;
        }
        if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
            e.preventDefault();
        }
    });
    $('.date-picker').datepicker({
        orientation: "bottom left",
        format: "M dd, yyyy",
        startDate: m_names[curr_month] + " " + curr_date + ", " + curr_year,
        autoclose: true,
        todayHighlight: true
    });
    $('.time-picker').timepicker({
        defaultTime: 'current',
        disableFocus: false,
        disableMousewheel: false,
        isOpen: false,
        minuteStep: 15,
        modalBackdrop: false,
        orientation: {x: 'auto', y: 'auto'},
        secondStep: 15,
        snapToStep: false,
        showSeconds: false,
        showInputs: true,
        showMeridian: true,
        template: 'dropdown',
        appendWidgetTo: 'body',
        showWidgetOnAddonClick: true,
        icons: {
            up: 'glyphicon glyphicon-chevron-up',
            down: 'glyphicon glyphicon-chevron-down'
        },
        maxHours: 24,
        explicitMode: false
    });
</script>
</body>
</html>