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
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;

public class UISelect2PickerCombo extends UIComponentBase {

	private String _for;
	private String placeHolder;
	private boolean allowClearing;
	private String listWidth;
	private String formatSelection;
	private String formatResult;

	public UISelect2PickerCombo() {
		super();
		setRendererType("org.openntf.xsp.bootstrap.Select2PickerComboRendererType");
	}

	@Override
	public String getFamily() {
		return "org.openntf.xsp.bootstrap.Select2ComboPicker";
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
		this._for = (String) _values[1];
		this.placeHolder = (String) _values[2];
		this.allowClearing = (Boolean) _values[3];
		this.listWidth = (String) _values[4];
		this.formatSelection = (String) _values[5];
		this.formatResult = (String) _values[6];
	}

	@Override
	public Object saveState(FacesContext _context) {
		Object _values[] = new Object[7];
		_values[0] = super.saveState(_context);
		_values[1] = _for;
		_values[2] = placeHolder;
		_values[3] = allowClearing;
		_values[4] = listWidth;
		_values[5] = formatSelection;
		_values[6] = formatResult;
		return _values;
	}

}