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

                <h4> Edytuj użytkownika</h4>
                <form action="/user/edit" method="post" >
                    <input type="hidden" name="id" value="${user.id}">

                    <div class="form">
                        <label for="userName"> Nazwa </label><br>
                        <input type="text" value="${user.username}" name="username" id="userName" placeholder="Nazwa użytkownika">
                    </div>

                    <div class="form">
                        <label for="email"> Email </label><br>
                        <input type="text" value="${user.email}" name="email" id="email" placeholder="Email">
                    </div>

                    <div class="form">
                        <label for="email"> Hasło </label><br>
                        <input name="password" type="password" id="password" placeholder="Hasło użytkownika">
                    </div>
                    <button class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm" type="submit"> ZAPISZ </button>
                </form>

                <h3>${not empty message ? message : ""}</h3>

            </div>
            <!-- /.container-fluid -->

<jsp:include page="../includes/footer.jsp"/>
