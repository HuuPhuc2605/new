<%--
  Created by IntelliJ IDEA.
  User: lehuu
  Date: 3/30/2026
  Time: 5:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<script src="https://cdn.tailwindcss.com"></script>
<html>
<head>
    <title>Danh sách phòng trọ</title>
</head>
<body>
<div class="p-6 gap-10 ">
<a class="py-2 px-4 bg-gray-400 rounded-xl" href="phongtro?action=list">load lại</a>
<form class="mt-4" action="phongtro" method="get">
    <input type="hidden" name="action" value="search">
    Tên phòng trọ: <input class="border w-60 h-10 p-4" placeholder="nhập tên cần tìm..." type="text" name="name" value="${name}">
    <button class="py-2 px-4 bg-green-400 rounded-xl"  type="submit">Tìm</button>
</form>
<form action="phongtro" method="get">
    <input type="hidden" name="action" value="byKV">
    Chọn thể loại:
    <select name="id">
        <c:forEach var="p" items="${khuvucList}">
            <option class="border w-60 h-10 p-4"  value="${p.maKV}">${p.tenKV}</option>
        </c:forEach>
    </select>
    <button class="py-2 px-4 bg-green-400 rounded-xl"  type="submit">Lọc</button>
</form>
<a class="py-2 px-4 bg-blue-400 rounded-xl"  href="phongtro?action=add">Thêm mới</a>

<h2 class="text-2xl text-green-500 font-bold text-center">Danh sách phòng trọ hiện tại</h2>
<%--<div class="grid grid-cols-3 gap-4">--%>
<table  class="w-full border border-collapse border-gray-400 text-center">
    <thead>
    <tr>
        <td class="border px-4 py-2">ảnh</td>
        <td class="border px-4 py-2">tên</td>
        <td class="border px-4 py-2">giá</td>
        <td class="border px-4 py-2">diện tích</td>
        <td class="border px-4 py-2">khu vực</td>
        <td class="border px-4 py-2">thao tác</td>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="p" items="${list}">
        <%--        <div class="bg-green-100 p-3 mt-6 rounded-2xl">--%>
        <tr>
            <td>
                <img src="images/${p.hinhAnh}" width="80">
            </td>
            <td>${p.tenPT}</td>
            <td>${p.giaThue}</td>

            <td>${p.dienTich}</td>

            <td>  <c:forEach var="k" items="${khuvucList}">
                <c:if test="${k.maKV == p.maKV.maKV}">
                    <div>${k.tenKV}</div>
                </c:if>
            </c:forEach></td>
            <td> <a href="phongtro?action=detail&id=${p.maPT}">chi tiết</a>
                <a href="phongtro?action=edit&id=${p.maPT}">cập nhật</a>
                <form action="phongtro" method="post">
                    <input type="hidden" name="action" value="delete">
                    <input type="hidden" name="maPT" value="${p.maPT}">
                    <button type="submit" onclick="return confirm('Bạn có chắc xóa không?')">Xóa</button>
                </form></td>

        </tr>

<%--        <div class="text-xl">${p.tenPT}</div>--%>
<%--        <div class="color-green">${p.giaThue}</div>--%>
<%--        <div>${p.dienTich}</div>--%>
<%--        <c:forEach var="k" items="${khuvucList}">--%>
<%--            <c:if test="${k.maKV == p.maKV.maKV}">--%>
<%--                <div>${k.tenKV}</div>--%>
<%--            </c:if>--%>
<%--        </c:forEach>--%>
<%--        <div>--%>
<%--           --%>
<%--        </div>--%>
        <%--        </div>--%>
    </c:forEach>
    </tbody>
</table>


<%--</div>--%>
</div>
</body>
</html>
