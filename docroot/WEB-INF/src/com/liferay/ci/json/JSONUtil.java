package com.liferay.ci.json;

import com.liferay.portal.kernel.util.StringBundler;

public class JSONUtil {

	private static String _buildColor(String color) {
		return "color: '" + color + "'";
	}

	public static String buildMarker(String color) {
		StringBundler sb = new StringBundler();

		sb.append("marker:{");
		sb.append("fill:{");
		sb.append(_buildColor(color));
		sb.append(_close());
		sb.append("border:{");
		sb.append(_buildColor(color));
		sb.append(_close());
		sb.append("over:{");
		sb.append("fill:{");
		sb.append(_buildColor("#ffffff"));
		sb.append(_close());
		sb.append("border:{");
		sb.append(_buildColor("#fe0000"));
		sb.append(_close());
		sb.append("width: 12,");
		sb.append("height: 12,");
		sb.append("}");
		sb.append(_close());

		return sb.toString();
	}

	private static String _close() {
		return "},";
	}

}