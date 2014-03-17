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

/*
 * x$ snippet by Mark Roden
 * http://openntf.org/XSnippets.nsf/snippet.xsp?id=x-jquery-selector-for-xpages
 */
function x$(idTag, param){ //Updated 18 Feb 2012
	idTag=idTag.replace(/:/gi, "\\:")+(param ? param : "");
	return($("#"+idTag));
}

var bs4xp = bs4xp || {};

//function to create a responsive collapsible menu from the left column contents

bs4xp.initCollapsibleMenu = function() {
	
	var left = $(".applayout-column-left");		//left column
	var ul = left.find("ul").first();
	
	if (ul.length>0) {
		
		var div = $('<div/>').addClass('visible-xs visible-sm dropdown');
		var btn = $('<button class="btn dropdown-toggle" data-toggle="dropdown">Menu<span class="caret"></span></button>');
		var clone = ul.clone().addClass('dropdown-menu');		//clone of the menu
	
		div.append( btn );
		div.append( clone );
		
		left.after(div);
	}
	
}
