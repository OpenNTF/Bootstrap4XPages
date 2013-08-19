/* ==========================================================
 * CarouselItem.js v2.0.0
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
    "dojo/_base/declare",
    "dijit/_WidgetBase",
    "dijit/_TemplatedMixin",
    "dijit/_Contained",
    "dojo/query"
], function (support, declare, _WidgetBase, _TemplatedMixin, _Contained, query) {
    "use strict";

    // module:
    //      CarouselItem

    return declare("CarouselItem", [_WidgetBase, _TemplatedMixin, _Contained], {
        // summary:
        //      Item for viewing inside the carousel widget
        // description:
        //      This widget provides the basic functionality needed for displaying a Twitter Bootstrap carousel item.
        // example:
        // |    <div data-dojo-type="CarouselItem" data-dojo-props="img:'img.jpg', title:'Slide'"></div>

        templateString:
            '<div class="item">' +
            '   <img data-dojo-attach-point="imageNode" />' +
            '   <div class="carousel-caption">' +
            '       <h4 data-dojo-attach-point="titleNode"></h4>' +
            '       <p data-dojo-attach-point="contentNode"></p>' +
            '   </div>' +
            '</div>',

        // active: Boolean
        //          makes a carousel item active or inactive. Default is false.
        active: false,
        _setActiveAttr: function(val){
            this._set("active", val);
            query(this.domNode)[this.active === true ? "addClass" : "removeClass"]("active");
        },

        // img: String
        //          image for the carousel item.
        img: "",
        _setImgAttr: function(val){
            this._set("img", val);
            if(this.val !== ""){
                query(this.imageNode).removeClass("hide");
                this.imageNode.src = this.img;
            } else {
                query(this.imageNode).addClass("hide");
            }
        },

        // title: String
        //          title for the carousel item
        title: "",
        _setTitleAttr: { node: "titleNode", type: "innerHTML" },

        // content: String
        //          html content for the carousel item
        content: "",
        _setContentAttr: { node: "contentNode", type: "innerHTML" },

        postCreate:function () {
            // summary:
            //      hides the carousel caption if no content or title is present.
            // tags:
            //		private extension
            if(this.title === "" && this.content === ""){
                query(".carousel-caption", this.domNode).addClass("hide");
            }
        }
    });
});