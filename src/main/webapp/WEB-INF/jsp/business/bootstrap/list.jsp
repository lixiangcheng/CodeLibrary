<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="pageTitle" value="展示bootstrap创建和编辑的弹出窗" scope="session"/>
<jsp:include page="/web/title/header.jsp"/>
<body>
<div id="wrapper">
    <!-- Navigation -->
    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">展示bootstrap创建和编辑的弹出窗
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
                        展示bootstrap创建和编辑的弹出窗
                    </div>
                    <div class="panel-body">
                        <div class="form-horizontal">
                            <div class="row">
                                <div class="col-lg-2">&nbsp;</div>
                                <div class="col-lg-6">
                                    <div class="form-group">
                                        <a data-toggle="modal" data-target="#myModal" href="#">
                                            <i class="btn btn-primary btn-sm fa fa-check">创建</i></a>

                                    </div>
                                    <div class="form-group">
                                        <a data-toggle="modal" data-target="#model_setting"
                                           href="${pageContext.request.contextPath}/bootstrap/cu/edit"><i
                                                class="btn btn-success btn-sm fa fa-edit">编辑</i></a>

                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-7" style="padding: 8px 12px;">
                                            <h4>使用注意：</h4>
                                            1、下面的两个div分别是创建和编辑。<br/>
                                            2、data-target需要跟下面的div分别对应，不然无法匹配。<br/>
                                            3、编辑需要引入另外的jsp，只需要书写主要div包容进来即可。<br/>
                                            4、需要依赖bootstarp的 css 或者js。<br/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
    </div>
    <!-- /#page-wrapper -->

</div>
<%--创建div--%>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel" style="text-align: center">创建</h4>
            </div>
            <div class="modal-body">
                <div class="form-horizontal">
                    <div class="form-group">
                        <label for="name" class="col-sm-3 control-label">名称：</label>

                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="name" name="name"
                                   placeholder="请输入名称"
                                   value="${name}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">选项：</label>

                        <div class="col-sm-8">
                            <div class="col-sm-4 radio">
                                <label><input name="type" type="radio" value="1" checked="checked">android</label>
                            </div>
                            <div class="col-sm-4 radio">
                                <label><input name="type" type="radio" value="0">ios</label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary">保存</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<%--编辑div引用到另外一个jsp--%>
<div class="modal fade" id="model_setting" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="false">
    <div class="modal-dialog">
        <div class="modal-content">

        </div>
    </div>
</div>
<!-- /#wrapper -->
<jsp:include page="/web/common/footer.jsp"/>
<jsp:include page="/web/common/footerjs.jsp"/>
<script type="text/javascript">
    Command: toastr["success"](".")

    toastr.options = {
        "closeButton": false,
        "debug": false,
        "newestOnTop": false,
        "progressBar": false,
        "positionClass": "toast-top-right",
        "preventDuplicates": false,
        "onclick": null,
        "showDuration": "300",
        "hideDuration": "1000",
        "timeOut": "5000",
        "extendedTimeOut": "1000",
        "showEasing": "swing",
        "hideEasing": "linear",
        "showMethod": "fadeIn",
        "hideMethod": "fadeOut"
    }
</script>
</body>
</html>
