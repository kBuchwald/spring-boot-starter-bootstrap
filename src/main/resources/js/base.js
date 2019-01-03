$(document).ready(function() {
	var socket = new SockJS('/gs-guide-websocket');
	stompClient = Stomp.over(socket);
	stompClient.connect({}, function (frame) {
		stompClient.subscribe('/topic/content/remove', function (remove) {
			var remove = JSON.parse(remove.body);
			$('#'+remove.componentId).remove();
		});
		stompClient.subscribe('/topic/content/set', function (set) {
			var set = JSON.parse(set.body);
			$('#'+set.parentId).load('base/contentByParent/[(${page.id})]/'+set.parentId);
			$.getScript('base/content/js/[(${page.id})]/'+set.parentId);
		});
		stompClient.subscribe('/topic/content/add', function (add) {
			var add = JSON.parse(add.body);
			$.get('base/content/[(${page.id})]/'+add.contentId, function(html){
				$('#'+add.parentId).append(html);
			});
			$.getScript('base/content/js/[(${page.id})]/'+add.contentId);
		});
		stompClient.subscribe('/topic/ccontent/adWithIndex', function (adWithIndex) {
			var adWithIndex = JSON.parse(adWithIndex.body);
			$.get('base/content/[(${page.id})]/'+adWithIndex.contentId, function(html){
				if(adWithIndex.asFirst)
					$('#'+addWithIndex.parentId).prepend(html);
				else
					$('#'+addWithIndex.afterId).after(html);
			});
			$.getScript('base/content/js/[(${page.id})]/'+adWithIndex.contentId);
		});
		
		stompClient.subscribe('/topic/button/disable', function (disable) {
			var disable = JSON.parse(disable.body);
			if(disable.disabled)
				$('#'+disable.componentId).attr("disabled", "disabled");
			else
				$('#'+disable.componentId).removeAttr("disabled");
		});
		stompClient.subscribe('/topic/base/css', function (css) {
			var css = JSON.parse(css.body);
			$('#'+css.componentId).attr("class", css.cssClass);
		});
		stompClient.subscribe('/topic/content/text', function (text) {
			var text = JSON.parse(text.body);
			$('#'+text.componentId).html(text.text);
		});
	});
});