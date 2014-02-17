"use strict";

/*
  XPages Bootstrap specific JavaScript files for all versions
*/

/*
 * x$ snippet by Mark Roden
 * http://openntf.org/XSnippets.nsf/snippet.xsp?id=x-jquery-selector-for-xpages
 */
function x$(idTag, param){ //Updated 18 Feb 2012
	   idTag=idTag.replace(/:/gi, "\\:")+(param ? param : "");
	   return($("#"+idTag));
	}
