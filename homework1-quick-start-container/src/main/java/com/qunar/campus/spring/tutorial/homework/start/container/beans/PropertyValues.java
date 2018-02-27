package com.qunar.campus.spring.tutorial.homework.start.container.beans;

import com.google.common.collect.Lists;
import java.util.List;

/**
 * Created by liqingzhou on 17/7/21.
 */
public class PropertyValues {

    List<PropertyValue> propertyValueList = Lists.newArrayList();

    public void addPropertyValue(PropertyValue pv) {
        this.propertyValueList.add(pv);
    }

    public List<PropertyValue> getPropertyValues() {
        return this.propertyValueList;
    }

}
