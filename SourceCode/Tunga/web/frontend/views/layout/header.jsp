<%-- 
    Document   : header
    Created on : Mar 17, 2016, 2:09:59 AM
    Author     : MyPC
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="helper" uri="/WEB-INF/tlds/helper" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Tunga Traditional Restaurant</title>
        <meta charset="utf-8">
        <link rel="icon" href="${themeUrl}/assets/images/favicon.ico">
        <link rel="shortcut icon" href="${themeUrl}/assets/images/favicon.ico">
        <link rel="stylesheet" href="${themeUrl}/assets/css/bootstrap.min.css">
        <link rel="stylesheet" href="${themeUrl}/assets/css/font-awesome.css">
        <link rel="stylesheet" href="${themeUrl}/assets/css/bootstrap-datepicker3.css">
        <link rel="stylesheet" href="${themeUrl}/assets/css/style.css">
        <link rel="stylesheet" href="${themeUrl}/assets/css/slider.css">
        <script src="${themeUrl}/assets/js/jquery.js"></script>
        <script src="${themeUrl}/assets/js/bootstrap.min.js"></script>
        <script src="${themeUrl}/assets/js/jquery-migrate-1.1.1.js"></script>
        <script src="${themeUrl}/assets/js/superfish.js"></script>
        <script src="${themeUrl}/assets/js/jquery.easing.1.3.js"></script>
        <script src="${themeUrl}/assets/js/sForm.js"></script>
        <script src="${themeUrl}/assets/js/jquery.carouFredSel-6.2.1-packed.js"></script>
        <script src="${themeUrl}/assets/js/bootstrap-datepicker.js"></script>
        <script src="${themeUrl}/assets/js/tms-0.4.1.js"></script>
    </head>
    <body>
        <div class="main">
            <header>
                <div class="container_12">
                    <div class="grid_12">
                        <h1><a href="index"><img src="${themeUrl}/assets/images/logo.png" alt=""></a></h1>
                        <div class="menu_block">
                            <nav>
                                <ul class="sf-menu">
                                    <li class="current"><a href="index">Home</a></li>
                                    <li><a href="javascript://">Food menu</a>
                                        <ul>
                                            <c:forEach var="menu" items="${menus}">
                                                <li><a href="menu?id=${menu.id}"> ${menu.name}</a></li>
                                                </c:forEach>
                                        </ul>
                                    </li>
                                    <li><a href="Book a table">Book a table</a></li>
                                    <li class="with_ul"><a href="about">About Us</a>
                                </ul>
                            </nav>
                            <div class="cart fleft relative">
                                <a href="cart" class="relative cart-info">
                                    <i class="fa fa-shopping-cart fa-2x"></i>
                                    <c:choose>
                                        <c:when test="${not empty requestScope.cart && requestScope.cart != null}">
                                            <span class="count">${cart.totalCount}</span>
                                            <p class="text">${helper:currency(cart.totalPrice)}</p>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="count">0</span>
                                            <p class="text">Empty cart!</p>
                                        </c:otherwise>
                                    </c:choose>
                                </a>
                            </div>
                            <div class="clear"></div>
                        </div>
                        <div class="clear"></div>
                    </div>
                </div>
            </header>
            <div class="content page1">
                <div class="container_12">
