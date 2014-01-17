package org.openntf.xsp.bootstrap.renderkit.html_extended.extlib.picker;

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
 *
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;

import org.openntf.xsp.bootstrap.component.UISelect2Picker;
import org.openntf.xsp.bootstrap.util.BootstrapUtil;

import com.ibm.commons.util.StringUtil;
import com.ibm.commons.util.io.json.JsonException;
import com.ibm.commons.util.io.json.JsonGenerator;
import com.ibm.commons.util.io.json.JsonJavaFactory;
import com.ibm.commons.util.io.json.JsonJavaObject;
import com.ibm.xsp.component.UIInputEx;
import com.ibm.xsp.component.UIViewRootEx;
import com.ibm.xsp.extlib.component.picker.data.IPickerData;
import com.ibm.xsp.extlib.component.picker.data.SimpleValuePickerData;
import com.ibm.xsp.extlib.renderkit.html_extended.FacesRendererEx;
import com.ibm.xsp.extlib.util.ExtLibUtil;
import com.ibm.xsp.resource.ScriptResource;
import com.ibm.xsp.resource.StyleSheetResource;
import com.ibm.xsp.util.FacesUtil;
import com.ibm.xsp.renderkit.FacesRenderer;

public class Select2PickerRenderer extends FacesRendererEx {

	@Override
	public void encodeBegin(FacesContext context, UIComponent component)
            throws IOException {
		
        ResponseWriter writer = context.getResponseWriter();
        
        UISelect2Picker picker = (UISelect2Picker)component;
        IPickerData data = picker.getDataProvider();
                   
        UIInputEx _for = (UIInputEx) getFor(context,picker);
       
        boolean readOnly = _for!=null ? FacesUtil.isComponentReadOnly(context, _for) : false;
        
        //load select2 library and stylesheet
    	ScriptResource js = new ScriptResource();
    	js.setClientSide(true);
    	js.setSrc("/.ibmxspres/.extlib/bootstrap/select2/select2.js");
    	
    	StyleSheetResource css = new StyleSheetResource();
    	css.setHref("/.ibmxspres/.extlib/bootstrap/select2/select2.css");
    	
    	UIViewRootEx rootEx = (UIViewRootEx) context.getViewRoot();
    	rootEx.addEncodeResource(js);
    	rootEx.addEncodeResource(css);
    	
    	if (readOnly ) {
    		
    		//selected values, let's not wrap them in a frameset and table here :-)
    		writer.writeText( Select2PickerRenderer.join( _for.getValueAsList(), ", "), null);
    		
    		//TODO: FIX
    		
    	} else {
  
    		String id = picker.getId();
    		
    		//if we're using the search option, select2 needs to be attached to the hidden input
    		if (!picker.isUseRemoteData() ) {
    			
    			newLine(writer);
    			writer.startElement("select", component);
    			writer.writeAttribute("id", picker.getId(), null);
    			writer.writeAttribute("class", "select2picker", null);
    		
    			if (StringUtil.isNotEmpty(_for.getMultipleSeparator()) ) {
    				writer.writeAttribute("multiple", "multiple", null);
    			}
    		    		
    			writer.endElement("select");
    		}
   
    		newLine(writer);
    		writer.startElement("script", component); // $NON-NLS-1$

    		//create the parameters to initialize a new select2 object
    		HashMap<String,Object> params = new HashMap<String,Object>();
    	    		
            params.put("id",  id);
            params.put("forId", _for.getClientId(context));
            params.put("currentValue", _for.getValueAsString() );		//TODO: implement for multi value
            params.put("allowMultiple", StringUtil.isNotEmpty(_for.getMultipleSeparator()) );
            params.put("restUrl", picker.getUrl(context, null));		// rest service URL
            params.put("useRemoteData", picker.isUseRemoteData() );
           	params.put("placeHolder", picker.getPlaceHolder() );
            params.put("allowClearing", picker.isAllowClearing() );
            params.put("template", picker.getTemplate() );
            
			try {
				//writer.writeText("dojo.addOnLoad( function() { initSelect2(" + JsonGenerator.toJson(JsonJavaFactory.instanceEx, params) + "); } );", null);
				writer.writeText("XSP.initSelect2Picker(" + JsonGenerator.toJson(JsonJavaFactory.instanceEx, params) + ");", null);
			} catch (JsonException e) { }
			
    		writer.endElement("script");
    		newLine(writer);
    		
    	}
    	
    }
	
	public static String join(List<?> in, String sep_in) {
	    StringBuilder sb = new StringBuilder();
	    String sep = "";
	    for(Object i : in) {
	        sb.append(sep).append( i.toString() );
	        sep = sep_in;
	    }
	    return sb.toString();                           
	}
	
    @Override
	public void encodeEnd(FacesContext context, UIComponent component)
            throws IOException {
        
    }
    
    protected UIComponent getFor(FacesContext context, UISelect2Picker picker) {
        // Associated control
        String control = picker.getFor();
        if(StringUtil.isNotEmpty(control)) {
            UIComponent c = FacesUtil.getComponentFor(picker, control);
            return c;
        }
        return null;
    }

}



