<%@taglib prefix="helper" uri="/WEB-INF/tlds/helper" %>
<div class="itemdiv commentdiv">
    <div class="body pointer" onclick="window.location.href='order?action=view&id=${invoice.id}'">
        <div class="name">
            <a href="#">${helper:ucFirst(invoice.type)} order</a>
        </div>

        <div class="time">
            <i class="ace-icon fa fa-clock-o"></i>
            <span class="green">${invoice.createdAt}</span>
        </div>

        <div class="text">
            <i class="ace-icon fa fa-quote-left"></i>
            Order ID: ${invoice.id} | Token: ${invoice.token} | Total: ${helper:currency(invoice.total)}
        </div>
    </div>
</div>
