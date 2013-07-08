package br.com.kamaleon.relatorio;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RelatorioServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/META-INF/spring/applicationContext.xml");

		DataSource dataSource = (DataSource) context.getBean("dataSource");
		Connection conn = null;
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		
		
		try {
			parametros.put("id", Long.parseLong(request.getParameter("id")));
			System.out.println(getServletContext().getRealPath("")+" Hello World "+request.getParameter("id"));
			conn = dataSource.getConnection();

			File reportFile = new File(getServletContext().getRealPath("")+"/META-INF/report.jasper");

			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(reportFile);

			//this.preencherRelatorio(request);

			JasperPrint jasperPrint = null;


			response.setContentType("application/pdf");
			jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, conn);
			
			System.out.println(jasperPrint);
			
			JasperExportManager.exportReportToPdfStream(jasperPrint,response.getOutputStream());
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
