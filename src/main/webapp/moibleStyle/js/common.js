var sss = 0;
(function (doc, win) {
	var docEl = doc.documentElement,
		resizeEvt = 'orientationchange' in window ? 'orientationchange' : 'resize',
		recalc = function () {
			var clientWidth = docEl.clientWidth;
			if (!clientWidth) return;
			docEl.style.fontSize = 20 * (clientWidth / 750) + 'px';
		};
	sss = 20 * (docEl.clientWidth / 750);
	if (!doc.addEventListener) return;
	win.addEventListener(resizeEvt, recalc, false);
	doc.addEventListener('DOMContentLoaded', recalc, false);
})(document, window);

$(function(){
	$("#wrap").css("min-height", $(window).height());

	$("#main").on("mousedown touchstart", ".act", function(){
		$(this).addClass("active");
	}).on("mousemove touchmove", ".act", function(){
		$(this).removeClass("active");
	}).on("mouseup touchend", ".act", function(){
		$(this).removeClass("active");
	});

	if($("#spinLoad").size() > 0){
		var line = 10,
			leng = 8 * sss / 20,
			w = 4 * sss / 20,
			rad = 8 * sss / 20;
		$.fn.spin.presets.flower = {
			lines: parseInt(line),
			length: parseInt(leng),
			width: parseInt(w),
			radius: parseInt(rad)
		};
		$('#spinLoad').spin('flower', '#555');
	};

	$(".filRegion").click(function(){
		var i = $("i", this),
			obj = $(this);
		if(i.hasClass("fa-angle-down")){
			i.addClass("fa-angle-up").removeClass("fa-angle-down");
			$(".hpFilRegion").show();
			$(".popupbg").show().css("z-index", 1).one("click",function(){
				obj.trigger("click");
			});
		}else{
			i.addClass("fa-angle-down").removeClass("fa-angle-up");
			$(".hpFilRegion").hide();
			$(".popupbg").hide().removeAttr("style");
		}
	})

	$(".filBank").click(function(){
		var i = $("i", this),
			obj = $(this);
		if(i.hasClass("fa-angle-down")){
			i.addClass("fa-angle-up").removeClass("fa-angle-down");
			$(".selFilBank").show();
			$(".popupbg").show().css("z-index", 1).one("click",function(){
				obj.trigger("click");
			});
		}else{
			i.addClass("fa-angle-down").removeClass("fa-angle-up");
			$(".selFilBank").hide();
			$(".popupbg").hide().removeAttr("style");
		}
	})

	$(".storeSales").on("click","h2 span",function(){
		var i = $(this).index();
		$(this).addClass("act").siblings().removeClass("act");
		$("ul",".storeSales").removeClass("blur").eq(i).addClass("blur");
	});

	$(".storeSales").on("click",".btnUseRule",function(){
		var i = $("i", this);
		if(i.hasClass("fa-angle-down")){
			i.addClass("fa-angle-up").removeClass("fa-angle-down");
			$(this).parent().next(".coupRule").show();
		}else{
			i.addClass("fa-angle-down").removeClass("fa-angle-up");
			$(this).parent().next(".coupRule").hide();
		}
	});

	$(".shoppingFilter").on("click","h2",function(e){
		e.preventDefault();
		$(this).addClass("act").siblings("h2").removeClass("act");
	});

	$(".filStatus").click(function(e){
		e.preventDefault();
		var i = $("i", this),
			obj = $(this);
		if(i.hasClass("fa-angle-down")){
			i.addClass("fa-angle-up").removeClass("fa-angle-down");
			$(".selFilStatus").show();
			$(".popupbg").show().css("z-index", 1).one("click",function(){
				obj.trigger("click");
			});
		}else{
			i.addClass("fa-angle-down").removeClass("fa-angle-up");
			$(".selFilStatus").hide();
			$(".popupbg").hide().removeAttr("style");
		}
	})

	$(".selFilStatus").on("click","a",function(e){
		e.preventDefault();
		var val = $(this).html();
		$(this).parent().addClass("cur").siblings().removeClass("cur");
		$(".filStatus").find("span").html(val);
		$(".filStatus").trigger("click");
	});

	$(".filCycle").click(function(e){
		e.preventDefault();
		var i = $("i", this),
			obj = $(this);
		if(i.hasClass("fa-angle-down")){
			i.addClass("fa-angle-up").removeClass("fa-angle-down");
			$(".selFilCycle").show();
			$(".popupbg").show().css("z-index", 1).one("click",function(){
				obj.trigger("click");
			});
		}else{
			i.addClass("fa-angle-down").removeClass("fa-angle-up");
			$(".selFilCycle").hide();
			$(".popupbg").hide().removeAttr("style");
		}
	})

	$(".selFilCycle").on("click","a",function(e){
		e.preventDefault();
		var val = $(this).html();
		$(this).parent().addClass("cur").siblings().removeClass("cur");
		$(".filCycle").find("span").html(val);
		$(".filCycle").trigger("click");
	});

	$(".myCoupFilter").on("click","h2",function(e){
		e.preventDefault();
		$(this).addClass("act").siblings("h2").removeClass("act");
	});

});

$(window).load(function(){

	/* focus */
	if($(".hpFocus").size() > 0) {
		$(".hpFocus").flexslider({
			animation: "slide",
			directionNav : false
		});
	};

	/* Coupons */
	if($(".storeCoupons").size() > 0){
		$(".storeCoupons").each(function(index, element) {
			var obj = $("ul", this),
				li = obj.children();
			obj.width(li.innerWidth() * li.size());

		});
	};

	/* makeCoupons */
	if($(".makeCoupons").size() > 0) {
		$(".makeCoupons").flexslider({
			animation: "slide",
			slideshow : false,
			controlNav : false,
			prevText : '<i class="fa fa-angle-left"></i>',
			nextText : '<i class="fa fa-angle-right"></i>'
		});
	};
});
$(window).bind("resize",function(){
	$("#wrap").css("min-height", $(window).height());

	if($(".storeCoupons").size() > 0){
		$(".storeCoupons").each(function(index, element) {
			var obj = $("ul", this),
				li = obj.children();
			obj.width(li.innerWidth() * li.size());

		});
	};
});

$.extend({
	showPopup : function(obj){
	},
	closePopup : function(obj){
	}
});

$.fn.extend({
	touch : function(btn){
		return $(this).on("mousedown touchstart", btn ,function(){
			$(this).addClass("active");
		}).on("mousemove touchmove", btn ,function(){
			$(this).removeClass("active");
		}).on("mouseup touchend", btn ,function(){
			$(this).removeClass("active");
		});
	},
	touchNav : function(btn,fun){
		var touch = !1,
			obj = $(this);
		fun = fun || {};
		return $(this).on("mousedown touchstart", btn ,function(){
			touch = !0;
		}).on("mousemove touchmove", btn ,function(){
			touch = !1;
		}).on("mouseup touchend", btn ,function(){
			if(touch){
				touch = !1;
				$(this).addClass("active").siblings().removeClass("active");
				fun($(this));
			}
		});
	}
});
function isNum(num){
		  var reNum=/^\d*$/;
		  return(reNum.test(num));
} 

function checkNumValue(qty,displayname){
	if(isNaN(qty) || qty.trim()=="" || !isNum(qty)){
		alert(displayname+"必须是整数");	
		return false;
	}else{
		return true;	
	}	
}


