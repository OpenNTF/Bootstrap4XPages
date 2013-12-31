package biz.webgate.simplecontacts.bootstrap;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import org.openntf.xpt.oneui.component.UITips;

import com.ibm.commons.util.StringUtil;
import com.ibm.xsp.renderkit.FacesRenderer;
public class UITipsRenderer extends FacesRenderer{

	
	public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
		UITips uit = (UITips) component;
		if (!uit.isRendered()) {
			return;
		}
		ResponseWriter writer = context.getResponseWriter();
		writeMainFrame(context, writer, uit);
	}

	private void writeMainFrame(FacesContext context, ResponseWriter writer, UITips uit) throws IOException {
		String strID = uit.getClientId(context);
		String strStyle = uit.getStyle();
		String strStyleClass = uit.getStyleClass();
		String strTitle = uit.getTitle();
		String strText = uit.getText();
		writer.startElement("div", uit);
		writer.writeAttribute("id", strID, null);

		if (StringUtil.isEmpty(strStyleClass)) {
			strStyleClass = "well sidebar-nav";
		}
		writer.writeAttribute("class", strStyleClass, null);
		if (!StringUtil.isEmpty(strStyle)) {
			writer.writeAttribute("style", strStyle, null);
		}
		//writer.writeAttribute("role", "note", null);

		writeTitle(context, writer, uit, strTitle);
		writeText(context, writer, uit, strText);

		writer.endElement("div");

	}

	private void writeText(FacesContext context, ResponseWriter writer, UITips uit, String strText) throws IOException {
		writer.startElement("p", uit);
		if (!StringUtil.isEmpty(strText)) {
			writer.writeText(strText, null);
		}
		writer.endElement("p");

	}

	private void writeTitle(FacesContext context, ResponseWriter writer, UITips uit, String strTitle) throws IOException {
		writer.startElement("h4", uit);
		//writer.startElement("span", null);
		//writer.writeAttribute("class", "lotusLeft", null);
		if (!StringUtil.isEmpty(strTitle)) {
			writer.writeText(strTitle, null);
		}
		//writer.endElement("span");
		writer.endElement("h4");

	}

	@Override
	public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
	}

	@Override
	public boolean getRendersChildren() {
		return true;
	}

}
