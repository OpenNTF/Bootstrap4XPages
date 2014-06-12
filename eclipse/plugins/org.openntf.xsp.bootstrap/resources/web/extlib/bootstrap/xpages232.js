/*
  XPages Boostrap specific JavaScript files
  This defines the missing JS code in Bs 2.3
*/

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

bs4xp.initCollapsibleMenu = function(label, collapseTo) {
	
	var left = $(".applayout-column-left");		//left column
	var ul = left.find("ul").first();
	
	if (ul.length>0) {
		
		var div = $('<div/>').addClass('visible-phone dropdown');
		var btn = $('<button class="btn btn-default btn-left-col-menu dropdown-toggle" data-toggle="dropdown">' + label + ' <span class="caret"></span></button>');
		var clone = ul.clone().addClass('dropdown-menu');		//clone of the menu
	
		div.append( btn );
		div.append( clone );
		
		//append menu button to target element
		if (collapseTo.indexOf(".")==-1 && collapseTo.indexOf("#")==-1) {
			collapseTo = "." + collapseTo;
		}
		var $tgt = $(collapseTo);
		if ($tgt.length==0) {
			$tgt = left;
		}
		
		$tgt.after(div);
	}
	
}
