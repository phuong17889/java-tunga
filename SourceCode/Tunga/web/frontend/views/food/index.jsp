<%-- 
    Document   : view
    Created on : Mar 20, 2016, 4:55:04 PM
    Author     : MyPC
--%>
<%@include file="../layout/header.jsp" %>
<div class="grid_9 food">
    <div class="row">
        <div class="col-sm-12">
            <div class="col-sm-6">
                <div class="image"><img src="${food.imageUrl}"></div>
            </div>
            <div class="col-sm-6">
                <div class="detail">
                    <h2>${food.name}</h2>
                    <span>Menu's name: <label>${food.menu.name}</label></span>
                    <span>Price: <label>${helper:currency(food.price)}</label></span>
                    <hr>
                    <span class="quantity">Quantity: <input name="quantity" type="number" value="1"> food(s)</span>
                    <button id="${food.id}" type="button" class="btn btn-success btn-add-to-cart col-sm-9">Add to cart</button>
                </div>
            </div>
        </div>
    </div>
    <div class="clearfix"></div>
    <div class="row">
        <h2 class="head2">Description</h2>
        <div class="col-sm-12">
            ${food.description}
        </div>
    </div>
    <div class="clear"></div>
</div>
<div class="grid_3">
    <h2 class="head2">Related food</h2>
    <ul class="list l1">
        <c:forEach var="food" items="${foods}">
            <li><a href="food?id=${food.id}"> ${food.name}</a></li>
            </c:forEach>
    </ul>
</div>
<div class="clear"></div>
<script>
    $(document).on("click", ".btn-add-to-cart:not(.loading)", function () {
        var th = $(this);
        var id = th.attr("id");
        var quantity = $("input[name='quantity']").val();
        th.addClass("loading").html('<i class="fa fa-spin fa-spinner fa-2x"></i>');
        $.ajax({
            type: "POST",
            cache: false,
            url: "order?action=food",
            data: "id=" + id + "&quantity=" + quantity,
            dataType: "json",
            success: function (response) {
                var itemImg = th.closest(".food").find('.image img').eq(0);
                flyToElement($(itemImg), $('.cart.fleft.relative'));
                setTimeout(function () {
                    $(".cart .count").text(response.count);
                    $(".cart .text").text(response.total);
                    th.removeClass("loading").html("Add to cart");
                }, 1000);
            },
            error: function () {
                alert("Something went wrong!");
                th.removeClass("loading").html("Add to cart");
            }
        });
    });
    function flyToElement(flyer, flyingTo) {
        var $func = $(this);
        var divider = 3;
        var flyerClone = $(flyer).clone();
        $(flyerClone).css({position: 'absolute', top: $(flyer).offset().top + "px", left: $(flyer).offset().left + "px", opacity: 1, 'z-index': 1000});
        $('body').append($(flyerClone));
        var gotoX = $(flyingTo).offset().left + ($(flyingTo).width() / 2) - ($(flyer).width() / divider) / 2;
        var gotoY = $(flyingTo).offset().top + ($(flyingTo).height() / 2) - ($(flyer).height() / divider) / 2;

        $(flyerClone).animate({
            opacity: 0.4,
            left: gotoX,
            top: gotoY,
            width: $(flyer).width() / divider,
            height: $(flyer).height() / divider
        }, 800, function () {
            $(flyingTo).fadeOut('fast', function () {
                $(flyingTo).fadeIn('fast', function () {
                    $(flyerClone).fadeOut('fast', function () {
                        $(flyerClone).remove();
                    });
                });
            });
        });
        return true;
    }
</script>
<%@include file="../layout/footer.jsp" %>