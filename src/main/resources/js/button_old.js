$(document).ready(function() {
	[# th:each="button : ${page.componentsByType['button']}"]
		$([(${'\"#'+button.id+'\"'})]).click(function() {
			$.post({url:'[(${'button/'+button.id+'/'+page.id+'/click'})]'
				,contentType:'application/json'});
		});
	[/]
});