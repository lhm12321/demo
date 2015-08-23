package com.jfinal.ext.xml;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class LoadConfigXml {

	public static boolean loadConfigXml(String path) {
		try {
			File f = new File(path);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(f);
			NodeList nodeList = doc.getElementsByTagName("section");
			for(int i=0;i<nodeList.getLength();i++){
				Element section = (Element)nodeList.item(i);
				if(section.getAttribute("name").equals("core")){
					NodeList web = section.getElementsByTagName("entry");
					for(int j=0;j<web.getLength();j++){
						Element e= (Element)web.item(j);
						if(e.getAttribute("key").equals("Product.Debug")){
							if(e.getAttribute("value").equals("false")){
								return false;
							}else{
								return true;
							}
						}
					}
				}
			}
		} catch (Exception e) {
			return true;
			// TODO: handle exception
		}
		return true;
	}
}
