package edu.olezha.sandbox.customL10n;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

	public static void main(String[] args) {
		String key;
		while ((key = getL10nKey(template)) != null) {
			System.out.println(key);
			template = template.replaceAll("#\\{" + key + "}", "OK" + key);
		}
		System.out.println(template);
	}

	private static String getL10nKey(String template) {
		Pattern pattern = Pattern.compile("#\\{.*}");
		Matcher matcher = pattern.matcher(template);
		try {
			if (matcher.find()) {
				String key = matcher.group();
				return key.substring(2, key.length() - 1);
			}
		} catch (IllegalStateException ignored) {}
		return null;
	}

	private static String template = "<div style=\"float: left; text-align: right;\">\n" +
		 "    <form method=\"post\">\n" +
		 "        <input name=\"formPassword\" type=\"hidden\" value=\"${formPassword}\">\n" +
		 "        <label for=\"smtp-bind-port\">#{Freemail.InfoToadlet.smtp-port.title}</label>\n" +
		 "        <input id=\"smtp-bind-port\" name=\"smtp-bind-port\" type=\"text\" value=\"${smtpBindPort}\"/>\n" +
		 "        <br/>\n" +
		 "        <label for=\"smtp-bind-address\">#{Freemail.InfoToadlet.smtp-addr.title}</label>\n" +
		 "        <input id=\"smtp-bind-address\" name=\"smtp-bind-address\" type=\"text\" value=\"${smtpBindAddress}\"/>\n" +
		 "        <br/>\n" +
		 "        <label for=\"imap-bind-port\">#{Freemail.InfoToadlet.imap-port.title}</label>\n" +
		 "        <input id=\"imap-bind-port\" name=\"imap-bind-port\" type=\"text\" value=\"${imapBindPort}\"/>\n" +
		 "        <br/>\n" +
		 "        <label for=\"imap-bind-address\">#{Freemail.InfoToadlet.imap-addr.title}</label>\n" +
		 "        <input id=\"imap-bind-address\" name=\"imap-bind-address\" type=\"text\" value=\"${imapBindAddress}\"/>\n" +
		 "        <br/>\n" +
		 "        <input type=\"submit\" value=\"Save\">\n" +
		 "    </form>\n" +
		 "</div>\n" +
		 "<div style=\"clear: both;\"></div>\n";
}
