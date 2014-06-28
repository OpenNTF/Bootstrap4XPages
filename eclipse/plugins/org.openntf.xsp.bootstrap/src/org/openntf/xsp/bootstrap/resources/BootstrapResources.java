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
        if(v==BootstrapUtil.BOOTSTRAP_V232) {
            r = Bootstrap232Resources.instance;
        } else if(v==BootstrapUtil.BOOTSTRAP_V300 || v==BootstrapUtil.BOOTSTRAP_V311 || v==BootstrapUtil.BOOTSTRAP_V320 ) {
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
    public static final DojoModuleResource bootstrapDialog = new DojoModuleResource("extlib.dijit.BootstrapDialog"); // $NON-NLS-1$
    public static final DojoModuleResource bootstrapDialog3 = new DojoModuleResource("extlib.dijit.BootstrapDialog3"); // $NON-NLS-1$

    public static final DojoModuleResource bootstrapTooltip = new DojoModuleResource("extlib.dijit.BootstrapTooltip");   // $NON-NLS-1$
    
    public static final DojoModuleResource bootstrapPickerCheckbox = new DojoModuleResource("extlib.dijit.BootstrapPickerCheckbox"); // $NON-NLS-1$
    public static final DojoModuleResource bootstrapPickerCheckbox3 = new DojoModuleResource("extlib.dijit.BootstrapPickerCheckbox3"); // $NON-NLS-1$
    public static final DojoModuleResource bootstrapPickerList = new DojoModuleResource("extlib.dijit.BootstrapPickerList"); // $NON-NLS-1$
    public static final DojoModuleResource bootstrapPickerList3 = new DojoModuleResource("extlib.dijit.BootstrapPickerList3"); // $NON-NLS-1$
    public static final DojoModuleResource bootstrapPickerListSearch = new DojoModuleResource("extlib.dijit.BootstrapPickerListSearch"); // $NON-NLS-1$
    public static final DojoModuleResource bootstrapPickerListSearch3 = new DojoModuleResource("extlib.dijit.BootstrapPickerListSearch3"); // $NON-NLS-1$
    public static final DojoModuleResource bootstrapPickerName = new DojoModuleResource("extlib.dijit.BootstrapPickerName"); // $NON-NLS-1$
    public static final DojoModuleResource bootstrapPickerName3 = new DojoModuleResource("extlib.dijit.BootstrapPickerName3"); // $NON-NLS-1$
    public static final DojoModuleResource bootstrapPickerSelect2 = new DojoModuleResource("extlib.dijit.BootstrapPickerSelect2"); // $NON-NLS-1$
    
    public BootstrapResources() {
        this.BLANK_GIF      				  = "/.ibmxspres/.extlib/bootstrap/icons/blank.gif"; // $NON-NLS-1$
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
