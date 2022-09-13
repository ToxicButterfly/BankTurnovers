<%@ page import="java.util.List" %>
<%@ page import="com.example.bankturnovers.entity.SheetLine" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.12.1/css/jquery.dataTables.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.12.1/js/jquery.dataTables.js"></script>
    <script>$(document).ready( function () {
        $('#table_id').DataTable();
    } );</script>
    <title>Turnover Sheets</title>

</head>
<body>
<table id="table_id" class="display">
    <thead>
    <tr>
        <th rowspan="2">Б/сч</th>
        <th colspan="2">ВХОДЯЩЕЕ САЛЬДО</th>
        <th colspan="2">ОБОРОТЫ</th>
        <th colspan="2">ИСХОДЯЩЕЕ САЛЬДО</th>
    </tr>
    <tr>
        <th>Актив</th>
        <th>Пассив</th>
        <th>Дебит</th>
        <th>Кредит</th>
        <th>Актив</th>
        <th>Пассив</th>
    </tr>
    </thead>
    <tbody id="tbody">
    <%
        List<SheetLine> sheetLines = (List<SheetLine>) request.getAttribute("sheets");
        DecimalFormat df = new DecimalFormat("#.##########");
        for(SheetLine sheet: sheetLines) {
            out.println("<tr>");
            for(int d = 0; d < 7; d++) {
                switch(d) {
                    case 0: out.println("<td>" + sheet.getAccounting() + "</td>"); break;
                    case 1: out.println("<td>" + df.format(sheet.getIncomeSaldo().getActive()) + "</td>"); break;
                    case 2: out.println("<td>" + df.format(sheet.getIncomeSaldo().getPassive()) + "</td>"); break;
                    case 3: out.println("<td>" + df.format(sheet.getTurnovers().getDebit()) + "</td>"); break;
                    case 4: out.println("<td>" + df.format(sheet.getTurnovers().getCredit()) + "</td>"); break;
                    case 5: out.println("<td>" + df.format(sheet.getOutcomeSaldo().getActive()) + "</td>"); break;
                    case 6: out.println("<td>" + df.format(sheet.getOutcomeSaldo().getPassive()) + "</td>"); break;
                }
            }
            out.println("</tr>");
        }
    %>
    </tbody>
</table>
<br><br>
<%

    if(request.getAttribute("accounts")!=null) {
        List<String> accs = (List<String>) request.getAttribute("accounts");
        out.println("<h4>Choose lines you want to see by first two numbers of accounts</h4>");
        out.println("<select onchange=\"if (this.value) window.location.href+=this.value\">");
        out.println("<option selected disabled>Choose accounts</option>");
        for (int i = 0; i < accs.size(); i++) {
            out.println("<option value=/" +accs.get(i)+">" + accs.get(i) + "</option>");
        }
        out.println("</select>");
        out.println("</form>");
    }
%>

</body>
</html>
