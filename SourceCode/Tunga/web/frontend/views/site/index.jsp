<%-- 
    Document   : index
    Created on : Mar 17, 2016, 1:59:27 AM
    Author     : MyPC
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                            <input type="text" class="col-sm-12 form-control-static" placeholder="Your Name">
                        </div>
                        <div class="form-group col-sm-7">
                            <span class="form-control-feedback" aria-hidden="true"><i class="fa fa-envelope fa-2x"></i></span>
                            <input type="text" class="col-sm-12 form-control-static" placeholder="Your E-MAIL">
                        </div>
                    </div>
                    <div>
                        <div class="form-group col-sm-4">
                            <span class="form-control-feedback" aria-hidden="true"><i class="fa fa-phone fa-2x"></i></span>
                            <input type="text" class="col-sm-12 form-control-static" placeholder="Your Phone">
                        </div>
                        <div class="form-group col-sm-4">
                            <span class="form-control-feedback" aria-hidden="true"><i class="fa fa-list-ol fa-2x"></i></span>
                            <input type="text" class="col-sm-12 form-control-static" placeholder="Number of guest">
                        </div>
                        <div class="form-group col-sm-4">
                            <span class="form-control-feedback" aria-hidden="true"><i class="fa fa-calendar fa-2x"></i></span>
                            <input type="text" class="col-sm-12 form-control-static" placeholder="09-06-2014">
                        </div>
                    </div>
                    <button class="btn btn-success booknow"> BOOK NOW </button>
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
                                <div><a href="food?id=${food.id}"><img src="${food.image}" alt=""></a>
                                    <div class="col1 upp"> <a href="food?id=${food.id}">${food.name}</a></div>
                                    <span> ${food.description}</span>
                                    <div class="price">${food.price}$</div>
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
                width: 240,
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
</script>
<%@include file="../layout/footer.jsp" %>