/*
 * © Copyright IBM Corp. 2010, 2012
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

package org.openntf.xsp.bootstrap.themes;

import java.io.InputStream;

import com.ibm.xsp.stylekit.StyleKitFactory;


/**
 * Bootstrap theme Factory.
 * 
 * @author Philippe Riand
 */
public class BootstrapStyleKitFactory implements StyleKitFactory {

	public BootstrapStyleKitFactory() {
	}
	
	@Override
	public InputStream getThemeAsStream(String themeId, int scope) {
		if(scope==StyleKitFactory.STYLEKIT_GLOBAL) {
            String folderPath = "org/openntf/xsp/bootstrap/themes"; //$NON-NLS-1$
            
			if(themeId.equals("bootstrap")){ //$NON-NLS-1$
                return getThemeFromBundle(folderPath+"/bootstrap.theme"); //$NON-NLS-1$
			}
			
			if(themeId.equals("bootstrapv2_base")){ //$NON-NLS-1$
                return getThemeFromBundle(folderPath+"/bootstrapv2_base.theme"); //$NON-NLS-1$
			}
			
			//redirect 2.3.1 theme to 2.3.2
			if(themeId.equals("bootstrapv2.3.2") || themeId.equals("bootstrapv2.3.1") ){ //$NON-NLS-1$
                return getThemeFromBundle(folderPath+"/bootstrapv232.theme"); //$NON-NLS-1$
			}
			if(themeId.equals("bootstrapv2.3.2r") || themeId.equals("bootstrapv2.3.1r")){ //$NON-NLS-1$
                return getThemeFromBundle(folderPath+"/bootstrapv232r.theme"); //$NON-NLS-1$
			}

			if(themeId.equals("bootstrapv3_base")){ //$NON-NLS-1$
                return getThemeFromBundle(folderPath+"/bootstrapv3_base.theme"); //$NON-NLS-1$
			}
			
			if(themeId.equals("bootstrapv3.0.0")){ //$NON-NLS-1$
                return getThemeFromBundle(folderPath+"/bootstrapv300.theme"); //$NON-NLS-1$
			}
			if(themeId.equals("bootstrapv3.0.0_3d")){ //$NON-NLS-1$
                return getThemeFromBundle(folderPath+"/bootstrapv300_3d.theme"); //$NON-NLS-1$
			}
			
			if(themeId.equals("bootstrapv3.1.1")){ //$NON-NLS-1$
                return getThemeFromBundle(folderPath+"/bootstrapv311.theme"); //$NON-NLS-1$
			}
			if(themeId.equals("bootstrapv3.1.1_3d")){ //$NON-NLS-1$
                return getThemeFromBundle(folderPath+"/bootstrapv311_3d.theme"); //$NON-NLS-1$
			}
			
			if(themeId.equals("bootstrapv3.2.0")){ //$NON-NLS-1$
                return getThemeFromBundle(folderPath+"/bootstrapv320.theme"); //$NON-NLS-1$
			}
			if(themeId.equals("bootstrapv3.2.0_3d")){ //$NON-NLS-1$
                return getThemeFromBundle(folderPath+"/bootstrapv320_3d.theme"); //$NON-NLS-1$
			}
		}
		return null;
	}

	@Override
	public InputStream getThemeFragmentAsStream(String themeId, int scope) {
		if(scope==StyleKitFactory.STYLEKIT_GLOBAL) {
            String folderPath = "org/openntf/xsp/bootstrap/themes"; //$NON-NLS-1$
			if(themeId.equals("bootstrap")){ //$NON-NLS-1$
                return getThemeFromBundle(folderPath+"/bootstrap_extlib.theme"); //$NON-NLS-1$
			}
			
			if(themeId.startsWith("bootstrapv2")){ //$NON-NLS-1$
				return getThemeFromBundle(folderPath+"/bootstrapv232_extlib.theme"); //$NON-NLS-1$
			}
			
			if(themeId.startsWith("bootstrapv3")){ //$NON-NLS-1$
                return getThemeFromBundle(folderPath+"/bootstrapv3_extlib.theme"); //$NON-NLS-1$
			}

		}
		return null;
	}

	private InputStream getThemeFromBundle(final String fileName) {
		ClassLoader cl = getClass().getClassLoader();
		return cl.getResourceAsStream(fileName);
   }
}