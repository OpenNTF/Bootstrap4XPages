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

package org.openntf.xsp.bootstrap.renderkit.html_extended.extlib.util;

import javax.faces.component.UIComponent;

import com.ibm.xsp.extlib.util.ExtLibUtil;


public class BootstrapNavButtonRenderer extends BootstrapNavRenderer {

    private static final long serialVersionUID = 1L;

    public BootstrapNavButtonRenderer() {
    }

    public BootstrapNavButtonRenderer(UIComponent component) {
    	super(component);
    }
    
    @Override
	protected boolean makeSelectedActive(TreeContextImpl node) {
    	return false;
    }

//    @Override
//    protected String getContainerTag() {
//        return "div"; // $NON-NLS-1$
//    }
//    
//    @Override
//    protected String getItemTag() {
//        return "button"; // $NON-NLS-1$
//    }

	@Override
	protected String getContainerStyleClass(TreeContextImpl node) {
    	if(node.getDepth()==1 && buttonGroup()) {
    		return "btn-group"; // $NON-NLS-1$
    	}
    	return super.getContainerStyleClass(node);
	}
	protected boolean buttonGroup() {
		return true;
	}

	@Override
	protected String getItemStyleClass(TreeContextImpl tree, boolean enabled, boolean selected) {
    	if(tree.getDepth()==2) {
			String s = super.getItemStyleClass(tree, enabled, selected);
			return ExtLibUtil.concatStyleClasses("btn", s);
			//return ExtLibUtil.concatStyleClasses("btn btn-default", s); // bootstrap 3
    	}
		return super.getItemStyleClass(tree, enabled, selected);
	}

}