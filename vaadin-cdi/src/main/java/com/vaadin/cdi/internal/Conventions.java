/*
 * Copyright 2000-2013 Vaadin Ltd.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.vaadin.cdi.internal;

import com.vaadin.cdi.CDIUI;
import com.vaadin.cdi.CDIView;

public class Conventions {

     public static String deriveMappingForUI(Class<?> beanClass) {
        if (beanClass.isAnnotationPresent(CDIUI.class)) {
            CDIUI annotation = beanClass.getAnnotation(CDIUI.class);
            String mapping = annotation.value();
            if (mapping != null && !CDIUI.USE_CONVENTIONS.equals(mapping)) {
                return mapping;
            } else {
                // derive mapping from classname
                mapping = beanClass.getSimpleName().replaceFirst("UI$", "");
                return upperCamelToLowerHyphen(mapping);
            }
        } else {
            return null;
        }
    }

    public static String deriveMappingForView(Class<?> beanClass) {
        if (beanClass.isAnnotationPresent(CDIView.class)) {
            CDIView annotation = beanClass.getAnnotation(CDIView.class);
            if (annotation != null
                    && !CDIView.USE_CONVENTIONS.equals(annotation.value())) {
                return annotation.value();
            } else {
                String mapping = beanClass.getSimpleName().replaceFirst(
                        "View$", "");
                return upperCamelToLowerHyphen(mapping);
            }
        } else {
            return null;
        }
    }
    
    public static String upperCamelToLowerHyphen(String string) {
        StringBuilder sb = new StringBuilder();
        int startOfWord = 0;
        int endOfWord = -1;
        int i = 1;
        while(i <= string.length()) {
            if(i == string.length() || Character.isUpperCase(string.charAt(i))) {
                endOfWord = i;
                if(sb.length() != 0) {
                    sb.append('-');
                }
                sb.append(string.substring(startOfWord, endOfWord).toLowerCase());
                startOfWord = i;
            }
            ++i;
        }
        return sb.toString();
    }
}
