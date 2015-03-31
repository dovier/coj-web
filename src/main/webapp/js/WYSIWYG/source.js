/**
 *
 * Editor WYSIWYG v1.0 RC3
 *
 * Este script convertira todos tus textareas en
 * editores WYSIWYG (What You See Is What You Get)
 * Un editor WYSIWYG permite al usuario escribir
 * textos con estilos de una manera practica y sencilla.
 * Funciona tanto en IE como en FireFox
 *
 * Este script ha sido desarrollado integramente
 * por Ivon Arias (arias@elleondeoro.com)
 *
 * Vista www.php-hispano.net y #php_para_torpes (iRC-Hispano)
 *
 */

var isIE = navigator.userAgent.toLowerCase().indexOf('msie') >= 0;
var isGECKO = navigator.userAgent.toLowerCase().indexOf('gecko') >= 0;

var textarea = null;
var config = { border: "1px solid #CCC", bgcolor: "#FFFFFF" };
var timers = new Array();
addEvent(window, "load", init);

function addEvent(object, event, handler) {
 if (isIE)
    object.attachEvent("on" + event, handler);
  else
    object.addEventListener(event, handler, false)
}

function init() {
  if (!isIE && !isGECKO) return;
  var elements = document.getElementsByTagName('textarea');
  for (var i=0; i<elements.length; i++)
    if (wantToMake(elements[i].id))
      makeWysiwyg(elements[i]);
}

function wantToMake(id) {
  if (!id || !isVar("excluidos")) return true;
  for (var i=0; i<excluidos.length; i++)
    if (excluidos[i] == id)
      return false;
  return true;
}

function isVar(variable) {
  try {
    eval("test = "+variable+";");
    return true;
  } catch (ex) {
    return false;
  }
}

function makeWysiwyg (element) {
  textarea = element;
  if (!element.id) element.id = getValidId(element.name);

  var edit = element.id+'_edit';
  var style = getStyle(element);

  html = '\
    <div style="border: '+style['border']+'; width: '+style['width']+';">\
    \<div style="border-top: '+style['border']+'; background-color: #F0F0EE; padding: 0px;" align="center">\
    '+addImage("bold.gif", edit, "bold", "Negrita")+'\
    '+addImage("italic.gif", edit, "italic", "Cursiva")+'\
    '+addImage("underline.gif", edit, "underline", "Subrayado")+'\
    '+addImage("strikethrough.gif", edit, "strikethrough", "Tachado")+'\
    '+addSeparator()+'\
    '+addImage("link.gif", edit, "createlink", "Insertar enlace")+'\
    '+addImage("unlink.gif", edit, "unlink", "Quitar enlace")+'\
    '+addImage("image.gif", edit, "insertimage", "Añadir imagen")+'\
    '+addSeparator()+'\
    '+addImage("bullist.gif", edit, "insertunorderedlist", "Lista sin ordenar")+'\
    '+addImage("numlist.gif", edit, "insertorderedlist", "Lista sin ordenar")+'\
    <br>\
    '+addImage("undo.gif", edit, "undo", "Deshacer")+'\
    '+addImage("redo.gif", edit, "redo", "Rehacer")+'\
    '+addSeparator()+'\
    '+addImage("left.gif", edit, "justifyleft", "Alinear a la izquierda")+'\
    '+addImage("center.gif", edit, "justifycenter", "Alinear al centro")+'\
    '+addImage("right.gif", edit, "justifyright", "Alinear a la derecha")+'\
    '+addImage("full.gif", edit, "justifyfull", "Alinear justificado")+'\
    '+addSeparator()+'\
    '+addImage("outdent.gif", edit, "outdent", "Disminuir sangria")+'\
    '+addImage("indent.gif", edit, "indent", "Aumentar sangria")+'\
    '+addSeparator()+'\
    '+addImage("emoticons.gif", edit, "emoticons", "Despeglar emoticons")+'\
    </div>\
    <span id="'+edit+'">IFRAME</span>\
    \
    </div>\
    <table id="'+element.id+'_emo" cellspacing="0" style="position: absolute; visibility: hidden;" onmouseover="cancelTimer(\''+element.id+'_emo\')"; onmouseout="addTimer(\''+element.id+'_emo\');">\
    <tr><td style="border: '+style['border']+'; background: #F0F0EE;">\
    '+addEmoticon('angry.gif', edit, "Enfadado")+'\
    '+addEmoticon('biggrin.gif', edit, "Sonriente")+'\
    '+addEmoticon('blush.gif', edit, "Sonrojado")+'\
    '+addEmoticon('confused.gif', edit, "Confuso")+'<br/>\
    '+addEmoticon('cool.gif', edit, "Chulo")+'\
    '+addEmoticon('crazy.gif', edit, "Loco")+'\
    '+addEmoticon('cry.gif', edit, "Llorando")+'\
    '+addEmoticon('doze.gif', edit, "Adormilado")+'<br/>\
    '+addEmoticon('hehe.gif', edit, "Hehe")+'\
    '+addEmoticon('laugh.gif', edit, "Riendo")+'\
    '+addEmoticon('plain.gif', edit, "Enfadado")+'\
    '+addEmoticon('rolleyes.gif', edit, "Juegueton")+'<br/>\
    '+addEmoticon('sad.gif', edit, "Triste")+'\
    '+addEmoticon('satisfied.gif', edit, "Satisfecho")+'\
    '+addEmoticon('smile.gif', edit, "Sonriente")+'\
    '+addEmoticon('shocked.gif', edit, "Sorprendido")+'<br/>\
    '+addEmoticon('tongue.gif', edit, "Sacando la lengua")+'\
    '+addEmoticon('wink.gif', edit, "Guiñado el ojo")+'\
    </td></tr></table>';

  if (isIE)
    element.insertAdjacentHTML("beforeBegin", html);
  else {
    var rng = element.ownerDocument.createRange();
    rng.setStartBefore(element);
    element.parentNode.insertBefore(rng.createContextualFragment(html), element);
  }

  element.style.display = 'none';
  element.startContent = element.value;

  var span = document.getElementById(edit);
  var iframe = document.createElement("iframe");
  iframe.id = edit;
  iframe.border = iframe.frameBorder = iframe.marginWidth = iframe.marginHeight = iframe.leftMargin = iframe.topMargin = "0";
  iframe.style.width = "100%";
  iframe.style.height = style['height'];

  if (isIE) {
    iframe.src = "WYSIWYG/blank.html";
    span.outerHTML = iframe.outerHTML;
    getDocument(iframe).bgColor = style['bgcolor'];
  } else {
    span.parentNode.replaceChild(iframe, span);
    var doc = getDocument(iframe);
    doc.designMode = "on";
    doc.open();
    doc.write('<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">'
             +'<html>'
             +'<head>'
             +'<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />'
             +'</head>'
             +'<body style="padding: 2px; margin: 0; spacing: 0; word-wrap: break-word;">'
             +element.value
             +'</body>'
             +'</html>');
    doc.close();
    iframe.style.backgroundColor = style['bgcolor'];
  }

  var doc1 = getDocument(iframe);
  doc1.editor = edit;
  addEvent(doc1, "keyup", setTextareaContent);
  if (element.form)
    addEvent(element.form, "reset", resetForm);
}

