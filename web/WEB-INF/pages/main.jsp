<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><spring:message code="label.title" /></title>
</head>
<body>
<div align="center">
    <h2><spring:message code="label.title" /></h2>
    <form:form method="post" action="add" commandName="person">
        <table>
            <tr>
                <td><form:label path="name">
                    <spring:message code="label.name" />
                </form:label></td>
                <td><form:input path="name" /></td>
            </tr>
            <tr>
                <td><form:label path="email">
                    <spring:message code="label.email" />
                </form:label></td>
                <td><form:input path="email" /></td>
            </tr>
            <tr>
                <td><form:label path="phoneNumber">
                    <spring:message code="label.phoneNumber" />
                </form:label></td>
                <td><form:input path="phoneNumber" /></td>
            </tr>
            <tr>
                <td><form:label path="birthDay">
                    <spring:message code="label.birthDay" />
                </form:label></td>
                <td><form:input path="birthDay" /></td>
            </tr>
            <tr>
                <td><form:label path="salary">
                    <spring:message code="label.salary" />
                </form:label></td>
                <td><form:input path="salary" /></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit"
                                       value="<spring:message code="label.addcontact"/>" /></td>
            </tr>
        </table>
    </form:form>

    <h3><spring:message code="label.contacts" /></h3>
        <table class="data">
            <tr>
                <th><spring:message code="label.name" /></th>
                <th><spring:message code="label.email" /></th>
                <th><spring:message code="label.phoneNumber" /></th>
                <th><spring:message code="label.birthDay" /></th>
                <th><spring:message code="label.salary" /></th>
                <th>&nbsp;</th>
            </tr>
            <c:forEach items="${personList}" var="contact">
                <tr>
                    <td>${contact.name}</td>
                    <td>${contact.email}</td>
                    <td>${contact.phoneNumber}</td>
                    <td>${contact.birthDay}</td>
                    <td>${contact.salary}</td>
                    <td><a href="delete/${contact.id}"><spring:message code="label.delete" /></a></td>
                </tr>
            </c:forEach>
        </table>
</div>
</body>
</html>
