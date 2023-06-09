let phoneS, c1, c2, c3, index = 0, searchIndex = 0;
c1 = $(".content1");
c2 = $(".content2");
c3 = $(".content3");
let li = $('.nav-p-s li');

// 定义事件监听器函数
function watchWindowSize() {
    main();
}

function clear() {
    c1.removeClass("hide");
    c2.removeClass("hide");
    c3.removeClass("hide");
}

function clearBorder() {
    li.each(function (index) {
        $(this).removeClass("nav-choose");
    })
}

main();

function main() {
    var w = document.documentElement.clientWidth;
    var h = document.documentElement.clientHeight;
    if (w <= 610) {
        ;
        phoneS = function (num) {
            clear();
            clearBorder()
            if (num === 1) {
                c1.addClass("hide");
                c2.css("display", "grid")
                c3.css("display", "none");
            } else if (num === 2) {
                c1.addClass("hide");
                c2.css("display", "none")
                c3.css("display", "flex");
            } else {
                c2.css("display", "none")
                c3.css("display", "none");
            }
            $(li[num]).addClass("nav-choose");
        }
        if (index++ === 0) {
            phoneS(0);
        }
    } else {
        clear();
        c2.css("display", "none");
        c3.css("display", "none");
        clearBorder();
        $(li[0]).addClass("nav-choose");
    }
}

let txt = $("#search-p-txt");
txt.on("input", () => {
    search();
})
// 实现搜索功能
let search = function () {
    let showS = $(".search-show");
    if (txt.val() !== "" && txt.val() != null && txt.val() !== undefined) {
        $.ajax({
            data: {txt: txt.val()},
            type: "get",
            url: "/search.do",
            dataType: "json",
            success: (data) => {
                showS.empty();
                let content = $("<div style='animation: moveUp 1s'><div></div></div>");
                $(data).each(function (index, value) {
                    let content = $("<div class='search-i-btf'><div></div></div>");
                    content.css({"background-image": `url("${value.path}")`});
                    content.children('div').text(value.commodity);
                    showS.append(content);
                })
                if (data[0] === undefined) {
                    content.text("没有找到你想要的内容")
                    showS.append(content);
                }
            },
            error: (e) => {
                console.log(e);
            }
        })
    }
}
// $(".")
// 登录功能
$(".input-data>input").each(function (index, value) {
    $(value).blur(() => {
        if ($(value).val() == null || $(value).val() === "") {

            $($(".input-data>label")[index]).css({"transform": "translateY(0px)", "font-size": "16px"});

        }
    })
    $(value).focus(() => {
        $($(".input-data>label")[index]).css({"transform": "translateY(-10px)", "font-size": "12px"});
    })
})
// 注册事件监听器
window.addEventListener("resize", watchWindowSize);

