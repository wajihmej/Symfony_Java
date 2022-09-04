(function($) {
    "use strict";
    // Document ready function     
    /*-------------------------------------
     animsition
     ------------------------------------- */
    if ($('.animsition').length > 0) {
        $(".animsition").animsition({
            inClass: 'fade-in',
            outClass: 'fade-out',
            inDuration: 1000,
            outDuration: 700,
            linkElement: '.menu-list a',
            loading: true,
            loadingParentElement: 'body',
            loadingClass: 'spinner',
            loadingInner: '<div class="bounce-bounce"></div><div class="bounce-bounce-bounce"></div>',
            timeout: false,
            timeoutCountdown: 5000,
            onLoadEvent: true,
            browser: ['animation-duration', '-webkit-animation-duration'],
            overlay: false,
            overlayClass: 'animsition-overlay-slide',
            overlayParentElement: 'body',
            transition: function(url) {
                window.location.href = url;
            }
        });
    }
    /*-------------------------------------
        magnificPopup
        ------------------------------------- */
    $(function() {


        /*-------------------------------------
         jquery Nav Scroll activation code
         ------------------------------------- */
        var onePageNav = $('.navOnePage');
        if (onePageNav.length) {
            $('.navOnePage').onePageNav({
                scrollOffset: 80

            });
        }



        if ($('.zoom-gallery').length) {

            $('.zoom-gallery').each(function() { // the containers for all your galleries

                $(this).magnificPopup({

                    delegate: 'a.bst-zoom', // the selector for gallery item

                    type: 'image',

                    gallery: {

                        enabled: true

                    }
                });

            });
        }

        /*-------------------------------------
         Fixing for hover effect at IOS
         ------------------------------------- */

        $('*').on('touchstart', function() {

            $(this).trigger('hover');

        }).on('touchend', function() {

            $(this).trigger('hover');

        });

        if ($('.gallery-wrapper').length) {

            $('.gallery-wrapper').magnificPopup({

                type: 'image',

                delegate: 'a',

                gallery: {

                    enabled: true

                }

            });

        }

        /*-------------------------------------
         Popup
         -------------------------------------*/
        if ($(".popup-youtube").length) {

            $('.popup-youtube').magnificPopup({
                disableOn: 700,
                type: 'iframe',
                mainClass: 'mfp-fade',
                removalDelay: 160,
                preloader: false,
                fixedContentPos: false
            });
        }
        $('#product-filter-trigger').on("click", function() {

            if ($(window).width() > '990') {

                $('#filter-parameter-wrapper').addClass('open');

                $('#product-filter-trigger i').removeClass('fa-bars');

                $('#product-filter-trigger i').addClass('fa-times');

            }

            return false;

        });
        $('#filter-parameter-wrapper .close').on("click", function() {
            $('#filter-parameter-wrapper').removeClass('open');
            return false;
        });
        $('#product-search-btn').on("click", function() {

            if ($(window).width() > '990') {

                $('.search-panel').addClass('open');

            }
            return false;
        });
        $('.pbox-search-area .close').on("click", function() {
            $('.search-panel').removeClass('open');
            return false;
        });

        /*-------------------------------------
        On click loadmore functionality 
        -------------------------------------*/

        $('.loadmore').on('click', 'a', function(e) {

            e.preventDefault();

            var _this = $(this),

                _parent = _this.parents('.classes-list-wrapper'),

                _target = _parent.find('.classes-list'),

                _set = _target.find('.classes-item.hidden').slice(0, 4); // Herre 4 is the limit

            if (_set.length) {

                _set.animate({ opacity: 0 });

                _set.promise().done(function() {

                    _set.removeClass('hidden');

                    _set.show().animate({ opacity: 1 }, 1000);

                });

            } else {

                _this.text('No more item to display');

            }

            return false;

        });

        /*-------------------------------------
         Input Quantity Up & Down activation code
         -------------------------------------*/

        $('#quantity-holder').on('click', '.quantity-plus', function() {

            var $holder = $(this).parents('.quantity-holder');

            var $target = $holder.find('input.quantity-input');

            var $quantity = parseInt($target.val(), 10);

            if ($.isNumeric($quantity) && $quantity > 0) {

                $quantity = $quantity + 1;

                $target.val($quantity);

            } else {

                $target.val($quantity);

            }


        }).on('click', '.quantity-minus', function() {

            var $holder = $(this).parents('.quantity-holder');

            var $target = $holder.find('input.quantity-input');

            var $quantity = parseInt($target.val(), 10);

            if ($.isNumeric($quantity) && $quantity >= 2) {

                $quantity = $quantity - 1;

                $target.val($quantity);

            } else {

                $target.val(1);

            }

        });
        /*-------------------------------------
         Jquery Serch Box
         -------------------------------------*/

        $('#product-search-btn').on('click', function(e) {

            e.preventDefault();

            $(this).prev('.search-panel').slideToggle('slow');

        });

    });

    $(window).on('load', function() {

        var wow = new WOW({
            offset: 100,
            mobile: false
        });

        wow.init();
    });
    var navbar = $('.nav-stacked');

    /*-------------------------------------
    Stellar activation code
    -------------------------------------*/
    $(window).stellar({
        responsive: true,
        horizontalScrolling: false,
        hideDistantElements: false,
        horizontalOffset: 0,
        verticalOffset: 0,
    });


    /*-------------------------------------
      navbar activation code
      -------------------------------------*/
    navbar.affix({
        offset: {
            top: 150
        }
    });
    navbar.on('affix.bs.affix', function() {
        if (!navbar.hasClass('affix')) {
            navbar.addClass('animated slideInDown');
        }
    });

    navbar.on('affixed-top.bs.affix', function() {
        navbar.removeClass('animated slideInDown');

    });

    $('.nav-mobile-list li a[href="#"]').on('click', function() {
        $(this).closest('li').toggleClass('current');
        $(this).closest('li').children('ul').slideToggle(200);
        return false;
    });

    /*-------------------------------------
         nav-mobile activation code
         -------------------------------------*/

    $('.nav-mobile-list li a[href="#"]').on('click', function() {
        $(this).closest('li').toggleClass('current');
        $(this).closest('li').children('ul').slideToggle(200);
        return false;
    });

    $('.navbar-toggle').on('click', function() {
        $('body').removeClass('menu-is-closed').addClass('menu-is-opened');
    });

    $('.close-menu, .click-capture').on('click', function() {
        $('body').removeClass('menu-is-opened').addClass('menu-is-closed');
        $('.menu-list ul').slideUp(300);
    });

    var dropToggle = $('.menu-list > li').has('ul').children('a');
    dropToggle.on('click', function() {
        dropToggle.not(this).closest('li').find('ul').slideUp(200);
        $(this).closest('li').children('ul').slideToggle(200);
        return false;
    });
    /*-------------------------------------
    pagepiling activation code    
    -------------------------------------*/
    if ($('.pagepiling').length > 0) {
        $('.pagepiling').pagepiling({
            scrollingSpeed: 280,
            loopBottom: true,
            menu: '#page-menu',
            anchors: ['page1', 'page2', 'page3', 'page4', 'page5', 'page6', 'page7', 'page8'],
            afterRender: function() {
                //playing the video
                $('video').get(0).play();
            },
            afterLoad: function(anchorLink, index) {
                if (index == 8 || index == 6 || index == 4) {
                    $('.navbar').removeClass('navbar-light');
                    $('#pp-nav').removeClass('txt-light');
                    $('.footer-copy-bottom').removeClass('txt-light');
                    $('.footer-social-bottom').removeClass('txt-light');
                    $('.page-menu').removeClass('txt-light');
                } else {
                    $('.navbar').addClass('navbar-light');
                    $('#pp-nav').addClass('txt-light');
                    $('.footer-copy-bottom').addClass('txt-light');
                    $('.footer-social-bottom').addClass('txt-light');
                    $('.page-menu').addClass('txt-light');
                }
                if (index == 8 || index == 4 || index == 6) {
                    $('.page-menu').removeClass('txt-light txt-primary');
                } else {

                    $('.page-menu').addClass('txt-light txt-primary');
                }

            }
        });
        $('.pp-scrollable').on('scroll', function() {
            var scrollTop = $(this).scrollTop();
            if (scrollTop > 0) {
                $('.navbar-2').removeClass('navbar-light');
            } else {
                $('.navbar-2').addClass('navbar-light');
            }
        });
        $('#pp-nav').remove().appendTo('.animsition').prepend('<div class="pp-nav-up icon-chevron-up"></div>').append('<div class="pp-nav-down icon-chevron-down"></div>').addClass('txt-light txt-dark boxed-full hidden-xs');
        $('.pp-nav-up').on('click', function() {
            $.fn.pagepiling.moveSectionUp();
        });
        $('.pp-nav-down').on('click', function() {
            $.fn.pagepiling.moveSectionDown();
        });
    }
    $('.project-box').on('mouseover', function() {
        var index = $('.project-box').index(this);
        $('.bg-changer .section-bg').removeClass('active').eq(index).addClass('active');
    });

    /*-------------------------------------
     jQuery MeanMenu activation code
     --------------------------------------*/
    $('nav#dropdown').meanmenu({ siteLogo: "<div class='mobile-menu-back'><a href='index.html' class='logo-mobile-menu'><img src='img/mobile-logo.png'/></a></div>" });

    /*-------------------------------------
     Jquery Scollup
     -------------------------------------*/
    $.scrollUp({

        scrollText: '<i class="fa fa-angle-double-up"></i>',

        easingType: 'linear',

        scrollSpeed: 900,

        animation: 'fade'

    });
    /*-------------------------------------
     Window load function
     -------------------------------------*/
    $(window).on('load', function() {

        // Page Preloader

        $('#preloader').fadeOut('slow', function() {

            $(this).remove();

        });
        /*-------------------------------------
         jQuery for Isotope initialization
         -------------------------------------*/
        var $container = $('#inner-isotope');

        if ($container.length > 0) {

            // Isotope initialization

            var $isotope = $container.find('.featuredContainer').isotope({

                filter: '*',

                animationOptions: {

                    duration: 750,

                    easing: 'linear',

                    queue: false

                }

            });
            // Isotope filter
            $container.find('.isotop-classes-tab').on('click', 'a', function() {
                var $this = $(this);
                $this.parent('.isotop-classes-tab').find('a').removeClass('current');
                $this.addClass('current');
                var selector = $this.attr('data-filter');
                $isotope.isotope({
                    filter: selector,
                    animationOptions: {
                        duration: 750,
                        easing: 'linear',
                        queue: false
                    }
                });
                return false;
            });
        }
        var testimonialCarousel = $('#rt-testimonial-slider-wrap');

        if (testimonialCarousel.length) {

            testimonialCarousel.find('.testimonial-sliders').slick({

                slidesToShow: 1,

                slidesToScroll: 1,

                arrows: true,

                autoplay: true,

                asNavFor: '.testimonial-carousel',

                prevArrow: '<span class="bst-prev bst-arrow"><i class="fa fa-long-arrow-left" aria-hidden="true"></i></span>',

                nextArrow: '<span class="bst-next bst-arrow"><i class="fa fa-long-arrow-right" aria-hidden="true"></i></span>'

            });

            testimonialCarousel.find('.testimonial-carousel').slick({

                slidesToShow: 3,

                slidesToScroll: 1,

                asNavFor: '.testimonial-sliders',

                dots: false,

                arrows: true,

                prevArrow: true,

                nextArrow: true,

                centerMode: true,

                centerPadding: '0px',

                focusOnSelect: true

            });

        }

    }); // end window load function


    /*-------------------------------------
     About Counter
     -------------------------------------*/

    var aboutContainer = $('.about-counter');

    if (aboutContainer.length) {

        aboutContainer.counterUp({

            delay: 50,

            time: 5000

        });

    }

    /*-------------------------------------
     Select2 activation code
     -------------------------------------*/
    if ($('#checkout-form  select.select2').length) {

        $('#checkout-form select.select2').select2({

            theme: 'classic',

            dropdownAutoWidth: true,

            width: '100%'

        });

    }



    /*-------------------------------------
    mobile on load
    -------------------------------------*/

    $(window).on('load resize', function() {
        equalHeight();
        mobile();

    });

    function clickEqualHeight() {
        $("#trigger button").on('click', function() {
            equalHeight();
        });

    }

    /*-------------------------------------
       Auto height for product listing
       -------------------------------------*/

    function equalHeight() {

        var imgH = 0,

            boxH = 0,

            wWidth = $(window).width(),

            allH;

        $('.equal_height-wrapper').height('auto');

        if (wWidth > 991) {

            $('.equal_height-wrapper').each(function() {

                var self = $(this);

                var TempImgH = self.find('.item-img').height();

                imgH = TempImgH > imgH ? TempImgH : imgH;

                var TempBoxH = self.find('.item-content').outerHeight();

                boxH = TempBoxH > boxH ? TempBoxH : boxH;

            });


            allH = imgH;

            allH = boxH > imgH ? boxH : imgH;

            $('.equal_height-wrapper').height(allH + "px");

        }

    }


    /*-------------------------------------
     Contact Form processing
     -------------------------------------*/
    var contactForm = $('#contact-form');

    if (contactForm.length) {

        contactForm.validator().on('submit', function(e) {

            var _this = $(this),

                target = contactForm.find('.form-response');

            if (e.isDefaultPrevented()) {

                target.html("<div class='alert alert-danger'><p>Please select all required field.</p></div>");

            } else {

                $.ajax({

                    url: "vendor/php/form-process.php",

                    type: "POST",

                    data: contactForm.serialize(),

                    beforeSend: function() {

                        target.html("<div class='alert alert-info'><p>Loading ...</p></div>");

                    },

                    success: function(text) {

                        if (text === "success") {

                            _this[0].reset();

                            target.html("<div class='alert alert-success'><p><i class='fa fa-check' aria-hidden='true'></i>Message has been sent successfully.</p></div>");

                        } else {

                            target.html("<div class='alert alert-danger'><p>" + text + "</p></div>");

                        }

                    }

                });

                return false;

            }

        });

    }


    /*-------------------------------------
     Countdown activation code
     -------------------------------------*/
    $('#countdown').countdown('2018/6/01', function(e) {

        $(this).html(e.strftime("<div class='countdown-section'><h3>%D</h3> <p>Day%!D</p> </div><div class='countdown-section'><h3>%H</h3> <p>Hour%!H</p> </div><div class='countdown-section'><h3>%M</h3> <p>Min%!M</p> </div><div class='countdown-section'><h3>%S</h3> <p>Sec%!S</p> </div>"));


    });


    /*-------------------------------------
     Carousel slider initiation
     -------------------------------------*/
    $('.thegym-carousel').each(function() {

        var carousel = $(this),

            loop = carousel.data('loop'),

            items = carousel.data('items'),

            margin = carousel.data('margin'),

            stagePadding = carousel.data('stage-padding'),

            autoplay = carousel.data('autoplay'),

            autoplayTimeout = carousel.data('autoplay-timeout'),

            smartSpeed = carousel.data('smart-speed'),

            dots = carousel.data('dots'),

            nav = carousel.data('nav'),

            navSpeed = carousel.data('nav-speed'),

            rXsmall = carousel.data('r-x-small'),

            rXsmallNav = carousel.data('r-x-small-nav'),

            rXsmallDots = carousel.data('r-x-small-dots'),

            rXmedium = carousel.data('r-x-medium'),

            rXmediumNav = carousel.data('r-x-medium-nav'),

            rXmediumDots = carousel.data('r-x-medium-dots'),

            rSmall = carousel.data('r-small'),

            rSmallNav = carousel.data('r-small-nav'),

            rSmallDots = carousel.data('r-small-dots'),

            rMedium = carousel.data('r-medium'),

            rMediumNav = carousel.data('r-medium-nav'),

            rMediumDots = carousel.data('r-medium-dots'),

            rLarge = carousel.data('r-large'),

            rLargeNav = carousel.data('r-large-nav'),

            rLargeDots = carousel.data('r-large-dots'),

            center = carousel.data('center');


        carousel.owlCarousel({

            loop: (loop ? true : false),

            items: (items ? items : 4),

            lazyLoad: true,

            margin: (margin ? margin : 0),

            autoplay: (autoplay ? true : false),

            autoplayTimeout: (autoplayTimeout ? autoplayTimeout : 1000),

            smartSpeed: (smartSpeed ? smartSpeed : 250),

            dots: (dots ? true : false),

            nav: (nav ? true : false),

            navText: ["<i class='fa fa-long-arrow-left' aria-hidden='true'></i>", "<i class='fa fa-long-arrow-right' aria-hidden='true'></i>"],

            navSpeed: (navSpeed ? true : false),

            center: (center ? true : false),

            responsiveClass: true,

            responsive: {

                0: {

                    items: (rXsmall ? rXsmall : 1),

                    nav: (rXsmallNav ? true : false),

                    dots: (rXsmallDots ? true : false)

                },

                480: {

                    items: (rXmedium ? rXmedium : 2),

                    nav: (rXmediumNav ? true : false),

                    dots: (rXmediumDots ? true : false)

                },

                768: {

                    items: (rSmall ? rSmall : 3),

                    nav: (rSmallNav ? true : false),

                    dots: (rSmallDots ? true : false)

                },

                992: {

                    items: (rMedium ? rMedium : 5),

                    nav: (rMediumNav ? true : false),

                    dots: (rMediumDots ? true : false)

                },

                1199: {

                    items: (rLarge ? rLarge : 6),

                    nav: (rLargeNav ? true : false),

                    dots: (rLargeDots ? true : false)

                }

            }

        });

    });



    /*-------------------------------------
     Window onLoad and onResize event trigger
     -------------------------------------*/
    $(window).on('load resize', function() {

        //Define the maximum height for mobile menu

        var wHeight = $(window).height(),

            mLogoH = $('a.logo-mobile-menu').outerHeight();

        wHeight = wHeight - 50;

        $('.mean-nav > ul').css('height', wHeight + 'px');


    });



    /*-------------------------------------
     Jquery Stiky Menu at window Load
     -------------------------------------*/
    $(window).on('scroll', function() {

        var s = $('#sticker'),

            w = $('.wrapper'),

            h = s.outerHeight(),

            windowpos = $(window).scrollTop(),

            windowWidth = $(window).width(),

            h3 = s.parent('.bt-header'),

            h3H = h3.find('.header-top-area').outerHeight(),

            topBar = s.prev('.header-top-area');


        if (windowWidth > 767) {

            w.css('padding-top', '');

            var topBarH, mBottom = 0;

            if (h3.length) {

                topBarH = topBar.outerHeight();

                if (windowpos <= topBarH) {

                    h3.css('top', '-' + windowpos + 'px');

                }

            }

            if (windowpos >= topBarH) {



                if (h3.length) {

                    s.addClass('stick');

                    h3.css('top', '-' + topBarH + 'px');

                }

            } else {

                s.removeClass('stick');


            }

        }

    });
    /*-------------------------------------
         Google Map
         -------------------------------------*/
    if ($('#googleMap').length) {

        //Map initialize
        var initialize = function() {
            var mapOptions = {
                zoom: 15,
                scrollwheel: false,
                center: new google.maps.LatLng(-37.81618, 144.95692),
                styles: [{
                    stylers: [{
                        saturation: -100
                    }]
                }]
            };
            var map = new google.maps.Map(document.getElementById("googleMap"),
                mapOptions);
            var marker = new google.maps.Marker({
                position: map.getCenter(),
                animation: google.maps.Animation.BOUNCE,
                icon: 'img/map-marker.png',
                map: map
            });
        }

        // Add the map initialize function to the window load function
        google.maps.event.addDomListener(window, "load", initialize);
    }
    $('#filter-btn').on("click", function() {

        if ($(window).width() > '990') {

            $('.filter-area').addClass('open');

            $('#filter-btn i').removeClass('fa-bars');

            $('#filter-btn i').addClass('fa-times');

        }

        return false;

    });
    $('.filter-area .close').on("click", function() {

        $('.filter-area').removeClass('open');

        return false;

    });

    $('#search-btn').on("click", function() {

        if ($(window).width() > '990') {

            $('.search-panel').addClass('open');

        }

        return false;

    });
    $('.pbox-search-area .close').on("click", function() {

        $('.search-panel').removeClass('open');

        return false;

    });

    /***************************************
     * jquery Price Table Carousel Mobile View activation code    
     * jquery services Carousel Mobile View activation code
     ***************************************/
    function pTmobile() {
        var checkWidth = $(window).width()
        var pricetable = $(".price-table-mobile");
        var services = $(".services-mobile");
        if (checkWidth > 992) {
            if (pricetable.hasClass('owl-carousel')) {
                pricetable.data('owlCarousel').destroy();
                pricetable.removeClass('owl-carousel');
            }
            if (services.hasClass('owl-carousel')) {
                services.data('owlCarousel').destroy();
                services.removeClass('owl-carousel');
            }
        } else {
            pricetable.owlCarousel({
                items: 1,
                itemsDesktop: false,
                itemsDesktopSmall: [980, 1],
                itemsTablet: false,
                itemsTabletSmall: false,
                itemsMobile: false,
                singleItem: false,
                itemsScaleUp: false,
                // Navigation
                navigation: false,
                navigationText: ["<i class='fa fa-angle-left'></i>", "<i class='fa fa-angle-right'></i>"],
                /* transitionStyle : "fade", */
                /* [This code for animation ] */
                // Responsive
                responsive: true,
                pagination: true,

            });
            services.owlCarousel({
                items: 2,
                itemsDesktop: false,
                itemsDesktopSmall: false,
                itemsTablet: false,
                itemsTabletSmall: false,
                itemsMobile: [479, 1],
                singleItem: false,
                itemsScaleUp: false,
                // Navigation
                navigation: false,
                navigationText: ["<i class='fa fa-angle-left'></i>", "<i class='fa fa-angle-right'></i>"],
                /* transitionStyle : "fade", */
                /* [This code for animation ] */
                // Responsive
                responsive: true,
                pagination: true,

            });

        }
    }

    /***************************************
     * jquery services-mobile-2 Mobile View activation code  
     ***************************************/

    function mobile() {
        var checkWidth = $(window).width()
        var services_2 = $(".services-mobile-2");
        if (checkWidth > 991) {
            if (services_2.hasClass('owl-carousel')) {
                services_2.data('owlCarousel').destroy();
                services_2.removeClass('owl-carousel');
            }
        } else {
            services_2.owlCarousel({
                loop: true,
                margin: 10,
                responsiveClass: true,
                autoplay: true,
                autoplayHoverPause: false,
                responsive: {
                    0: {
                        items: 1,
                        nav: false
                    },
                    767: {
                        items: 2,
                        nav: false
                    },
                    992: {
                        items: 3,
                        nav: true,
                        loop: false
                    }
                }
            });


        }
    }

})(jQuery);