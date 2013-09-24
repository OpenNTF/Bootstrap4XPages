/*
 * © Copyright IBM Corp. 2010, 2012
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

package org.openntf.xsp.bootstrap.renderkit.html_extended.extlib.containers;

import java.io.IOException;

import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import org.openntf.xsp.bootstrap.renderkit.html_extended.extlib.outline.tree.BootstrapWidgetDropDownRenderer;
import org.openntf.xsp.bootstrap.resources.BootstrapResources;

import com.ibm.xsp.extlib.component.containers.UIWidgetContainer;
import com.ibm.xsp.extlib.renderkit.html_extended.containers.WidgetContainerRenderer;


/**
 * OneUI Widget container renderer.
 */
public class BootstrapWidgetContainerRenderer extends WidgetContainerRenderer {

	// TEMP: until target platform fixed for the build system
	protected static final int PROP_TWISTYCLASSIMGOPEN      = 20;
    protected static final int PROP_TWISTYCLASSIMGCLOSE     = 21;

    public BootstrapWidgetContainerRenderer() {
    }

    // Use 'panel' classes that will be in Bootstrap 3
    @Override
    protected Object getProperty(int prop) {
        switch(prop) {
            //Main
            case PROP_CSSWIDGETBASIC:       return "panel"; // $NON-NLS-1$
            case PROP_CSSWIDGETSIDEBAR:     return "panel"; // $NON-NLS-1$
            case PROP_CSSWIDGETPLAIN:       return "panel"; // $NON-NLS-1$
            case PROP_CONTAINER_STYLE_DEFAULT: return null;
            
            // Title Bar
            case PROP_TAGTITLE:             return "div"; // $NON-NLS-1$
            case PROP_CSSTITLEBAR:			return "panel-heading"; // $NON-NLS-1$
            case PROP_STYLETITLEBAR:        return "overflow: auto; cursor: auto;"; // Not movable.... $NON-NLS-1$
            case PROP_TAGTITLETEXT:         return "h5"; // $NON-NLS-1$
            case PROP_CSSTITLETEXT:         return "panel-title pull-left"; // $NON-NLS-1$
            case PROP_TREEDROPDOWN:         return new BootstrapWidgetDropDownRenderer();
            // title bar looks ok when no text present - no need to insert nbsp 
            case PROP_TITLE_PREVENT_BLANK:  return false;
            case PROP_TWISTYCLASSIMGOPEN:   return BootstrapResources.get().getIconClass("chevron-down"); // $NON-NLS-1$
            case PROP_TWISTYCLASSIMGCLOSE:  return BootstrapResources.get().getIconClass("chevron-right"); // $NON-NLS-1$

            // Header
            case PROP_TAGHEADER:            return "div"; // $NON-NLS-1$
            
            // Body
            case PROP_CSSSCROLLUP:          return "widget-section-scroll"; // $NON-NLS-1$
            case PROP_CSSSCROLLUPLINK:      return "widget-section-arrow "+BootstrapResources.get().getIconClass("arrow-up"); // $NON-NLS-1$
            
            case PROP_CSSSCROLLUPALTTEXT:   return "&#x25b2;"; //$NON-NLS-1$
            
            case PROP_CSSSCROLLDOWN:        return "widget-section-scroll"; // $NON-NLS-1$
            case PROP_CSSSCROLLDOWNLINK:    return "widget-section-arrow "+BootstrapResources.get().getIconClass("arrow-down"); // $NON-NLS-1$
            
            case PROP_CSSSCROLLDOWNALTTEXT: return "&#x25bc;"; //$NON-NLS-1$

            // body looks ok when no text present - no need to insert nbsp 
            case PROP_BODY_PREVENT_BLANK:   return false;
            
            // Footer
            case PROP_TAGFOOTER:            return "div"; // $NON-NLS-1$
            case PROP_CSSFOOTER:            return "panel-footer"; // $NON-NLS-1$
        }
        return null;
    }
    
    @Override
    protected void writeDropDown(FacesContext context, ResponseWriter w, UIWidgetContainer c) throws IOException {
    	w.startElement("div", null);
    	w.writeAttribute("class", "pull-right", null);
    	super.writeDropDown(context, w, c);
    	w.endElement("div");
    }
}