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
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibm.commons.util.TextUtil;
import com.ibm.domino.services.rest.RestServiceEngine;
import com.ibm.xsp.component.UITypeAhead;
import com.ibm.xsp.extlib.component.picker.data.IPickerData;
import com.ibm.xsp.extlib.component.picker.data.IPickerEntry;
import com.ibm.xsp.extlib.component.picker.data.IPickerResult;
import com.ibm.xsp.extlib.component.picker.data.IValuePickerData;
import com.ibm.xsp.extlib.component.picker.data.SimplePickerOptions;
import com.ibm.xsp.extlib.component.rest.IRestService;
import com.ibm.xsp.extlib.component.rest.UIBaseRestService;
import com.ibm.xsp.extlib.services.rest.picker.RestValuePickerService;
import com.ibm.xsp.extlib.services.rest.picker.ValuePickerParameters;
import com.ibm.xsp.extlib.util.ExtLibUtil;
import com.ibm.xsp.util.FacesUtil;
import com.ibm.xsp.util.TypedUtil;

public class UISelect2Picker extends UIBaseRestService {

	private static final int MAX_ROWS_DEFAULT = 50;
	
	private String _for;
	private IValuePickerData dataProvider;
	private boolean useRemoteData;
	private String placeHolder;
	private boolean allowClearing;
	private String listWidth;
	private int maxRowCount;

