<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<li class="">
    <a href="index.html">
        <i class="menu-icon fa fa-tachometer"></i>
        <span class="menu-text"> Dashboard </span>
    </a>

    <b class="arrow"></b>
</li>

<li class="<c:if test="${requestScope.servlet == 'menu'}">active</c:if>">
    <a href="#" class="dropdown-toggle">
        <i class="menu-icon fa fa-desktop"></i>
        <span class="menu-text">
            Menu
        </span>
        <b class="arrow fa fa-angle-down"></b>
    </a>
    <b class="arrow"></b>
    <ul class="submenu">
        <li class="<c:if test="${requestScope.route == 'menu/add'}">active</c:if>">
            <a href="menu?action=add">
                <i class="menu-icon fa fa-caret-right"></i>
                Add new menu
            </a>
            <b class="arrow"></b>
        </li>
        <li class="<c:if test="${requestScope.route == 'menu/index'}">active</c:if>">
        <a href="menu?action=index">
            <i class="menu-icon fa fa-caret-right"></i>
            List menu
        </a>
        <b class="arrow"></b>
        </li>
    </ul>
</li>
<li class="<c:if test="${requestScope.servlet == 'food'}">active</c:if>">
    <a href="#" class="dropdown-toggle">
        <i class="menu-icon fa fa-desktop"></i>
        <span class="menu-text">
            Food
        </span>
        <b class="arrow fa fa-angle-down"></b>
    </a>
    <b class="arrow"></b>
    <ul class="submenu">
        <li class="<c:if test="${requestScope.route == 'food/add'}">active</c:if>">
            <a href="food?action=add">
                <i class="menu-icon fa fa-caret-right"></i>
                Add new food
            </a>
            <b class="arrow"></b>
        </li>
        <li class="<c:if test="${requestScope.route == 'food/index'}">active</c:if>">
        <a href="food?action=index">
            <i class="menu-icon fa fa-caret-right"></i>
            List food
        </a>
        <b class="arrow"></b>
        </li>
    </ul>
</li>
