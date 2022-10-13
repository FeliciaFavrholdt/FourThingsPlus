<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>

<t:fourthingsplus>
    <jsp:attribute name="header">
         Welcome
    </jsp:attribute>

    <jsp:body>

        <form method="post">

            <div class="form-outline mb-4">
                <div class="row">
                    <div class="col">
                        <input name="name" placeholder="New item" class="form-control" id="name" type="text"/>
                    </div>
                    <div class="col">
                        <button class="btn btn-outline-secondary" formaction="additem" name="item_id">
                            Add
                        </button>
                    </div>
                </div>
            </div>

            <h3>Doing</h3>

            <table class="table table-striped">
                <tr>
                    <th  style="width:70%">Item</th>
                    <th>Action</th>
                </tr>
                <c:forEach var="item" items="${requestScope.itemList}">
                    <c:if test="${!item.done}">
                        <tr>
                            <td class="w-80">${item.name}</td>
                            <td class="w-20">
                                <button  style="width:75px;" class="btn btn-outline-secondary mb-2" formaction="done" value="${item.id}" name="item_id">Done</button>
                                <button  style="width:75px;" class="btn btn-outline-secondary  mb-2" formaction="edit" value="${item.id}" name="item_id">Edit</button>
                            </td>
                        </tr>
                    </c:if>
                </c:forEach>
            </table>
        </form>

        <h3>Way gone and done</h3>

        <form method="post">
            <table class="table table-striped">
                <tr>
                    <th style="width:70%">Item</th>
                    <th>Action</th>
                </tr>
                <c:forEach var="item" items="${requestScope.itemList}">
                    <c:if test="${item.done}">
                        <tr>
                            <td>${item.name}</td>
                            <td>
                                <button style="width:75px;" class="btn btn-outline-secondary" formaction="done" value="${item.id}" name="item_id">Undo</button>
                            </td>
                        </tr>
                    </c:if>
                </c:forEach>
            </table>
        </form>
    </jsp:body>

</t:fourthingsplus>