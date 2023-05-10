
let name_, inv, hidden, objs, index = 0;

hidden = $("#hidden-div").addClass("hidden");
// jquery 加载事件

// hidden.text("邀请码错误");
var aRClass = function(element,add, isPri) {
    if (isPri === 0) {
        element.addClass(add);
        element.removeClass();
    } else {
        element.removeClass();
        element.addClass(add);
    }
}
// 点击事件
function clickBtn() {
    //获取input值
    name = $("#name-txt").val();
    inv = $("#inv-txt").val();

    // 要是有一个input为空就展示 然后返回
    if (name === "" || inv === "") {
        hidden.text("不能为空值");
        aRClass(hidden, "show", 1);
        return;
    }
    // 到这来了就肯定不是空 所以要隐藏
    aRClass(hidden, "hidden", 1);
    // 发送ajax请求
    axios({
        method: 'POST',
        url: '/loginIn',
        data: { name, inv }
    })
        .then(function (response) {
            if (response.data === "") {
                index = 1;
                hidden.text("邀请码错误");
                aRClass(hidden, "show", 1)
                console.log(hidden.text());
            }
            if (!(response.data instanceof Object) && response.data.toString().startsWith('<!DOCTYPE html>')) {
                console.log(response.request.responseURL)
                window.location.href = response.request.responseURL;
                aRClass(hidden, "hidden", 1)
            }
        })
        .catch(function (e){
            console.log(e);
        })
}

