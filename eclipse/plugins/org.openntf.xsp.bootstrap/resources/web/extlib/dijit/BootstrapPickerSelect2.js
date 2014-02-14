dojo.provide("extlib.dijit.BootstrapPickerSelect2");

dojo.require("dijit._WidgetBase");
dojo.require("dojo.io.script");
dojo.require("dojo.NodeList-manipulate");

dojo.declare(
	'extlib.dijit.BootstrapPickerSelect2',
	dijit._WidgetBase,
	{
		
		constructor: function(args) {
			console.log('construct it');
	        dojo.safeMixin(this,args);
			
			var head = dojo.query("head");
			
			head.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"/xsp/.ibmxspres/.extlib/bootstrap/select2/select2.css\">");
			head.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"/xsp/.ibmxspres/.extlib/bootstrap/select2/select2-bootstrap.css\">");
			
			var deferred = dojo.io.script.get({url : "/xsp/.ibmxspres/.extlib/bootstrap/select2/select2.js"});
	       
			deferred.then( dojo.hitch( this, function() {
				//code executed after the script lib has loaded
				
				x$(this.forId)
					.select2();
				
			} ));
			
	    }
	}
);
