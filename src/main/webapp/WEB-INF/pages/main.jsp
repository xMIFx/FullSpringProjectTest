<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<spring:message code="label.title"          var = "title"/>
<spring:message code="label.name"           var = "name"/>
<spring:message code="label.email"          var = "email"/>
<spring:message code="label.phoneNumber"    var = "phone"/>
<spring:message code="label.birthDay"       var = "birthDay"/>
<spring:message code="label.salary"         var = "salary"/>
<spring:message code="label.addcontact"     var = "addcontact"/>
<spring:message code="label.delete"         var = "delete"/>
<spring:message code="label.contacts"       var = "contacts"/>
<spring:message code="label.russian"       var = "russian"/>
<spring:message code="label.english"       var = "english"/>
<%--<spring:message code="label.name"           var = "title"/>--%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>${title}</title>
</head>
<body>
<div align="center">
    <h2>${title}</h2>
    Language : <a href="/?lang=en">${english}</a>|<a href="/?lang=ru">${russian}</a>
    <form:form method="post" action="add" commandName="person">
        <table>
            <tr>
                <td><form:label path="name">
                    ${name}
                </form:label></td>
                <td><form:input path="name" /></td>
                <td><form:errors path="name" cssClass="error" /></td>
            </tr>
            <tr>
                <td><form:label path="email">
                    ${email}
                </form:label></td>
                <td><form:input path="email" /></td>
                <td><form:errors path="email" cssClass="error" /></td>
            </tr>
            <tr>
                <td><form:label path="phoneNumber">
                    ${phone}
                </form:label></td>
                <td><form:input path="phoneNumber" /></td>
                <td><form:errors path="phoneNumber" cssClass="error" /></td>
            </tr>
            <tr>
                <td><form:label path="birthDay">
                    ${birthDay}
                </form:label></td>
                <td><form:input path="birthDay" /></td>
                <td><form:errors path="birthDay" cssClass="error" /></td>
            </tr>
            <tr>
                <td><form:label path="salary">
                    ${salary}
                </form:label></td>
                <td><form:input path="salary" /></td>
                <td><form:errors path="salary" cssClass="error" /></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit"
                                       value="${addcontact}" /></td>
            </tr>
        </table>
    </form:form>

    <h3>${contacts}</h3>
        <table class="data">
            <tr>
                <th>${name}</th>
                <th>${email}</th>
                <th>${phone}</th>
                <th>${birthDay}</th>
                <th>${salary}</th>
                <th>&nbsp;</th>
            </tr>
            <c:forEach items="${personList}" var="contact">
                <tr>
                    <td>${contact.name}</td>
                    <td>${contact.email}</td>
                    <td>${contact.phoneNumber}</td>
                    <td>${contact.birthDay}</td>
                    <td>${contact.salary}</td>
                    <td><a href="delete/${contact.id}">${delete}</a></td>
                </tr>
            </c:forEach>
        </table>
</div>
</body>
</html>
