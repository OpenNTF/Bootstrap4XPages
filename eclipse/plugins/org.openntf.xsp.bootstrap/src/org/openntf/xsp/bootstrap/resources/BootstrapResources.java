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

package org.openntf.xsp.bootstrap.resources;

import org.openntf.xsp.bootstrap.util.BootstrapUtil;

import com.ibm.designer.runtime.Version;
import com.ibm.xsp.context.FacesContextEx;
import com.ibm.xsp.resource.DojoModuleResource;

/**
 * Shared Bootstrap resources.
 * 
 * @author priand
 *
 */
public abstract class BootstrapResources {

    public String BLANK_GIF;
	public String VIEW_COLUMN_SORT_NONE;
    public String VIEW_COLUMN_SORT_BOTH_ASCENDING;
    public String VIEW_COLUMN_SORT_BOTH_DESCENDING;
    public String VIEW_COLUMN_SORT_NORMAL;  
    public String VIEW_COLUMN_SORT_REVERSE;

    public static BootstrapResources get() {
        return get(FacesContextEx.getCurrentInstance());
    }
    
    public static BootstrapResources get(FacesContextEx context) {
        BootstrapResources r = (BootstrapResources)context.getAttributes().get("openntf.bootstrap.Resources"); // $NON-NLS-1$
        if(r!=null) {
            return r;
        }
        Version v = BootstrapUtil.getBootstrapVersion(context);
        if(v==BootstrapUtil.BOOTSTRAP_V231) {
            r = Bootstrap231Resources.instance;
        } else if(v==BootstrapUtil.BOOTSTRAP_V232) {
            r = Bootstrap232Resources.instance;
        } else if(v==BootstrapUtil.BOOTSTRAP_V300) {
            r = Bootstrap300Resources.instance;
        } else {
            // Default to v232 anyway
            r = Bootstrap232Resources.instance;
        }
        context.getAttributes().put("openntf.bootstrap.Resources",r); // $NON-NLS-1$
        return r;
    }
    
    // Dojo module
    public static final DojoModuleResource bootstrapNavigator = new DojoModuleResource("extlib.dijit.BootstrapNavigator"); // $NON-NLS-1$
    
    
    public BootstrapResources() {
    	// We know this one is available
        this.BLANK_GIF      = "/.ibmxspres/domino/oneuiv2/images/blank.gif"; // $NON-NLS-1$
    	
        // This is not specific to a particular version of OneUI
        this.VIEW_COLUMN_SORT_NONE            = "/.ibmxspres/.extlib/bootstrap/icons/sort_none.gif"; // $NON-NLS-1$ 
        this.VIEW_COLUMN_SORT_BOTH_ASCENDING  = "/.ibmxspres/.extlib/bootstrap/icons/sort_both_ascending.gif"; // $NON-NLS-1$ 
        this.VIEW_COLUMN_SORT_BOTH_DESCENDING = "/.ibmxspres/.extlib/bootstrap/icons/sort_both_descending.gif"; // $NON-NLS-1$ 
        this.VIEW_COLUMN_SORT_NORMAL          = "/.ibmxspres/.extlib/bootstrap/icons/sort_normal.gif"; // $NON-NLS-1$   
        this.VIEW_COLUMN_SORT_REVERSE         = "/.ibmxspres/.extlib/bootstrap/icons/sort_reverse.gif"; // $NON-NLS-1$
    }
    
    public boolean isBootstrap2() {
    	return false;
    }

    public boolean isBootstrap3() {
    	return false;
    }
    
    public String getIconClass(String iconName) {
    	return "icon-"+iconName;
    }
}
