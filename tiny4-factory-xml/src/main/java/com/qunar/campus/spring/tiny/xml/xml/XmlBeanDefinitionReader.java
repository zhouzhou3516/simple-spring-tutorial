package com.qunar.campus.spring.tiny.xml.xml;

import com.qunar.campus.spring.tiny.xml.AbstractBeanDefinitionReader;
import com.qunar.campus.spring.tiny.xml.PropertyValue;
import com.qunar.campus.spring.tiny.xml.PropertyValues;
import com.qunar.campus.spring.tiny.xml.io.ResourceLoader;
import com.qunar.campus.spring.tiny.xml.support.BeanDefinition;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Description: xml reader
 *
 * 其实spring的这个类实现的非常精巧, 增加了许多可供扩展的地方。
 * 针对不同的标签实现不同的解析功能
 *
 * @author yushen.ma
 * @version 2015-03-16 21:28
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

	public XmlBeanDefinitionReader(ResourceLoader resourceLoader) {
		super(resourceLoader);
	}

	@Override
	public void loadBeanDefinitions(String location) {
        try {
            InputStream inputStream = getResourceLoader().getResource(location).getInputStream();
            doLoadBeanDefinitions(inputStream);
        } catch (Throwable t) {
            throw new IoCXMLFileLoadException(t);
        }
	}

	protected void doLoadBeanDefinitions(InputStream inputStream) throws ParserConfigurationException, IOException, SAXException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = factory.newDocumentBuilder();
		Document doc = docBuilder.parse(inputStream);
		// 解析bean
		registerBeanDefinitions(doc);
		inputStream.close();
	}

	public void registerBeanDefinitions(Document doc) {
		Element root = doc.getDocumentElement();
		parseBeanDefinitions(root);
	}

	protected void parseBeanDefinitions(Element root) {
		NodeList nl = root.getChildNodes();
		for (int i = 0; i < nl.getLength(); i++) {
			Node node = nl.item(i);
			if (node instanceof Element) {
				Element ele = (Element) node;
				processBeanDefinition(ele);
			}
		}
	}

	protected void processBeanDefinition(Element ele) {
		String name = ele.getAttribute("name");
		String className = ele.getAttribute("class");
        BeanDefinition beanDefinition = new BeanDefinition();
        processProperty(ele,beanDefinition);
        beanDefinition.setBeanClassName(className);
		getRegistry().put(name, beanDefinition);
	}

    protected void processProperty(Element ele,BeanDefinition beanDefinition) {
        NodeList propertyNode = ele.getElementsByTagName("property");
        for (int i = 0; i < propertyNode.getLength(); i++) {
            Node node = propertyNode.item(i);
            if (node instanceof Element) {
                Element propertyEle = (Element) node;
                String name = propertyEle.getAttribute("name");
                String value = propertyEle.getAttribute("value");
                if (beanDefinition.getPropertyValues() == null) {
                    beanDefinition.setPropertyValues(new PropertyValues());
                }
                beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name,value));
            }
        }
    }

}
