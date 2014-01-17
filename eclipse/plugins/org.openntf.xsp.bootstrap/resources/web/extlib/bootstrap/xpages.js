/*
  XPages Boostrap specific JavaScript files for all versions
*/

/*x$ snippet by Mark Roden
 * http://openntf.org/XSnippets.nsf/snippet.xsp?id=x-jquery-selector-for-xpages
 */
function x$(idTag, param){ //Updated 18 Feb 2012
	   idTag=idTag.replace(/:/gi, "\\:")+(param ? param : "");
	   return($("#"+idTag));
	}


XSP.initSelect2Picker = function(params) {
	
	var select2Options = {
			placeholder: params.placeHolder,
			allowClear: params.allowClearing
		};
	
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
		    width: '250px',		//TODO: configurable
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
		    },//ajax
		    initSelection: function(element, callback) {		//TODO
	            //sets a default value (if a value was selected previously
	            var id=$(element).val();
	            if (id!=="") {
	            	//console.log("existing: " + id);
	                $.ajax( 
	                	"#{javascript:configBean.getDbUrl()}/zoekGebruiker.xsp" ,
	                		{
	                    		data: {
	                        		id: id
	                    		},
	                    dataType: "json"
	                }).done(function(data) { callback(data); } );
				}
			}	
		};
		
		x$(params.forId)
			.select2(select2Options);
			
	} else {
		
		var select2Options = {
			placeholder: params.placeHolder,
			allowClear: params.allowClearing,
			width: "resolve"		//TODO: configurable
		};
		
		//use the template to render the entries
		if (params.template != null) {
			
			function format(entry) {
			    if (!entry.id) return entry.text; // optgroup
			    return params.template
			    	.replace("{value}", entry.id)
			    	.replace("{text}", entry.text);
			}
			
			select2Options.formatResult = format;
			select2Options.formatSelection = format;
			select2Options.escapeMarkup = function(m) { return m; };
			
		}
	
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
	}
	
};