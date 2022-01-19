package com.chuyx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chuyx.constant.NormalConstant;
import com.chuyx.mapper.BlogMapper;
import com.chuyx.pojo.dto.BlogDTO;
import com.chuyx.pojo.dto.Pager;
import com.chuyx.pojo.model.Blog;
import com.chuyx.pojo.model.Category;
import com.chuyx.pojo.model.User;
import com.chuyx.pojo.vo.BlogBaseVO;
import com.chuyx.service.BlogService;
import com.chuyx.service.CategoryService;
import com.chuyx.service.CommentsService;
import com.chuyx.service.UserService;
import com.chuyx.utils.BlogUtils;
import com.chuyx.utils.DateUtils;
import com.chuyx.utils.DozerUtil;
import com.chuyx.utils.NormalUtils;
import com.chuyx.wrapper.BlogWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author yuxiang.chu
 */
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
    public Integer updateBlogVisitCount(Integer visitCount, Integer id) {
        return blogMapper.updateBlogVisitCount(visitCount, id);
    }

    @Override
    public Pager<BlogDTO> queryBlogByPage(int page, int size) {
        Page<Blog> pager = new Page<>(page,size);
        QueryWrapper<Blog> wrapper = new QueryWrapper<>();
        Page<Blog> blogPage = blogMapper.selectPage(pager, wrapper);
        Pager<BlogDTO> result = new Pager<>();
        result.setRows(pageBlogUtil(blogPage.getRecords()));
        result.setPage(page);
        result.setTotal(blogPage.getTotal());
        int allPageSize = Integer.parseInt(String.valueOf(blogPage.getTotal()))/size;
        if (blogPage.getTotal()%size > 0){
            allPageSize += 1;
        }
        result.setSize(allPageSize);
        return result;
    }

    @Override
    public List<BlogDTO> queryBlogSearch(String name) {
        QueryWrapper<Blog> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("title", name);
        List<Blog> blogList = blogMapper.selectList(queryWrapper);
        List<BlogDTO> blogDTOList = pageBlogUtil(blogList);
        blogDTOList.forEach(a ->{
            a.setTitle(a.getTitle().replaceAll(name, "<b style='color:#6bc30d'>" + name + "</b>"));
        });
        return blogDTOList;
    }

    @Override
    public Pager<BlogDTO> queryBlogByPageAuthor(Integer uid,Integer page) {
        QueryWrapper<Blog> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid", uid);
        Page<Blog> blogPage = new Page<>(page, NormalConstant.TOP_SIZE);
        Page<Blog> blogPageResult = blogMapper.selectPage(blogPage, queryWrapper);
        if (CollectionUtils.isEmpty(blogPageResult.getRecords())){
            return NormalUtils.pagerRows(blogPageResult, Collections.emptyList());
        }
        return NormalUtils.pagerRows(blogPageResult, pageBlogUtil(blogPageResult.getRecords()));
    }

    @Override
    public Integer deleteBlog(Integer id) {
        blogMapper.deleteById(id);
        commentsService.delCommentByBlogId(id);
        return 0;
    }

    @Override
    public Integer deleteBlog(Integer id, Integer uid) {
        QueryWrapper<Blog> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        queryWrapper.eq("uid", uid);
        return blogMapper.delete(queryWrapper);
    }

    @Override
    public Integer getAllBlogSize() {
        return blogMapper.selectCount(new QueryWrapper<>());
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
            wrapper.eq("category_id", req.getOrdinaryId());
        }
        wrapper.orderByDesc("release_date");
        IPage<Blog> blog = blogMapper.selectPage(page, wrapper);
        if (CollectionUtils.isEmpty(page.getRecords())){
            return result;
        }
        blog.getRecords().forEach((a) -> rows.add(blogToBlogVO(a)));
        return NormalUtils.pagerRows(blog, rows);
    }

    @Override
    public BlogBaseVO queryBlogById(Integer id) {
        Blog blog = blogMapper.selectById(id);
        return blogToBlogVO(blog);
    }

    @Override
    public Integer save(BlogWrapper.SaveBlogDTO req, Integer uid) {
        Blog blog = DozerUtil.map(req, Blog.class);
        blog.setReleaseDate(DateUtils.getSqlNowDate());
        blog.setUid(uid);
        blog.setCategoryId(req.getCapacity());
        blog.setVisitCount(NormalConstant.ZERO);
        if (blog.getId() != null){
            UpdateWrapper<Blog> blogUpdateWrapper = new UpdateWrapper<>();
            blogUpdateWrapper.eq("uid", uid);
            blogUpdateWrapper.eq("id", req.getId());
            return blogMapper.update(blog, blogUpdateWrapper);
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

    @Override
    public List<BlogBaseVO> searchBlogByComment(String comment) {
        QueryWrapper<Blog> query = new QueryWrapper<>();
        if (StringUtils.isEmpty(comment)){
            return Collections.emptyList();
        }
        query.like("title", comment);
        Page<Blog> result = blogMapper.selectPage(NormalConstant.RANKING_PAGE, query);
        if (CollectionUtils.isEmpty(result.getRecords())){
            return Collections.emptyList();
        }
        return result.getRecords().stream().map((b) -> {
            BlogBaseVO blogBaseVO = blogToBlogVO(b);
            // 高亮替换
            blogBaseVO.setTitle(blogBaseVO.getTitle().replaceAll(comment, "<b style='color:#6bc30d'>" + comment + "</b>"));
            return blogBaseVO;
        }).collect(Collectors.toList());
    }

    @Override
    public Map<Integer, String> getBlogIdNameMap(List<Integer> blogIdList) {
        if (CollectionUtils.isEmpty(blogIdList)){
            return Collections.emptyMap();
        }
        QueryWrapper<Blog> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id", blogIdList);
        List<Blog> blogs = blogMapper.selectList(queryWrapper);
        if (CollectionUtils.isEmpty(blogs)){
            return Collections.emptyMap();
        }
        return blogs.stream().collect(Collectors.toMap(Blog::getId, Blog::getTitle, (ok, nk) -> ok));
    }

    @Override
    public boolean checkBlogInCategory(Integer id) {
        QueryWrapper<Blog> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_id", id);
        return !CollectionUtils.isEmpty(blogMapper.selectList(queryWrapper));
    }

    @Override
    public Pager<BlogDTO> searchBlog(BlogWrapper.SearchBlogDTO searchBlogDTO) {
        String blogTitle = searchBlogDTO.getBlogTitle();
        String date = searchBlogDTO.getDate();
        QueryWrapper<Blog> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(date)){
            String[] split = date.split(" - ");
            List<Date> collect = Arrays.stream(split).map(a -> DateUtils.stringToSqlDate(a)).collect(Collectors.toList());
            queryWrapper.ge("release_date", collect.get(0)).le("release_date", collect.get(1));
        }
        if (!StringUtils.isEmpty(blogTitle)){
            queryWrapper.or().like("title", blogTitle);
        }
        List<Blog> blogs = blogMapper.selectList(queryWrapper);
        Page<Blog> userPager = new Page<>();
        if (CollectionUtils.isEmpty(blogs)){
            return  NormalUtils.pagerRows(userPager, null);
        }
        userPager.setRecords(blogs);
        userPager.setTotal(Long.parseLong(String.valueOf(blogs.size())));
        userPager.setSize(Long.parseLong(String.valueOf(NormalConstant.ONE)));
        return NormalUtils.pagerRows(userPager, pageBlogUtil(userPager.getRecords()));
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

    /**
     * 数据转换
     * @param blogs 转换前
     * @return 转换完毕
     */
    public List<BlogDTO> pageBlogUtil(List<Blog> blogs) {
        if (CollectionUtils.isEmpty(blogs)){
            return Collections.emptyList();
        }
        Map<Integer, String> uidNameMap = userService.getUidNameMap(blogs.stream().map(Blog::getUid).distinct().collect(Collectors.toList()));
        Map<Integer, String> categoryIdNameMap = categoryService.getCategoryIdNameMap(blogs.stream().map(Blog::getCategoryId).distinct().collect(Collectors.toList()));
        // 评论量
        Map<Integer, Long> blogIdCommentsMap = commentsService.getBlogIdCommentsMap(blogs.stream().map(Blog::getId).distinct().collect(Collectors.toList()));
        return blogs.stream().map(a -> {
            BlogDTO item = DozerUtil.map(a, BlogDTO.class);
            if (categoryIdNameMap.containsKey(a.getCategoryId())){
                item.setCatecoty(categoryIdNameMap.get(a.getCategoryId()));
            }
            if (uidNameMap.containsKey(a.getUid())){
                item.setAuthor(uidNameMap.get(a.getUid()));
            }
            if (blogIdCommentsMap.containsKey(a.getId())){
                item.setCount(Integer.parseInt(String.valueOf(blogIdCommentsMap.get(a.getId()))));
            }else{
                item.setCount(NormalConstant.ZERO);
            }
            BlogUtils.BlogDateToYMD(a, item);
            return item;
        }).collect(Collectors.toList());
    }
}
