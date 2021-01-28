package com.chuyx.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chuyx.constant.NormalConstant;
import com.chuyx.mapper.BlogMapper;
import com.chuyx.pojo.dto.*;
import com.chuyx.pojo.model.Blog;
import com.chuyx.pojo.model.Category;
import com.chuyx.pojo.model.User;
import com.chuyx.pojo.vo.BlogBaseVO;
import com.chuyx.service.BlogService;
import com.chuyx.service.CategoryService;
import com.chuyx.service.CommentsService;
import com.chuyx.service.UserService;
import com.chuyx.utils.BlogUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.chuyx.utils.DateUtils;
import com.chuyx.utils.DozerUtil;
import com.chuyx.utils.NormalUtils;
import com.chuyx.wrapper.BlogWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    BlogMapper blogMapper;
    @Autowired
    CategoryService categoryService;
    @Autowired
    CommentsService commentsService;
    @Autowired
    UserService userService;

    @Override
    public List<Blog> queryAllBlog() {
        return this.blogMapper.queryAllBlog();
    }

    @Override
    public int queryAllBlogSize() {
        return this.blogMapper.queryAllBlogSize();
    }

    @Override
    public List<Blog> queryBlogByCateId(int categoryId) {
        return this.blogMapper.queryBlogByCateId(categoryId);
    }

    @Override
    public List<Blog> queryNewBlog() {
        return this.blogMapper.queryNewBlog();
    }

    @Override
    public Blog queryBlogById(int id) {
        return this.blogMapper.queryBlogById(id);
    }

    @Override
    public Integer updateBlogVisitCount(Integer visitCount, Integer id) {
        return blogMapper.updateBlogVisitCount(visitCount, id);
    }

    @Override
    public List<Blog> queryCapacityBlog(int capaId) {
        return this.blogMapper.queryCapacityBlog(capaId);
    }

    @Override
    public Pager<BlogDTO> queryBlogByPage(int page, int size) {
        int index = (page - 1) * size;
        List<Blog> blogs = blogMapper.queryBlogByPage(index, size);
        Pager<BlogDTO> result = new Pager();
        List<BlogDTO> blogDTOS = pageBlogUtil(blogs);
        result.setRows(blogDTOS);
        int countSize = blogMapper.countSize();
        result.setTotal((long) countSize);
        result.setPage(page);
        if (countSize < size) {
            result.setSize(1);
        } else if (countSize % size > 0) {
            result.setSize(countSize / size + 1);
        } else {
            result.setSize(countSize / size);
        }

        return result;
    }

    @Override
    public Pager<BlogDTO> queryBlogByPageCata(int cataId, int page, int size) {
        int index = (page - 1) * 5;
        List<Blog> blogs = this.blogMapper.queryBlogByPageCata(cataId, index, size);
        Pager<BlogDTO> result = new Pager();
        List<BlogDTO> blogDTOS = this.pageBlogUtil(blogs);
        result.setRows(blogDTOS);
        int countSize = this.blogMapper.countSizeCata(cataId);
        result.setTotal((long) countSize);
        result.setPage(page);
        if (countSize < 5) {
            result.setSize(1);
        } else if (countSize / 5 > 0) {
            result.setSize(countSize / 5 + 1);
        } else {
            result.setSize(countSize / 5);
        }

        return result;
    }

    @Override
    public List<BlogDTO> queryBlogSearch(String name) {
        char[] chars = name.toCharArray();
        String name2 = "%" + name + "%";
        List<Blog> blogs = this.blogMapper.queryBlogSearch(name2);
        List<BlogDTO> blogDTOS = this.pageBlogUtil(blogs);
        Iterator var6 = blogDTOS.iterator();

        while (var6.hasNext()) {
            BlogDTO blogDTO = (BlogDTO) var6.next();
            blogDTO.setTitle(blogDTO.getTitle().replaceAll(name, "<b style='color:#6bc30d'>" + name + "</b>"));
        }

        return blogDTOS;
    }

    @Override
    public int addBlog(PublishBlogDTO publishBlogDTO, int uid) {
        NewBlogDTO newBlogDTO = new NewBlogDTO();
        newBlogDTO.setTitle(publishBlogDTO.getTitle());
        newBlogDTO.setSmallTitle(publishBlogDTO.getSmallTitle());
        newBlogDTO.setCatecoty(publishBlogDTO.getCapacity());
        newBlogDTO.setContent(publishBlogDTO.getContent());
        newBlogDTO.setUid(uid);
        newBlogDTO.setAuthor(this.userService.queryUserById(uid).getUname());
        newBlogDTO.setRepleseaDate(new Date());
        this.blogMapper.addBlog(newBlogDTO);
        return 0;
    }

    @Override
    public int updateBlog(PublishBlogDTO publishBlogDTO, int uid) {
        NewBlogDTO newBlogDTO = new NewBlogDTO();
        newBlogDTO.setTitle(publishBlogDTO.getTitle());
        newBlogDTO.setSmallTitle(publishBlogDTO.getSmallTitle());
        newBlogDTO.setCatecoty(publishBlogDTO.getCapacity());
        newBlogDTO.setContent(publishBlogDTO.getContent());
        newBlogDTO.setUid(uid);
        newBlogDTO.setAuthor(this.userService.queryUserById(uid).getUname());
        newBlogDTO.setId(publishBlogDTO.getId());
        this.blogMapper.updateBlog(newBlogDTO);
        return 0;
    }

    @Override
    public Pager<BlogDTO> queryBlogByPageAuthord(int uid) {
        Pager<BlogDTO> result = new Pager();
        List<Blog> blogs = this.blogMapper.queryBlogByAuthorId(uid);
        List<BlogDTO> blogDTOS = this.pageBlogUtil(blogs);
        result.setRows(blogDTOS);
        int count = this.blogMapper.queryBlogByAuthorIdCount(uid);
        result.setTotal((long) count);
        result.setPage(1);
        if (count <= 10) {
            result.setSize(1);
        } else if (count % 10 > 0) {
            result.setSize(count / 10 + 1);
        } else {
            result.setSize(count / 10);
        }

        return result;
    }

    @Override
    public Pager<BlogDTO> queryBlogByPageAuthordPage(int uid, int page) {
        Pager<BlogDTO> result = new Pager();
        int index = (page - 1) * 10;
        List<Blog> blogs = this.blogMapper.queryBlogByAuthorIdPage(uid, index, 10);
        List<BlogDTO> blogDTOS = this.pageBlogUtil(blogs);
        result.setRows(blogDTOS);
        int count = this.blogMapper.queryBlogByAuthorIdCount(uid);
        result.setTotal((long) count);
        result.setPage(page);
        if (count <= 10) {
            result.setSize(1);
        } else if (count % 10 > 0) {
            result.setSize(count / 10 + 1);
        } else {
            result.setSize(count / 10);
        }

        return result;
    }

    @Override
    public int deleteBlog(int id) {
        this.blogMapper.delBlog(id);
        this.commentsService.delCommentByBlogId(id);
        return 0;
    }

    @Override
    public int getAllBlogSize() {
        return this.blogMapper.countSize();
    }

    @Override
    public List<Blog> getHotBlog() {
        QueryWrapper<Blog> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("visit_count");
        return blogMapper.selectPage(NormalConstant.RANKING_PAGE, wrapper).getRecords();
    }

    @Override
    public List<Blog> getNewestBlog() {
        QueryWrapper<Blog> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("release_date");
        return blogMapper.selectPage(NormalConstant.RANKING_PAGE, wrapper).getRecords();
    }

    @Override
    public Pager<BlogBaseVO> queryPageBlog(BlogWrapper.QueryPageDTO req) {
        Pager<BlogBaseVO> result = new Pager<>();
        ArrayList<BlogBaseVO> rows = new ArrayList<>();
        Page<Blog> page = new Page<>(req.getPage(), req.getSize());
        QueryWrapper<Blog> wrapper = new QueryWrapper<>();
        if (req.getOrdinaryId() != null){
            wrapper.eq("categoryId", req.getOrdinaryId());
        }
        IPage<Blog> blog = blogMapper.selectPage(page, wrapper);
        if (CollectionUtils.isEmpty(page.getRecords())){
            return result;
        }
        blog.getRecords().forEach((a) -> rows.add(blogToBlogVO(a)));
        return NormalUtils.pagerRows(blog, rows);
    }

    @Override
    public BlogBaseVO queryBlogById2(Integer id) {
        Blog blog = blogMapper.selectById(id);
        return blogToBlogVO(blog);
    }

    @Override
    public Integer save(BlogWrapper.SaveBlogDTO req) {
        Blog blog = DozerUtil.map(req, Blog.class);
        blog.setReleaseDate(DateUtils.getSqlNowDate());
        // todo 获取uid 并set session
        if (blog.getId() != null){
            return blogMapper.updateById(blog);
        }
        return blogMapper.insert(blog);
    }

    @Override
    public Integer softDeleteBlog(Integer id) {
        if (id == null){
            return 0;
        }
        return blogMapper.deleteById(id);
    }


    /**
     * blog实体类转blog视图类
     *
     * @param blog 实体类
     * @return 视图类
     */
    private BlogBaseVO blogToBlogVO(Blog blog) {
        BlogBaseVO result = DozerUtil.map(blog, BlogBaseVO.class);
        setBlogVoData(blog, result);
        Category category = categoryService.getCategoryById(blog.getCategoryId());
        result.setCategory(category.getName());
        User user = userService.queryUserById(blog.getUid());
        if (user != null) {
            result.setAuthor(user.getUname());
        }
        int count = commentsService.queryCountByBlogId(blog.getId());
        result.setCount(count);
        return result;
    }

    /**
     * 将blog的发布时间分成年月日
     *
     * @param blog 博客po
     * @param blogVo 博客vo
     */
    private void setBlogVoData(Blog blog, BlogBaseVO blogVo) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String releaseTime = simpleDateFormat.format(blog.getReleaseDate());
        String year = releaseTime.substring(0, 4);
        blogVo.setYear(year);
        String month = releaseTime.substring(5, 7);
        blogVo.setMonth(month);
        String day = releaseTime.substring(8, 10);
        blogVo.setDay(day);
    }

    public List<BlogDTO> pageBlogUtil(List<Blog> blogs) {
        List<BlogDTO> blogDTOS = new ArrayList();
        Iterator var3 = blogs.iterator();

        while (var3.hasNext()) {
            Blog blog = (Blog) var3.next();
            BlogDTO blogDTO = new BlogDTO();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            simpleDateFormat.format(blog.getReleaseDate());
            blogDTO = BlogUtils.BolgDateToYMD(blog, blogDTO);
            BeanUtils.copyProperties(blog, blogDTO);
            Category category = this.categoryService.getCategoryById(blog.getCategoryId());
            blogDTO.setCatecoty(category.getName());
            int count = this.commentsService.queryCountByBlogId(blog.getId());
            blogDTO.setCount(count);
            User user = this.userService.queryUserById(blog.getUid());
            blogDTO.setAuthor(user.getUname());
            blogDTOS.add(blogDTO);
        }

        return blogDTOS;
    }
}
