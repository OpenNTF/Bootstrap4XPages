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
