/*
 * © Copyright IBM Corp. 2010, 2011
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

package org.openntf.xsp.bootstrap.renderkit.html_extended.extlib.layout;

import java.io.IOException;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import org.openntf.xsp.bootstrap.components.layout.BootstrapApplicationConfiguration;
import org.openntf.xsp.bootstrap.renderkit.html_extended.extlib.layout.tree.BootstrapApplicationLinksRenderer;
import org.openntf.xsp.bootstrap.renderkit.html_extended.extlib.layout.tree.BootstrapFooterLinksRenderer;
import org.openntf.xsp.bootstrap.renderkit.html_extended.extlib.layout.tree.BootstrapPlaceBarActionsRenderer;
import org.openntf.xsp.bootstrap.renderkit.html_extended.extlib.layout.tree.BootstrapSearchOptionsRenderer;
import org.openntf.xsp.bootstrap.renderkit.html_extended.extlib.layout.tree.BootstrapTitleBarTabsRenderer;
import org.openntf.xsp.bootstrap.renderkit.html_extended.extlib.layout.tree.BootstrapUtilityLinksRenderer;
import org.openntf.xsp.bootstrap.resources.BootstrapResources;

import com.ibm.commons.util.StringUtil;
import com.ibm.xsp.component.UICallback;
import com.ibm.xsp.component.xp.XspEventHandler;
import com.ibm.xsp.extlib.component.layout.ApplicationConfiguration;
import com.ibm.xsp.extlib.component.layout.UIApplicationLayout;
import com.ibm.xsp.extlib.component.layout.impl.BasicApplicationConfigurationImpl;
import com.ibm.xsp.extlib.component.layout.impl.SearchBar;
import com.ibm.xsp.extlib.renderkit.html_extended.FacesRendererEx;
import com.ibm.xsp.extlib.renderkit.html_extended.outline.tree.AbstractTreeRenderer;
import com.ibm.xsp.extlib.renderkit.html_extended.outline.tree.ComboBoxRenderer;
import com.ibm.xsp.extlib.tree.ITree;
import com.ibm.xsp.extlib.tree.impl.TreeImpl;
import com.ibm.xsp.extlib.util.ExtLibUtil;
import com.ibm.xsp.extlib.util.ThemeUtil;
import com.ibm.xsp.renderkit.html_basic.HtmlRendererUtil;
import com.ibm.xsp.util.FacesUtil;
import com.ibm.xsp.util.HtmlUtil;
import com.ibm.xsp.util.JSUtil;
import com.ibm.xsp.util.TypedUtil;

/**
 * Base class for a boostrap application layout renderer.
 * 
 * @author priand
 */
public class BootstrapApplicationLayoutRenderer extends FacesRendererEx {

	public static final boolean FLUID = true;

	
	// ==========================================================================
	// Check for the bootstrap specific configuration
	// ==========================================================================

	public BootstrapApplicationConfiguration asBootstrapConfig(BasicApplicationConfigurationImpl configuration) {
		if(configuration instanceof BootstrapApplicationConfiguration) {
			return (BootstrapApplicationConfiguration)configuration;
		}
		return null;
	}
	
	
	// ================================================================
	// Main Frame
	// ================================================================

	protected void writeMainFrame(FacesContext context, ResponseWriter w, UIApplicationLayout c, BasicApplicationConfigurationImpl configuration) throws IOException {
		// Start the mast header
		if (null != configuration && configuration.isMastHeader()) {
			writeMastHeader(context, w, c, configuration);
		}

		w.startElement("div", c);
		w.writeAttribute("class", "applayout-main", null); // $NON-NLS-1$
		if (HtmlUtil.isUserId(c.getId())) {
			w.writeAttribute("id", c.getClientId(context), null); // $NON-NLS-1$
		}
		newLine(w);

		if (configuration != null) {

			// Start the banner
			if (configuration.isBanner()) {
				writeBanner(context, w, c, configuration);
			}

			// Start the title bar
			if (configuration.isTitleBar()) {
				writeTitleBar(context, w, c, configuration);
			}

			// Start the place bar
			if (configuration.isPlaceBar()) {
				writePlaceBar(context, w, c, configuration);
			}

			// Start the main content
			writeMainContent(context, w, c, configuration);

			// Start the footer
			if (configuration.isFooter()) {
				writeFooter(context, w, c, configuration);
			}

			// Start the legal
			if (configuration.isLegal()) {
				writeLegal(context, w, c, configuration);
			}
		}

		// Close the main frame
		w.endElement("div");
		newLine(w);

		// Start the mast footer
		if (null != configuration && configuration.isMastFooter()) {
			writeMastFooter(context, w, c, configuration);
		}
	}

	// ================================================================
	// Mast Header
	// ================================================================

