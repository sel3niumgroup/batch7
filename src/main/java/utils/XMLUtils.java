package utils;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class XMLUtils {
	
	public static Document getDocument(String fileName) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(fileName);
		return doc;
	}
	
	public static List<String> evaluateXpath(Document document,String xpathExpression){
		XPathFactory xpathFactory = XPathFactory.newInstance();
		XPath xpath = xpathFactory.newXPath();
		List<String> values = new ArrayList<>();
		try {
			XPathExpression expr = xpath.compile(xpathExpression);
			NodeList nodes = (NodeList) expr.evaluate(document, XPathConstants.NODESET);
			for (int i = 0; i < nodes.getLength(); i++) {
				values.add(nodes.item(i).getNodeValue());
			}
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		return values;
	}
	
	@SuppressWarnings("resource")
	public static void CSVWriter(List<String> XmlFiles1, List<String> XmlFiles2, String filePath,List<Entry<String,List<Pojo>>> list) throws Exception {
		 BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath), StandardOpenOption.APPEND,
				 StandardOpenOption.CREATE);
		 CSVPrinter csvPrinter = null;
		 
		 csvPrinter = new CSVPrinter(writer,
				 CSVFormat.DEFAULT.withHeader("PolicyNo","VehicleId"));
		 
		 for (int i = 0; i < XmlFiles1.size(); i++) {
			 try {
				Document xml1 = getDocument(XmlFiles1.get(i));
				 Document xml2 = getDocument(XmlFiles2.get(i));
				 String xpath = "//Policy/ContractNumber/text()";
				 
				 List<String> policyIdList = evaluateXpath(xml1, xpath);
				 List<Pojo> l = list.get(i).getValue();
				 List<String> vehicleNumList = new ArrayList<>();
				 for (Pojo pojo : l) {
					 vehicleNumList.add(pojo.getVehicle_Id());
				}
				 
				 for (int j = 0; j < policyIdList.size(); j++) {
					 csvPrinter.printRecord((j<policyIdList.size())?policyIdList.get(j):policyIdList.get(0),
							 (j<vehicleNumList.size())?vehicleNumList.get(j):vehicleNumList.get(0));
				}
				 csvPrinter.flush();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
