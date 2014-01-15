/*
  XPages Boostrap specific JavaScript files
  This defines the missing JS code in Bs 3.0
*/

/* Fix the missing sub menu in BS 3.0*/
$(document).ready(function() {
	$('ul.dropdown-menu [data-toggle=dropdown]').on('click', function(event) {
    	event.preventDefault(); event.stopPropagation(); 
    	// 	opening the one you clicked on
    	$(this).parent().addClass('open');
    	var menu = $(this).parent().find("ul");
    	var menupos = menu.offset();
    	if ((menupos.left + menu.width()) + 30 > $(window).width()) {
        	var newpos = - menu.width();      
    	} else {
        	var newpos = $(this).parent().width();
    	}
    	menu.css({ left:newpos, width: menu.width() });
	});
});

/*x$ snippet by Mark Roden
 * http://openntf.org/XSnippets.nsf/snippet.xsp?id=x-jquery-selector-for-xpages
 */
function x$(idTag, param){ //Updated 18 Feb 2012
	   idTag=idTag.replace(/:/gi, "\\:")+(param ? param : "");
	   return($("#"+idTag));
	}


function initSelect2(params) {
	
	$.get(
		params.restUrl,
		function(data, status, request) {
			
			var $select = x$(params.id);
			
			$(data.items).each( function() {
				var val = this['@value'];
				
				$select.append(
					$("<option>")
						.attr('value',val)
						.text(val)
				)
			});
			
			x$(params.id)
				.select2({
					placeholder: "Select a value",
					allowClear: true
				})
				.on("change", function(e) {		//set value in target field on change
					x$(params.forId).val(e.val);
				});
		},
		"json"
	);
	
	/*var xhrArgs = {
		url: params.restUrl,
		handleAs: "json",
		load: function(data){			
			var $select = x$(params.id);
			
			$(data.items).each( function() {
				var val = this['@value'];
				
				$select.append(
					$("<option>")
						.attr('value',val)
						.text(val)
				)
			});
			
			x$(params.id)
				.select2({
				
					placeholder: "Select a value",
					allowClear: true
				})
				.on("change", function(e) {		//set value in target field on change
					console.log('changed: ' + e.val);
					x$(params.forId).val(e.val);
				});
		
		},
		error: function(error){
		  targetNode.innerHTML = "An unexpected error occurred: " + error;
		    }
	}
		
	var deferred = dojo.xhrGet(xhrArgs);*/
	
}