package com.github.bestheroz.standard.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

public class TestMyFilterUtils {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final List<String> dirtyCodes = Arrays.asList("<applet><!-- abc --></applet>", "<img src='ftp://aaa'>", "<A href=\\\"javaScript:popImage('6921730')\\\" ></A>",
            "<A style=\\\"expression\\\" ></A>", "<embed src=\"\"></embed>", "<object></object>", "<SCRIPT SRC=http://xxx/xss.js></SCRIPT>", "<IMG SRC=\"javascript:alert!('XSS');\">",
            "<IMG SRC=javascript:alert!('XSS')>", "<IMG SRC=JaVaScRiPt:alert!('XSS')>", "<IMG SRC=javascript:alert!(\"XSS\")>", "<IMG SRC=`javascript:alert!(\"RSnake says, 'XSS'\")`>",
            "<IMG \"\"\"><SCRIPT>alert!(\"XSS\")</SCRIPT>\">", "<IMG SRC=javascript:alert!(String.fromCharCode(88,83,83))>", "<IMG SRC=javascript:alert('XSS')>", "<IMG SRC=javascript:alert('XSS')>",
            "<IMG SRC=&#x6A&#x61&#x76&#x61&#x73&#x63&#x72&#x69&#x70&#x74&#x3A&#x61&#x6C&#x65&#x72&#x74&#x28&#x27&#x58&#x53&#x53&#x27&#x29>", "<IMG SRC=\"jav ascript:alert!('XSS');\">",
            "<IMG SRC=\"jav ascript:alert!('XSS');\">", "<IMG SRC=\"jav ascript:alert!('XSS');\">", "<IMG SRC=\"jav ascript:alert!('XSS');\">", "<IMG SRC=\"   javascript:alert!('XSS');\">",
            "<IMG SRC=\"javascript:alert!('XSS')\"", "<IMG DYNSRC=\"javascript:alert!('XSS')\">", "<IMG LOWSRC=\"javascript:alert!('XSS')\">", "<IMG SRC='vbscript!:msgbox(\"XSS\")'>",
            "<SCRIPT/XSS SRC=\"http://xxxx/xss.js\"></SCRIPT>", "</TITLE><SCRIPT>alert!(\"XSS\");</SCRIPT>", "<INPUT TYPE=\"IMAGE\" SRC=\"javascript:alert!('XSS');\">",
            "<BODY BACKGROUND=\"javascript:alert!('XSS')\">", "<BODY ONLOAD=alert!('XSS')>", "<META HTTP-EQUIV=\"refresh\" CONTENT=\"0;url=javascript:alert!('XSS');\">",
            "<META HTTP-EQUIV=\"refresh\" CONTENT=\"0; URL=http://;URL=javascript:alert!('XSS');\">", "<IFRAME SRC=\"javascript:alert!('XSS');\"></IFRAME>", "<iframe src=http://xxxx/scriptlet.html <",
            "<FRAMESET><FRAME SRC=\"javascript:alert!('XSS');\"></FRAMESET>", "<TABLE BACKGROUND=\"javascript:alert!('XSS')\">", "<TABLE><TD BACKGROUND=\"javascript:alert!('XSS')\">",
            "<DIV STYLE=\"background-image: url(javascript:alert!('XSS'))\">",
            "<DIV STYLE=\"background-image:\0075\0072\006C\0028'\006a\0061\0076\0061\0073\0063\0072\0069\0070\0074\003a\0061\006c\0065\0072\0074\0028.1027\0058.1053\0053\0027\0029'\0029\">",
            "<DIV STYLE=\"background-image: url(javascript:alert!('XSS'))\">", "<DIV STYLE=\"width: expression!(alert!('XSS'));\">", "<STYLE>@import!'http://xxx/xss.css';</STYLE>",
            "<XSS STYLE=\"behavior!: url(xss.htc);\">", "<STYLE>li {list-style-image: url(\"javascript:alert!('XSS')\");}</STYLE><UL><LI>XSS",
            "<STYLE>@im\\port'\\ja\\vasc\\ript:alert!(\"XSS\")';</STYLE>", "<IMG STYLE=\"xss:expr/*XSS*/ession(alert!('XSS'))\">", "<XSS STYLE=\"xss:expression!(alert!('XSS'))\">",
            "<STYLE>.XSS{background-image:url(\"javascript:alert!('XSS')\");}</STYLE><A CLASS=XSS></A> ", "<STYLE type=\"text/css\">BODY{background:url(\"javascript:alert!('XSS')\")}</STYLE>",
            "<LINK REL=\"stylesheet\" HREF=\"javascript:alert!('XSS');\">", "<LINK REL=\"stylesheet\" HREF=\"http://xxx/xss.css\">", "<!--[if gte IE 4]><SCRIPT>alert!('XSS');</SCRIPT><![endif]-->",
            "<BASE HREF=\"javascript:alert!('XSS');//\">", "<EMBED SRC=http://xxxx/xss.swf AllowScriptAccess=\"always\"></EMBED>", "<<SCRIPT>alert!(\"XSS\");//<</SCRIPT>",
            "<SCRIPT>a=/XSS/alert!(a.source)</SCRIPT>", "#\\\";alert!('XSS');//", "#¼script¾alert!(¢XSS¢)¼/script¾");

