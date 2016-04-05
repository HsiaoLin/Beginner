package com.beginner.base.utils;

import org.junit.Assert;
import org.junit.Test;

import com.beginner.core.utils.PropertyUtil;


public class TestResourcesUtil {

	@Test
	public void testGetProperties() throws Exception {
		Assert.assertEquals("mysql", PropertyUtil.getProperties("type", "beginner.properties"));
		Assert.assertEquals(PropertyUtil.getProperties("type", "beginner.properties"),
				PropertyUtil.getProperties("type", "/data/app/beginner.properties"),
				PropertyUtil.getProperties("type", "D:/data/app/beginner.properties"));
	}

	@Test
	public void testGetProperty() throws Exception {
		Assert.assertEquals("mysql", PropertyUtil.getProperty("type", "beginner"));
	}

	@Test
	public void testGetXmlProperties() throws Exception {
		Assert.assertNotNull(PropertyUtil.getXmlProperties("ehcache.xml"));
	}

	@Test
	public void testGetXmlProperty() throws Exception {
		Assert.assertEquals("beginner", PropertyUtil.getXmlProperty("cache[@name]", "ehcache.xml"));
	}
}
