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

package org.openntf.xsp.bootstrap.renderkit.html_extended.extlib.data;

import java.io.IOException;

import javax.faces.component.NamingContainer;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import org.openntf.xsp.bootstrap.resources.BootstrapResources;

import com.ibm.commons.util.StringUtil;
import com.ibm.xsp.extlib.component.data.AbstractDataView;
import com.ibm.xsp.extlib.component.data.UIDataView;
import com.ibm.xsp.extlib.renderkit.html_extended.data.DataViewRenderer;
import com.ibm.xsp.extlib.util.ExtLibRenderUtil;
import com.ibm.xsp.renderkit.html_basic.HtmlRendererUtil;
import com.ibm.xsp.util.JSUtil;


/**
 * One UI data view renderer.
 */
public class BootstrapCustomViewRenderer extends DataViewRenderer {

    @Override
    protected Object getProperty(int prop) {
        switch(prop) {
            case PROP_BLANKIMG:                 return BootstrapResources.get().BLANK_GIF;
            // note, for an Alt, there's a difference between the empty string and null
            case PROP_BLANKIMGALT:              return ""; //$NON-NLS-1$
            case PROP_ALTTEXTCLASS:             return "lotusAltText"; // $NON-NLS-1$
            
            
            case PROP_HEADERCLASS:              return "clearfix"; // $NON-NLS-1$
            case PROP_HEADERLEFTSTYLE:          return null; 
            case PROP_HEADERLEFTCLASS:          return "pull-left"; // $NON-NLS-1$
            case PROP_HEADERRIGHTSTYLE:         return null; 
            case PROP_HEADERRIGHTCLASS:         return "pull-right"; // $NON-NLS-1$

            case PROP_FOOTERCLASS:              return "clearfix"; // $NON-NLS-1$
            case PROP_FOOTERLEFTSTYLE:          return null; 
            case PROP_FOOTERLEFTCLASS:          return "pull-left"; // $NON-NLS-1$
            case PROP_FOOTERRIGHTSTYLE:         return null; 
            case PROP_FOOTERRIGHTCLASS:         return "pull-right"; // $NON-NLS-1$

            case PROP_TABLECLASS:               return "clearfix table"; // $NON-NLS-1$
            case PROP_TABLEROWEXTRA:            return "lotusMeta lotusNowrap"; // $NON-NLS-1$
            
            case PROP_COLLAPSEICON:             return BootstrapResources.get().BLANK_GIF;
            case PROP_COLLAPSEICONSTYLE:        return "padding-right:3px"; // $NON-NLS-1$
            case PROP_COLLAPSEICONCLASS: 		return BootstrapResources.get().getIconClass("minus-sign")+" icon-lighter";	
            case PROP_EXPANDICON:               return BootstrapResources.get().BLANK_GIF;            
            case PROP_EXPANDICONSTYLE:          return "padding-right:3px"; // $NON-NLS-1$
            case PROP_EXPANDICONCLASS: 			return BootstrapResources.get().getIconClass("plus-sign")+" icon-lighter";	
            
            case PROP_TABLEROWINDENTPX:         return 10;
            case PROP_SHOWICONDETAILSCLASS:     return BootstrapResources.get().getIconClass("chevron-down")+" icon-lighter"; // $NON-NLS-1$
            case PROP_HIDEICONDETAILSCLASS:     return BootstrapResources.get().getIconClass("chevron-up")+" icon-lighter"; // $NON-NLS-1$
            
            case PROP_TABLEHDRCOLIMAGE_SORTBOTH_ASCENDING:  return BootstrapResources.get().VIEW_COLUMN_SORT_BOTH_ASCENDING;
            case PROP_TABLEHDRCOLIMAGE_SORTBOTH_DESCENDING: return BootstrapResources.get().VIEW_COLUMN_SORT_BOTH_DESCENDING;
            case PROP_TABLEHDRCOLIMAGE_SORTBOTH:            return BootstrapResources.get().VIEW_COLUMN_SORT_NONE;
            case PROP_TABLEHDRCOLIMAGE_SORTED_ASCENDING:    return BootstrapResources.get().VIEW_COLUMN_SORT_NORMAL;
            case PROP_TABLEHDRCOLIMAGE_SORTED_DESCENDING:   return BootstrapResources.get().VIEW_COLUMN_SORT_REVERSE;
            // the bootstrap sort header icons are 16x13 px
            case PROP_TABLEHDRCOLIMAGE_SORT_WIDTH:           return "16"; //$NON-NLS-1$
            case PROP_TABLEHDRCOLIMAGE_SORT_HEIGHT:          return "13"; //$NON-NLS-1$
        }
        return super.getProperty(prop);
    }
    
