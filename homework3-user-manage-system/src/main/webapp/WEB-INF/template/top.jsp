<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link href="/resources/css/style.css" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-default">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Spring Homework3</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">

            <ul class="nav navbar-nav navbar-right">
                <c:if test="${not empty sessionScope.subject}">
                    <li><a href="#">欢迎:${sessionScope.subject.username}</a>
                    </li>
                    <li><a href="/user/logout">退出</a></li>
                </c:if>
                <c:if test="${empty sessionScope.subject}">

                    <li><a href="/user/login">登录</a></li>
                </c:if>
            </ul>

        </div><!--/.nav-collapse -->

    </div>
</nav>

<div class="container">
    <c:if test="${message !=null}">
    <div class="alert alert-success alert-dismissible">${message}</div>
    </c:if>
