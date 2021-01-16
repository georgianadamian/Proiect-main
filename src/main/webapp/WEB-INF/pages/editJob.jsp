<%-- 
    Document   : addJob
    Created on : Jan 8, 2021, 2:07:31 PM
    Author     : Stefan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Edit Job">
    <style>
        h1, form {
            color: white;
        }
    </style>
    <h1>Edit Job</h1>
    <form class="needs-validation" novalidate method="POST" action="${pageContext.request.contextPath}/Jobs/Update">
        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="job_type">Job Type</label>
                <input type="text" class="form-control" id="job_type" name="job_type" placeholder="" value="${job.jobType}" required>
                <div class="invalid-feedback">
                    Job Type is required.
                </div>
            </div>
        </div>
       
        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="firm_name">Firm Name</label>
                <input type="text" class="form-control" id="firm_name" name="firm_name" placeholder="" value="${job.firmName}" required>
                <div class="invalid-feedback">
                    Firm Name is required.
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="owner_id">Owner</label>
                <select class="custom-select d-block w-100" id="owner_id" name="owner_id" required>
                    <option value="">Choose...</option>
                    <c:forEach var="user" items="${users}" varStatus="status">
                        <option value="${user.id}" ${job.username eq user.username ? 'selected' : ''}>${user.username}</option>
                    </c:forEach>
                </select>
                <div class="invalid-feedback">
                    Please select a valid owner.
                </div>
            </div>
        </div>
    <input type="hidden" name="job_id" value="${job.id}"/>
    <button class="btn btn-primary btn-lg btn-block" type="submit">Save</button>

<script>
      // Example starter JavaScript for disabling form submissions if there are invalid fields
      (function() {
        'use strict';

        window.addEventListener('load', function() {
          // Fetch all the forms we want to apply custom Bootstrap validation styles to
          var forms = document.getElementsByClassName('needs-validation');

          // Loop over them and prevent submission
          var validation = Array.prototype.filter.call(forms, function(form) {
            form.addEventListener('submit', function(event) {
              if (form.checkValidity() === false) {
                event.preventDefault();
                event.stopPropagation();
              }
              form.classList.add('was-validated');
            }, false);
          });
        }, false);
      })();
    </script>
</t:pageTemplate>
