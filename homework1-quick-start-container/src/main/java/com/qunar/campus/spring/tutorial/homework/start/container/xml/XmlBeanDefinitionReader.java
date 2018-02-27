package com.qunar.campus.spring.tutorial.homework.start.container.xml;


import com.qunar.campus.spring.tutorial.homework.start.container.beans.BeanDefinition;
import com.qunar.campus.spring.tutorial.homework.start.container.beans.BeanReference;
import com.qunar.campus.spring.tutorial.homework.start.container.beans.PropertyValue;
import com.qunar.campus.spring.tutorial.homework.start.container.io.ResourceLoader;
import java.io.InputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Created by liqingzhou on 17/7/21.
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public XmlBeanDefinitionReader(ResourceLoader resourceLoader) {
        super(resourceLoader);
    }

    @Override
    public void loadBeanDefinitions(String location) throws Exception {
        InputStream inputStream = getResourceLoader().getResource(location).getInputStream();
        doLoadBeanDefinitions(inputStream);
    }

    protected void doLoadBeanDefinitions(InputStream inputStream) throws Exception {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = factory.newDocumentBuilder();
            Document doc = docBuilder.parse(inputStream);
            // 解析bean
            registerBeanDefinitions(doc);
        } finally {

            inputStream.close();
        }
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
        String id = ele.getAttribute("id");
        String className = ele.getAttribute("class");
        String initMethod = ele.getAttribute("init-method");
        BeanDefinition beanDefinition = new BeanDefinition();
        beanDefinition.setBeanId(id);
        processProperty(ele, beanDefinition);
        beanDefinition.setBeanClassName(className);
        beanDefinition.setInitMethod(initMethod);
        getRegistry().add(beanDefinition);

    }

    private void processProperty(Element ele, BeanDefinition beanDefinition) {
        NodeList propertyNode = ele.getElementsByTagName("property");
        for (int i = 0; i < propertyNode.getLength(); i++) {
            Node node = propertyNode.item(i);
            if (node instanceof Element) {
                Element propertyEle = (Element) node;
                String name = propertyEle.getAttribute("name");
                String value = propertyEle.getAttribute("value");
                if (value != null && value.length() > 0) {
                    beanDefinition.getPropertyValues()
                            .addPropertyValue(new PropertyValue(name, value));
                } else {
                    String ref = propertyEle.getAttribute("ref");
                    if (ref == null || ref.length() == 0) {
                        throw new IllegalArgumentException(
                                "Configuration problem: <property> element for property '"
                                        + name + "' must specify a ref or value");
                    }
                    BeanReference beanReference = new BeanReference(ref);
                    beanDefinition.getPropertyValues()
                            .addPropertyValue(new PropertyValue(name, beanReference));
                }
            }
        }
    }
}
