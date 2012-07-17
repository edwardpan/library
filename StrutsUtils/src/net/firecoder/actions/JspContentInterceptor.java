/* WebInfInterceptor.java
 * project: EnvirLims
 */
package net.firecoder.actions;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.config.entities.ResultConfig;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * @author �˳�
 * @created 2011-8-5
 */
public class JspContentInterceptor implements Interceptor {
	private static final String LOCATION = "location";
	private String content = "/WEB-INF/";
	
    public void setContent(String content) {
    	if (content == null) {
    		return;
    	}
    	if (content.endsWith("/")) {
    		content = content.substring(0, content.length() - 1);
        }
		this.content = content;
	}

	@Override
    public void destroy() {
    }

    @Override
    public void init() {
    }

    @Override
    public String intercept(ActionInvocation acIn) throws Exception {
        Map<String, ResultConfig> map = acIn.getProxy().getConfig().getResults();

        Iterator<ResultConfig> iter = map.values().iterator();
        while ( iter.hasNext()) {
            ResultConfig rcg = (ResultConfig) iter.next();
            transferLocation(rcg);
        }

        return acIn.invoke();
    }

    public void transferLocation(ResultConfig rcg) throws Exception {
        Map<String, String> map = rcg.getParams();
        if (!map.containsKey(LOCATION)) {
            return;
        }

        String location = (String) map.get(LOCATION);
        if (location.startsWith(content)) {
            return;
        }

        if (!location.startsWith("/")) {
            location = "/" + location;
        }
        
        location = content + location;
        // rcg.getParams()���ص�Map��һ����������Ĳ������޸ĵ�Collections.UnmodifableMap��
        // ʹ�÷���ȡ�ñ������ԭʼMap���޸�
		try {
			Field mField = map.getClass().getDeclaredField("m");
			mField.setAccessible(true);
			Object mapData = mField.get(map);
			Class mapC = mapData.getClass();
			Method mapPutM = mapC.getMethod("put", Object.class, Object.class);
			mapPutM.invoke(mapData, LOCATION, location);// ���޸ĺ��locationֵ�Żز���Map����
		} catch (Exception e) {
			throw e;
		}
    }
}
