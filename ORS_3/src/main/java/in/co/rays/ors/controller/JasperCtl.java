package in.co.rays.ors.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.connection.ConnectionProvider;
import org.hibernate.engine.SessionFactoryImplementor;

import in.co.rays.ors.util.HibDataSource;
import in.co.rays.ors.util.JDBCDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

@WebServlet(name = "JasperCtl", urlPatterns = "/ctl/JasperCtl")
public class JasperCtl extends BaseCtl {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		doGet(req, resp);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("====================>>>>>>>>>>>>>>>>>>...sinside jasper ctl");

		Connection conn = null;
		JasperReport jr = null;
		JasperDesign jd = null;
		try {
			ResourceBundle rb = ResourceBundle.getBundle("in.co.rays.ors.bundle.system");
			String database = rb.getString("DATABASE");
			if ("HIBERNATE".equalsIgnoreCase(database)) {
				Session session = HibDataSource.getSession();
				SessionFactoryImplementor sessionFactoryImplementation = (SessionFactoryImplementor) session
						.getSessionFactory();
				ConnectionProvider connectionProvider = sessionFactoryImplementation.getConnectionProvider();
				try {
					conn = connectionProvider.getConnection();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if ("JDBC".equalsIgnoreCase(database)) {
				conn = JDBCDataSource.getConnection();
			}
			Map map = new HashMap();
			String path = getServletContext().getRealPath("/WEB-INF/");
			jd = JRXmlLoader.load(path + "/Marksheet1.jrxml");
			jr = JasperCompileManager.compileReport(jd);

			byte[] byteStream = JasperRunManager.runReportToPdf(jr, map, conn);
			java.io.OutputStream os = response.getOutputStream();
			response.setContentType("application/pdf");
			response.setContentLength(byteStream.length);
			os.write(byteStream, 0, byteStream.length);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected String getView() {

		return ORSView.JESPER_CTL;
	}

}