    @Override
	protected void writeShowHideDetailContent(FacesContext context, ResponseWriter w, AbstractDataView c, ViewDefinition viewDef) throws IOException {
        if(!viewDef.hasSummary || !viewDef.hasDetail) {
            return;
        }
        
        // In case this is diabled for this particular row
        if(viewDef.rowDisableHideRow) {
            return;
        }
        
        boolean detailsOnClient = viewDef.detailsOnClient;
        String linkId = c.getClientId(context) + (viewDef.rowDetailVisible?HIDE_DELIMITER:SHOW_DELIMITER) + viewDef.rowPosition;

        w.startElement("a",c);
        w.writeAttribute("id",linkId,null); // $NON-NLS-1$
        w.writeAttribute("href","javascript:;",null); // $NON-NLS-1$ $NON-NLS-2$
        //LHEY97CCSZ adding the role=button
        w.writeAttribute("role", "button", null); // $NON-NLS-1$ // $NON-NLS-2$
        String label = (String)getProperty(viewDef.rowDetailVisible ? PROP_HIDEICONDETAILSTOOLTIP : PROP_SHOWICONDETAILSTOOLTIP);
        if(StringUtil.isNotEmpty(label)) {
            w.writeAttribute("title", label, null); // $NON-NLS-1$
            w.writeAttribute("aria-label", label, null); // $NON-NLS-1$
        }
        
        if(detailsOnClient) {
            StringBuilder b = new StringBuilder();
            b.append(viewDef.showHideDetailFunctionName);
            b.append("(");
            JSUtil.addSingleQuoteString(b,Integer.toString(viewDef.dataModel.getRowIndex()));
            b.append(",");
            JSUtil.addSingleQuoteString(b,viewDef.rowPosition);
            b.append(")");
            w.writeAttribute("onclick","javascript:"+b.toString(),null); // $NON-NLS-1$ $NON-NLS-2$
        }
        String clazz = (String)getProperty(viewDef.rowDetailVisible?PROP_HIDEICONDETAILSCLASS:PROP_SHOWICONDETAILSCLASS);
       
        w.startElement("span",c); // $NON-NLS-1$    
        w.writeAttribute("class",clazz,null); // $NON-NLS-1$
   
        if( viewDef.rowDetailVisible ){
            w.writeAttribute("title", "Hide",null); // $NLS-AbstractWebDataViewRenderer.Hide_HideDetailIconAlt-1$
        }else{
        	 w.writeAttribute("title", "Show",null); // $NLS-AbstractWebDataViewRenderer.Show-1$
        }
        w.endElement("span"); // $NON-NLS-1$
        w.endElement("a");

        if(!detailsOnClient) {
            if(viewDef.viewRowRefresh) {
                String refreshId = c.getClientId(context)+NamingContainer.SEPARATOR_CHAR+UIDataView.ROW_ID;
                setupSubmitOnClick(context, c, linkId, linkId, refreshId);
            } else {
                setupSubmitOnClick(context, c, linkId, linkId, null);
            }
        }
    }
    
    @Override
	protected void writeExpandCollapseIcon(FacesContext context, ResponseWriter w, AbstractDataView c, ViewDefinition viewDef) throws IOException {
        boolean leaf = isRowLeaf(context, c, viewDef);
        if(leaf) {
            String icon = (String)getProperty(PROP_EMPTYICON);
            if(icon!=null) {
                w.startElement("img",c); // $NON-NLS-1$
                w.writeAttribute("src",HtmlRendererUtil.getImageURL(context,icon),null); // $NON-NLS-1$
                String iconAlt = (String) getProperty(PROP_EMPTYICONALT);
                if( ExtLibRenderUtil.isAltPresent(iconAlt) ){
                    // "" - present but empty
                    w.writeAttribute("alt",iconAlt,null); //$NON-NLS-1$
                }
                String style = (String)getProperty(PROP_EMPTYICONSTYLE);
                if(StringUtil.isNotEmpty(style)) {
                    w.writeAttribute("style",style,null); // $NON-NLS-1$
                }
                String clazz = (String)getProperty(PROP_EMPTYICONCLASS);
                if(StringUtil.isNotEmpty(clazz)) {
                    w.writeAttribute("class",clazz,null); // $NON-NLS-1$
                }
                w.endElement("img"); // $NON-NLS-1$
            }
        } else {
            boolean expanded = isRowExpanded(context, c, viewDef);
            String icon = (String)getProperty(expanded ? PROP_COLLAPSEICON : PROP_EXPANDICON);
            if(icon!=null) {
                String linkId = c.getClientId(context) + (expanded?SHRINK_DELIMITER:EXPAND_DELIMITER) + viewDef.rowPosition;
                w.startElement("a",c);
                w.writeAttribute("id",linkId,null); // $NON-NLS-1$
                w.writeAttribute("href","javascript:;",null); // $NON-NLS-1$ $NON-NLS-2$
                //LHEY97CCSZ adding the role=button
                w.writeAttribute("role", "button", null); // $NON-NLS-1$ // $NON-NLS-2$
                String iconAlt = (String) getProperty(expanded? PROP_COLLAPSEICONALT : PROP_EXPANDICONALT);
                w.writeAttribute("title", iconAlt, null); //$NON-NLS-1$
                w.writeAttribute("aria-label", iconAlt, null); //$NON-NLS-1$

                w.startElement("span",c); // $NON-NLS-1$
                w.writeAttribute("title", iconAlt, null); //$NON-NLS-1$
                String style = (String)getProperty(expanded ? PROP_COLLAPSEICONSTYLE : PROP_EXPANDICONSTYLE);
                if(StringUtil.isNotEmpty(style)) {
                    w.writeAttribute("style",style,null); // $NON-NLS-1$
                }
                String clazz = (String)getProperty(expanded ? PROP_COLLAPSEICONCLASS : PROP_EXPANDICONCLASS);
                if(StringUtil.isNotEmpty(clazz)) {
                    w.writeAttribute("class",clazz,null); // $NON-NLS-1$
                }
                w.endElement("span"); // $NON-NLS-1$
                w.endElement("a");
                
                setupSubmitOnClick(context, c, linkId, linkId, null);
            }
        }
    }
}