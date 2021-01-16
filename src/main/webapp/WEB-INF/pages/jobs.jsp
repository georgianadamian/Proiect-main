<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Jobs">
    <style>
        h1, form{
             color: white;
             font-size: 20px;
        }
       
    </style>
    <h1> Jobs </h1>
    <form method="POST" action="${pageContext.request.contextPath}/Jobs">
        <c:if test="${pageContext.request.isUserInRole('HrRole') or pageContext.request.isUserInRole('DirectorRole')}">
            <a class="btn btn-outline-success btn-lg" href="${pageContext.request.contextPath}/Jobs/Create" role="button">Add Job</a>
            <button class="btn btn-outline-danger btn-lg" type="submit">Delete Jobs</button>
        </c:if>
        <c:forEach var="job" items="${jobs}" varStatus="status">
            <div class="row">
                <div class="col-md">
                    <input type="checkbox" name="job_ids" value="${job.id}"/>
                </div>   
                <div class="col-md-3">  
                    ${job.jobType}
                </div>
                <div class="col-md-3">
                    ${job.firmName}
                </div>
                <div class="col-md-3">
                    ${job.username}
                </div>
                <div class="col-md-2">
                    <c:if test="${pageContext.request.isUserInRole('DirectorRole')}">
                        <a class="btn btn-outline-secondary btn-sm" href="${pageContext.request.contextPath}/Jobs/Update?id=${job.id}" role="button">Edit Job</a>
                    </c:if>
                </div>
            </div>
        </c:forEach>
    </form> 
</t:pageTemplate>
