$(document).ready(function() {
	[# th:each="component, status : ${components}"]
		[# th:replace="__${component.name + '.js'}__ (component=${component}, page=${page})"]
		[/]
	[/]	
});