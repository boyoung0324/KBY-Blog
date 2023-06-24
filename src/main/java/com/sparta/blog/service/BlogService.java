package com.sparta.blog.service;

import com.sparta.blog.dto.BlogRequestDto;
import com.sparta.blog.dto.BlogResponseDto;
import com.sparta.blog.entity.Blog;
import com.sparta.blog.repository.BlogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BlogService {


    private final BlogRepository blogRepo;

    public BlogService(BlogRepository blogRe) {
        this.blogRepo = blogRe;
    } //생성자 주입


    //전체 조회
    public List<BlogResponseDto> getContent() {
        return blogRepo.findAllByOrderByModifiedAtDesc().stream().map(BlogResponseDto::new).toList();
    } 


    //삭제
    @Transactional
    public Integer deleteContent(Integer bno, String pwd) {


        Blog blog = findContent(bno);

        if (pwd.equals(blog.getPwd())) {
            blogRepo.deleteById(bno);
            return 1;
        } else return 0;


    }


    //업뎃 -> setter로 값 수정(udpate_Transaction)
    @Transactional
    public Blog updateContent(Integer bno, BlogRequestDto requestDto) {
        Blog blog = findContent(bno);


        if (blog.getPwd().equals(requestDto.getPwd())) blog.update(requestDto);

        return blog;
    }


    //작성
     @Transactional
    public BlogResponseDto writeContent(BlogRequestDto requestDto) {
        Blog blog = new Blog(requestDto);
        Blog saveBlog = blogRepo.save(blog);

        BlogResponseDto responseDto = new BlogResponseDto(saveBlog);
        return responseDto;
    }

    //번호 조회
    public Blog findContent(Integer bno) {
        return blogRepo.findById(bno).orElseThrow(() ->
                new IllegalArgumentException("선택한 메모가 존재하지 않습니다."));
    }

}
