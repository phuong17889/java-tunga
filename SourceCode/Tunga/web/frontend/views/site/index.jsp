<%-- 
    Document   : index
    Created on : Mar 17, 2016, 1:59:27 AM
    Author     : MyPC
--%>

<%@include file="../layout/header.jsp" %>
<div class="container">
    <div class="grid_12">
        <div class="bookonlinewrapper">
            <div class="container col-sm-12" id="bookonline">
                <h2 class="wow fadeInUp"> Book online</h2>
                <form class="form-inline" action="order" method="POST">
                    <input type="hidden" name="action" value="book">
                    <div>
                        <div class="form-group col-sm-5">
                            <span class="form-control-feedback" aria-hidden="true"><i class="fa fa-user fa-2x"></i></span>
                            <input name="name" type="text" class="col-sm-12 form-control-static" placeholder="Your Name">
                        </div>
                        <div class="form-group col-sm-7">
                            <span class="form-control-feedback" aria-hidden="true"><i class="fa fa-envelope fa-2x"></i></span>
                            <input name="email" type="email" class="col-sm-12 form-control-static" placeholder="Your E-MAIL">
                        </div>
                    </div>
                    <div>
                        <div class="form-group col-sm-4">
                            <span class="form-control-feedback" aria-hidden="true"><i class="fa fa-phone fa-2x"></i></span>
                            <input name="phone" type="text" class="col-sm-12 form-control-static number-only" placeholder="Your Phone">
                        </div>
                        <div class="form-group col-sm-4">
                            <span class="form-control-feedback" aria-hidden="true"><i class="fa fa-list-ol fa-2x"></i></span>
                            <input name="number" type="text" class="col-sm-12 form-control-static number-only" placeholder="Number of guest">
                        </div>
                        <div class="form-group col-sm-4">
                            <span class="form-control-feedback" aria-hidden="true"><i class="fa fa-calendar fa-2x"></i></span>
                            <input name="date" type="text" class="col-sm-12 form-control-static date-picker" placeholder="${today}">
                        </div>
                    </div>
                        <button class="btn btn-success booknow" type="submit"> BOOK NOW </button>
                </form>
            </div>
        </div>
    </div>
    <div class="clear"></div>
    <div class="grid_12">
        <div class="hor_separator"></div>
    </div>
    <c:forEach var="menu" items="${menus}">
        <c:if test="${menu.foods != null}">
            <div class="grid_12">
                <div class="car_wrap">
                    <h2>${menu.name}</h2>
                    <a href="javascript://" id="prev${menu.id}" class="prev"></a><a href="javascript://" id="next${menu.id}" class="next"></a>
                    <ul id="carousel${menu.id}" class="carousel1">
                        <c:forEach var="food" items="${menu.foods}">
                            <li>
                                <div><a href="food?id=${food.id}"><img src="${food.imageUrl}" alt=""></a>
                                    <div class="col1 upp"> <a href="food?id=${food.id}">${food.name}</a></div>
                                    <span> ${food.description}</span>
                                    <div class="price">${helper:currency(food.price)}</div>
                                </div>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </c:if>
    </c:forEach>
    <div class="clear"></div>
</div>
<script>
    $(function () {
    <c:forEach var="menu" items="${menus}">
        $('#carousel${menu.id}').carouFredSel({
            auto: false,
            prev: '#prev${menu.id}',
            next: '#next${menu.id}',
            width: 960,
            items: {
                visible: {
                    min: 1,
                    max: 4
                },
                height: 'auto',
                width: 240
            },
            responsive: false,
            scroll: 1,
            mousewheel: false,
            swipe: {
                onMouse: false,
                onTouch: false
            }
        });
    </c:forEach>
    });
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
        autoclose: true,
        todayHighlight: true
    });
</script>
<%@include file="../layout/footer.jsp" %>