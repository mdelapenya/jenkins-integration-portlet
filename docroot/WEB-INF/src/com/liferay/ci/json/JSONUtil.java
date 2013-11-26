/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

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