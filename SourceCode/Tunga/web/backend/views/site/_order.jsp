<%@taglib prefix="helper" uri="/WEB-INF/tlds/helper" %>
<div class="itemdiv commentdiv">
    <div class="body">
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

    <div class="tools">
        <div class="inline position-relative">
            <button class="btn btn-minier bigger btn-yellow dropdown-toggle" data-toggle="dropdown">
                <i class="ace-icon fa fa-angle-down icon-only bigger-120"></i>
            </button>

            <ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">
                <li>
                    <a href="#" class="tooltip-success" data-rel="tooltip">
                        <span class="red">
                            <i class="ace-icon fa fa-ban bigger-110"></i> Cancel
                        </span>
                    </a>
                </li>

                <li>
                    <a href="#" class="tooltip-warning" data-rel="tooltip">
                        <span class="orange">
                            <i class="ace-icon fa fa-ship bigger-110"></i> Ship
                        </span>
                    </a>
                </li>

                <li>
                    <a href="#" class="tooltip-error" data-rel="tooltip">
                        <span class="green">
                            <i class="ace-icon fa fa-check bigger-110"></i> Complete
                        </span>
                    </a>
                </li>
            </ul>
        </div>
    </div>
</div>
