<%--
  Created by IntelliJ IDEA.
  User: lehuu
  Date: 3/30/2026
  Time: 5:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<script src="https://cdn.tailwindcss.com"></script>

<html>
<head>
    <title>Title</title>
</head>
<body>
<h2> Thông tin chi tiết</h2>
<div class="bg-green-100 p-3 mt-6 rounded-2xl">
    <img src="images/${detail.hinhAnh}" width="80">
    <div>${detail.tenPT}</div>
    <div>${detail.giaThue}</div>
    <div>${detail.dienTich}</div>
    <c:forEach var="k" items="${khuvucList}">
        <c:if test="${k.maKV == detail.maKV.maKV}">
            <div>${k.tenKV}</div>
            <div>${k.maKV}</div>
            <div>${k.diaChi}</div>

        </c:if>
    </c:forEach>
</div>
<a href="phongtro?action=list">quay lại</a>

</body>
</html>
