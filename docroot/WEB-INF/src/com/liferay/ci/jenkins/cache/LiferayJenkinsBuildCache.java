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

package com.liferay.ci.jenkins.cache;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;

/**
 * @author Manuel de la Peña
 */
public class LiferayJenkinsBuildCache {

	public LiferayJenkinsBuildCache() {
		_initialize();
	}

	public void clear() {
		for (String key : _buildsCache.keySet()) {
			clear(key);
		}
	}

	public void clear(String portletId) {
		Map<String, JSONArray> portletCache = _getPortletCache(portletId);

		portletCache.clear();
	}

	public boolean containsKey(String portletId, Object key) {
		Map<String, JSONArray> portletCache = _getPortletCache(portletId);

		return portletCache.containsKey(key);
	}

	public boolean containsValue(String portletId, Object value) {
		if (!_buildsCache.containsKey(portletId)) {
			_buildsCache.put(portletId, new HashMap<String, JSONArray>());
		}

		Map<String, JSONArray> portletCache = _getPortletCache(portletId);

		return portletCache.containsValue(value);
	}

	public Set<java.util.Map.Entry<String, JSONArray>> entrySet(
			String portletId) {

		Map<String, JSONArray> portletCache = _getPortletCache(portletId);

		return portletCache.entrySet();
	}

	public JSONArray get(String portletId, Object key) {
		Map<String, JSONArray> portletCache = _getPortletCache(portletId);

		return portletCache.get(key);
	}

	public boolean isEmpty(String portletId) {
		Map<String, JSONArray> portletCache = _getPortletCache(portletId);

		return portletCache.isEmpty();
	}

	public Set<String> keySet(String portletId) {
		Map<String, JSONArray> portletCache = _getPortletCache(portletId);

		return portletCache.keySet();
	}

	public JSONArray put(String portletId, String key, JSONArray value) {
		Map<String, JSONArray> portletCache = _getPortletCache(portletId);

		return portletCache.put(key, value);
	}

	public void putAll(
		String portletId, Map<? extends String, ? extends JSONArray> m) {

		Map<String, JSONArray> portletCache = _getPortletCache(portletId);

		portletCache.putAll(m);
	}

	public JSONArray remove(String portletId, Object key) {
		Map<String, JSONArray> portletCache = _getPortletCache(portletId);

		return portletCache.remove(key);
	}

	public int size(String portletId) {
		Map<String, JSONArray> portletCache = _getPortletCache(portletId);

		return portletCache.size();
	}

	public Collection<JSONArray> values(String portletId) {
		Map<String, JSONArray> portletCache = _getPortletCache(portletId);

		return portletCache.values();
	}

	private Map<String, JSONArray> _getPortletCache(String portletId) {
		if (!_buildsCache.containsKey(portletId)) {
			_buildsCache.put(portletId, new HashMap<String, JSONArray>());
		}

		return _buildsCache.get(portletId);
	}

	private void _initialize() {
		_buildsCache = new HashMap<String, Map<String, JSONArray>>();
	}

	private Map<String, Map<String, JSONArray>> _buildsCache;

}