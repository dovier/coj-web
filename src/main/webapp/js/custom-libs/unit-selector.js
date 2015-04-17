/* ========================================================================
 * Bootstrap: unit-selector.js v1.0.0 
 * ========================================================================
 * Based on Bootstrap popover implementation
 * @Lan: Used in http://coj.uci.cu
 * ======================================================================== */

+function ($) { "use strict";

  // POPOVER PUBLIC CLASS DEFINITION
  // ===============================

  var UnitSelector = function (element, options) {
    this.init('unitSelector', element, options)
  }

  if (!$.fn.tooltip) throw new Error('UnitSelector requires tooltip.js')

  UnitSelector.DEFAULTS = $.extend({} , $.fn.tooltip.Constructor.DEFAULTS, {
    placement: 'right'
  , trigger: 'click'
  , html: true
  , content: '<div class="unit-selector clearfix" style="width: 170px"><div class="input-group"><input type="text" value="<value>" class="form-control" placeholder="Size"><span class="input-group-btn"><button type="button" class="btn btn-default" aria-label="Left Align"><span class="fa fa-check" aria-hidden="true"></span></button></span></div><select class="form-control"><option value="1">B</option><option value="1024">KB</option><option selected value="1048576">MB</option><option value="1073741824">GB</option></select></div>'
  , template: '<div class="popover"><div class="arrow"></div><h3 class="popover-title"></h3><div class="popover-content"></div></div>'
  })


  // NOTE: POPOVER EXTENDS tooltip.js
  // ================================

  UnitSelector.prototype = $.extend({}, $.fn.tooltip.Constructor.prototype)

  UnitSelector.prototype.constructor = UnitSelector

  UnitSelector.prototype.getDefaults = function () {
    return UnitSelector.DEFAULTS
  }

  UnitSelector.prototype.setContent = function () {
    var $tip    = this.tip()
    var title   = this.getTitle()
    var content = this.getContent()

    $tip.find('.popover-title')[this.options.html ? 'html' : 'text'](title)
    $tip.find('.popover-content')[this.options.html ? 'html' : 'text'](content)

    $tip.removeClass('fade top bottom left right in')

    // IE8 doesn't accept hiding via the `:empty` pseudo selector, we have to do
    // this manually by checking the contents.
    if (!$tip.find('.popover-title').html()) $tip.find('.popover-title').hide()
  }

  UnitSelector.prototype.hasContent = function () {
    return this.getTitle() || this.getContent()
  }

  UnitSelector.prototype.getContent = function () {
    var $e = this.$element
    var o  = this.options    

    return $e.attr('data-content')
      || (typeof o.content == 'function' ?
            o.content.call($e[0]) :
            o.content.replace('<value>',parseInt(this.$element.val()/1048576)))
  }

  UnitSelector.prototype.arrow = function () {
    return this.$arrow = this.$arrow || this.tip().find('.arrow')
  }

  UnitSelector.prototype.tip = function () {
    if (!this.$tip) this.$tip = $(this.options.template)
    return this.$tip
  }


  // POPOVER PLUGIN DEFINITION
  // =========================

  var old = $.fn.unitSelector

  $.fn.unitSelector = function (option) {
        return this.each(function() {
            var $this = $(this)
            var self = this
            var data = $this.data('bs.unitSelector')
            var options = typeof option == 'object' && option
            options['container'] = $(this).parent();

            if (!data)
                $this.data('bs.unitSelector', (data = new UnitSelector(this, options)))
            if (typeof option == 'string')
                data[option]()
            else {
                $(this).parent().on("keypress", ".unit-selector input", function(e) {
                    if (e.which === 13) {
                        e.preventDefault();
                        e.stopPropagation();
                        $(this).parents(".unit-selector").find("button").trigger("click");
                    }
                });

                $(this).parent().on("click", ".unit-selector button", function() {
                    var input = $(this).parents(".unit-selector").find("input");
                    var select = $(this).parents(".unit-selector").find("select");
                    self.value = input.val() * select.val();
                    $(self).unitSelector('hide');
                });
            }
        })
  }

  $.fn.unitSelector.Constructor = UnitSelector


  // POPOVER NO CONFLICT
  // ===================

  $.fn.unitSelector.noConflict = function () {
    $.fn.unitSelector = old
    return this
  }

}(window.jQuery);