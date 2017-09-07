<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<%@page import="java.util.PropertyResourceBundle"%>
<%@page import="java.io.IOException"%>
<%@page import="java.io.InputStream"%>
<%
	String iePid = request.getParameter("dps_pid");
	String repPid = request.getParameter("rep_pid");
	String dvs = request.getParameter("dps_dvs");
	PropertyResourceBundle resourceBundle = null;
	InputStream resourceAsStream = null;
	try {
		resourceAsStream = getServletContext().getResourceAsStream("/conf/viewer.properties");
		resourceBundle = new PropertyResourceBundle(resourceAsStream);
	} finally {
		try {
			resourceAsStream.close();
		} catch (IOException e) {
		}
	}

	String serverUrl =  resourceBundle.getString("deliveryServerUrl");
	String manifestUrl = serverUrl + "/delivery/iiif/presentation/2.1/"+iePid+"/manifest&dps_dvs="+dvs;
	String sequesnceUrl = serverUrl + "/delivery/iiif/presentation/2.1/"+iePid+"/sequence/"+repPid;
%>

<head>
    <title>IIIF Book Reader Viewer</title>

    <link rel="stylesheet" type="text/css" href="js/lib/BookReader/BookReader.css"/>
    <!-- Custom CSS overrides -->
    <link rel="stylesheet" type="text/css" href="css/main.css"/>

    <script type="text/javascript" src="js/lib/BookReader/jquery-1.4.2.min.js"></script>
    <script type="text/javascript" src="js/lib/BookReader/jquery-ui-1.8.5.custom.min.js"></script>

    <script type="text/javascript" src="js/lib/BookReader/dragscrollable.js"></script>
    <script type="text/javascript" src="js/lib/BookReader/jquery.colorbox-min.js"></script>
    <script type="text/javascript" src="js/lib/BookReader/jquery.ui.ipad.js"></script>
    <script type="text/javascript" src="js/lib/BookReader/jquery.bt.min.js"></script>

    <script type="text/javascript" src="js/lib/BookReader/BookReader.js"></script>
</head>
<body style="background-color: ##939598;">

<div id="BookReader">
    Internet Archive BookReader <br/>

    <noscript>
    <p>
        The BookReader requires JavaScript to be enabled. Please check that your browser supports JavaScript and that it is enabled in the browser settings.  You can also try one of the <a href="http://www.archive.org/details/goodytwoshoes00newyiala"> other formats of the book</a>.
    </p>
    </noscript>
</div>

<script type="text/javascript" src="js/lib/IIIFBookReader.js"></script>
<script type="text/javascript">
	var br = new BookReader();
	br.IIIF({
		url: '<%=manifestUrl%>',
	    sequenceId : '<%=sequesnceUrl%>',
	    maxWidth: 800
	});

	br.getEmbedCode = function(frameWidth, frameHeight, viewParams) {
	    return "Embed code not supported in bookreader demo.";
	}

	// Book title and the URL used for the book title link
	br.bookTitle = 'Exlibris';
	// Override the path used to find UI images
	br.imagesBaseURL = 'js/lib/BookReader/images/';

	// Let's go!
	br.init();
</script>
<script type="text/javascript" src="js/main.js"></script>

</body>
</html>
