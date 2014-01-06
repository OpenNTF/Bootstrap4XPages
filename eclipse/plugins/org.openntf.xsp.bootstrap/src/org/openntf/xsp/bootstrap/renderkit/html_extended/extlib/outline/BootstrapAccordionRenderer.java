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

package org.openntf.xsp.bootstrap.renderkit.html_extended.extlib.outline;

import javax.faces.context.FacesContext;

import com.ibm.xsp.extlib.component.outline.AbstractOutline;
import com.ibm.xsp.extlib.component.outline.UIOutlineGeneric;
import com.ibm.xsp.extlib.renderkit.html_extended.outline.OutlineDropDownButtonRenderer;
import com.ibm.xsp.extlib.renderkit.html_extended.outline.tree.HtmlListRenderer;
import com.ibm.xsp.extlib.tree.ITreeRenderer;

public class BootstrapAccordionRenderer extends OutlineDropDownButtonRenderer {

    @Override
    protected ITreeRenderer findTreeRenderer(FacesContext context, AbstractOutline outline) {
    	// ExtLib bug, where the regular outline picks up the accordion theme (style kit family)
    	// we hard code a workaround here
    	if(outline instanceof UIOutlineGeneric) {
    		HtmlListRenderer r = new HtmlListRenderer();
    		return r;
    	}
        return new org.openntf.xsp.bootstrap.renderkit.html_extended.extlib.outline.tree.BootstrapAccordionRenderer(outline.getClientId(context));
    }
}