<h2>Welcome to <i>Bootstrap4XPages</i></h2>

This project provides the Twitter Bootstrap rendering technology to XPages. The goal is to enable this new rendering through a dedicated theme, without having to change the application pages. All you have to do is to set the application theme to 'bootstrapv2.3.1' in Domino Designer. Voila!</br>
The theme covers all the regular XPages controls, as well as the ones delivered as part of the extension library and generally rendered using OneUI (application layout, data view...).</br>
</br>
![Screenshot](https://raw.github.com/OpenNTF/Bootstrap4XPages/master/DataView.png)
</br>	
<h3>Library overview</h3>
Bootstrap4Xpages is defined as a custom XPages library and delivered as an Eclipse update site. As of today, it is only composed of one plug-in, but this might evolve in the future. In order to be consumed, it has to be installed in both Domino Designer and the Domino server, like any other XPages extension library. For more information, see: <a href='http://www-10.lotus.com/ldd/ddwiki.nsf/dx/Master_Table_of_Contents_for_XPages_Extensibility_APIs_Developer_Guide#Deployment'>XPages Extensibility API Developers Guide</a></br>
</br>
The library provides the following features:</br>
<ul>
<li>
The Bootstrap files (CSS & JS), as well as a copy of JQuery which is required to run the Bootstrap components</br>
These files are served as global resources, thus they don't need to be added to the NSF applications where bootstrap is used. Moreover, they are already minified and shrinked to maximize the runtime performance. Last but not least, the plug-in makes them participating to the XPages resources aggregator.
</li>
<li>
An XPages theme</br>
A set of theme files are provided to automatically add the necessary resources to the pages (CSS & JS), and to make the XPages components consume the provided styles. The theme is easily selected from the Xsp properties editor within Domino Designer. To support multiple versions of Bootstrap, the theme name includes the version of Bootstrap, like, for example, 'bootstrapv2.3.1' for Bootstrap 2.3.1, or 'bootstrapv2.3.1r' for Bootstrap 2.3.1 Responsive (the last one add an extra responsive css and a meta tag to the page, see <a href='http://getbootstrap.com/2.3.2/scaffolding.html#responsive'>http://getbootstrap.com/2.3.2/scaffolding.html#responsive</a>). Note that the theme loads the responsive CSS version of Bootstrap.
</li>
<li>
DBootstrap</br>
This is an open source library that defines defines a Dojo theme using the Bootstrap CSS. It makes the standard Dojo dijits consistent with the Bootstrap look and feel. This theme replaces the default Dojo theme ('tundra' for core XPages).
</li>
<li>
Dojo Bootstrap</br>
This is another open source library that wraps the Bootstrap components into Dojo components. This library is currently *not* used by Bootstrap4XPages, but is provided as a convenience to Dojo developers. An later release might use these components instead of the standard JQuery ones, to minimize the downloaded JS, take full advantage of the AMD notation and make the whole JavaScript code consistent.
</li>
</ul>

<h3>Provided third party components</h3>
The following open source components had been legally cleared out and are delivered as part of the library:
<ul>
	<li>Bootstrap 2.3.1 (<a href='https://github.com/twbs/bootstrap'>https://github.com/twbs/bootstrap</a>)</li>
	<li>JQuery 1.8.2 (<a href='http://jquery.com/'>http://jquery.com/</a>)</li>
	<li>Dojo Bootstrap (<a href='https://github.com/xsokev/Dojo-Bootstrap'>https://github.com/xsokev/Dojo-Bootstrap</a>)</li>
	<li>DBootstrap (<a href='http://thesociable.github.io/dbootstrap/'>http://thesociable.github.io/dbootstrap/</a>)</li>
</ul>

<h3>Running the existing demo databases</h3>
The theme has been successfully applied to several existing demo databases. The extlib demo database has some hard coded OneUI styles that should be replaced to get a complete Bootstrap look and feel:
<ul>
	<li>
		Cancel buttons</br>
		The cancel buttons used in the dialogs are hard coded as HTML links with OneUI style classes.</br>
		The following code:</br>
		<code>&lt;xp:link id="button17" text="Cancel" styleClass="lotusAction"&gt;</code></br>
		should be replaced by:</br>
		<code>&lt;xp:button value="Cancel" themeId="Button"&gt;</code></br>
		in the different pages where a dialog is used.
	</li>
	<li>
		Navigator</br>
		Bootstrap allows 2 different rendering for a navigator, called "pill" and "list". "pill", which is the default with Bootstrap4XPages, should be used for the main navigation menu, generally in the left column of the page. "List" should be used for simple lists within a page. But, as the extension library only offers one xe:navigator component, the library detects if the style 'nav-list' is set to the component. If so, then it renders a 'list' instead of a 'pill'.<br>
		In the extlib demo database, the xe:navigator in the OneUI_Outline.xsp page should be modified like this:</br>
		<code>&lt;xe:navigator id="xxxx" styleClass="nav-list"&gt;</code>
	</li>
	<li>
		Application Layout Configuration</br>
		The base application layout configuration object, as well as the OneUI one, work perfectly with the Bootstrap theme. Now, there is a bootstrap specific configuration object that will bring more bootstrap specific options in the future. So it is advised to use this one instead, to leverage the future enhancements.
	</li>

</ul>

<h3>Licensing information</h3>
This library is delivered under the Apache 2.0 license. A compiled, ready to use version, is available from the OpenNTF web site at: <a href='http://bootstrap4xpages.openntf.org/'>http://bootstrap4xpages.openntf.org/</a>
<br/>

<h3>Known Limitations</h3>
The components provided by the library are currently not matching the IBM standard in term of localization (support for multiple languages, bibi support) or accessibity.

<h3>And what's next?</h3>
Well, many things can be done! Now that the bulk of the code is there, I'm seeking help from the community to extend this library, or build on top of it. Feel free to fork the code and create your additions. Then get them integrated within the core project, if it makes sense. We'll accept external contributions to this library after having the code properly reviewed.</br>
Bellow is a initial set of ideas, but please come with yours!</br>
<ul>
	<li>
		Remove all the lotusXXXX styles</br>
		Clean-up all the renderers from these classes and add new support classes. Also, merge xsp.css and xpages.css in one single file.
	</li>
	<li>
		Move to Bootstrap 3.0</br>
		Shouldn't be a big deal if dbootstrap is upgraded, and all the code legally cleared. The 'panel' classes should be removed from xpages.css.
	</li>
	<li>
		Use the native Boostrap components instead of the Dojo ones</br>
		Some of the extlib controls, like Dialog or Accordion, leverage the corresponding Dojo controls. But they are actually not tightly coupled to Dojo, and the implementation can use the native Bootstrap components itself for a better fidelity and higher performance.
	</li>
	<li>
		Wrap the Bootstrap components</br>
		Create a series of Bootstrap specific components wrapping the Boostrap features and/or some Bootstrap extensions (see: <a href='http://bootsnipp.com/resources'>http://bootsnipp.com/resources</a>).
	</li>
	<li>
		Demo Database</br>
		Provide a new database demontrating all the Bootstrap features in action. Contribute to Bootstrap4XPages site: <a href='http://www.bootstrap4xpages.com/bs4xp/site.nsf'>http://www.bootstrap4xpages.com/bs4xp/site.nsf</a>
	</li>
	<li>
		Leverage the Bootstrap mobile capability</br>
		Bootstrap 3 is mobile first, let's leverage it.
	</li>
	<li>
		Your idea goes here...</br>
	</li>
</ul>
As you can see, there is enough for an army to work on.</br>
<p>
Enjoy!
</p>