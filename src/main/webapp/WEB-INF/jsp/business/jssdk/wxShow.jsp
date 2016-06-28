<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" contentType="text/html; charset=utf-8" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=0.5, minimum-scale=0.5, maximum-scale=1.0, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    <meta name="description" content="">
    <meta http-equiv="x-dns-prefetch-control" content="on">

    <title>发布地板</title>
    <link href="<%=request.getContextPath()%>/moibleStyle/css/css.css" rel="stylesheet" type="text/css">
    <script src="<%=request.getContextPath()%>/moibleStyle/js/jquery-2.1.4.min.js"></script>
    <script>
        function forDistrict() {
            var cityId = $("#cityId").val();
            if (cityId == null || cityId == "") {
                alert("请选择省市");
                return;
            }
            $.ajax({
                type: "POST",
                url: "<c:url value="/select_district_a.htm"/>",
                data: {cityId: cityId},
                contentType: "application/x-www-form-urlencoded;charset=UTF-8",
                success: function (data) {
                    var str = "";
                    for (var i = 0; i < data.districtList.length; i++) {
                        str = str + "<a href='javascript:void(0);' " +
                                "onclick=\"setDistrict(\'" +
                                $.trim(data.districtList[i].districtName) + "\',\'" + data.districtList[i].districtId
                                + "\')\">" +
                                "<div class='listitem'>" + data.districtList[i].districtName + "</div></a>";
                    }
                    $("#distictDivList").html("");
                    $("#distictDivList").html(str);
                }
            });
            $("#distictDiv").css('display', 'block');
        }
        function setDistrict(districtName, districtId) {
            $("#districtId").val(districtId);
            $("#districtName").val(districtName);
            $("#districtNameSpan").html(districtName);

            $("#distictDiv").css('display', 'none');
            $("#cityDiv").css('display', 'none');
        }

        function showCity() {
            $("#cityDiv").css('display', 'block');
        }


        function getDistrict(provinceId, cityId, cityName) {
            $("#provinceId").val(provinceId);
            $("#cityId").val(cityId);
            $("#cityName").val(cityName);
            $("#cityNameSpan").html(cityName);
            $("#districtId").val("");
            $("#districtName").val("");
            $("#districtNameSpan").html("");

            $("#cityDiv").css('display', 'none');
        }
    </script>
</head>

