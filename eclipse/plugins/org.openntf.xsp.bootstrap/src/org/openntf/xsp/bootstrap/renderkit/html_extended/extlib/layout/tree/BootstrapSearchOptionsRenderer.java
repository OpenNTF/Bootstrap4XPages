/*
 * � Copyright IBM Corp. 2010
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

package org.openntf.xsp.bootstrap.renderkit.html_extended.extlib.layout.tree;

import com.ibm.xsp.extlib.renderkit.html_extended.outline.tree.HtmlComboBoxRenderer;



public class BootstrapSearchOptionsRenderer extends HtmlComboBoxRenderer {
    
    private static final long serialVersionUID = 1L;

    public BootstrapSearchOptionsRenderer() {
    }
        
    @Override
    public String getStyle() {
        return "border-width: 1px; border-style: solid;";
//      return "border-style: none;";
    }
    
    @Override
    public String getStyleClass() {
        return "search-options applayout-search-options";
    }
}