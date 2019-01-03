$([(${'\"#'+component.id+'\"'})]).click(function() {
	$.post({url:'[(${'button/'+component.id+'/'+page.id+'/click'})]'
		,contentType:'application/json'});
});