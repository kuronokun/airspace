package com.mucahit.airspace.server.statics.html

import com.mucahit.airspace.core.conf.Configuration._

object StaticHTMLPages {

  final val mainPage: String =
    s"""
     <html>
       |<head>
       |    <title>Welcome to AirSpace</title>
       |</head>
       |<body>
       |
 |<table border="0" cellpadding="10">
       |    <tr>
       |        <td>
       |            <form action="$SERVER_BASE_URL/query">
       |                <input type="submit" value="Query Page" />
       |            </form>
       |        </td>
       |        <td>
       |            <form action="$SERVER_BASE_URL/report">
       |                <input type="submit" value="Report Page" />
       |            </form>
       |        </td>
       |    </tr>
       |</table>
       |</body>
       |</html>
    """.stripMargin

  final val queryPage: String =
    s"""
       |<html>
       |<head>
       |    <script>
       |        function getByCountryName(name) {
       |            var xmlHttp = new XMLHttpRequest();
       |            xmlHttp.open("GET", "$SERVER_BASE_URL/api/getByName/" + name, false);
       |            xmlHttp.send(null);
       |            document.getElementById("result").innerHTML = xmlHttp.responseText;
       |        }
       |    </script>
       |    <script>
       |        function getByCountryCode(code) {
       |            var xmlHttp = new XMLHttpRequest();
       |            xmlHttp.open("GET", "$SERVER_BASE_URL/api/getByCode/" + code, false);
       |            xmlHttp.send(null);
       |            document.getElementById("result").innerHTML = xmlHttp.responseText;
       |        }
       |    </script>
       |</head>
       |
      |<body>
       |<form>
       |    Country Code:<br>
       |    <input id="countryCode" type="text" name="firstname"><br>
       |    <button type="button" onclick=getByCountryCode(document.getElementById("countryCode").value)>Get!</button>
       |    <br>Country Name:<br>
       |    <input id="countryName" type="text" name="lastname"><br>
       |    <button type="button" onclick=getByCountryName(document.getElementById("countryName").value)>Get!</button>
       |</form>
       |
      |<p id="result"></p>
       |
      |</body>
       |
      |</html>
       |
    """.stripMargin

  final val reportPage: String =
    s"""
       |
      |<html>
       |<head>
       |    <script>
       |        function getTop10() {
       |            var xmlHttp = new XMLHttpRequest();
       |            xmlHttp.open("GET", "$SERVER_BASE_URL/api/top10", false);
       |            xmlHttp.send(null);
       |            document.getElementById("result").innerHTML = xmlHttp.responseText;
       |        }
       |    </script>
       |    <script>
       |        function getBottom10() {
       |            var xmlHttp = new XMLHttpRequest();
       |            xmlHttp.open("GET", "$SERVER_BASE_URL/api/bottom10", false);
       |            xmlHttp.send(null);
       |            document.getElementById("result").innerHTML = xmlHttp.responseText;
       |        }
       |    </script>
       |    <script>
       |        function getRunwaysPerCountry() {
       |            var xmlHttp = new XMLHttpRequest();
       |            xmlHttp.open("GET", "$SERVER_BASE_URL/api/getRunwaysPerCountry", false);
       |            xmlHttp.send(null);
       |            document.getElementById("result").innerHTML = xmlHttp.responseText;
       |        }
       |    </script>
       |</head>
       |
      |<body>
       |<form>
       |    Top 10:<br>
       |    <button type="button" onclick=getTop10()>Get!</button>
       |    <br>Bottom 10:<br>
       |    <button type="button" onclick=getBottom10()>Get!</button>
       |    <br>Runways per Country:<br>
       |    <button type="button" onclick=getRunwaysPerCountry()>Get!</button>
       |</form>
       |
      |<p id="result"></p>
       |
      |</body>
       |
      |</html>
       |
      |
""".stripMargin

}