function mensaje (event) {
  alert(event);
}

function mostrarPanel(panel, event) {
  var source = getEventSource(event);
  var target = document.getElementById(panel);
  target.style.left = isIE? event.clientX + document.body.scrollLeft : event.pageX;
  target.style.top  = isIE? event.clientY + document.body.scrollTop : event.pageY;
  target.style.visibility = "visible";
}

function ocultarPanel(panel) {
  document.getElementById(panel).style.visibility = 'hidden';
}

function addTimer(source) {
  if (!timers[source])
    timers[source] = setTimeout("ocultarPanel('"+source+"');", 500)
}

function cancelTimer(source) {
  if (timers[source]) {
    clearTimeout(timers[source]);
    timers[source] = false;
  }
}

function getStyle(textarea) {
  return isIE? getStyleIE(textarea) : getStyleNoIE(textarea);
}

function getStyleIE(textarea) {
  var border = textarea.style.border ? textarea.style.border : null;
  var bgcolor = textarea.style.backgroundColor ? textarea.style.backgroundColor : null;
  var width = textarea.style.width ? textarea.style.width : null;
  var height = textarea.style.height ? textarea.style.height : null;

  var rules;
  var styles = document.styleSheets;
  for (var i=0; i<styles.length; i++) {
    rules = styles[i].rules;
    for (var j=0; j<rules.length; j++) {
      if (rules[j].selectorText == "TEXTAREA") {
        if (!border) border = rules[j].style.border;
        if (!bgcolor) bgcolor = rules[j].style.backgroundColor;
        if (!width) width = rules[j].style.width;
        if (!height) height = rules[j].style.height;
      }        
    }
  }

  if (!border) border = config['border'];
  if (!bgcolor) bgcolor = config['bgcolor'];
  if (!width) width = textarea.offsetWidth;
  if (!height) height = textarea.offsetHeight;

  if (bgcolor.length == 4) {
    bgcolor = "#"+bgcolor.charAt(1)+bgcolor.charAt(1)+bgcolor.charAt(2)+bgcolor.charAt(2)+bgcolor.charAt(3)+bgcolor.charAt(3);
  }

  return { border: border, bgcolor: bgcolor, width: width, height: height };
}

