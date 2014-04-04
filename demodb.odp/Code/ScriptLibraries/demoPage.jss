/*
 * demo function that adds some dummy variables to the various scopes
 * and that shows how to use some of the XPage Debug Toolbar functions
 */

function setDemoScopeVariables() {
	
	try {
		
		dBar.info("Adding demo values to the various scopes...", "setDemoScopeVariables");
		
		dBar.startTimer("adding scope messages");

		viewScope.put("foo", "bar");
		viewScope.put("arrayObject", [
			{ id : "object1", name : "IBM"},
			{ id : "object2", name : "Microsoft"},
			{ id : "object3", name : "Oracle"},
			{ id : "object4", name : "Facebook"},
			{ id : "object5", name : "Twitter"}

		]);

		applicationScope.put("appSampleVar", 8796);
		sessionScope.put("sessionSampleVar", false);
		
		var m = new java.util.HashMap();
		m.put("1000", { name : "Hashmap object", count : 2 });
		m.put("2000", { name : "Hashmap object", count : 4 });
		m.put("3000", { name : "Hashmap object", count : 6 });
		
		sessionScope.put("hashMap", m);
		
		var v = new java.util.Vector();
		v.add("red");
		v.add("white");
		v.add("blue");
		viewScope.put("vector", v);
		
		viewScope.put("someObject", {
			_var1 : true,
			_var2 : 45823,
			_var3 : "yes"
			
		})
		
		//large(r) array
		var largeArray = [];
		for (var i=0; i<500; i++) {
			largeArray.push( "value " + i);	
		
		}
		viewScope.put("largeArray", largeArray);
		
		//wait 400 ms for the timer
		java.lang.Thread.currentThread().sleep(400);		
		
		dBar.stopTimer("adding scope messages");
		
		dBar.info("Done adding", "setDemoScopeVariables");
		
	} catch (e) {
		
		dBar.error(e, "setDemoScopeVariables");
		
	}

}

