package site.qipeng.wxapi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import site.qipeng.wxapi.entity.Banner;

public interface BannerRepository extends JpaRepository<Banner, Integer> {
}
