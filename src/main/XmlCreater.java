package main;

import java.io.File;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XmlCreater {

	public static boolean create(String name, List<List<String>> records) {

		try {
			// Create a parser factory
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = factory.newDocumentBuilder();
			Document document = db.newDocument();
			document.setXmlStandalone(true);

			// Write in data
			Element container = document.createElement("container");

			// first Line become the type of data in group
			List<String> types = records.get(0);

			for (int i = 1; i < records.size(); i++) {
				// remove line without key data
				if (!records.get(i).get(0).equals("")) {

					Element group = document.createElement("group");
					group.setAttribute("id", String.valueOf(i));

					for (int j = 0; j < types.size(); j++) {
						// remove data without type
						if (!types.get(j).equals("")) {

							Element data = document.createElement("data");
							data.setAttribute("type", types.get(j));
							data.setTextContent(records.get(i).get(j));
							group.appendChild(data);

						}
					}

					container.appendChild(group);
				}
			}

			document.appendChild(container);

			// Create TransformerFactory Class
			TransformerFactory tff = TransformerFactory.newInstance();
			// Create Transformer Class
			Transformer tf = tff.newTransformer();

			// Whether the output content uses line break
			tf.setOutputProperty(OutputKeys.INDENT, "yes");
			// Create xml file and write content
			tf.transform(new DOMSource(document), new StreamResult(new File("xml_" + name + ".xml")));

			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
