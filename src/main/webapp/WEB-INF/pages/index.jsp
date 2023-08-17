<%-- 
    Document   : index
    Created on : Jul 25, 2023, 4:05:23 PM
    Author     : ThanhThuyen
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="<c:url value="/css/style.css"/>" rel="stylesheet" />
<c:url value="/" var="action"/>
<!DOCTYPE html>
<html>

    <body>

        <!<!-- TIN CHO THUE TRO -->
        <div class="bangtin">

            <c:forEach items="${baiviet_1}" var="t" >



                <div class="bviet">
                    <div class="bviet_anh">
                        <img src="${t.hinhAnh}"/>
<!--                     <img src="${t.hinhAnh1}"/>
                        <img src="${t.hinhAnh2}"/>-->
                    </div>
                    <div class="bviet_ndung">
                        <table style="width:100%">
                            <c:url value="/thtin_bviet" var="bvietAction">
                                <c:param name="baivietId" value="${t.id}" />  
                            </c:url>
                            <a href="${bvietAction}" >${t.tenBaiViet}</a>
                            <tr>
                                <th>Khu vực:</th>
                                <td>${t.phamViCanTim}</td>
                            </tr>
                            <tr>
                                <th>Giá thuê:</th>
                                <td>${t.giaThue}/tháng</td>
                            </tr>
                            <tr>
                                <th>Diện tích:</th>
                                <td>${t.dienTich}m2</td>
                            </tr>
                            <tr>

                                <th></th>
                                <td>   
                                    <c:url value="/thtin_bviet" var="bvietAction">
                                        <c:param name="baivietId" value="${t.id}" />  
                                    </c:url>
                                    <a href="${bvietAction}" class="bt-docthem" style="vertical-align:middle"> <span>Đọc thêm </span></a>
                                </td>
                            </tr>
                        </table>



                        <%--<c:if test="${follows.trangThai eq 1}">--%>
                        <%--<c:if test="${follows.idChuTro.id.toString() eq t.idNguoiDung.id}">--%>
                        <!--<input type="submit" value="Hủy Follow" class="btn btn-danger"/>-->
                        <%--</c:if>--%>
                        <%--</c:if>--%>
                        <%--<c:forEach items="follows" var="fls">--%>
                            <%--<c:url value="/${fls.id}" var="apiDelete"/>--%>

                            <!--<button onclick="deleteFollow('${apiDelete}')">Hủy Follow</button>-->

                        <%--</c:forEach>--%>



                        <%--<form:form method="post" action="${action}" var="p" modelAttribute="follow" >--%>
                            <%--<c:if test="${follows.trangThai eq 0}">--%>

                                <!--<input type="submit" value="Follow" class="btn btn-danger"/>-->

                            <%--</c:if>--%>
                            <%--<form:input type="text" id="file" path="tenNguoiDangBai" value="${pageContext.request.userPrincipal.name}"  readonly="true"  cssClass="form -control"/>--%>
                            <%--<form:input type="text" id="file" path="idChuBaiViet" value="${t.idNguoiDung.id}"  readonly="true"  cssClass="form -control"/>--%>
                            <!--<p>${t.idNguoiDung.tenNguoiDung}</p>-->
                        <%--</form:form>--%>


                    </div>


                </div>


            </c:forEach>
        </div>
        <!<!-- TIN TIM TRO -->
        <div class="bangtin">

            <c:forEach items="${baiviet_2}" var="m" >
                <div class="bviet">

                    <div class="bviet_ndung">
                        <table style="width:100%">
                            <a >${m.tenBaiViet}</a>
                            <tr>
                                <th>Khu vực:</th>
                                <td>${m.phamViCanTim}</td>
                            </tr>

                        </table>
                    </div>
                </div>

            </c:forEach>
        </div>


    </body>
</html>
