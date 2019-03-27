/**
 * @(#)MapUtil.java 2016年9月7日
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


import java.util.HashMap;
import java.util.Map;


/**
 * 将JSONParam数组转化为Map类型
 */
public class MapUtil
{
    public static Map<String, String> arrayToMap(JSONParam vo[])
    {
        Map<String, String> map = new HashMap<String, String>();
        for (JSONParam item : vo)
        {
            map.put(item.getName(), item.getValue());
        }
        return map;
    }
    
    
    public static Map<String, Object> arrayToMapObj(JSONParam vo[])
    {
        Map<String, Object> map = new HashMap<String, Object>();
        for (JSONParam item : vo)
        {
            map.put(item.getName(), item.getValue());
        }
        return map;
    }
}
