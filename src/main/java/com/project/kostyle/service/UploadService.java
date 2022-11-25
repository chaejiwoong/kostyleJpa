package com.project.kostyle.service;

import com.project.kostyle.dto.product.ProductImgDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UploadService {

    public List<ProductImgDto> upload(MultipartFile[] uploadFile) {

        String uploadFolder = "C:\\upload"; // 파일들이 저장될 경로

        List<ProductImgDto> list = new ArrayList<>(); // 파일들의정보를 담을 list

        // make folder -------------------
        String uploadFolderPath = getFolder(); // 현재 날짜에 맞는 날짜경로 반환
        File uploadPath = new File(uploadFolder, uploadFolderPath); // 기존 경로 + 날짜경로

        if (!uploadPath.exists()) { // 해당 폴더가 없다면 만들어라.
            uploadPath.mkdirs();
        }

        for (MultipartFile multipartFile : uploadFile) {

            ProductImgDto imgDto = new ProductImgDto(); // 파일의 정보를 담을 DTO

            String uploadFileName = multipartFile.getOriginalFilename(); // 파일의 진짜이름

            imgDto.setFileName(uploadFileName); // 오리지널파일명 저장

            UUID uuid = UUID.randomUUID();
            uploadFileName = uuid + "_" + uploadFileName; // 파일명의 중복 방지를 위한 uuid를 추가한 파일명

            try {
                File saveFile = new File(uploadPath, uploadFileName); // 날짜포함된 파일경로 + UUID 포함된 파일명
                // 파일 업로드
                multipartFile.transferTo(saveFile); // 해당 파일 업로드
                imgDto.setUuid(uuid.toString()); // uuid 저장
                imgDto.setFilePath(uploadFolderPath); // 날짜 폴더 경로 저장, 날짜 전의 폴더 경로는 모두 동일하기 때문에 날짜만 반환

                log.info("dto = {}", imgDto);

                list.add(imgDto);
            } catch (IOException e) {
                log.error(e.getMessage());
            }

        }
        return list;
    }

    public ResponseEntity<byte[]> showImg(String fileName) {

        // 파라미터로 넘어온 파일경로와 서버의 경로를 합친 file 객체 생성 => 파일의 Full Path
        File file = new File("c:\\upload\\" + fileName);

        ResponseEntity<byte[]> result = null;

        try {
            // 헤더정보를 담기 위한 HttpHeaders 객체 생성
            HttpHeaders header = new HttpHeaders();

            // 헤더의 해당 파일의 MIME 타입을 Content-Type 으로 추가
            header.add("Content-Type", Files.probeContentType(file.toPath()));
            // ResponseEntity 에 해당 파일정보를 Byte 코드로 변환한 값, 헤더, 상태코드를 담아서 리턴
            result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    private String getFolder() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 날짜 포맷 설정
        Date date = new Date(); // 현재 날짜의 date 객체 생성
        String str = sdf.format(date); // 현재 날짜를 포맷에 맞게 변환
        return str.replace("-", File.separator); // -를 \로 변환해서 폴더경로정보로 반환
    }

}
