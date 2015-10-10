package com.ran.service.system.user;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ran.dao.DaoSupport;
import com.ran.entity.Page;
import com.ran.entity.system.User;
import com.ran.util.PageData;


@Service("userService")
public class UserService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	//======================================================================================
	
	/*
	* 通过id获取数据
	*/
	public PageData findByUiId(PageData pd)throws Exception{
		return (PageData)dao.findForObject("UserMapper.findByUiId", pd);
	}
	/*
	* 通过loginname获取数据
	*/
	public PageData findByUId(PageData pd)throws Exception{
		return (PageData)dao.findForObject("UserMapper.findByUId", pd);
	}
	
	/*
	* 通过邮箱获取数据
	*/
	public PageData findByUE(PageData pd)throws Exception{
		return (PageData)dao.findForObject("UserMapper.findByUE", pd);
	}
	
	/*
	* 通过编号获取数据
	*/
	public PageData findByUN(PageData pd)throws Exception{
		return (PageData)dao.findForObject("UserMapper.findByUN", pd);
	}
	
	/*
	* 保存用户
	*/
	public void saveU(PageData pd)throws Exception{
		dao.save("UserMapper.saveU", pd);
	}
	/*
	* 修改用户
	*/
	public void editU(PageData pd)throws Exception{
		dao.update("UserMapper.editU", pd);
	}
	/*
	* 换皮肤
	*/
	public void setSKIN(PageData pd)throws Exception{
		dao.update("UserMapper.setSKIN", pd);
	}
	/*
	* 删除用户
	*/
	public void deleteU(PageData pd)throws Exception{
		dao.delete("UserMapper.deleteU", pd);
	}
	/*
	* 批量删除用户
	*/
	public void deleteAllU(String[] USER_IDS)throws Exception{
		dao.delete("UserMapper.deleteAllU", USER_IDS);
	}
	/*
	*用户列表(用户组)
	*/
	public List<PageData> listPdPageUser(Page page)throws Exception{
		return (List<PageData>) dao.findForList("UserMapper.userlistPage", page);
	}
	
	/*
	*用户列表(全部)
	*/
	public List<PageData> listAllUser(PageData pd)throws Exception{
		return (List<PageData>) dao.findForList("UserMapper.listAllUser", pd);
	}
	
	/*
	*用户列表(供应商用户)
	*/
	public List<PageData> listGPdPageUser(Page page)throws Exception{
		return (List<PageData>) dao.findForList("UserMapper.userGlistPage", page);
	}
	/*
	* 保存用户IP
	*/
	public void saveIP(PageData pd)throws Exception{
		dao.update("UserMapper.saveIP", pd);
	}
	
	/*
	* 登录判断
	*/
	public PageData getUserByNameAndPwd(PageData pd)throws Exception{
		return (PageData)dao.findForObject("UserMapper.getUserInfoEx", pd);
	}
	/*
	 * 通过用户名查找用户对象
	 */
	public PageData getUserByName(PageData pd)throws Exception{
		return (PageData)dao.findForObject("UserMapper.getUserByName", pd);
	}
	/*
	* 跟新登录时间
	*/
	public void updateLastLogin(PageData pd)throws Exception{
		dao.update("UserMapper.updateLastLogin", pd);
	}
	
	/*
	*通过id获取数据
	*/
	public User getUserAndRoleById(String userId) throws Exception {
		return (User) dao.findForObject("UserMapper.getUserAndRoleById", userId);
	}
	/*
	 *通过id获取数据
	 */
	public PageData findById(PageData pd) throws Exception {
		return (PageData) dao.findForObject("UserMapper.findById", pd);
	}
	/*
	 *通过id获取数据
	 */
	public void updateUserStatus(String[] ids) throws Exception {
		 dao.update("UserMapper.updateUserStatus", ids);
	}
	/*
	 *通过id删除数据
	 */
	public void deleteUser(String[] ids) throws Exception {
		dao.update("UserMapper.deleteUser", ids);
	}
	/*
	 *添加一个用户
	 */
	public void addUser(Map<String, Object> map) throws Exception {
		dao.update("UserMapper.addUser", map);
	}
	/*
	 *修改用户信息
	 */
	public void updateUser(Map<String, Object> map) throws Exception {
		dao.update("UserMapper.updateUser", map);
	}

	
}