	private String formatSelection;
	private String formatResult;

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
			return (java.lang.String) _vb.getValue(FacesContext
					.getCurrentInstance());
		} else {
			return null;
		}
	}

	public void setFor(String _for) {
		this._for = _for;
	}

	public String getFormatResult() {
		return formatResult;
	}
	
	public void setFormatResult(String formatResult) {
		this.formatResult = formatResult;
	}
	
		public String getFormatSelection() {
		return formatSelection;
	}

	public void setFormatSelection(String formatSelection) {
		this.formatSelection = formatSelection;
	}
	
	public int getMaxRowCount() {
		return (maxRowCount == 0 ? MAX_ROWS_DEFAULT : maxRowCount);
	}
	public void setMaxRowCount(int maxRowCount) {
		this.maxRowCount = maxRowCount;
	}

	public IValuePickerData getDataProvider() {
		return this.dataProvider;
	}

	public void setDataProvider(IValuePickerData dataProvider) {
		this.dataProvider = dataProvider;
	}

	public boolean isUseRemoteData() {
		return useRemoteData;
	}

	public void setUseRemoteData(boolean useSearch) {
		this.useRemoteData = useSearch;
	}

	public String getPlaceHolder() {
		return placeHolder;
	}

	public void setPlaceHolder(String placeHolder) {
		this.placeHolder = placeHolder;
	}

	public boolean isAllowClearing() {
		return allowClearing;
	}

	public void setAllowClearing(boolean allowClearing) {
		this.allowClearing = allowClearing;
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

	@Override
	public void restoreState(FacesContext _context, Object _state) {
		Object _values[] = (Object[]) _state;
		super.restoreState(_context, _values[0]);
		this.dataProvider = (IValuePickerData) FacesUtil
				.objectFromSerializable(_context, this, _values[1]);
		this._for = (String) _values[2];
		this.useRemoteData = (Boolean) _values[3];
		this.placeHolder = (String) _values[4];
		this.allowClearing = (Boolean) _values[5];
		this.listWidth = (String) _values[6];
		this.maxRowCount = (Integer) _values[7];
		this.formatSelection = (String) _values[8];
		this.formatResult = (String) _values[9];
	}

	@Override
	public Object saveState(FacesContext _context) {
		Object _values[] = new Object[10];
		_values[0] = super.saveState(_context);
		_values[1] = FacesUtil.objectToSerializable(_context, dataProvider);
		_values[2] = _for;
		_values[3] = useRemoteData;
		_values[4] = placeHolder;
		_values[5] = allowClearing;
		_values[6] = listWidth;
		_values[7] = maxRowCount;
		_values[8] = formatSelection;
		_values[9] = formatResult;
		return _values;
	}

	@Override
	public IRestService getService() {
		return new Service();
	}

	protected static class Service implements IRestService {
		@Override
		public RestServiceEngine createEngine(FacesContext context,
				final UIBaseRestService parent, HttpServletRequest httpRequest,
				HttpServletResponse httpResponse) {
			ValuePickerParameters p = new ValuePickerParameters() {
				@Override
				public String[] getAttributeNames() {
					return null;
				}

				@Override
				public IPickerData getDataProvider() {
					UISelect2Picker picker = (UISelect2Picker) parent;
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
			return new RestValuePickerService(httpRequest, httpResponse, p);
		}

		@Override
		public boolean writePageMarkup(FacesContext context,
				UIBaseRestService parent, ResponseWriter writer)
				throws IOException {
			return false;
		}
	}

	// =====================================================================
	// Validation interface
	// =====================================================================

	public boolean isValidValue(Object value) {
		IPickerData dp = getDataProvider();
		if (dp != null) {
			List<IPickerEntry> val = dp.loadEntries(new Object[] { value },
					null);
			if (val != null && val.get(0) != null) {
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
		return mk ? getTypeAheadMarkupValue(typeAhead)
				: getTypeAheadSimpleValue(typeAhead);
	}

	// @deprecated Use {@link #getTypeAheadSimpleValue(UITypeAhead)} instead

	public Object getTypeAheadSampleValue(UITypeAhead typeAhead) {
		throw new UnsupportedOperationException(
				"Method renamed from getTypeAheadSampleValue to getTypeAheadSimpleValue"); // $NLX-AbstractPicker.MethodrenamedfromgetTypeAheadSamp-1$
	}

	public Object getTypeAheadSimpleValue(UITypeAhead typeAhead) {
		IPickerData dp = getDataProvider();
		if (dp != null) {
			int max = typeAhead.getMaxValues();
			if (max <= 0) {
				max = Integer.MAX_VALUE;
			}
			max = Math.min(max, 50); // Put a limit for now...

			// How to get isMultipleTrim here?
			String value = getStartsValue(FacesContext.getCurrentInstance(),
					typeAhead, true);
			// Use the Key and not the startKey to filter the right set of
			// values
			int source = 0; // Always the first source?
			SimplePickerOptions options = new SimplePickerOptions(source, 0,
					max, value, null, null);
			IPickerResult r = dp.readEntries(options);
			List<IPickerEntry> entries = r.getEntries();
			Object[] res = new Object[entries.size()];
			for (int i = 0; i < res.length; i++) {
				res[i] = entries.get(i).getValue();
			}

			return res;
		}
		return null;
	}

	public Object getTypeAheadMarkupValue(UITypeAhead typeAhead) {
		IPickerData dp = getDataProvider();
		if (dp != null) {
			int max = typeAhead.getMaxValues();
			if (max <= 0) {
				max = Integer.MAX_VALUE;
			}
			max = Math.min(max, 50); // Put a limit for now...

			// Read the entry
			String value = getStartsValue(FacesContext.getCurrentInstance(),
					typeAhead, true);
			// Use the Key and not the startKey to filter the right set of
			// values
			int source = 0; // Always the first source?
			SimplePickerOptions options = new SimplePickerOptions(source, 0,
					max, value, null, null);
			IPickerResult r = dp.readEntries(options);
			List<IPickerEntry> entries = r.getEntries();

			StringBuilder b = new StringBuilder();
			b.append("<ul>"); // $NON-NLS-1$
			int sz = entries.size();
			for (int i = 0; i < sz; i++) {
				IPickerEntry e = entries.get(i);
				Object val = e.getValue();
				if (value != null) {
					b.append("<li>"); // $NON-NLS-1$
					Object label = e.getLabel();
					if (label != null) {
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
	private String getStartsValue(FacesContext context, UITypeAhead typeAhead,
			boolean isMultipleTrim) {
		Map<String, String> requestMap = TypedUtil
				.getRequestParameterMap(context.getExternalContext());
		String startsValue = requestMap.get(UITypeAhead.VALUE_NAME);
		if (isMultipleTrim && null != startsValue) {
			startsValue = startsValue.trim();
		}
		return startsValue;
	}

}