	protected void writeMastHeader(FacesContext context, ResponseWriter w, UIApplicationLayout c, BasicApplicationConfigurationImpl configuration) throws IOException {
		UIComponent mastHeader = c.getMastHeader();
		if (!isEmptyComponent(mastHeader)) {
			if (DEBUG) {
				w.writeComment("Start Mast Header"); // $NON-NLS-1$
				newLine(w);
			}
			FacesUtil.renderComponent(context, mastHeader);
			if (DEBUG) {
				w.writeComment("End Mast Header"); // $NON-NLS-1$
				newLine(w);
			}
		}
	}

	// ================================================================
	// Mast Footer
	// ================================================================

	protected void writeMastFooter(FacesContext context, ResponseWriter w, UIApplicationLayout c, BasicApplicationConfigurationImpl configuration) throws IOException {
		UIComponent mastFooter = c.getMastFooter();
		if (!isEmptyComponent(mastFooter)) {
			if (DEBUG) {
				w.writeComment("Start Mast Footer"); // $NON-NLS-1$
				newLine(w);
			}
			FacesUtil.renderComponent(context, mastFooter);
			if (DEBUG) {
				w.writeComment("End Mast Footer"); // $NON-NLS-1$
				newLine(w);
			}
		}
	}

	// ================================================================
	// Banner
	// ================================================================

	protected void writeBanner(FacesContext context, ResponseWriter w, UIApplicationLayout c, BasicApplicationConfigurationImpl configuration) throws IOException {
		w.startElement("div", c);

		String navStyle = "navbar navbar-static-top navbar-inverse applayout-banner";
		BootstrapApplicationConfiguration bc = asBootstrapConfig(configuration);
		if(bc!=null && !bc.isNavbarInverted()) {
			navStyle = "navbar navbar-static-top applayout-banner";
		}
		w.writeAttribute("class", navStyle, null); // $NON-NLS-1$
		newLine(w);

		w.startElement("div", c);
		w.writeAttribute("class", "navbar-inner", null); // $NON-NLS-1$
		newLine(w);

		// w.startElement("div",c);
		// w.writeAttribute("class","container-fluid",null); // $NON-NLS-1$
		// newLine(w);

		writeBannerContent(context, w, c, configuration);

		// Close the banner
		// w.endElement("div"); newLine(w,"container"); // $NON-NLS-1$
		// $NON-NLS-2$
		w.endElement("div");
		newLine(w, "navbar-inner"); // $NON-NLS-1$ $NON-NLS-2$
		w.endElement("div");
		newLine(w, "navbar-fixed-top"); // $NON-NLS-1$
	}

	protected void writeBannerContent(FacesContext context, ResponseWriter w, UIApplicationLayout c, BasicApplicationConfigurationImpl configuration) throws IOException {
		if (DEBUG) {
			w.writeComment("Start Banner"); // $NON-NLS-1$
			newLine(w);
		}
		writeBannerProductlogo(context, w, c, configuration);
		writeBannerLink(context, w, c, configuration);
		newLine(w);
		writeBannerUtilityLinks(context, w, c, configuration);
		newLine(w);
		writeBannerApplicationLinks(context, w, c, configuration);
		newLine(w);
		if (DEBUG) {
			w.writeComment("End Banner"); // $NON-NLS-1$
			newLine(w);
		}
	}

	protected void writeBannerLink(FacesContext context, ResponseWriter w, UIApplicationLayout c, BasicApplicationConfigurationImpl configuration) throws IOException {
		w.startElement("a", c);
		w.writeAttribute("class", "btn btn-navbar pull-left", null); // $NON-NLS-1$

		String href = "#"; // (String) getProperty(PROP_BANNERLINKHREF);
		if (null == href || '#' != href.charAt(0)) {
			href = "#"; //$NON-NLS-1$
		}
		w.writeAttribute("href", href, null); // $NON-NLS-1$

		w.endElement("a"); // $NON-NLS-1$
	}

