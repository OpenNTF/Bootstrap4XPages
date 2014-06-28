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

package org.openntf.xsp.bootstrap.util;

import javax.faces.context.FacesContext;

import com.ibm.designer.runtime.Version;
import com.ibm.xsp.context.FacesContextEx;
import com.ibm.xsp.stylekit.StyleKitImpl;



/**
 * Some utility used to deal with Bootstrap themes.
 */
public class BootstrapUtil {
    
	private static int uniqueId;
	public static String generateTemporaryUniqueId() {
		return "bstemp_"+Integer.toString(uniqueId++,36);
	}
    
    public static final Version BOOTSTRAP_NONE  = new Version(0, 0);	// Not Bootstrap
    public static final Version BOOTSTRAP_V232  = new Version(2, 3, 2); // Bootstrap v232
    public static final Version BOOTSTRAP_V300  = new Version(3, 0, 0); // Bootstrap v300
    public static final Version BOOTSTRAP_V311  = new Version(3, 1, 1); // Bootstrap v311
    public static final Version BOOTSTRAP_V320  = new Version(3, 2, 0); // Bootstrap v320
    
    @SuppressWarnings("deprecation") //$NON-NLS-1$
    public static Version getBootstrapVersion(FacesContext context) {
        return getBootstrapVersion((FacesContextEx)context);
    }
    
    @SuppressWarnings("deprecation") //$NON-NLS-1$
    public static Version getBootstrapVersion(FacesContextEx context) {
        Version v = (Version)context.getAttributes().get("openntf.bootstrap.Version"); // $NON-NLS-1$
        if(v!=null) {
            return v;
        }
        v = findBootstrapVersion(context);
        context.getAttributes().put("openntf.bootstrap.Version", v); // $NON-NLS-1$
        return v;
    }
    @SuppressWarnings("deprecation") //$NON-NLS-1$
    private static Version findBootstrapVersion(FacesContextEx ctxEx) {
        for(StyleKitImpl st = (StyleKitImpl)ctxEx.getStyleKit(); st!=null; st=st.getParent()) {
            if(st.getName().startsWith("bootstrap")) { // $NON-NLS-1$
                String libname = st.getName();
 
                if(libname.startsWith("bootstrapv2") ) { // $NON-NLS-1$ $NON-NLS-2$
                    return BOOTSTRAP_V232;
                }
                
                if(libname.startsWith("bootstrapv3.0.0")) { // $NON-NLS-1$ $NON-NLS-2$
                    return BOOTSTRAP_V300;
                }
                if(libname.startsWith("bootstrapv3.1.1") ) { // $NON-NLS-1$ $NON-NLS-2$
                    return BOOTSTRAP_V311;
                }
                if(libname.startsWith("bootstrapv3.2.0") || libname.startsWith("bootstrapv3") ) { // $NON-NLS-1$ $NON-NLS-2$
                    return BOOTSTRAP_V320;
                }
            }
        }
        return BOOTSTRAP_NONE;
    }

    public static boolean isBootstrapTheme(FacesContextEx context) {
        return getBootstrapVersion(context)!=BOOTSTRAP_NONE;
    }
    
    public static boolean isBootstrapVersion(FacesContext context, int major, int minor, int micro) {
        Version v = getBootstrapVersion(context);
        if(v.getMajor()==major && v.getMinor()==minor && v.getMicro()==micro) {
            return true;
        }
        return false;
    }
    public static boolean isBootstrapVersion(FacesContext context, int major, int minor) {
        Version v = getBootstrapVersion(context);
        if(v.getMajor()==major && v.getMinor()==minor) {
            return true;
        }
        return false;
    }
    
    public static boolean isBootstrapVersion(int major, int minor, int micro) {
        return isBootstrapVersion(FacesContext.getCurrentInstance(), major, minor, micro);
    }
    public static boolean isBootstrapVersion(int major, int minor) {
        return isBootstrapVersion(FacesContext.getCurrentInstance(), major, minor);
    }
    
    public static boolean isBootstrapVersionAtLeast(FacesContext context, int major, int minor) {
        Version v = getBootstrapVersion(context);
        if(v.getMajor()>major) {
            return true;
        }
        if(v.getMajor()==major) {
            if(v.getMinor()>=minor) {
                return true;
            }
        }
        return false;
    }
    public static boolean isBootstrapVersionAtLeast(int major, int minor) {
        return isBootstrapVersionAtLeast(FacesContext.getCurrentInstance(), major, minor);
    }
    
    public static boolean isResponsive(FacesContextEx ctxEx) {
    		
    	 for(StyleKitImpl st = (StyleKitImpl)ctxEx.getStyleKit(); st!=null; st=st.getParent()) {
             if(st.getName().startsWith("bootstrap")) { // $NON-NLS-1$
                 String libname = st.getName();
                 if(libname.startsWith("bootstrapv3") ) { // $NON-NLS-1$ $NON-NLS-2$
                     return true;
                 } else {
                	 return libname.endsWith("r");
                	 
                 }
             }
         }
         return false;
    	
    }
}