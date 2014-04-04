/*
 * <<
 * XPage Debug Toolbar
 * Copyright 2012,2013 Mark Leusink http://linqed.eu
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this 
 * file except in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under
 * the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF 
 * ANY KIND, either express or implied. See the License for the specific language governing
 * permissions and limitations under the License
 * >> 
 */

var dBarHelper = {
		
	MAX_DATASET_SAMPLE : 50,		//maximum number of samples from a list that are shown by default
	
	//returns the dumped contents of an entry the current selected scope
	getScopeEntry : function( entryName:String ) {
	
		if (entryName==null || entryName.length==0) {
			return "invalid input";
		}
		
		//don't render variables related to the debug toolbar
		if (dBar.hideDetails(entryName)) {
			return 	"(skipped - debugToolbar variable)";
		}
		
		var result;
		
		switch(dBar.getActiveTab()) {

			case "applicationScope":
				result = applicationScope.get(entryName);break;
			case "sessionScope":
				result = sessionScope.get(entryName);break;
			case "requestScope":
				result = requestScope.get(entryName);break;
			case "viewScope":
				result = viewScope.get(entryName);break;
		
		}
		
		return this.dump(result);
	},
	
	dumpObject : function() {
		return this.dump( dBar.getDumpObject() );
	},
		
	//dump the contents of any object
	dump : function(obj) {
		try {
			
			if (obj==null) {
				return "&lt;null&gt;";
			}
			
			var dumped = "";
			var showLists = dBar.isShowLists();
			
			var objType = this.typeOf(obj);
			
			switch (objType) {
			
			case "string": case "number":
		    	dumped = obj;
		    	break;
			case "object": 
			case "array": 
			case "map":
				
		    	if (!showLists) {
		    		return "<span class=\"highlight\">(hidden list/ map)</span>";
		    	}
		    	
		    	var rows = [];
		    	var type;
		    	var sortVal;
		    	
		    	for(var item in obj) {
		        	
	        		if (rows.length >= this.MAX_DATASET_SAMPLE) {
	        			rows.push( {
	        				sortVal : 999999,
	        				lbl : "", 
	        				val : "<tr><td colspan=\"2\"><span class=\"highlight\">More items available...</span></td></tr>"
	        			});
	        			break;
	        		}
	        		
					var value = obj[item];
					var valueType = this.typeOf(value);
					var label = (this.typeOf(item) == "number" ? "[" + item + "]" : item);
						
					rows.push({
						sortVal : item,
						lbl : label,
						val : ( dBar.hideDetails(label) ? "(skipped)" : ( (valueType=='object' || valueType=="array" || valueType=="map" || valueType=="java.util.Vector") ? this.dump(value) : value ) )
					});
		    	
		        }
		        
		        //sort rows
		    	rows.sort( function(a,b) {
					fieldA = a.sortVal;
					fieldB = b.sortVal;
					return ( fieldA > fieldB ? 1 : (fieldA < fieldB ? -1 : 0));
				} );
		        
				var result = [];
				var first = true;
				
				result.push("<table class=\"dumped\"><tbody>" );
				for (var i=0; j=rows.length, i<j; i++ ) {
					result.push(
							"<tr><td" + (first ? " class=\"first\"" : "") + ">" + rows[i].lbl + "</td>" +
							"<td" + (first ? " class=\"first\"" : "") + ">" + rows[i].val +
							"</td></tr>");
					
					first = false;
				}
				result.push("</tbody></table>");

				dumped = result.join("");
		        break;
		        
			case "java.util.Vector":
				
		    	if (!showLists) {
		    		return "<span class=\"highlight\">(hidden list/ map)</span>";
		    	}
		    	
		    	if (obj.size() == 0) {
		    		dumped = "(empty vector)";
		    	} else {

			    	var it:java.util.Iterator = obj.iterator();
			    	
			    	var rows = [];
					var first = true;
					var c = 0;
			    	
			    	while(it.hasNext() ) {
		    		
		    		    if (rows.length >= this.MAX_DATASET_SAMPLE) {
		    		    	rows.push("<tr><td colspan=\"2\"><span class=\"highlight\">More items available...</span></td></tr>");
		        			break;
		        		}
			    		
			    		rows.push( "<tr><td" + (first ? " class=\"first\"" : "") + ">[" + c + "]</td><td" + (first ? " class=\"first\"" : "") + ">" + this.dump(it.next()) + "</td></tr>");
			    		first = false;
			    		c++;
			    	}
			    	
					dumped = "<table class=\"dumped\"><tbody>" + rows.join("") + "</tbody></table>";
		    	}
				break;
			
		    default:
		    	if (obj.toString != "undefined") {
			    	dumped = obj.toString();
		    	} else {
		    		dumped = "could not dump variable";
		    	}
		    }
		    
		} catch (e) {
			dumped = "error while dumping: " + e.toString();
			
		}
		return dumped;
	},

	//modified typeOf function to detect dates/ arrays and maps 
	typeOf : function(input) {
		
		var isMap = false;
		try {
			isMap = (input.containsKey != null);
		} catch (e) { }
		
		if (isMap) {
			return "map";
		} else if ( typeof(input) != "object") {
			return typeof(input);
		} else if (input === null) {
			return null;
		} else if (input.constructor == (new Date).constructor) {
			return "date";
		} else if (input.constructor == (new Array).constructor) {
			return "array";
		} else {
			return "object";
		}
		
	},
	
	renderInspectorContents: function(){
		
		try {
			
			//reset exception- and expression info
			viewScope.remove( 'exception' );
			viewScope.remove( 'expressionInfo' );			
				
			var expression:string = dBar.getInspectorExpression();
			if( !expression ){ return; }
			
			//don't execute expression if parameters need to be set first
			var pattern = java.util.regex.Pattern.compile("\\((.*?)\\)", java.util.regex.Pattern.DOTALL);
			var matcher = pattern.matcher( expression );
			while(matcher.find()) {
				var match = @Trim(matcher.group(1));
				if (!@Left(match,1).equals("\"") && !@Right(match,1).equals("\"") ) {
					//not something in quotes
					if (match.indexOf(".")>-1 || match.indexOf("int")>-1 || match.indexOf("boolean")>-1 || match.indexOf("long")>-1) {
						eu.linqed.debugtoolbar.DebugToolbar.addInspectorMessage("Edit parameters and click 'Enter' to execute this expression");
						view.postScript("dojo.byId(\"" + getClientId("inputExpression") + "\").focus();");
						return;
					}
				}
			}

			var expressionObj, expressionClass, expressionValue, exceptionString;	
			
			// Test expression as an expression
			try {
				// Fix for expression being interpreted as string
				expressionObj = eval( "var foo;" + expression );
				expressionValue = expressionObj.toString();
				expressionClass = expressionObj.getClass();

			} catch (e) {
				
				try {	//test as a class, but check first if it has a class 'syntax'
				
					if (expression.indexOf("(")>-1) {
						eu.linqed.debugtoolbar.DebugToolbar.addInspectorMessage("Expression executed" + (expressionObj == null ? " (null returned)" : "") + ": " + expression);
						
					} else {
				
						exceptionString = this.getExceptionString( e );
						expressionClass = eval( 'new ' + expression + '()' ).getClass();
						exceptionString = ''; //Reset in case evaluation is OK
					}
						
				} catch(e){		

					exceptionString = this.getExceptionString( e );
					
					try { // Try expression as Class without constructor
						expressionClass = java.lang.Class.forName( expression );
						exceptionString = ''; //Reset in case evaluation is OK
					} catch (e) {
						exceptionString = this.getExceptionString( e );
					}
				}
			}
			
			if( expressionClass ){ 
				className = expressionClass.getName(); }
			else {	
				if( !exceptionString ){ exceptionString = 'No class found for ' + expression; }
			}
			
			if( exceptionString ){
				
				viewScope.put( 'expressionInfo', "" );		
				eu.linqed.debugtoolbar.DebugToolbar.addInspectorMessage(exceptionString);
				
			} else {				
				viewScope.put( 'expressionInfo', {
					className: className,	
					value: expressionValue
				});

			}
			
		} catch( e ){
			viewScope.put( 'expressionInfo', '' );
			dBar.error(e, "inspector");
		}	
	},
	
	getClassItems : function( className, type) {
		try {
			var expression = dBar.getInspectorExpression();
			if( !expression ){ return; }
				
			var expressionObj, expressionClass	
			
			try {
				// Fix for expression being interpreted as string
				expressionObj = eval( 'var foo;' + expression );						
				expressionClass = expressionObj.getClass();	
			
			} catch(e){
				
				try { // Test expression as a class
					expressionClass = eval( 'new ' + expression + '()' ).getClass();
				} catch(e){					
					try { // Try expression as Class without constructor
						expressionClass = java.lang.Class.forName( expression );
					} catch(e){}
				}
			}
			
			if( expressionClass ){
				return (type=="methods" ? dBar.getSortedMethods(expressionClass) : dBar.getSortedFields(expressionClass) );
			}
			
		} catch( e ){
			dBar.error(e);
		}
		
	},
	
	getExceptionString: function( exception ){
		var errorMessage = exception.message;
		
		if (typeof exception === 'com.ibm.jscript.InterpretException') {
			errorMessage = exception.getNode().getTraceString() + '\n\n' + errorMessage;
		}
		
		return errorMessage;
	}
}