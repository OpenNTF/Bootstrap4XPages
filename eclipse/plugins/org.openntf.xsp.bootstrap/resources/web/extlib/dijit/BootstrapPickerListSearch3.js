/*
* © Copyright IBM Corp. 2010
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at:
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
* implied. See the License for the specific language governing
* permissions and limitations under the License.
*/
dojo.provide("extlib.dijit.BootstrapPickerListSearch3");

dojo.require("extlib.dijit.BootstrapDialog3")
dojo.require("extlib.dijit.PickerListSearch")

dojo.declare(
	'extlib.dijit.BootstrapPickerListSearch3',
	[extlib.dijit.PickerListSearch],
	{
        listWidth: "210px",
		templateString: dojo.cache("extlib.dijit", "templates/BootstrapPickerListSearch3.html")
	}
);
