// 获取body的div

let username = $("#username"), container = $("#div-body"), index = 0,
    list = [{"background": "url(./image/asshole3.png)"}, {"background": "url(./image/handsome3.png)"},
        {"background": "pink"}, {"background": "red"}],
    session = $("#session"), maxNum = $("#max"), minNum = $("#min"), loopNum = $("#loop");

username.text(getCookie("name"));
container.css(list[0]);
setInterval('a()', 3000);


function a() {
    if (index >= 4) {
        index = 0;
    }
    container.css(list[index++]);
}

function aRClass(element, add, isPri) {
    if (isPri === 0) {
        element.addClass(add);
        element.removeClass();
    } else {
        element.removeClass();
        element.addClass(add);
    }
}

//
function check() {
    let checkBox = $("#check-box");
    if (session.val() === "" || maxNum.val() === "" || minNum.val() === "" || loopNum.val() === "") {
        aRClass(checkBox, "show", 1);
        return;
    } else if (session.val().length !== 48) {
        aRClass(checkBox, "show", 1);
        checkBox.text("session wrong!!");
        return;
    } else if (isNaN(maxNum.val()) || isNaN(minNum.val()) || isNaN(loopNum.val())) {
        aRClass(checkBox, "show", 1);
        checkBox.text("maxNum and minNum and loopNum must number!")
        return;
    }else if (loopNum.val() <= 0 || loopNum.val() > 10) {
        console.log("Nihoa")
        aRClass(checkBox, "show", 1);
        checkBox.text("loopNum between 0 and 10 !");
        return;
    } else if ((maxNum.val() < 0 || maxNum.val() > 5) &&
        (maxNum.val() < 0 || maxNum.val() > 5) ||
        maxNum.val() === minNum.val()) {
        console.log(typeof maxNum.val());
        console.log( typeof minNum.val());
        if (maxNum.val() != 0 && minNum.val() != 0) {
            aRClass(checkBox, "show", 1);
            checkBox.text("maxNum or minNum is wrong num");
            return;
        }
    }
    //30BB36E207E0467EF9562B45B68E426A
    //9D7F6D37790C14172E2D9BE69D65A4E6
    aRClass(checkBox, "hidden", 1);
    let load = $("#load-animation");
    let container = $('#container');
    $.ajax({
        dataType: "json",
        url:"/bugweb07/bug",
        type: "get",
        data: {session: session.val(), maxNum: maxNum.val(), minNum: minNum.val(), loopNum: loopNum.val()},
        beforeSend: function() {
            container.css('filter', 'brightness(0.4)');
            load.css('display', 'block');
        },
        success: function (data) {
            console.log(data);
            container.css('filter', 'none');
            load.css('display', 'none');
            
        },
        error: function (e) {
            if (e.responseText.indexOf("{\"min\":3.41")) {
                container.css('filter', 'none');
                load.css('display', 'none');
                return;
            }
            console.log(e);
        }
    })


}


