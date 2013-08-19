/* ==========================================================
 * CollapseItem.js v2.0.0
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
    "dijit/_Contained",
    "dojo/query",
    "dojo/on",
    "dojo/_base/lang",
    "dojo/dom-style",
    "dojo/dom-class"
], function (support, _BootstrapWidget, declare, _TemplatedMixin, _Contained, query, on, lang, domStyle, domClass) {
    "use strict";

    // module:
    //      CollapseItem

    var _reset = function(size){
            size = size ? parseInt(size, 10) + 'px' : 'auto';
            var dimension = _getParentDimension.call(this);
            domClass.remove(this.contentNode, 'collapse');
            domStyle.set(this.contentNode, dimension, size);
            this._offsetWidth = this.contentNode.offsetWidth;
            domClass[(size !== null ? 'add' : 'remove')](this.contentNode, 'collapse');
            return this;
        },
        _transition = function(method, startEvent, completeEvent, quiet){
            var _complete = lang.hitch(this, function(){
                if(startEvent === "show"){ _reset.call(this); }
                this._transitioning = false;
                quiet || this.emit(completeEvent, { type: completeEvent, target: this.domNode });
            });
            quiet || this.emit(startEvent, { type: startEvent, target: this.domNode });

            this._transitioning = true;

            domClass.add(this.contentNode, 'in');

            if (support.trans && domClass.contains(this.contentNode, 'collapse')) {
                on.once(this.domNode, support.trans.end, _complete);
            } else {
                _complete();
            }
        },
        _getParentDimension = function(){
            var parentNode = this.getParent().domNode;
            return domClass.contains(parentNode, 'width') ? 'width' : 'height';
        };

    return declare("CollapseItem", [_BootstrapWidget, _TemplatedMixin], {
        // summary:
        //      Item for viewing inside the collapse widget
        // description:
        //      Provides an accordion group with collapse and expand support. A CollapseItem can be used as
        //      part of a Collapse widget or on its own. This makes it useful for providing collapse support
        //      to a simple panel.
        //
        //      ## Events ##
        //		Call `widget.on("show", func)` to monitor when the CollapseItem is about to show.
        //
        //		Call `widget.on("shown", func)` to monitor when the CollapseItem has been shown.
        //
        //		Call `widget.on("hide", func)` to monitor when the CollapseItem is about to hide.
        //
        //		Call `widget.on("hidden", func)` to monitor when the CollapseItem has been hidden.
        //
        // example:
        // |    <div data-dojo-type="CollapseItem"
        // |         data-dojo-props="title:'California', content:'California is a West Coast state.'"></div>
        // example:
        // |    <div data-dojo-type="CollapseItem"
        // |         data-dojo-props="title:'<i class=\'icon-globe\'></i>Globe', content:'Some content.'"></div>

        templateString:
            '<div class="${baseClass}">' +
            '    <div class="accordion-heading">' +
            '        <a class="accordion-toggle" data-dojo-attach-point="titleNode"></a>' +
            '    </div>' +
            '    <div class="accordion-body collapse" data-dojo-attach-point="contentNode">' +
            '        <div class="accordion-inner" data-dojo-attach-point="containerNode"></div>' +
            '    </div>' +
            '</div>',

        baseClass: "accordion-group",

        // title: String
        //          title for the carousel item
        title: "",
        _setTitleAttr: { node: "titleNode", type: "innerHTML" },

        // content: String
        //          html content for the carousel item
        content: "",
        _setContentAttr: { node: "containerNode", type: "innerHTML" },

        postCreate:function () {
            // summary:
            //      creates click handler for collapsing and expanding the widget.
            // tags:
            //		private extension
            this.own(on(this.titleNode, "click", lang.hitch(this, function(e){
                this.toggle();
            })));
            this._offsetWidth = 0;
            this._transitioning = false;
        },

        show: function(/*Boolean?*/quiet){
            // summary:
            //      expands the widget.
            if (this._transitioning || !this.isCollapsed()) { return; }

            var dimension = _getParentDimension.call(this),
                scroll = support.toCamel(['scroll', dimension].join('-'));
            domStyle.set(this.contentNode, dimension, '0px');
            _transition.call(this, 'add', 'show', 'shown', quiet);
            domClass.add(this.contentNode, 'in');
            if (support.trans) {
                domStyle.set(this.contentNode, dimension, this.contentNode[scroll] + 'px');
            }
        },

        hide: function(/*Boolean?*/quiet){
            // summary:
            //      collapses the widget.
            if (this._transitioning || this.isCollapsed()) { return; }

            var dimension = _getParentDimension.call(this);
            _reset.call(this, domStyle.get(this.contentNode, dimension));
            _transition.call(this, 'remove', 'hide', 'hidden', quiet);
            domClass.remove(this.contentNode, 'in');
            domStyle.set(this.contentNode, dimension, '0px');
        },

        toggle: function(){
            // summary:
            //      toggles the widget collapsed or expanded
            this[this.isCollapsed() ? 'show' : 'hide']();
        },

        isCollapsed: function(){
            // summary:
            //      returns whether the widget is collapsed or expanded
            return !domClass.contains(this.contentNode, 'in');  //return Boolean
        }
    });
});