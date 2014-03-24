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

package org.openntf.xsp.bootstrap.components.layout;

import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;

import com.ibm.xsp.extlib.component.layout.impl.BasicApplicationConfigurationImpl;

/**
 * Bootstrap Application Configuration object.
 */
public class BootstrapApplicationConfiguration extends BasicApplicationConfigurationImpl {
	
	public static final String WIDTH_FULL = "full";	
	public static final String WIDTH_FLUID = "fluid";
	public static final String WIDTH_FIXED = "fixed";
	
	private static final String COLLAPSE_LEFT_COLUMN_TARGET = ".applayout-column-left";
	
	// Bootstrap specific properties
    private Boolean navbarInverted;
    private Boolean navbarFixed;
    private Boolean collapseLeftColumn;
    private String collapseLeftColumnTarget;
    private String pageWidth;
	
	public BootstrapApplicationConfiguration() {
		
	}
	
    public boolean isNavbarInverted() {
        if(navbarInverted!=null) {
            return navbarInverted;
        }
        ValueBinding vb = getValueBinding("navbarInverted"); // $NON-NLS-1$
        if(vb!=null) {
            Boolean b = (Boolean)vb.getValue(getFacesContext());
            if(b!=null) {
                return b;
            }
        }
        return false;
    }
    public void setNavbarInverted(boolean navbarInverted) {
        this.navbarInverted = navbarInverted;
    }
    
    public boolean isNavbarFixed() {
        if(navbarFixed!=null) {
            return navbarFixed;
        }
        ValueBinding vb = getValueBinding("navbarFixed"); // $NON-NLS-1$
        if(vb!=null) {
            Boolean b = (Boolean)vb.getValue(getFacesContext());
            if(b!=null) {
                return b;
            }
        }
        return false;
    }
    public void setNavbarFixed(boolean navbarFixed) {
        this.navbarFixed = navbarFixed;
    }
    
    
    public boolean isCollapseLeftColumn() {
    	 if(collapseLeftColumn!=null) {
             return collapseLeftColumn;
         }
         ValueBinding vb = getValueBinding("collapseLeftColumn"); // $NON-NLS-1$
         if(vb!=null) {
             Boolean b = (Boolean)vb.getValue(getFacesContext());
             if(b!=null) {
                 return b;
             }
         }
         return false;
    }
    public void setCollapseLeftColumn(boolean collapseLeftColumn) {
		this.collapseLeftColumn = collapseLeftColumn;
	}
    
    public String getCollapseLeftColumnTarget() {
    	return (collapseLeftColumnTarget != null ? collapseLeftColumnTarget : COLLAPSE_LEFT_COLUMN_TARGET);
	}
    public void setCollapseLeftColumnTarget(String collapseLeftColumnTarget) {
		this.collapseLeftColumnTarget = collapseLeftColumnTarget;
	}
    
	public String getPageWidth() {
		return (pageWidth != null ? pageWidth : WIDTH_FULL);
    }
    public void setPageWidth(String pageWidth) {
    	this.pageWidth = pageWidth;
    }
    
    @Override
    public void restoreState(FacesContext context, Object state) {
        Object values[] = (Object[]) state;
        super.restoreState(context, values[0]);
        this.navbarInverted = (Boolean)values[1];
        this.collapseLeftColumn = (Boolean)values[2];
        this.pageWidth = (String)values[3];
        this.navbarFixed = (Boolean)values[4];
        this.collapseLeftColumnTarget = (String)values[5];
    }

    @Override
    public Object saveState(FacesContext context) {
        Object values[] = new Object[6];
        values[0] = super.saveState(context);
        values[1] = navbarInverted;
        values[2] = collapseLeftColumn;
        values[3] = pageWidth;
        values[4] = navbarFixed;
        values[5] = collapseLeftColumnTarget;
        return values;
    }
}
