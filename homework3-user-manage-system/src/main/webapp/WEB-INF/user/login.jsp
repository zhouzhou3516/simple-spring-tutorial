<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="../template/top.jsp" flush="true"></jsp:include>

<form:form class="form-signin" action="dologin" commandName="user" method="post">
    <h2 class="form-signin-heading">请登录</h2>(默认用户:admin1/123456,user1/123456)
    <label for="username" class="sr-only">用户名</label>
    <form:input path="username" id="username" cssClass="form-control" placeholder="用户名"/>
    <label for="inputPassword" class="sr-only">Password</label>
    <form:input path="password" id="inputPassword" class="form-control" placeholder="密码"/>

    <button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
</form:form>

<jsp:include page="../template/bottom.jsp" flush="true"></jsp:include>
