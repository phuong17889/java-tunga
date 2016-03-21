<%-- 
    Document   : submit
    Created on : Mar 21, 2016, 11:54:48 AM
    Author     : Hoangha.FPT
--%>
<%@include file="../layout/header.jsp" %>
<form action="cart" method="POST">
    <input type="hidden" name="action" value="submit">
<label>Full name: </label><input type="text" name="fullName"><br>
<label>Address: </label><input type="text" name="address"><br>
<label>Phone: </label><input type="text" name="phone"><br>
<button type="submit">Submit order</button>
</form>
<%@include file="../layout/footer.jsp" %>