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

package org.openntf.xsp.bootstrap.library;

import com.ibm.xsp.library.AbstractXspLibrary;


/**
 * Bootstrap XPages Library
 */
public class BootstrapLibrary extends AbstractXspLibrary {

	public BootstrapLibrary() {
	}

	@Override
	public String getLibraryId() {
        return "org.openntf.xsp.bootstrap.library"; // $NON-NLS-1$
    }

    public boolean isDefault() {
		return true;
	}

    @Override
	public String getPluginId() {
        return "org.openntf.xsp.bootstrap"; // $NON-NLS-1$
    }
    
    @Override
	public String[] getDependencies() {
        return new String[] {
            "com.ibm.xsp.core.library",     // $NON-NLS-1$
            "com.ibm.xsp.extsn.library",    // $NON-NLS-1$
            "com.ibm.xsp.domino.library",   // $NON-NLS-1$
            "com.ibm.xsp.extlib.library"    // $NON-NLS-1$
        };
    }
    
    @Override
	public String[] getXspConfigFiles() {
        String[] files = new String[] {
                "org/openntf/xsp/bootstrap/config/bootstrap.xsp-config", // $NON-NLS-1$
                "org/openntf/xsp/bootstrap/config/bootstrap-extlib.xsp-config", // $NON-NLS-1$
            };
        return files;
    }
    
    @Override
	public String[] getFacesConfigFiles() {
        String[] files = new String[] {
                "org/openntf/xsp/bootstrap/config/bootstrap-faces-config.xml", // $NON-NLS-1$
            };
        return files;
    }
}
