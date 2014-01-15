package org.openntf.xsp.bootstrap.component;

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

import javax.faces.el.ValueBinding;

import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibm.commons.util.TextUtil;
import com.ibm.domino.services.rest.RestServiceEngine;
import com.ibm.xsp.component.UITypeAhead;
import com.ibm.xsp.dojo.DojoAttribute;
import com.ibm.xsp.dojo.FacesDojoComponent;
//import com.ibm.xsp.extlib.component.picker.AbstractPicker;
//import com.ibm.xsp.extlib.component.picker.AbstractPicker.Service;
import com.ibm.xsp.extlib.component.picker.data.IPickerData;
import com.ibm.xsp.extlib.component.picker.data.IPickerEntry;
import com.ibm.xsp.extlib.component.picker.data.IPickerResult;
import com.ibm.xsp.extlib.component.picker.data.IValuePickerData;
import com.ibm.xsp.extlib.component.picker.data.SimplePickerOptions;
import com.ibm.xsp.extlib.component.rest.IRestService;
import com.ibm.xsp.extlib.component.rest.UIBaseRestService;
import com.ibm.xsp.extlib.services.rest.picker.RestValuePickerService;
import com.ibm.xsp.extlib.services.rest.picker.ValuePickerParameters;
import com.ibm.xsp.extlib.stylekit.StyleKitExtLibDefault;
import com.ibm.xsp.extlib.util.ExtLibUtil;
//import com.ibm.xsp.extlib.util.ThemeUtil;
import com.ibm.xsp.stylekit.ThemeControl;
import com.ibm.xsp.util.FacesUtil;
import com.ibm.xsp.util.StateHolderUtil;
import com.ibm.xsp.util.TypedUtil;

public class UISelect2Picker extends UIBaseRestService {
	
	private String _for;
	private IValuePickerData dataProvider;
	
	public UISelect2Picker() {
		super();
		setRendererType("org.openntf.xsp.bootstrap.Select2PickerRendererType");
	}

	@Override
	public String getFamily() {
		return "org.openntf.xsp.bootstrap.Select2Picker";
	}
	
	public String getFor() {
        if (null != this._for) {
            return this._for;
        }
        ValueBinding _vb = getValueBinding("for"); //$NON-NLS-1$
        if (_vb != null) {
            return (java.lang.String) _vb.getValue(FacesContext.getCurrentInstance());
        } else {
            return null;
        }
    }

    public void setFor(String _for) {
        this._for = _for;
    }
    
    public IValuePickerData getDataProvider() {
		return this.dataProvider;
	}

	public void setDataProvider(IValuePickerData dataProvider) {
		this.dataProvider = dataProvider;
	}
	
	
	@Override
	public void restoreState(FacesContext _context, Object _state) {
		Object _values[] = (Object[]) _state;
		super.restoreState(_context, _values[0]);
        this.dataProvider = (IValuePickerData) FacesUtil.objectFromSerializable(_context, this, _values[1]);
        this._for = (String)_values[2];
	}

	@Override
	public Object saveState(FacesContext _context) {
		Object _values[] = new Object[3];
		_values[0] = super.saveState(_context);
        _values[1] = FacesUtil.objectToSerializable(_context, dataProvider);
        _values[2] = _for;
		return _values;
	}
	
	/*  public void restoreStateOrig(FacesContext _context, Object _state) {
	        Object _values[] = (Object[]) _state;
	        super.restoreState(_context, _values[0]);
	        this.dojoType = (java.lang.String) _values[1];
	        this.dojoAttributes = StateHolderUtil.restoreList(_context, this, _values[2]);
	        this._for = (String)_values[3];
	        this.dialogTitle = (String)_values[4];
	        this.pickerIcon = (String)_values[5];
	        this.pickerText = (String)_values[6];
	        this.listWidth = (String)_values[7];
	        this.listHeight = (String)_values[8];
	    }

	    public Object saveStateOrig(FacesContext _context) {
	        Object _values[] = new Object[9];
	        _values[0] = super.saveState(_context);
	        _values[1] = dojoType;
	        _values[2] = StateHolderUtil.saveList(_context, dojoAttributes);
	        _values[3] = _for;
	        _values[4] = dialogTitle;
	        _values[5] = pickerIcon;
	        _values[6] = pickerText;
	        _values[7] = listWidth;
	        _values[8] = listHeight;
	        return _values;
	    }
	*/
	
	
	@Override
    public IRestService getService() {
        return new Service();
    }
    protected static class Service implements IRestService {
        @Override
		public RestServiceEngine createEngine(FacesContext context, final UIBaseRestService parent, HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
            ValuePickerParameters p = new ValuePickerParameters() {
                @Override
				public String[] getAttributeNames() {
                    return null;
                }
                @Override
				public IPickerData getDataProvider() {
                	UISelect2Picker picker = (UISelect2Picker)parent;
                    return picker.getDataProvider();
                }
                @Override
				public int getSource() {
                    return 0;
                }
                @Override
				public int getStart() {
                    return 0;
                }
                @Override
				public int getCount() {
                    return 1000; // Maximum allowed by default?
                }
                @Override
				public String getKey() {
                    return null;
                }
                @Override
				public String getStartKey() {
                    return null;
                }
                @Override
				public String getContentType() {
                    return "application/json"; // $NON-NLS-1$
                }
                @Override
				public boolean isCompact() {
                    return !ExtLibUtil.isDevelopmentMode(); // false for debug
                }
                
            };
            return new RestValuePickerService(httpRequest,httpResponse,p);
        }
        @Override
		public boolean writePageMarkup(FacesContext context, UIBaseRestService parent, ResponseWriter writer) throws IOException {
            return false;
        }
    }
    
