package site.qipeng.wxapi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import site.qipeng.wxapi.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
