package com.qunar.campus.spring.tiny.xml;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.List;
import java.util.Set;

/**
 * Description: PropertyValues
 *
 * @author yushen.ma
 * @version 2015-03-16 21:28
 */
public class PropertyValues {

    private final Set<PropertyValue> propertyValueList = Sets.newHashSet();

    public PropertyValues() { }

    public void addPropertyValue(PropertyValue pv) {
        this.propertyValueList.add(pv);// distinct
    }

    public List<PropertyValue> getPropertyValues() {
        return Lists.newArrayList(this.propertyValueList);
    }

}