    // =====================================================================
    // Validation interface
    // =====================================================================
    
    public boolean isValidValue(Object value) {
        IPickerData dp = getDataProvider();
        if(dp!=null) {
            List<IPickerEntry> val = dp.loadEntries(new Object[]{value}, null);
            if(val!=null && val.get(0)!=null) {
                return true;
            }
        }
        return false;
    }
    
    
    // =====================================================================
    // Interface with typeahead
    // =====================================================================
    
    public Object getTypeAheadValue(UITypeAhead typeAhead) {
        boolean mk = typeAhead.isValueMarkup();
        return mk ? getTypeAheadMarkupValue(typeAhead) : getTypeAheadSimpleValue(typeAhead);
    }
    
    //@deprecated Use {@link #getTypeAheadSimpleValue(UITypeAhead)} instead


    public Object getTypeAheadSampleValue(UITypeAhead typeAhead) {
        throw new UnsupportedOperationException("Method renamed from getTypeAheadSampleValue to getTypeAheadSimpleValue"); // $NLX-AbstractPicker.MethodrenamedfromgetTypeAheadSamp-1$
    }

    public Object getTypeAheadSimpleValue(UITypeAhead typeAhead) {
        IPickerData dp = getDataProvider();
        if(dp!=null) {
            int max = typeAhead.getMaxValues();
            if(max<=0) {
                max = Integer.MAX_VALUE;
            }
            max = Math.min(max,50); // Put a limit for now...
            
            // How to get isMultipleTrim here?
            String value = getStartsValue(FacesContext.getCurrentInstance(), typeAhead, true);
            // Use the Key and not the startKey to filter the right set of values
            int source = 0; // Always the first source?
            SimplePickerOptions options = new SimplePickerOptions(source, 0, max, value, null, null);
            IPickerResult r = dp.readEntries(options);
            List<IPickerEntry> entries = r.getEntries();
            Object[] res = new Object[entries.size()];
            for(int i=0; i<res.length; i++) {
                res[i] = entries.get(i).getValue();
            }
            
            return res;
        }
        return null;
    }

    public Object getTypeAheadMarkupValue(UITypeAhead typeAhead) {
        IPickerData dp = getDataProvider();
        if(dp!=null) {
            int max = typeAhead.getMaxValues();
            if(max<=0) {
                max = Integer.MAX_VALUE;
            }
            max = Math.min(max,50); // Put a limit for now...

            // Read the entry
            String value = getStartsValue(FacesContext.getCurrentInstance(), typeAhead, true);
            // Use the Key and not the startKey to filter the right set of values
            int source = 0; // Always the first source?
            SimplePickerOptions options = new SimplePickerOptions(source,0, max, value, null, null);
            IPickerResult r = dp.readEntries(options);
            List<IPickerEntry> entries = r.getEntries();
            
            StringBuilder b = new StringBuilder();
            b.append("<ul>"); // $NON-NLS-1$
            int sz = entries.size();
            for(int i=0; i<sz; i++) {
                IPickerEntry e = entries.get(i);
                Object val = e.getValue();
                if(value!=null) {
                    b.append("<li>"); // $NON-NLS-1$
                    Object label = e.getLabel();
                    if(label!=null) {
                        // Note, use double-quotes instead of single-quotes for
                        // attributes, so as to be XHTML-compliant.
                        b.append("<span class=\"informal\">"); // $NON-NLS-1$
                        b.append(TextUtil.toXMLString(label.toString()));
                        b.append("</span>"); // $NON-NLS-1$
                    }
                    b.append(TextUtil.toXMLString(val.toString()));
                    b.append("</li>"); // $NON-NLS-1$
                }
            }
            b.append("</ul>"); // $NON-NLS-1$
            return b.toString();
        }
        return null;
    }
    // The typeahead control should expose some features here...
    private String getStartsValue(FacesContext context, UITypeAhead typeAhead, boolean isMultipleTrim) {
        Map<String, String> requestMap = TypedUtil.getRequestParameterMap(context.getExternalContext());
        String startsValue = requestMap.get(UITypeAhead.VALUE_NAME);
        if( isMultipleTrim && null != startsValue){
            startsValue = startsValue.trim();
        }
        return startsValue;
    } 

}

