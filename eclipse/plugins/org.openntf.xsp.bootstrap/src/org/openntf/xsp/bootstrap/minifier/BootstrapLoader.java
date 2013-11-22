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

package org.openntf.xsp.bootstrap.minifier;

import java.net.URL;

import javax.servlet.http.HttpServletRequest;

import org.openntf.xsp.bootstrap.library.BootstrapActivator;
import org.osgi.framework.Bundle;

import com.ibm.commons.util.DoubleMap;
import com.ibm.xsp.extlib.minifier.ExtLibLoaderExtension;
import com.ibm.xsp.extlib.resources.ExtlibResourceProvider;
import com.ibm.xsp.extlib.util.ExtLibUtil;


/**
 * Resource Loader that loads the resources from the Bootstrap plug-in.
 */
public class BootstrapLoader extends ExtLibLoaderExtension {

	public BootstrapLoader() {
	}
    
    @Override
    public Bundle getOSGiBundle() {
        return BootstrapActivator.instance.getBundle();
    }
	
	
	// ========================================================
	//	Handling Dojo
	// ========================================================
    
    @Override
    public void loadDojoShortcuts(DoubleMap<String, String> aliases, DoubleMap<String, String> prefixes) {
        /// ALIASES
        if(aliases!=null) {
        	// CAREFULLY MAKE SURE THAT THERE IS NO CONFLICT WITH ANOTHER LIBRARY
        }
        
        /// PREFIXES
        if(prefixes!=null) {
        	// CAREFULLY MAKE SURE THAT THERE IS NO CONFLICT WITH ANOTHER LIBRARY
        }
    }
    
    // ========================================================
    //  Handling CSS
    // ========================================================
    
    @Override
    public void loadCSSShortcuts(DoubleMap<String, String> aliases, DoubleMap<String, String> prefixes) {
        /// ALIASES
        if(aliases!=null) {
        	// CAREFULLY MAKE SURE THAT THERE IS NO CONFLICT WITH ANOTHER LIBRARY
            //aliases.put("@Ea","/.ibmxspres/.extlib/css/tagcloud.css"); //$NON-NLS-1$ //$NON-NLS-2$
        }

        /// PREFIXES
        if(prefixes!=null) {
        	// CAREFULLY MAKE SURE THAT THERE IS NO CONFLICT WITH ANOTHER LIBRARY
            prefixes.put("3T0a","/.ibmxspres/.extlib/bootstrap/bootstrap231"); 		//$NON-NLS-1$ //$NON-NLS-2$
            prefixes.put("3T0b","/.ibmxspres/.extlib/bootstrap/bootstrap231/js"); 	//$NON-NLS-1$ //$NON-NLS-2$
            prefixes.put("3T0c","/.ibmxspres/.extlib/bootstrap/bootstrap231/css"); 	//$NON-NLS-1$ //$NON-NLS-2$
            prefixes.put("3T1a","/.ibmxspres/.extlib/bootstrap/bootstrap232"); 		//$NON-NLS-1$ //$NON-NLS-2$
            prefixes.put("3T1b","/.ibmxspres/.extlib/bootstrap/bootstrap232/js"); 	//$NON-NLS-1$ //$NON-NLS-2$
            prefixes.put("3T1c","/.ibmxspres/.extlib/bootstrap/bootstrap232/css"); 	//$NON-NLS-1$ //$NON-NLS-2$
            prefixes.put("3T2a","/.ibmxspres/.extlib/bootstrap/bootstrap300"); 		//$NON-NLS-1$ //$NON-NLS-2$
            prefixes.put("3T3b","/.ibmxspres/.extlib/bootstrap/bootstrap300/js"); 	//$NON-NLS-1$ //$NON-NLS-2$
            prefixes.put("3T3c","/.ibmxspres/.extlib/bootstrap/bootstrap300/css"); 	//$NON-NLS-1$ //$NON-NLS-2$
            prefixes.put("3TXa","/.ibmxspres/.extlib/bootstrap/dbootstrap"); 			//$NON-NLS-1$ //$NON-NLS-2$
        }
    }

    
    // ========================================================
    // Serving resources
    // ========================================================
    
    @Override
    public URL getResourceURL(HttpServletRequest request, String name) {
    	if(name.startsWith("bootstrap")) {
    		String path = ExtlibResourceProvider.BUNDLE_RES_PATH_EXTLIB+name;
    		return ExtLibUtil.getResourceURL(BootstrapActivator.instance.getBundle(), path);
    	}
    	if(name.startsWith("dijit")) {
    		String path = ExtlibResourceProvider.BUNDLE_RES_PATH_EXTLIB+name;
    		return ExtLibUtil.getResourceURL(BootstrapActivator.instance.getBundle(), path);
    	}
    	return null;
    }
}
