/**
 * 将封装一下共用的js在里面
 * Created by lixiangcheng on 16/4/1.
 */

/**
 * 表单提交前：对含有required属性input的元素，检查是否为空，为空时，给予提示
 */
var $errorMessageTip = $("#errorMessageTip");
var CLICK_RESTRICT = false;


function checkInputRequired() {
    var inputRequiredArray = $('input:required');
    if (inputRequiredArray.length > 0) {
        inputRequiredArray.each(function () {
            if ($(this).val() == '') {
                var $name = $(this).parent().siblings('.inputname').text();
                alert("请输入" + $name);
                $errorMessageTip.html("请输入" + $name);
                $errorMessageTip.show();
                CLICK_RESTRICT = false;
                return false;
            }
        });
    }
    return true;
}

/**
 * 表单提交前：对含有required属性textarea的元素，检查是否为空，为空时，给予提示
 */
function checkTextareaRequired() {
    var textareaRequiredArray = $('textarea:required');
    if (textareaRequiredArray.length) {
        textareaRequiredArray.each(function () {
            if ($(this).val() == '') {
                var $name = $(this).parent().siblings('.inputname').text();
                alert("请输入" + $name);
                $errorMessageTip.html("请输入" + $name);
                $errorMessageTip.show();
                CLICK_RESTRICT = false;
                return false;
            }
        });
    }
    return true;
}

/**
 * 表单提交前：对含有required属性select的元素，检查是否为空，为空时，给予提示
 */
function checkSelectRequired() {
    var selectRequiredArray = $('select:required');
    if (selectRequiredArray.length) {
        selectRequiredArray.each(function () {
            if ($(this).val() == '') {
                var $name = $(this).parent().siblings('.inputname').text();
                alert("请输入" + $name);
                $errorMessageTip.html("请输入" + $name);
                $errorMessageTip.show();
                $errorMessageTip.show();
                CLICK_RESTRICT = false;
                return false;
            }
        });
    }
    return true;
}
