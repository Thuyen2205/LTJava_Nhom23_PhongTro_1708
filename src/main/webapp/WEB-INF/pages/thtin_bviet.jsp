<%-- 
    Document   : thtin_bviet
    Created on : Aug 12, 2023, 2:34:35 PM
    Author     : Admins
--%>


<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="<c:url value="/css/style.css" />" rel="stylesheet" />
<c:url value="/thtin_bviet_bl" var="action">
    <c:param name="baivietId" value="${BaiViet.id}" />  
</c:url>
<c:url value="/thtin_bviet_fl" var="actionfl">
    <c:param name="baivietId" value="${BaiViet.id}" />  
</c:url>
<%--<c:url value="/thtin_bviet_fl" var="actionfl">
    <c:param name="baivietId" value="${BaiViet.id}" />  
</c:url>--%>
<%--<c:url value="/thtin_bviet" var="actionfl"/>--%>


<section class="chitiettin" >
    <div class="chitiettin-col1">
        <div class="anh-chitiet">

        </div>
        <div class="ndung-chitiet">
            <h2>${BaiViet.tenBaiViet}</h2>
            <p>Địa chỉ: ${BaiViet.phamViCanTim}</p>
            <div class="chitiet-3tt">
                <p>Giá: ${BaiViet.giaThue}</p>
                <p>Diện tích: ${BaiViet.dienTich}</p>
                <p> #${BaiViet.id}</p>
            </div>
            <p>${BaiViet.noiDung}</p>
            <c:if test="${pageContext.request.userPrincipal.name == null}">
                <form:form method="post" action="${action}" var="p" modelAttribute="binhluan" enctype="multipart/form-data" >

                    <form:input type="text" id="file" path="tenNguoiDangBai" value="${pageContext.request.userPrincipal.name}"  readonly="true"  cssClass="form -control"/>
                    <form:input type="text" id="file" path="idBaiVietBinhLuan" value="${BaiViet.id}"  readonly="true"  cssClass="form -control"/>
                    <form:input type="text" path="noiDung"/>
                    <input type="submit" value="Bình Luận" class="btn btn-danger" disabled/>
                </form:form>
            </c:if>
            <c:if test="${pageContext.request.userPrincipal.name != null}">
                <form:form method="post" action="${action}" var="p" modelAttribute="binhluan" enctype="multipart/form-data" >

                    <form:input type="text" id="file" path="tenNguoiDangBai" value="${pageContext.request.userPrincipal.name}"  readonly="true"  cssClass="form -control"/>
                    <form:input type="text" id="file" path="idBaiVietBinhLuan" value="${BaiViet.id}"  readonly="true"  cssClass="form -control"/>

                    <form:input type="text" path="noiDung"/>
                    <input type="submit" value="Bình Luận" class="btn btn-danger"/>
                </form:form>
            </c:if>





        </div>
    </div>

    <div class="chitiettin-col2">
        <a class="navbar-brand" href="#">
            <img src="${BaiViet.idNguoiDung.avatar}" style="width:80px;" class="rounded-pill"> 
        </a>
        <h2>${BaiViet.idNguoiDung.tenNguoiDung}</h2>
        <h2>${BaiViet.idNguoiDung.sdt}</h2>
        <%--<c:if  test="${BaiViet.idNguoiDung.idLoaiTaiKhoan.id==2}">--%>
        <%--<form:form method="post" action="${actionfl}" var="p" modelAttribute="follow" enctype="multipart/form-data" >--%>
        <%--<form:input type="text" id="file" path="tenNguoiDangBai" value="${pageContext.request.userPrincipal.name}"  readonly="true"  cssClass="form -control"/>--%>
        <%--<form:input type="text" id="file" path="idChuBaiViet" value="${BaiViet.idNguoiDung.id}"  readonly="true"  cssClass="form -control"/>--%>
        <!--<input type="submit" value="Follow" class="btn btn-danger"/>-->
        <%--</form:form>--%>
        <%--</c:if>--%>
        <%--<c:forEach></c:forEach>--%>
        <c:forEach items="${follows}" var="fl">

            <c:url value="/api/thtin_bviet/${fl.id}" var="apiDelete"/>
            <c:if test="${fl.idChuTro.id.toString() eq BaiViet.idNguoiDung.id.toString()}">
                <button class="btn btn-danger text-center" onclick="deleteFollow('${apiDelete}')">Hủy Follow</button>
                <p>${fl.id}</p>
            </c:if>
        </c:forEach>



        <form:form method="post" action="${actionfl}" var="p" modelAttribute="follow" >
            <%--<c:if test="${follows.trangThai eq 0}">--%>

            <input type="submit" value="Follow" class="btn btn-danger"/>

            <%--</c:if>--%>
            <form:input type="text" id="file" path="tenNguoiDangBai" value="${pageContext.request.userPrincipal.name}"  readonly="true"  cssClass="form -control"/>
            <form:input type="text" id="file" path="idChuBaiViet" value="${BaiViet.idNguoiDung.id}"  readonly="true"  cssClass="form -control"/>
            <p>${t.idNguoiDung.tenNguoiDung}</p>
        </form:form>
    </div>
    <div>
        <c:forEach items="${binhluans}" var="b">
            <div class="comtent row" style="border-width: 20px">
                <div class="col-md-1">
                    <img src="${b.idNguoiDung.avatar} " style="width:80px" />
                </div>
                <div>
                    <p>${b.idNguoiDung.tenNguoiDung}</p>  
                    <p>${b.noiDung}</p>
                    <p class="commentDate">${b.ngayBinhLuan}</p>
                </div>  
            </div>
        </c:forEach>
    </div>
    <script>
        window.onload = function () {
            let dates = document.getElementsByClassName("commentDate")
            for (let i = 0; i < dates.length; i++)
            {
                dates[i].innerText = moment(dates[i].innerText).fromNow()
            }

        }

    </script>





</div>
</section>
