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

import com.ibm.xsp.extlib.renderkit.html_extended.data.ForumViewRenderer;


/**
 * One UI forum view renderer.
 */
public class BootstrapForumViewRenderer extends ForumViewRenderer {

    @Override
    protected Object getProperty(int prop) {
        switch(prop) {
        	case PROP_BLANKIMG:                 return BootstrapResources.get().BLANK_GIF;
        	
            // note, for an Alt, there's a difference between the empty string and null
            case PROP_BLANKIMGALT:              return ""; //$NON-NLS-1$
            case PROP_ALTTEXTCLASS:             return "lotusAltText";   // $NON-NLS-1$
            
            
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

            case PROP_SHOWICONDETAILSCLASS:     return BootstrapResources.get().getIconClass("chevron-down")+" icon-lighter"; // $NON-NLS-1$
            case PROP_HIDEICONDETAILSCLASS:     return BootstrapResources.get().getIconClass("chevron-up")+" icon-lighter"; // $NON-NLS-1$
            
            
            //case PROP_MAINDIVCLASS:             return "media-list"; // $NON-NLS-1$
            case PROP_MAINLISTCLASS:            return "media-list"; // $NON-NLS-1$
            case PROP_CHILDLISTCLASS:           return "media"; // $NON-NLS-1$
            case PROP_LISTITEMCLASS:            return "media-body"; // $NON-NLS-1$
            
            //case PROP_COLLAPSIBLECONTENTSTYLE:    return "margin: 7px;";
            case PROP_COLLAPSIBLEDIVSTYLE:      return "float: right;"; // $NON-NLS-1$
            
            case PROP_TABLEHDRCOLIMAGE_SORTBOTH_ASCENDING:  return BootstrapResources.get().VIEW_COLUMN_SORT_BOTH_ASCENDING;
            case PROP_TABLEHDRCOLIMAGE_SORTBOTH_DESCENDING: return BootstrapResources.get().VIEW_COLUMN_SORT_BOTH_DESCENDING;
            case PROP_TABLEHDRCOLIMAGE_SORTBOTH:            return BootstrapResources.get().VIEW_COLUMN_SORT_NONE;
            case PROP_TABLEHDRCOLIMAGE_SORTED_ASCENDING:    return BootstrapResources.get().VIEW_COLUMN_SORT_NORMAL;
            case PROP_TABLEHDRCOLIMAGE_SORTED_DESCENDING:   return BootstrapResources.get().VIEW_COLUMN_SORT_REVERSE;
        }
        return super.getProperty(prop);
    }
}