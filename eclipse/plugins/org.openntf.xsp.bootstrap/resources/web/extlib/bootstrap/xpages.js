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

XSP.initSelect2Picker = function(params) {
	
	if (params.useRemoteData) {		//picker with remote search capability
		
		var select2Options = {
			placeholder: params.placeHolder,	
			minimumInputLength : 2,		
			allowClear: params.allowClearing,			
			formatInputTooShort : function(term, minLength) {
				var rest = minLength - term.length;
				return "Please enter " + rest + " more character" + (rest>1 ? "s" : "");
			},
			formatSearching : function () {
					return "Searching...";
			},
		    quietMillis: 1000,
		    ajax: {
              url: params.restUrl,
               dataType: 'json',
               data: function (term, page) {
                   return {
                       startkeys: "*" + term + "*", // search term,
                       count: 30
                   };
               },
               results: function (data, page) { // parse the results into the format expected by Select2.
            	  
            	   var _r = [];
            	   var _hasLabels = null;
            	   
        	   $(data.items).each( function() {
            		   
            		 //check if we have labels or only values
	   					if (_hasLabels == null) {
	   						_hasLabels = this.hasOwnProperty('@label');
	   					}
            	   
            		   	var val = this['@value'];
            		   	var txt = this[ ( _hasLabels ? '@label' : '@value' ) ];

            		   	_r.push(
            		   		{ "id" : val, "text" : txt}	
            		   	);
   					
            	   });
            	   
   					//array of elements..results: [choice1, choice2, ...], more: true/false }
            		return {
            			more : false,
            			results : _r
            		}
                   
               }//results
		    }//ajax
		    /*initSelection: function(element, callback) {		//TODO
	            //sets a default value (if a value was selected previously
	            var id=$(element).val();
	            if (id!=="") {
	                $.ajax( 
	                	"#{javascript:configBean.getDbUrl()}/zoekGebruiker.xsp" ,
	                		{
	                    		data: {
	                        		id: id
	                    		},
	                    dataType: "json"
	                }).done(function(data) { callback(data); } );
				}
			}	*/
		};
		
		if (params.hasOwnProperty('listWidth') ) {
			select2Options.width = params.listWidth;
		}
		
		x$(params.forId)
			.select2(select2Options);
			
	} else {
		
		var select2Options = {
			placeholder: params.placeHolder,
			allowClear: params.allowClearing
		};
		
		if (params.hasOwnProperty('listWidth') ) {
			select2Options.width = params.listWidth;
		}
		
		if (params.formatSelection != null) {
			//use a template to render the selected entry
		
			select2Options.formatSelection = function(entry) {
			    if (!entry.id) { return entry.text; } // optgroup
			    return params.formatSelection
			    	.replace(/{value}/g, entry.id)
			    	.replace(/{text}/g, entry.text);
			};
			select2Options.escapeMarkup = function(m) { return m; };
			
		}
		
		if (params.formatResult != null) {
			//use a template to render the entries
		
			select2Options.formatResult = function(entry) {
			    if (!entry.id) { return entry.text; } // optgroup
			    return (params.formatResult)
			    	.replace(/{value}/g, entry.id)
			    	.replace(/{text}/g, entry.text);
			};
			select2Options.escapeMarkup = function(m) { return m; };
			
		}
		
		if (params.isNativeSelect) {
			//code to execute if select2 should be attached to a <select> element 
			
			var $select = x$(params.forId);
			
			if (params.placeHolder != null || params.allowClearing ) { 
				
				//add a blank option add the beginning of the option list: required for the placeH
				$select.prepend("<option>");
				
			}
	
			$select
				.select2( select2Options );
			
		} else {
			//code to execute if select2 is attached to a (hidden) import
	
			$.get(
				params.restUrl,
				function(data, status, request) {
					
					var $select = x$(params.id);
					var _hasLabels = null;
					
					//add a blank option if a placeholder is set
					if (params.placeHolder) {
						$select.append( "<option>" );
					}
					
					$(data.items).each( function() {
						
						var val = this['@value'];
						
						//check if we have labels or only values
						if (_hasLabels == null) {
							_hasLabels = this.hasOwnProperty('@label');
						}
						
						var txt = this[ ( _hasLabels ? '@label' : '@value' ) ];
						
						$select.append(
							$("<option>")
								.attr('value',val)
								.text(txt)
							
						)
					});
					
					if (params.currentValue.length > 0 ) {
						$select.val(params.currentValue);		//select current value
						
					}
						
					x$(params.id)
						.select2(select2Options)
						.on("change", function(e) {		//set value in target field on change
							x$(params.forId).val(e.val);
						});
				},
				"json"
			);
		} // params.nativeSelect
	}
	
};