	protected void writeBannerProductlogo(FacesContext context, ResponseWriter w, UIApplicationLayout c, BasicApplicationConfigurationImpl configuration) throws IOException {
        w.startElement("span",c); // $NON-NLS-1$
        
        String clazz = ExtLibUtil.concatStyleClasses("pull-left",configuration.getProductLogoClass());
        if(StringUtil.isNotEmpty(clazz)) {
            w.writeAttribute("class",clazz,null); // $NON-NLS-1$
        }
        String style = configuration.getProductLogoStyle();
        if(StringUtil.isNotEmpty(style)) {
            w.writeAttribute("style",style,null); // $NON-NLS-1$
        }
        
        String logoImg = configuration.getProductLogo();
        if(StringUtil.isNotEmpty(logoImg)) {
            String imgSrc = HtmlRendererUtil.getImageURL(context, logoImg);
            w.startElement("img",c); // $NON-NLS-1$
            w.writeURIAttribute("src",imgSrc,null); // $NON-NLS-1$
            String logoAlt = configuration.getProductLogoAlt();
            if(!isAltNotEmpty(logoAlt)) {
                logoAlt = "Banner Product Logo"; // $NLS-AbstractApplicationLayoutRenderer.BannerProductLogo-1$
            }
            w.writeAttribute("alt",logoAlt,null); // $NON-NLS-1$
            String width = configuration.getProductLogoWidth();
            if(StringUtil.isNotEmpty(width)) {
                w.writeAttribute("width",width,null); // $NON-NLS-1$
            }
            String height = configuration.getProductLogoHeight();
            if(StringUtil.isNotEmpty(height)) {
                w.writeAttribute("height",height,null); // $NON-NLS-1$
            }
            w.endElement("img"); // $NON-NLS-1$
        }
    
        w.endElement("span"); // $NON-NLS-1$
	}

	protected void writeBannerApplicationLinks(FacesContext context, ResponseWriter w, UIApplicationLayout c, BasicApplicationConfigurationImpl configuration) throws IOException {
		ITree tree = TreeImpl.get(configuration.getBannerApplicationLinks());
		if (tree != null) {
			AbstractTreeRenderer renderer = new BootstrapApplicationLinksRenderer();
			if (renderer != null) {
				renderer.render(context, c, "al", tree, w); // $NON-NLS-1$
			}
		}
	}

	protected void writeBannerUtilityLinks(FacesContext context, ResponseWriter w, UIApplicationLayout c, BasicApplicationConfigurationImpl configuration) throws IOException {
		ITree tree = TreeImpl.get(configuration.getBannerUtilityLinks());
		if (tree != null) {
			AbstractTreeRenderer renderer = new BootstrapUtilityLinksRenderer();
			if (renderer != null) {
				renderer.render(context, c, "ul", tree, w); // $NON-NLS-1$
			}
		}
	}

	// ================================================================
	// Title Bar
	// ================================================================

	protected void writeTitleBar(FacesContext context, ResponseWriter w, UIApplicationLayout c, BasicApplicationConfigurationImpl configuration) throws IOException {
		w.startElement("div", c);
		w.writeAttribute("class", "navbar navbar-static-top applayout-titlebar", null); // $NON-NLS-1$
		newLine(w);

		w.startElement("div", c);
		w.writeAttribute("class", "navbar-inner applayout-titlebar-inner", null); // $NON-NLS-1$
		newLine(w);
		
        String titleBarName = configuration.getTitleBarName();
        if( StringUtil.isNotEmpty(titleBarName)) {
        	w.startElement("h4",c); //$NON-NLS-1$
            w.writeAttribute("class","applayout-titlebar-name",null); // $NON-NLS-1$
            w.writeAttribute("title",titleBarName,null); // $NON-NLS-1$
            w.write(titleBarName);
        	w.endElement("h4"); //$NON-NLS-1$
        	newLine(w);
        }

		writeTitleBarTabsArea(context, w, c, configuration);
		writeSearchBar(context, w, c, configuration);

		// Close the banner
		w.endElement("div");
		newLine(w, "navbar-inner"); // $NON-NLS-1$ $NON-NLS-2$
		w.endElement("div");
		newLine(w, "navbar-static-top"); // $NON-NLS-1$

		w.endElement("div"); // $NON-NLS-1$
	}

	protected void writeTitleBarTabsArea(FacesContext context, ResponseWriter w, UIApplicationLayout c, BasicApplicationConfigurationImpl configuration) throws IOException {
		// Write the tabs
		writeTitleBarTabs(context, w, c, configuration);
	}

	protected void writeTitleBarTabs(FacesContext context, ResponseWriter w, UIApplicationLayout c, BasicApplicationConfigurationImpl configuration) throws IOException {
		ITree tree = TreeImpl.get(configuration.getTitleBarTabs());
		if (tree != null) {
			AbstractTreeRenderer renderer = new BootstrapTitleBarTabsRenderer();
			if (renderer != null) {
				renderer.render(context, c, "tb", tree, w); // $NON-NLS-1$
			}
		}
	}

	// ================================================================
	// Search Bar (normally part of the title bar)
	// ================================================================

