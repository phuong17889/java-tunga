<%-- 
    Document   : index
    Created on : Mar 21, 2016, 12:35:22 AM
    Author     : MyPC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="helper" uri="/WEB-INF/tlds/helper" %>
<!DOCTYPE html>
<%@include file="../layout/header.jsp" %>
<div class="page-content">
    <div class="page-header">
        <h1>
            Welcome to admin control panel!
        </h1>
    </div>
</div>
<script>
    var socket = new WebSocket("${helper:socketUrl()}/websocket");
    socket.onmessage = onMessage;
    function onMessage(event) {
        var invoice = JSON.parse(event.data);
        if (invoice.action === "add") {
            $.gritter.add({
                title: 'Have a new food order',
                text: 'Order id: ' + invoice.id,
                class_name: 'gritter-success'
            });
            //TODO xong cần phải xóa ngay socket bằng 1 action gửi lên
            return false;
        }
    }
</script>
<%@include file="../layout/footer.jsp" %>