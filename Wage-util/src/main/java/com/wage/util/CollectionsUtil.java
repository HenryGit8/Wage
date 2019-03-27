/**
 * @(#)CollectionsUtil.java 2016年9月7日
 *
 * Copyright ( c ) 2015 Wonders Information Co., Ltd. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of Wonders 
 * Information Co., Ltd. ("Confidential Information").  You shall not disclose 
 * such Confidential Information and shall use it only in accordance with the 
 * terms of the license agreement you entered into with Wonders Information  
 * Co., Ltd. or a Wonders authorized reseller (the "License Agreement"). Wonders 
 *  may make changes to the Confidential Information from time to time. Such  
 * Confidential Information may contain errors.
 *
 * EXCEPT AS EXPLICITLY SET FORTH IN THE LICENSE AGREEMENT, WONDERS DISCLAIMS ALL
 * WARRANTIES, COVENANTS, REPRESENTATIONS, INDEMNITIES, AND GUARANTEES WITH
 * RESPECT TO SOFTWARE AND DOCUMENTATION, WHETHER EXPRESS OR IMPLIED, WRITTEN OR
 * ORAL, STATUTORY OR OTHERWISE INCLUDING, WITHOUT LIMITATION, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY, TITLE, NON-INFRINGEMENT AND FITNESS FOR A
 * PARTICULAR PURPOSE. WONDERS DOES NOT WARRANT THAT END USER'S USE OF THE
 * SOFTWARE WILL BE UNINTERRUPTED, ERROR FREE OR SECURE.
 *
 * WONDERS SHALL NOT BE LIABLE TO END USER, OR ANY OTHER PERSON, CORPORATION OR
 * ENTITY FOR INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY OR CONSEQUENTIAL
 * DAMAGES, OR DAMAGES FOR LOSS OF PROFITS, REVENUE, DATA OR USE, WHETHER IN AN
 * ACTION IN CONTRACT, TORT OR OTHERWISE, EVEN IF WONDERS HAS BEEN ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGES. WONDERS' TOTAL LIABILITY TO END USER SHALL NOT
 * EXCEED THE AMOUNTS PAID FOR THE WONDERS SOFTWARE BY END USER DURING THE PRIOR
 * TWELVE (12) MONTHS FROM THE DATE IN WHICH THE CLAIM AROSE.  BECAUSE SOME
 * STATES OR JURISDICTIONS DO NOT ALLOW LIMITATION OR EXCLUSION OF CONSEQUENTIAL
 * OR INCIDENTAL DAMAGES, THE ABOVE LIMITATION MAY NOT APPLY TO END USER.
 *
 * Copyright version 1.0
 */
package com.wage.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;

import com.google.common.primitives.Doubles;
import com.google.common.primitives.Ints;
import com.google.common.primitives.Longs;

/**
 * Collections工具集.
 * 
 * 主要由三部分组成：
 * 
 * 1. 是否Empty的最常用函数.
 * 
 * 2. 支持以原始类型存储的List，节约存储空间, 基于Guava.
 * 
 * 2. 源自Apache Commons Collection, 争取不用在项目里引入它.
 * 
 * 3. 反射提取集合种元素及其属性的功能.
 * 
 * 在JDK的Collections和Guava的Collections2/Iterables后, 命名为Collections3.
 * 
 * 另请直接使用:
 * 
 * 1. JDK Collections的singletonList()/singletonMap()/emptyList()/emptyMap()
 * 
 * 2. Guava Lists/Maps的newArrayLists(Element...elements)等函数
 * 
 * @author calvin
 */
@SuppressWarnings("rawtypes")
public class CollectionsUtil
{

    /**
     * 判断是否为空.
     */
    public static boolean isEmpty(Collection collection)
    {
        return (collection == null) || collection.isEmpty();
    }

    /**
     * 判断是否为空.
     */
    public static boolean isEmpty(Map map)
    {
        return (map == null) || map.isEmpty();
    }

    /**
     * 判断是否不为空.
     */
    public static boolean isNotEmpty(Collection collection)
    {
        return (collection != null) && !(collection.isEmpty());
    }

    /**
     * 判断是否不为空.
     */
    public static boolean isNotEmpty(Map map)
    {
        return (map != null) && !(map.isEmpty());
    }

    /**
     * 取得Collection的第一个元素，如果collection为空返回null.
     */
    public static <T> T getFirst(Collection<T> collection)
    {
        if (isEmpty(collection))
        {
            return null;
        }

        return collection.iterator().next();
    }

    /**
     * 获取Collection的最后一个元素，如果collection为空返回null.
     */
    public static <T> T getLast(Collection<T> collection)
    {
        if (isEmpty(collection))
        {
            return null;
        }

        // 当类型List时，直接取得最后一个元素.
        if (collection instanceof List)
        {
            List<T> list = (List<T>) collection;
            return list.get(list.size() - 1);
        }

        // 其他类型通过iterator滚动到最后一个元素.
        Iterator<T> iterator = collection.iterator();
        while (true)
        {
            T current = iterator.next();
            if (!iterator.hasNext())
            {
                return current;
            }
        }
    }

    /**
     * 返回a+b的新List.
     */
    public static <T> List<T> union(final Collection<T> a, final Collection<T> b)
    {
        List<T> result = new ArrayList<T>(a);
        result.addAll(b);
        return result;
    }

    /**
     * 返回a-b的新List.
     */
    public static <T> List<T> subtract(final Collection<T> a, final Collection<T> b)
    {
        List<T> list = new ArrayList<T>(a);
        for (T element : b)
        {
            list.remove(element);
        }

        return list;
    }

    /**
     * 返回a与b的交集的新List.
     */
    public static <T> List<T> intersection(Collection<T> a, Collection<T> b)
    {
        List<T> list = new ArrayList<T>();

        for (T element : a)
        {
            if (b.contains(element))
            {
                list.add(element);
            }
        }
        return list;
    }

    /**
     * 返回一个底层由原始类型long保存的List, 与保存Long相比节约空间.
     */
    public static List<Long> asList(long... backingArray)
    {
        return Longs.asList(backingArray);
    }

    /**
     * 返回一个底层由原始类型int保存的List, 与保存Integer相比节约空间.
     */
    public static List<Integer> asList(int... backingArray)
    {
        return Ints.asList(backingArray);
    }

    /**
     * 返回一个底层由原始类型double保存的Double, 与保存Double相比节约空间.
     */
    public static List<Double> asList(double... backingArray)
    {
        return Doubles.asList(backingArray);
    }

    /**
     * 转换Collection所有元素(通过toString())为String, 中间以 separator分隔。
     */
    public static String convertToString(final Collection collection, final String separator)
    {
        return StringUtils.join(collection, separator);
    }

    /**
     * 转换Collection所有元素(通过toString())为String,
     * 每个元素的前面加入prefix，后面加入postfix，如<div>mymessage</div>。
     */
    public static String convertToString(final Collection collection, final String prefix, final String postfix)
    {
        StringBuilder builder = new StringBuilder();
        for (Object o : collection)
        {
            builder.append(prefix).append(o).append(postfix);
        }
        return builder.toString();
    }
}