	protected void writeSearchBar(FacesContext context, ResponseWriter w, UIApplicationLayout c, BasicApplicationConfigurationImpl configuration) throws IOException {
		UIComponent cSearchBar = c.getSearchBar();
		if (!isEmptyComponent(cSearchBar)) {
			if (DEBUG) {
				w.writeComment("Start SearchBar Facet"); // $NON-NLS-1$
				newLine(w);
			}
			w.startElement("div", c); // $NON-NLS-1$
			w.writeAttribute("class","navbar-search pull-right applayout-searchbar",null); // $NON-NLS-1$
			w.writeAttribute("role", "search", null); // $NON-NLS-1$
			FacesUtil.renderComponent(context, cSearchBar);
			w.endElement("div"); // $NON-NLS-1$
			if (DEBUG) {
				w.writeComment("End SearchBar Facet"); // $NON-NLS-1$
				newLine(w);
			}
			return;
		}

		SearchBar searchBar = configuration.getSearchBar();
		if (searchBar != null && searchBar.isRendered()) {
			if (DEBUG) {
				w.writeComment("Start Search Bar"); // $NON-NLS-1$
				newLine(w);
			}
			w.startElement("div", c); // $NON-NLS-1$
			w.writeAttribute("class","navbar-search pull-right input-append applayout-searchbar",null); // $NON-NLS-1$
			w.writeAttribute("style", "margin-top: 5px; margin-bottom: 5px", null); // $NON-NLS-1$
			w.writeAttribute("role", "search", null); // $NON-NLS-1$
			newLine(w);

			boolean searchOptions = false;
			ITree tree = TreeImpl.get(searchBar.getOptions());
			if (tree != null) {
				searchOptions = true;
			}

			// Write the search options
			if (searchOptions) {
				writeSearchOptions(context, w, c, configuration, searchBar, tree);
			}

			// Write the search box
			w.startElement("span", c); // $NON-NLS-1$
			w.writeAttribute("class","form-search nav-search-panel",null); // $NON-NLS-1$
			w.startElement("span", c); // $NON-NLS-1$
			w.writeAttribute("class","input-append",null); // $NON-NLS-1$
			writeSearchBox(context, w, c, configuration, searchBar, tree, searchOptions);
			writeSearchButton(context, w, c, configuration, searchBar, tree);
			w.endElement("span"); // $NON-NLS-1$
			w.endElement("span"); // $NON-NLS-1$
			newLine(w);

			// Write the required script
			writeSearchScript(context, w, c, configuration, searchBar, tree, searchOptions);
			newLine(w);

			w.endElement("div"); // $NON-NLS-1$
			newLine(w);
			if (DEBUG) {
				w.writeComment("End Search Bar"); // $NON-NLS-1$
				newLine(w);
			}
		}
	}

	protected void writeSearchOptions(FacesContext context, ResponseWriter w, UIApplicationLayout c, BasicApplicationConfigurationImpl configuration, SearchBar searchBar, ITree tree) throws IOException {
		AbstractTreeRenderer renderer = getSearchOptionsRenderer(context, w, c, configuration, searchBar);
		if (renderer != null) {
			renderer.render(context, c, "so", tree, w); // $NON-NLS-1$
		}
	}

	protected AbstractTreeRenderer getSearchOptionsRenderer(FacesContext context, ResponseWriter w, UIApplicationLayout c, BasicApplicationConfigurationImpl configuration, SearchBar searchBar) {
		String cid = c.getClientId(context) + "_searchopt"; // $NON-NLS-1$
		ComboBoxRenderer renderer = new BootstrapSearchOptionsRenderer();
		renderer.setClientId(cid);
		String scopeTitle = searchBar.getScopeTitle();
		if (null == scopeTitle) {
			scopeTitle = "";
		}
		if (StringUtil.isNotEmpty(scopeTitle)) {
			renderer.setAccTitle(scopeTitle);
		}
		return renderer;
	}

	protected void writeSearchBox(FacesContext context, ResponseWriter w, UIApplicationLayout c, BasicApplicationConfigurationImpl configuration, SearchBar searchBar, ITree tree, boolean options) throws IOException {
		String cid = c.getClientId(context) + "_search"; // $NON-NLS-1$
		w.startElement("input", c); // $NON-NLS-1$
		w.writeAttribute("id", cid, null); // $NON-NLS-1$
		w.writeAttribute("name", cid, null); // $NON-NLS-1$
		w.writeAttribute("type", "text", null); // $NON-NLS-1$ $NON-NLS-2$

		w.writeAttribute("class", "search-query", null); // $NON-NLS-1$

		String inputTitle = searchBar.getInputTitle();
		if (StringUtil.isNotEmpty(inputTitle)) {
			w.writeAttribute("title", inputTitle, null); // $NON-NLS-1$
		}
		String inactiveText = searchBar.getInactiveText();
		if (StringUtil.isNotEmpty(inactiveText)) {
			w.writeAttribute("placeHolder", inactiveText, null); // $NON-NLS-1$
		}

        String submitSearch = "_xspAppSearchSubmit"; // $NON-NLS-1$
        // TODO accessibility 
        w.writeAttribute("onkeypress","javascript:var kc=event.keyCode?event.keyCode:event.which;if(kc==13){"+submitSearch+"(); return false}",null); // $NON-NLS-1$ $NON-NLS-2$ $NON-NLS-3$

        w.endElement("input"); // $NON-NLS-1$
        newLine(w);
	}

