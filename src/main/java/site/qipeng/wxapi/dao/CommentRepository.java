package site.qipeng.wxapi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import site.qipeng.wxapi.entity.Comment;
import site.qipeng.wxapi.entity.vo.CommentVO;

import java.util.List;
import java.util.Map;

public interface CommentRepository extends JpaRepository<Comment, Integer>, PagingAndSortingRepository<Comment, Integer>, JpaSpecificationExecutor<Comment> {


    @Query(value = "select new site.qipeng.wxapi.entity.vo.CommentVO(c.id, c.userId, c.videoId, c.createTime, c.content, u.nickname, u.headImg) from Comment c left join User u on c.userId=u.id where c.videoId=?1 order by c.createTime desc")
    List<CommentVO> findComment(Integer videoId);
}
