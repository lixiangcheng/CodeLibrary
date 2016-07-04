<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="modal-content">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title" id="myModalLabel" style="text-align: center">编辑</h4>
    </div>

        <div class="modal-body">
            <div class="form-horizontal">
                <div class="form-group">
                    <label for="name" class="col-sm-3 control-label">名称：</label>

                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="name" name="name"
                               placeholder="请输入名称"
                               value="李志恒">
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