<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../includes/header.jsp"/>
            <!-- Begin Page Content -->
            <div class="container-fluid">

                <!-- Page Heading -->
                <div class="d-sm-flex align-items-center justify-content-between mb-4">
                    <h1 class="h3 mb-0 text-gray-800">Dashboard</h1>
                    <a href="/user/add" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
                            class="fas fa-download fa-sm text-white-50"></i> Dodaj użytkownika</a>
                    <a href="/user/list" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
                            class="fas fa-download fa-sm text-white-50"></i> Lista użytkowników</a>
                </div>
                <table class="table">

                    <thead>
                    <h3>Lista użytkowników</h3>
                    <tr>
                        <th>ID</th>
                        <th>Nazwa</th>
                        <th>Email</th>
                        <th>Akcja</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${users}" var="user">
                        <tr>
                            <td>${user.id}</td>
                            <td>${user.username}</td>
                            <td>${user.email}</td>
                            <td>
                                <a href="<c:url value="/user/delete?id=${user.id}"/>">Usuń</a>
                                <a href="<c:url value="/user/edit?id=${user.id}"/>">Edit</a>
                                <a href="<c:url value="/user/show?id=${user.id}"/>">Pokaż</a>
                            </td>
                        </tr>

                    </c:forEach>

                    </tbody>
                </table>


            </div>
            <!-- /.container-fluid -->

<jsp:include page="../includes/footer.jsp"/>