<body class="graybg">
<!--wrap start-->
<section class="wrap">
    <!--隐藏省市区的选择
    请勿修改id 已经省市区对应的id
    -->
    <section class="markbg" id="cityDiv" style="display:none">
        <div class="wbg sortdropdowncon2 sortarea">
            <c:import url="/select_city.htm"/>
        </div>
    </section>
    <section class="markbg" id="distictDiv" style="display:none">
        <div class="wbg sortdropdowncon2 sortarea" id="forImportCityId">
            <section class="wrap">
                <section class="titlebar">
                    选择地点
                </section>
                <section class="subtitle">区县</section>
                <section class="listarea">
                    <div id="distictDivList"></div>
                </section>
            </section>
        </div>
    </section>
    <!--隐藏省市区的选择-->

    <section class="titlebar">发布地板-添加详情</section>
    <section class="addarea">
        <form action="" method="post" id="add_diban_from" onsubmit="return false;">
            <div class="add_sub_title">已选分类</div>
            <a href="<%=request.getContextPath()%>/select_diban.htm">
                <div class="inputbg lh120 p30all">
                    ${dibanInfo.goodsTypeName}
                    <input name="goodsType" value="${dibanInfo.goodsType}" type="hidden">
                    <input name="goodsTypeName" value="${dibanInfo.goodsTypeName}" type="hidden">
                    <i class="iconfont graytext right">&#xe603;</i>
                </div>
            </a>
            <div class="add_sub_title mt30">个人信息</div>
            <div class="inputbg p30left">
                <div class="inputarea line">
                    <div class="inputname">您的姓名</div>
                    <div class="inputline"></div>
                    <div class="inputform"><input type="text" name="userName" class="formstyle w100" required="required"
                                                  value="${dibanInfo.userName}"/>
                    </div>
                </div>
                <div class="inputarea">
                    <div class="inputname">联系电话</div>
                    <div class="inputline"></div>
                    <div class="inputform"><input type="text" name="telePhone" class="formstyle w100" id="telePhone"
                                                  required="required" value="${dibanInfo.telePhone}"/></div>
                </div>
            </div>
            <div class="add_sub_title mt30">物品信息</div>
            <div class="inputbg p30left">
                <%--<div class="inputarea line">
                    <div class="inputname">品类</div>
                    <div class="inputline"></div>
                    <a href="javascript:void(0)">
                        <div class="inputform"><i class="iconfont right graytext">&#xe603;</i></div>
                    </a>
                </div>--%>
                <div class="inputarea line">
                    <div class="inputname">木材种类</div>
                    <div class="inputline"></div>
                    <div class="inputform"><input type="text" class="formstyle w100" required="required"
                                                  name="materialQuality" value="${dibanInfo.materialQuality}"/>
                    </div>
                </div>
                <div class="inputarea line">
                    <div class="inputname">数量(m<sup>2</sup>)</div>
                    <div class="inputline"></div>
                    <div class="inputform"><input type="text" class="formstyle w100" required="required" name="qty" id="qty"
                                                  value="${dibanInfo.qty}"/></div>
                </div>
                <div class="inputarea">
                    <div class="inputname">已用年数</div>
                    <div class="inputline"></div>
                    <div class="inputform"><input type="text" class="formstyle w100" required="required"
                                                  name="usedYears" id="usedYears" value="${dibanInfo.usedYears}"/></div>
                </div>
            </div>
            <div class="add_sub_title mt30">规格（单片完整地板）</div>
            <div class="inputbg p30left">
                <div class="inputarea line">
                    <div class="inputname">长(mm)</div>
                    <div class="inputline"></div>
                    <div class="inputform"><input type="text" class="formstyle w100" required="required" name="length" id="length"
                                                  value="${dibanInfo.leigth.intValue()}"/></div>
                </div>
                <div class="inputarea line">
                    <div class="inputname">宽(mm)</div>
                    <div class="inputline"></div>
                    <div class="inputform"><input type="text" class="formstyle w100" required="required" name="width" id="width"
                                                  value="${dibanInfo.width.intValue()}"/></div>
                </div>
                <div class="inputarea">
                    <div class="inputname">高(mm)</div>
                    <div class="inputline"></div>
                    <div class="inputform"><input type="text" class="formstyle w100" required="required" name="height" id="height"
                                                  value="${dibanInfo.height.intValue()}"/></div>
                </div>
            </div>
            <div class="add_sub_title mt30">地址</div>
            <div class="inputbg p30left">
                <%--<div class="inputarea line">
                    <div class="inputname">省</div>
                    <div class="inputline"></div>
                    <a href="javascript:void(0)">
                        <div class="inputform"><i class="iconfont right graytext">&#xe603;</i></div>
                    </a>
                </div>--%>
                <div class="inputarea line">
                    <div class="inputname">省市</div>
                    <div class="inputline"></div>
                    <a href="javascript:void(0)" onclick="showCity()">
                        <div class="inputform"><span id="cityNameSpan">${dibanInfo.cityName}</span><i
                                class="iconfont right graytext">
                            &#xe603;</i></div>
                        <input type="hidden" name="cityId" id="cityId" value="${dibanInfo.cityId}" required="required"/>
                        <input type="hidden" name="cityName" id="cityName" value="${dibanInfo.cityName}"/>
                        <input type="hidden" name="provinceId" id="provinceId" value="${dibanInfo.provinceId}"/>
                    </a>
                </div>
                <div class="inputarea line">
                    <div class="inputname">区县</div>
                    <div class="inputline"></div>
                    <a href="javascript:void(0)" onclick="forDistrict();">
                        <div class="inputform"><span id="districtNameSpan">${dibanInfo.districtName}</span><i
                                class="iconfont right graytext">
                            &#xe603;</i></div>
                        <input type="hidden" name="districtId" id="districtId" value="${dibanInfo.districtId}"
                               required="required"/>
                        <input type="hidden" name="districtName" id="districtName" value="${dibanInfo.districtName}"/>
                    </a>
                </div>
                <div class="inputarea line">
                    <div class="inputname">详细地址</div>
                    <div class="inputline"></div>
                    <div class="inputform"><input type="text" class="formstyle w100" required="required" name="address"
                                                  value="${dibanInfo.address}"/></div>
                </div>
                <div class="inputarea line">
                    <div class="inputname">楼层</div>
                    <div class="inputline"></div>
                    <div class="inputform"><input type="text" class="formstyle w100" required="required" name="floors" id="floors"
                                                  value="${dibanInfo.floors}"/></div>
                </div>
                <div class="inputarea line">
                    <div class="inputname">是否有电梯</div>
                    <div class="inputline"></div>
                    <a href="javascript:void(0)">
                        <div class="inputform">
                            <c:if test="${dibanInfo.elevatorFlag eq '1'}">
                                <a href="javascript:checkelevatorFlag(1);" class="selectdone" id="elevatorFlagY">是</a>
                                <a href="javascript:checkelevatorFlag(0);" class="selectnormal" id="elevatorFlagN">否</a>
                                <input type="hidden" name="elevatorFlag" id="elevatorFlag"
                                       value="${dibanInfo.elevatorFlag}">
                            </c:if>
                            <c:if test="${empty dibanInfo.elevatorFlag or (dibanInfo.elevatorFlag eq '0') }">
                                <a href="javascript:checkelevatorFlag(1);" class="selectnormal mr6p" id="elevatorFlagY">是</a>
                                <a href="javascript:checkelevatorFlag(0);" class="selectdone" id="elevatorFlagN">否</a>
                                <input type="hidden" name="elevatorFlag" id="elevatorFlag" value="0">
                            </c:if>
                        </div>
                    </a>
                </div>
                <div style="display:none">
                <div class="inputarea line">
                    <div class="inputname">销售底价</div>
                    <div class="inputline"></div>
                    <div class="inputform"><input type="text" class="formstyle w100" required="required"
                                                  name="expectPriceLow"
                                                  value="0"/></div>
                </div>
                <div class="inputarea line">
                    <div class="inputname">期望价格</div>
                    <div class="inputline"></div>
                    <div class="inputform"><input type="text" class="formstyle w100" required="required"
                                                  name="expectPriceHigh"
                                                  value="0"/></div>
                </div>
                </div>
            </div>
            <div class="add_sub_title mt30">宝贝照片<font class="smalltextR right">**拍照如遇异常，请选择手机相册照片上传。</font></div>
            <div class="inputbg p30left p30bottom">
                <div class="inputarea line">
                    <div class="infosubtitle">细节照片（请在大约是您膝盖高度位置对地板拍摄）</div>
                </div>
                <div class="takephotoarea">
                    <input type="hidden" value="${dibanInfo.goodsPictureId}" id="goodsPictureId" name="goodsPictureId"><%--图片批次id--%>
                    <div id="chooseImageSelf1" class="photoshow mr5p btnphoto">
                        <c:choose>
                            <c:when test="${!empty goodsUrlMap.goodsurl1}">
                                <input type="hidden" id="goodsId1" name="goodsId1" value="${goodsUrlMap.goodsId1}">
                                <div class="delbtn"><img src="<%=request.getContextPath()%>/moibleStyle/img/del_btn.png"
                                                         width="100%" height="100%" onclick="delChooseImage(1)"/>
                                </div>
                                <img src="${realPath}${goodsUrlMap.goodsurl1}" width="100%" height="100%"/>
                            </c:when>
                            <c:otherwise>
                                <i class="iconfont graytext">&#xe606;</i>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div id="chooseImageSelf2" class="photoshow mr5p btnphoto">
                        <c:choose>
                            <c:when test="${!empty goodsUrlMap.goodsurl2}">
                                <input type="hidden" id="goodsId2" name="goodsId2" value="${goodsUrlMap.goodsId2}">
                                <div class="delbtn"><img src="<%=request.getContextPath()%>/moibleStyle/img/del_btn.png"
                                                         width="100%" height="100%" onclick="delChooseImage(2)"/>
                                </div>
                                <img src="${realPath}${goodsUrlMap.goodsurl2}" width="100%" height="100%"/>
                            </c:when>
                            <c:otherwise>
                                <i class="iconfont graytext">&#xe606;</i>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div id="chooseImageSelf3" class="photoshow btnphoto">
                        <c:choose>
                        <c:when test="${!empty goodsUrlMap.goodsurl3}">
                        <input type="hidden" id="goodsId3" name="goodsId3" value="${goodsUrlMap.goodsId3}">
                        <div class="delbtn"><img src="<%=request.getContextPath()%>/moibleStyle/img/del_btn.png"
                                                 width="100%" height="100%" onclick="delChooseImage(3)"/>
                        </div>
                        <img src="${realPath}${goodsUrlMap.goodsurl3}" width="100%" height="100%"/>
                    </div>
                    </c:when>
                    <c:otherwise>
                        <i class="iconfont graytext">&#xe606;</i>
                    </c:otherwise>
                    </c:choose>
                </div>

                <%--<button class="btnphoto mr6p" id="chooseImage" style=""><i class="iconfont graytext">&#xe606;</i></button>
                <button class="btnphoto"><i class="iconfont graytext">&#xe608;</i></button>--%>
            </div>
            <div class="inputarea line">
                <div class="infosubtitle">全景照片（请在房间角落从您胸口高度对地板进行拍摄）</div>
            </div>
            <div class="takephotoarea">
                <input type="hidden" value="${dibanInfo.panoramaPictureId}" id="panoramaPictureId"
                       name="panoramaPictureId"><%--图片批次id--%>
                <div id="chooseImageAll1" class="photoshow mr5p btnphoto">
                    <c:choose>
                        <c:when test="${!empty panoUrlMap.panoramaUrl1}">
                            <input type="hidden" id="panoramaId1" name="panoramaId1"
                                   value="${panoUrlMap.panoramaId1}">
                            <div class="delbtn"><img src="<%=request.getContextPath()%>/moibleStyle/img/del_btn.png"
                                                     width="100%" height="100%" onclick="delChooseImage(4)"/>
                            </div>
                            <img src="${realPath}${panoUrlMap.panoramaUrl1}" width="100%" height="100%"/>
                        </c:when>
                        <c:otherwise>
                            <i class="iconfont graytext">&#xe606;</i>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div id="chooseimage2All" class="photoshow mr5p btnphoto">
                    <c:choose>
                        <c:when test="${!empty panoUrlMap.panoramaUrl2}">
                            <input type="hidden" id="panoramaId2" name="panoramaId2"
                                   value="${panoUrlMap.panoramaId2}">
                            <div class="delbtn"><img src="<%=request.getContextPath()%>/moibleStyle/img/del_btn.png"
                                                     width="100%" height="100%" onclick="delChooseImage(5)"/>
                            </div>
                            <img src="${realPath}${panoUrlMap.panoramaUrl2}" width="100%" height="100%"/>
                        </c:when>
                        <c:otherwise>
                            <i class="iconfont graytext">&#xe606;</i>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div id="chooseImageAll3" class="photoshow btnphoto">
                    <c:choose>
                        <c:when test="${!empty panoUrlMap.panoramaUrl3}">
                            <input type="hidden" id="panoramaId3" name="panoramaId3"
                                   value="${panoUrlMap.panoramaId3}">
                            <div class="delbtn"><img src="<%=request.getContextPath()%>/moibleStyle/img/del_btn.png"
                                                     width="100%" height="100%" onclick="delChooseImage(6)"/>
                            </div>
                            <img src="${realPath}${panoUrlMap.panoramaUrl3}" width="100%" height="100%"/>
                        </c:when>
                        <c:otherwise>
                            <i class="iconfont graytext">&#xe606;</i>
                        </c:otherwise>
                    </c:choose>
                </div>
                <%--<div class="photoshow mr6p">
                    <div class="delbtn"><a href="javascript:void(0)"><img src="<%=request.getContextPath()%>/moibleStyle/img/del_btn.png" width="100%"
                                                         height="100%;"/></a></div>
                    <img src="<%=request.getContextPath()%>/moibleStyle/img/w_temp.png" width="100%" height="100%"/>
                </div>
                <button class="btnphoto mr6p"><i class="iconfont graytext">&#xe606;</i></button>
                <button class="btnphoto"><i class="iconfont graytext">&#xe608;</i></button>--%>
            </div>

            <div class="inputarea line">
                <div class="infosubtitle">全景照片2<!-- （请您站在刚才拍照位置的对角位置再拍一张全景照） --></div>
            </div>
            <div class="takephotoarea">
                <input type="hidden" value="${dibanInfo.panoramaPictureId}" id="panoramaPictureId2"
                       name="panoramaPictureId2"><%--图片批次id--%>
                <div id="chooseimage2All1" class="photoshow mr5p btnphoto">
                    <c:choose>
                        <c:when test="${!empty panoUrlMap2.panoramaUrl21}">
                            <input type="hidden" id="panoramaId21" name="panoramaId21"
                                   value="${panoUrlMap2.panoramaId21}">
                            <div class="delbtn"><img src="<%=request.getContextPath()%>/moibleStyle/img/del_btn.png"
                                                     width="100%" height="100%" onclick="delChooseImage(7)"/>
                            </div>
                            <img src="${realPath}${panoUrlMap2.panoramaUrl21}" width="100%" height="100%"/>
                        </c:when>
                        <c:otherwise>
                            <i class="iconfont graytext">&#xe606;</i>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div id="chooseimage2All2" class="photoshow mr5p btnphoto">
                    <c:choose>
                        <c:when test="${!empty panoUrlMap2.panoramaUrl22}">
                            <input type="hidden" id="panoramaId22" name="panoramaId22"
                                   value="${panoUrlMap2.panoramaId22}">
                            <div class="delbtn"><img src="<%=request.getContextPath()%>/moibleStyle/img/del_btn.png"
                                                     width="100%" height="100%" onclick="delChooseImage(8)"/>
                            </div>
                            <img src="${realPath}${panoUrlMap2.panoramaUrl22}" width="100%" height="100%"/>
                        </c:when>
                        <c:otherwise>
                            <i class="iconfont graytext">&#xe606;</i>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div id="chooseimage2All3" class="photoshow btnphoto">
                    <c:choose>
                        <c:when test="${!empty panoUrlMap2.panoramaUrl23}">
                            <input type="hidden" id="panoramaId23" name="panoramaId23"
                                   value="${panoUrlMap2.panoramaId23}">
                            <div class="delbtn"><img src="<%=request.getContextPath()%>/moibleStyle/img/del_btn.png"
                                                     width="100%" height="100%" onclick="delChooseImage(9)"/>
                            </div>
                            <img src="${realPath}${panoUrlMap2.panoramaUrl23}" width="100%" height="100%"/>
                        </c:when>
                        <c:otherwise>
                            <i class="iconfont graytext">&#xe606;</i>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            </div>
            <div class="add_sub_title mt30">宝贝描述</div>
            <div class="inputname" style="display: none;">宝贝描述</div>
            <div class="inputbg p30all">
                <textarea rows="4" class="formstylemore" placeholder="请简要填写宝贝介绍" name="goodsDesc"
                          required="required">${dibanInfo.goodsDesc}</textarea>
            </div>
            <div style="display:none">
            <div class="add_sub_title">录入语音</div>
            <a href="javascript:void(0)" id="recording" data-status="startrecord">
                <div class="inputbg lh120 p30all graytext2" id="recordDiv">
                    添加语音
                    <i class="iconfont graytext right">&#xe603;</i>
                </div>
            </a>
            <a href="javascript:void(0)" id="listen">
                <div class="inputbg lh120 p30all graytext2">
                    听语音
                    <i class="iconfont graytext right">&#xe603;</i>
                </div>
            </a>
            </div>
            <div class="add_sub_title mt30"><!-- 可交易日期段 -->什么时候可以来回收地板？</div>
            <div class="inputbg p30left">
                <div class="inputarea line">
                    <div class="inputname">起始日期</div>
                    <div class="inputline"></div>
                    <div class="inputform"><input type="date" class="formstyle2 w100" name="validStartDate"
                                                  value="${dibanInfo.validStartDate}"
                                                  required="required" placeholder="选择起始时间"/></div>
                </div>
                <div class="inputarea line">
                    <div class="inputname">截止日期</div>
                    <div class="inputline"></div>
                    <div class="inputform"><input type="date" class="formstyle2 w100" name="validEndDate"
                                                  value="${dibanInfo.validEndDate}"
                                                  required="required" placeholder="选择结束时间"/></div>
                </div>
            </div>
            <div class="add_sub_title"><!-- 可交易时间段 -->什么时间点方便回收？</div>
            <div class="selectarea">
                <c:if test="${dibanInfo.amFlag eq '1'}">
                    <a href="javascript:checkthFlag('am');" class="selectdone2" id="thfalg_am">09:00~12:00</a>
                </c:if>
                <c:if test="${empty dibanInfo.amFlag or (dibanInfo.amFlag eq '0') }">
                    <a href="javascript:checkthFlag('am');" class="selectnormal2 " id="thfalg_am">09:00~12:00</a>
                </c:if>
                <c:if test="${dibanInfo.pmFlag eq '1'}">
                    <a href="javascript:checkthFlag('pm');" class="selectdone2" id="thfalg_pm">13:00~18:00</a>
                </c:if>
                <c:if test="${empty dibanInfo.pmFlag or (dibanInfo.pmFlag eq '0') }">
                    <a href="javascript:checkthFlag('pm');" class="selectnormal2" id="thfalg_pm">13:00~18:00</a>
                </c:if>
                <c:if test="${dibanInfo.eveFlag eq '1'}">
                    <a href="javascript:checkthFlag('evn');" class="selectdone2" id="thfalg_evn">19:00~21:00</a>
                </c:if>
                <c:if test="${empty dibanInfo.eveFlag or (dibanInfo.eveFlag eq '0') }">
                    <a href="javascript:checkthFlag('evn');" class="selectnormal2" id="thfalg_evn">19:00~21:00</a>
                </c:if>
                <input type="hidden" name="amFlag" id="amFlag" value="${dibanInfo.amFlag}">
                <input type="hidden" name="pmFlag" id="pmFlag" value="${dibanInfo.pmFlag}">
                <input type="hidden" name="evnFlag" id="evnFlag" value="${dibanInfo.evnFlag}">
                <input type="hidden" name="thFalg" id="thFalg" value="${dibanInfo.thFalg}">
            </div>
            <div class="add_sub_title">以下日期后，同意按废品处理。</div>
            <div class="inputbg p30left">
                <div class="inputarea line">
                    <div class="inputname">废品日期</div>
                    <div class="inputline"></div>
                    <div class="inputform"><input type="date" class="formstyle w100" name="acceptWasteDate"
                                                  value="${dibanInfo.acceptWasteDate}"
                                                  placeholder="选择日期后, 我接受本物品按废品回收处理。"/></div>
                </div>
            </div>
            </div>
            <div id="errorMessageTip" style="display: none;color: red;"></div>
            <input id="id" name="id" value="${dibanInfo.id}" type="hidden">
            <button type="button" class="mt30 btn upload" onclick="submitAjax();">提交</button>
        </form>
    </section>
