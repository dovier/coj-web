package cu.uci.coj.utils.paging;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class PaginatedArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.getParameterType().equals(PagingOptions.class);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
			String pageNum = webRequest.getParameter("page"); 
			Integer page = Integer.parseInt(pageNum == null?"1":pageNum);
			String direction = webRequest.getParameter("dir");
			String sort = webRequest.getParameter("sort");
			return new PagingOptions(page,direction,sort);
	}

}



