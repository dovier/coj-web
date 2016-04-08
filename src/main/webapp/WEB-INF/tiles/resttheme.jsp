<%@page import="cu.uci.coj.utils.Utils"%>
<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>COJ: Rest Documentation</title>
  <link rel="shortcut icon"
          href="<c:url value="/images/coj_favicon.png"/>"/>
  
  <meta name="description" content="Caribbean Online Judge (COJ)"/>

  <link href='/css/rest/typography.css' media='screen' rel='stylesheet' type='text/css'/>
  <link href='/css/rest/reset.css' media='screen' rel='stylesheet' type='text/css'/>
  <link href='/css/rest/screen.css' media='screen' rel='stylesheet' type='text/css'/>
  <link href='/css/rest/reset.css' media='print' rel='stylesheet' type='text/css'/>
  <link href='/css/rest/print.css' media='print' rel='stylesheet' type='text/css'/>
  

  <script src="<c:url value="/js/rest/lib/jquery-1.8.0.min.js" />" type='text/javascript'></script>
  <script src='/js/rest/lib/jquery.slideto.min.js' type='text/javascript'></script>
  <script src='/js/rest/lib/jquery.wiggle.min.js' type='text/javascript'></script>
  <script src='/js/rest/lib/jquery.ba-bbq.min.js' type='text/javascript'></script>
  <script src='/js/rest/lib/handlebars-2.0.0.js' type='text/javascript'></script>
  <script src='/js/rest/lib/js-yaml.min.js' type='text/javascript'></script>
  <script src='/js/rest/lib/lodash.min.js' type='text/javascript'></script>
  <script src='/js/rest/lib/backbone-min.js' type='text/javascript'></script>
  <script src='/js/rest/swagger-ui.js' type='text/javascript'></script>
  <script src='/js/rest/lib/highlight.9.1.0.pack.js' type='text/javascript'></script>
  <script src='/js/rest/lib/highlight.9.1.0.pack_extended.js' type='text/javascript'></script>
  <script src='/js/rest/lib/jsoneditor.min.js' type='text/javascript'></script>
  <script src='/js/rest/lib/marked.js' type='text/javascript'></script>
  <script src='/js/rest/lib/swagger-oauth.js' type='text/javascript'></script>

  <!-- Some basic translations -->
  <!-- <script src='lang/translator.js' type='text/javascript'></script> -->
  <!-- <script src='lang/ru.js' type='text/javascript'></script> -->
  <!-- <script src='lang/en.js' type='text/javascript'></script> -->

  <script type="text/javascript">
    $(function () {
      var url = window.location.search.match(/url=([^&]+)/);
      if (url && url.length > 1) {
        url = decodeURIComponent(url[1]);
      } else {
        url = "http://localhost:8084/RestSpringMaven/api-docs";
      }

      hljs.configure({
        highlightSizeThreshold: 5000
      });

      // Pre load translate...
      if(window.SwaggerTranslator) {
        window.SwaggerTranslator.translate();
      }
      window.swaggerUi = new SwaggerUi({
        url: url,
        dom_id: "swagger-ui-container",
        supportedSubmitMethods: ['get', 'post'],
        onComplete: function(swaggerApi, swaggerUi){
          if(typeof initOAuth == "function") {
            initOAuth({
              clientId: "your-client-id",
              clientSecret: "your-client-secret-if-required",
              realm: "your-realms",
              appName: "your-app-name",
              scopeSeparator: ",",
              additionalQueryStringParams: {}
            });
          }

          if(window.SwaggerTranslator) {
            window.SwaggerTranslator.translate();
          }
        },
        onFailure: function(data) {
          log("Unable to Load SwaggerUI");
        },
        docExpansion: "none",
        jsonEditor: false,
        defaultModelRendering: 'schema',
        showRequestHeaders: false
      });

      window.swaggerUi.load();

      function log() {
        if ('console' in window) {
          console.log.apply(console, arguments);
        }
      }
  });
  </script>
</head>

<body class="swagger-section">
    <div id="message-bar" class="swagger-ui-wrap" data-sw-translate>&nbsp;</div>
    <div id="swagger-ui-container" class="swagger-ui-wrap"></div>
</body>
</html>
