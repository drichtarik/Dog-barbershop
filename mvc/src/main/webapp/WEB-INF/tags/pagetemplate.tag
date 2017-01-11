<%@ tag pageEncoding="utf-8" dynamic-attributes="dynattrs" trimDirectiveWhitespaces="true" %>
<%@ attribute name="title" required="false" %>
<%@ attribute name="head" fragment="true" %>
<%@ attribute name="body" fragment="true" required="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html lang="${pageContext.request.locale}">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><c:out value="${title}"/></title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/favicon.ico" type="image/x-icon">
    <!-- bootstrap loaded from content delivery network -->
    <%--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" crossorigin="anonymous">--%>
    <%--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css"  crossorigin="anonymous">--%>
    <link href="https://maxcdn.bootstrapcdn.com/bootswatch/3.3.7/readable/bootstrap.min.css" rel="stylesheet" integrity="sha384-Li5uVfY2bSkD3WQyiHX8tJd0aMF91rMrQP5aAewFkHkVSTT2TmD2PehZeMmm7aiL" crossorigin="anonymous">
    <jsp:invoke fragment="head"/>
</head>
<body>
<!-- navigation bar -->
<nav class="navbar navbar-inverse navbar-static-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <my:a class="navbar-brand" href="/">Dog Barbershop</my:a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <sec:authorize access="hasRole('ROLE_ANONYMOUS')">
                <ul class="nav navbar-nav">
                    <li><my:a href="/services/list">Services</my:a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><my:a href="/login">Login</my:a></li>
                    <li><my:a href="/customers/new">Register</my:a></li>
                </ul>
            </sec:authorize>
            <sec:authorize access="hasRole('ROLE_CUSTOMER')">
                <ul class="nav navbar-nav">
                    <li><my:a href="/services/list">Services</my:a></li>
                    <li><my:a href="/customers/detail/{$currentUser.id}">My profile</my:a></li>
                    <li><my:a href="/dogs/list">My dogs</my:a></li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><b class="caret">My orders</b></a>
                        <ul class="dropdown-menu">
                            <li><my:a href="/orders/list/all">All</my:a></li>
                            <li><my:a href="/orders/list/waiting">Ordered</my:a></li>
                            <li><my:a href="/orders/list/done">Done</my:a></li>
                        </ul>
                    </li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><my:a href="/logout">Logout</my:a></li>
                </ul>
            </sec:authorize>
            <sec:authorize access="hasRole('ROLE_EMPLOYEE')">
                <ul class="nav navbar-nav">
                    <li><my:a href="/services/list">Services</my:a></li>
                    <li><my:a href="/customers/list">Customers</my:a></li>
                    <li><my:a href="/dogs/list">Dogs</my:a></li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><b class="caret">Orders</b></a>
                        <ul class="dropdown-menu">
                            <li><my:a href="/orders/list/all">All</my:a></li>
                            <li><my:a href="/orders/list/waiting">Ordered</my:a></li>
                            <li><my:a href="/orders/list/done">Done</my:a></li>
                        </ul>
                    </li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><my:a href="/logout">Logout</my:a></li>
                </ul>
            </sec:authorize>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <!-- TODO -->
                <ul class="nav navbar-nav">
                    <li><my:a href="/employees/list">Employees</my:a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><my:a href="/logout">Logout</my:a></li>
                </ul>
            </sec:authorize>
        </div><!--/.nav-collapse -->
    </div>
</nav>

<div class="container">

    <!-- page title -->
    <c:if test="${not empty title}">
        <div class="page-header">
            <h1><c:out value="${title}"/></h1>
        </div>
    </c:if>

    <!-- authenticated user info -->
    <c:if test="${not empty authenticatedUser}">
    <div class="row">
        <div class="col-xs-6 col-sm-8 col-md-9 col-lg-10"></div>
        <div class="col-xs-6 col-sm-4 col-md-3 col-lg-2">
            <div class="panel panel-default">
                <div class="panel-body">
                    <c:out value="${authenticatedUser.givenName} ${authenticatedUser.surname}"/>
                </div>
            </div>
        </div>
    </div>
    </c:if>

    <!-- alerts -->
    <c:if test="${not empty alert_danger}">
        <div class="alert alert-danger" role="alert">
            <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
            <c:out value="${alert_danger}"/></div>
    </c:if>
    <c:if test="${not empty alert_info}">
        <div class="alert alert-info" role="alert"><c:out value="${alert_info}"/></div>
    </c:if>
    <c:if test="${not empty alert_success}">
        <div class="alert alert-success" role="alert"><c:out value="${alert_success}"/></div>
    </c:if>
    <c:if test="${not empty alert_warning}">
        <div class="alert alert-warning" role="alert"><c:out value="${alert_warning}"/></div>
    </c:if>

    <!-- page body -->
    <jsp:invoke fragment="body"/>

    <!-- footer -->
    <footer class="footer">
        <p>&copy;&nbsp;2016&nbsp;Masaryk University</p>
    </footer>
</div>
<!-- javascripts placed at the end of the document so the pages load faster -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>
</html>
