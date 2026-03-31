<%--
  Created by IntelliJ IDEA.
  User: lehuu
  Date: 3/30/2026
  Time: 5:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--lỗi sai chí mạng có thể viết nhằm post thành get--%>
<form action="phongtro" method="post">
    <input type="hidden" name="action" value="update">
<%--    lỗi sai chí mạng có thể viết thiếu maPT--%>
    <input type="hidden" name="maPT" value="${ptid.maPT}">
    Tên phòng trọ: <input type="text" name="tenPT" value="${ptid.tenPT}"><br/>
    Giá phòng trọ: <input type="number" name="giaThue" value="${ptid.giaThue}"><br/>
    Diện tích phòng trọ: <input type="number" name="dienTich" value="${ptid.dienTich}"><br/>

    Ảnh phòng trọ: <input type="text" name="hinhAnh" value="${ptid.hinhAnh}"><br/>
    Khu vục: <select name="maKV">
        <c:forEach var="p" items="${khuvucList}">
            <option value="${p.maKV}" ${p.maKV == ptid.maKV.maKV ? 'selected' :''}>${p.tenKV}</option>
        </c:forEach>
    </select>
    <button type="submit">Lưu</button>
</form>

</body>
</html>
