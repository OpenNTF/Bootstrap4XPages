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

import com.ibm.xsp.extlib.renderkit.html_extended.data.FormTableRenderer;


/**
 * One UI form table renderer.
 */
public class BootstrapFormTableRenderer extends FormTableRenderer {

	@Override
	protected Object getProperty(int prop) {
      switch(prop) {
          case PROP_TABLESTYLECLASS:          return "form-table"; // $NON-NLS-1$
          
          case PROP_STYLECLASSERRORSUMMARY:   return "alert alert-danger"; // $NON-NLS-1$
          case PROP_ERRORSUMMARYMAINTEXT:     return "Please check the following:"; // $NLS-OneUIFormTableRenderer.Pleasecheckthefollowing-1$
          case PROP_ERRORSUMMARYCLASS:        return "text-error"; // $NON-NLS-1$
          case PROP_WARNSUMMARYMAINTEXT:      return getProperty(PROP_ERRORSUMMARYMAINTEXT);
          case PROP_WARNSUMMARYCLASS:         return "text-warning"; // $NON-NLS-1$
          case PROP_INFOSUMMARYMAINTEXT:      return getProperty(PROP_ERRORSUMMARYMAINTEXT);
          case PROP_INFOSUMMARYCLASS:         return "text-info"; // $NON-NLS-1$
          
          case PROP_TAGFORMTITLE:             return "h2"; // $NON-NLS-1$
          case PROP_STYLECLASSHEADER:         return "form-title"; // $NON-NLS-1$
          case PROP_STYLECLASSFORMDESC:       return "lotusMeta"; // $NON-NLS-1$
          case PROP_TAGFORMDESC:              return "div"; // $NON-NLS-1$
          
          case PROP_STYLECLASSFOOTER:         return "form-footer"; // $NON-NLS-1$

          case PROP_ERRORROWCLASS:            return "text-error"; // $NON-NLS-1$

          case PROP_ERRORMSGALTTEXT:          return "Error:";
          case PROP_ERRORMSGALTTEXTCLASS:     return "lotusAltText"; // $NON-NLS-1$
          case PROP_FATALMSGALTTEXT:          return "Fatal:"; // $NLS-OneUIFormTableRenderer.Fatal_messagePrefix-1$

          case PROP_WARNMSGALTTEXT:           return "Warning:"; // $NLS-OneUIFormTableRenderer.Warning.1-1$
          case PROP_WARNMSGALTTEXTCLASS:      return "lotusAltText"; // $NON-NLS-1$

          case PROP_INFOMSGALTTEXT:           return "Information:"; // $NLS-OneUIFormTableRenderer.Information.2-1$
          case PROP_INFOMSGALTTEXTCLASS:      return "lotusAltText"; // $NON-NLS-1$

          case PROP_FIELDROWCLASS:            return "control-group"; // $NON-NLS-1$
          case PROP_FIELDLABELWIDTH:          return "15%";
          case PROP_FIELDLABELCLASS:          return "control-label"; // $NON-NLS-1$
          case PROP_FIELDLABELREQUIREDTEXT:   return "*";

          case PROP_HELPIMGCLASS:             return null;
          case PROP_HELPIMGSRC:               return null;
          case PROP_HELPIMGALT:               return "Help"; // $NLS-OneUIFormTableRenderer.Help-1$
          case PROP_HELPMSGALTTEXT:           return "Help"; // $NLS-OneUIFormTableRenderer.Help.1-1$
          case PROP_HELPMSGALTTEXTCLASS:      return "lotusAltText"; // $NON-NLS-1$
      }
      
      return super.getProperty(prop);
  }
}