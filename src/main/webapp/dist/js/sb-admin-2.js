(function($) {
    // core chart function
    $.fn.extend({
        chart: function(opts) {
            var defaults = {
                    title: '',
                    subtitle: '',
                    xtitle: '',
                    ytitle: '',
                    suffix: '人',
                    categories: ['01:00', '02:00', '03:00', '04:00', '05:00', '06:00','07:00', '08:00', '09:00', '10:00', '11:00', '12:00', '13:00', '14:00', '15:00', '16:00', '17:00', '18:00', '19:00', '20:00', '21:00', '22:00', '23:00', '24:00'],
                    data: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
                },
                o = $.extend({}, defaults, opts || {});

            return this.each(function() {
                var axisTitleStyle = {
                    fontSize: '16px',
                    letterSpacing: '1px'
                };
                $(this).highcharts({
                    title: {
                        text: o.title
                    },
                    subtitle: {
                        text: o.subtitle
                    },
                    xAxis: {
                        title: {
                            text: o.xtitle,
                            style: axisTitleStyle
                        },
                        categories: o.categories
                    },
                    yAxis: {
                        title: {
                            text: o.ytitle,
                            style: axisTitleStyle
                        }
                    },
                    tooltip: {
                        formatter: function () {
                            return this.x + ': <b>' + this.y + '</b> ' + o.suffix;
                        }
                    },
                    series: [{
                        data: o.data
                    }]
                });
            });
        }
    });
})(jQuery);

$(function() {
    var $sideMenu = $('#side-menu');
    if ($sideMenu.length) $sideMenu.metisMenu();

    $('.collapse-title').each(function() {
        var $block = $(this).parent().parent();
        $block.on('focusout', function() {
            $block.addClass('collapse-read');
        });
    });

    $('.expansion').each(function() {
        var $this = $(this);
        var $button = $this.children('.expansion-button');
        $button.on('click', function() {
            $this.toggleClass('hover');
        });
    });

    // tab
    $('.tab-options').each(function() {
        var $this = $(this);
        var $opts = $this.children('li');
        var $items = $this.next('.tab-container').children('.tab-item');

        if ($opts.length > 1 && $items.length > 1) {
            $opts.on('click', function() {
                var $th = $(this);
                $th.add($items.eq($th.index()))
                    .addClass('selected').siblings().removeClass('selected');
            });
        }
    });

    // picture add
    function setPicturesHeight() {
        $picturesPic.each(function() {
            var $th = $(this);
            $th.css('height', $th.width() * 3 / 5);
        });
    }

    var $picturesPic = $('.pictures-pic');
    if ($picturesPic.length) {
        setPicturesHeight();
        $(window).resize(setPicturesHeight);
    }

    $('.direct-show-file').on('change', function() {
        var $this = $(this);
        var $img = $this.prev('.direct-show-img');

        $img.attr('src', $this.val());
        console.log($this[0].files[0].name, $this.val());
    });

});

//Loads the correct sidebar on window load,
//collapses the sidebar on window resize.
// Sets the min-height of #page-wrapper to window size
$(function() {
    $(window).bind("load resize", function() {
        topOffset = 60;
        width = (this.window.innerWidth > 0) ? this.window.innerWidth : this.screen.width;
        if (width < 768) {
            $('div.navbar-collapse').addClass('collapse');
            topOffset = 120; // 2-row-menu
        } else {
            $('div.navbar-collapse').removeClass('collapse');
        }

        height = ((this.window.innerHeight > 0) ? this.window.innerHeight : this.screen.height) - 1;
        height = height - topOffset;
        if (height < 1) height = 1;
        if (height > topOffset) {
            $("#page-wrapper").css("min-height", (height) + "px");
        }
    });

    var url = window.location;
    var element = $('ul.nav a').filter(function() {
        return this.href == url || url.href.indexOf(this.href) == 0;
    }).addClass('active').parent().parent().addClass('in').parent();
    if (element.is('li')) {
        element.addClass('active');
    }
});

// 校验邮箱地址
String.prototype.isMail = function () {
    return /^(?:[\w-]+\.?)*[\w-]+@(?:[\w-]+\.)+[\w]{2,3}$/.test(this);
};

//校验手机号码：必须以数字13,14,15,18,17开头，共11位
String.prototype.isMobileNum = function () {
    return /^1[34578]\d{9}$/.test(this);
};
//验证是否是qq
String.prototype.isQQ = function () {
    return /^[1-9][0-9]{4,9}$/.test(this);
};