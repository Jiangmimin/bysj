(function($){
  Drupal.behaviors.CToolsAutoSubmit = {
    attach: function(context) {
      // 'this' references the form element
      function triggerSubmit (e) {
        var $this = $(this);
        if (!$this.hasClass('ctools-ajaxing')) {
          $this.find('.ctools-auto-submit-click').click();
        }
      }


      // the change event bubbles so we only need to bind it to the outer form
      $('form.ctools-auto-submit-full-form', context)
        .add('.ctools-auto-submit', context)
        .filter('form, select, input:not(:text, :submit)')
        .once('ctools-auto-submit')
        .change(function (e) {
          // don't trigger on text change for full-form
          if ($(e.target).is(':not(:text, :submit, .ctools-auto-submit-exclude)')) {
            triggerSubmit.call(e.target.form);
          }
        });


      // e.keyCode: key
      var discardKeyCode = [
        16, // shift
        17, // ctrl
        18, // alt
        20, // caps lock
        33, // page up
        34, // page down
        35, // end
        36, // home
        37, // left arrow
        38, // up arrow
        39, // right arrow
        40, // down arrow
        9, // tab
        13, // enter
        27  // esc
      ];
      // Don't wait for change event on textfields
      $('.ctools-auto-submit-full-form input:text, input:text.ctools-auto-submit', context)
        .filter(':not(form-item-search-api-views-fulltext input)')
        .once('ctools-auto-submit', function () {
          // each textinput element has his own timeout
          var timeoutID = 0;
          $(this)
            .bind('keydown keyup', function (e) {
              if ($.inArray(e.keyCode, discardKeyCode) === -1) {
                timeoutID && clearTimeout(timeoutID);
              }
            })
            .bind('change', function (e) {
              if ($.inArray(e.keyCode, discardKeyCode) === -1) {
                timeoutID = setTimeout($.proxy(triggerSubmit, this.form), 500);
              }
            });
        });
    }
  }
})(jQuery);

jQuery(document).ready(function($) {
  $('.dexp-container').find('.block-big-title').wrap('<div class="container">');
  $('.search-icon').click(function(){
    $(this).parent().find('.search-wrapper').show(0,function(){
        $(this).parent().find('.search-wrapper').find('input[type=text]').focus();
    });
  });
  $('.search-close').click(function(){
    $(this).closest('.search-wrapper').hide(0);
  });
  // Auto clear default value field
  $('.form-text,.form-textarea').cleardefault();
  // Tooltips 
  $('.bs-example-tooltips').tooltip({
    selector: "[data-toggle=tooltip]",
    container: "body"
  });
  $('.dtooltip').tooltip({
    container: 'body'
  });
  $("#bs-example a").popover();
 
  $(".stat-count").each(function() {
    //alert('ok');
    $(this).data('count', parseInt($(this).html(), 10));
    $(this).html('0');
    count($(this));
  });
  //Go to top


  //Masonry hover on ipad
  $('.dexp-masonry-item').hover(function(){
     $(this).find('.portfolio-item-inner').trigger('hover');
  });
  
  $("#user-login-form #edit-name").attr("placeholder", "USERNAME");
  $("#user-login-form #edit-pass").attr("placeholder", "PASSWORD");
  $("#user-login-form #edit-submit").val("SIGN IN");
  $("#user-login-form .item-list li.last a").text("forgot password?");
  $("#block-formblock-user-register .username").attr("placeholder", "CHOOSE YOUR USERNAME");
  $("#block-formblock-user-register .field-name-field-display-name .text-full").attr("placeholder", "YOUR REAL NAME");
  $("#block-formblock-user-register .form-item-mail .form-text").attr("placeholder", "YOUR EMAIL");
  $("#block-formblock-user-register .form-submit").val("JOIN NOW");
  $(".textarea_show_small").click(function() {
	 $(".textarea_small").addClass("show");
  });
  $(".textarea_show_big").click(function() {
	 $(".textarea_big").addClass("show");
  });
  $(".embed_close").click(function() {
	  $(".textarea_small").removeClass("show");
	  $(".textarea_big").removeClass("show");
  });
});

function count($this) {
  var current = parseInt($this.html(), 10);
  current = current + 1; /* Where 50 is increment */


  $this.html(++current);
  if (current > $this.data('count')) {
    $this.html($this.data('count'));
  } else {
    setTimeout(function() {
      count($this)
    }, 50);
  }
}
jQuery.fn.cleardefault = function() {
  return this.focus(function() {
    if (this.value == this.defaultValue) {
      //this.value = "";
    }
  }).blur(function() {
    if (!this.value.length) {
      this.value = this.defaultValue;
    }
  });
};;