	protected void writeSearchButton(FacesContext context, ResponseWriter w, UIApplicationLayout c, BasicApplicationConfigurationImpl configuration, SearchBar searchBar, ITree tree) throws IOException {
		 String submitSearch = "_xspAppSearchSubmit"; // $NON-NLS-1$
		
		 w.startElement("button",c); // $NON-NLS-1$
		 w.writeAttribute("class","btn",null); // $NON-NLS-1$
		 w.writeAttribute("onclick","javascript:"+submitSearch+"(); return false;",null); // $NON-NLS-1$ $NON-NLS-2$ $NON-NLS-3$
		 w.startElement("span",c); // $NON-NLS-1$
		 w.writeAttribute("class",BootstrapResources.get().getIconClass("search"),null); // $NON-NLS-1$
		 w.endElement("span"); // $NON-NLS-1$
		 w.endElement("button"); // $NON-NLS-1$
	}

	protected void writeSearchScript(FacesContext context, ResponseWriter w, UIApplicationLayout c, BasicApplicationConfigurationImpl configuration, SearchBar searchBar, ITree tree, boolean options) throws IOException {
		String cid = c.getClientId(context) + "_search"; // $NON-NLS-1$
        String submitSearch = "_xspAppSearchSubmit"; // $NON-NLS-1$

        // "/search.xsp"
        String searchPageName = searchBar.getPageName();
        if( StringUtil.isEmpty(searchPageName) ){
            searchPageName = "/";
        }else{
            // append .xsp if needed
            searchPageName = ExtLibUtil.getPageXspUrl(searchPageName);
        }
        // "/apps/XPagesExt.nsf/search.xsp"
        String path = context.getApplication().getViewHandler().getResourceURL(context, searchPageName);
        path = context.getExternalContext().encodeActionURL(path);

        // Compose the script function
        w.startElement("script",c); // $NON-NLS-1$
        if(DEBUG) { newLine(w); }
        StringBuilder sb = new StringBuilder();
        sb.append("function "); // $NON-NLS-1$
        sb.append(submitSearch);
        sb.append("(){");
        if(DEBUG) { sb.append('\n'); }
        //sb.append("var val=XSP.getElementById('"); sb.append(cid); sb.append("').value;");
        sb.append("var val=XSP.getFieldValue(XSP.getElementById('"); sb.append(cid); sb.append("'));"); // $NON-NLS-1$
        if(DEBUG) { sb.append('\n'); }
        if(options) {
            String oid = c.getClientId(context)+"_searchopt"; // $NON-NLS-1$
            sb.append("var opt=XSP.getFieldValue(XSP.getElementById('"); sb.append(oid); sb.append("'));"); // $NON-NLS-1$
            if(DEBUG) { sb.append('\n'); }
        }
        sb.append("if(val){var loc='"); // $NON-NLS-1$
        JSUtil.appendJavaScriptString(sb, path);
        sb.append("?");
        String queryParam = searchBar.getQueryParam();
        if(StringUtil.isEmpty(queryParam)) {
            queryParam = "search"; // $NON-NLS-1$
        }
        JSUtil.appendJavaScriptString(sb, queryParam);
        sb.append("='+encodeURIComponent(val)"); // $NON-NLS-1$
        if(options) {
            sb.append("+'&");
            String optionsParam = searchBar.getOptionsParam();
            if(StringUtil.isEmpty(optionsParam)) {
                optionsParam = "option"; // $NON-NLS-1$
            }
            JSUtil.appendJavaScriptString(sb, optionsParam);
            sb.append("='+encodeURIComponent(opt)"); // $NON-NLS-1$
        }
        sb.append(";");
        if(DEBUG) { sb.append('\n'); }
        sb.append("window.location.href=loc;}}"); // $NON-NLS-1$
        w.writeText(sb.toString(),null);
        if(DEBUG) { newLine(w); }
        
        w.endElement("script"); // $NON-NLS-1$
	}

	
	// ================================================================
	// Place Bar
	// ================================================================