/*



public class UISelect2 extends UIBaseRestService implements FacesDojoComponent, ThemeControl {
	
    public static final String COMPONENT_TYPE = "org.openntf.xsp.bootstrap.picker.Select2Picker"; //$NON-NLS-1$
    public static final String RENDERER_TYPE = "org.openntf.xsp.bootstrap.component.select2"; //$NON-NLS-1$
    public static final String COMPONENT_FAMILY = "org.openntf.xsp.bootstrap.component.select2"; //$NON-NLS-1$

	
	
	@Override
	public String getStyleKitFamily() {
		return StyleKitExtLibDefault.PICKER_VALUE;
	}
	
	
	
	

	@Override
	public String getFamily() {
		return COMPONENT_FAMILY;
	}
	
	
	      private String pickerIcon;
	    private String pickerText;
	    private String dialogTitle;
	    private String listWidth;
	    private String listHeight;
	    
	    // Dynamic Dojo attributes
	    private String dojoType;
	    private List<DojoAttribute> dojoAttributes;


	    

	    public String getDialogTitle() {
	        if (null != this.dialogTitle) {
	            return this.dialogTitle;
	        }
	        ValueBinding _vb = getValueBinding("dialogTitle"); //$NON-NLS-1$
	        if (_vb != null) {
	            return (java.lang.String) _vb.getValue(FacesContext.getCurrentInstance());
	        } else {
	            return null;
	        }
	    }

	    public void setDialogTitle(String dialogTitle) {
	        this.dialogTitle = dialogTitle;
	    }

	    public String getPickerIcon() {
	        if (null != this.pickerIcon) {
	            return this.pickerIcon;
	        }
	        ValueBinding _vb = getValueBinding("pickerIcon"); //$NON-NLS-1$
	        if (_vb != null) {
	            return (java.lang.String) _vb.getValue(FacesContext.getCurrentInstance());
	        } else {
	            return null;
	        }
	    }

	    public void setPickerIcon(String pickerIcon) {
	        this.pickerIcon = pickerIcon;
	    }

	    public String getPickerText() {
	        if (null != this.pickerText) {
	            return this.pickerText;
	        }
	        ValueBinding _vb = getValueBinding("pickerText"); //$NON-NLS-1$
	        if (_vb != null) {
	            return (java.lang.String) _vb.getValue(FacesContext.getCurrentInstance());
	        } else {
	            return null;
	        }
	    }

	    public void setPickerText(String pickerText) {
	        this.pickerText = pickerText;
	    }

	    public String getListWidth() {
	        if (null != this.listWidth) {
	            return this.listWidth;
	        }
	        ValueBinding _vb = getValueBinding("listWidth"); //$NON-NLS-1$
	        if (_vb != null) {
	            return (String) _vb.getValue(FacesContext.getCurrentInstance());
	        } 
	        return null;
	    }

	    public void setListWidth(String listWidth) {
	        this.listWidth = listWidth;
	    }

	    public String getListHeight() {
	        if (null != this.listHeight) {
	            return this.listHeight;
	        }
	        ValueBinding _vb = getValueBinding("listHeight"); //$NON-NLS-1$
	        if (_vb != null) {
	            return (String) _vb.getValue(FacesContext.getCurrentInstance());
	        } 
	        return null;
	    }

	    public void setListHeight(String listHeight) {
	        this.listHeight = listHeight;
	    }

	    @Override
		public java.lang.String getDojoType() {
	        if (null != this.dojoType) {
	            return this.dojoType;
	        }
	        ValueBinding _vb = getValueBinding("dojoType"); //$NON-NLS-1$
	        if (_vb != null) {
	            return (java.lang.String) _vb.getValue(getFacesContext());
	        } else {
	            return null;
	        }
	    }

	    public void setDojoType(java.lang.String dojoType) {
	        this.dojoType = dojoType;
	    }

	    @Override
		public List<DojoAttribute> getDojoAttributes() {
	        return this.dojoAttributes;
	    }
	    
	    public void addDojoAttribute(DojoAttribute attribute) {
	        if(dojoAttributes==null) {
	            dojoAttributes = new ArrayList<DojoAttribute>();
	        }
	        dojoAttributes.add(attribute);
	    }

	    public void setDojoAttributes(List<DojoAttribute> dojoAttributes) {
	        this.dojoAttributes = dojoAttributes;
	    }

	  
	    
	    

	    
	   
}
*/