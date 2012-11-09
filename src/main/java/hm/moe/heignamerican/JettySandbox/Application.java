package hm.moe.heignamerican.JettySandbox;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <ol>
 * <li>$ maven clean jetty:run -Djetty.port=XXXX</li>
 * <li>http://localhost:XXXX/sandbox?name1=value1&...</li>
 * </ol>
 */
@WebServlet(name = "main", urlPatterns = "/sandbox", loadOnStartup = 0)
public class Application extends HttpServlet {
	protected final Logger LOGGER = LoggerFactory.getLogger(Application.class);

	private static final String DEFAULT_ENCODING = "UTF-8";
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest aReqaRequest, HttpServletResponse aResponse) throws ServletException, IOException {
		show(toString("GET", aReqaRequest.getParameterMap()), aResponse);
	}

	@Override
	protected void doPost(HttpServletRequest aRequest, HttpServletResponse aResponse) throws ServletException, IOException {
		show(toString("POST", aRequest.getParameterMap()), aResponse);
	}

	private void show(String aString, HttpServletResponse aResponse) throws IOException {
		System.out.println(aString);

		aResponse.setContentType("text/plain");
		aResponse.setCharacterEncoding(DEFAULT_ENCODING);
		aResponse.getWriter().write(aString);
	}

	private String toString(String aMethod, Map<String, String[]> aParameterMap) {
		StringBuilder tBuilder = new StringBuilder();

		tBuilder.append("====== " + aMethod + " =========================================").append("\n");
		Set<Entry<String, String[]>> tEntrySet = aParameterMap.entrySet();
		for (Entry<String, String[]> tEntry : tEntrySet) {
			tBuilder.append(tEntry.getKey()).append("\n");
			for (String tString : tEntry.getValue()) {
				tBuilder.append("\t" + tString).append("\n");
			}
		}

		return tBuilder.toString();
	}
}
