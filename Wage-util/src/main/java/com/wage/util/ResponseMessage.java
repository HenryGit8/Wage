/**
 * @(#)ResponseMessage.java 2016-9-29
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

/**
 * @Title ResponseMessage.java 
 * @Package com.kingtsoft.study.util
 * @Description TODO
 * @author zhouning@wondersgroup.com
 * @date 2016-9-29
 * @version v1.0
 */
package com.wage.util;

/**
 * @ClassName: ResponseMessage
 * @date 2016-9-29
 * 
 */
public class ResponseMessage
{
    public static final int STATUS_SUCCESS = 1;
    public static final int STATUS_FAILURE = 0;

    // 是否成功 //1成功 0失败
    private int status;

    // 说明
    private String message;

    // 返回结果
    private Object result;

    public int getStatus()
    {
        return status;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public Object getResult()
    {
        return result;
    }

    public void setResult(Object result)
    {
        this.result = result;
    }
}