	protected void writePlaceBar(FacesContext context, ResponseWriter w, UIApplicationLayout c, BasicApplicationConfigurationImpl configuration) throws IOException {
		w.startElement("div", c);
		w.writeAttribute("class", "navbar navbar-static-top applayout-placebar", null); // $NON-NLS-1$
		newLine(w);

		w.startElement("div", c);
		w.writeAttribute("class", "navbar-inner", null); // $NON-NLS-1$
		newLine(w);

		w.startElement("div", c);
		w.writeAttribute("class", "applayout-placebar-title", null); // $NON-NLS-1$
		writePlaceBarName(context, w, c, configuration);
		UIComponent cPlaceBarName = c.getPlaceBarName();
		if (!isEmptyComponent(cPlaceBarName)) {
			if (DEBUG) {
				w.writeComment("Start PlaceBarName Facet"); // $NON-NLS-1$
				newLine(w);
			}
			FacesUtil.renderComponent(context, cPlaceBarName);
		}
		w.endElement("div"); // $NON-NLS-1$

		w.startElement("div", c);
		w.writeAttribute("class", "navbar pull-right applayout-placebar-actions", null); // $NON-NLS-1$
		writePlaceBarActions(context, w, c, configuration);
		UIComponent cPlaceBarActions = c.getPlaceBarActions();
		if (!isEmptyComponent(cPlaceBarActions)) {
			if (DEBUG) {
				w.writeComment("Start PlaceBarActions Facet"); // $NON-NLS-1$
				newLine(w);
			}
			w.startElement("div", c); // $NON-NLS-1$
			w.writeAttribute("class", "lotusBtnContainer", null); //$NON-NLS-1$ $NON-NLS-2$
			FacesUtil.renderComponent(context, cPlaceBarActions);
			w.endElement("div"); // $NON-NLS-1$
		}
		w.endElement("div"); // $NON-NLS-1$

		// Close the banner
		w.endElement("div");
		newLine(w, "navbar-inner"); // $NON-NLS-1$ $NON-NLS-2$
		w.endElement("div");
		newLine(w, "navbar-static-top"); // $NON-NLS-1$

		w.endElement("div"); // $NON-NLS-1$
	}

	protected void writePlaceBarName(FacesContext context, ResponseWriter w, UIApplicationLayout c, BasicApplicationConfigurationImpl configuration) throws IOException {
		String placeName = configuration.getPlaceBarName();
		if (StringUtil.isNotEmpty(placeName)) {
			String placeBarNameTag = "h3";
			w.startElement(placeBarNameTag, c);
			w.writeText(placeName, null);
			w.endElement(placeBarNameTag);
			newLine(w);
		}
	}

	protected void writePlaceBarActions(FacesContext context, ResponseWriter w, UIApplicationLayout c, BasicApplicationConfigurationImpl configuration) throws IOException {
		ITree tree = TreeImpl.get(configuration.getPlaceBarActions());
		if (tree != null) {
			AbstractTreeRenderer renderer = new BootstrapPlaceBarActionsRenderer();
			if (renderer != null) {
				renderer.render(context, c, "pb", tree, w); // $NON-NLS-1$
			}
		}
	}

	// ================================================================
	// Main content
	// ================================================================

	protected void writeMainContent(FacesContext context, ResponseWriter w, UIApplicationLayout c, BasicApplicationConfigurationImpl configuration) throws IOException {
		w.startElement("div", c); // $NON-NLS-1$
		w.writeAttribute("class", FLUID ? "container-fluid" : "container", null); // $NON-NLS-1$
		newLine(w);

		w.startElement("div", c); // $NON-NLS-1$
		w.writeAttribute("class", FLUID ? "row-fluid" : "row", null); // $NON-NLS-1$

		// Write the 3 columns
		writeLeftColumn(context, w, c, configuration);
		writeContentColumn(context, w, c, configuration);
		writeRightColumn(context, w, c, configuration);

		// Close the main content
		w.endElement("div");
		newLine(w, "row"); // $NON-NLS-1$
		w.endElement("div");
		newLine(w, "container"); // $NON-NLS-1$
	}

	protected void writeLeftColumn(FacesContext context, ResponseWriter w, UIApplicationLayout c, BasicApplicationConfigurationImpl configuration) throws IOException {
		UIComponent left = c.getLeftColumn();
		if (!isEmptyComponent(left)) {
			if (DEBUG) {
				w.writeComment("Start Left Column"); // $NON-NLS-1$
				newLine(w);
			}
			w.startElement("div", c); // $NON-NLS-1$
			w.writeAttribute("class", "span2 applayout-column-left", null); // $NON-NLS-1$

			FacesUtil.renderComponent(context, left);

			w.endElement("div");
			newLine(w); // $NON-NLS-1$

			if (DEBUG) {
				w.writeComment("End Left Column"); // $NON-NLS-1$
				newLine(w);
			}
		}
	}