    private final List<String> dirtyMarkupCodes = Arrays.asList("<SCRIPT>", "<SCRIPT SRC=http://hacker-site.com/xss.js></SCRIPT>", "<SCRIPT> alert(“XSS”); </SCRIPT>", "<BODY>",
            "<BODY ONLOAD=alert(\"XSS\")>", "<BODY BACKGROUND=\"javascript:alert('XSS')\">", "<IMG>", "<IMG SRC=\"javascript:alert('XSS');\">", "<IMG DYNSRC=\"javascript:alert('XSS')\">",
            "<IMG LOWSRC=\"javascript:alert('XSS')\">", "<IFRAME>", "<IFRAME SRC=”http://hacker-site.com/xss.html”>", "<INPUT>", "<INPUT TYPE=\"IMAGE\" SRC=\"javascript:alert('XSS');\">", "<LINK>",
            "<LINK REL=\"stylesheet\" HREF=\"javascript:alert('XSS');\">", "<TABLE>", "<TABLE BACKGROUND=\"javascript:alert('XSS')\">", "<TD BACKGROUND=\"javascript:alert('XSS')\">", "<DIV>",
            "<DIV STYLE=\"background-image: url(javascript:alert('XSS'))\">", "<DIV STYLE=\"width: expression(alert('XSS'));\">", "<OBJECT>",
            "<OBJECT TYPE=\"text/x-scriptlet\" DATA=\"http://hacker.com/xss.html\">", "<EMBED>", "<EMBED SRC=\"http://hacker.com/xss.swf\" AllowScriptAccess=\"always\">");

    // @Test
    public void dirtycodes() {
        // https://github.com/naver/lucy-xss-filter/blob/master/src/test/resources/xss-dirtycodes.txt#L55
        // 에 나와있는 보안 이슈코드를 모두 처리할수 있어야한다.
        for (final String dirtyCode : this.dirtyCodes) {
            this.logger.debug("src         ||||  {}", dirtyCode);
            final String clean = MyEscapeUtils.escapeAll(dirtyCode);
            this.logger.debug("src -> dest ||||  {}", clean);
            this.logger.debug("dest -> src ||||  {}\n", MyEscapeUtils.unescapeAll(clean));
        }
    }

    // @Test
    public void dirtyMarkupCodes() {
        // https://github.com/naver/lucy-xss-filter/blob/master/docs/attack_markup
        // 에 나와있는 보안 이슈코드를 모두 처리할수 있어야한다.
        for (final String dirtyCode : this.dirtyMarkupCodes) {
            this.logger.debug("src         ||||  {}", dirtyCode);
            final String clean = MyEscapeUtils.escapeAll(dirtyCode);
            this.logger.debug("src -> dest ||||  {}", clean);
            this.logger.debug("dest -> src ||||  {}\n", MyEscapeUtils.unescapeAll(clean));
        }
    }
}
