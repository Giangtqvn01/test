package com.example.actvn.util;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleDocxReportConfiguration;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

import javax.naming.NamingException;
import java.io.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@SuppressWarnings({"unchecked", "rawtypes"})
public class ReportUtils {
    private final static Logit LOG = Logit.getInstance(ReportUtils.class);

    public static byte[] generateReportDocx(List<?> dataSource, Map<String, Object> parameters, JasperReport jasperReport) throws JRException {
        JRBeanCollectionDataSource beanDataSource = new JRBeanCollectionDataSource(dataSource);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanDataSource);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        JRDocxExporter exporter = new JRDocxExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(baos));
        SimpleDocxReportConfiguration config = new SimpleDocxReportConfiguration();
        exporter.setConfiguration(config);
        exporter.exportReport();
        return baos.toByteArray();
    }

    public static byte[] generateReportExcels(List<?> dataSource, Map<String, Object> parameters, JasperReport jasperReport, String[] sheetNames) throws JRException {
        JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataSource);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);
        JRXlsxExporter exporter = new JRXlsxExporter();
        SimpleXlsxReportConfiguration reportConfigXLS = new SimpleXlsxReportConfiguration();
        reportConfigXLS.setSheetNames(sheetNames);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        exporter.setConfiguration(reportConfigXLS);
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(baos));
        exporter.exportReport();
        return baos.toByteArray();
    }

    public static JasperReport getCompiledFile(String jasperPath, String jrxmlPath) throws JRException {

        File reportFile = new File(jasperPath);
        // If compiled file is not found, then compile XML template
        if (!reportFile.exists()) {
            JasperCompileManager.compileReportToFile(jrxmlPath, jasperPath);
        }
        BufferedInputStream bufferedInputStream = null;
        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(reportFile.getPath()));
        } catch (FileNotFoundException e) {
            LOG.error("FileNotFoundException " + e);
        }

        return (JasperReport) JRLoader.loadObject(bufferedInputStream);
    }

    public static byte[] generateReportPDF(List<?> dataSource, Map parameters, JasperReport jasperReport)
        throws JRException, NamingException, SQLException, IOException {
        byte[] bytes = null;
        jasperReport.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);
        JRBeanCollectionDataSource beanDataSource = new JRBeanCollectionDataSource(dataSource);
        bytes = JasperRunManager.runReportToPdf(jasperReport, parameters, beanDataSource);
        return bytes;
    }

    public static byte[] generateReportPDF(Map parameters, JasperReport jasperReport)
        throws JRException, NamingException, SQLException, IOException {
        byte[] bytes = null;
        bytes = JasperRunManager.runReportToPdf(jasperReport, parameters);
        return bytes;
    }


}
