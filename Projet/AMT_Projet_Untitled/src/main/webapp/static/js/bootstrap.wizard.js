/*!
 * jQuery twitter bootstrap wizard plugin
 * Examples and documentation at: http://github.com/VinceG/twitter-bootstrap-wizard
 * version 1.0
 * Requires jQuery v1.3.2 or later
 * Supports Bootstrap 2.2.x, 2.3.x, 3.0
 * Dual licensed under the MIT and GPL licenses:
 * http://www.opensource.org/licenses/mit-license.php
 * http://www.gnu.org/licenses/gpl.html
 * Authors: Vadim Vincent Gabriel (http://vadimg.com), Jason Gill (www.gilluminate.com)
 */
(function($){var bootstrapWizardCreate=function(element,options){var element=$(element);var obj=this;var baseItemSelector='li:has([data-toggle="tab"])';var historyStack=[];var $settings=$.extend({},$.fn.bootstrapWizard.defaults,options);var $activeTab=null;var $navigation=null;this.rebindClick=function(selector,fn){selector.unbind("click",fn).bind("click",fn)};this.fixNavigationButtons=function(){if(!$activeTab.length){$navigation.find("a:first").tab("show");$activeTab=$navigation.find(baseItemSelector+":first")}$($settings.previousSelector,element).toggleClass("disabled",obj.firstIndex()>=obj.currentIndex());$($settings.nextSelector,element).toggleClass("disabled",obj.currentIndex()>=obj.navigationLength());$($settings.nextSelector,element).toggleClass("hidden",obj.currentIndex()>=obj.navigationLength()&&$($settings.finishSelector,element).length>0);$($settings.lastSelector,element).toggleClass("hidden",obj.currentIndex()>=obj.navigationLength()&&$($settings.finishSelector,element).length>0);$($settings.finishSelector,element).toggleClass("hidden",obj.currentIndex()<obj.navigationLength());$($settings.backSelector,element).toggleClass("disabled",historyStack.length==0);$($settings.backSelector,element).toggleClass("hidden",obj.currentIndex()>=obj.navigationLength()&&$($settings.finishSelector,element).length>0);obj.rebindClick($($settings.nextSelector,element),obj.next);obj.rebindClick($($settings.previousSelector,element),obj.previous);obj.rebindClick($($settings.lastSelector,element),obj.last);obj.rebindClick($($settings.firstSelector,element),obj.first);obj.rebindClick($($settings.finishSelector,element),obj.finish);obj.rebindClick($($settings.backSelector,element),obj.back);if($settings.onTabShow&&typeof $settings.onTabShow==="function"&&$settings.onTabShow($activeTab,$navigation,obj.currentIndex())===false){return false}};this.next=function(e){if(element.hasClass("last")){return false}if($settings.onNext&&typeof $settings.onNext==="function"&&$settings.onNext($activeTab,$navigation,obj.nextIndex())===false){return false}var formerIndex=obj.currentIndex();$index=obj.nextIndex();if($index>obj.navigationLength()){}else{historyStack.push(formerIndex);$navigation.find(baseItemSelector+":eq("+$index+") a").tab("show")}};this.previous=function(e){if(element.hasClass("first")){return false}if($settings.onPrevious&&typeof $settings.onPrevious==="function"&&$settings.onPrevious($activeTab,$navigation,obj.previousIndex())===false){return false}var formerIndex=obj.currentIndex();$index=obj.previousIndex();if($index<0){}else{historyStack.push(formerIndex);$navigation.find(baseItemSelector+":eq("+$index+") a").tab("show")}};this.first=function(e){if($settings.onFirst&&typeof $settings.onFirst==="function"&&$settings.onFirst($activeTab,$navigation,obj.firstIndex())===false){return false}if(element.hasClass("disabled")){return false}historyStack.push(obj.currentIndex());$navigation.find(baseItemSelector+":eq(0) a").tab("show")};this.last=function(e){if($settings.onLast&&typeof $settings.onLast==="function"&&$settings.onLast($activeTab,$navigation,obj.lastIndex())===false){return false}if(element.hasClass("disabled")){return false}historyStack.push(obj.currentIndex());$navigation.find(baseItemSelector+":eq("+obj.navigationLength()+") a").tab("show")};this.finish=function(e){if($settings.onFinish&&typeof $settings.onFinish==="function"){$settings.onFinish($activeTab,$navigation,obj.lastIndex())}};this.back=function(){if(historyStack.length==0){return null}var formerIndex=historyStack.pop();if($settings.onBack&&typeof $settings.onBack==="function"&&$settings.onBack($activeTab,$navigation,formerIndex)===false){historyStack.push(formerIndex);return false}element.find(baseItemSelector+":eq("+formerIndex+") a").tab("show")};this.currentIndex=function(){return $navigation.find(baseItemSelector).index($activeTab)};this.firstIndex=function(){return 0};this.lastIndex=function(){return obj.navigationLength()};this.getIndex=function(e){return $navigation.find(baseItemSelector).index(e)};this.nextIndex=function(){return $navigation.find(baseItemSelector).index($activeTab)+1};this.previousIndex=function(){return $navigation.find(baseItemSelector).index($activeTab)-1};this.navigationLength=function(){return $navigation.find(baseItemSelector).length-1};this.activeTab=function(){return $activeTab};this.nextTab=function(){return $navigation.find(baseItemSelector+":eq("+(obj.currentIndex()+1)+")").length?$navigation.find(baseItemSelector+":eq("+(obj.currentIndex()+1)+")"):null};this.previousTab=function(){if(obj.currentIndex()<=0){return null}return $navigation.find(baseItemSelector+":eq("+parseInt(obj.currentIndex()-1)+")")};this.show=function(index){var tabToShow=isNaN(index)?element.find(baseItemSelector+" a[href=#"+index+"]"):element.find(baseItemSelector+":eq("+index+") a");if(tabToShow.length>0){historyStack.push(obj.currentIndex());tabToShow.tab("show")}};this.disable=function(index){$navigation.find(baseItemSelector+":eq("+index+")").addClass("disabled")};this.enable=function(index){$navigation.find(baseItemSelector+":eq("+index+")").removeClass("disabled")};this.hide=function(index){$navigation.find(baseItemSelector+":eq("+index+")").hide()};this.display=function(index){$navigation.find(baseItemSelector+":eq("+index+")").show()};this.remove=function(args){var $index=args[0];var $removeTabPane=typeof args[1]!="undefined"?args[1]:false;var $item=$navigation.find(baseItemSelector+":eq("+$index+")");if($removeTabPane){var $href=$item.find("a").attr("href");$($href).remove()}$item.remove()};var innerTabClick=function(e){var $ul=$navigation.find(baseItemSelector);var clickedIndex=$ul.index($(e.currentTarget).parent(baseItemSelector));var $clickedTab=$($ul[clickedIndex]);if($settings.onTabClick&&typeof $settings.onTabClick==="function"&&$settings.onTabClick($activeTab,$navigation,obj.currentIndex(),clickedIndex,$clickedTab)===false){return false}};var innerTabShown=function(e){$element=$(e.target).parent();var nextTab=$navigation.find(baseItemSelector).index($element);if($element.hasClass("disabled")){return false}if($settings.onTabChange&&typeof $settings.onTabChange==="function"&&$settings.onTabChange($activeTab,$navigation,obj.currentIndex(),nextTab)===false){return false}$activeTab=$element;obj.fixNavigationButtons()};this.resetWizard=function(){$('a[data-toggle="tab"]',$navigation).off("click",innerTabClick);$('a[data-toggle="tab"]',$navigation).off("shown shown.bs.tab",innerTabShown);$navigation=element.find("ul:first",element);$activeTab=$navigation.find(baseItemSelector+".active",element);$('a[data-toggle="tab"]',$navigation).on("click",innerTabClick);$('a[data-toggle="tab"]',$navigation).on("shown shown.bs.tab",innerTabShown);obj.fixNavigationButtons()};$navigation=element.find("ul:first",element);$activeTab=$navigation.find(baseItemSelector+".active",element);if(!$navigation.hasClass($settings.tabClass)){$navigation.addClass($settings.tabClass)}if($settings.onInit&&typeof $settings.onInit==="function"){$settings.onInit($activeTab,$navigation,0)}if($settings.onShow&&typeof $settings.onShow==="function"){$settings.onShow($activeTab,$navigation,obj.nextIndex())}$('a[data-toggle="tab"]',$navigation).on("click",innerTabClick);$('a[data-toggle="tab"]',$navigation).on("shown shown.bs.tab",innerTabShown)};$.fn.bootstrapWizard=function(options){if(typeof options=="string"){var args=Array.prototype.slice.call(arguments,1);if(args.length===1){args.toString()}return this.data("bootstrapWizard")[options](args)}return this.each(function(index){var element=$(this);if(element.data("bootstrapWizard"))return;var wizard=new bootstrapWizardCreate(element,options);element.data("bootstrapWizard",wizard);wizard.fixNavigationButtons()})};$.fn.bootstrapWizard.defaults={tabClass:"nav nav-pills",nextSelector:".wizard li.next",previousSelector:".wizard li.previous",firstSelector:".wizard li.first",lastSelector:".wizard li.last",finishSelector:".wizard li.finish",backSelector:".wizard li.back",onShow:null,onInit:null,onNext:null,onPrevious:null,onLast:null,onFirst:null,onFinish:null,onBack:null,onTabChange:null,onTabClick:null,onTabShow:null}})(jQuery);