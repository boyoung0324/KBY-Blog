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
        return blogRepo.findAll().stream().map(BlogResponseDto::new).toList();
    } //왜 response생성자 만드니까 작동을 하w죠?


    //삭제
    public Integer deleteContent(Integer bno) {
        Blog blog = findContent(bno);
        if (blog != null) {
            blogRepo.deleteById(bno);
        } else {
            System.out.println("해당하는 글이 없습니다.");
        }
        return bno;
    }


    //업뎃 -> setter로 값 수정(udpate_Transaction)
    @Transactional
    public Integer updateContent(Integer bno, BlogRequestDto requestDto) {
        Blog blog = findContent(bno);

        if (blog != null) {
            blog.update(requestDto);
        } else {
            System.out.println("해당하는 글이 없습니다.");
        }
        return bno;
    }


    //작성
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
