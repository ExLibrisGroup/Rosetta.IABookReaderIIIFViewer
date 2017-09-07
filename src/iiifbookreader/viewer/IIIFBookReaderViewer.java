package iiifbookreader.viewer;

import gov.loc.mets.MetsType.FileSec.FileGrp;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.PropertyResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.namespace.QName;

import com.exlibris.dps.sdk.deposit.IEParser;
import com.exlibris.dps.sdk.deposit.IEParserFactory;
import com.exlibris.dps.ws.delivery.DeliveryAccessWS;
import com.exlibris.dps.ws.delivery.DeliveryAccessWS_Service;


public class IIIFBookReaderViewer extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String VIEWER_PROPERTIES = "/conf/viewer.properties";
	private static final String WSDL_LOCATION = "wsdlLocation";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String wsdlLocation = getWsdUrl();
		URL wsdlLocationUrl = new URL(wsdlLocation);
		DeliveryAccessWS deliveryAccessWS = new DeliveryAccessWS_Service(wsdlLocationUrl,new QName("http://dps.exlibris.com/", "DeliveryAccessWS")).getDeliveryAccessWSPort();

		String dvs = request.getParameter("dps_dvs");
		String iePid = request.getParameter("dps_pid");
		String repPid = null;
		IEParser ieParser = null;
		FileGrp[] repList = null;
		String redirect = "";

		try {
			ieParser = IEParserFactory.parse(deliveryAccessWS.getIEByDVS(dvs));
			repList = ieParser.getFileGrpArray();
			repPid = repList[0].getID();
			redirect = "iiif_book_reader.jsp?dps_pid="+iePid+"&rep_pid="+repPid+"&dps_dvs="+dvs;
		} catch (Exception e) {
			e.printStackTrace();
			redirect = "error.jsp";
		}

		RequestDispatcher requestDispatcher = request.getRequestDispatcher(redirect);
		requestDispatcher.forward(request, response);
		return;
	}

	private String getWsdUrl() {
		PropertyResourceBundle resourceBundle = null;
		InputStream resourceAsStream = null;
		try {
			resourceAsStream = getServletContext().getResourceAsStream(VIEWER_PROPERTIES);
			resourceBundle = new PropertyResourceBundle(resourceAsStream);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				resourceAsStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		String wsdlLocation =  resourceBundle.getString(WSDL_LOCATION);
		return wsdlLocation;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}