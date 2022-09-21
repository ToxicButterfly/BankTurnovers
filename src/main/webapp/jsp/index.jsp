<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.util.Enumeration" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<html>
<body>
<h2>Excel parsing application</h2>
<h4>Press to parse file</h4>
<script type="text/javascript">
    function AjaxCallServlet() {
        var oReq = new XMLHttpRequest();
        oReq.onreadystatechange = function () {
            if(oReq.readyState == 4 && oReq.status == 200) {
                document.getElementById("somediv").innerHTML = oReq.responseText;
            }
        }
        oReq.open('GET', '/parse', true);
        oReq.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        oReq.send();
    }
    function getClass() {
        var select = document.getElementById('class');
        if(select.value != "Choose a class") {
            window.location.href += select.value;
        }
    }
    function getAll() { window.location.href += '/classes' }

</script>

<button onclick="AjaxCallServlet()">Parse file</button>
<div id="somediv"></div><br>
<h4>Choose class you wanna see</h4>
<form method="get">
    <select id="class" >
        <option selected disabled>Choose a class</option>
        <%
            int num = (int) request.getAttribute("classes");
            for(int i=1;i<num+1;i++) {
                out.println("<option value=\"/class/" + i +"\">" + i + "</option>");
            }
        %>
    </select>
</form>
<button type="button" onclick="getClass()" >Submit</button>
<br>
<h4>Or press if you wanna see the whole list</h4>
<button type="button" onclick="getAll()" >Submit</button>
</body>
</html>
