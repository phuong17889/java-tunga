<%-- 
    Document   : index
    Created on : Mar 21, 2016, 10:09:09 AM
    Author     : Hoangha.FPT
--%>

<%@include file="../layout/header.jsp" %>
<c:choose>
    <c:when test="${cart == null}">
        <div class="shopping-cart">
            <h2>Your cart is empty!</h2>
        </div>
    </c:when>
    <c:otherwise>
        <div class="shopping-cart">
            <h2>Your shopping cart</h2>
            <div class="row">
                <div class="col-sm-12">
                    <table class="table table-responsive table-striped">
                        <tr>
                            <th class="col-sm-5">Food's name</th>
                            <th class="col-sm-2">Price</th>
                            <th class="col-sm-2">Quantity</th>
                            <th class="col-sm-2">Sub-total</th>
                            <th class="col-sm-1"></th>
                        </tr>
                        <c:forEach var="food" items="${cart.getCartContent()}">
                            <tr>
                                <td>${food.name}</td>
                                <td>${helper:currency(food.price)}</td>
                                <td>${cart.getCartQuantity(food.id)}</td>
                                <td>${helper:currency(cart.getCartQuantity(food.id) * food.price)}</td>
                                <td><a href="javascript://" class="btn-delete" id="${food.id}"><i class="fa fa-trash"></i></a></td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td colspan="3"><strong>Total:</strong></td>
                            <td class="total"><strong>${helper:currency(cart.getTotalPrice())}</strong></td>
                            <td></td>
                        </tr>
                    </table>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12">
                    <div class="col-sm-4 col-sm-offset-8">
                        <button class="btn btn-warning" onclick="window.location.href = 'index'">Continue food ordering</button>
                        <button class="btn btn-success btn-checkout">Checkout</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="checkout" style="display: none">
            <h2>Please fill your information!</h2>
            <form action="cart" method="POST" class="form-horizontal">
                <input type="hidden" name="action" value="submit">
                <div class="row">
                    <div class="col-sm-12">
                        <div class="form-group">
                            <label class="control-label col-sm-3" for="fullName">Full name: </label>
                            <div class="input-group col-sm-7">
                                <input id="fullName" type="text" name="fullName" class="form-control">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-3" for="email">Email: </label>
                            <div class="input-group col-sm-7">
                                <input id="email" type="text" name="email" class="form-control">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-3" for="address">Address: </label>
                            <div class="input-group col-sm-7">
                                <textarea id="address" name="address" class="form-control"></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-3" for="phone">Phone: </label>
                            <div class="input-group col-sm-7">
                                <input id="phone" type="text" name="phone" class="form-control number-only">
                            </div>
                        </div>
                    </div>
                </div>
                <input type="hidden" name="total" value="${cart.getTotalPrice()}">
                <div class="row">
                    <div class="col-sm-12">
                        <div class="col-sm-4 col-sm-offset-7">
                            <button class="btn btn-success btn-submit" type="submit">Place an order</button>
                            <button class="btn btn-danger btn-cancel">Cancel your order</button>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </c:otherwise>
</c:choose>
<script>
    $(document).on("click", ".btn-submit", function () {
        if (!isEmail($("input#email").val())) {
            alert("Please input exactly yout email!");
            return false;
        }
    });
    $(document).on("click", ".btn-checkout", function () {
        $(".shopping-cart").slideUp('normal', function () {
            $(".checkout").slideDown();
        });
    });
    $(document).on("click", ".btn-delete", function () {
        var conf = confirm("Are you sure?");
        if (conf) {
            var th = $(this);
            $.ajax({
                type: "POST",
                cache: false,
                dataType: "json",
                url: "cart?action=delete",
                data: "id=" + th.attr("id"),
                success: function (data) {
                    th.closest("tr").fadeOut("normal", function () {
                        $(this).remove();
                        if (data.isEmpty) {
                            $(".shopping-cart").html('<h2>Your cart is empty!</h2>');
                            $(".cart .text").text('Empty cart!');
                        } else {
                            $("td.total").html('<strong>' + data.totalText + '</strong>');
                            $("input[type='hidden'][name='total']").val(data.total);
                            $(".cart .text").text(data.totalText);
                        }
                        $(".cart .count").text(data.count);
                    });
                }, error: function () {
                    alert("Something went wrong!");
                }
            });
        }
    });
    $(".number-only").keydown(function (e) {
        if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 || (e.keyCode === 65 && (e.ctrlKey === true || e.metaKey === true)) || (e.keyCode >= 35 && e.keyCode <= 40)) {
            return;
        }
        if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
            e.preventDefault();
        }
    });
    function isEmail(email) {
        var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
        return regex.test(email);
    }
</script>
<%@include file="../layout/footer.jsp" %>

