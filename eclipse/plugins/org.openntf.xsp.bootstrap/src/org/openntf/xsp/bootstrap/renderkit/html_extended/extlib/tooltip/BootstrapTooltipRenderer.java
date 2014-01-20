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

package org.openntf.xsp.bootstrap.renderkit.html_extended.extlib.tooltip;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import org.openntf.xsp.bootstrap.resources.BootstrapResources;

import com.ibm.commons.util.StringUtil;
import com.ibm.xsp.FacesExceptionEx;
import com.ibm.xsp.component.UIViewRootEx;
import com.ibm.xsp.dojo.FacesDojoComponent;
import com.ibm.xsp.extlib.component.tooltip.UITooltip;
import com.ibm.xsp.extlib.renderkit.dojo.DojoRendererUtil;
import com.ibm.xsp.extlib.renderkit.html_extended.tooltip.TooltipRenderer;
import com.ibm.xsp.extlib.resources.ExtLibResources;
import com.ibm.xsp.util.FacesUtil;

public class BootstrapTooltipRenderer extends TooltipRenderer {

    @Override
	public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
        ResponseWriter w = context.getResponseWriter();
        
        UITooltip tooltip = (UITooltip)component;
        String clientId = tooltip.getClientId(context);

        boolean dynamicContent = tooltip.isDynamicContent();
        
        // Add the dojo module
        UIViewRootEx rootEx = (UIViewRootEx)context.getViewRoot();
        String dojoType;
        ExtLibResources.addEncodeResource(rootEx, BootstrapResources.bootstrapTooltip);
        if(dynamicContent) {
            dojoType = "extlib.dijit.BootstrapDynamicTooltip"; // $NON-NLS-1$
        } else {
            dojoType = "extlib.dijit.BootstrapTooltip"; // $NON-NLS-1$
            //dojoType = "dijit.Tooltip"; // $NON-NLS-1$
        }

        // Main dialog div 
        w.startElement("div", component); // $NON-NLS-1$
        w.writeAttribute("id", clientId, "id"); // $NON-NLS-1$ $NON-NLS-2$

        // Compose the list of attributes from the list of dojo attributes
        Map<String,String> attrs = new HashMap<String,String>();
        DojoRendererUtil.getDojoAttributeMap(tooltip,attrs);
        initDojoAttributes(context, tooltip, attrs);
        DojoRendererUtil.writeDojoHtmlAttributes(context,tooltip,dojoType,attrs);
    }    
}