package com.book.controller;

import com.book.model.*;
import com.book.service.AdminService;
import com.book.service.AuthorService;
import com.book.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private OrderService orderService;


    @GetMapping("/main")
    public void adminMainGet() throws Exception {

        System.out.println("관리자 페이지 이동");

    }

    // 주문 삭제
    @PostMapping("/orderCancel")
    public String orderCancelPost(OrderCancelDTO dto) {

        orderService.orderCancel(dto);

        return "redirect:/admin/orderList?keyword=" + dto.getKeyword() + "&amount=" + dto.getAmount() + "&pageNum=" + dto.getPageNum();

    }

    // 주문 현황 페이지
    @GetMapping("/orderList")
    public String orderListGet(Criteria cri, Model model) {

        List<OrderDTO> list = adminService.getOrderList(cri);

        if(!list.isEmpty()) {
            model.addAttribute("list", list);
            model.addAttribute("pageMaker", new PageDTO(cri, adminService.getOrderTotal(cri)));
        } else {
            model.addAttribute("listCheck", "empty");
        }

        return "/admin/orderList";

    }

    // 상품 등록 페이지
    @GetMapping("/goodsEnroll")
    public void goodsEnrollGet(Model model) throws Exception {

        System.out.println("상품 등록 페이지 접속");

        ObjectMapper objm = new ObjectMapper();

        List list = adminService.cateList();

        String cateList = objm.writeValueAsString(list);

        model.addAttribute("cateList", cateList);

    }

    // 상품 등록
    @PostMapping("/goodsEnroll")
    public String goodsEnrollPost(BookVO book, RedirectAttributes rttr) {

        adminService.bookEnroll(book);

        rttr.addFlashAttribute("enroll_result", book.getBookName());

        return "redirect:/admin/goodsManage";

    }

    // 상품 관리 페이지
    @GetMapping("/goodsManage")
    public void goodsManageGet(Criteria cri, Model model) throws Exception {

        System.out.println("상품 관리 페이지 접속");

        // 상품 리스트 데이터
        List list = adminService.goodsGetList(cri);

        if(!list.isEmpty()) {
            model.addAttribute("list",list);
        }else{
            model.addAttribute("listCheck","empty");
            return;
        }

        // 페이지 인터페이스 데이터
        model.addAttribute("pageMaker", new PageDTO(cri, adminService.goodsGetTotal(cri)));

    }

    // 상품 조회 페이지
    @GetMapping({"/goodsDetail", "/goodsModify"})
    public void goodsGetInfoGet(int bookId, Criteria cri, Model model) throws JsonProcessingException {

        System.out.println("goodsGetInfo() : " + bookId);

        ObjectMapper mapper = new ObjectMapper();

        // 카테고리 리스트 데이터
        model.addAttribute("cateList", mapper.writeValueAsString(adminService.cateList()));

        // 목록 페이지 조건 정보
        model.addAttribute("cri", cri);

        // 조회 페이지 정보
        model.addAttribute("goodsInfo", adminService.goodsGetDetail(bookId));

    }

    //상품 수정
    @PostMapping("/goodsModify")
    public String goodsModifyPost(BookVO book, RedirectAttributes rttr) throws Exception {

        int result = adminService.goodsModify(book);

        rttr.addFlashAttribute("modify_result", result);

        return "redirect:/admin/goodsManage";

    }

    // 상품 삭제
    @PostMapping("/goodsDelete")
    public String goodsDeletePost(int bookId, RedirectAttributes rttr) {

        List<AttachImageVO> fileList = adminService.getAttachInfo(bookId);

        if(fileList != null) {

            List<Path> pathList = new ArrayList();

            fileList.forEach(vo -> {

                // 원본 이미지
                Path path = Paths.get("D:\\upload", vo.getUploadPath(), vo.getUuid() + "_" + vo.getFileName());
                pathList.add(path);

                // 썸네일 이미지
                path = Paths.get("D:\\upload", vo.getUploadPath(), "s_" + vo.getUuid() + "_" + vo.getFileName());
                pathList.add(path);

            });

            pathList.forEach(path -> {

                path.toFile().delete();

            });

        }

        int result = adminService.goodsDelete(bookId);

        rttr.addFlashAttribute("delete_result", result);

        return "redirect:/admin/goodsManage";

    }

    // 첨부 파일 업로드
    @PostMapping(value = "/uploadAjaxAction", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<AttachImageVO>> uploadAjaxActionPost(MultipartFile[] uploadFile) {

        // 이미지 파일 체크
        for(MultipartFile multipartFile : uploadFile) {

            File checkfile = new File(multipartFile.getOriginalFilename());
            String type = null;

            try {

                type = Files.probeContentType(checkfile.toPath());

            } catch (IOException e) {

                e.printStackTrace();

            }

            if(!type.startsWith("image")) {

                List<AttachImageVO> list = null;
                return new ResponseEntity<>(list, HttpStatus.BAD_REQUEST);

            }

        }

        String uploadFolder = "D:\\upload";

        // 날짜 폴더 경로
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date date = new Date();

        String str = sdf.format(date);

        String datePath = str.replace("-", File.separator);


        // 폴더 생성
        File uploadPath = new File(uploadFolder, datePath);

        if(uploadPath.exists() == false){
            uploadPath.mkdirs();
        }

        // 이미지 정보 담는 객체
        List<AttachImageVO> list = new ArrayList();

        for(MultipartFile multipartFile : uploadFile) {

            // 이미지 정보 객체
            AttachImageVO vo = new AttachImageVO();

            // 파일 이름
            String uploadFileName = multipartFile.getOriginalFilename();
            vo.setFileName(uploadFileName);
            vo.setUploadPath(datePath);

            // uuid 적용 파일 이름
            String uuid = UUID.randomUUID().toString();
            vo.setUuid(uuid);

            uploadFileName = uuid + "_" + uploadFileName;

            // 파일 위치, 파일 이름을 합친 File 객체
            File saveFile = new File(uploadPath, uploadFileName);

            // 파일 저장
            try {

                multipartFile.transferTo(saveFile);

                File thumbnailFile = new File(uploadPath, "s_" + uploadFileName);

                BufferedImage bo_image = ImageIO.read(saveFile);

                // 비율
                double ratio = 3;

                // 넓이 높이
                int width = (int)(bo_image.getWidth() / ratio);
                int height = (int)(bo_image.getHeight() / ratio);

                Thumbnails.of(saveFile)
                        .size(width,height)
                        .toFile(thumbnailFile);

            } catch (Exception e) {

                e.printStackTrace();

            }

            list.add(vo);

        }

        ResponseEntity<List<AttachImageVO>> result = new ResponseEntity<List<AttachImageVO>>(list, HttpStatus.OK);

        return result;

    }

    // 이미지 파일 삭제
    @PostMapping("/deleteFile")
    public ResponseEntity<String> deleteFile(String fileName) {

        File file = null;

        try {

            // 썸네일 파일 삭제
            file = new File("D:\\upload\\" + URLDecoder.decode(fileName, "UTF-8"));
            file.delete();

            // 원본 파일 삭제
            String originFileName = file.getAbsolutePath().replace("s_", "");
            System.out.println("originFileName : " + originFileName);
            file = new File(originFileName);
            file.delete();

        } catch (Exception e) {

            e.printStackTrace();
            return new ResponseEntity<String>("fail", HttpStatus.NOT_IMPLEMENTED);

        }

        return new ResponseEntity<String>("success", HttpStatus.OK);
    }

    // 작가 검색 팝업창
    @GetMapping("/authorPop")
    public void authorPopGet(Criteria cri, Model model) throws Exception {

        // pop창에 작가 5명씩 출력
        cri.setAmount(5);

        // 게시물 출력 데이터
        List list = authorService.authorGetList(cri);

        // 작가가 존재하는 경우
        if(!list.isEmpty()) {
            model.addAttribute("list",list);

        // 작가가 존재하지 않는 경우
        } else {
            model.addAttribute("empty", "empty");
        }

        // 페이지 이동 인터페이스
        model.addAttribute("pageMaker", new PageDTO(cri, authorService.authorGetTotal(cri)));

    }

    // 작가 등록 페이지
    @GetMapping("/authorEnroll")
    public void authorEnrollGet() throws Exception {

        System.out.println("작가 등록 페이지 접속");

    }

    // 작가 등록
    @PostMapping("/authorEnroll")
    public String authorEnrollPost(AuthorVO author, RedirectAttributes rttr) throws Exception {

        System.out.println("authorEnroll : " + author);

        authorService.authorEnroll(author);

        rttr.addFlashAttribute("enroll_result", author.getAuthorName());

        return "redirect:/admin/authorManage";

    }

    // 작가 관리 페이지
    @GetMapping("/authorManage")
    public void authorManageGet(Criteria cri, Model model) throws Exception {

        System.out.println("작가 관리 페이지 접속");

        // 작가 목록 출력 데이터
        List list = authorService.authorGetList(cri);

        if(!list.isEmpty()) {
            // 작가가 존재하는 경우
            model.addAttribute("list",list);
        }else{
            // 작가가 존재하지 않는 경우
            model.addAttribute("listCheck","empty");
        }

        // 페이지 이동 인터페이스 데이터
        model.addAttribute("pageMaker", new PageDTO(cri, authorService.authorGetTotal(cri)));

    }

    // 작가 상세, 정보 수정 페이지
    @GetMapping({"/authorDetail", "/authorModify"})
//    @GetMapping("/authorDetail")
    public void authorGetInfoGet(int authorId, Criteria cri, Model model) throws Exception {

        // 작가 관리 페이지 정보
        model.addAttribute("cri", cri);

        // 선택 작가 정보
        model.addAttribute("authorInfo", authorService.authorGetDetail(authorId));

    }

    // 작가 정보 수정
    @PostMapping("/authorModify")
    public String authorModifyPost(AuthorVO author, RedirectAttributes rttr) throws Exception {

        int result = authorService.authorModify(author);

        rttr.addFlashAttribute("modify_result", result);

        return "redirect:/admin/authorManage";

    }

    // 작가 정보 삭제
    @PostMapping("/authorDelete")
    public String authorDeletePost(int authorId, RedirectAttributes rttr) {

        int result = 0;

        try {

            result = authorService.authorDelete(authorId);

        } catch (Exception e) {

            // 삭제하려는 작가가 다른 테이블에 참조되어 있는 경우
            e.printStackTrace();
            result = 2;
            rttr.addFlashAttribute("delete_result", result);

            return "redirect:/admin/authorManage";

        }

        rttr.addFlashAttribute("delete_result", result);

        return "redirect:/admin/authorManage";

    }

}
