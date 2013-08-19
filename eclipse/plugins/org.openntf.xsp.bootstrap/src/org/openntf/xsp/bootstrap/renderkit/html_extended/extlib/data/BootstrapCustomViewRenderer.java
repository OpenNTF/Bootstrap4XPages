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

package org.openntf.xsp.bootstrap.renderkit.html_extended.extlib.data;

import org.openntf.xsp.bootstrap.resources.BootstrapResources;

import com.ibm.xsp.extlib.renderkit.html_extended.data.DataViewRenderer;


/**
 * One UI data view renderer.
 */
public class BootstrapCustomViewRenderer extends DataViewRenderer {

    @Override
    protected Object getProperty(int prop) {
        switch(prop) {
            // TODO the AbstractWebDataViewRenderer has hard-coded the width and height of this gif
            // as 16x16 - the width and height should be specified here along with the gif name.
            case PROP_BLANKIMG:                 return BootstrapResources.get().BLANK_GIF;
            // note, for an Alt, there's a difference between the empty string and null
            case PROP_BLANKIMGALT:              return ""; //$NON-NLS-1$
            case PROP_ALTTEXTCLASS:             return "lotusAltText"; // $NON-NLS-1$
            
            
            case PROP_HEADERCLASS:              return "clearfix"; // $NON-NLS-1$
            case PROP_HEADERLEFTSTYLE:          return null; 
            case PROP_HEADERLEFTCLASS:          return "pull-left"; // $NON-NLS-1$
            case PROP_HEADERRIGHTSTYLE:         return null; 
            case PROP_HEADERRIGHTCLASS:         return "pull-right"; // $NON-NLS-1$

            case PROP_FOOTERCLASS:              return "clearfix"; // $NON-NLS-1$
            case PROP_FOOTERLEFTSTYLE:          return null; 
            case PROP_FOOTERLEFTCLASS:          return "pull-left"; // $NON-NLS-1$
            case PROP_FOOTERRIGHTSTYLE:         return null; 
            case PROP_FOOTERRIGHTCLASS:         return "pull-right"; // $NON-NLS-1$

            case PROP_TABLECLASS:               return "clearfix table"; // $NON-NLS-1$
            case PROP_TABLEROWEXTRA:            return "lotusMeta lotusNowrap"; // $NON-NLS-1$
            
            //case PROP_COLLAPSEICON:             return "/.ibmxspres/.extlib/bootstrap/icons/view_expanded.png"; // $NON-NLS-1$
            case PROP_COLLAPSEICON:             return BootstrapResources.get().BLANK_GIF;
            case PROP_COLLAPSEICONSTYLE:        return "width:17.0px;height:17.0px;padding-right:3px"; // $NON-NLS-1$
            case PROP_COLLAPSEICONCLASS: 		return "icon-minus-sign icon-lighter";	
            //case PROP_EXPANDICON:               return "/.ibmxspres/.extlib/bootstrap/icons/view_collapsed.png"; // $NON-NLS-1$
            case PROP_EXPANDICON:               return BootstrapResources.get().BLANK_GIF;            
            case PROP_EXPANDICONSTYLE:          return "width:17.0px;height:17.0px;padding-right:3px"; // $NON-NLS-1$
            //case PROP_EXPANDICONCLASS: 			return "icon-lighter";	
            case PROP_EXPANDICONCLASS: 			return "icon-plus-sign icon-lighter";	
            
            case PROP_TABLEROWINDENTPX:         return 10;
            case PROP_SHOWICONDETAILSCLASS:     return "icon-chevron-down icon-lighter"; // $NON-NLS-1$
            case PROP_HIDEICONDETAILSCLASS:     return "icon-chevron-up icon-lighter"; // $NON-NLS-1$
            
            case PROP_TABLEHDRCOLIMAGE_SORTBOTH_ASCENDING:  return BootstrapResources.get().VIEW_COLUMN_SORT_BOTH_ASCENDING;
            case PROP_TABLEHDRCOLIMAGE_SORTBOTH_DESCENDING: return BootstrapResources.get().VIEW_COLUMN_SORT_BOTH_DESCENDING;
            case PROP_TABLEHDRCOLIMAGE_SORTBOTH:            return BootstrapResources.get().VIEW_COLUMN_SORT_NONE;
            case PROP_TABLEHDRCOLIMAGE_SORTED_ASCENDING:    return BootstrapResources.get().VIEW_COLUMN_SORT_NORMAL;
            case PROP_TABLEHDRCOLIMAGE_SORTED_DESCENDING:   return BootstrapResources.get().VIEW_COLUMN_SORT_REVERSE;
            // the bootstrap sort header icons are 16x13 px
            case PROP_TABLEHDRCOLIMAGE_SORT_WIDTH:           return "16"; //$NON-NLS-1$
            case PROP_TABLEHDRCOLIMAGE_SORT_HEIGHT:          return "13"; //$NON-NLS-1$
        }
        return super.getProperty(prop);
    }
}