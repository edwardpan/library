package net.firecoder.actions;

import java.lang.reflect.Field;

import net.firecoder.utils.ReflectionUtils;

import org.apache.commons.lang.StringUtils;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.util.CompoundRoot;
import com.opensymphony.xwork2.util.ValueStack;

/**
 * Result type Ϊchainʱ ��ͨ��ע��ķ�ʽʵ�ֲ������� �˲���Ϊǰ��Action�ĳ�Ա���������ṩgetter����
 * �˲�������Ҫ��һ��Ҫ��ֵջ��
 * 
 * @author liming
 */
public class ChainParameterInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = -8279316685527646358L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ValueStack stack = invocation.getStack();
		CompoundRoot root = stack.getRoot();

		// ֵջ��Ϊnull ���Ѿ���ǰ��Action
		// ջ���(index = 0)Ϊ��ǰAction��������(index = 1) Ϊǰ��Action
		if (root == null || root.size() <= 2) {
			return invocation.invoke();
		}

		// ��ǰAction����
		Object target = invocation.getAction();

		Field[] fields = target.getClass().getDeclaredFields();

		// ������Action��������� �Ƿ���RecieveDataע��
		for (Field field : fields) {
			if (field.isAnnotationPresent(ChainTransParam.class)) {
				ChainTransParam rData = field.getAnnotation(ChainTransParam.class);
				// ȡ��Դ�����ֶ���
				String fromName = rData.fieldName();
				fromName = StringUtils.isEmpty(fromName) ? field.getName() : fromName;

				// ȡ�������ǰ��Action
				Object srcAction = root.get(1);

				// ȡ�ö�Ӧ�ֶε�ֵ
				Object value = ReflectionUtils.getFieldValue(srcAction, srcAction.getClass(), field.getName());
				// �趨ֵ
				ReflectionUtils.setFieldValue(target, field.getName(), field.getType(), value);
			}
		}

		return invocation.invoke();
	}

	@SuppressWarnings("unused")
	private Object findFieldValue(CompoundRoot root, Field field) {
		Object value = null;

		int size = root.size();

		// ��˳�����ǰ��Action
		for (int index = 1; index < size; index++) {
			Object srcAction = root.get(index);

			Object tmp = ReflectionUtils.getFieldValue(srcAction, srcAction.getClass(), field.getName());
			// ȡ�ö�Ӧ�ֶε�ֵ �򷵻�
			// ���⣺���ǰ��Action�и��ֶα����Ϊnull ���޷�����
			if (tmp != null) {
				break;
			}
		}

		return value;
	}
}