function getStyleNoIE(textarea) {
  var border = textarea.style.border ? textarea.style.border : null;
  var bgcolor = textarea.style.backgroundColor ? textarea.style.backgroundColor : null;
  var width = textarea.style.width ? textarea.style.width : null;
  var height = textarea.style.height ? textarea.style.height : null;

  var rules, text, aux;
  var styles = document.styleSheets;
  for (var i=0; i<styles.length; i++) {
    rules = styles[i].cssRules;
    for (var j=0; j<rules.length; j++) {
      text = rules[j].cssText.toLowerCase();
      if (text.match("[^{]*textarea[^{]\\{")) {
        if (!border && (aux = text.match("(\\s|;)border:([^;]*);"))) border = aux[2];
        if (!bgcolor && (aux = text.match("(\\s|;)background-color:([^;]*);"))) bgcolor = aux[2];
        if (!width && (aux = text.match("(\\s|;)width:([^;]*);"))) width = aux[2];
        if (!height && (aux = text.match("(\\s|;)height:([^;]*);"))) height = aux[2];
      }
    }
  }

  if (!border) border = config['border'];
  if (!bgcolor) bgcolor = config['bgcolor'];
  if (!width) width = textarea.offsetWidth;
  if (!height) height = textarea.offsetHeight;

  return { border: border, bgcolor: bgcolor, width: width, height: height };
}

function getValidId(name) {
  var id;
  if (!name) name = "text";
  if (!document.getElementById(name))
    id = name;
  else
    for (var i=1; document.getElementById(id = name+i); i++);
  return id;
}

function getEventSource(event) {
  return isIE ? event.srcElement : event.target;
}

function setEventSource(event, src) {
  if (isIE)
    event.srcElement = src;
  else
    event.target = src;
}

function resetForm(event) {
  var source = getEventSource(event);
  var textareas = source.getElementsByTagName('textarea');
  var iframe;
  for (var i=0; i<textareas.length; i++) {
    iframe = document.getElementById(textareas[i].id+"_edit");
    if (iframe)
      getDocument(iframe).body.innerHTML = textareas[i].startContent;
  }
}

function setTextareaContent(event, element) {
  var source = document.getElementById(element ? element : getEventSource(event).ownerDocument.editor);
  var target = document.getElementById(source.id.substring(0, source.id.indexOf("_")));
  target.value = getDocument(source).body.innerHTML;
}

function addImage(src, editor, command, coment) {
  return '<img src="/js/WYSIWYG/images/actions/'+src+'" onmouseover="seleccionar(this, \'#B6BDD2\');" onmouseout="desseleccionar(this);" onmousedown="seleccionar(this, \'#8592B5\');" onclick="executeCommand(\''+editor+'\', \''+command+'\', event); desseleccionar(this);" style="border: 1px solid #F0F0EE; width; 2px; height: 20px; cursor: pointer;" alt="'+coment+'" title="'+coment+'">';
}

function addEmoticon (src, editor, coment) {
  return '<img src="/js/WYSIWYG/images/emoticons/'+src+'" onmouseover="this.style.backgroundColor = \'#B6BDD2\';" onmouseout="this.style.backgroundColor = \'#F0F0EE\';" onclick="executeCommand(\''+editor+'\', \'insertimage\', event, this.src);" style="width; 18px; height: 18px; cursor: pointer;" alt="'+coment+'" title="'+coment+'">';
}

function addSeparator() {
  return '<img src="/js/WYSIWYG/images/spacer.gif" style="width; 2px; height: 20px;" alt="" title="">';
}

function seleccionar(img, fondo) {
  img.style.backgroundColor = fondo;
  img.style.border = '1px solid #0A246A';
}

function desseleccionar(img) {
  img.style.backgroundColor = '#F0F0EE';
  img.style.border = '1px solid #F0F0EE';
}

function getDocument(iframe) {
  return isIE? document.frames[iframe.id].window.document : iframe.contentDocument;
}

function executeCommand(element, command, event, text) {
  var iframe = document.getElementById(element);
 
  if (command.toLowerCase() == "insertimage" && !text) {
    text = prompt("Introduce la URL de la imagen", "");
    if (text == null || text == "") return;
  }

  if (command.toLowerCase() == "createlink" && !isIE) {
    text = prompt("Introduce la URL del enlace", "");
    if (text == null || text.match("^\\s*$")) {
      iframe.contentWindow.focus();
      return;
    }
    if ((selection = iframe.contentWindow.getSelection()) == "") {
      var link = document.createElement("a");
      link.innerHTML = link.href = text;
      selection.getRangeAt(0).insertNode(link);
      selection.addRange(selection.getRangeAt(0).cloneRange());
      selection.getRangeAt(1).setEndAfter(link);
      selection.getRangeAt(1).setStartAfter(link);
      selection.removeRange(selection.getRangeAt(0));
      iframe.contentWindow.focus();
      return;
    }
  }

  if (command.toLowerCase() == "emoticons") {
    mostrarPanel(element.substring(0, element.indexOf("_"))+"_emo", event);
    return;
  }

  iframe.contentWindow.focus();
  getDocument(iframe).execCommand(command, false, text);

  setTextareaContent(event, element);
}