	protected void writeRightColumn(FacesContext context, ResponseWriter w, UIApplicationLayout c, BasicApplicationConfigurationImpl configuration) throws IOException {
		UIComponent right = c.getRightColumn();
		if (!isEmptyComponent(right)) {
			if (DEBUG) {
				w.writeComment("Start Right Column"); // $NON-NLS-1$
				newLine(w);
			}
			w.startElement("div", c); // $NON-NLS-1$
			w.writeAttribute("class", "span2 applayout-column-right", null); // $NON-NLS-1$

			FacesUtil.renderComponent(context, right);

			w.endElement("div");
			newLine(w);

			if (DEBUG) {
				w.writeComment("End Right Column"); // $NON-NLS-1$
				newLine(w);
			}
		}
	}

	protected void writeContentColumn(FacesContext context, ResponseWriter w, UIApplicationLayout c, BasicApplicationConfigurationImpl configuration) throws IOException {
		if (!isEmptyChildren(c)) {
			if (DEBUG) {
				w.writeComment("Start Content Column"); // $NON-NLS-1$
				newLine(w);
			}
			w.startElement("div", c); // $NON-NLS-1$

			boolean left = !isEmptyComponent(c.getLeftColumn());
			boolean right = !isEmptyComponent(c.getRightColumn());
			if (left && right) {
				w.writeAttribute("class", "span8 applayout-content", null); // $NON-NLS-1$
			}
			if (left || right) {
				w.writeAttribute("class", "span10 applayout-content", null); // $NON-NLS-1$
			} else {
				w.writeAttribute("class", "span12 applayout-content", null); // $NON-NLS-1$
			}

			renderChildren(context, c);

			w.endElement("div");
			newLine(w); // $NON-NLS-1$

			if (DEBUG) {
				w.writeComment("End Content Column"); // $NON-NLS-1$
				newLine(w);
			}
		}
	}

	// ================================================================
	// Footer
	// ================================================================

	protected void writeFooter(FacesContext context, ResponseWriter w, UIApplicationLayout c, BasicApplicationConfigurationImpl configuration) throws IOException {
		w.startElement("footer", c);
		w.writeAttribute("class", "navbar navbar-bottom  applayout-footer", null); // $NON-NLS-1$
		newLine(w);

		w.startElement("div", c);
		w.writeAttribute("class", "navbar-inner", null); // $NON-NLS-1$
		w.startElement("div", c);
		w.writeAttribute("style", "display: table; margin-left: auto; margin-right: auto; text-align: center;", null); // $NON-NLS-1$
		newLine(w);

		writeFooterLinks(context, w, c, configuration);

		w.endElement("div");
		newLine(w, null); // $NON-NLS-1$ $NON-NLS-2$
		w.endElement("div");
		newLine(w, "navbar-inner"); // $NON-NLS-1$ $NON-NLS-2$

		w.endElement("footer");
		newLine(w, "footer");
	}

	protected void writeFooterLinks(FacesContext context, ResponseWriter w, UIApplicationLayout c, BasicApplicationConfigurationImpl configuration) throws IOException {
		ITree tree = TreeImpl.get(configuration.getFooterLinks());
		if (tree != null) {
			AbstractTreeRenderer renderer = new BootstrapFooterLinksRenderer();
			if (renderer != null) {
				renderer.render(context, c, "fl", tree, w); // $NON-NLS-1$
			}
		}
	}

	// ================================================================
	// Legal
	// ================================================================

	protected void writeLegal(FacesContext context, ResponseWriter w, UIApplicationLayout c, BasicApplicationConfigurationImpl configuration) throws IOException {
		w.startElement("footer", c);
		w.writeAttribute("class", "navbar navbar-bottom applayout-legal", null); // $NON-NLS-1$
		newLine(w);

		w.startElement("div", c);
		w.writeAttribute("style", "display: table; margin-left: auto; margin-right: auto; text-align: center;", null); // $NON-NLS-1$
		newLine(w);

		writeLegalLogo(context, w, c, configuration);
		writeLegalText(context, w, c, configuration);

		w.endElement("div");
		newLine(w, null); // $NON-NLS-1$ $NON-NLS-2$

		w.endElement("footer");
		newLine(w, "footer");
	}

