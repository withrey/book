package com.book.task;

import com.book.dao.AdminDao;
import com.book.model.AttachImageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

@Component
public class AttachFileCheckTask {

    @Autowired
    private AdminDao adminDao;


    private String getFolderYesterDay() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Calendar cal = Calendar.getInstance();

        cal.add(Calendar.DATE, -1);

        String str = sdf.format(cal.getTime());

        return str.replace("-", File.separator);

    }

    // 1분마다 실행
//    @Scheduled(cron = "0 * * * * *")
    // 새벽1시 실행
    @Scheduled(cron = "0 0 1 * * *")
    public void checkFiles() throws Exception {

        // DB에 저장된 파일 리스트
        List<AttachImageVO> fileList = adminDao.checkFileList();

        // 비교 기준 파일 리스트(Path 객체)
        List<Path> checkFilePath = new ArrayList<Path>();

        // 원본 이미지
        fileList.forEach(vo -> {
            Path path = Paths.get("D:\\upload", vo.getUploadPath(), vo.getUuid() + "_" + vo.getFileName());
            checkFilePath.add(path);
        });

        // 썸네일 이미지
        fileList.forEach(vo -> {
            Path path = Paths.get("D:\\upload", vo.getUploadPath(), "s_" + vo.getUuid() + "_" + vo.getFileName());
            checkFilePath.add(path);
        });

        // 디렉토리 파일 리스트
        File targetDir = Paths.get("D:\\upload", getFolderYesterDay()).toFile();
        File[] targetFile = targetDir.listFiles();

        // 삭제 대상 파일 리스트(분류)
        List<File> removeFileList = new ArrayList<File>(Arrays.asList(targetFile));
        for(File file : targetFile) {
            checkFilePath.forEach(checkFile -> {
                if(file.toPath().equals(checkFile))
                    removeFileList.remove(file);
            });
        }

        // 삭제 대상 파일 제거
        for(File file : removeFileList) {
            file.delete();
        }

    }
}
