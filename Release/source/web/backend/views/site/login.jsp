<%-- 
    Document   : login
    Created on : Mar 16, 2016, 8:48:00 PM
    Author     : notte
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
        <meta charset="utf-8" />
        <title>Login | Tunga Restaurant</title>

        <meta name="description" content="User login page" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
        <link rel="stylesheet" href="${themeUrl}/assets/css/bootstrap.min.css" />
        <link rel="stylesheet" href="${themeUrl}/assets/font-awesome/4.2.0/css/font-awesome.min.css" />
        <link rel="stylesheet" href="${themeUrl}/assets/fonts/fonts.googleapis.com.css" />
        <link rel="stylesheet" href="${themeUrl}/assets/css/ace.min.css" />
        <link rel="stylesheet" href="${themeUrl}/assets/css/ace-rtl.min.css" />
    </head>

    <body class="login-layout">
        <div class="main-container">
            <div class="main-content">
                <div class="row">
                    <div class="col-sm-10 col-sm-offset-1">
                        <div class="login-container">
                            <div class="center">
                                <h1>
                                    <i class="ace-icon fa fa-leaf green"></i>
                                    <span class="red">Tunga</span>
                                    <span class="white" id="id-text2">Restaurant</span>
                                </h1>
                            </div>

                            <div class="space-6"></div>

                            <div class="position-relative">
                                <div id="login-box" class="login-box visible widget-box no-border">
                                    <div class="widget-body">
                                        <div class="widget-main">
                                            <h4 class="header blue lighter bigger">
                                                <i class="ace-icon fa fa-coffee green"></i>
                                                Please Enter Your Information
                                            </h4>

                                            <div class="space-6"></div>

                                            <form action="" method="POST">
                                                <fieldset>
                                                    <label class="block clearfix">
                                                        <span class="block input-icon input-icon-right">
                                                            <input type="text" class="form-control" name="username" placeholder="Username" />
                                                            <i class="ace-icon fa fa-user"></i>
                                                        </span>
                                                    </label>

                                                    <label class="block clearfix">
                                                        <span class="block input-icon input-icon-right">
                                                            <input type="password" class="form-control" name="password" placeholder="Password" />
                                                            <i class="ace-icon fa fa-lock"></i>
                                                        </span>
                                                    </label>

                                                    <div class="space"></div>

                                                    <div class="clearfix">
                                                        <label class="inline">
                                                            <input type="checkbox" class="ace" />
                                                            <span class="lbl"> Remember Me</span>
                                                        </label>

                                                        <button type="submit" class="width-35 pull-right btn btn-sm btn-primary">
                                                            <i class="ace-icon fa fa-key"></i>
                                                            <span class="bigger-110">Login</span>
                                                        </button>
                                                    </div>

                                                    <div class="space-4"></div>
                                                </fieldset>
                                            </form>
                                        </div><!-- /.widget-main -->
                                    </div><!-- /.widget-body -->
                                </div><!-- /.login-box -->
                            </div><!-- /.position-relative -->
                        </div>
                    </div><!-- /.col -->
                </div><!-- /.row -->
            </div><!-- /.main-content -->
        </div><!-- /.main-container -->
        <script src="${themeUrl}/assets/js/jquery.2.1.1.min.js"></script>
        <script type="text/javascript">
            if ('ontouchstart' in document.documentElement)
                document.write("<script src='${themeUrl}/assets/js/jquery.mobile.custom.min.js'>" + "<" + "/script>");
        </script>
        <script>
            <c:if test="${not empty requestScope.error}">
            alert("<c:out value="${requestScope.error}"/>");
            </c:if>
        </script>
    </body>
</html>
