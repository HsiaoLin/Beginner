package com.beginner.base.utils;

import org.junit.Assert;
import org.junit.Test;


public class TestResourcesUtil {
	@Test
	public void testGetProperties() throws Exception {
		Assert.assertEquals("mysql", ResourcesUtil.getProperties("type", "beginner.properties"));
		Assert.assertEquals(ResourcesUtil.getProperties("type", "beginner.properties"),
				ResourcesUtil.getProperties("type", "/data/app/beginner.properties"),
				ResourcesUtil.getProperties("type", "D:/data/app/beginner.properties"));
	}
	@Test
	public void testGetProperty() throws Exception {
		Assert.assertEquals("mysql", ResourcesUtil.getProperty("type", "beginner"));
	}
	@Test
	public void testGetXmlProperties() throws Exception {
		Assert.assertEquals(18, ResourcesUtil.getXmlProperties("ehcache.xml").size());
	}
	@Test
	public void testGetXmlProperty() throws Exception {
		Assert.assertEquals("beginner", ResourcesUtil.getXmlProperty("cache[@name]", "ehcache.xml"));
	}
}
