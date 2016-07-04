<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="pageTitle" value="展示file上传预览" scope="session"/>
<jsp:include page="/web/title/header.jsp"/>
<body>
<div id="wrapper">
    <!-- Navigation -->
    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">展示file上传预览
                </h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading" style="text-align: center">

                        <div class="pull-left">
                            <div class="btn-group">
                                <a class="btn btn-danger btn-xs pull-left" href="javascript:history.back();">
                                    <i class="fa fa-backward"> 返回</i>
                                </a>
                            </div>
                        </div>
                        展示file上传预览
                    </div>

                    <div class="panel-body">
                        <div class="form-horizontal">
                            <form class="form-horizontal"
                                  action="${contextPath}/uploadPreviewImage.htm"
                                  method="post"
                                  id="uploadPreviewImage"
                                  enctype="multipart/form-data" role="form">
                                <div class="row">
                                    <div class="col-lg-2">&nbsp;</div>
                                    <div class="col-lg-6">
                                        <div class="form-group">
                                            <dl class="form-table-half user-mgt-avatar" id="preview">
                                                <img id="imghead" width=120 height=100 border=0 class="avatar-img"
                                                     alt="">
                                            </dl>
                                        </div>
                                        <div class="form-group">
                                            <dl>
                                                <input type="file" name="previewImage1" id="previewImage1"
                                                       class="form-input-file" required="required"
                                                       onchange="previewImage(this)" class="form-input-file"/>
                                                <span>*上传图片</span>
                                            </dl>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-sm-7" style="padding: 8px 12px;">
                                                <h4>使用注意：</h4>
                                                1、function可以直接copy。<br/>
                                                2、需要对显示的div或者其他元素增加function对应的img 的id<br/>
                                                3、对需要预览的input添加onchange事件，传入当前对象this。<br/>
                                                4、不需要依赖其他css 或者js<br/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                    <%--
                    使用注意：
                        1、function可以直接copy。
                        2、需要对显示的div或者其他元素增加function对应的img 的id
                        3、对需要预览的input添加onchange事件，传入当前对象this。
                        4、不需要依赖其他css 或者js
                    --%>
                </div>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
    </div>
    <!-- /#page-wrapper -->

</div>
<!-- /#wrapper -->
<jsp:include page="/web/common/footer.jsp"/>
<jsp:include page="/web/common/footerjs.jsp"/>
<script type="text/javascript">
    function previewImage(file) {
        var MAXWIDTH = 100;
        var MAXHEIGHT = 100;
        var div = document.getElementById('preview');
        if (file.files && file.files[0]) {
            div.innerHTML = '<img id=imghead>';
            var img = document.getElementById('imghead');
            img.onload = function () {
                var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
                img.width = rect.width;
                img.height = rect.height;
                img.style.marginLeft = rect.left + 'px';
                img.style.marginTop = rect.top + 'px';
            }
            var reader = new FileReader();
            reader.onload = function (evt) {
                img.src = evt.target.result;
            }
            reader.readAsDataURL(file.files[0]);
        }
        else {
            var sFilter = 'filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
            file.select();
            var src = document.selection.createRange().text;
            div.innerHTML = '<img id=imghead>';
            var img = document.getElementById('imghead');
            img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;
            var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
            status = ('rect:' + rect.top + ',' + rect.left + ',' + rect.width + ',' + rect.height);
            div.innerHTML = "<div id=divhead style='width:" + rect.width + "px;height:" + rect.height + "px;margin-top:" + rect.top + "px;margin-left:" + rect.left + "px;" + sFilter + src + "\"'></div>";
        }
    }

    function clacImgZoomParam(maxWidth, maxHeight, width, height) {
        var param = {top: 0, left: 0, width: width, height: height};
        if (width > maxWidth || height > maxHeight) {
            rateWidth = width / maxWidth;
            rateHeight = height / maxHeight;

            if (rateWidth > rateHeight) {
                param.width = maxWidth;
                param.height = Math.round(height / rateWidth);
            } else {
                param.width = Math.round(width / rateHeight);
                param.height = maxHeight;
            }
        }
        param.left = Math.round((maxWidth - param.width) / 2);
        param.top = Math.round((maxHeight - param.height) / 2);
        return param;
    }
</script>


</body>

</html>
