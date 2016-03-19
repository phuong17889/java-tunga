<%-- 
    Document   : index
    Created on : Mar 17, 2016, 1:59:27 AM
    Author     : MyPC
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="header.jsp" %>
<div class="container">
    <div class="grid_12">
        <div class="bookonlinewrapper">
            <div class="container" id="bookonline">
                <h2 class="wow fadeInUp" data-wow-delay="0.3s"> Book online</h2>
                <form >
                    <input type="text" class="name wow zoomIn" placeholder="Your Name" >
                    <input type="text" class="email wow zoomIn" placeholder="Your E-MAIL">
                    <input type="text" class="from wow zoomIn" placeholder="09-06-2014">
                    <input type="text" class="to wow zoomIn" placeholder="09-06-2014">
                    <input type="text" class="persons wow zoomIn" placeholder="Number of persons">
                    <button class="booknow wow fadeInUp"> BOOK NOW </button>
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
                    <a href="#" class="prev"></a><a href="#" class="next"></a>
                    <ul class="carousel1">
                        <c:forEach var="food" items="${menu.foods}">
                            <li>
                                <div><img src="${food.image}" alt="">
                                    <div class="col1 upp"> <a href="#">${food.name}</a></div>
                                    <span> Dorem ipsum dolor amet consectetur</span>
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
    $('.carousel1').carouFredSel({
        auto: false,
        prev: '.prev',
        next: '.next',
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
</script>
<%@include file="footer.jsp" %>