/*

public class Select2Renderer extends FacesRenderer {
	

    protected void writeLink(FacesContext context, ResponseWriter w, UISelect2 picker, IPickerData data, String dojoType) throws IOException {
        UIComponent _for = getFor(context,picker);
   boolean readOnly = _for!=null ? FacesUtil.isComponentReadOnly(context, _for) : false;
        
        
        if(!readOnly) {
            Boolean _disabled_ = _for!=null ? (Boolean)_for.getAttributes().get("disabled") : null; // $NON-NLS-1$
            boolean disabled = _disabled_!=null ? _disabled_:false; // $NON-NLS-1$
            
            if(disabled) {
                w.startElement("span", null); // $NON-NLS-1$
            } else {
                w.startElement("a", null);
                w.writeAttribute("href", "javascript:;", null); // $NON-NLS-1$ $NON-NLS-2$
                if(data!=null) {
                    //PHAN8YWEJZ fix IE namepicker beforeunload event occurring
                    StringBuilder onclick = new StringBuilder();
                    onclick.append("return XSP.selectValue("); // $NON-NLS-1$
                    JSUtil.addSingleQuoteString(onclick, dojoType);
                    onclick.append(","); // $NON-NLS-1$
                    onclick.append(createParametersAsJson(context, picker, _for, data, dojoType));
                    onclick.append(")"); // $NON-NLS-1$
                    w.writeAttribute("onclick", onclick.toString(), null); // $NON-NLS-1$
                    
                    StringBuilder onkeydown = new StringBuilder();
                    onkeydown.append("javascript:var kc=event.keyCode?event.keyCode:event.which;if(kc==32){ return XSP.selectValue("); // $NON-NLS-1$
                    JSUtil.addSingleQuoteString(onkeydown, dojoType);
                    onkeydown.append(","); // $NON-NLS-1$
                    // TODO this onkeydown="javascript: something" contains JSON with double-quotes, breaking the XML attribute
                    onkeydown.append(createParametersAsJson(context, picker, _for,data, dojoType));
                    onkeydown.append(")}"); // $NON-NLS-1$
                    w.writeAttribute("onkeydown", onkeydown.toString(), null); // $NON-NLS-1$ $NON-NLS-2$
                }
                  //LHEY97QME8 adding the role= button
                    w.writeAttribute("role", "button", null); // $NON-NLS-1$ $NON-NLS-2$
            }
          
          
            
            if(disabled) {
                w.endElement("span"); // $NON-NLS-1$
            } else {
                w.endElement("a");
            }
        }
    }
    
    
    protected String createParametersAsJson(FacesContext context, UISelect2 picker, UIComponent _for, IPickerData data, String dojoType) {
        try {
            JsonJavaObject json = new JsonJavaObject();
            DojoRendererUtil.getDojoAttributeMap(picker,json);
            initDojoAttributes(context, picker, _for, data, dojoType, json);
            // And generate them
            return DojoRendererUtil.getDojoAttributesAsJson(context,picker,json);
        } catch(Exception e) {
            throw new FacesExceptionEx(e);
        }
    }
    
    protected void initDojoAttributes(FacesContext context, UISelect2 picker, UIComponent _for, IPickerData data, String dojoType, JsonJavaObject json) throws IOException {
        // Associated control
        boolean allowMultiple = false;
        if(_for!=null) {
            json.putString("control",_for.getClientId(context)); // $NON-NLS-1$
            if(_for instanceof UIInputEx) {
                UIInputEx iex = (UIInputEx)_for; 
                // Check for a multiple separator
                String ch = iex.getMultipleSeparator();
                if(StringUtil.isNotEmpty(ch)) {
                    boolean trim = iex.isMultipleTrim();
                    json.putString("msep",ch); // $NON-NLS-1$
                    json.putBoolean("trim",trim); // $NON-NLS-1$
                    allowMultiple = true;
                }
            }
        }

        // Dialog title
        String title = picker.getDialogTitle();
        if(StringUtil.isEmpty(title)) {
            if( allowMultiple ){
                title = getDialogTitleMultipleSelect();
            }else{
                title = getDialogTitleSingleSelect();
            }
        }
        json.putString("dlgTitle",title); // $NON-NLS-1$
        // Dialog sizes
        String lw = picker.getListWidth();
        if(StringUtil.isNotEmpty(lw)) {
            json.putString("listWidth",lw); // $NON-NLS-1$
        }
        String lh = picker.getListHeight();
        if(StringUtil.isNotEmpty(lh)) {
            json.putString("listHeight",lh); // $NON-NLS-1$
        }
        // The rest service URL
        String url = picker.getUrl(context, null);
        json.putString("url",url); // $NON-NLS-1$
        // Generate the source list, if applicable
        boolean hasMultipleSource = data.hasCapability(IPickerData.CAPABILITY_MULTIPLESOURCES);
        if(hasMultipleSource) {
            String[] labels = data.getSourceLabels();
            if(labels!=null && labels.length>=2) {
                json.putObject("sources",labels); // $NON-NLS-1$
            }
        }
    }  
    
}

*/