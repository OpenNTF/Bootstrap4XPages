dojo.provide("extlib.dijit.BootstrapPickerSelect2");

XSP.initSelect2PickerNow = function(options) {
	
	console.log('init it...');
	
	var extract = function(markStart,markEnd) {
		var startIndex = cont.indexOf(markStart);
		if( startIndex >= 0 ){
			var endIndex = cont.lastIndexOf(markEnd);
			if( endIndex >= 0 ) {
				var script = cont.substring(startIndex+markStart.length, endIndex);
				cont = cont.substring(0, startIndex) + cont.substring(endIndex+markEnd.length);
				return script;
			}
		}
	};
	// Execute the script inthe header first
	var header = extract("<!-- XSP_UPDATE_HEADER_START -->\n","<!-- XSP_UPDATE_HEADER_END -->\n");
	if(header) {
		XSP.execScripts(XSP.processScripts(header,true));
	}
	
		
}

