package com.qunar.campus.spring.tiny.xml.xml;

import com.qunar.campus.spring.tiny.xml.PropertyValue;
import com.qunar.campus.spring.tiny.xml.PropertyValues;
import com.qunar.campus.spring.tiny.xml.io.ResourceLoader;
import com.qunar.campus.spring.tiny.xml.support.BeanDefinition;
import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Description: XmlBeanRefSupportReader
 *
 * @author yushen.ma
 * @version 2015-03-20 10:30
 */
public class XmlBeanRefSupportReader extends XmlBeanDefinitionReader {

    public XmlBeanRefSupportReader(ResourceLoader resourceLoader) {
        super(resourceLoader);
    }

    /**
     * 增加了一个对ref 的解析
     * @param ele element
     * @param beanDefinition beanDefinition
     */
    protected void processProperty(Element ele,BeanDefinition beanDefinition) {
        NodeList propertyNode = ele.getElementsByTagName("property");
        for (int i = 0; i < propertyNode.getLength(); i++) {
            Node node = propertyNode.item(i);
            if (node instanceof Element) {
                Element propertyEle = (Element) node;
                String name = propertyEle.getAttribute("name");
                String value = propertyEle.getAttribute("value");
                String ref = propertyEle.getAttribute("ref");
                if (beanDefinition.getPropertyValues() == null) {
                    beanDefinition.setPropertyValues(new PropertyValues());
                }
                PropertyValue property = new PropertyValue(name, StringUtils.trimToNull(value), StringUtils.trimToNull(ref));
                beanDefinition.getPropertyValues().addPropertyValue(property);
            }
        }
    }

}