</section>
<script src="<%=request.getContextPath()%>/moibleStyle/js/common.js"></script>
<script src="<%=request.getContextPath()%>/moibleStyle/js/woodrecycle.js"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script>

    function submitAjax() {
        CLICK_RESTRICT = true;
        checkInputRequired();
        if (!CLICK_RESTRICT)return false;
        checkTextareaRequired();
        if (!CLICK_RESTRICT)return false;
        var telePhone = $("#telePhone").val();
        if (telePhone.length != 11 || isNaN(telePhone)) {
            alert("请输入11位全数字联系电话");
            return false;
        }
        var qty = $("#qty").val();
        if(!checkNumValue(qty,"数量")){
        	return false;
        }
        
        var usedYears = $("#usedYears").val();
        if(!checkNumValue(usedYears,"已用年数")){
        	return false;
        }
        
        var lengths = $("#length").val();
        if(!checkNumValue(lengths,"长度")){
        	return false;
        }
        
        var widths = $("#width").val();
        if(!checkNumValue(widths,"宽度")){
        	return false;
        }
        
        var heights = $("#height").val();
        if(!checkNumValue(heights,"高度")){
        	return false;
        }
        
        var floors = $("#floors").val();
        if(!checkNumValue(floors,"楼层")){
        	return false;
        }
        //表单提交
        var woodSavePath = "<%=request.getContextPath()%>/publishDibanGoodSave.htm";
        $.ajax({
            type: "POST",
            url: woodSavePath,
            data: $('#add_diban_from').serialize(),
            contentType: "application/x-www-form-urlencoded;charset=UTF-8",
            success: function (data) {
                alert(data.outResultReason);
                if (data.outResult == "1") {
                    //window.location.href = "<%=request.getContextPath()%>/woodList/list.htm";
                    //添加宝贝成功之后，转入“我的订单”页面
                    window.location.href = "<%=request.getContextPath()%>/myWoodOrder.htm?backType=add";
                }
            },
            error: function (data) {
                alert("error:" + data);
            }
        });
    }

    var voice = {
        localId: '',
        serverId: ''
    };
    var image = {
        localId: '',
        serverId: ''
    };
    $(function () {
        $.post("<c:url value="/wecaht/getWechatSign.htm"/>", {url: location.href.split('#')[0]}, function (response) {
            /*
             console.log(response.app_id);
             console.log(response.timestamp);
             console.log(response.wechat_noncestr);
             console.log(response.ticket);
             */
            wx.config({
                debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
                appId: response.app_id, // 必填，公众号的唯一标识
                timestamp: response.timestamp, // 必填，生成签名的时间戳
                nonceStr: response.wechat_noncestr, // 必填，生成签名的随机串
                signature: response.ticket,// 必填，签名，见附录1
                jsApiList: ['startRecord', 'stopRecord', 'playVoice', 'uploadVoice', 'downloadVoice', 'chooseImage', 'uploadImage'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2 onMenuShareAppMessage
            });
            wx.error(function (res) {
                alert("error:" + res.errMsg);
            });
        })
        /*
         //开始录音
         $("#recording").bind("touchstart", function () {
         wx.startRecord({
         cancel: function () {
         alert('用户拒绝授权录音');
         }
         });
         event.preventDefault();
         })
         //结束录音
         $("#recording").bind("touchend", function () {
         wx.stopRecord({
         success: function (res) {
         voice.localId = res.localId;
         //alert(res.localId);
         //结束录音之后,上传服务器
         uploadMediaWX();
         },
         fail: function (res) {
         //alert(JSON.stringify(res));
         }
         });
         });*/

        $("#recording").bind("click", function () {
            var tar = $("#recording");
            var btnStatus = tar.attr('data-status');
            if (btnStatus == 'startrecord') {
                $('#recordDiv').html('停止录音<i class="iconfont graytext right">&#xe603;</i>');
                tar.attr({'data-status': 'stoprecord'});
                wx.startRecord({
                    cancel: function () {
                        alert('用户拒绝授权录音');
                    }
                });
                event.preventDefault();
            } else {
                $('#recordDiv').html('开始录音<i class="iconfont graytext right">&#xe603;</i>');
                tar.attr({'data-status': 'startrecord'});
                wx.stopRecord({
                    success: function (res) {
                        voice.localId = res.localId;
                        //alert(res.localId);
                        //结束录音之后,上传服务器
                        uploadMediaWX();
                    },
                    fail: function (res) {
                        //alert(JSON.stringify(res));
                    }
                });
                event.preventDefault();
            }
        });
        $("#listen").bind("touchend", function () {
            if (voice.localId == '') {
                alert('请先录制一段声音');
                return;
            }
            wx.playVoice({
                localId: voice.localId
            });
        })

        //拍照或从手机相册中选图接口
        $("#chooseImageSelf1").bind("click", function () {
            //id传过来
            //1.弹出确认层,提示是否删除当前图并重新选择
            //2.点了是之后,不做删除,ajax更新id为无效标记
            //3.当选择图片再次调用上传时候,同步做删除并根据pictreId新增数据
            var goodsId1 = $("#goodsId1").val();
            if (goodsId1 != null && goodsId1 != '') {
                if (!confirm("是否删除当前图片并重新选择?")) {
                    return false;
                }
                $.post("<c:url value="/delMediaFromWX.htm"/>", {
                    goodsPictureId: goodsId1,
                    type: "imageSelf"
                }, function (response) {
                })
            }

            wx.chooseImage({
                count: 1, // 默认9
                sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
                sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
                success: function (res) {
                    image.localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
                    //开始.改变 html.
                    var sb = '<div class="delbtn">' +
                            '<img src="<%=request.getContextPath()%>/moibleStyle/img/del_btn.png" width="100%" height="100%" onclick="delChooseImage(1)"/></div>' +
                            '<img src="' + image.localIds + '" width="100%" height="100%"/>';
                    $("#chooseImageSelf1").html(sb);
                    var goodsPictureId = $("#goodsPictureId").val();
                    uploadImageWX("imageSelf", goodsPictureId + '');//上传
                },
                fail: function (res) {
                    alert("error:" + res);
                }
            });
        })
        $("#chooseImageSelf2").bind("click", function () {
            var goodsId2 = $("#goodsId2").val();
            if (goodsId2 != null && goodsId2 != '') {
                if (!confirm("是否删除当前图片并重新选择?")) {
                    return false;
                }
                $.post("<c:url value="/delMediaFromWX.htm"/>", {
                    goodsPictureId: goodsId2,
                    type: "imageSelf"
                }, function (response) {
                })
            }

            wx.chooseImage({
                count: 1, // 默认9
                sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
                sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
                success: function (res) {
                    image.localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
                    //开始.改变 html.
                    var sb = '<div class="delbtn">' +
                            '<img src="<%=request.getContextPath()%>/moibleStyle/img/del_btn.png" width="100%" height="100%" onclick="delChooseImage(2)"/></div>' +
                            '<img src="' + image.localIds + '" width="100%" height="100%"/>';
                    $("#chooseImageSelf2").html(sb);
                    var goodsPictureId = $("#goodsPictureId").val();
                    uploadImageWX("imageSelf", goodsPictureId + '');//上传
                }
            });
        })
        $("#chooseImageSelf3").bind("click", function () {
            var goodsId3 = $("#goodsId3").val();
            if (goodsId3 != null && goodsId3 != '') {
                if (!confirm("是否删除当前图片并重新选择?")) {
                    return false;
                }
                $.post("<c:url value="/delMediaFromWX.htm"/>", {
                    goodsPictureId: goodsId3,
                    type: "imageSelf"
                }, function (response) {
                })
            }
            wx.chooseImage({
                count: 1, // 默认9
                sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
                sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
                success: function (res) {
                    image.localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
                    //开始.改变 html.
                    var sb = '<div class="delbtn">' +
                            '<img src="<%=request.getContextPath()%>/moibleStyle/img/del_btn.png" width="100%" height="100%" onclick="delChooseImage(3)"/></div>' +
                            '<img src="' + image.localIds + '" width="100%" height="100%"/>';
                    $("#chooseImageSelf3").html(sb);
                    var goodsPictureId = $("#goodsPictureId").val();
                    uploadImageWX("imageSelf", goodsPictureId + '');//上传
                }
            });
        })
        $("#chooseImageAll1").bind("click", function () {
            var panoramaId1 = $("#panoramaId1").val();
            if (panoramaId1 != null && panoramaId1 != '') {
                if (!confirm("是否删除当前图片并重新选择?")) {
                    return false;
                }
                $.post("<c:url value="/delMediaFromWX.htm"/>", {
                    goodsPictureId: panoramaId1,
                    type: "imageAll"
                }, function (response) {
                })
            }
            wx.chooseImage({
                count: 1, // 默认9
                sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
                sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
                success: function (res) {
                    image.localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
                    //开始.改变 html.
                    var sb = '<div class="delbtn">' +
                            '<img src="<%=request.getContextPath()%>/moibleStyle/img/del_btn.png" width="100%" height="100%" onclick="delChooseImage(4)"/></div>' +
                            '<img src="' + image.localIds + '" width="100%" height="100%"/>';
                    $("#chooseImageAll1").html(sb);
                    var panoramaPictureId = $("#panoramaPictureId").val();
                    uploadImageWX("imageAll", panoramaPictureId + '');//上传
                }
            });
        })
        $("#chooseimage2All").bind("click", function () {
            var panoramaId2 = $("#panoramaId2").val();
            if (panoramaId2 != null && panoramaId2 != '') {
                if (!confirm("是否删除当前图片并重新选择?")) {
                    return false;
                }
                $.post("<c:url value="/delMediaFromWX.htm"/>", {
                    goodsPictureId: panoramaId2,
                    type: "imageAll"
                }, function (response) {
                })
            }
            wx.chooseImage({
                count: 1, // 默认9
                sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
                sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
                success: function (res) {
                    image.localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
                    //开始.改变 html.
                    var sb = '<div class="delbtn">' +
                            '<img src="<%=request.getContextPath()%>/moibleStyle/img/del_btn.png" width="100%" height="100%" onclick="delChooseImage(5)"/></div>' +
                            '<img src="' + image.localIds + '" width="100%" height="100%"/>';
                    $("#chooseimage2All").html(sb);
                    var panoramaPictureId = $("#panoramaPictureId").val();
                    uploadImageWX("imageAll", panoramaPictureId + '');//上传
                }
            });
        })
        $("#chooseImageAll3").bind("click", function () {
            var panoramaId3 = $("#panoramaId3").val();
            if (panoramaId3 != null && panoramaId3 != '') {
                if (!confirm("是否删除当前图片并重新选择?")) {
                    return false;
                }
                $.post("<c:url value="/delMediaFromWX.htm"/>", {
                    goodsPictureId: panoramaId3,
                    type: "imageAll"
                }, function (response) {
                })
            }
            wx.chooseImage({
                count: 1, // 默认9
                sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
                sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
                success: function (res) {
                    image.localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
                    //开始.改变 html.
                    var sb = '<div class="delbtn">' +
                            '<img src="<%=request.getContextPath()%>/moibleStyle/img/del_btn.png" width="100%" height="100%" onclick="delChooseImage(6)"/></div>' +
                            '<img src="' + image.localIds + '" width="100%" height="100%"/>';
                    $("#chooseImageAll3").html(sb);
                    var panoramaPictureId = $("#panoramaPictureId").val();
                    uploadImageWX("imageAll", panoramaPictureId + '');//上传
                }
            });
        })

        //全景照片2
        $("#chooseimage2All1").bind("click", function () {
            var panoramaId21 = $("#panoramaId21").val();
            if (panoramaId21 != null && panoramaId21 != '') {
                if (!confirm("是否删除当前图片并重新选择?")) {
                    return false;
                }
                $.post("<c:url value="/delMediaFromWX.htm"/>", {
                    goodsPictureId: panoramaId21,
                    type: "image2All"
                }, function (response) {
                })
            }
            wx.chooseImage({
                count: 1, // 默认9
                sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
                sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
                success: function (res) {
                    image.localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
                    //开始.改变 html.
                    var sb = '<div class="delbtn">' +
                            '<img src="<%=request.getContextPath()%>/moibleStyle/img/del_btn.png" width="100%" height="100%" onclick="delChooseImage(7)"/></div>' +
                            '<img src="' + image.localIds + '" width="100%" height="100%"/>';
                    $("#chooseimage2All1").html(sb);
                    var panoramaPictureId2 = $("#panoramaPictureId2").val();
                    uploadImageWX("image2All", panoramaPictureId2 + '');//上传
                }
            });
        })
        $("#chooseimage2All2").bind("click", function () {
            var panoramaId22 = $("#panoramaId22").val();
            if (panoramaId22 != null && panoramaId22 != '') {
                if (!confirm("是否删除当前图片并重新选择?")) {
                    return false;
                }
                $.post("<c:url value="/delMediaFromWX.htm"/>", {
                    goodsPictureId: panoramaId22,
                    type: "image2All"
                }, function (response) {
                })
            }
            wx.chooseImage({
                count: 1, // 默认9
                sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
                sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
                success: function (res) {
                    image.localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
                    //开始.改变 html.
                    var sb = '<div class="delbtn">' +
                            '<img src="<%=request.getContextPath()%>/moibleStyle/img/del_btn.png" width="100%" height="100%" onclick="delChooseImage(8)"/></div>' +
                            '<img src="' + image.localIds + '" width="100%" height="100%"/>';
                    $("#chooseimage2All2").html(sb);
                    var panoramaPictureId2 = $("#panoramaPictureId2").val();
                    uploadImageWX("image2All", panoramaPictureId2 + '');//上传
                }
            });
        })
        $("#chooseimage2All3").bind("click", function () {
            var panoramaId23 = $("#panoramaId23").val();
            if (panoramaId23 != null && panoramaId23 != '') {
                if (!confirm("是否删除当前图片并重新选择?")) {
                    return false;
                }
                $.post("<c:url value="/delMediaFromWX.htm"/>", {
                    goodsPictureId: panoramaId23,
                    type: "image2All"
                }, function (response) {
                })
            }
            wx.chooseImage({
                count: 1, // 默认9
                sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
                sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
                success: function (res) {
                    image.localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
                    //开始.改变 html.
                    var sb = '<div class="delbtn">' +
                            '<img src="<%=request.getContextPath()%>/moibleStyle/img/del_btn.png" width="100%" height="100%" onclick="delChooseImage(9)"/></div>' +
                            '<img src="' + image.localIds + '" width="100%" height="100%"/>';
                    $("#chooseimage2All3").html(sb);
                    var panoramaPictureId2 = $("#panoramaPictureId2").val();
                    uploadImageWX("image2All", panoramaPictureId2 + '');//上传
                }
            });
        })
        //全景照片2

        //预览图片接口
        $("#previewImage").bind("click", function () {
            wx.previewImage({
                current: '', // 当前显示图片的http链接
                urls: [image.localIds] // 需要预览的图片http链接列表
            });
        })
    });
    function checkelevatorFlag(flag) {
        if (flag == '1') {
            $("#elevatorFlagY").removeClass().addClass("selectdone mr6p");
            $("#elevatorFlagN").removeClass().addClass("selectnormal");
            $("#elevatorFlag").val("1");
        } else {
            $("#elevatorFlagN").removeClass().addClass("selectdone");
            $("#elevatorFlagY").removeClass().addClass("selectnormal mr6p");
            $("#elevatorFlag").val("0");
        }
    }
    function checkthFlag(flag) {
        if ($("#thfalg_" + flag).hasClass('selectdone2')) {
            if (flag == 'pm') {
                $("#thfalg_" + flag).removeClass().addClass("selectnormal2");
            } else {
                $("#thfalg_" + flag).removeClass().addClass("selectnormal2");
            }
            $("#" + flag + "Flag").val("0");
            $("#thFalg").val($("#thFalg").val().replace(flag, ""));
        } else {
            $("#thfalg_" + flag).removeClass().addClass("selectdone2");
            $("#" + flag + "Flag").val("1");
            if ($("#thFalg").val().indexOf(flag) < 0) {
                $("#thFalg").val($("#thFalg").val() + flag);
            }
        }
    }
    function uploadMediaWX() {
        if (voice.localId != null) {
            wx.uploadVoice({
                localId: voice.localId, // 需要上传的音频的本地ID，由stopRecord接口获得
                isShowProgressTips: 1, // 默认为1，显示进度提示
                success: function (res) {
                    voice.serverId = res.serverId; // 返回音频的服务器端ID
                    //上面上传到微信服务器成功后,通过获取语音http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID
                    //需要的是media_id..这里传到后台去获取,
                    if (voice.serverId != null && voice.serverId != "") {
                        $.post("<c:url value="/getMediaFromWX.htm"/>", {
                            type: 'voice',
                            media_id: voice.serverId
                        }, function (response) {
                            //alert(response.resultMessage);//不做提示
                        })
                    }
                }
            });
        }
    }

    function uploadImageWX(type, pictureId) {
        if (image.localIds != null) {
            wx.uploadImage({
                localId: image.localIds[0], // 需要上传的图片的本地ID，由chooseImage接口获得
                isShowProgressTips: 1, // 默认为1，显示进度提示
                success: function (res) {
                    //alert("image.serverId:"+res.serverId);
                    image.serverId = res.serverId; // 返回图片的服务器端ID
                    if (image.serverId != null && image.serverId != "") {
                        $.post("<c:url value="/getMediaFromWX.htm"/>", {
                            type: type,
                            media_id: image.serverId,
                            pictureId: pictureId
                        }, function (response) {
                            //alert(response.resultMessage);//不做提示
                        })
                    }
                },
                error: function (data) {
                    alert("error:" + data);
                }
            });
        }
    }


    var parentwin = window.opener;
    $(function () {
        $(document).on('scroll', function (e) {
            var hash = window.location.hash;
            if (hash) {
                $('.subtitle:has(a[name][id])').css({
                    'margin-top': '0px'
                });
                $(hash).parent().css({
                    'margin-top': '51px'
                });
            }
        });
        $('.scroll-auto').css({
            'height': ($(window).height() > 1000 ? window.screen.height - 40 : $(window).height() - 30) + 'px'
        });
        $('.citylist a').on('click', function (e) {
            alert($(e.target).html());
        });
    });

</script>
</body>
</html>
