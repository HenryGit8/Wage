package test.wage;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.ResultActions;
import test.wage.generic.GenericControllerTest;


public class TestT extends GenericControllerTest {

    
	@Test
    public void test() throws Exception {
		

        Map<String, Object> params = new HashMap<String, Object>();
        
        params.put("empId", "100001");
        params.put("password", "123");

        ResultActions resultActions = super.getResultActions("/login/getLoginMan", params, "",HttpMethod.POST);

        MockHttpServletResponse httpServletResponse = resultActions.andReturn().getResponse();
        System.out.println(httpServletResponse.getContentAsString());

    }

	@Test
    public void test2() throws Exception {


        Map<String, Object> params = new HashMap<String, Object>();



       params.put("medId", "13709 ");
       params.put("regTradeMark", "fd66d");
       
        ResultActions resultActions = super.getResultActions("/drugs/cloudMed/saveMedCert", params, "",HttpMethod.POST);

     

        
        MockHttpServletResponse httpServletResponse = resultActions.andReturn().getResponse();
        System.out.println(httpServletResponse.getContentAsString());
        

    }

}
