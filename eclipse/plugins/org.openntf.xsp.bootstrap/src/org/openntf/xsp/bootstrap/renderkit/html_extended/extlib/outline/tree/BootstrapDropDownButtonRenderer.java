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

package org.openntf.xsp.bootstrap.renderkit.html_extended.extlib.outline.tree;

import java.io.IOException;

import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import org.openntf.xsp.bootstrap.renderkit.html_extended.extlib.util.BootstrapNavButtonRenderer;

import com.ibm.commons.util.StringUtil;
import com.ibm.xsp.extlib.tree.ITreeNode;
import com.ibm.xsp.renderkit.html_basic.HtmlRendererUtil;
import com.ibm.xsp.util.JSUtil;


public class BootstrapDropDownButtonRenderer extends BootstrapNavButtonRenderer {
    
    private static final long serialVersionUID = 1L;

    public BootstrapDropDownButtonRenderer() {
    }
 
	@Override
	protected boolean buttonGroup() {
		return false;
	}
	

    @Override
    protected void renderEntryItemContent(FacesContext context, ResponseWriter writer, TreeContextImpl tree, boolean enabled, boolean selected) throws IOException {
        boolean leaf = tree.getNode().getType() == ITreeNode.NODE_LEAF;
        if (leaf || tree.getDepth()>2) {
            super.renderEntryItemContent(context, writer, tree, enabled, selected);
        }
        else {
            renderPopupButton(context, writer, tree, enabled, selected);
        }
    }

    @Override
    protected String getItemTag() {
        return "li"; // $NON-NLS-1$
    }
	@Override
	protected String getItemStyleClass(TreeContextImpl tree, boolean enabled, boolean selected) {
    	if(tree.getDepth()==2) {
			return "btn-group";
    	}
		return super.getItemStyleClass(tree, enabled, selected);
	}

    protected void renderPopupButton(FacesContext context, ResponseWriter writer, TreeContextImpl tree, boolean enabled, boolean selected) throws IOException {
        writer.startElement("button",null); //$NON-NLS-1$
        
        // A popup button requires an id
        String clientId = tree.getClientId(context,"ab",1); // $NON-NLS-1$
        if(StringUtil.isNotEmpty(clientId)) {
            writer.writeAttribute("id", clientId, null); // $NON-NLS-1$
        }
        writer.writeAttribute("class","btn dropdown-toggle",null); // $NON-NLS-1$ $NON-NLS-2$
        writer.writeAttribute("data-toggle","dropdown",null); // $NON-NLS-1$ $NON-NLS-2$

        String image = tree.getNode().getImage();
        boolean hasImage = StringUtil.isNotEmpty(image);
        if(hasImage) {
            writer.startElement("img",null); // $NON-NLS-1$
            if(StringUtil.isNotEmpty(image)) {
                image = HtmlRendererUtil.getImageURL(context, image);
                writer.writeAttribute("src",image,null); // $NON-NLS-1$
                String imageAlt = tree.getNode().getImageAlt();
                if (StringUtil.isNotEmpty(imageAlt)) {
                    writer.writeAttribute("alt",imageAlt,null); // $NON-NLS-1$
                }
                String imageHeight = tree.getNode().getImageHeight();
                if (StringUtil.isNotEmpty(imageHeight)) {
                    writer.writeAttribute("height",imageHeight,null); // $NON-NLS-1$
                }
                String imageWidth = tree.getNode().getImageWidth();
                if (StringUtil.isNotEmpty(imageWidth)) {
                    writer.writeAttribute("width",imageWidth,null); // $NON-NLS-1$
                }
            }
            writer.endElement("img"); // $NON-NLS-1$
        }
        
        // Render the text
        String label = tree.getNode().getLabel();
        if(StringUtil.isNotEmpty(label)) {
            writer.writeText(label, "label"); // $NON-NLS-1$
        }

        writePopupImage(context, writer, tree);

        writer.endElement("button");//$NON-NLS-1$
        JSUtil.writeln(writer);
    }
    
    @Override
	protected void renderEntryItemLinkAttributes(FacesContext context, ResponseWriter writer, TreeContextImpl tree, boolean enabled, boolean selected) throws IOException {
//    	if(tree.getNode().getType()==ITreeNode.NODE_CONTAINER) {
//	        writer.writeAttribute("class","dropdown-toggle",null); // $NON-NLS-1$ $NON-NLS-2$
//	        writer.writeAttribute("data-toggle","dropdown",null); // $NON-NLS-1$ $NON-NLS-2$
//    	}
    }
}