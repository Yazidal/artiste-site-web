/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(window).load(function () {

    var parametres_1 = {
        start: function (slider) {

            $(slider)
                    .find('img.lazy:eq(0)')
                    .each(function () {
                        var src = $(this).attr('data-original');
                        $(this).attr('src', src).removeAttr('data-original');
                    });
        },
        before: function (slider) {

            var slides = slider.slides,
                    index = slider.animatingTo,
                    $slide = $(slides[index]),
                    current = index,
                    nxt_slide = current + 1,
                    prev_slide = current - 1;

            $slide
                    .parent()
                    .find('img.lazy:eq(' + current + '), img.lazy:eq(' + prev_slide + '), img.lazy:eq(' + nxt_slide + ')')
                    .each(function () {
                        var src = $(this).attr('data-original');
                        $(this).attr('src', src).removeAttr('data-original');
                    });
        }
    };

    $.extend(parametres_1, {
        animation: "slide",
        align: 'center',
        controlNav: false,
        animationLoop: false,
        slideshow: false,
        sync: "#carousel"
    });

    var parametres_2 = {
        start: function (slider) {
            var slide_count = slider.count - 1;
            var img = $(slider).find('img.lazy');
            for (var i = 0; i <= slide_count && i < 9; i++) {
                var src = img.eq(i).attr('data-original');
                img.eq(i).attr('src', src).removeAttr('data-original');
            }
        },
        before: function (slider) {
            var index = slider.animatingTo,
                    current = index,
                    nxt_slide = (current * 9) + 9;

            var slide_count = slider.count - 1;

            var img = $(slider).find('img.lazy');
            for (var i = current; (i <= slide_count && i < nxt_slide); i++) {
                var src = img.eq(i).attr('data-original');
                if (src)
                    img.eq(i).attr('src', src).removeAttr('data-original');
            }
        }
    };

    $.extend(parametres_2, {
        animation: "slide",
        controlNav: false,
        animationLoop: false,
        slideshow: false,
        itemWidth: 156,
        itemMargin: 20,
        asNavFor: '#slider'});

    $('#carousel').flexslider(parametres_2);
    $('#slider').flexslider(parametres_1);

});