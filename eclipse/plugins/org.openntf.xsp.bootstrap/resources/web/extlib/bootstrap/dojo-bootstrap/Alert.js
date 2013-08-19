/* ==========================================================
 * Alert.js v2.0.0
 * ==========================================================
 * Copyright 2012 xsokev
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ========================================================== */

define([
    "./Support",
    "./_BootstrapWidget",
    "dojo/_base/declare",
    "dijit/_TemplatedMixin",
    "dojo/query",
    "dojo/on",
    "dojo/_base/lang",
    "dojo/dom-class",
    "dojo/dom-style"
], function (support, _BootstrapWidget, declare, _TemplatedMixin, query, on, lang, domClass, domStyle) {
    "use strict";

    // module:
    //      Alert

    return declare("Alert", [_BootstrapWidget, _TemplatedMixin], {
        // summary:
        //		Wrap all alerts with close functionality.
        // description:
        //		Wrap all alerts with close functionality. To have your alerts animate out when closed,
        //      make sure they have the .fade and .in class already applied to them.
        //
        //      ## Events ##
        //		Call `widget.on("close", func)` to monitor when an Alert widget is about to close.
        //
        //		Call `widget.on("closed", func)` to monitor when an Alert widget has closed.
        //
        // example:
        // |	<div data-dojo-type="Alert" data-dojo-props="timeout:10000">...alert message...</div>
        //
        // example:
        // |	new Alert({closable: false, content: "..."});
        //

        // templateString: String
        //      template used to create new Alerts programmatically
        templateString:
            '<div class="alert fade in">' +
            '        <button data-dojo-attach-point="closeNode" class="close">&times;</button>' +
            '   <div data-dojo-attach-point="containerNode"></div>' +
            '</div>',

        // closable: Boolean
        //      alert can be closed. Default is true.
        closable: true,
        _setClosableAttr: function(val){
            this._set("closable", val);
            domStyle.set(this.closeNode, "visibility", (val ? "visible" : "hidden"));
        },

        // content: String
        //      content for the alert.
        content: "",
        _setContentAttr: { node: "containerNode", type: "innerHTML" },

        // timeout: Number|Boolean
        //      number of milliseconds to display alert before closing. Default is false.
        timeout: false,

        postCreate:function () {
            // summary:
            //      creates event handler for close button. Handles initial timeout value if not falsey.
            // tags:
            //		private extension
            query("> .close", this.domNode).on("click", lang.hitch(this, function(){
                this.close();
            }));
            if(!support.falsey(this.timeout) && typeof parseInt(this.timeout, 10) === "number"){
                window.setTimeout(lang.hitch(this, function(){
                    this.close();
                }), parseInt(this.timeout, 10));
            }
        },

        close: function(){
            // summary:
            //      hide and destroy widget using an optional fade transition
            var transition = support.trans,
                _remove = lang.hitch(this, function(){
                    this.emit('closed', {});
                    this.destroyRecursive();
                });

            this.emit("close", {});
            if (domClass.contains(this.domNode, 'fade')) {
                if(transition){
                    domClass.remove(this.domNode, "in");
                    on(this.domNode, support.trans.end, _remove);
                } else {
                    support.fadeOut(this.domNode, _remove);
                }
            } else {
                _remove();
            }
        }
    });
});
