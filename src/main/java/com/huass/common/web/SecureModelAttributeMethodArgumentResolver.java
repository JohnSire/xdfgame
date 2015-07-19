package com.huass.common.web;

import java.lang.annotation.Annotation;
import java.util.Collections;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.core.Conventions;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.mvc.method.annotation.ExtendedServletRequestDataBinder;

import com.huass.common.utils.Reflections;


public class SecureModelAttributeMethodArgumentResolver implements HandlerMethodArgumentResolver {

	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(SecureModelAttributeMethodArgumentResolver.class);

	private final boolean annotationNotRequired;

	public SecureModelAttributeMethodArgumentResolver(boolean annotationNotRequired) {
		this.annotationNotRequired = annotationNotRequired;
	}

	@Override
	public final boolean supportsParameter(MethodParameter parameter) {
		if (parameter.hasParameterAnnotation(SecureModelAttribute.class)) {
			return true;
		} else if (this.annotationNotRequired) {
			return !BeanUtils.isSimpleProperty(parameter.getParameterType());
		} else {
			return false;
		}
	}

	@Override
	public final Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
	        NativeWebRequest request, WebDataBinderFactory binderFactory) throws Exception {
		SecureModelAttribute annot = parameter.getParameterAnnotation(SecureModelAttribute.class);
		String attrName = (annot != null) ? annot.value() : null;
		String name = StringUtils.hasText(attrName) ? attrName : Conventions.getVariableNameForParameter(parameter);
		Object attribute = (mavContainer.containsAttribute(name)) ? mavContainer.getModel().get(name)
		        : createAttribute(name, parameter, binderFactory, request);
		
		String[] clearFields = annot.clearFiled();
		for (String clearField : clearFields) {
			if (StringUtils.hasText(clearField)) {
				Reflections.invokeMethod(Reflections.getFieldValue(attribute, clearField), "clear", null, null);
			}
		}
		WebDataBinder binder = (ExtendedServletRequestDataBinder) binderFactory.createBinder(request, attribute, name);
		if (binder.getTarget() != null) {
			if (annot != null) {
				String[] allowedFields = annot.allowedField();
				String[] deniedFields = annot.deniedField();
				binder.setAllowedFields(allowedFields);
				binder.setDisallowedFields(deniedFields);
			}
			bindRequestParameters(binder, request);
			validateIfApplicable(binder, parameter);
			if (binder.getBindingResult().hasErrors()) {
				if (isBindExceptionRequired(binder, parameter)) {
					throw new BindException(binder.getBindingResult());
				}
			}
		}
		Map<String, Object> bindingResultModel = binder.getBindingResult().getModel();
		mavContainer.removeAttributes(bindingResultModel);
		mavContainer.addAllAttributes(bindingResultModel);

		return binder.getTarget();
	}

	private Object createAttribute(String attributeName, MethodParameter parameter, WebDataBinderFactory binderFactory,
	        NativeWebRequest request) throws Exception {
		String value = getRequestValueForAttribute(attributeName, request);
		if (value != null) {
			Object attribute = createAttributeFromRequestValue(value, attributeName, parameter, binderFactory, request);
			if (attribute != null) {
				return attribute;
			}
		}
		return BeanUtils.instantiateClass(parameter.getParameterType());
	}
	private String getRequestValueForAttribute(String attributeName, NativeWebRequest request) {
		Map<String, String> variables = getUriTemplateVariables(request);
		if (StringUtils.hasText(variables.get(attributeName))) {
			return variables.get(attributeName);
		} else if (StringUtils.hasText(request.getParameter(attributeName))) {
			return request.getParameter(attributeName);
		} else {
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	private Map<String, String> getUriTemplateVariables(NativeWebRequest request) {
		Map<String, String> variables = (Map<String, String>) request.getAttribute(
		        HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE, RequestAttributes.SCOPE_REQUEST);
		return (variables != null) ? variables : Collections.<String, String> emptyMap();
	}
	private Object createAttributeFromRequestValue(String sourceValue, String attributeName, MethodParameter parameter,
	        WebDataBinderFactory binderFactory, NativeWebRequest request) throws Exception {
		DataBinder binder = binderFactory.createBinder(request, null, attributeName);
		ConversionService conversionService = binder.getConversionService();
		if (conversionService != null) {
			TypeDescriptor source = TypeDescriptor.valueOf(String.class);
			TypeDescriptor target = new TypeDescriptor(parameter);
			if (conversionService.canConvert(source, target)) {
				return binder.convertIfNecessary(sourceValue, parameter.getParameterType(), parameter);
			}
		}
		return null;
	}
	private void bindRequestParameters(WebDataBinder binder, NativeWebRequest request) {
		ServletRequest servletRequest = request.getNativeRequest(ServletRequest.class);
		ServletRequestDataBinder servletBinder = (ServletRequestDataBinder) binder;
		servletBinder.bind(servletRequest);
	}
	private void validateIfApplicable(WebDataBinder binder, MethodParameter parameter) {
		Annotation[] annotations = parameter.getParameterAnnotations();
		for (Annotation annot : annotations) {
			if (annot.annotationType().getSimpleName().startsWith("Valid")) {
				Object hints = AnnotationUtils.getValue(annot);
				binder.validate(hints instanceof Object[] ? (Object[]) hints : new Object[] { hints });
				break;
			}
		}
	}
	private boolean isBindExceptionRequired(WebDataBinder binder, MethodParameter parameter) {
		int i = parameter.getParameterIndex();
		Class<?>[] paramTypes = parameter.getMethod().getParameterTypes();
		boolean hasBindingResult = (paramTypes.length > (i + 1) && Errors.class.isAssignableFrom(paramTypes[i + 1]));
		return !hasBindingResult;
	}

}
