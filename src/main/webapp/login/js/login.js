let name_, inv, hidden, index = 0, clickDiv;

hidden = $("#hidden-div").addClass("hidden");
// jquery 加载事件

// hidden.text("邀请码错误");
var aRClass = function (element, add, isPri) {
    if (isPri === 0) {
        element.addClass(add);
        element.removeClass();
    } else {
        element.removeClass();
        element.addClass(add);
    }
}

clickDiv = function(num) {
    
    //获取input值
    name = $("#name-txt").val();
    inv = $("#inv-txt").val();

    // 要是有一个input为空就展示 然后返回
    if (name === "" || inv === "") {
        $("#choose").css("display", "none");
        $(".container").css({"filter": "none"});
        hidden.text("不能为空值");
        aRClass(hidden, "show", 1);
        return;
    }
    // 到这来了就肯定不是空 所以要隐藏
    aRClass(hidden, "hidden", 1);

    // 发送axios请求
    axios({
        method: 'POST',
        url: '/loginIn.do',
        data: {name :encodeURI(encodeURI(name)), inv, num}
    })
        .then(function (response) {
            if (response.data === "") {
                $("#choose").css("display", "none");
                $(".container").css({"filter": "none"});
                index = 1;
                hidden.text("邀请码错误");
                aRClass(hidden, "show", 1)
                console.log(hidden.text());
            }
            if (!(response.data instanceof Object) &&
                response.data.toString().startsWith('<!DOCTYPE html>')) {
                aRClass(hidden, "hidden", 1);
                console.log(name)
                window.location.href = response.request.responseURL;
            }
        })
        .catch(function (e) {
            console.log(e);
        })
}
// 点击事件
function clickBtn() {

    $("#choose").css({
        "display": "block",
        "animation-name": "identifier",
        "animation-duration": ".8s",
        "animation-fill-mode": "forwards"
    });
    $(".container").css({"filter": "brightness(0.4)"});

   
}

