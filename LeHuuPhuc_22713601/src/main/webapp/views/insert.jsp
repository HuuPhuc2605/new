<%--
  Created by IntelliJ IDEA.
  User: lehuu
  Date: 3/30/2026
  Time: 5:43 PM
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
<%--lỗi sai chí mạng có thể viết nhằm post thành get--%>
<form action="phongtro" method="post" class="space-y-4">
    <input type="hidden" name="action" value="insert">
    Tên phòng trọ: <input class="border mt-4 w-60 h-10 p-4" type="text" name="tenPT" required><br/>
    Giá phòng trọ: <input class="border mt-4 w-60 h-10 p-4" type="number" name="giaThue" min="0" required><br/>
    Diện tích phòng trọ: <input class="border mt-4 w-60 h-10 p-4" type="number" name="dienTich" min="0" required><br/>

    Ảnh phòng trọ: <input class="border mt-4 w-60 h-10 p-4" type="text" name="hinhAnh" required><br/>
    Khu vục: <select name="maKV">
        <c:forEach var="p" items="${khuvucList}">
            <option class="border mt-4 w-60 h-10 p-4" value="${p.maKV}" >${p.tenKV}</option>
        </c:forEach>
    </select>
    <button class="py-2 mt-4 px-4 bg-green-400 rounded-xl"  type="submit">Lưu</button>
</form>

</body>
</html>
