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

import javax.faces.el.ValueBinding;

import com.ibm.xsp.extlib.component.layout.impl.BasicApplicationConfigurationImpl;

/**
 * Bootstrap Application Configuration object.
 */
public class BootstrapApplicationConfiguration extends BasicApplicationConfigurationImpl {

	// Bootstrap specific properties
    private Boolean navbarInverted;
	
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
        return true;
    }
    
    public void setNavbarInverted(boolean navbarInverted) {
        this.navbarInverted = navbarInverted;
    }
}
