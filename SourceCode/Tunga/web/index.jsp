<%-- 
    Document   : index
    Created on : Mar 17, 2016, 1:59:27 AM
    Author     : MyPC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="menumodel" uri="/WEB-INF/tlds/menuModel.tld" %>
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
    <div class="grid_12">
        <div class="car_wrap">
            <c:forEach var="menu" items="${menus}">
                <h2>${menu.name}</h2>
                <a href="#" class="prev"></a><a href="#" class="next"></a>
                <ul class="carousel1">
                    <c:forEach var="food" items="${menumodel:getFoods(menu.id)}">
                        <li>
                            <div><img src="${food.image}" alt="">
                                <div class="col1 upp"> <a href="#">${food.name}</a></div>
                                <span> Dorem ipsum dolor amet consectetur</span>
                                <div class="price">${food.price}$</div>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
            </c:forEach>
        </div>
    </div>
    <div class="clear"></div>
</div>
<script>
    $(window).load(function () {
        $('.slider')._TMS({
            show: 0,
            pauseOnHover: false,
            prevBu: '.prev',
            nextBu: '.next',
            playBu: false,
            duration: 800,
            preset: 'fade',
            pagination: true, //'.pagination',true,'<ul></ul>'
            pagNums: false,
            slideshow: 8000,
            numStatus: false,
            banners: false,
            waitBannerAnimation: false,
            progressBar: false
        })
    });
    $(window).load(function () {
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
    });
</script>
<%@include file="footer.jsp" %>