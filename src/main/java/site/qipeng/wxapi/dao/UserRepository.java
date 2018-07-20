package site.qipeng.wxapi.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import site.qipeng.wxapi.entity.User;


public interface UserRepository extends JpaRepository<User, Integer> {

    User findByOpenId(String openId);
}