	protected void writeLegalLogo(FacesContext context, ResponseWriter w, UIApplicationLayout c, BasicApplicationConfigurationImpl configuration) throws IOException {
		String logoImg=configuration.getLegalLogo();
		if(StringUtil.isNotEmpty(logoImg)) {
			w.startElement("td", c); // $NON-NLS-1$
			w.startElement("span", c); // $NON-NLS-1$
			String clazz=configuration.getLegalLogoClass();
			if(StringUtil.isNotEmpty(clazz)) {
				w.writeAttribute("class", clazz, null); // $NON-NLS-1$
			}
			String style=ExtLibUtil.concatStyles("float:left;vertical-align:middle;margin-right: 5px;", configuration.getLegalLogoStyle());
			if(StringUtil.isNotEmpty(style)) {
				w.writeAttribute("style", style, null); // $NON-NLS-1$
			}
			String imgSrc=HtmlRendererUtil.getImageURL(context, logoImg);
			w.startElement("img", c); // $NON-NLS-1$
			w.writeURIAttribute("src", imgSrc, null); // $NON-NLS-1$
			String logoAlt=configuration.getLegalLogoAlt();
			if(!isAltNotEmpty(logoAlt)) {
				logoAlt="Legal Logo"; 
			}
			w.writeAttribute("alt", logoAlt, null); // $NON-NLS-1$
			String width=configuration.getLegalLogoWidth();
			if(StringUtil.isNotEmpty(width)) {
				w.writeAttribute("width", width, null); // $NON-NLS-1$
			}
			String height=configuration.getLegalLogoHeight();
			if(StringUtil.isNotEmpty(height)) {
				w.writeAttribute("height", height, null); // $NON-NLS-1$
			}
			w.endElement("img"); // $NON-NLS-1$
			w.endElement("span"); // $NON-NLS-1$
			w.endElement("td"); // $NON-NLS-1$
		}
	}

	protected void writeLegalText(FacesContext context, ResponseWriter w, UIApplicationLayout c, BasicApplicationConfigurationImpl configuration) throws IOException {
		String legalText = configuration.getLegalText();
		if (StringUtil.isNotEmpty(legalText)) {
			w.startElement("td", c); // $NON-NLS-1$
			// w.writeAttribute("class",legalTextClass,null); // $NON-NLS-1$
			w.writeText(legalText, null);
			w.endElement("td"); // $NON-NLS-1$
		}
	}

	// ==================================================================
	// JSF renderer methods

	@Override
	public void decode(FacesContext context, UIComponent component) {
		// Nothing to decode here...
	}

	@Override
	public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
		ResponseWriter w = context.getResponseWriter();

		UIApplicationLayout c = (UIApplicationLayout) component;
		if (!c.isRendered()) {
			return;
		}

		ApplicationConfiguration _conf = c.findConfiguration();
		if (!(_conf instanceof BasicApplicationConfigurationImpl)) {
			return;

		}
		BasicApplicationConfigurationImpl configuration = (BasicApplicationConfigurationImpl) _conf;

		writeMainFrame(context, w, c, configuration);
	}

	@Override
	public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
		// All is done is encode begin...
	}

	@Override
	public boolean getRendersChildren() {
		return true;
	}

	@Override
	public void encodeChildren(FacesContext context, UIComponent component) throws IOException {
		// Forget about the children, only the facets are rendered
	}

	// ==================================================================
	// Renderer utilities

	// we should not render the children as we don't want to render the
	// event hander (we directly generate calls to the fireEvent methods
	// rather than attaching event here.

	protected void renderChildren(FacesContext context, UIComponent component) throws IOException {
		// encode component and children
		int count = component.getChildCount();
		if (count > 0) {
			List<?> children = component.getChildren();
			for (int i = 0; i < count; i++) {
				UIComponent child = (UIComponent) children.get(i);
				if (isRenderChild(context, child)) {
					FacesUtil.renderComponent(context, child);
				}
			}
		}
	}

	protected boolean isRenderChild(FacesContext context, UIComponent child) throws IOException {
		// Only render the non event handler components
		if (!(child instanceof XspEventHandler)) {
			return true;
		}
		return false;
	}

	protected boolean isEmptyComponent(UIComponent c) {
		// If the component is null, then it is considered as empty
		if (c == null) {
			return true;
		}
		// If it is not rendered, then it is empty as well
		if (!c.isRendered()) {
			return true;
		}
		// Else, if it is a UICallback, then we should check it content
		// a UICallback without anything in it should be considered as
		// and empty component.
		if (c instanceof UICallback) {
			if (c.getChildCount() > 0) {
				for (Object child : c.getChildren()) {
					if (!isEmptyComponent((UIComponent) child)) {
						return false;
					}
				}
			}
			if (c.getFacetCount() > 0) {
				for (Object child : c.getFacets().values()) {
					if (!isEmptyComponent((UIComponent) child)) {
						return false;
					}
				}
			}
			return true;
		}
		// Ok, the component exists so it is not considered as empty
		return false;
	}

	protected boolean isEmptyChildren(UIComponent c) {
		if (c.getChildCount() > 0) {
			// We should check the children one by one...
			for (UIComponent child : TypedUtil.getChildren(c)) {
				if (!isEmptyComponent(child)) {
					return false;
				}
			}
		}
		// No children, so the list is empty
		return true;
	}

    private boolean isAltNotEmpty(String alt) {
        // Note, do not use StringUtil.isNotEmpty for alt text
        // because for accessibility reasons there's a difference
        // between alt="" and no alt attribute set,
        // so we treat null and "" as different for alt.
        return null != alt;
    }	
}