/*
 * � Copyright IBM Corp. 2010
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

package org.openntf.xsp.bootstrap.renderkit.html_extended.extlib.containers;

import com.ibm.xsp.extlib.renderkit.html_extended.containers.ListRenderer;



/**
 * OneUI Inline List renderer.
 */
public class BootstrapInlineListRenderer extends ListRenderer {
    
    public BootstrapInlineListRenderer() {
    }

    @Override
    protected Object getProperty(int prop) {
        switch(prop) {
            case PROP_LISTSTYLECLASS:           return "lotusInlinelist"; // $NON-NLS-1$
            case PROP_FIRSTITEMSTYLECLASS:      return "lotusFirst"; // $NON-NLS-1$
            //case PROP_LASTITEMSTYLE:          return "padding-right: 0";
        }
        return super.getProperty(prop);
    }
}