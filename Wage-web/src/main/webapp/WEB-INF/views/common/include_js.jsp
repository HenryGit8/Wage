<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="jsRoot" value="${ctx}${'/static/js'}"/>
<c:set var="jsModulesRoot" value="${ctx}${'/static/js-modules'}"/>
<c:set var="cssRoot" value="${ctx}${'/static/css'}"/>
<c:set var="imgRoot" value="${ctx}${'/static/img'}"/>
<script>
    window.ctx = '${ctx}';
    window.jsRoot = '${jsRoot}';
    window.jsModulesRoot = '${jsModulesRoot}';
    window.cssRoot = '${cssRoot}';
    window.imgRoot = '${imgRoot}';
</script>