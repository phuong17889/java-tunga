<%-- 
    Document   : header
    Created on : Mar 18, 2016, 1:59:50 PM
    Author     : MyPC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
        <meta charset="utf-8" />
        <title>${requestScope.title} | Tunga Restaurant</title>
        <meta name="description" content="Common form elements and layouts" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
        <link rel="stylesheet" href="${themeUrl}/assets/css/bootstrap.min.css" />
        <link rel="stylesheet" href="${themeUrl}/assets/font-awesome/4.2.0/css/font-awesome.min.css" />
        <link rel="stylesheet" href="${themeUrl}/assets/css/jquery-ui.custom.min.css" />
        <link rel="stylesheet" href="${themeUrl}/assets/css/jquery.gritter.min.css" />
        <link rel="stylesheet" href="${themeUrl}/assets/fonts/fonts.googleapis.com.css" />
        <link rel="stylesheet" href="${themeUrl}/assets/css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style" />
        <link rel="stylesheet" href="${themeUrl}/assets/css/style.css"/>
        <script src="${themeUrl}/assets/js/ace-extra.min.js"></script>
        <script type="text/javascript">
            if ('ontouchstart' in document.documentElement)
                document.write("<script src='${themeUrl}/assets/js/jquery.mobile.custom.min.js'>" + "<" + "/script>");
        </script>
        <script src="${themeUrl}/assets/js/jquery.2.1.1.min.js"></script>
        <script src="${themeUrl}/assets/js/bootstrap.min.js"></script>
        <script src="${themeUrl}/assets/js/jquery-ui.custom.min.js"></script>
        <script src="${themeUrl}/assets/js/jquery.ui.touch-punch.min.js"></script>
        <script src="${themeUrl}/assets/js/jquery.dataTables.min.js"></script>
        <script src="${themeUrl}/assets/js/jquery.dataTables.bootstrap.min.js"></script>
        <script src="${themeUrl}/assets/js/dataTables.tableTools.min.js"></script>
        <script src="${themeUrl}/assets/js/dataTables.colVis.min.js"></script>
        <script src="${themeUrl}/assets/js/jquery.inputmask.bundle.js"></script>
        <script src="${themeUrl}/assets/js/jquery.gritter.min.js"></script>
        <script src="${themeUrl}/assets/tinymce/tinymce.min.js"></script>
        <script src="${themeUrl}/assets/js/ace-elements.min.js"></script>
        <script src="${themeUrl}/assets/js/ace.min.js"></script>
    </head>

    <body class="no-skin">
        <div id="navbar" class="navbar navbar-default">
            <script type="text/javascript">
            try {
                ace.settings.check('navbar', 'fixed')
            } catch (e) {
            }
            </script>

            <div class="navbar-container" id="navbar-container">
                <button type="button" class="navbar-toggle menu-toggler pull-left" id="menu-toggler" data-target="#sidebar">
                    <span class="sr-only">Toggle sidebar</span>

                    <span class="icon-bar"></span>

                    <span class="icon-bar"></span>

                    <span class="icon-bar"></span>
                </button>

                <div class="navbar-header pull-left">
                    <a href="index" class="navbar-brand">
                        <small>
                            <i class="fa fa-leaf"></i>
                            Tunga Restaurant Control panel
                        </small>
                    </a>
                </div>

                <div class="navbar-buttons navbar-header pull-right" role="navigation">
                    <ul class="nav ace-nav">
                        <li class="light-blue">
                            <a data-toggle="dropdown" href="#" class="dropdown-toggle">
                                <img class="nav-user-photo" src="${themeUrl}/assets/avatars/user.jpg" alt="Jason's Photo" />
                                <span class="user-info">
                                    <small>Welcome,</small>
                                    Admin
                                </span>

                                <i class="ace-icon fa fa-caret-down"></i>
                            </a>

                            <ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
                                <li>
                                    <a href="index?action=logout">
                                        <i class="ace-icon fa fa-power-off"></i>
                                        Logout
                                    </a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div><!-- /.navbar-container -->
        </div>

        <div class="main-container" id="main-container">
            <script type="text/javascript">
                try {
                    ace.settings.check('main-container', 'fixed')
                } catch (e) {
                }
            </script>

            <div id="sidebar" class="sidebar responsive">
                <script type="text/javascript">
                    try {
                        ace.settings.check('sidebar', 'fixed')
                    } catch (e) {
                    }
                </script>

                <div class="sidebar-shortcuts" id="sidebar-shortcuts">
                    <div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
                        <button class="btn btn-success">
                            <i class="ace-icon fa fa-signal"></i>
                        </button>

                        <button class="btn btn-info">
                            <i class="ace-icon fa fa-pencil"></i>
                        </button>

                        <button class="btn btn-warning">
                            <i class="ace-icon fa fa-users"></i>
                        </button>

                        <button class="btn btn-danger">
                            <i class="ace-icon fa fa-cogs"></i>
                        </button>
                    </div>

                    <div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
                        <span class="btn btn-success"></span>

                        <span class="btn btn-info"></span>

                        <span class="btn btn-warning"></span>

                        <span class="btn btn-danger"></span>
                    </div>
                </div><!-- /.sidebar-shortcuts -->

                <ul class="nav nav-list">
                    <%@include file="sidebar.jsp" %>
                </ul><!-- /.nav-list -->

                <div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
                    <i class="ace-icon fa fa-angle-double-left" data-icon1="ace-icon fa fa-angle-double-left" data-icon2="ace-icon fa fa-angle-double-right"></i>
                </div>

                <script type="text/javascript">
                    try {
                        ace.settings.check('sidebar', 'collapsed')
                    } catch (e) {
                    }
                </script>
            </div>

            <div class="main-content">
                <div class="main-content-inner">
                    <div class="breadcrumbs" id="breadcrumbs">
                        <script type="text/javascript">
                            try {
                                ace.settings.check('breadcrumbs', 'fixed')
                            } catch (e) {
                            }
                        </script>

                        <ul class="breadcrumb">
                            <li>
                                <i class="ace-icon fa fa-home home-icon"></i>
                                <a href="index">Home</a>
                            </li>
                            <li>
                                <a href="${requestScope.servlet}"><span>${requestScope.servlet}</span></a>
                            </li>
                            <li class="active"><span>${requestScope.method}</span></li>
                        </ul>
                    </div>

