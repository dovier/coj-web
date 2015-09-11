<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer = "16kb" autoFlush="true" %>


<h2 class="postheader">
    <fmt:message key="page.addinstitution.header"/>
</h2>
<div class="postcontent">

    <form:form method="post" commandName="institution" enctype="multipart/form-data"
			 cssClass="form-horizontal">
        <table class="createnewuser">
            <tbody>
                <tr>
                    <td style="align:right"><fmt:message key="page.addcountry.name"/><i class="fa fa-asterisk"></i></td>
                    <td><form:input path="name" size="30" maxlength="70" /></td>
                    <td><span class="label label-danger"><form:errors path="name" /></span></td>
                </tr>

                <tr>
                    <td style="align:right"><fmt:message key="page.addinstitution.acronym"/><i class="fa fa-asterisk"></i></td>
                    <td><form:input path="zip" size="30" maxlength="12" /></td>
                    <td><span class="label label-danger"><form:errors path="zip" /></span></td>
                </tr>
                
                                <!-- INSTITUTION LOGO-->
                
                <tr>
                    <td style="align:right">Logo (28x28, &lt;35KB)<i class="fa fa-asterisk"></i></td>
                    <td><input id="logo" size="30" name="logo" type="file" class="file"
							data-show-upload="false" accept="image/*"
							data-show-caption="true"></td>
                    <td><span class="label label-danger"><form:errors path="name" /></span></td>
                </tr> 
                
                <tr>
                    <td style="align:right">Website<i class="fa fa-asterisk"></i></td>
                    <td><form:input path="website"/></td>
                    <td><span class="label label-danger"><form:errors path="website" /></span></td>
                </tr>

                <tr>
                    <td style="align:right"><fmt:message key="judge.register.country"/><i class="fa fa-asterisk"></i></td>
                    <td>
                        <form:select path="country_id">
                            <form:option value="0">
                                <fmt:message key="judge.register.select"/>
                            </form:option>
                            <form:options items="${countries}" itemLabel="name" itemValue="id"/>
                        </form:select>
                    </td>
                    <td><span class="label label-danger"><form:errors path="country_id" /></span></td>
                </tr>

                <tr>
                    <td><input type="submit" name="submit" id="submit" value="<fmt:message key="judge.register.submit.value"/>" /></td>
                    <td><input type="reset" name="submit" id="submit" value="<fmt:message key="judge.register.reset.value"/>" /></td>
                    <td></td>
                </tr>
            </tbody>
        </table>
    </form:form>    
</div>

<script>
	$("#logo").fileinput({
		maxFileSize : 35,
		msgProgress : 'Loading {percent}%',
		previewClass : 'avatar_preview',
		previewFileType : "image",
		browseClass : "btn btn-primary",
		browseLabel : "Pick Image",
		browseIcon : '<i class="fa fa-picture-o"></i>&nbsp;',
		removeClass : "btn btn-default",
		removeLabel : "Delete",
		removeIcon : '<i class="fa fa-trash"></i>'
	});
	$("[data-toggle='tooltip']").tooltip();
</script>