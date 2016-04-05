/*
 * Copyright 2015-9999 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.beginner.core.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@SuppressWarnings("rawtypes")
@Repository("dao")
public class DaoSupport implements DAO {

	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public Object save(String str, Object obj) throws Exception {
		return sqlSessionTemplate.insert(str, obj);
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public Object saveBatch(String str, Object obj) throws Exception {
		return sqlSessionTemplate.insert(str, obj);
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void batchSave(String str, List objs) throws Exception {
		SqlSessionFactory sqlSessionFactory = sqlSessionTemplate.getSqlSessionFactory();
		//批量执行器
		SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
		try {
			if (objs != null) {
				for (int i = 0, size = objs.size(); i < size; i++) {
					sqlSession.insert(str, objs.get(i));
				}
				sqlSession.flushStatements();
				sqlSession.commit();
				sqlSession.clearCache();
			}
		} finally {
			sqlSession.close();
		}
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public Object update(String str, Object obj) throws Exception {
		return sqlSessionTemplate.update(str, obj);
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void batchUpdate(String str, List objs) throws Exception {
		SqlSessionFactory sqlSessionFactory = sqlSessionTemplate.getSqlSessionFactory();
		//批量执行器
		SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
		try {
			if (objs != null) {
				for (int i = 0, size = objs.size(); i < size; i++) {
					sqlSession.update(str, objs.get(i));
				}
				sqlSession.flushStatements();
				sqlSession.commit();
				sqlSession.clearCache();
			}
		} finally {
			sqlSession.close();
		}
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void batchDelete(String str, List objs) throws Exception {
		SqlSessionFactory sqlSessionFactory = sqlSessionTemplate.getSqlSessionFactory();
		//批量执行器
		SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
		try {
			if (objs != null) {
				for (int i = 0, size = objs.size(); i < size; i++) {
					sqlSession.delete(str, objs.get(i));
				}
				sqlSession.flushStatements();
				sqlSession.commit();
				sqlSession.clearCache();
			}
		} finally {
			sqlSession.close();
		}
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public Object delete(String str, Object obj) throws Exception {
		return sqlSessionTemplate.delete(str, obj);
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public Object findForObject(String str, Object obj) throws Exception {
		return sqlSessionTemplate.selectOne(str, obj);
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public Object findForList(String str, Object obj) throws Exception {
		return sqlSessionTemplate.selectList(str, obj);
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public Object findForMap(String str, Object obj, String key, String value) throws Exception {
		return sqlSessionTemplate.selectMap(str, obj, key);
	}
}
